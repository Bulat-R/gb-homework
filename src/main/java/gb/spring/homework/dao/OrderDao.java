package gb.spring.homework.dao;

import gb.spring.homework.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends AbstractDao<Order> {
    protected OrderDao() {
        super(Order.class);
    }
}
