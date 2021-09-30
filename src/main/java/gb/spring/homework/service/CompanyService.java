package gb.spring.homework.service;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import gb.spring.homework.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService extends AbstractService<Company> {

    private final OrderService orderService;

    public CompanyService(CompanyRepository repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }

    public List<Product> findProducts(Long companyId) {
        return findById(companyId).getProducts();
    }

    public List<OrderDto> findOrders(Long companyId) {
        return orderService.findByCompany(companyId);
    }
}
