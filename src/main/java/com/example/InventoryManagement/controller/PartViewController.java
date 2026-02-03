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

import java.util.Collections;
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
     * 初期表示用画面。全ての部品情報を取得し、一覧画面を表示します。
     *
     * @param model 画面表示用
     * @return 部品情報の一覧画面
     */
    @GetMapping("/parts")
    public String list(Model model) {
        model.addAttribute("parts", Collections.emptyList());
        model.addAttribute("allParts", partService.findAll());
        model.addAttribute("process", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 指定した部品IDに基づいて部品情報を検索し、一覧画面を表示します。
     *
     * @param partId 検索対象の部品ID
     * @param model  画面表示用
     * @return 部品一覧表示用フラグメント名
     */
    @GetMapping("/parts/search")
    public String searchById(
            @RequestParam(required = false) Long partId, Model model) {
        List<Part> parts;
        if (partId == null) {
            parts = Collections.emptyList();
        } else {
            Part p = partService.findById(partId);
            parts = p != null ? List.of(p) : List.of();
        }
        model.addAttribute("parts", parts);
        model.addAttribute("allParts", partService.findAll());
        model.addAttribute("process", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 全ての部品情報を取得し、一覧画面を表示します。
     *
     * @param model 画面表示用
     * @return 部品一覧表示用フラグメント名
     */
    @GetMapping("/parts/all")
    public String allParts(Model model) {
        List<Part> parts = partService.findAll();
        model.addAttribute("parts", parts);
        model.addAttribute("allParts", parts);
        model.addAttribute("process", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 入力された部品情報を登録し、一覧画面用のフラグメントに最新情報を渡します。
     *
     * @param part  登録する部品情報
     * @param model 画面表示用
     * @return 部品一覧表示用フラグメント名
     */
    @PostMapping("/parts/register")
    public String registerPart(@Valid Part part, Model model) {
        partService.registerPart(part);
        model.addAttribute("parts", partService.findByProcessId(part.getProcessId()));
        model.addAttribute("allParts", partService.findAll());
        model.addAttribute("process", processService.findById(part.getProcessId()));
        model.addAttribute("part", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 入力された部品情報を更新し、一覧画面用のフラグメントに最新情報を渡します。
     *
     * @param part  更新する部品情報
     * @param model 画面表示用
     * @return 部品一覧表示用フラグメント名
     */
    @PostMapping("/parts/update")
    public String updatePart(@ModelAttribute Part part, Model model) {
        partService.updatePart(part);
        model.addAttribute("parts", partService.findByProcessId(part.getProcessId()));
        model.addAttribute("allParts", partService.findAll());
        model.addAttribute("process", processService.findById(part.getProcessId()));
        model.addAttribute("part", partService.findById(part.getId()));
        return "fragments/part-list :: partsContent";
    }

    /**
     * 指定された部品IDの部品情報を削除し、一覧画面用のフラグメントに最新情報を渡します。
     * 削除に失敗した場合はエラーメッセージを表示します。
     *
     * @param deleteIds 削除対象の部品IDリスト
     * @param model     画面表示用
     * @return 部品一覧表示用フラグメント名
     */
    @PostMapping("/parts/delete")
    public String deleteParts(@RequestParam List<Long> deleteIds, Model model) {
        try {
            partService.deleteParts(deleteIds);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        List<Part> parts = partService.findAll();
        model.addAttribute("parts", parts);
        model.addAttribute("allParts", parts);
        model.addAttribute("process", null);
        return "fragments/part-list :: partsContent";
    }

    /**
     * 指定された部品IDの部品情報を編集用に取得し、フラグメントに渡します。
     *
     * @param id    編集対象の部品ID
     * @param model 画面表示用
     * @return 部品編集表示用フラグメント名
     */
    @GetMapping("/parts/{id}/edit")
    public String editPart(@PathVariable Long id, Model model) {
        Part part = partService.findById(id);
        model.addAttribute("part", part);
        model.addAttribute("parts", Collections.emptyList());
        model.addAttribute("allParts", partService.findAll());
        if (part != null && part.getProcessId() != null) {
            model.addAttribute("process", processService.findById(part.getProcessId()));
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
