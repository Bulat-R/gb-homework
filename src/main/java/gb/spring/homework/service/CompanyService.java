package gb.spring.homework.service;

import gb.spring.homework.dao.CompanyDao;
import gb.spring.homework.exception.IdNotFoundException;
import gb.spring.homework.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyDao dao;

    public Company getByName(String name) {
        return dao.findByName(name);
    }

    public List<Company> findAll() {
        return dao.findAll();
    }

    public void save(Company company) {
        if (getByName(company.getName()) == null) {
            company.setId(null);
            dao.save(company);
        }
    }

    public void deleteById(Long id) {
        if (dao.deleteById(id) == 0) {
            throw new IdNotFoundException();
        }
    }

    public Company getById(Long id) {
        Company company = dao.findById(id);
        if (company == null) {
            throw new IdNotFoundException();
        }
        return company;
    }
}
