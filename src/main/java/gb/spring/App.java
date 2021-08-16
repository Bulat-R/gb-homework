package gb.spring;

import gb.spring.config.AppConfig;
import gb.spring.entity.Cart;
import gb.spring.menu.MainMenu;
import gb.spring.menu.Menu;
import gb.spring.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Menu mainMenu = MainMenu.builder()
                .title("MAIN MENU")
                .reader(context.getBean(BufferedReader.class))
                .cart(context.getBean(Cart.class))
                .repository(context.getBean(ProductRepository.class))
                .build();

        mainMenu.getBars().put("1", "show products");
        mainMenu.getBars().put("2", "show cart");
        mainMenu.getBars().put("exit", "exit");

        mainMenu.init();
    }
}
