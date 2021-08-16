package gb.spring.repository;

import gb.spring.entity.Product;
import gb.spring.exception.IdNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {
    private static final AtomicLong count = new AtomicLong();
    private final List<Product> list = new CopyOnWriteArrayList<>();

    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(count.incrementAndGet());
            list.add(product);
        } else {
            Product old = getById(product.getId());
            old.setTitle(product.getTitle());
            old.setPrice(product.getPrice());
        }
    }

    public List<Product> getAll() {
        return Collections.unmodifiableList(list);
    }

    public Product getById(long id) {
        return list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(IdNotFoundException::new);
    }

    public void remove(Long id) {
        list.remove(getById(id));
    }
}
