package gb.spring.homework.repository;

import gb.spring.homework.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends AbstractEntityRepository<Company> {
}
