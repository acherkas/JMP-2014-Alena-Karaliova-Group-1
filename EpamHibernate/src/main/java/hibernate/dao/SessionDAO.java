package hibernate.dao;

/**
 * Created by Aliaksei_Cherkas.
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionDAO {

	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	@SuppressWarnings("deprecation")
	private static final SessionFactory sessionFactory =
			new AnnotationConfiguration().configure().buildSessionFactory();

	protected SessionDAO() {
	}

	public static Session getSession() {
		Session session = (Session) SessionDAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			SessionDAO.session.set(session);
		}
		return session;
		
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	public static void close() {
		getSession().close();
		SessionDAO.session.set(null);
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			e.printStackTrace();
        }
		try {
			getSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		SessionDAO.session.set(null);
	}

}
