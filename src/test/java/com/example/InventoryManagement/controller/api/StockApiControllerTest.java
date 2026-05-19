package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.dto.PartStockSummary;
import com.example.InventoryManagement.service.SummaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StockApiController.class)
class StockApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SummaryService summaryService;

    @Test
    @DisplayName("指定期間の部品入出庫集計を取得できる")
    void testGetPartSummaries() throws Exception {

        PartStockSummary summary = new PartStockSummary();

        LocalDate from = LocalDate.of(2025, 1, 1);
        LocalDate to = LocalDate.of(2025, 1, 31);

        when(summaryService.getPartSummaries(
                from,
                to.plusDays(1)))
                .thenReturn(List.of(summary));

        mockMvc.perform(get("/api/stock/summary")
                        .param("from", "2025-01-01")
                        .param("to", "2025-01-31"))
                .andExpect(status().isOk());

        verify(summaryService, times(1))
                .getPartSummaries(
                        from,
                        to.plusDays(1));
    }
}