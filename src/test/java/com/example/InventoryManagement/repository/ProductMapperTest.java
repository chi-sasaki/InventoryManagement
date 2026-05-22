package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Product;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void findAll_全件取得できる() {
        List<Product> products = productMapper.findAll();
        assertThat(products).hasSize(3);
    }

    @Test
    void findById_存在する製品を取得できる() {
        Product product = productMapper.findById(1L);

        assertThat(product).isNotNull();
        assertThat(product.getProductName())
                .isEqualTo("Laptop");
        assertThat(product.getCompanyName())
                .isEqualTo("Google");
    }

    @Test
    void findById_存在しない場合nullを返す() {
        Product product = productMapper.findById(999L);
        assertThat(product).isNull();
    }

    @Test
    void findByCompanyId_会社に紐づく製品を取得できる() {
        List<Product> products = productMapper.findByCompanyId(1L);

        assertThat(products).hasSize(2);
        assertThat(products)
                .extracting(Product::getProductName)
                .containsExactlyInAnyOrder(
                        "Laptop",
                        "Mouse"
                );
    }

    @Test
    void findByProcessId_工程に紐づく製品を取得できる() {
        List<Product> products = productMapper.findByProcessId(100L);
        assertThat(products).hasSize(2);
    }

    @Test
    void registerProduct_製品を登録できる() {
        Product product = new Product();

        product.setProductName("Monitor");
        product.setCompanyId(1L);
        product.setModelNumber("MN-001");
        product.setStockQuantity(15);
        product.setLastOrderedAt(LocalDate.of(2026, 5, 20));

        productMapper.registerProduct(product);
        assertThat(product.getId())
                .isNotNull();

        Product saved = productMapper.findById(product.getId());

        assertThat(saved).isNotNull();
        assertThat(saved.getProductName())
                .isEqualTo("Monitor");
    }

    @Test
    void updateProduct_製品情報を更新できる() {
        Product product = productMapper.findById(1L);
        product.setProductName("Gaming Laptop");
        product.setStockQuantity(99);

        productMapper.updateProduct(product);
        Product updated = productMapper.findById(1L);

        assertThat(updated.getProductName())
                .isEqualTo("Gaming Laptop");

        assertThat(updated.getStockQuantity())
                .isEqualTo(99);
    }

    @Test
    void deleteProduct_製品を削除できる() {
        productMapper.deleteProduct(1L);
        Product deleted = productMapper.findById(1L);
        assertThat(deleted).isNull();
    }

    @Test
    void lastOrdered_ascで古い順になる() {
        List<Product> products = productMapper.lastOrdered("asc");

        assertThat(products)
                .extracting(Product::getProductName)
                .containsExactly(
                        "Keyboard",
                        "Laptop",
                        "Mouse"
                );
    }

    @Test
    void lastOrdered_descで新しい順になる() {
        List<Product> products = productMapper.lastOrdered("desc");

        assertThat(products)
                .extracting(Product::getProductName)
                .containsExactly(
                        "Mouse",
                        "Laptop",
                        "Keyboard"
                );
    }

    @Test
    void orderByStock_ascで在庫少ない順になる() {
        List<Product> products = productMapper.orderByStock("asc");

        assertThat(products)
                .extracting(Product::getStockQuantity)
                .containsExactly(
                        10,
                        30,
                        50
                );
    }

    @Test
    void orderByStock_descで在庫多い順になる() {
        List<Product> products = productMapper.orderByStock("desc");

        assertThat(products)
                .extracting(Product::getStockQuantity)
                .containsExactly(
                        50,
                        30,
                        10
                );
    }

    @Test
    void orderByName_ascで名前昇順になる() {
        List<Product> products = productMapper.orderByName("asc");

        assertThat(products)
                .extracting(Product::getProductName)
                .containsExactly(
                        "Keyboard",
                        "Laptop",
                        "Mouse"
                );
    }

    @Test
    void orderByName_descで名前降順になる() {
        List<Product> products = productMapper.orderByName("desc");

        assertThat(products)
                .extracting(Product::getProductName)
                .containsExactly(
                        "Mouse",
                        "Laptop",
                        "Keyboard"
                );
    }
}