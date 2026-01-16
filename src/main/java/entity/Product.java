package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends StockRecord {
    private Long id;
    private String productName;
}
