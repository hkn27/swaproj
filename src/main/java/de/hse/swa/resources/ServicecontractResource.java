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

public class ServicecontractResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public ServicecontractResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Tservicecontract getServicecontract() {
		Tservicecontract sc = ServicecontractDao.getInstance().getSC(id);
		if(sc==null)
			throw new RuntimeException("Get: Tservicecontract with " + id +  " not found");
		return sc;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Tservicecontract getServicecontractHTML() {
		Tservicecontract sc = ServicecontractDao.getInstance().getSC(id);
		if(sc==null)
			throw new RuntimeException("Get: Tservicecontract with " + id +  " not found");
		return sc;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putServicecontract(JAXBElement<Tservicecontract> sc) {
		Tservicecontract c = sc.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteServicecontract() {
		ServicecontractDao.getInstance().deleteSC(id);;
	}

	private Response putAndGetResponse(Tservicecontract sc) {
		Response res;
		ServicecontractDao.getInstance().saveSC(sc);;
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 