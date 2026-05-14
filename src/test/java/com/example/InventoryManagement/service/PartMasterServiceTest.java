package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.PartMaster;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.PartMasterMapper;
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
class PartMasterServiceTest {

    @Mock
    private PartMasterMapper mapper;

    @Test
    void 部品マスタ情報を全件取得する() {
        PartMasterService sut = new PartMasterService(mapper);
        // テストデータ
        PartMaster part1 = new PartMaster();
        part1.setId(1L);
        PartMaster part2 = new PartMaster();
        part2.setId(2L);

        List<PartMaster> expected = List.of(part1, part2);
        // モック設定
        when(mapper.findAll()).thenReturn(expected);
        // 実行
        List<PartMaster> actual = sut.findAll();
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(mapper).findAll();
    }

    @Test
    void 指定したIDの部品マスタ情報を取得する() {
        PartMasterService sut = new PartMasterService(mapper);
        Long id = 1L;

        // テストデータ
        PartMaster expected = new PartMaster();
        expected.setId(id);
        // モック設定
        when(mapper.findById(id)).thenReturn(expected);
        // 実行
        PartMaster actual = sut.findById(id);
        // 検証
        assertEquals(expected, actual);
        // 呼び出し確認
        verify(mapper).findById(id);
    }

    @Test
    void 指定したIDの部品マスタ情報が存在しない場合は例外を投げる() {
        PartMasterService sut = new PartMasterService(mapper);
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