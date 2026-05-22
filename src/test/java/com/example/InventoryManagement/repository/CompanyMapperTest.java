package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Company;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;


    @Test
    void findAll_全件取得できる() {
        List<Company> companies = companyMapper.findAll();
        assertThat(companies).hasSize(3);
        assertThat(companies)
                .extracting(Company::getCompanyName)
                .containsExactly(
                        "Apple",
                        "Google",
                        "Microsoft"
                );
    }

    @Test
    void findById_存在する会社IDで検索できる() {
        Company company = companyMapper.findById(1L);

        assertThat(company).isNotNull();
        assertThat(company.getId()).isEqualTo(1L);
        assertThat(company.getCompanyName()).isEqualTo("Google");
    }

    @Test
    void findById_存在しない会社IDの場合nullを返す() {
        Company company = companyMapper.findById(999L);
        assertThat(company).isNull();
    }

    @Test
    void findByCompanyName_部分一致検索できる() {
        List<Company> companies = companyMapper.findByCompanyName("Mic");

        assertThat(companies).hasSize(1);
        assertThat(companies.get(0).getCompanyName())
                .isEqualTo("Microsoft");
    }

    @Test
    void findByCompanyName_該当データが存在しない場合空リストを返す() {
        List<Company> companies = companyMapper.findByCompanyName("Amazon");
        assertThat(companies).isEmpty();
    }

    @Test
    void registerCompany_会社を登録できる() {
        Company company = new Company();
        company.setCompanyName("Amazon");
        companyMapper.registerCompany(company);
        assertThat(company.getId()).isNotNull();
        Company saved = companyMapper.findById(company.getId());

        assertThat(saved).isNotNull();
        assertThat(saved.getCompanyName())
                .isEqualTo("Amazon");
    }

    @Test
    void updateCompany_会社情報を更新できる() {
        Company company = companyMapper.findById(1L);
        company.setCompanyName("Google Japan");
        companyMapper.updateCompany(company);
        Company updated = companyMapper.findById(1L);

        assertThat(updated).isNotNull();
        assertThat(updated.getCompanyName())
                .isEqualTo("Google Japan");
    }

    @Test
    void deleteCompany_会社情報を削除できる() {
        companyMapper.deleteCompany(1L);
        Company deleted = companyMapper.findById(1L);
        assertThat(deleted).isNull();
    }
}