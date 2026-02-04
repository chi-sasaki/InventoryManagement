package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.PartMaster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部品マスタ情報をデータベースから取得するMapperインターフェースです。
 */
@Mapper
public interface PartMasterMapper {

    /**
     * 部品マスタ情報を全件取得します。
     *
     * @return 部品マスタ情報の一覧
     */
    List<PartMaster> findAll();

    /**
     * 指定されたIDの部品マスタ情報を取得します。
     *
     * @param id 部品マスタID
     * @return 部品マスタ情報
     */
    PartMaster findById(Long id);

}
