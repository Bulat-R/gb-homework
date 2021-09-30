package gb.spring.homework.controller;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import gb.spring.homework.service.CompanyService;
import gb.spring.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@Validated
public class CompanyController {

    private final CompanyService companyService;
    private final ProductService productService;

    @GetMapping
    public List<Company> getAll(@RequestParam(required = false) List<String> sortBy) {
        return companyService.findAll(sortBy);
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid Company company) {
        companyService.save(company);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Long id) {
        companyService.deleteById(id);
    }

    @GetMapping("/{companyId}/product")
    public List<Product> getProducts(@PathVariable Long companyId) {
        return companyService.findProducts(companyId);
    }

    @GetMapping("/{companyId}/order")
    public List<OrderDto> getOrders(@PathVariable Long companyId) {
        return companyService.findOrders(companyId);
    }
}
