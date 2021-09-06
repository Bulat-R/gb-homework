package gb.spring.homework.dao.wrapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SessionWrapper {

    private final SessionFactory factory;

    public <T> List<T> wrap(SessionWorker<T> worker) {
        List<T> result = null;
        Session session = factory.openSession();
        log.debug("Open session");
        try {
            session.beginTransaction();
            log.debug("Begin transaction");
            result = worker.doWork(session);
            session.getTransaction().commit();
            log.debug("Commit transaction with result: {}", result);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.error("DB session error", e);
        } finally {
            session.close();
            log.debug("Close session");
        }
        return result;
    }
}
