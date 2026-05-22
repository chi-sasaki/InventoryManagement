package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Part;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class PartMapperTest {

    @Autowired
    private PartMapper partMapper;

    @Test
    void findAll_全件取得できる() {
        List<Part> parts = partMapper.findAll();
        assertThat(parts).hasSize(3);
    }

    @Test
    void findById_存在するIDの部品を取得できる() {
        Part part = partMapper.findById(1L);
        assertThat(part).isNotNull();
        assertThat(part.getId()).isEqualTo(1L);
        assertThat(part.getPartName()).isEqualTo("CPU");
        assertThat(part.getModelNumber()).isEqualTo("CPU-001");
    }

    @Test
    void findById_存在しないIDの場合nullを返す() {
        Part part = partMapper.findById(999L);
        assertThat(part).isNull();
    }

    @Test
    void findByPartMasterId_指定部品マスタの部品一覧を取得できる() {
        List<Part> parts = partMapper.findByPartMasterId(1L);

        assertThat(parts).hasSize(2);
        assertThat(parts)
                .extracting(Part::getModelNumber)
                .containsExactly(
                        "CPU-001",
                        "CPU-002"
                );
    }

    @Test
    void findByPartMasterId_該当データなしの場合空リスト() {
        List<Part> parts = partMapper.findByPartMasterId(999L);
        assertThat(parts).isEmpty();
    }

    @Test
    void findByProcessId_工程IDに紐づく部品を取得できる() {
        List<Part> parts = partMapper.findByProcessId(100L);
        assertThat(parts).hasSize(2);
    }

    @Test
    void registerPart_部品を登録できる() {
        Part part = new Part();
        part.setPartMasterId(1L);
        part.setProcessId(100L);
        part.setModelNumber("CPU-003");
        part.setStockQuantity(15);
        part.setLastOrderedAt(LocalDate.of(2026, 5, 10));

        partMapper.registerPart(part);
        assertThat(part.getId()).isNotNull();
        Part saved = partMapper.findById(part.getId());

        assertThat(saved).isNotNull();
        assertThat(saved.getModelNumber())
                .isEqualTo("CPU-003");
        assertThat(saved.getStockQuantity())
                .isEqualTo(15);
    }

    @Test
    void updatePart_部品情報を更新できる() {
        Part part = partMapper.findById(1L);
        part.setModelNumber("CPU-999");
        part.setStockQuantity(99);
        partMapper.updatePart(part);
        Part updated = partMapper.findById(1L);

        assertThat(updated.getModelNumber())
                .isEqualTo("CPU-999");
        assertThat(updated.getStockQuantity())
                .isEqualTo(99);
    }

    @Test
    void deletePart_部品を削除できる() {
        partMapper.deletePart(1L);
        Part deleted = partMapper.findById(1L);
        assertThat(deleted).isNull();
    }
}