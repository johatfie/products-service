package com.example.widgets_are_us.productsservice.controllers;

import com.example.widgets_are_us.productsservice.exceptions.ResourceNotFoundException;
import com.example.widgets_are_us.productsservice.models.Product;
import com.example.widgets_are_us.productsservice.repositories.ProductRepository;
import com.example.widgets_are_us.productsservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

   @Autowired
   public ProductController(ProductService productService,
                            ProductRepository productRepository) {
       this.productService = productService;
       this.productRepository = productRepository;
   }

   @ResponseBody
   @GetMapping(value = "/{id}")
   public Product findByProductId(@PathVariable(value = "id") Long id) {
       return productRepository.findByProductId(id)
               .orElseThrow(() -> new ResourceNotFoundException(ProductService.PRODUCT_NOT_FOUND_FOR_THIS_ID + id));
   }

   @DeleteMapping(value = "/{id}")
    public void deleteByProductId(@PathVariable(value = "id") Long id) {
       productRepository.deleteByProductId(id);
   }

   @ResponseBody
   @PostMapping(value = "/new",
           consumes = "application/json")
    public Product createProduct(@RequestParam("product") String product) {
       return productService.createProduct(product);
   }

   @ResponseBody
    @PutMapping(value = "/{id}",
    consumes = "application/json")
    public Product updateProduct(@PathVariable(value = "id") Long id,
                                 @RequestBody String product) {
       return  productService.replaceProduct(id, product);
   }

}
