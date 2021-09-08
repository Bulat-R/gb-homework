package gb.spring.homework.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"name"})
public class Order extends AbstractEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("product_id")
    private Product product;

    @Column(name = "selling_price")
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer count;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user.id +
                ", product_id=" + product.id +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
