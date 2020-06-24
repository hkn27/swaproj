package de.hse.swa.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.hse.swa.dao.*;
import de.hse.swa.model.*;


public class CompanyResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public CompanyResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Tcompany getCompany() {
		Tcompany company = CompanyDao.getInstance().getCompany(id);
		if(company==null)
			throw new RuntimeException("Get: Tcompany with " + id +  " not found");
		return company;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Tcompany getCompanyHTML() {
		Tcompany company = CompanyDao.getInstance().getCompany(id);
		if(company==null)
			throw new RuntimeException("Get: Tcompany with " + id +  " not found");
		return company;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCompany(JAXBElement<Tcompany> company) {
		Tcompany c = company.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteCompany() {
		CompanyDao.getInstance().deleteCompany(id);
	}

	private Response putAndGetResponse(Tcompany company) {
		Response res;
		CompanyDao.getInstance().saveCompany(company);;
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 