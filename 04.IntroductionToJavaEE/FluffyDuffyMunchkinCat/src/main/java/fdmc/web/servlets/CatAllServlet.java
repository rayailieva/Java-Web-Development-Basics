package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatAllServlet extends HttpServlet {

    private final static String ALL_CATS_HTML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaWebDevelopmentBasics\\04.IntroductionToJavaEE\\FluffyDuffyMunchkinCat\\src\\main\\resources\\views\\cat-all.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlFileContent = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);

        if(req.getSession().getAttribute("cats") == null){
            htmlFileContent = htmlFileContent
                    .replace("{{allCats}}", "There are no cats. <a href=\"/cats/create\">Create some!</a>");
        }else {
            StringBuilder allCats = new StringBuilder();

            ((Map<String, Cat>)req.getSession()
                    .getAttribute("cats"))
                    .values()
                    .forEach(cat -> {
                        allCats.append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br />",
                                cat.getName(), cat.getName()))
                                .append(System.lineSeparator());
                    });

            htmlFileContent = htmlFileContent.replace("{{allCats}}", allCats.toString().trim());

        }
        resp.getWriter().println(htmlFileContent);
    }
}