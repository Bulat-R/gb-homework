package gb.spring.homework.service;

import gb.spring.homework.exception.CompanyNotFoundException;
import gb.spring.homework.exception.IdNotFoundException;
import gb.spring.homework.exception.SortParamsNotValidException;
import gb.spring.homework.model.Product;
import gb.spring.homework.repository.CompanyRepository;
import gb.spring.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final CompanyRepository companyRepository;

    public List<Product> findAll(String... sortFields) {
        Sort sort = sortFields == null ? Sort.unsorted() : Sort.by(sortFields);
        try {
            return repository.findAll(sort);
        } catch (PropertyReferenceException e) {
            throw new SortParamsNotValidException();
        }
    }

    public Product save(Product product) {
        product.setCompany(companyRepository.getByName(product.getCompany().getName()).orElseThrow(CompanyNotFoundException::new));
        return repository.getByNameAndCompany(product.getName(), product.getCompany()).orElse(repository.save(product));
    }

    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).orElseThrow(IdNotFoundException::new));
    }

    public List<Product> getByCriteria(String name, BigDecimal minCost, BigDecimal maxCost, String companyName, String... sortBy) {
        Sort sort = sortBy == null ? Sort.unsorted() : Sort.by(sortBy);
        try {
            return repository.findAll((Specification<Product>) (root, query, builder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (name != null) predicates.add(builder.like(root.get("name"), "%" + name + "%"));
                if (minCost != null) predicates.add(builder.greaterThanOrEqualTo(root.get("cost"), minCost));
                if (maxCost != null) predicates.add(builder.lessThanOrEqualTo(root.get("cost"), maxCost));
                if (companyName != null)
                    predicates.add(builder.like(root.get("company").get("name"), "%" + companyName + "%"));
                return builder.and(predicates.toArray(new Predicate[0]));
            }, sort);
        } catch (PropertyReferenceException e) {
            throw new SortParamsNotValidException();
        }
    }
}
