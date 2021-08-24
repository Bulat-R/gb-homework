package gb.spring.homework.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gb.spring.homework.deserializer.ProductJsonDeserializer;
import gb.spring.homework.validator.CompanyInList;
import lombok.Data;

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
