package gb.spring.homework.dao;

import gb.spring.homework.model.Product;
import gb.spring.homework.model.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductDao {

    private final SessionFactory factory;

    public List<Product> findAll(List<String> sortBy) {
        List<Product> result = null;
        String orderBy = "";
        if (sortBy != null && sortBy.size() > 0) {
            StringJoiner sj = new StringJoiner(", ");
            for (String str : sortBy) {
                sj.add(str);
            }
            orderBy = " ORDER BY " + sj;
        }
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("SELECT p FROM Product p" + orderBy, Product.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public Product findById(Long id) {
        Product result = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            result = session.get(Product.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public void save(Product product) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            if (product.getId() != null) {
                Product old = session.find(Product.class, product.getId());
                old.setName(product.getName());
                old.setCost(product.getCost());
                old.setCompany(product.getCompany());
            } else {
                session.persist(product);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    public int deleteById(Long id) {
        int result = 0;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("DELETE FROM Product p WHERE p.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public List<Product> findByCriteria(ProductFilter filter, List<String> sortBy) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        CriteriaQuery<Product> criteriaQuery = query.select(root)
                .where(getFullPredicate(builder, root, filter))
                .orderBy(getOrderList(builder, root, sortBy));
        Session session = factory.openSession();
        List<Product> result = null;
        try {
            session.beginTransaction();
            result = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return result;
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


}
