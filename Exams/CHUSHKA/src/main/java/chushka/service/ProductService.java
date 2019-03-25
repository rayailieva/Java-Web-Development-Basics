package chushka.service;

import chushka.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    boolean createProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel getProductById(String id);

    void delete(String id);
}
