package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.model.Tservicecontract;

public class ServicecontractDao {
	
	private static EntityManager em;
	private static ServicecontractDao singleton;
	
	private ServicecontractDao(){
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static ServicecontractDao getInstance() {
		if (singleton == null) {
			singleton = new ServicecontractDao();
		}
		return singleton;
	}
	public Tservicecontract getSC(int id) {
		return em.find(Tservicecontract.class, id);
	}
	
	public List<Tservicecontract> getSCs() {
		Query q = em.createQuery("select c from Tservicecontract c");
		List<Tservicecontract> scs = q.getResultList();
		return scs;
	}
	public List<Tservicecontract> getUserByCompanyId(int id){
		Query q = em.createQuery("select u from Tservicecontract where u.company_idcompany = :cid");
		q.setParameter("cid", CompanyDao.getInstance().getCompany(id));
		List<Tservicecontract> servicecontracts = q.getResultList();
		return servicecontracts;
	}
	public List<Tservicecontract> getUserByUserId(int id){
		Query q = em.createQuery("select u from Tservicecontract where u.user_iduser = :uid");
		q.setParameter("uid", UserDao.getInstance().getUser(id));
		List<Tservicecontract> servicecontracts = q.getResultList();
		return servicecontracts;
	}
	
	public void saveSC(Tservicecontract sc) {
		em.getTransaction().begin();
		em.persist(sc);
		em.getTransaction().commit();
	}

	public void deleteSC(Integer id) {

		Tservicecontract cm = em.find(Tservicecontract.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
	
	public void updateSC(int id) {
		em.getTransaction().begin();
		em.refresh(ServicecontractDao.getInstance().getSC(id));
	}
}
