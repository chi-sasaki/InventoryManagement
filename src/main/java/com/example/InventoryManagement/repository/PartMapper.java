package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.entity.Part;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部品情報テーブルに対するSQL操作を定義するMapperです。
 */
@Mapper
public interface PartMapper {

    /**
     * 指定したIDの部品情報を取得します。
     *
     * @param id 取得対象の部品ID
     * @return 指定したIDの部品情報
     */
    Part findById(Long id);

    /**
     * 全ての部品情報を取得します。
     *
     * @return 取得した部品情報の一覧
     */
    List<Part> findAll();

    /**
     * 指定した部品名を条件に部品情報を検索します。
     *
     * @param partName 検索対象の部品名
     * @return 条件に一致する部品情報の一覧
     */
    List<Part> findByPartName(String partName);

    /**
     * 部品情報を新規登録します。
     *
     * @param part 登録する部品情報
     */
    void registerPart(Part part);

    /**
     * 指定したIDの部品情報を更新します。
     *
     * @param part 更新対象の部品情報
     */
    void updatePart(Part part);

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 削除対象の部品ID
     */
    void deletePart(Long id);

    /**
     * 指定された工程IDに紐づく部品一覧を取得します。
     *
     * @param processId 工程ID
     * @return 工程IDに紐づく部品一覧
     */
    List<Part> findByProcessId(Long processId);
}
