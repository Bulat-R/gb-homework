package gb.spring.homework.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class Product extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("company_id")
    private Company company;

    @NotNull
    @Min(value = 0)
    private BigDecimal cost;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order> orders;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", company_id=" + company.id +
                ", cost=" + cost +
                '}';
    }
}
