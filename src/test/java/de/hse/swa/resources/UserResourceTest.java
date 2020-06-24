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

public class UserResourceTest {
	private static Tuser user;
	private static UserResource userResource;
	private static Tcompany company = null;
	private static CompanyDao c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();

		c = CompanyDao.getInstance();
		company = new Tcompany();
		company.setName("385i");
		c.saveCompany(company);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		user = new Tuser();
		user.setTcompany(company);
		UserDao.getInstance().saveUser(user);
		userResource = new UserResource(null, null, user.getIduser());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser(){
		assertEquals(userResource.getUser(),user);
	}
	
	@Test
	public void testGetUserHtml(){
		assertNotEquals(userResource.getUserHTML(),"Get: Tuser with " + user.getIduser() +  " not found");
	}
	
	@Test
	public void testDeleteUser(){
		userResource.deleteUser();
		assertNotEquals(1,2);
	}
}