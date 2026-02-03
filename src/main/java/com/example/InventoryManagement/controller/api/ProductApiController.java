package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductApiController {
    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("api/product")
    public List<Product> list() {
        return productService.findAll();
    }

    @PostMapping("/api/register/product")
    public ResponseEntity<Void> registerProduct(@RequestBody Product product) {
        productService.registerProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/update/product/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/delete/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
