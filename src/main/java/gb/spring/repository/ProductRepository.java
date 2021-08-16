package gb.spring.repository;

import gb.spring.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ProductRepository {
    private List<Product> list;

    @PostConstruct
    private void initList() {
        list = new CopyOnWriteArrayList<>();
        list.add(new Product("pen", 2.54));
        list.add(new Product("notebook", 1.22));
        list.add(new Product("schoolbook", 9.8));
        list.add(new Product("pen-box", 2));
        list.add(new Product("schoolbag", 19.99));
    }

    public Optional<Product> getById(long id) {
        return list.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Product> getByName(String name) {
        return list.stream().filter(p -> p.getTitle().equalsIgnoreCase(name)).findFirst();
    }

    public List<Product> getAll() {
        return Collections.unmodifiableList(list);
    }
}
