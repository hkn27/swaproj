package de.hse.swa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoManager {
	private static DaoManager dm;
	private static EntityManager em;
	
	private static final String PERSISTENCE_UNIT_NAME = "swa";
	private static EntityManagerFactory factory;

	private DaoManager() {
	    em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	public static DaoManager getInstance() {
		if (dm == null) {
			dm = new DaoManager();
		}
		return dm;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}

}