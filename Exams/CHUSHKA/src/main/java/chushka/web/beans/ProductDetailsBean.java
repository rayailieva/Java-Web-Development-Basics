package chushka.web.beans;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductViewModel;
import chushka.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductDetailsBean {

    private ProductService productService;

    public ProductDetailsBean() {
    }

    @Inject
    public ProductDetailsBean(ProductService productService) {
        this.productService = productService;
    }

    public ProductServiceModel getProduct(String id){
        return this.productService.getProductById(id);
    }

}
