package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.service.ProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryViewController {
    private final ProcessService processService;

    public InventoryViewController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        model.addAttribute("processes", processService.findAll());
        return "index";
    }
}
