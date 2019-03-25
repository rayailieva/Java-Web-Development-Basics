package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean createProduct(ProductServiceModel productServiceModel) {
        try{
            this.productRepository.save(
                    this.modelMapper.map(productServiceModel, Product.class));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getProductById(String id) {
        return this.modelMapper
                .map(this.productRepository.findById(id), ProductServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.productRepository.delete(id);
    }
}
