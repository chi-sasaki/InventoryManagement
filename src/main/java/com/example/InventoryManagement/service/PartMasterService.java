package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.PartMaster;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.PartMasterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部品マスタ情報に関するデータを取得するサービスクラスです。
 */
@Service
public class PartMasterService {
    private final PartMasterMapper mapper;

    public PartMasterService(PartMasterMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 部品マスタ情報を全件取得します。
     *
     * @return 部品マスタ情報の一覧
     */
    public List<PartMaster> findAll() {
        return mapper.findAll();
    }

    /**
     * 指定されたIDの部品マスタ情報を取得します。
     *
     * @param id 部品マスタID
     * @return 部品マスタ情報
     */
    public PartMaster findById(Long id) {
        PartMaster partMaster = mapper.findById(id);
        if (partMaster == null) {
            throw new ResourceNotFoundException(
                    "部品マスタが存在しません");
        }
        return partMaster;
    }
}
