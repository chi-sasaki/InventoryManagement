package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 製品情報の取得、登録、更新、削除、ソートおよび
 * 工程や会社に紐づく製品情報の取得を定義するMapper インターフェースです。
 *
 */
@Mapper
public interface ProductMapper {
    /**
     * 指定された製品IDに対応する製品情報を取得します。
     *
     * @param id 製品ID
     * @return 該当する製品情報
     */
    Product findById(Long id);

    /**
     * 指定された会社IDに紐づく製品情報を取得します。
     *
     * @param companyId 会社ID
     * @return 該当する製品情報のリスト
     */
    List<Product> findByCompanyId(Long companyId);

    /**
     * 全ての製品情報を取得します。
     *
     * @return 製品情報のリスト
     */
    List<Product> findAll();

    /**
     * 製品情報を登録します。
     *
     * @param product 登録する製品情報
     */
    void registerProduct(Product product);

    /**
     * 製品情報を更新します。
     *
     * @param product 更新する製品情報
     */
    void updateProduct(Product product);

    /**
     * 指定された製品IDの製品情報を削除します。
     *
     * @param id 製品ID
     */
    void deleteProduct(Long id);

    /**
     * 製品を最終発注日でソートして取得します。
     *
     * @param sort ソート順
     * @return ソート済みの製品情報リスト
     */
    List<Product> lastOrdered(String sort);

    /**
     * 製品を在庫数でソートして取得します。
     *
     * @param sort ソート順
     * @return ソート済みの製品情報リスト
     */
    List<Product> orderByStock(String sort);

    /**
     * 製品を名前でソートして取得します。
     *
     * @param sort ソート順
     * @return ソート済みの製品情報リスト
     */
    List<Product> orderByName(String sort);

    /**
     * 指定された工程IDに紐づく製品情報を取得します。
     *
     * @param processId 工程ID
     * @return 該当する製品情報のリスト
     */
    List<Product> findByProcessId(Long processId);
}
