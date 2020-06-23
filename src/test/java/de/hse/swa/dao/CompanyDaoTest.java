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

public class CompanyDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
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
		CompanyDao c = CompanyDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetCompany() {
		CompanyDao c = CompanyDao.getInstance();
		Tcompany company = new Tcompany();
		company.setName("385i");
		c.saveCompany(company);
		Tcompany companies = c.getCompany(company.getIdcompany());
		assertNotNull(companies);
	}

	@Test
	public void testGetCompanies() {
		CompanyDao c = CompanyDao.getInstance();
		Tcompany company;
		for (long i=0; i < 10; ++i) {
			company = new Tcompany();
			company.setName("385i "+i);
			c.saveCompany(company) ;
		}
		List<Tcompany> companies = c.getCompanies();
		assertTrue(companies.size()>=10);
	}

	@Test
	public void testSaveCompany() {
		CompanyDao companyDao = CompanyDao.getInstance();
		Tcompany company = new Tcompany();
		company.setName("Alpha");
		companyDao.saveCompany(company) ;
		Tcompany companies = companyDao.getCompany(company.getIdcompany());
		assertNotNull(companies);
	}

	@Test
	public void testDeleteCompanies() {
		CompanyDao companyDao = CompanyDao.getInstance();
		List<Tcompany> companies = companyDao.getCompanies();
		for (Tcompany company: companies) {
			companyDao.deleteCompany(company.getIdcompany());
		}
		companies = companyDao.getCompanies();
		assertTrue(companies.size()<1);
	}

}
