package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<Company> findByCompanyName(String companyName);

    List<Company> findAll();

    Company findById(Long id);

    void registerCompany(Company company);

    void updateCompany(Company company);
}
