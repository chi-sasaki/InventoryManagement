package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.service.PartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部品情報の取得・登録・更新を行うRESTAPI用のコントローラクラスです。
 */
@Validated
@RestController
public class PartApiController {
    private final PartService partService;

    public PartApiController(PartService partService) {
        this.partService = partService;
    }

    /**
     * 全ての部品情報を取得します。
     *
     * @return 部品情報の一覧
     */
    @GetMapping("/api/parts")
    public List<Part> list() {
        return partService.findAll();
    }

    /**
     * 指定したIDの部品情報を取得します。
     *
     * @param id 部品ID
     * @return 指定したIDの部品情報
     */
    @GetMapping("/api/parts/{id}")
    public Part get(@PathVariable Long id) {
        return partService.findById(id);
    }

    /**
     * 部品情報を新規登録します。
     *
     * @param part 登録する部品情報
     * @return 登録成功時は 201 Created
     */
    @PostMapping("/api/parts")
    public ResponseEntity<Void> register(@RequestBody @Valid Part part) {
        partService.registerPart(part);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 指定したIDの部品情報を更新します。
     *
     * @param id   更新対象の部品ID
     * @param part 更新後の部品情報
     * @return 更新成功時は 200 OK
     */
    @PutMapping("/api/parts/{id}")
    public ResponseEntity<Void> update(@PathVariable @NotNull Long id, @RequestBody @Valid Part part) {
        part.setId(id);
        partService.updatePart(part);
        return ResponseEntity.ok().build();
    }

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 削除対象の部品ID
     * @return 現在は削除できない仕様となっているので、409 Conflictが返ってきます。
     */
    @DeleteMapping("/api/parts/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull Long id) {
        try {
            partService.deletePart(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            // 履歴が残っている、在庫が残っている場合
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            // 部品が存在しない場合
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
