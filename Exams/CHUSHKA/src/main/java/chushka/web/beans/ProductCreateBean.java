package chushka.web.beans;

import chushka.domain.models.binding.ProductCreateBindingModel;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class ProductCreateBean {

    private ProductCreateBindingModel productCreateBindingModel;

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductCreateBean(){}

    @Inject
    public ProductCreateBean(ProductService productService, ModelMapper modelMapper) {
        this.productCreateBindingModel = new ProductCreateBindingModel();
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    public ProductCreateBindingModel getProductCreateBindingModel() {
        return this.productCreateBindingModel;
    }

    public void setProductCreateBindingModel(ProductCreateBindingModel productCreateBindingModel) {
        this.productCreateBindingModel = productCreateBindingModel;
    }

    public void addProduct() throws IOException {
        this.productService.createProduct(
                this.modelMapper.map(this.productCreateBindingModel, ProductServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }
}
