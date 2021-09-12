package gb.spring.homework.controller;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.filter.OrderFilter;
import gb.spring.homework.model.Order;
import gb.spring.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderDto> getAll(@RequestParam(required = false) List<String> sortBy) {
        return service.findAll(sortBy);
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public OrderDto save(@RequestBody @Valid Order order) {
        return service.save(order);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Long id) {
        service.deleteById(id);
    }

    @PostMapping("/filter")
    public List<OrderDto> getAllByFilterAndPageable(@RequestBody(required = false) List<OrderFilter> filters,
                                                    @RequestBody(required = false) @Min(1) Integer pageSIze,
                                                    @RequestBody(required = false) @Min(0) Integer pageNumber,
                                                    @RequestBody(required = false) Boolean ascDir,
                                                    @RequestBody(required = false) List<String> sortBy) {
        return service.findAll(filters, pageSIze, pageNumber, ascDir, sortBy);
    }
}
