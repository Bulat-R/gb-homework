package gb.spring.homework.dto;

import gb.spring.homework.model.Order;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private long id;
    private LocalDateTime dateTime;
    private long productId;
    private String productName;
    private BigDecimal productPrice;
    private int productCount;
    private long userId;
    private String userName;
    private long companyId;
    private String companyName;

    public static OrderDto from(Order o) {
        return OrderDto.builder()
                .id(o.getId())
                .dateTime(o.getDate())
                .productId(o.getProduct().getId())
                .productName(o.getProduct().getName())
                .productPrice(o.getPrice())
                .productCount(o.getCount())
                .userId(o.getUser().getId())
                .userName(o.getUser().getName())
                .companyId(o.getCompany().getId())
                .companyName(o.getCompany().getName())
                .build();
    }
}
