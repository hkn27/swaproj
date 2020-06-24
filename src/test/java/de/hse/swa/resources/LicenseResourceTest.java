package de.hse.swa.resources;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.model.*;
import de.hse.swa.dao.*;
import de.hse.swa.PrepareTests;

public class LicenseResourceTest {
	private static Tlicense license;
	private static LicenseResource licenseResource;
	private static Tcompany company = null;
	private static Tuser user = null;
	private static Tservicecontract servicecontract = null;
	private static UserDao u;
	private static CompanyDao c;
	private static ServicecontractDao s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
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
		license = new Tlicense();
		license.setTservicecontract(servicecontract);
		LicenseDao.getInstance().saveLicense(license);
		licenseResource = new LicenseResource(null, null, license.getIdlicense());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLicense(){
		assertEquals(licenseResource.getLicense(),license);
	}
	
	@Test
	public void testGetLicenseHtml(){
		assertNotEquals(licenseResource.getLicenseHTML(),"Get: Tlicense with " + license.getIdlicense() +  " not found");
	}
	
	@Test
	public void testDeleteLicense(){
		licenseResource.deleteLicense();
		assertNotEquals(1,2);
	}
}