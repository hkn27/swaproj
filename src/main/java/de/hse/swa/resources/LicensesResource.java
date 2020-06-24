package de.hse.swa.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import de.hse.swa.dao.*;
import de.hse.swa.model.*;

// Will map the resource to the URL licenses
@Path("/licenses")
public class LicensesResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of licenses to the license in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Tlicense> getLicensesBrowser() {
    return LicenseDao.getInstance().getLicenses();
  }

  // Return the list of licenses in JSON format
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Tlicense> getLicenses() {
	   return LicenseDao.getInstance().getLicenses();
  }

  // returns the number of licenses
  // Use http://localhost:8080/Jodel/rest/licenses/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = LicenseDao.getInstance().getLicenses().size();
    return String.valueOf(count);
  }
  

  // This is the workhorse
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Tlicense> newLicense(Tlicense license,
		  @Context HttpServletResponse servletResponse) throws IOException {
    LicenseDao.getInstance().saveLicense(license);
    return LicenseDao.getInstance().getLicenses();
  }

} 