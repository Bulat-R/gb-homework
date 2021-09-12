package gb.spring.homework.service;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.User;
import gb.spring.homework.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService extends AbstractService<User> {

    private final OrderService orderService;

    public UserService(UserRepository repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }

    public List<OrderDto> findOrders(Long userId) {
        return orderService.findByUser(userId);
    }
}
