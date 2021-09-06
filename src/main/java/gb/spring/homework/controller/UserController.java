package gb.spring.homework.controller;

import gb.spring.homework.model.Order;
import gb.spring.homework.model.User;
import gb.spring.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public void save(@Valid @RequestBody User user) {
        service.save(user);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrders(@PathVariable Long userId) {
        return service.findOrders(userId);
    }
}
