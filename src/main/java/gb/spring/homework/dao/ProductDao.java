package gb.spring.homework.dao;

import gb.spring.homework.dao.filter.ProductFilter;
import gb.spring.homework.dao.wrapper.SessionWrapper;
import gb.spring.homework.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductDao extends AbstractDao<Product> {

    private final SessionWrapper wrapper;
    private final SessionFactory factory;

    public ProductDao(SessionWrapper wrapper, SessionFactory factory) {
        super(Product.class);
        this.wrapper = wrapper;
        this.factory = factory;
    }

    public List<Product> findByCriteria(ProductFilter filter, List<String> sortBy) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        CriteriaQuery<Product> criteriaQuery = query.select(root)
                .where(getFullPredicate(builder, root, filter))
                .orderBy(getOrderList(builder, root, sortBy));
        return wrapper.wrap(session -> session.createQuery(criteriaQuery).getResultList());
    }

    private List<Order> getOrderList(CriteriaBuilder builder, Root<Product> root, List<String> sortBy) {
        if (sortBy == null || sortBy.size() == 0) {
            return Collections.emptyList();
        }
        return sortBy.stream().map(s -> builder.asc(root.get(s))).collect(Collectors.toList());
    }

    private Predicate getFullPredicate(CriteriaBuilder builder, Root<Product> root, ProductFilter filter) {
        ProductFilter currentFilter = filter;
        Predicate predicate = builder.and();
        while (currentFilter != null) {
            switch (filter.getType()) {
                case OR:
                    predicate = builder.or(predicate, getSinglePredicate(builder, root, currentFilter));
                    break;
                case AND:
                default:
                    predicate = builder.and(predicate, getSinglePredicate(builder, root, currentFilter));
                    break;
            }
            currentFilter = currentFilter.getNext();
        }
        return predicate;
    }

    private Predicate getSinglePredicate(CriteriaBuilder builder, Root<Product> root, ProductFilter filter) {
        switch (filter.getOperation()) {
            case EQ:
                return builder.equal(root.get(filter.getFieldName()), filter.getValue());
            case NOT_EQ:
                return builder.notEqual(root.get(filter.getFieldName()), filter.getValue());
            case GT:
                return builder.greaterThan(root.get(filter.getFieldName()), filter.getValue());
            case LT:
                return builder.lessThan(root.get(filter.getFieldName()), filter.getValue());
            case LIKE:
                return builder.like(root.get(filter.getFieldName()), filter.getValue());
            case NULL:
                return builder.isNull(root.get(filter.getFieldName()));
            case NOT_NULL:
                return builder.isNotNull(root.get(filter.getFieldName()));
        }
        return null;
    }


    public List<gb.spring.homework.model.Order> findOrders(Long productId) {
        return wrapper.wrap(session -> session.find(Product.class, productId).getOrders());
    }
}
