package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.PartService;

@Controller
public class PartController {
    private PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    // 部品一覧
    @GetMapping("/parts")
    public String list(Model model) {
        model.addAttribute("parts", partService.findAll());
        return "part/list";
    }

    // 部品種類別検索
    @GetMapping("/parts/search")
    public String searchByName(
            @RequestParam String partName, Model model) {

        model.addAttribute("parts", partService.findByPartName(partName));
        return "part/list";
    }
}
