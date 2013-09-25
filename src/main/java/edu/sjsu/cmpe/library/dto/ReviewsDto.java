package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



import edu.sjsu.cmpe.library.domain.Reviews;
@JsonPropertyOrder(alphabetic = true)
public class ReviewsDto  extends LinksDto {
	private ArrayList<Reviews> reviews;

    /**
     * @param book
     */
    public ReviewsDto(ArrayList<Reviews> reviews) {
	super();
	this.reviews = reviews;
    }
   
    /**
     * @return the book
     */
    public ArrayList<Reviews> getReviews() {
	return reviews;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setReviews(ArrayList<Reviews> reviews) {
	this.reviews = reviews;
    }

}
