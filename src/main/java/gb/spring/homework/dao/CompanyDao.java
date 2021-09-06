package gb.spring.homework.dao;

import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDao extends AbstractDao<Company> {
    protected CompanyDao() {
        super(Company.class);
    }

    public List<Product> findProducts(Long companyId) {
        return wrapper.wrap(session -> session.find(Company.class, companyId).getProducts());
    }
}
