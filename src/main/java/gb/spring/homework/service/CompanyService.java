package gb.spring.homework.service;

import gb.spring.homework.dao.CompanyDao;
import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyDao dao;

    public Company getByName(String name) {
        return dao.findByName(name).get(0);
    }

    public List<Company> findAll() {
        return dao.findAll();
    }

    public void save(Company company) {
        dao.save(company);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public Company getById(Long id) {
        return dao.findById(id);
    }

    public List<Product> findProducts(Long companyId) {
        return dao.findProducts(companyId);
    }
}
