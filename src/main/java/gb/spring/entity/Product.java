package gb.spring.entity;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
public class Product {
    private static final AtomicLong count = new AtomicLong(0);

    private final long id;
    private final String title;
    private final BigDecimal price;

    public Product(String title, double price) {
        id = count.incrementAndGet();
        this.title = title;
        this.price = new BigDecimal(price).setScale(2, RoundingMode.DOWN);
    }
}
