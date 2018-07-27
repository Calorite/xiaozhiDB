package com.yidi.jerseyREST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void getIt(@Context HttpServletRequest request,@Context HttpServletResponse response) {
    	try {
            //请求转发
    		//request.setAttribute("questionlist", DBupdate.getsolutionlist());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e+"request dispatcher fail");
        }

    }

    @POST
    @Path("/getparametes")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllparametes(@FormParam("description") String test) {

    	return test;
    }

    @POST
    @Path("/solution")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public String InsertSolution(@FormParam("parameters") String parameters,@FormParam("solution") String test) throws IOException {
    	parameters=parameters.replace("]","");
    	parameters=parameters.replace("[","");
		System.out.println(parameters);
    	System.out.println(test);
    	return "false";
    }

}
