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
//import de.hse.swa.PrepareTests;

public class CompanyResourceTest {
	private static Tcompany company;
	private static CompanyResource companyResource;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		company = new Tcompany();
		CompanyDao.getInstance().saveCompany(company);
		companyResource = new CompanyResource(null, null, company.getIdcompany());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCompany(){
		assertEquals(companyResource.getCompany(),company);
	}
	
	@Test
	public void testGetCompanyHtml(){
		assertNotEquals(companyResource.getCompanyHTML(),"Get: Tcompany with " + company.getIdcompany() +  " not found");
	}
	
	@Test
	public void testDeleteCompany(){
		companyResource.deleteCompany();
		assertNotEquals(1,2);
	}
}