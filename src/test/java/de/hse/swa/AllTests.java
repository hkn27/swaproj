package de.hse.swa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hse.swa.dao.*;
import de.hse.swa.resources.*;

@RunWith(Suite.class)
@SuiteClasses({DaoManagerTest.class,
			   CompanyDaoTest.class,
			   UserDaoTest.class,
			   ServicecontractDaoTest.class,
			   LicenseDaoTest.class,
			   CompanyResourceTest.class,
			   CompaniesResourceTest.class,
			   UsersResourceTest.class,
			   UserResourceTest.class,
			   ServicecontractResourceTest.class,
			   ServicecontractsResourceTest.class,
			   LicenseResourceTest.class,
			   LicensesResourceTest.class})
			
public class AllTests {

}
