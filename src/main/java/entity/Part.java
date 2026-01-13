package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Part extends StockRecord {
    private Long partId;
    private String partName;
    private Long processId;
}
