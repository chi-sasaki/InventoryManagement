package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductApiController.class)
class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    @DisplayName("製品一覧を取得できる")
    void testList() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Laptop");

        when(productService.findAll())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].productName").value("Laptop"));

        verify(productService, times(1)).findAll();
    }

    @Test
    @DisplayName("指定した製品情報を取得できる")
    void testGet() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Laptop");

        when(productService.findById(1L))
                .thenReturn(product);

        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("Laptop"));

        verify(productService, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("製品情報を登録できる")
    void testRegister() throws Exception {

        Product product = new Product();

        product.setProductName("Laptop");
        product.setModelNumber("PC-001");
        product.setStockQuantity(10);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());

        verify(productService, times(1))
                .registerProduct(any(Product.class));
    }

    @Test
    @DisplayName("製品情報を更新できる")
    void testUpdate() throws Exception {

        Product product = new Product();

        product.setProductName("Gaming Laptop");
        product.setModelNumber("PC-002");
        product.setStockQuantity(5);

        mockMvc.perform(put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        verify(productService, times(1))
                .updateProduct(any(Product.class));
    }

    @Test
    @DisplayName("製品情報を削除できる")
    void testDelete() throws Exception {

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1))
                .deleteProduct(1L);
    }
}