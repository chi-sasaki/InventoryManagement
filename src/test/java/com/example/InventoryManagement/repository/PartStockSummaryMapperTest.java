package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.entity.StockHistoryPart;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class PartStockSummaryMapperTest {

    @Autowired
    private PartStockSummaryMapper mapper;

    @Test
    void findSummaryByPeriod_期間内の集計結果を取得できる() {

        List<PartStockSummary> summaries =
                mapper.findSummaryByPeriod(
                        LocalDate.of(2026, 5, 1),
                        LocalDate.of(2026, 5, 31));

        assertThat(summaries).hasSize(3);

        PartStockSummary cpu001 = summaries.stream()
                .filter(s -> s.getPartId().equals(1L))
                .findFirst()
                .orElseThrow();

        assertThat(cpu001.getPartName())
                .isEqualTo("CPU");

        assertThat(cpu001.getModelNumber())
                .isEqualTo("CPU-001");

        assertThat(cpu001.getTotalIn())
                .isEqualTo(10);

        assertThat(cpu001.getTotalOut())
                .isEqualTo(3);

        assertThat(cpu001.getDifference())
                .isEqualTo(7);
    }

    @Test
    void findSummaryByPeriod_履歴が存在しない期間は入出庫数が0になる() {

        List<PartStockSummary> summaries =
                mapper.findSummaryByPeriod(
                        LocalDate.of(2030, 1, 1),
                        LocalDate.of(2030, 12, 31));

        PartStockSummary cpu001 = summaries.stream()
                .filter(s -> s.getPartId().equals(1L))
                .findFirst()
                .orElseThrow();

        assertThat(cpu001.getTotalIn())
                .isEqualTo(0);

        assertThat(cpu001.getTotalOut())
                .isEqualTo(0);

        assertThat(cpu001.getDifference())
                .isEqualTo(0);
    }

    @Test
    void insertHistory_入出庫履歴を登録できる() {

        StockHistoryPart history =
                new StockHistoryPart();

        history.setPartId(1L);
        history.setQuantity(20);
        history.setActionAt(
                LocalDate.of(2026, 5, 10));

        mapper.insertHistory(history);

        List<PartStockSummary> summaries =
                mapper.findSummaryByPeriod(
                        LocalDate.of(2026, 5, 1),
                        LocalDate.of(2026, 5, 31));

        PartStockSummary cpu001 = summaries.stream()
                .filter(s -> s.getPartId().equals(1L))
                .findFirst()
                .orElseThrow();

        assertThat(cpu001.getTotalIn())
                .isEqualTo(30); // 10 + 20

        assertThat(cpu001.getTotalOut())
                .isEqualTo(3);
    }

    @Test
    void existsHistoryByPartId_履歴が存在する場合true() {
        boolean result =
                mapper.existsHistoryByPartId(1L);

        assertThat(result).isTrue();
    }

    @Test
    void existsHistoryByPartId_履歴が存在しない場合false() {
        boolean result =
                mapper.existsHistoryByPartId(999L);

        assertThat(result).isFalse();
    }
}