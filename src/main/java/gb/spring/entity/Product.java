package gb.spring.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private long id;
    private String title;
    private double cost;
}
