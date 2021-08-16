package gb.spring.menu;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MainMenu extends Menu {

    @Override
    public void action(String str) {
        switch (str) {
            case "1":
                Menu products = AllProductsMenu.builder()
                        .title("PRODUCTS")
                        .reader(getReader())
                        .cart(getCart())
                        .parent(this)
                        .repository(getRepository())
                        .build();

                repository.getAll()
                        .forEach(p -> products.getBars().put(String.valueOf(p.getId()), p.getTitle() + " (price " + p.getPrice().doubleValue() + ")"));
                products.getBars().put("back", "return to previous menu");
                products.getBars().put("exit", "exit");

                products.init();
                break;
            case "2":
                Menu cart = CartMenu.builder()
                        .title("CART (full price " + getCart().getFullPrice().doubleValue() + ")")
                        .reader(getReader())
                        .cart(getCart())
                        .parent(this)
                        .repository(getRepository())
                        .build();

                getCart().getAll().forEach((k, v) -> cart.getBars().put(String.valueOf(k.getId()), k.getTitle() + " (amount " + v + ")"));
                cart.getBars().put("back", "return to previous menu");
                cart.getBars().put("exit", "exit");

                cart.init();
                break;
            case "exit":
                break;
        }
    }
}
