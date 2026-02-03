package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    public List<Product> findByCompanyId(Long companyId) {
        return productMapper.findByCompanyId(companyId);
    }

    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Transactional
    public void registerProduct(Product product) {
        productMapper.registerProduct(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }

    public List<Product> lastOrdered(String sort) {
        return productMapper.lastOrdered(sort);
    }

    public List<Product> orderByStock(String sort) {
        return productMapper.orderByStock(sort);
    }

    public List<Product> orderByName(String sort) {
        return productMapper.orderByName(sort);
    }

    public List<Product> findByProcessId(Long processId) {
        return productMapper.findByProcessId(processId);
    }
}
