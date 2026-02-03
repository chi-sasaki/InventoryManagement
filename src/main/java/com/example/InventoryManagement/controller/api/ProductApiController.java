package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 製品情報の取得、登録、更新、削除を行うRESTAPI用のコントローラクラスです。
 */
@RestController
public class ProductApiController {
    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 製品情報の一覧を取得します。
     *
     * @return 製品情報の一覧
     */
    @GetMapping("api/product")
    public List<Product> list() {
        return productService.findAll();
    }

    /**
     * 指定したIDの製品情報を取得します。
     *
     * @param id 製品のID
     * @return 指定したIDの製品情報
     */
    @GetMapping("/api/product/{id}")
    public Product get(@PathVariable Long id) {
        return productService.findById(id);
    }

    /**
     * 製品情報を登録します。
     *
     * @param product 製品情報
     * @return 成功時は 201 Created
     */
    @PostMapping("/api/product")
    public ResponseEntity<Void> register(@RequestBody Product product) {
        productService.registerProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 指定したIDの製品情報を更新します。
     *
     * @param id      製品のID
     * @param product 製品情報
     * @return 成功時は 200 OK
     */
    @PutMapping("/api/product/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    /**
     * 指定した製品情報を削除します。
     *
     * @param id 製品のID
     * @return 成功時は 204 No Content
     */
    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
