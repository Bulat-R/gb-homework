package gb.spring.homework.repository;

import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> getByNameAndCompany(String name, Company company);
}
