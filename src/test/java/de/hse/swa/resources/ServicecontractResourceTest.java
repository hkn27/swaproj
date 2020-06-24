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

public class ServicecontractResourceTest {
	private static ServicecontractResource servicecontractResource;
	private static Tservicecontract sc = null;
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
		sc = new Tservicecontract();
		sc.setTcompany(company);
		sc.setTuser(user);
		ServicecontractDao.getInstance().saveSC(sc);
		servicecontractResource = new ServicecontractResource(null, null, sc.getIdservicecontract());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetServicecontract(){
		assertEquals(servicecontractResource.getServicecontract(),sc);
	}
	
	@Test
	public void testGetServicecontractHtml(){
		assertNotEquals(servicecontractResource.getServicecontractHTML(),"Get: Tservicecontract with " + sc.getIdservicecontract() +  " not found");
	}
	
	@Test
	public void testDeleteServicecontract(){
		servicecontractResource.deleteServicecontract();
		assertNotEquals(1,2);
	}
}