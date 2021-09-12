package gb.spring.homework.service;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.Product;
import gb.spring.homework.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends AbstractService<Product> {

    private final OrderService orderService;

    public ProductService(ProductRepository repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }

    public List<OrderDto> findOrders(Long productId) {
        return orderService.findByProduct(productId);
    }
}
