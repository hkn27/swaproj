package de.hse.swa.resources;


import static org.junit.Assert.assertEquals;

import de.hse.swa.PrepareTests;
import de.hse.swa.dao.*;
import de.hse.swa.model.*;
import org.junit.*;

import java.io.IOException;
import java.util.List;

public class LicensesResourceTest {
	private static Tcompany company = null;
	private static Tuser user = null;
	private static Tservicecontract servicecontract = null;
	private static LicenseDao l;
	private static UserDao u;
	private static CompanyDao c;
	private static ServicecontractDao s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
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
	public void testGetLicenses(){
		LicensesResource licensesResource = new LicensesResource();
		int count1 = licensesResource.getLicenses().size();
		Tlicense license = new Tlicense();
		license.setTservicecontract(servicecontract);
		LicenseDao.getInstance().saveLicense(license);
		int count2 = licensesResource.getLicenses().size();
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testNewLicense() throws IOException {
		List<Tlicense> list = l.getLicenses();
		int count1 = list.size();
		LicensesResource licensesResource = new LicensesResource();
		Tlicense license = new Tlicense();
		license.setTservicecontract(servicecontract);
		list = licensesResource.newLicense(license,null);
		int count2 = list.size(); 
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testGetCount() throws IOException{
		LicensesResource licensesResource = new LicensesResource();
		int count1 = Integer.parseInt(licensesResource.getCount());
		Tlicense license = new Tlicense();
		license.setTservicecontract(servicecontract);
		l.saveLicense(license);
		int count2 = Integer.parseInt(licensesResource.getCount());
		assertEquals(count2 - count1,1);
	}

}
