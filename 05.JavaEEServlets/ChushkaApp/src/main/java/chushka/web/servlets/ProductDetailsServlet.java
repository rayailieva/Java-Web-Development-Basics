package chushka.web.servlets;

import chushka.domain.models.view.ProductDetailsViewModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {

    private final static String PRODUCT_DETAILS_HTML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaWebDevelopmentBasics\\05.JavaEEServlets\\ChushkaApp\\src\\main\\resources\\views\\details-product.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public ProductDetailsServlet(HtmlReader htmlReader, ProductService productService, ModelMapper modelMapper) {
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDetailsViewModel productDetailsViewModel =
                this.modelMapper.map(this.productService.findProductByName(req.getQueryString().split("=")[1]), ProductDetailsViewModel.class);


        String htmlFileContent =
                this.htmlReader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH)
                        .replace("{{productName}}", productDetailsViewModel.getName())
                        .replace("{{productDescription}}", productDetailsViewModel.getDescription())
                        .replace("{{productType}}", productDetailsViewModel.getType());

        resp.getWriter().println(htmlFileContent);

    }
}
