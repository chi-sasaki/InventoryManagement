package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.service.PartService;
import com.example.InventoryManagement.service.ProcessService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部品情報の一覧表示・登録・更新・削除を行う
 * 画面（View）用のコントローラクラスです。
 */

@Validated
@Controller
public class PartViewController {
    private final PartService partService;
    private final ProcessService processService;

    public PartViewController(PartService partService, ProcessService processService) {
        this.partService = partService;
        this.processService = processService;
    }

    /**
     * 全ての部品情報を取得し、一覧画面を表示します。
     *
     * @param model 画面表示用
     * @return 部品情報の一覧画面
     */
    @GetMapping("/parts")
    public String list(Model model) {
        model.addAttribute("parts", partService.findAll());
        return "fragments/part-list :: partsContent";
    }


    @GetMapping("/parts/search")
    public String searchById(
            @RequestParam(required = false) Long partId, Model model) {
        if (partId == null) {
            model.addAttribute("parts", partService.findAll());
        } else {
            model.addAttribute("parts", List.of(partService.findById(partId)));
        }
        return "fragments/part-list :: partsContent";
    }

    /**
     * 入力された部品情報を登録します。
     *
     * @param part 登録する部品情報
     * @return 登録後、部品一覧画面へリダイレクト
     */
    @PostMapping("/parts/register")
    public String registerPart(@Valid Part part, Model model) {
        partService.registerPart(part);
        model.addAttribute("parts", partService.findByProcessId(part.getProcessId()));
        model.addAttribute("process", processService.findById(part.getProcessId()));
        model.addAttribute("part", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 入力された部品情報を更新します。
     *
     * @param part 更新する部品情報
     * @return 更新後、部品一覧画面へリダイレクト
     */
    @PostMapping("/parts/update")
    public String updatePart(@ModelAttribute Part part, Model model) {
        partService.updatePart(part);
        model.addAttribute("parts", partService.findByProcessId(part.getProcessId()));
        model.addAttribute("process", processService.findById(part.getProcessId()));
        model.addAttribute("part", partService.findById(part.getId()));
        return "fragments/part-list :: partsContent";
    }

    @PostMapping("/parts/delete")
    public String deleteParts(@RequestParam List<Long> deleteIds, Model model) {
        try {
            partService.deleteParts(deleteIds);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("parts", partService.findAll());
        return "fragments/part-list :: partsContent";
    }

    @GetMapping("/parts/{id}/edit")
    public String editPart(@PathVariable Long id, Model model) {
        Part part = partService.findById(id);
        model.addAttribute("part", part);
        model.addAttribute("parts", partService.findAll());
        if (part != null && part.getProcessId() != null) {
            ManufacturingProcess process = processService.findById(part.getProcessId());
            model.addAttribute("process", process);
        }
        return "fragments/part-list :: partsContent";
    }

    /**
     * 指定された工程IDに紐づく部品一覧を取得し、部品一覧表示用のフラグメントを返します。
     *
     * @param processId 工程ID
     * @param model     画面表示用
     * @return 部品一覧フラグメント
     */
    @GetMapping("/parts/process/{processId}")
    public String findByProcess(@PathVariable Long processId, Model model) {
        model.addAttribute("parts", partService.findByProcessId(processId));
        ManufacturingProcess process = processService.findById(processId);
        model.addAttribute("process", process);
        return "fragments/part-list :: partsContent";
    }
}
