package com.epaylater;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.epaylater.model.DatabaseHandler;
import com.epaylater.model.Profile;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
    @GET
    @Path("{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam ("phoneNo") String phoneNo) {
    	DatabaseHandler dbhandle = new DatabaseHandler();
		Profile p = dbhandle.retrieve(phoneNo);
		if(p == null){
			return new Profile();
		}else{
			return p;
		}
    }
    
    @POST
    @Path("activity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Profile createActivityParams(Profile p){
    	System.out.println(p.getFirstName());
    	System.out.println(p.getLastName());
    	return p;
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testIt(){
    	return "Working !!";
    }
    
    @PUT
    @Path("{phoneNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Profile updateProfile(Profile p){
    	DatabaseHandler dbhandle = new DatabaseHandler();
		dbhandle.performUpdate(p);
		return p;
    }
    
    
    
}
