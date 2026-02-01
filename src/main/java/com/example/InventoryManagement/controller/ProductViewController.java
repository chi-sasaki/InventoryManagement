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

    @GetMapping("/product")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/product/search")
    public String search(@RequestParam Long companyId, Model model) {
        model.addAttribute("products", productService.findByCompanyId(companyId));
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    @PostMapping("/register/product")
    public String registerProduct(@ModelAttribute Product product, Model model) {
        productService.registerProduct(product);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    @PostMapping("/product/update")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("product", productService.findById(product.getId()));
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/product/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    @PostMapping("/product/bulk-delete")
    public String bulkDelete(@RequestParam List<Long> deleteIds, Model model) {
        deleteIds.forEach(productService::deleteProduct);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("companies", companyService.findAll());
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/product/lastOrdered")
    public String lastOrdered(
            @RequestParam(defaultValue = "desc") String sort, Model model) {
        model.addAttribute("products", productService.lastOrdered(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "lastOrdered");
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/product/orderByStock")
    public String orderByStock(
            @RequestParam(defaultValue = "asc") String sort, Model model) {
        model.addAttribute("products", productService.orderByStock(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "stock");
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/product/orderByName")
    public String orderByName(
            @RequestParam(defaultValue = "asc") String sort, Model model) {
        model.addAttribute("products", productService.orderByName(sort));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentColumn", "name");
        return "fragments/product-list :: productsContent";
    }

    @GetMapping("/products/process/{processId}")
    public String findProductByProcess(@PathVariable Long processId, Model model) {
        model.addAttribute("products", productService.findByProcessId(processId));
        model.addAttribute("companies", companyService.findAll());
        ManufacturingProcess process = processService.findById(processId);
        model.addAttribute("process", process);
        return "fragments/product-list :: productsContent";
    }

}
