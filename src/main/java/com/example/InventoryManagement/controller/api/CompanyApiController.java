package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 取引先の会社情報の検索や登録、更新、削除などを行うRESTAPI用のコントローラクラスです。
 */
@RestController
public class CompanyApiController {
    private final CompanyService companyService;

    public CompanyApiController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 取引先の会社情報を全件取得します。
     *
     * @return 会社一覧
     */
    @GetMapping("/api/companies")
    public List<Company> list() {
        return companyService.findAll();
    }

    /**
     * 指定したIDの会社情報を取得します。
     *
     * @param id 会社ID
     * @return 指定した会社情報
     */
    @GetMapping("/api/companies/{id}")
    public Company get(@PathVariable Long id) {
        return companyService.findById(id);
    }

    /**
     * 会社情報を登録します。
     *
     * @param company 会社
     * @return HTTP 201 Created
     */
    @PostMapping("/api/companies")
    public ResponseEntity<Void> register(@RequestBody Company company) {
        companyService.registerCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 会社情報を更新します。
     *
     * @param id      会社ID
     * @param company 更新内容
     * @return HTTP 200 OK
     */
    @PutMapping("/api/companies/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Company company) {
        company.setId(id);
        companyService.updateCompany(company);
        return ResponseEntity.ok().build();
    }

    /**
     * 指定した会社情報を削除します。
     *
     * @param id 会社ID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/api/companies/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}
