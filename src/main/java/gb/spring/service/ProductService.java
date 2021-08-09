package gb.spring.service;

import gb.spring.entity.Product;

public class ProductService {

    public String get(int amount) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Products</title></head><body>");
        for (int i = 0; i < amount; i++) {
            sb.append("<p>");
            sb.append(Product.builder()
                    .id(i)
                    .title("some product")
                    .cost(i + 0.5)
                    .build().toString());
            sb.append("</p>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
