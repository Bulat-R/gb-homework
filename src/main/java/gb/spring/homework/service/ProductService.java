package gb.spring.homework.service;

import gb.spring.homework.dao.ProductDao;
import gb.spring.homework.dao.filter.ProductFilter;
import gb.spring.homework.model.Order;
import gb.spring.homework.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao dao;
    private final CompanyService companyService;

    public List<Product> findAll(List<String> sortBy) {
        return dao.findAll(sortBy);
    }

    public void save(Product product) {
        product.setCompany(companyService.getByName(product.getCompany().getName()));
        dao.save(product);
    }

    public Product getById(Long id) {
        return dao.findById(id);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public List<Product> findByFilter(ProductFilter filter, List<String> sortBy) {
        return dao.findByCriteria(filter, sortBy);
    }

    public List<Order> findOrders(Long productId) {
        return dao.findOrders(productId);
    }
}
