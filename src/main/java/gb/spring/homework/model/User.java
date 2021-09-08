package gb.spring.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@ToString(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntity {

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;
}
