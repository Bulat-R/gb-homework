package gb.spring.homework.dao;

import gb.spring.homework.model.Company;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyDao {

    private final SessionFactory factory;

    public List<Company> findAll() {
        List<Company> result = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
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

    public Company findById(Long id) {
        Company company = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            company = session.get(Company.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return company;
    }

    public Company findByName(String name) {
        Company company = null;
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            company = session.createQuery("SELECT a FROM Company a WHERE a.name=:name", Company.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return company;
    }

    public void save(Company company) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.persist(company);
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
            result = session.createQuery("DELETE FROM Company a WHERE a.id=:id")
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
}
