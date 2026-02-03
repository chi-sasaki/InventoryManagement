package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.repository.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    public List<Company> findByCompanyName(String companyName) {
        return companyMapper.findByCompanyName(companyName);
    }

    public List<Company> findAll() {
        return companyMapper.findAll();
    }

    public Company findById(Long id) {
        return companyMapper.findById(id);
    }

    @Transactional
    public void registerCompany(Company company) {
        companyMapper.registerCompany(company);
    }

    @Transactional
    public void updateCompany(Company company) {
        companyMapper.updateCompany(company);
    }
}
