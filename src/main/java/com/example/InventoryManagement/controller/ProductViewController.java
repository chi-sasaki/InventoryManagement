package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.service.CompanyService;
import com.example.InventoryManagement.service.ProcessService;
import com.example.InventoryManagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 製品情報の一覧表示・登録・更新・削除・ソートを行う画面（View）用のコントローラクラスです。
 */
@Controller
public class ProductViewController {
    private final ProductService productService;
    private final CompanyService companyService;
    private final ProcessService processService;

    public ProductViewController(ProductService productService, CompanyService companyService, ProcessService processService) {
        this.productService = productService;
        this.companyService = companyService;
        this.processService = processService;
    }

    /**
     * 全製品情報を取得し、一覧表示用フラグメントに渡します。
     *
     * @param model 画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/product")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    /**
     * 指定した会社IDに紐づく製品情報を検索し、一覧表示用フラグメントに渡します。
     *
     * @param companyId 検索対象の会社ID
     * @param model     画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/product/search")
    public String search(@RequestParam Long companyId, Model model) {
        model.addAttribute("products", productService.findByCompanyId(companyId));
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    /**
     * 製品情報を登録し、一覧表示用フラグメントに最新情報を渡します。
     *
     * @param product 登録する製品情報
     * @param model   画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @PostMapping("/register/product")
    public String registerProduct(@ModelAttribute Product product, Model model) {
        productService.registerProduct(product);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    /**
     * 入力された製品情報を更新し、一覧表示用フラグメントに最新情報を渡します。
     *
     * @param product 更新する製品情報
     * @param model   画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @PostMapping("/product/update")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("product", productService.findById(product.getId()));
        return "fragments/product-list :: productsContent";
    }

    /**
     * 指定した製品IDの編集画面用データを取得し、フラグメントに渡します。
     *
     * @param id    編集対象の製品ID
     * @param model 画面表示用
     * @return 製品編集表示用フラグメント名
     */
    @GetMapping("/product/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    /**
     * 指定した製品IDリストを一括削除し、一覧表示用フラグメントに最新情報を渡します。
     *
     * @param deleteIds 削除対象の製品IDリスト
     * @param model     画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @PostMapping("/product/bulk-delete")
    public String bulkDelete(@RequestParam List<Long> deleteIds, Model model) {
        deleteIds.forEach(productService::deleteProduct);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    /**
     * 製品を最終発注日でソートし、一覧表示用フラグメントに渡します。
     *
     * @param sort  ソート順（asc/desc）
     * @param model 画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/product/lastOrdered")
    public String lastOrdered(
            @RequestParam(defaultValue = "desc") String sort, Model model) {
        model.addAttribute("products", productService.lastOrdered(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "lastOrdered");
        return "fragments/product-list :: productsContent";
    }

    /**
     * 製品を在庫数でソートし、一覧表示用フラグメントに渡します。
     *
     * @param sort  ソート順（asc/desc）
     * @param model 画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/product/orderByStock")
    public String orderByStock(
            @RequestParam(defaultValue = "asc") String sort, Model model) {
        model.addAttribute("products", productService.orderByStock(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "stock");
        return "fragments/product-list :: productsContent";
    }

    /**
     * 製品を名前でソートし、一覧表示用フラグメントに渡します。
     *
     * @param sort  ソート順（asc/desc）
     * @param model 画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/product/orderByName")
    public String orderByName(
            @RequestParam(defaultValue = "asc") String sort, Model model) {
        model.addAttribute("products", productService.orderByName(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "name");
        return "fragments/product-list :: productsContent";
    }

    /**
     * 指定した工程IDに紐づく製品一覧を取得し、一覧表示用フラグメントに渡します。
     *
     * @param processId 工程ID
     * @param model     画面表示用
     * @return 製品一覧表示用フラグメント名
     */
    @GetMapping("/products/process/{processId}")
    public String findProductByProcess(@PathVariable Long processId, Model model) {
        model.addAttribute("products", productService.findByProcessId(processId));
        model.addAttribute("companies", companyService.findAll());
        ManufacturingProcess process = processService.findById(processId);
        model.addAttribute("process", process);
        return "fragments/product-list :: productsContent";
    }

}
