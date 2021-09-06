package gb.spring.homework.service;

import gb.spring.homework.dao.UserDao;
import gb.spring.homework.model.Order;
import gb.spring.homework.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao dao;


    public List<User> findAll() {
        return dao.findAll();
    }

    public User findById(Long id) {
        return dao.findById(id);
    }

    public void save(User user) {
        dao.save(user);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public List<Order> findOrders(Long userId) {
        return dao.findOrders(userId);
    }
}
