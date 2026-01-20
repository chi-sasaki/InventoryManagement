package controller.api;

import entity.Part;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.PartService;

import java.util.List;


/**
 * 部品情報の一覧表示・登録・更新・削除を行う
 * RESTAPI用のコントローラクラスです。
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
     * 部品名を条件に部品情報を検索します。
     *
     * @param partName 検索対象の部品名
     * @return 条件に一致する部品情報の一覧
     */
    @GetMapping("/api/parts/search")
    public List<Part> searchByName(@RequestParam String partName) {
        return partService.findByPartName(partName);
    }

    /**
     * 部品情報を新規登録します。
     *
     * @param part 登録する部品情報
     * @return 登録成功時は 201 Created
     */
    @PostMapping("/api/parts/register")
    public ResponseEntity<Void> registerPart(@RequestBody @Valid Part part) {
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
    public ResponseEntity<Void> updatePart(@PathVariable @NotNull Long id, @RequestBody @Valid Part part) {
        part.setId(id);
        partService.updatePart(part);
        return ResponseEntity.ok().build();
    }

    /**
     * 指定したIDの部品情報を削除します。
     *
     * @param id 削除対象の部品ID
     * @return 削除成功時は 204 No Content
     */
    @DeleteMapping("/api/parts/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable @NotNull Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
