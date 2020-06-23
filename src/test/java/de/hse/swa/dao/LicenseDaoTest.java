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
import de.hse.swa.model.Tlicense;
import de.hse.swa.model.Tcompany;
import de.hse.swa.model.Tuser;
import de.hse.swa.model.Tservicecontract;

public class LicenseDaoTest {
	

	private static Tcompany company = null;
	private static Tuser user = null;
	private static Tservicecontract servicecontract = null;
	private static LicenseDao l;
	private static UserDao u;
	private static CompanyDao c;
	private static ServicecontractDao s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//PrepareTests.initDatabase();
		l = LicenseDao.getInstance();
		u = UserDao.getInstance();
		c = CompanyDao.getInstance();
		s = ServicecontractDao.getInstance();

		company = new Tcompany();
		company.setName("385i");
		c.saveCompany(company);
		
		user = new Tuser();
		user.setUsername("Hafti");
		user.setPassword("root");
		user.setTcompany(company);
		u.saveUser(user);
		
		servicecontract = new Tservicecontract();
		servicecontract.setTcompany(company);
		servicecontract.setTuser(user);
		s.saveSC(servicecontract);
		
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
		LicenseDao ld = LicenseDao.getInstance();
		assertNotNull(ld);
	}

	@Test
	public void testGetTlicense() {
		LicenseDao ld = LicenseDao.getInstance();
		Tlicense license = new Tlicense();
		license.setIp1("192.168.2.1");
		license.setTservicecontract(servicecontract);
		ld.saveLicense(license);
		Tlicense license2 = ld.getLicense(license.getIdlicense());
		assertNotNull(license2);
	}

	@Test
	public void testGetTlicenses() {
		LicenseDao ld = LicenseDao.getInstance();
		Tlicense license;
		for (int i=0; i < 10; ++i) {
			license = new Tlicense();
			license.setTservicecontract(servicecontract);
			license.setLicensecount(i);
			ld.saveLicense(license);
		}
		List<Tlicense> licenses = ld.getLicenses();
		assertTrue(licenses.size()>=10);
	}

	@Test
	public void testSaveTlicense() {
		LicenseDao licenseDao = LicenseDao.getInstance();
		Tlicense license = new Tlicense();
		license.setTservicecontract(servicecontract);
		license.setIp1("132.111.111.111");
		licenseDao.saveLicense(license);
		Tlicense licenses = licenseDao.getLicense(license.getIdlicense());
		assertNotNull(licenses);
	}

	@Test
	public void testDeleteTlicenses() {
		LicenseDao ld = LicenseDao.getInstance();
		List<Tlicense> licenses = l.getLicenses();
		for (Tlicense license: licenses) {
			ld.deleteLicense(license.getIdlicense());
		}
		licenses = ld.getLicenses();
		assertTrue(licenses.size()<1);
	}

}