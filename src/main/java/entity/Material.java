package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Material extends StockRecord {
    private Long materialId;
    private String materialName;
    private Long processId;
}
