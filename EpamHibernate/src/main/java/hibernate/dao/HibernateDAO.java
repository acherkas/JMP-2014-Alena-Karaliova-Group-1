package hibernate.dao;

import org.hibernate.HibernateException;

/**
 * Created by Aliaksei_Cherkas.
 */

public class HibernateDAO<T> extends SessionDAO{

	public Object retrieve(Class<?> clazz, long id) throws Exception {
		try {
			begin();
			Object employee = (Object) getSession().get(clazz, id);
			commit();
			return employee;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get information of employee ", e);
		}
	}
	
	public T save(T t) throws Exception {
		try {
			begin();
			getSession().saveOrUpdate(t);
			commit();
			return t;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not save information to DB", e);
		}
	}


	public void delete(T t) throws Exception {
		try {
			begin();
			getSession().delete(t);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete employee from DB", e);
		}
	}
}
