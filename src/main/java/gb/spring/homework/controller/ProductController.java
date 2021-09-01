package gb.spring.homework.controller;

import gb.spring.homework.model.Product;
import gb.spring.homework.model.ProductFilter;
import gb.spring.homework.service.ProductService;
import gb.spring.homework.validator.ClassFieldName;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) List<@ClassFieldName(targetClass = Product.class) String> sortBy) {
        return service.findAll(sortBy);
    }

    @PostMapping
    public void save(@Valid @RequestBody Product product) {
        service.save(product);
    }

    @PostMapping("/filter")
    public List<Product> getByFilter(@RequestBody @Valid ProductFilter filter,
                                     @RequestParam(required = false) List<@ClassFieldName(targetClass = Product.class) String> sortBy) {
        return service.findByFilter(filter, sortBy);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable @Min(1L) Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete")
    public void removeCompany(@RequestParam @Min(1L) Long id) {
        service.deleteById(id);
    }


}
