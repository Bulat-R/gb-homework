package gb.spring.homework.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "selling_price")
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer count;

    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.id +
                ", product=" + product.id +
                ", company=" + company.id +
                ", price=" + price +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}
