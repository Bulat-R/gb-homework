package gb.spring.controller;

import gb.spring.entity.Product;
import gb.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", repository.getAll());
        return "products";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        repository.remove(id);
        return "redirect:/product";
    }

    @GetMapping("/edit")
    public String getEditForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("product", repository.getById(id));
        }
        return "edit";
    }

    @PostMapping
    public String edit(Product product) {
        repository.save(product);
        return "redirect:/product";
    }
}
