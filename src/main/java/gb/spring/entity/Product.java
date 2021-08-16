package gb.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private BigDecimal price;
}
