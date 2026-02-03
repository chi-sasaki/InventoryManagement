package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会社情報の取得、登録、更新、削除を定義するMapperインターフェースです。
 */
@Mapper
public interface CompanyMapper {

    /**
     * 指定された会社名に一致する会社情報を取得します。
     *
     * @param companyName 検索対象の会社名
     * @return 該当する会社情報のリスト
     */
    List<Company> findByCompanyName(String companyName);

    /**
     * 全ての会社情報を取得します。
     *
     * @return 会社情報のリスト
     */
    List<Company> findAll();

    /**
     * 指定されたIDに対応する会社情報を取得します。
     *
     * @param id 会社ID
     * @return 該当する会社情報
     */
    Company findById(Long id);

    /**
     * 会社情報を登録します。
     *
     * @param company 登録する会社情報
     */
    void registerCompany(Company company);

    /**
     * 会社情報を更新します。
     *
     * @param company 更新する会社情報
     */
    void updateCompany(Company company);

    /**
     * 指定されたIDの会社情報を削除します。
     *
     * @param id 会社ID
     */
    void deleteCompany(Long id);
}
