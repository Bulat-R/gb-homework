package gb.spring.homework.controller;

import gb.spring.homework.model.Company;
import gb.spring.homework.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public List<Company> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Company save(@Valid @RequestBody Company company) {
        return service.save(company);
    }

    @DeleteMapping("/delete")
    public void removeCompany(@NotNull @RequestParam Long id) {
        service.deleteById(id);
    }
}
