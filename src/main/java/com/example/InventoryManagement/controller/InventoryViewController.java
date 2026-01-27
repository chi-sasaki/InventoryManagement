package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.service.ProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 製造工程一覧を取得し、在庫状況を表示する画面へ渡します。
 */
@Controller
public class InventoryViewController {
    private final ProcessService processService;

    public InventoryViewController(ProcessService processService) {
        this.processService = processService;
    }

    /**
     * 在庫一覧画面を表示します。
     * 全ての製造工程情報を取得し、在庫一覧画面へ渡します。
     *
     * @param model 画面へデータを受け渡すためのModel
     * @return 在庫一覧画面のテンプレート名
     */
    @GetMapping("/inventory")
    public String showInventory(Model model) {
        model.addAttribute("processes", processService.findAll());
        return "index";
    }
}
