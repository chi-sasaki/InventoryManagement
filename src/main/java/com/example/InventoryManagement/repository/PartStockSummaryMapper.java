package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.entity.StockHistoryPart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 指定した期間の部品の入出庫数を集計するMapperインターフェースです。
 */
@Mapper
public interface PartStockSummaryMapper {
    /**
     * 指定した期間内の部品ごとの入出庫数を集計して取得します。
     *
     * @param from 集計開始日（この日を含む）
     * @param to   集計終了日（この日を含む）
     * @return 部品ごとの入出庫数をまとめたリスト
     */
    List<PartStockSummary> findSummaryByPeriod(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    /**
     * 指定された部品の入出庫履歴を登録します。
     *
     * @param history 部品入出庫履歴情報
     */
    void insertHistory(StockHistoryPart history);

    /**
     * 指定された部品IDの入出庫履歴が既に存在するかを確認します。
     *
     * @param partId 確認対象の部品ID
     * @return 入出庫履歴が存在する場合は true、存在しない場合は false
     */
    boolean existsHistoryByPartId(@Param("partId") Long partId);
}