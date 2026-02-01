package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyApiController {
    private final CompanyService companyService;

    public CompanyApiController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("api/company/search")
    public List<Company> searchByCompany(String companyName) {
        return companyService.findByCompanyName(companyName);
    }
}
