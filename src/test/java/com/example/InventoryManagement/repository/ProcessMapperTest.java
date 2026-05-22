package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class ProcessMapperTest {

    @Autowired
    private ProcessMapper processMapper;

    @Test
    void findAll_全件取得できる() {
        List<ManufacturingProcess> processes = processMapper.findAll();

        assertThat(processes).hasSize(3);
        assertThat(processes)
                .extracting(ManufacturingProcess::getProcessName)
                .containsExactly(
                        "切断",
                        "加工",
                        "組立"
                );
    }

    @Test
    void findAll_sortOrder順で取得できる() {
        List<ManufacturingProcess> processes = processMapper.findAll();

        assertThat(processes)
                .extracting(ManufacturingProcess::getSortOrder)
                .containsExactly(1, 2, 3);
    }

    @Test
    void findById_存在するIDの工程を取得できる() {
        ManufacturingProcess process = processMapper.findById(1L);

        assertThat(process).isNotNull();
        assertThat(process.getId())
                .isEqualTo(1L);

        assertThat(process.getProcessName())
                .isEqualTo("切断");

        assertThat(process.getSortOrder())
                .isEqualTo(1);
    }

    @Test
    void findById_存在しないIDの場合nullを返す() {
        ManufacturingProcess process =
                processMapper.findById(999L);

        assertThat(process).isNull();
    }
}