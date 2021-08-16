package gb.spring.menu;

import gb.spring.entity.Product;
import lombok.experimental.SuperBuilder;

import java.util.Locale;

@SuperBuilder
public class SingleProductMenu extends Menu {
    @Override
    protected void action(String str) {
        Product product = repository.getByName(getTitle().toLowerCase(Locale.ROOT)).orElse(null);
        switch (str) {
            case "back":
                parent.init();
                break;
            case "exit":
                break;
            case "1":
                cart.add(product, 1);
                parent.init();
                break;
            case "2":
                cart.remove(product, 1);
                parent.getParent().init();
                break;
        }
    }
}
