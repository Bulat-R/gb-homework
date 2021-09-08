package gb.spring.homework.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gb.spring.homework.deserializer.ProductJsonDeserializer;
import gb.spring.homework.validator.CompanyInList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

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
@JsonDeserialize(using = ProductJsonDeserializer.class)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Product extends AbstractEntity {

    @CompanyInList
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("company_id")
    private Company company;

    @NotNull
    @Min(value = 0)
    private BigDecimal cost;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
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
