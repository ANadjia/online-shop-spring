package com.esisba.productservice.controller;

import com.esisba.productservice.entitiy.Product;
import com.esisba.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api-v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public Collection<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{idProduct}")
    public Product getProduct(@PathVariable("idProduct") Long idProduct){
        return productRepository.findById(idProduct).get();
    }

    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product){

        return productRepository.save(product);
    }

    @PutMapping("{idProduct}")
    public Product updateProduct(@PathVariable("idProduct") Long idProduct, @Valid @RequestBody Product product){
        if(productRepository.findById(idProduct).isPresent()){
            product.setId(idProduct);
            return productRepository.save(product);
        }
        return null;
    }

    @DeleteMapping("{idProduct}")
    public void deleteProduct(@PathVariable("idProduct") Long idProduct){
        if(productRepository.findById(idProduct).isPresent()){
            productRepository.deleteById(idProduct);
        }
    }

    @GetMapping("/findProductsByProductName")
    public List<Product> getProductByName(@RequestParam("name") String productName){
        return productRepository.findProductsByProductName(productName);
    }
}