package gb.spring.homework.controller;

import gb.spring.homework.model.Product;
import gb.spring.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> getSorted(@RequestParam(required = false) String... sortBy) {
        return service.findAll(sortBy);
    }

    @PostMapping
    public Product save(@Valid @RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/filter")
    public List<Product> getByCriteria(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) BigDecimal minCost,
                                       @RequestParam(required = false) BigDecimal maxCost,
                                       @RequestParam(required = false) String companyName,
                                       @RequestParam(required = false) String... sortBy) {
        return service.getByCriteria(name, minCost, maxCost, companyName, sortBy);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete")
    public void removeCompany(@NotNull @RequestParam Long id) {
        service.deleteById(id);
    }


}
