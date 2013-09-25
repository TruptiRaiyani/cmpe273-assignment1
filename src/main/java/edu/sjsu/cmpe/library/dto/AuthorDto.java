package edu.sjsu.cmpe.library.dto;


//import java.util.List;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Authors;


@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {

	public AuthorDto() {
		// TODO Auto-generated constructor stub
	}
private ArrayList<Authors> authors;

public AuthorDto(ArrayList<Authors> authors) {
	super();
	this.authors = authors;
   }

public ArrayList<Authors> getAuthors() {
	return authors;
   }
public void setAuthors(ArrayList<Authors> authors) {
	this.authors = authors;
    }



/*public Authors getAuthors1() {
	return authors1;
   }
public void setAuthors(Authors authors1) {
	this.authors1 = authors1;
    }*/
}
