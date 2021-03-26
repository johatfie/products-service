package com.example.widgets_are_us.productsservice.services;

import com.example.widgets_are_us.productsservice.exceptions.ResourceNotFoundException;
import com.example.widgets_are_us.productsservice.models.Product;
import com.example.widgets_are_us.productsservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {
    public static final String PRODUCT_NOT_FOUND_FOR_THIS_ID = "Product not found for this id :: ";

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String json) {
        log.info("Mapping product from json:" + json);
        Product product = productRepository.save(Product.fromJson(json));
        log.debug("Product created: " + product);

        return product;
    }

    public Product replaceProduct(Long id, String product) {
        productRepository.findByProductId(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_FOR_THIS_ID + id));

        Product updatedProduct = Product.fromJson(product);
        updatedProduct.setId(id);
        updatedProduct = productRepository.save(updatedProduct);

        log.debug(updatedProduct.toJson());

        return updatedProduct;
    }
}
