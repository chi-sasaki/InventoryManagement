package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 製品情報の検索、登録、更新、削除および並び替え取得を行うサービスクラスです。
 */
@Service
public class ProductService {
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * 指定したIDの製品情報を取得します。
     *
     * @param id 製品ID
     * @return 指定したIDの製品情報
     */
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    /**
     * 指定した会社IDに紐づく製品情報の一覧を取得します。
     *
     * @param companyId 会社ID
     * @return 指定会社に紐づく製品情報のリスト
     */
    public List<Product> findByCompanyId(Long companyId) {
        return productMapper.findByCompanyId(companyId);
    }

    /**
     * 全ての製品情報を取得します。
     *
     * @return 取得した製品情報のリスト
     */
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    /**
     * 製品情報を登録します。
     *
     * @param product 登録する製品情報
     */
    @Transactional
    public void registerProduct(Product product) {
        productMapper.registerProduct(product);
    }

    /**
     * 製品情報を更新します。
     *
     * @param product 更新対象の製品情報
     */
    @Transactional
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    /**
     * 指定したIDの製品情報を削除します。
     *
     * @param id 削除対象の製品ID
     */
    @Transactional
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }

    /**
     * 最終発注日で製品一覧を並び替えて取得します。
     *
     * @param sort ソート順
     * @return 並び替えた製品情報のリスト
     */
    public List<Product> lastOrdered(String sort) {
        return productMapper.lastOrdered(sort);
    }

    /**
     * 在庫数で製品一覧を並び替えて取得します。
     *
     * @param sort ソート順
     * @return 並び替えた製品情報のリスト
     */
    public List<Product> orderByStock(String sort) {
        return productMapper.orderByStock(sort);
    }

    /**
     * 製品名で製品一覧を並び替えて取得します。
     *
     * @param sort ソート順
     * @return 並び替えた製品情報のリスト
     */
    public List<Product> orderByName(String sort) {
        return productMapper.orderByName(sort);
    }

    /**
     * 指定した工程IDに紐づく製品情報の一覧を取得します。
     *
     * @param processId 工程ID
     * @return 工程に紐づく製品情報のリスト
     */
    public List<Product> findByProcessId(Long processId) {
        return productMapper.findByProcessId(processId);
    }
}
