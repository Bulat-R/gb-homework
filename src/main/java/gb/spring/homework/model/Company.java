package gb.spring.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "company",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Company extends AbstractEntity {

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private List<Product> products;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private List<Order> orders;
}
