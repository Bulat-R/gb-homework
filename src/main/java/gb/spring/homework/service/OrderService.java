package gb.spring.homework.service;

import gb.spring.homework.dto.OrderDto;
import gb.spring.homework.exception.EntityNotFoundException;
import gb.spring.homework.filter.OrderFilter;
import gb.spring.homework.model.Order;
import gb.spring.homework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static gb.spring.homework.dto.OrderDto.from;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;

    public List<OrderDto> findAll(List<OrderFilter> filters, Integer pageSize, Integer pageNumber, Boolean askDir, List<String> sortBy) {
        int page;
        int size;
        if (pageSize == null) {
            size = (int) repository.count();
            page = 0;
        } else {
            size = pageSize;
            page = pageNumber == null ? 0 : pageNumber;
        }
        Pageable pageable;
        if (sortBy == null || sortBy.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.unsorted());
        } else {
            Sort.Direction dir = askDir == null || askDir ? Sort.Direction.ASC : Sort.Direction.DESC;
            pageable = PageRequest.of(page, size, dir, sortBy.toArray(new String[0]));
        }
        Specification<Order> spec = (filters == null || filters.isEmpty()) ? null : OrderFilter.getSpec(filters);
        return repository.findAll(spec, pageable).stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    public List<OrderDto> findByCompany(Long companyId) {
        return repository.findByCompanyId(companyId);
    }

    public List<OrderDto> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<OrderDto> findByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<OrderDto> findAll(List<String> sortBy) {
        Sort sort = sortBy == null || sortBy.isEmpty() ? Sort.unsorted() : Sort.by(sortBy.toArray(new String[0]));
        return repository.findAll(sort).stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        return from(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public OrderDto save(Order order) {
        return from(repository.save(order));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
