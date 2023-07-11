package com.springboot.api.controller;

import com.springboot.api.dto.ProductRecordDto;
import com.springboot.api.model.ProductModel;
import com.springboot.api.repository.ProductRepository;
import com.springboot.api.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProduct(productRecordDto));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value="id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
    }

    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveProduct(productRecordDto));
    }

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProduct(id));
    }


}
