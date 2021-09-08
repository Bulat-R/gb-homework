package gb.spring.homework.dao.wrapper;

import org.hibernate.Session;

import java.util.List;

public interface SessionWorker<T> {
    List<T> doWork(Session session);
}
