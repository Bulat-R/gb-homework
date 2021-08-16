package gb.spring.menu;

import gb.spring.entity.Cart;
import gb.spring.repository.ProductRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class Menu {

    protected ProductRepository repository;
    protected Cart cart;
    protected BufferedReader reader;
    protected String title;
    @Builder.Default
    protected Map<String, String> bars = new TreeMap<>();
    protected Menu parent;

    protected abstract void action(String str);

    public void init() {
        System.out.println(title);
        System.out.println("==============================");
        bars.forEach((k, v) -> System.out.printf("%-5s- %s\n", k, v));
        System.out.println("==============================");
        System.out.print("Enter your choice: ");
        while (true) {
            try {
                String userChoice = reader.readLine();
                System.out.println("\n\n");
                if (bars.containsKey(userChoice)) {
                    action(userChoice);
                    break;
                } else {
                    System.out.print("Wrong... Enter your choice: ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
