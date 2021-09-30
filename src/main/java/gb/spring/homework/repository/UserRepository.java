package gb.spring.homework.repository;

import gb.spring.homework.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractEntityRepository<User> {
}
