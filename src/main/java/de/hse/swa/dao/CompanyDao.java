package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.model.Tcompany;


public class CompanyDao {
	
	private static EntityManager em;
	private static CompanyDao singleton;
	
	private CompanyDao(){
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static CompanyDao getInstance() {
		if (singleton == null) {
			singleton = new CompanyDao();
		}
		return singleton;
	}
	
	public Tcompany getCompany(Integer id) {
		return em.find(Tcompany.class, id);
	}

	public List<Tcompany> getCompanies() {
		Query q = em.createQuery("select c from Tcompany c");
		List<Tcompany> companies = q.getResultList();
		return companies;
	}
	
	public void saveCompany(Tcompany company) {
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
	}
	
	public void updateCompany(int id) {
		em.getTransaction().begin();
		em.refresh(CompanyDao.getInstance().getCompany(id));
		em.getTransaction().commit();
	}

	public void deleteCompany(Integer id) {

		Tcompany cm = em.find(Tcompany.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
