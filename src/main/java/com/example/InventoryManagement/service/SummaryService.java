package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.repository.PartStockSummaryMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 指定された期間における部品の入出庫数を集計するサービスクラスです。
 */
@Service
public class SummaryService {
    private final PartStockSummaryMapper partSummaryMapper;

    public SummaryService(PartStockSummaryMapper partSummaryMapper) {
        this.partSummaryMapper = partSummaryMapper;
    }

    /**
     * 指定された期間の部品ごとの入出庫数の集計結果を取得します。
     *
     * @param from 集計開始日時（この日時を含む）
     * @param to   集計終了日時（この日時を含まない）
     * @return 指定した期間における部品の集計結果
     */
    public List<PartStockSummary> getPartSummaries(
            LocalDate from, LocalDate to) {
        return partSummaryMapper.findSummaryByPeriod(from, to);
    }
}
