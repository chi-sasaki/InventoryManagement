package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyViewController {
    private final CompanyService companyService;

    public CompanyViewController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/search")
    public String searchByCompany(String companyName, Model model) {
        model.addAttribute("company", companyService.findByCompanyName(companyName));
        model.addAttribute("content", "company/list :: content");
        return "company/list";
    }

    @GetMapping("/company")
    public String list(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "company/list";
    }

    @PostMapping("/company/register")
    public String register(@ModelAttribute Company company) {
        companyService.registerCompany(company);
        return "redirect:/company";
    }

    @GetMapping("/company/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/edit";
    }

    @PostMapping("/company/update")
    public String update(@ModelAttribute Company company) {
        companyService.updateCompany(company);
        return "redirect:/company";
    }
}
