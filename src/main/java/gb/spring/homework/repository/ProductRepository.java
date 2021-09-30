package gb.spring.homework.repository;

import gb.spring.homework.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends AbstractEntityRepository<Product> {
}
