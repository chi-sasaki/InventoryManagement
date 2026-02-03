package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.repository.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会社情報の取得、登録、更新、削除を行うサービスクラスです。
 */
@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    /**
     * 指定された会社名に一致する会社情報を取得します。
     *
     * @param companyName 会社名
     * @return 該当する会社情報のリスト
     */
    public List<Company> findByCompanyName(String companyName) {
        return companyMapper.findByCompanyName(companyName);
    }

    /**
     * 全ての会社情報を取得します。
     *
     * @return 会社情報のリスト
     */
    public List<Company> findAll() {
        return companyMapper.findAll();
    }

    /**
     * 指定されたIDに対応する会社情報を取得します。
     *
     * @param id 会社ID
     * @return 該当する会社情報
     */
    public Company findById(Long id) {
        return companyMapper.findById(id);
    }

    /**
     * 会社情報を登録します。
     *
     * @param company 登録する会社情報
     */
    @Transactional
    public void registerCompany(Company company) {
        companyMapper.registerCompany(company);
    }

    /**
     * 会社情報を更新します。
     *
     * @param company 更新する会社情報
     */
    @Transactional
    public void updateCompany(Company company) {
        companyMapper.updateCompany(company);
    }

    /**
     * 指定された会社IDの会社情報を削除します。
     *
     * @param id 会社ID
     */
    @Transactional
    public void deleteCompany(Long id) {
        companyMapper.deleteCompany(id);
    }
}
