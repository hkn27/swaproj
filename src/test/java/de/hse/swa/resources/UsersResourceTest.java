package de.hse.swa.resources;


import static org.junit.Assert.assertEquals;

import de.hse.swa.PrepareTests;
import de.hse.swa.dao.*;
import de.hse.swa.model.*;
import org.junit.*;

import java.io.IOException;
import java.util.List;

public class UsersResourceTest {
	private static UserDao u;
	private static Tcompany company = null;
	private static CompanyDao c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		c = CompanyDao.getInstance();
		company = new Tcompany();
		company.setName("385i");
		c.saveCompany(company);
		u = UserDao.getInstance();
			
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
	public void testGetUsers(){
		UsersResource usersResource = new UsersResource();
		int count1 = usersResource.getUsers().size();
		Tuser user = new Tuser();
		user.setTcompany(company);
		UserDao.getInstance().saveUser(user);
		int count2 = usersResource.getUsers().size();
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testNewUser() throws IOException {
		List<Tuser> list = u.getUsers();
		int count1 = list.size();
		UsersResource usersResource = new UsersResource();
		Tuser user = new Tuser();
		user.setTcompany(company);
		list = usersResource.newUser(user,null);
		int count2 = list.size(); 
		assertEquals(count2 - count1,1);
	}
	
	@Test
	public void testGetCount() throws IOException{
		UsersResource usersResource = new UsersResource();
		int count1 = Integer.parseInt(usersResource.getCount());
		Tuser user = new Tuser();
		user.setTcompany(company);
		u.saveUser(user);
		int count2 = Integer.parseInt(usersResource.getCount());
		assertEquals(count2 - count1,1);
	}

}
