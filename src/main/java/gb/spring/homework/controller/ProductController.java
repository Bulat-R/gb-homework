package gb.spring.homework.controller;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.Product;
import gb.spring.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) List<String> sortBy) {
        return service.findAll(sortBy);
    }

    @PostMapping
    public void save(@Valid @RequestBody Product product) {
        service.save(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{productId}/order")
    public List<OrderDto> getOrders(@PathVariable Long productId) {
        return service.findOrders(productId);
    }
}
