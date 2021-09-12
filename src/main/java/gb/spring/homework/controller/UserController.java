package gb.spring.homework.controller;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.model.User;
import gb.spring.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll(@RequestParam(required = false) List<String> sortBy) {
        return userService.findAll(sortBy);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User save(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/{userId}/order")
    public List<OrderDto> getOrders(@PathVariable Long userId) {
        return userService.findOrders(userId);
    }
}
