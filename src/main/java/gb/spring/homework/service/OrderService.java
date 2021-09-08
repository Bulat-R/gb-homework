package gb.spring.homework.service;

import gb.spring.homework.dao.OrderDao;
import gb.spring.homework.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao dao;
    private final ProductService productService;
    private final UserService userService;

    public List<Order> findAll() {
        return dao.findAll();
    }

    public void save(Order order) {
        order.setUser(userService.findById(order.getUser().getId()));
        order.setProduct(productService.getById(order.getProduct().getId()));
        dao.save(order);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public Order getById(Long id) {
        return dao.findById(id);
    }
}
