package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;





import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.yammer.dropwizard.validation.ValidationMethod;


public class Book {
	
    private long isbn;
    @NotEmpty 
    @JsonProperty private String title;
    @NotEmpty 
    @JsonProperty("publication-date")  private String publication_date;
    private String language;
    @JsonProperty("num-pages") private long num_pages;
    
    private String status;
    @NotEmpty
    @JsonProperty("authors") private ArrayList<Authors> authors;
   
    @JsonProperty("reviews") private ArrayList<Reviews> reviews;
    // add more fields here

   
    public long getIsbn() {
	return isbn;
    }

   
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

   /* @ValidationMethod(message="enter proper status(available,checked-out,in-queue,lost)")
    public boolean isNotstatus()
    {
    	return ("available".equals(status) ||"checked-out".equals(status) || "in-queue".equals(status) || "lost".equals(status));
    }*/
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle( @JsonProperty("title") String title) {
	this.title = title;
    }
    public String getPublicationDate()
    {
    	return publication_date;
    }
    public void setPublicationDate(  @JsonProperty("publication-date") String publication_date)
    {
    	this.publication_date = publication_date;
    }
    
    public String getlanguage()
    {
    	return language;
    }
    public void setlanguage(String language)
    {
    	this.language = language;
    }
    
    public long getnum_page()
    {
    	return num_pages;
    }
    public void setnum_page(@JsonProperty("num-pages") long num_pages)
    {
    	this.num_pages = num_pages;
    }
    
    public String getstatus()
    {
    	return status;
    }
    public void setstatus(String status)
    {
    	this.status = status;
    }
    
    public ArrayList<Authors> getauthor()
    {
    	return authors;
    }
    public void setauthor(@JsonProperty("authors") ArrayList<Authors> authors)
    {
    	this.authors = authors;

    }
    public ArrayList<Reviews> getreviews()
    {
    	return reviews;
    }
    public void setreviews(@JsonProperty("reviews") ArrayList<Reviews> reviews)
    {
    	this.reviews = reviews;

    }
}