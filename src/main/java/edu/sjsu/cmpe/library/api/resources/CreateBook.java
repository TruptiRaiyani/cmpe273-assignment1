package edu.sjsu.cmpe.library.api.resources;

//import java.io.IOException;
//import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.library.domain.Book;

//import org.apache.commons.httpclient.methods.*;

@Path("v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateBook {

	public CreateBook() {
		// TODO Auto-generated constructor stub
	}
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book create(@FormParam("language") String language,@FormParam("status") String status)
    {
    	final Book b = new Book();
    	b.setlanguage(language);
    	b.setstatus(status);
    	return b;
    }
    }
   /* @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPostHello(){
        //System.out.println(mb);
    	GetMethod method = new GetMethod("chrome-extension://cokgbflfommojglbmbpenpphppikmonn/index.html#response");
        
          byte[] responseBody = null;
		try {
			responseBody = method.getResponseBody();
		
          //BookDto bookResponse = new BookDto();
    	//GetInputData g = new GetInputData();
         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return responseBody.toString();
        //return Response.status(200).build();
    }*/


