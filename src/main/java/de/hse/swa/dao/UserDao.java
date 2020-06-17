package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.model.Tuser;

public class UserDao {
	
	private static EntityManager em;
	private static UserDao singleton;
	
	private UserDao(){
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static UserDao getInstance() {
		if (singleton == null) {
			singleton = new UserDao();
		}
		return singleton;
	}
	
	public Tuser getUser(int id) {
		return em.find(Tuser.class, id);
	}
	
	public List<Tuser> getUsers() {
		Query q = em.createQuery("select c from Tuser c");
		List<Tuser> users = q.getResultList();
		return users;
	}
	
	public List<Tuser> getUserByCompanyId(int id){
		Query q = em.createQuery("select u from Tuser where u.company_idcompany = :cid");
		q.setParameter("cid", CompanyDao.getInstance().getCompany(id));
		List<Tuser> users = q.getResultList();
		return users;
	}
	
	public void saveUser(Tuser user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void deleteUser(Integer id) {

		Tuser cm = em.find(Tuser.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
	public void updateUser(int id) {
		em.getTransaction().begin();
		em.refresh(UserDao.getInstance().getUser(id));
		
	}
}
