package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.yammer.dropwizard.validation.ValidationMethod;

public class Reviews {
	private long id;
	@NotEmpty 
	@JsonProperty("rating")	private int rating;
	@NotEmpty 
	@JsonProperty("comment") private String comment;
	   public long getreviewid() {
			return id;
		    }

		   
		    public void setreviewid(long id) {
			this.id = id;
		    }
		    
		    public int getrating() {
		    return rating;
		    }

		       
		    public void setrating(@JsonProperty("rating") int rating) {
		    this.rating = rating;
		    }
		    public String getcomment() {
		    return comment;
		    }

		           
		    public void setcomment( @JsonProperty("comment") String comment) {
		    	this.comment=comment;
		    }
		   /* @ValidationMethod(message="enter rating between 1 to 5 numbers")
		    public boolean isNotrating()
		    {
		    	return ("1".equals(rating) ||"2".equals(rating) || "3".equals(rating) || "4".equals(rating)|| "5".equals(rating));
		    }*/
}
