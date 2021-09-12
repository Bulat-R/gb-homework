package gb.spring.homework.filter;

import gb.spring.homework.model.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@RequiredArgsConstructor
public class OrderFilter {
    @NotNull
    private final String fieldName;
    @NotNull
    private final FilterType type;
    @NotNull
    private final FilterCondition condition;
    @NotNull
    private final String criteria;

    public static Specification<Order> getSpec(List<OrderFilter> filterList) {
        return (root, query, cb) -> {
            AtomicReference<Predicate> predicate = new AtomicReference<>(cb.and());
            if (filterList != null && !filterList.isEmpty()) {
                filterList.forEach(filter -> {
                    switch (filter.type) {
                        case AND:
                            predicate.set(cb.and(predicate.get(), singlePredicate(filter, root, cb)));
                            break;
                        case OR:
                            predicate.set(cb.or(predicate.get(), singlePredicate(filter, root, cb)));
                            break;
                        case NOT:
                            predicate.set(cb.not(singlePredicate(filter, root, cb)));
                            break;
                    }
                });
            }
            return predicate.get();
        };
    }

    private static Predicate singlePredicate(OrderFilter filter, Root<Order> root, CriteriaBuilder cb) {
        switch (filter.condition) {
            case GT:
                return cb.greaterThan(root.get(filter.getFieldName()), filter.getCriteria());
            case LT:
                return cb.lessThan(root.get(filter.getFieldName()), filter.getCriteria());
            case LIKE:
                return cb.like(root.get(filter.getFieldName()), "%" + filter.getCriteria() + "%");
            case EQ:
                return cb.equal(root.get(filter.getFieldName()), filter.getCriteria());
            case NOT_EQ:
                return cb.notEqual(root.get(filter.getFieldName()), filter.getCriteria());
        }
        return null;
    }
}
