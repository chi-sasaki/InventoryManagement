package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.ProcessMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessServiceTest {

    @Mock
    private ProcessMapper mapper;

    @Test
    void 全工程の一覧を取得する() {
        ProcessService sut = new ProcessService(mapper);
        // テストデータ
        ManufacturingProcess process1 = new ManufacturingProcess();
        process1.setId(1L);
        ManufacturingProcess process2 = new ManufacturingProcess();
        process2.setId(2L);

        List<ManufacturingProcess> expected = List.of(process1, process2);
        // モック設定
        when(mapper.findAll()).thenReturn(expected);
        // 実行
        List<ManufacturingProcess> actual = sut.findAll();
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(mapper).findAll();
    }

    @Test
    void 指定したIDの工程を取得する() {
        ProcessService sut = new ProcessService(mapper);
        Long id = 1L;

        // テストデータ
        ManufacturingProcess expected = new ManufacturingProcess();
        expected.setId(id);
        // モック設定
        when(mapper.findById(id)).thenReturn(expected);
        // 実行
        ManufacturingProcess actual = sut.findById(id);
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(mapper).findById(id);
    }

    @Test
    void 指定したIDの工程が存在しない場合は例外を投げる() {
        ProcessService sut = new ProcessService(mapper);
        Long id = 1L;

        // null を返す設定
        when(mapper.findById(id)).thenReturn(null);
        // 例外確認
        assertThrows(ResourceNotFoundException.class,
                () -> sut.findById(id)
        );
        // 呼び出し確認
        verify(mapper).findById(id);
    }
}