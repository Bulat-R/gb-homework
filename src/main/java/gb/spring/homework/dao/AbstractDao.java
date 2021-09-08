package gb.spring.homework.dao;

import gb.spring.homework.dao.wrapper.SessionWrapper;
import gb.spring.homework.exception.IdNotFoundException;
import gb.spring.homework.model.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
public abstract class AbstractDao<T extends AbstractEntity> {

    protected SessionWrapper wrapper;
    private final Class<T> type;

    protected AbstractDao(Class<T> type) {
        this.type = type;
    }

    @Autowired
    public final void setWrapper(SessionWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public List<T> findAll() {
        String queryString = "SELECT t FROM " + type.getSimpleName() + " t";
        log.debug("Query: {}", queryString);
        return wrapper.wrap(session -> session.createQuery(queryString).getResultList());
    }

    public List<T> findAll(List<String> sortBy) {
        log.debug("Find all with sort by: {}", sortBy);
        String orderBy = "";
        if (sortBy != null && sortBy.size() > 0) {
            StringJoiner sj = new StringJoiner(", ");
            for (String str : sortBy) {
                sj.add(str);
            }
            orderBy = " ORDER BY " + sj;
        }
        String query = "SELECT p FROM Product p" + orderBy;
        log.debug("Query: {}", query);
        return wrapper.wrap(session -> session.createQuery(query, type)
                .getResultList());
    }

    public T findById(Long id) {
        return wrapper.wrap(session -> {
            T entity = session.find(type, id);
            log.debug("Find by id = {}: {}", id, entity);
            if (entity == null) {
                throw new IdNotFoundException();
            }
            return Collections.singletonList(entity);
        }).get(0);
    }

    public List<T> findByName(String name) {
        String queryString = "SELECT t FROM " + type.getSimpleName() + " t WHERE t.name=:name";
        log.debug("Query: {}", queryString);
        return wrapper.wrap(session -> session.createQuery(queryString)
                .setParameter("name", name)
                .getResultList());
    }

    public void save(T entity) {
        wrapper.wrap(session -> {
            if (entity.getId() == null) {
                session.persist(entity);
                log.debug("Save entity: {}", entity);
            } else {
                session.find(type, entity.getId());
                session.merge(entity);
                log.debug("Merge entity: {}", entity);
            }
            return null;
        });
    }

    public void deleteById(Long id) {
        wrapper.wrap(session -> {
            T entity = session.find(type, id);
            if (entity == null) {
                throw new IdNotFoundException();
            }
            session.delete(entity);
            log.debug("Delete by id = {}: {}", id, entity);
            return null;
        });
    }
}
