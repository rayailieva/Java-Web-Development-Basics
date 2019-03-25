package chushka.web.beans;

import chushka.domain.models.view.ProductViewModel;
import chushka.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserHomeBean {

    private List<ProductViewModel> products;

    private ProductService productService;
    private ModelMapper modelMapper;

    public UserHomeBean(){}

    @Inject
    public UserHomeBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        this.products = this.productService
                .findAllProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    public List<ProductViewModel> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }
}
