package com.springboot.api.service;

import com.springboot.api.controller.ProductController;
import com.springboot.api.dto.ProductRecordDto;
import com.springboot.api.exception.ProductNotFoundException;
import com.springboot.api.model.ProductModel;
import com.springboot.api.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductModel saveProduct(ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
       return repository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> productsList = repository.findAll();
        if(!productsList.isEmpty()){
            for(ProductModel product : productsList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class)
                        .getProductById(id)).withSelfRel());
            }
        }
       return repository.findAll();
    }

    public Optional<ProductModel> getProductById(UUID id) {
        Optional<ProductModel> product = repository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = this.getProductById(id);
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return repository.save(productModel);
    }

    public Object deleteProduct(UUID id){
        Optional<ProductModel> product = this.getProductById(id);
        repository.delete(product.get());
        return null;
    }

}
