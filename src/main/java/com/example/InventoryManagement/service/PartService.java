package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.entity.StockHistoryPart;
import com.example.InventoryManagement.repository.PartMapper;
import com.example.InventoryManagement.repository.PartStockSummaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 部品情報の取得、工程ごとの一覧取得、登録、更新、削除、および
 * 入出庫履歴の登録などを行うサービスクラスです。
 *
 */
@Service
public class PartService {
    private final PartMapper partMapper;
    private final PartStockSummaryMapper partStockSummaryMapper;

    public PartService(PartMapper partMapper, PartStockSummaryMapper partStockSummaryMapper) {
        this.partMapper = partMapper;
        this.partStockSummaryMapper = partStockSummaryMapper;
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
     * @return 部品情報の一覧
     */
    public List<Part> findAll() {
        return partMapper.findAll();
    }

    /**
     * 指定された部品マスタIDに紐づく部品情報の一覧を取得します。
     *
     * @param partMasterId 部品マスタID
     * @return 部品情報の一覧
     */
    public List<Part> findByPartMasterId(Long partMasterId) {
        return partMapper.findByPartMasterId(partMasterId);
    }

    /**
     * 部品情報を登録します。
     * 初期在庫が指定されている場合は、入出庫履歴として登録します。
     *
     * @param part 登録する部品情報
     */
    @Transactional
    public void registerPart(Part part) {
        partMapper.registerPart(part);
        if (part.getStockQuantity() != null
                && part.getStockQuantity() > 0) {

            StockHistoryPart history = new StockHistoryPart();
            history.setPartId(part.getId());
            history.setQuantity(part.getStockQuantity());
            history.setActionAt(part.getLastOrderedAt() != null ? part.getLastOrderedAt() : LocalDate.now());
            partStockSummaryMapper.insertHistory(history);
        }
    }

    /**
     * 指定したIDの部品情報を更新します。
     * 在庫数の変更があれば、入出庫履歴として登録します。
     *
     * @param part 更新対象の部品情報
     */
    @Transactional
    public void updatePart(Part part) {
        Part oldPart = partMapper.findById(part.getId());
        int oldQty = oldPart.getStockQuantity() == null ? 0 : oldPart.getStockQuantity();
        int newQty = part.getStockQuantity() == null ? 0 : part.getStockQuantity();
        int diff = newQty - oldQty;
        partMapper.updatePart(part);

        if (diff != 0) {
            StockHistoryPart history = new StockHistoryPart();
            history.setPartId(part.getId());
            history.setQuantity(diff);
            history.setActionAt(part.getLastOrderedAt() != null ? part.getLastOrderedAt() : LocalDate.now());
            partStockSummaryMapper.insertHistory(history);
        }
    }

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 部品ID
     * @throws IllegalArgumentException 部品が存在しない場合
     * @throws IllegalStateException    履歴が存在する場合、または在庫が残っている場合
     */
    @Transactional
    public void deletePart(Long id) {
        Part part = partMapper.findById(id);
        if (part == null) {
            throw new IllegalArgumentException("部品が存在しません");
        }
        if (partStockSummaryMapper.existsHistoryByPartId(id)) {
            throw new IllegalStateException("履歴が存在する部品は削除できません");
        }
        if (part.getStockQuantity() > 0) {
            throw new IllegalStateException("在庫が残っている部品は削除できません");
        }
        partMapper.deletePart(id);
    }

    /**
     * 指定された複数の部品情報を一括削除します。
     *
     * @param ids 部品IDリスト
     * @throws IllegalArgumentException 部品が存在しない場合
     * @throws IllegalStateException    履歴が存在する場合、または在庫が残っている場合
     */
    @Transactional
    public void deleteParts(List<Long> ids) {
        for (Long id : ids) {
            deletePart(id);
        }
    }
}
