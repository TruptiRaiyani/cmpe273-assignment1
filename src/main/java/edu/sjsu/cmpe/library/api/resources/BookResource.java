package edu.sjsu.cmpe.library.api.resources;


//import java.net.URI;
//import java.util.UUID;


import java.util.ArrayList;
import java.util.HashMap;

//import java.util.List;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





import com.yammer.dropwizard.validation.Validated;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	 public BookResource() {
			// do nothing
		    }
	private static HashMap<Long,Book> hbook = new HashMap<Long,Book>();
		private static long isbnkey , authorkey, reviewkey;
   
	
 
   
  // Create Book ///////// 
 
    @POST
    @Timed(name = "create-book")
    public Response CreateBook(@Validated Book b) {
    	//b.getIsbn()
    	
    	b.setIsbn(generateISBNKEY());
    	ArrayList<Authors> a = new ArrayList<Authors>();
    	ArrayList<Authors> tempauthordata = new ArrayList<Authors>();
    	a = b.getauthor();
    	for(int i=0;i<a.size();i++)
    	{
    		
    		Authors tempdata = new Authors();
    		tempdata =b.getauthor().get(i);
    		tempdata.SetID(generateAUTHORKEY());
            tempauthordata.add(tempdata);
    		
    	}
    	b.setauthor(tempauthordata);
    	hbook.put(b.getIsbn(), b);
    	
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("view-book", "/books/" + b.getIsbn(), "GET"));
    	links.addLink(new LinkDto("update-book","/books/" + b.getIsbn(), "PUT"));
    	links.addLink(new LinkDto("delete-book",	"/books/" + b.getIsbn(), "DELETE"));
    	links.addLink(new LinkDto("create-review", "/books/" + b.getIsbn() + "/reviews", "POST"));

    	return Response.ok(links).build();
        }
    
//  Get Book By ISBN number
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") long isbn) {
   

	Book book = new Book();
	book = hbook.get(isbn);
	
	BookDto bookResponse = new BookDto(book);	
	//AuthorDto a = new AuthorDto();
	for(int i=0 ;i<book.getauthor().size();i++)
	{
		bookResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+book.getauthor().get(i).getID(),"GET"));
		//a.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+book.getauthor().get(i).getID(),"GET"));
		
	}
	//bookResponse.setAuthorDto(a);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book",	"/books/" + book.getIsbn() , "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),"DELETE"));
	bookResponse.addLink(new LinkDto("create-review",	"/books/" + book.getIsbn() + "/reviews" , "POST"));
	if(book.getreviews() != null)
	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn()+"/reviews","GET"));

	return bookResponse;
    }
    
   
    //Delete Book
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBookByID(@PathParam("isbn") long isbn) {
    	
    	hbook.remove(isbn);
    	
    	
	LinksDto links = new LinksDto();
	links.addLink(new LinkDto("create-book", "/books", "POST"));

	return Response.ok(links).build();
    }
    
  //Update Book record
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBookByID(@PathParam("isbn") long isbn,@QueryParam("status") String status,@QueryParam("title") String title,@QueryParam("publication-date") String publicationdate,@QueryParam("language") String language,@QueryParam("num-pages") long numpages) {
    	
    	Book b = new Book();
    	b = hbook.get(isbn);
       if(status != null)
        	b.setstatus(status);
       if(title != null)
    	   b.setTitle(title);
       if(publicationdate != null)
    	   b.setPublicationDate(publicationdate);
       if(language != null)
    	   b.setlanguage(language);
       if(numpages != 0)
    	   b.setnum_page(numpages);
        
    		hbook.put(isbn, b);
	LinksDto links = new LinksDto();
	links.addLink(new LinkDto("view-book", "/books/" + isbn, "GET"));
	links.addLink(new LinkDto("update-book", "/books/" + isbn, "PUT"));
	links.addLink(new LinkDto("delete-book", "/books/"+ isbn, "DELETE"));
	links.addLink(new LinkDto("create-review", "/books/" + isbn+ "/reviews", "POST"));
if(b.getreviews() != null)
	links.addLink(new LinkDto("view-all-reviews", "/books/" + isbn+ "/reviews", "GET"));

	return Response.ok(links).build();
    }
    //Create BookReview
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-bookreview")
    public Response CreateBookReview(Reviews newreviewdata,@PathParam("isbn") long isbn) {
    	
    	
        Book b =new Book();
        b = hbook.get(isbn);
       
    	ArrayList<Reviews> oldreviewdata = new ArrayList<Reviews>();
    	oldreviewdata = b.getreviews();
    	
    	    newreviewdata.setreviewid(generateREVIEWKEY());
    	    if(oldreviewdata == null)
    	    {
    	    	 oldreviewdata = new ArrayList<Reviews>();
    	    }
    		oldreviewdata.add(newreviewdata);
    		
    	
    	b.setreviews(oldreviewdata);
    	hbook.put(b.getIsbn(), b);
       
    
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("view-review", "/books/" +isbn+ "/reviews/" + newreviewdata.getreviewid() , "GET"));

    	return Response.ok(links).build();

    }
    
// View Book Review By ReviewID
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-book")
    public ReviewsDto getReviewByID(@PathParam("isbn") long isbn, @PathParam("id") long id) {
   
	Book book = new Book();
	book = hbook.get(isbn);
    	
    	  	ArrayList<Reviews> reviewdata = new ArrayList<Reviews>();
    	reviewdata = book.getreviews();
    	Reviews matchReviews = new Reviews();
    	ArrayList<Reviews> reviewdatafinal = new ArrayList<Reviews>();
    	for(int i=0;i<reviewdata.size();i++)
    	{
    		   		
    		Reviews tempdata = new Reviews();
    		tempdata =reviewdata.get(i);
    		if(tempdata.getreviewid() == id)
    		{
    		
    		matchReviews.setrating(tempdata.getrating());
    		matchReviews.setreviewid(tempdata.getreviewid());
    		matchReviews.setcomment(tempdata.getcomment());
    		reviewdatafinal.add(matchReviews);
            break;
    		}
    	}
	ReviewsDto reviewResponse = new ReviewsDto(reviewdatafinal);	
	reviewResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn()+ "/reviews/" + matchReviews.getreviewid() ,"GET"));
		
	return reviewResponse;
    }
    
    
    //view reviews of book
    
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-book")
    public ReviewsDto getReviews(@PathParam("isbn") long isbn) {
   
    	ArrayList<Reviews> bookreviews = new ArrayList<Reviews>();
    	 Book b =new Book();
         b = hbook.get(isbn);
         bookreviews = b.getreviews();
         ReviewsDto authorResponse = new ReviewsDto(bookreviews);
         return authorResponse;
    }
    
    // Get author by AuthorID
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    public AuthorDto getAuthorByAuthorID(@PathParam("isbn") long isbn, @PathParam("id") long id) { 	   
    
 	 	Book book = new Book();
 		book = hbook.get(isbn);
 		ArrayList<Authors> authordata = new ArrayList<Authors>();
 		authordata = book.getauthor();
    	Authors matchauthor = new Authors();
    	ArrayList<Authors> authordatafinal = new ArrayList<Authors>();
    	for(int i=0;i<authordata.size();i++)
    	{
    		   		
    		Authors tempdata = new Authors();
    		tempdata =authordata.get(i);
    		if(tempdata.getID() == id)
    		{
    		
    			matchauthor.SetID(tempdata.getID());
    			matchauthor.SetName(tempdata.GetName());
    			authordatafinal.add(matchauthor);
            break;
    		}
    	}
 		AuthorDto authorResponse = new AuthorDto(authordatafinal); 	
 		authorResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+ "/authors/" + matchauthor.getID() ,"GET"));
 		return authorResponse;
 	    }
    
    
    // Get author by BookID
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-author")
    public AuthorDto getAuthorByID(@PathParam("isbn") long isbn) { 	   
    
 	 	Book book = new Book();
 		book = hbook.get(isbn);
 		AuthorDto authorResponse = new AuthorDto(book.getauthor()); 		
 		return authorResponse;
 	    }
    
    
    
    //Generate keys
    private final Long generateISBNKEY()
    {
    	return Long.valueOf(++isbnkey);
    }
    private final Long generateAUTHORKEY()
    {
    	return Long.valueOf(++authorkey);
    }
    private final Long generateREVIEWKEY()
    {
    	return Long.valueOf(++reviewkey);
    }

}

