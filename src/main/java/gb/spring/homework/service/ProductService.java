package gb.spring.homework.service;

import gb.spring.homework.dao.CompanyDao;
import gb.spring.homework.dao.ProductDao;
import gb.spring.homework.exception.IdNotFoundException;
import gb.spring.homework.model.Product;
import gb.spring.homework.model.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao dao;
    private final CompanyDao companyDao;

    public List<Product> findAll(String... sortBy) {
        return dao.findAll(sortBy);
    }

    public void save(Product product) {
        product.setCompany(companyDao.findByName(product.getCompany().getName()));
        if (product.getId() != null && dao.findById(product.getId()) == null) {
            throw new IdNotFoundException();
        }
        dao.save(product);
    }

    public Product getById(Long id) {
        Product product = dao.findById(id);
        if (product == null) {
            throw new IdNotFoundException();
        }
        return product;
    }

    public void deleteById(Long id) {
        if (dao.deleteById(id) == 0) {
            throw new IdNotFoundException();
        }
    }

    public List<Product> findByFilter(ProductFilter filter, String... sortBy) {
        return dao.findByCriteria(filter, sortBy);
    }
}
