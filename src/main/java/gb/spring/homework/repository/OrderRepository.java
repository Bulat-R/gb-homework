package gb.spring.homework.repository;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query(value = "select new gb.spring.homework.dto.OrderDto(o.id, " +
            "o.date, " +
            "o.product.id, " +
            "o.product.name, " +
            "o.price, o.count, " +
            "o.user.id, " +
            "o.user.name, " +
            "o.company.id, " +
            "o.company.name) " +
            "from Order o where o.company.id=:id")
    List<OrderDto> findByCompanyId(Long id);

    @Query(value = "select new gb.spring.homework.dto.OrderDto(o.id, " +
            "o.date, " +
            "o.product.id, " +
            "o.product.name, " +
            "o.price, o.count, " +
            "o.user.id, " +
            "o.user.name, " +
            "o.company.id, " +
            "o.company.name) " +
            "from Order o where o.user.id=:id")
    List<OrderDto> findByUserId(Long id);

    @Query(value = "select new gb.spring.homework.dto.OrderDto(o.id, " +
            "o.date, " +
            "o.product.id, " +
            "o.product.name, " +
            "o.price, o.count, " +
            "o.user.id, " +
            "o.user.name, " +
            "o.company.id, " +
            "o.company.name) " +
            "from Order o where o.product.id=:id")
    List<OrderDto> findByProductId(Long id);
}
