package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.dto.PartStockSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 指定した期間の部品の入出庫数を集計します。
 */
@Mapper
public interface PartStockSummaryMapper {
    List<PartStockSummary> findSummaryByPeriod(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}