package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.service.SummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * 部品の在庫に関する集計情報を画面に返すコントローラーです。
 */
@Controller
public class StockViewController {
    private final SummaryService summaryService;

    public StockViewController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    /**
     * 指定された期間の部品の入出庫数を集計して画面に返します。
     *
     * @param from  集計開始日（この日を含む）
     * @param to    集計終了日（この日を含む）
     * @param model 画面表示用
     * @return 部品の入出庫集計結果を表示する画面
     */
    @GetMapping("/stock/summary/parts")
    public String partStockSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            Model model) {

        model.addAttribute(
                "partSummaries",
                summaryService.getPartSummaries(
                        from.atStartOfDay(),
                        to.plusDays(1).atStartOfDay()
                )
        );
        return "fragments/part-stock-summary :: summary";
    }
}
