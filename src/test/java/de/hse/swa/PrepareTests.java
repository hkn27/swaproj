
package de.hse.swa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import de.hse.swa.dao.DaoManager;

public class PrepareTests {

	@Test
	public void clearDB() {
		initDatabase();
	}
	
	
	public static void initDatabase() {
		DaoManager dm = DaoManager.getInstance();
		EntityManager em = dm.getEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();


		try {
			em.createNativeQuery("DELETE FROM TLICENSE").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			em.createNativeQuery("DELETE FROM TSERVICECONTRACT").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			em.createNativeQuery("DELETE FROM TUSER").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			em.createNativeQuery("DELETE FROM TCOMPANY").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		et.commit();
		
		et = em.getTransaction();
		et.begin();
 		em.createNativeQuery("INSERT INTO TCOMPANY (IDCOMPANY, NAME, DEPARTMENT, ADDRESS) VALUES (1, 'Firma1', 'b', 'hallostrasse 1')").executeUpdate();		
		em.createNativeQuery("INSERT INTO TUSER (IDUSER, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, ADMIN, COMPANYID) VALUES (1, 'MAX', 'Mueller', 'MM', 'abc@abc.de', '123', 0, 1)").executeUpdate();
		em.createNativeQuery("INSERT INTO TSERVICECONTRACT (IDSERVICECONTRACT, STARTDATE, ENDDATE, COMPANYID, USERID) VALUES (1, '10.10.2020', '10.11.2020', 1, 1)").executeUpdate();
		em.createNativeQuery("INSERT INTO TLICENSE (IDLICENSE, EXPIRATIONDATE, LICENSEKEY, LICENSECOUNT, IP1, IP2, IP3, IP4, TSCID) VALUES (1, '20.1.2020', 'key', 6, '123.111.111.111', '121.111.111.111', '1.3.4.5', '1.1.1.1', 1)").executeUpdate();

		et.commit();
	}

}
