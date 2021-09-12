package gb.spring.homework.service;

import gb.spring.homework.exception.EntityNotFoundException;
import gb.spring.homework.model.AbstractEntity;
import gb.spring.homework.repository.AbstractEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> {

    protected final AbstractEntityRepository<T> repository;

    public List<T> findAll(List<String> sortBy) {
        Sort sort = (sortBy == null || sortBy.isEmpty()) ? Sort.unsorted() : Sort.by(sortBy.toArray(new String[0]));
        return repository.findAll(sort);
    }

    public T findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public T findByName(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
