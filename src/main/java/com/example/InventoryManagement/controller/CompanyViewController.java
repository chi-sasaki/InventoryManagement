package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 会社情報を表示、登録、更新する画面（View）用のコントローラクラスです。
 */
@Controller
public class CompanyViewController {
    private final CompanyService companyService;

    public CompanyViewController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 会社情報の一覧を取得し、画面に渡します。
     *
     * @param model 画面に渡すデータ
     * @return 会社情報の一覧画面
     */
    @GetMapping("/company")
    public String list(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "company/list";
    }

    /**
     * 指定された会社名に基づいて会社情報を検索し、画面に渡します。
     *
     * @param companyName 検索対象の会社名
     * @param model       画面に渡すデータ
     * @return 検索結果を表示する会社情報一覧画面
     */
    @GetMapping("/company/search")
    public String searchByCompany(String companyName, Model model) {
        model.addAttribute("company", companyService.findByCompanyName(companyName));
        model.addAttribute("content", "company/list :: content");
        return "company/list";
    }

    /**
     * 会社情報を登録します。
     *
     * @param company 登録する会社情報
     * @return 会社情報一覧画面のURL
     */
    @PostMapping("/company/register")
    public String register(@ModelAttribute Company company) {
        companyService.registerCompany(company);
        return "redirect:/company";
    }

    /**
     * 指定されたIDの会社情報を編集画面に表示します。
     *
     * @param id    会社情報ID
     * @param model 画面に渡すデータ
     * @return 編集画面
     */
    @GetMapping("/company/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/edit";
    }

    /**
     * 会社情報を更新します。
     *
     * @param company 更新する会社情報
     * @return 会社情報一覧画面のURL
     */
    @PostMapping("/company/update")
    public String update(@ModelAttribute Company company) {
        companyService.updateCompany(company);
        return "redirect:/company";
    }
}
