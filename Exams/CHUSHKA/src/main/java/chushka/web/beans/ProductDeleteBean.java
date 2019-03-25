package chushka.web.beans;

import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class ProductDeleteBean {

    private ProductService productService;

    public ProductDeleteBean(){}

    @Inject
    public ProductDeleteBean(ProductService productService) {
        this.productService = productService;
    }

    public ProductServiceModel getProduct(String id){
        ProductServiceModel result =
                this.productService.getProductById(id);

        return result;
    }

    public void delete() throws IOException {
        String id = ((HttpServletRequest)FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getRequest())
                        .getParameter("id");
        this.productService.delete(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }
}
