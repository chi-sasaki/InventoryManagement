package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.entity.StockHistoryPart;
import com.example.InventoryManagement.exception.CannotDeleteException;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.PartMapper;
import com.example.InventoryManagement.repository.PartStockSummaryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {
    @Mock
    private PartMapper partMapper;
    @Mock
    private PartStockSummaryMapper partStockSummaryMapper;

    @Test
    void 指定したIDの部品情報を取得する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part expected = new Part();
        expected.setId(id);

        when(partMapper.findById(id)).thenReturn(expected);
        Part actual = sut.findById(id);
        assertEquals(expected, actual);
        verify(partMapper).findById(id);
    }

    @Test
    void 指定したIDの部品情報が存在しない場合は例外を投げる() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;

        when(partMapper.findById(id)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class,
                () -> sut.findById(id)
        );
        verify(partMapper).findById(id);
    }

    @Test
    void 指定した工程IDに紐づく部品情報を取得する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long processId = 1L;
        Part part = new Part();

        List<Part> expected = List.of(part);
        when(partMapper.findByProcessId(processId)).thenReturn(expected);
        List<Part> actual = sut.findByProcessId(processId);
        assertEquals(expected, actual);
        verify(partMapper).findByProcessId(processId);
    }

    @Test
    void 全ての部品情報を取得する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Part part1 = new Part();
        Part part2 = new Part();

        List<Part> expected = List.of(part1, part2);
        when(partMapper.findAll()).thenReturn(expected);
        List<Part> actual = sut.findAll();
        assertEquals(expected, actual);
        verify(partMapper).findAll();
    }

    @Test
    void 指定した部品マスタIDに紐づく部品情報を取得する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long partMasterId = 1L;
        Part part = new Part();

        List<Part> expected = List.of(part);
        when(partMapper.findByPartMasterId(partMasterId)).thenReturn(expected);
        List<Part> actual = sut.findByPartMasterId(partMasterId);
        assertEquals(expected, actual);
        verify(partMapper).findByPartMasterId(partMasterId);
    }

    @Test
    void 初期在庫付きで部品情報を登録する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Part part = new Part();
        part.setId(1L);
        part.setStockQuantity(10);
        part.setLastOrderedAt(LocalDate.now());

        sut.registerPart(part);
        verify(partMapper).registerPart(part);
        verify(partStockSummaryMapper).insertHistory(any(StockHistoryPart.class));
    }

    @Test
    void 初期在庫なしで部品情報を登録する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Part part = new Part();
        part.setId(1L);
        part.setStockQuantity(0);

        sut.registerPart(part);
        verify(partMapper).registerPart(part);
        verify(partStockSummaryMapper, never()).insertHistory(any());
    }

    @Test
    void 在庫変更ありで部品情報を更新する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part oldPart = new Part();
        oldPart.setId(id);
        oldPart.setStockQuantity(5);

        Part newPart = new Part();
        newPart.setId(id);
        newPart.setStockQuantity(10);

        when(partMapper.findById(id)).thenReturn(oldPart);
        sut.updatePart(newPart);
        verify(partMapper).findById(id);
        verify(partMapper).updatePart(newPart);
        verify(partStockSummaryMapper).insertHistory(any(StockHistoryPart.class));
    }

    @Test
    void 在庫変更なしで部品情報を更新する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part oldPart = new Part();
        oldPart.setId(id);
        oldPart.setStockQuantity(10);

        Part newPart = new Part();
        newPart.setId(id);
        newPart.setStockQuantity(10);

        when(partMapper.findById(id)).thenReturn(oldPart);
        sut.updatePart(newPart);
        verify(partMapper).updatePart(newPart);
        verify(partStockSummaryMapper, never()).insertHistory(any());
    }

    @Test
    void 部品情報を削除する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part part = new Part();
        part.setId(id);
        part.setStockQuantity(0);

        when(partMapper.findById(id)).thenReturn(part);
        when(partStockSummaryMapper.existsHistoryByPartId(id)).thenReturn(false);
        sut.deletePart(id);
        verify(partMapper).deletePart(id);
    }

    @Test
    void 履歴が存在する部品は削除できない() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part part = new Part();
        part.setId(id);
        part.setStockQuantity(0);

        when(partMapper.findById(id)).thenReturn(part);
        when(partStockSummaryMapper.existsHistoryByPartId(id)).thenReturn(true);
        assertThrows(CannotDeleteException.class,
                () -> sut.deletePart(id)
        );
    }

    @Test
    void 在庫が残っている部品は削除できない() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id = 1L;
        Part part = new Part();
        part.setId(id);
        part.setStockQuantity(10);

        when(partMapper.findById(id)).thenReturn(part);
        when(partStockSummaryMapper.existsHistoryByPartId(id)).thenReturn(false);
        assertThrows(CannotDeleteException.class,
                () -> sut.deletePart(id)
        );
    }

    @Test
    void 複数の部品情報を削除する() {
        PartService sut = new PartService(partMapper, partStockSummaryMapper);
        Long id1 = 1L;
        Long id2 = 2L;
        Part part1 = new Part();
        part1.setId(id1);
        part1.setStockQuantity(0);

        Part part2 = new Part();
        part2.setId(id2);
        part2.setStockQuantity(0);

        when(partMapper.findById(id1)).thenReturn(part1);
        when(partMapper.findById(id2)).thenReturn(part2);
        when(partStockSummaryMapper.existsHistoryByPartId(id1)).thenReturn(false);
        when(partStockSummaryMapper.existsHistoryByPartId(id2)).thenReturn(false);
        sut.deleteParts(List.of(id1, id2));
        verify(partMapper).deletePart(id1);
        verify(partMapper).deletePart(id2);
    }
}