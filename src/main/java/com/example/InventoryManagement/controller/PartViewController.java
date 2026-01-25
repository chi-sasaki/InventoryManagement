package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 部品情報の一覧表示・登録・更新・削除を行う
 * 画面（View）用のコントローラクラスです。
 */

@Controller
public class PartViewController {
    private final PartService partService;

    public PartViewController(PartService partService) {
        this.partService = partService;
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
        return "parts/list";
    }

    /**
     * 指定した部品名を条件に部品情報を検索し、一覧画面を表示します。
     *
     * @param partName 検索条件となる部品名
     * @param model    画面表示用
     * @return 部品一覧画面
     */
    @GetMapping("/parts/search")
    public String searchByName(
            @RequestParam String partName, Model model) {
        model.addAttribute("parts", partService.findByPartName(partName));
        return "parts/list";
    }

    /**
     * 入力された部品情報を登録します。
     *
     * @param part 登録する部品情報
     * @return 登録後、部品一覧画面へリダイレクト
     */
    @PostMapping("/parts/register")
    public String registerPart(@ModelAttribute Part part) {
        partService.registerPart(part);
        return "redirect:/parts";
    }

    /**
     * 部品登録画面を表示します。
     *
     * @param model 画面表示用
     * @return 部品登録画面
     */
    @GetMapping("/parts/new")
    public String showRegisterForm(Model model) {
        model.addAttribute("part", new Part());
        return "parts/new";
    }

    /**
     * 入力された部品情報を更新します。
     *
     * @param part 更新する部品情報
     * @return 更新後、部品一覧画面へリダイレクト
     */
    @PostMapping("/parts/update")
    public String updatePart(@ModelAttribute Part part) {
        partService.updatePart(part);
        return "redirect:/parts";
    }

    /**
     * 指定したIDの部品情報を取得し、編集画面を表示します。
     *
     * @param id    編集対象の部品ID
     * @param model 画面表示用
     * @return 部品編集画面
     */
    @GetMapping("/parts/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("part", partService.findById(id));
        return "parts/edit";
    }

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 削除対象の部品ID
     * @return 削除後、部品一覧画面へリダイレクト
     */
    @PostMapping("/parts/{id}/delete")
    public String deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return "redirect:/parts";
    }

    /**
     * 指定したIDの部品情報を取得し、削除確認画面を表示します。
     *
     * @param id    削除対象の部品ID
     * @param model 画面表示用
     * @return 部品削除確認画面
     */
    @GetMapping("/parts/{id}/delete")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        model.addAttribute("part", partService.findById(id));
        return "parts/delete";
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
        return "fragments/part-list :: partList";
    }
}
