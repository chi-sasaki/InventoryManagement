package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.repository.PartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部品情報の検索、登録、更新、削除を行います。
 */
@Service
public class PartService {
    private final PartMapper partMapper;

    public PartService(PartMapper partMapper) {
        this.partMapper = partMapper;
    }

    /**
     * 指定したIDの部品情報を取得します。
     *
     * @param id 取得対象の部品ID
     * @return 指定したIDの部品情報
     */
    public Part findById(Long id) {
        return partMapper.findById(id);
    }

    /**
     * 指定した工程IDに紐づく部品情報の一覧を取得します。
     *
     * @param processId 工程ID
     * @return 工程IDに紐づく部品情報の一覧
     */
    public List<Part> findByProcessId(Long processId) {
        return partMapper.findByProcessId(processId);
    }

    /**
     * 全ての部品情報を取得します。
     *
     * @return 取得した部品情報の一覧
     */
    public List<Part> findAll() {
        return partMapper.findAll();
    }

    /**
     * 指定した部品名を条件に部品情報を検索します。
     *
     * @param partName 検索対象の部品名
     * @return 条件に一致する部品情報の一覧
     */
    public List<Part> findByPartName(String partName) {
        return partMapper.findByPartName(partName);
    }

    /**
     * 部品情報を新規登録します。
     *
     * @param part 登録する部品情報
     */
    @Transactional
    public void registerPart(Part part) {
        partMapper.registerPart(part);
    }

    /**
     * 指定したIDの部品情報を更新します。
     *
     * @param part 更新対象の部品情報
     */
    @Transactional
    public void updatePart(Part part) {
        partMapper.updatePart(part);
    }

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 削除対象の部品ID
     */
    @Transactional
    public void deletePart(Long id) {
        Part part = partMapper.findById(id);
        if (part == null) {
            throw new IllegalArgumentException("部品が存在しません");
        }
        if (part.getStockQuantity() > 0) {
            throw new IllegalStateException("在庫が残っている部品は削除できません");
        }
        partMapper.deletePart(id);
    }

    @Transactional
    public void deleteParts(List<Long> ids) {
        for (Long id : ids) {
            deletePart(id);
        }
    }
}
