package gb.spring.homework.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gb.spring.homework.deserializer.ProductJsonDeserializer;
import gb.spring.homework.validator.CompanyInList;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
@JsonDeserialize(using = ProductJsonDeserializer.class)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @CompanyInList
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @NotNull
    @Min(value = 0)
    private BigDecimal cost;
}
