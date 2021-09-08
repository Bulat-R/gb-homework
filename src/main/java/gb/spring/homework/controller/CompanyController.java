package gb.spring.homework.controller;

import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import gb.spring.homework.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@Validated
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public List<Company> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable @Min(1L) Long id) {
        return service.getById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid Company company) {
        service.save(company);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam @Min(1L) Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{companyId}/products")
    public List<Product> getProducts(@PathVariable Long companyId) {
        return service.findProducts(companyId);
    }
}
