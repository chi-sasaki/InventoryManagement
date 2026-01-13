package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends StockRecord {
    private Long productId;
    private String productName;
}
