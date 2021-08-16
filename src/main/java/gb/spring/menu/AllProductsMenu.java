package gb.spring.menu;

import gb.spring.entity.Product;
import lombok.experimental.SuperBuilder;

import java.util.Locale;

@SuperBuilder
public class AllProductsMenu extends Menu {

    @Override
    protected void action(String str) {
        switch (str) {
            case "back":
                parent.init();
                break;
            case "exit":
                break;
            default:
                Product product = repository.getById(Long.parseLong(str)).orElseThrow(IllegalArgumentException::new);
                Menu single = SingleProductMenu.builder()
                        .title(product.getTitle().toUpperCase(Locale.ROOT))
                        .reader(getReader())
                        .cart(getCart())
                        .parent(this)
                        .repository(getRepository())
                        .build();

                single.getBars().put("1", "add to cart");
                single.getBars().put("back", "return to previous menu");
                single.getBars().put("exit", "exit");

                single.init();
                break;
        }
    }
}
