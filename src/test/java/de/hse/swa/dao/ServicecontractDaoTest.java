package de.hse.swa.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.PrepareTests;
import de.hse.swa.model.Tcompany;
import de.hse.swa.model.Tuser;
import de.hse.swa.model.Tservicecontract;

public class ServicecontractDaoTest {
	
	
	private static Tcompany company = null;
	private static Tuser user = null;
	private static UserDao u;
	private static CompanyDao c;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		u = UserDao.getInstance();
		c = CompanyDao.getInstance();

		company = new Tcompany();
		company.setName("385i");
		c.saveCompany(company);
		
		user = new Tuser();
		user.setUsername("Hafti");
		user.setPassword("root");
		user.setTcompany(company);
		u.saveUser(user);
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		ServicecontractDao c = ServicecontractDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetTservicecontract() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Tservicecontract sc = new Tservicecontract();
		sc.setTcompany(company);
		sc.setTuser(user);
		s.saveSC(sc);
		Tservicecontract l = s.getSC(sc.getIdservicecontract());
		assertNotNull(l);
	}

	@Test
	public void testGetTservicecontracts() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Tservicecontract sc;
		for (int i=0; i < 10; ++i) {
			sc = new Tservicecontract();
			sc.setTcompany(company);
			sc.setTuser(user);
			s.saveSC(sc);
		}
		List<Tservicecontract> scs = s.getSCs();
		assertTrue(scs.size()>=10);
	}

	@Test
	public void testSaveTservicecontract() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Tservicecontract sc = new Tservicecontract();
		sc.setTcompany(company);
		sc.setTuser(user);
		Tservicecontract scs = s.getSC(sc.getIdservicecontract());
		assertNotNull(scs);
	}

	@Test
	public void testDeleteTservicecontracts() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		List<Tservicecontract> scs = s.getSCs();
		for (Tservicecontract sc: scs) {
			s.deleteSC(sc.getIdservicecontract());
		}
		scs = s.getSCs();
		assertTrue(scs.size()<1);
	}

}
