package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.CompanyMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyMapper companyMapper;

    @Test
    void 指定された会社名に一致する会社情報を取得する() {
        CompanyService sut = new CompanyService(companyMapper);
        String companyName = "ABC株式会社";

        Company company = new Company();
        company.setCompanyName(companyName);

        List<Company> expected = List.of(company);
        // モックの動きを設定
        when(companyMapper.findByCompanyName(companyName)).thenReturn(expected);
        // 実行
        List<Company> actual = sut.findByCompanyName(companyName);
        // 検証
        assertEquals(expected, actual);
        // 呼ばれたか確認
        verify(companyMapper).findByCompanyName(companyName);
    }

    @Test
    void 全ての会社情報を取得する() {
        CompanyService sut = new CompanyService(companyMapper);
        // テストデータ
        Company company1 = new Company();
        company1.setCompanyName("ABC株式会社");
        Company company2 = new Company();
        company2.setCompanyName("XYZ株式会社");

        List<Company> expected = List.of(company1, company2);
        // モックの動作設定
        when(companyMapper.findAll()).thenReturn(expected);
        // 実行
        List<Company> actual = sut.findAll();
        // 検証
        assertEquals(expected, actual);
        // MapperのfindAllが呼ばれたか確認
        verify(companyMapper).findAll();
    }

    @Test
    void 指定したIDの会社情報を取得する() {
        CompanyService sut = new CompanyService(companyMapper);
        // テストデータ
        Long id = 1L;

        Company expected = new Company();
        expected.setId(id);
        expected.setCompanyName("ABC株式会社");

        // モックの動作設定
        when(companyMapper.findById(id)).thenReturn(expected);
        // 実行
        Company actual = sut.findById(id);
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(companyMapper).findById(id);
    }

    @Test
    void 指定したIDの会社情報が存在しない場合は例外を投げる() {
        CompanyService sut = new CompanyService(companyMapper);
        Long id = 1L;
        // null を返す設定
        when(companyMapper.findById(id)).thenReturn(null);
        // 例外確認
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> sut.findById(id)
        );
        // 呼び出し確認
        verify(companyMapper).findById(id);
    }

    @Test
    void 会社情報を登録する() {
        CompanyService sut = new CompanyService(companyMapper);
        // テストデータ
        Company company = new Company();
        company.setCompanyName("ABC株式会社");
        // 実行
        sut.registerCompany(company);
        // 検証
        verify(companyMapper).registerCompany(company);
    }

    @Test
    void 指定した会社情報を削除する() {
        CompanyService sut = new CompanyService(companyMapper);
        // テストデータ
        Long id = 1L;
        Company company = new Company();
        company.setId(id);
        company.setCompanyName("ABC株式会社");

        // findById 用モック設定
        when(companyMapper.findById(id)).thenReturn(company);
        // 実行
        sut.deleteCompany(id);
        // 存在確認が呼ばれたか
        verify(companyMapper).findById(id);
        // 削除処理が呼ばれたか
        verify(companyMapper).deleteCompany(id);
    }
}