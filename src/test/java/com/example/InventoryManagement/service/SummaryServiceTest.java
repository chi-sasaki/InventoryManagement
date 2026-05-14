package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.repository.PartStockSummaryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {

    @Mock
    private PartStockSummaryMapper partSummaryMapper;

    @Test
    void 指定期間の部品集計結果を取得する() {
        SummaryService sut = new SummaryService(partSummaryMapper);
        // テストデータ
        LocalDate from = LocalDate.of(2025, 1, 1);
        LocalDate to = LocalDate.of(2025, 1, 31);
        PartStockSummary summary1 = new PartStockSummary();
        PartStockSummary summary2 = new PartStockSummary();

        List<PartStockSummary> expected = List.of(summary1, summary2);
        // モック設定
        when(partSummaryMapper.findSummaryByPeriod(from, to)).thenReturn(expected);
        // 実行
        List<PartStockSummary> actual = sut.getPartSummaries(from, to);
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(partSummaryMapper).findSummaryByPeriod(from, to);
    }
}