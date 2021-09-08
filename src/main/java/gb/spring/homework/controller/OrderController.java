package gb.spring.homework.controller;

import gb.spring.homework.model.Order;
import gb.spring.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<Order> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable @Min(1L) Long id) {
        return service.getById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid Order order) {
        service.save(order);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam @Min(1L) Long id) {
        service.deleteById(id);
    }
}
