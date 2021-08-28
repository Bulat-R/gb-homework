package gb.spring.homework;

import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeworkApplication {

    @Bean
    public SessionFactory factory() {
        Configuration configuration = new Configuration();
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Product.class);
        return configuration.buildSessionFactory(registry);
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

}
