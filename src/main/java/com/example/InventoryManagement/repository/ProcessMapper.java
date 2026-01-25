package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessMapper {

    /**
     * 全ての工程を取得します。
     *
     * @return 全ての工程の一覧
     */
    List<ManufacturingProcess> findAll();
}