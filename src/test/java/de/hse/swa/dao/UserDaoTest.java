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
import de.hse.swa.model.*;

public class UserDaoTest {
	
	private static Tcompany company = null;
	private static CompanyDao c;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		c = CompanyDao.getInstance();
		
		company = new Tcompany();
		company.setName("Test1");
		c.saveCompany(company);
		
		
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
		UserDao c = UserDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetTuser() {
		UserDao u = UserDao.getInstance();
		Tuser user = new Tuser();
		user.setUsername("Hafti");
		user.setPassword("Test");
		user.setTcompany(company);
		u.saveUser(user);
		Tuser users = u.getUser(user.getIduser());
		assertNotNull(users);
	}

	@Test
	public void testGetTusers() {
		UserDao u = UserDao.getInstance();
		Tuser user;
		for (long i=0; i < 10; ++i) {
			user = new Tuser();
			user.setUsername("Abdi. "+i);
			user.setPassword("Test");
			user.setTcompany(company);
			u.saveUser(user) ;
		}
		List<Tuser> users = u.getUsers();
		assertTrue(users.size()>=10);
	}

	@Test
	public void testSaveTuser() {
		UserDao userDao = UserDao.getInstance();
		Tuser user = new Tuser();
		user.setUsername("Celo");
		user.setPassword("Test");
		user.setTcompany(company);
		userDao.saveUser(user) ;
		Tuser users = userDao.getUser(user.getIduser());
		assertNotNull(users);
	}

	@Test
	public void testDeleteTusers() {
		UserDao userDao = UserDao.getInstance();
		ServicecontractDao s = ServicecontractDao.getInstance();
		LicenseDao l = LicenseDao.getInstance();
		List<Tlicense> licenses = l.getLicenses();
		List<Tuser> users = userDao.getUsers();
		List<Tservicecontract> scs = s.getSCs();
		for (Tlicense license: licenses) {
			l.deleteLicense(license.getIdlicense());
		}
		for (Tservicecontract sc: scs) {
			s.deleteSC(sc.getIdservicecontract());
		}
		for (Tuser user: users) {
			userDao.deleteUser(user.getIduser());
		}
		users = userDao.getUsers();
		assertTrue(users.size()<1);
	}

}
