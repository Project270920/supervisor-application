package rtwmas.supervisor.tmm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class Token
{
	@Id
	private String token_id;
	private String toilet_id;
	private float parameter1;
	private float parameter2;
	private float parameter3;
	private float parameter4;
	private Date date_time;

	public Token()	{	}
	

	public Token(String token_id, String toilet_id, float parameter1, float parameter2,
			float parameter3,	float parameter4, Date date_time)
	{
		this.token_id = token_id;
		this.toilet_id = toilet_id;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.parameter3 = parameter3;
		this.parameter4 = parameter4;
		this.date_time = date_time;
	}


	public String getToken_id() 	{	return token_id;	}
	public String getToilet_id() 	{	return toilet_id;	}
	public float isParameter1() 	{	return parameter1;	}
	public float isParameter2() 	{	return parameter2;	}
	public float isParameter3() 	{	return parameter3;	}
	public float isParameter4() 	{	return parameter4;	}
	public Date getDate_time() 		{	return date_time;	}


	public Token setToken_id(String token_id) 		{	this.token_id = token_id;		return this;	}
	public Token setToilet_id(String toilet_id) 	{	this.toilet_id = toilet_id;		return this;	}
	public Token setParameter1(float parameter1)	{	this.parameter1 = parameter1;	return this;	}
	public Token setParameter2(float parameter2) 	{	this.parameter2 = parameter2;	return this;	}
	public Token setParameter3(float parameter3) 	{	this.parameter3 = parameter3;	return this;	}
	public Token setParameter4(float parameter4) 	{	this.parameter4 = parameter4;	return this;	}
	public Token setDate_time(Date date_time) 		{	this.date_time = date_time;		return this;	}

	@Override
	public String toString() {
		return "Token [token_id=" + token_id + ", toilet_id=" + toilet_id + ", parameter1=" + parameter1
				+ ", parameter2=" + parameter2 + ", parameter3=" + parameter3 + ", parameter4=" + parameter4
				+ ", date_time=" + date_time + "]";
	}
}
