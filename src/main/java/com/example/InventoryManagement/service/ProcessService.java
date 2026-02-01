package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.ManufacturingProcess;
import com.example.InventoryManagement.repository.ProcessMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 製造工程に関する情報を取得するサービスクラスです。
 */
@Service
public class ProcessService {
    private final ProcessMapper mapper;

    public ProcessService(ProcessMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 全工程の一覧を取得します。
     *
     * @return 全工程の一覧
     */
    public List<ManufacturingProcess> findAll() {
        return mapper.findAll();
    }

    /**
     * 指定IDの工程を取得します。
     *
     * @param id 工程ID
     * @return 該当の工程、存在しない場合はnull
     */
    public ManufacturingProcess findById(Long id) {
        return mapper.findById(id);
    }
}