package gb.spring.homework.service;

import gb.spring.homework.exception.IdNotFoundException;
import gb.spring.homework.model.Company;
import gb.spring.homework.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository repository;

    public Company getByName(String name) {
        return repository.getByName(name).orElse(null);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company save(Company company) {
        return repository.getByName(company.getName()).orElse(repository.save(company));
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).orElseThrow(IdNotFoundException::new));
    }

    public Company getById(Long id) {
        return repository.findById(id).orElseThrow(IdNotFoundException::new);
    }
}
