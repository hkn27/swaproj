package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.model.Tlicense;

public class LicenseDao {
	
	private static EntityManager em;
	private static LicenseDao singleton;
	
	private LicenseDao(){
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static LicenseDao getInstance() {
		if (singleton == null) {
			singleton = new LicenseDao();
		}
		return singleton;
	}

	public Tlicense getLicense(int id) {
		return em.find(Tlicense.class, id);
	}
	
	public List<Tlicense> getLicenses() {
		Query q = em.createQuery("SELECT l FROM Tlicense l");
		List<Tlicense> licenses = q.getResultList();
		return licenses;
	}

	public List<Tlicense> getLicenseBySCid(int id){
		Query q = em.createQuery("select l from Tlicense l where l.servicecontract = :sid");
		q.setParameter("sid", ServicecontractDao.getInstance().getSC(id));
		List<Tlicense> licenses = q.getResultList();
		return licenses;

	}
	
	public void saveLicense(Tlicense license) {
		em.getTransaction().begin();
		em.persist(license);
		em.getTransaction().commit();
	}
	
	public void updateLicense(int id) {
		em.getTransaction().begin();
		em.refresh(LicenseDao.getInstance().getLicense(id));
		em.getTransaction().commit();
	}

	public void deleteLicense(Integer id) {

		Tlicense cm = em.find(Tlicense.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}

}
