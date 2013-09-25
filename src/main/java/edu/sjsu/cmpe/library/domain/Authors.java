package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authors  {

	public long id;
	@NotEmpty 
	@JsonProperty("name")	public String name;
	/*public Authors(long id, String name)
	{
	//	super();
		this.id =id;
		this.name =name;
	}*/
	public long getID()
	{
		return this.id;
	}
	public void SetID(long id)
	{
		this.id = id;
	}
	public String GetName()
	{
		return this.name;
	}
	public void SetName(@JsonProperty("name") String name)
	{
		this.name = name;

}
}