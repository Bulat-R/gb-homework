package gb.spring.entity;

import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@ToString
@Component
@Scope("prototype")
public class Cart {
    private static final AtomicLong count = new AtomicLong(0);

    private final long id;
    private final Map<Product, Integer> map;

    public Cart() {
        this.id = count.incrementAndGet();
        map = new HashMap<>();
    }

    public void add(Product product, int amount) {
        map.merge(product, amount, Integer::sum);
    }

    public void remove(Product product, int amount) {
        if (map.get(product) == amount || map.get(product) < amount) {
            map.remove(product);
        } else {
            map.put(product, map.get(product) - amount);
        }
    }

    public Map<Product, Integer> getAll() {
        return map;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public BigDecimal getFullPrice() {
        return map.entrySet().stream()
                .map(p -> p.getKey().getPrice().multiply(new BigDecimal(p.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
