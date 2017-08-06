package spittr.db.hibernate5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spittr.db.SpitterRepository;
import spittr.domain.Spitter;

import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


@Repository
public class HibernateSpitterRepository implements SpitterRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;         //<co id="co_InjectSessionFactory"/>
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
    }

    public long count() {
        return findAll().size();
    }

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);  //<co id="co_UseCurrentSession"/>
        return new Spitter((Long) id,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getEmail(),
                spitter.isUpdateByEmail());
    }

    public Spitter findOne(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public Spitter findByUsername(String username) {

        Session session = currentSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Spitter> criteria = builder.createQuery(Spitter.class);
        Root<Spitter> spitterRootRoot = criteria.from(Spitter.class);
        criteria.select(spitterRootRoot);

        //**Adding where clause**
        criteria.where(builder.equal(spitterRootRoot.get("username"), username));

        return session.createQuery(criteria).getResultList().get(0);

//        return (Spitter) currentSession()
//                .createCriteria(Spitter.class)
//                .add(Restrictions.eq("username", username))
//                .list().get(0);
    }

    public List<Spitter> findAll() {

        Session session = currentSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Spitter> criteria = builder.createQuery(Spitter.class);
        Root<Spitter> spitterRootRoot = criteria.from(Spitter.class);
        criteria.select(spitterRootRoot);

        return session.createQuery(criteria).getResultList();

//        return (List<Spitter>) currentSession()
//                .createCriteria(Spitter.class).list();


    }

}
