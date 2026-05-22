package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.PartMaster;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class PartMasterMapperTest {

    @Autowired
    private PartMasterMapper partMasterMapper;

    @Test
    void findAll_全件取得できる() {
        List<PartMaster> partMasters = partMasterMapper.findAll();

        assertThat(partMasters).hasSize(2);
        assertThat(partMasters)
                .extracting(PartMaster::getPartName)
                .containsExactly(
                        "CPU",
                        "Memory"
                );
    }

    @Test
    void findById_存在するIDの部品マスタを取得できる() {
        PartMaster partMaster = partMasterMapper.findById(1L);

        assertThat(partMaster).isNotNull();
        assertThat(partMaster.getId())
                .isEqualTo(1L);
        assertThat(partMaster.getPartName())
                .isEqualTo("CPU");
    }

    @Test
    void findById_存在しないIDの場合nullを返す() {
        PartMaster partMaster = partMasterMapper.findById(999L);
        assertThat(partMaster).isNull();
    }
}