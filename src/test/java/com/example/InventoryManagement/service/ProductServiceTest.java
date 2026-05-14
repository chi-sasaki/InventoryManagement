package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Test
    void 指定したIDの製品情報を取得する() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;
        Product expected = new Product();
        expected.setId(id);

        when(productMapper.findById(id)).thenReturn(expected);
        Product actual = sut.findById(id);
        assertEquals(expected, actual);
        verify(productMapper).findById(id);
    }

    @Test
    void 指定したIDの製品情報が存在しない場合は例外を投げる() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;

        when(productMapper.findById(id)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class,
                () -> sut.findById(id)
        );
        verify(productMapper).findById(id);
    }

    @Test
    void 指定した会社IDに紐づく製品情報を取得する() {
        ProductService sut = new ProductService(productMapper);
        Long companyId = 1L;
        Product product = new Product();

        List<Product> expected = List.of(product);
        when(productMapper.findByCompanyId(companyId)).thenReturn(expected);
        List<Product> actual = sut.findByCompanyId(companyId);
        assertEquals(expected, actual);
        verify(productMapper).findByCompanyId(companyId);
    }

    @Test
    void 全ての製品情報を取得する() {
        ProductService sut = new ProductService(productMapper);
        Product product1 = new Product();
        Product product2 = new Product();

        List<Product> expected = List.of(product1, product2);
        when(productMapper.findAll()).thenReturn(expected);
        List<Product> actual = sut.findAll();
        assertEquals(expected, actual);
        verify(productMapper).findAll();
    }

    @Test
    void 製品情報を登録する() {
        ProductService sut = new ProductService(productMapper);
        Product product = new Product();
        product.setId(1L);
        sut.registerProduct(product);
        verify(productMapper).registerProduct(product);
    }

    @Test
    void 製品情報を更新する() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(productMapper.findById(id)).thenReturn(product);
        sut.updateProduct(product);
        verify(productMapper).findById(id);
        verify(productMapper).updateProduct(product);
    }

    @Test
    void 存在しない製品情報は更新できない() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(productMapper.findById(id)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class,
                () -> sut.updateProduct(product)
        );
        verify(productMapper).findById(id);
    }

    @Test
    void 製品情報を削除する() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(productMapper.findById(id)).thenReturn(product);
        sut.deleteProduct(id);
        verify(productMapper).findById(id);
        verify(productMapper).deleteProduct(id);
    }

    @Test
    void 存在しない製品情報は削除できない() {
        ProductService sut = new ProductService(productMapper);
        Long id = 1L;

        when(productMapper.findById(id)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class,
                () -> sut.deleteProduct(id)
        );
        verify(productMapper).findById(id);
    }

    @Test
    void 最終発注日で製品一覧を並び替えて取得する() {
        ProductService sut = new ProductService(productMapper);
        String sort = "asc";
        Product product = new Product();
        List<Product> expected = List.of(product);

        when(productMapper.lastOrdered(sort)).thenReturn(expected);
        List<Product> actual = sut.lastOrdered(sort);
        assertEquals(expected, actual);
        verify(productMapper).lastOrdered(sort);
    }

    @Test
    void 在庫数で製品一覧を並び替えて取得する() {
        ProductService sut = new ProductService(productMapper);
        String sort = "desc";
        Product product = new Product();

        List<Product> expected = List.of(product);
        when(productMapper.orderByStock(sort)).thenReturn(expected);
        List<Product> actual = sut.orderByStock(sort);
        assertEquals(expected, actual);
        verify(productMapper).orderByStock(sort);
    }

    @Test
    void 製品名で製品一覧を並び替えて取得する() {
        ProductService sut = new ProductService(productMapper);
        String sort = "asc";
        Product product = new Product();

        List<Product> expected = List.of(product);
        when(productMapper.orderByName(sort)).thenReturn(expected);
        List<Product> actual = sut.orderByName(sort);
        assertEquals(expected, actual);
        verify(productMapper).orderByName(sort);
    }

    @Test
    void 指定した工程IDに紐づく製品情報を取得する() {
        ProductService sut = new ProductService(productMapper);
        Long processId = 1L;
        Product product = new Product();

        List<Product> expected = List.of(product);
        when(productMapper.findByProcessId(processId)).thenReturn(expected);
        List<Product> actual = sut.findByProcessId(processId);
        assertEquals(expected, actual);
        verify(productMapper).findByProcessId(processId);
    }
}