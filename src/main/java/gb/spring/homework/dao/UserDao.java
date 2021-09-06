package gb.spring.homework.dao;

import gb.spring.homework.model.Order;
import gb.spring.homework.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao extends AbstractDao<User> {

    protected UserDao() {
        super(User.class);
    }

    public List<Order> findOrders(Long userId) {
        return wrapper.wrap(session -> session.find(User.class, userId).getOrders());
    }
}
