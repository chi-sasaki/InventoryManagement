package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product findById(Long id);

    List<Product> findByCompanyId(Long companyId);

    List<Product> findAll();

    void registerProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> lastOrdered(String sort);

    List<Product> orderByStock(String sort);

    List<Product> orderByName(String sort);

    List<Product> findByProcessId(Long processId);
}
