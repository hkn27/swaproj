package de.hse.swa.resources;



import static org.junit.Assert.assertEquals;

import de.hse.swa.dao.*;
import de.hse.swa.model.*;
import org.junit.*;

import java.io.IOException;
import java.util.List;

public class CompaniesResourceTest {
	private static CompanyDao c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c = CompanyDao.getInstance();
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
	public void testGetCompanies(){
		CompaniesResource companiesResource = new CompaniesResource();
		int count1 = companiesResource.getCompanies().size();
		CompanyDao.getInstance().saveCompany(new Tcompany());
		int count2 = companiesResource.getCompanies().size();
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testNewCompany() throws IOException {
		List<Tcompany> list = c.getCompanies();
		int count1 = list.size();
		CompaniesResource companiesResource = new CompaniesResource();
		list = companiesResource.newCompany(new Tcompany(),null);
		int count2 = list.size(); 
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testGetCount() throws IOException{
		CompaniesResource companiesResource = new CompaniesResource();
		int count1 = Integer.parseInt(companiesResource.getCount());
		c.saveCompany(new Tcompany());
		int count2 = Integer.parseInt(companiesResource.getCount());
		assertEquals(count2 - count1,1);
	}

}
