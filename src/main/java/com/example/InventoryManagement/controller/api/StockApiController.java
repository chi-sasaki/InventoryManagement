package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.service.SummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * 部品の在庫に関する集計情報を提供するREST APIコントローラーです。
 */
@RestController
public class StockApiController {
    private final SummaryService summaryService;

    public StockApiController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    /**
     * 指定された期間の部品の入出庫数を集計して返します。
     *
     * @param from 集計開始日（この日を含む）
     * @param to   集計終了日（この日を含む）
     * @return 指定された期間の部品の入出庫数の集計結果
     */
    @GetMapping("/api/stock/summary")
    public List<PartStockSummary> getPartSummaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return summaryService.getPartSummaries(from, to.plusDays(1));
    }
}
