package rtwmas.supervisor.fmm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp_feedback")
public class EmpFeedback
{	
	@Id
	private String feedback_id;
	private String toilet_id;
	private Date date_time;
	private boolean parameter1;
	private boolean parameter2;
	private boolean parameter3;
	private boolean parameter4;
	private boolean parameter5;
	private boolean parameter6;
	private boolean parameter7;
	private boolean parameter8;
	private String emp_id;
	
	public EmpFeedback()	{	}
	
	public EmpFeedback(String feedback_id, String toilet_id, String emp_id,
			boolean parameter1, boolean parameter2, boolean parameter3, boolean parameter4,
			 boolean parameter5,boolean parameter6, boolean parameter7, boolean parameter8, Date date_time)
	{
		this.feedback_id = feedback_id;
		this.toilet_id = toilet_id;
		this.emp_id = emp_id;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.parameter3 = parameter3;
		this.parameter4 = parameter4;
		this.parameter5 = parameter5;
		this.parameter6 = parameter6;
		this.parameter7 = parameter7;
		this.parameter8 = parameter8;
		this.date_time = date_time;
	}


	public String getFeedback_id()	{	return feedback_id;	}
	public String getToilet_id() 	{	return toilet_id;	}
	public String getEmp_id() 		{	return emp_id;		}
	public boolean isParameter1() 	{	return parameter1;	}
	public boolean isParameter2() 	{	return parameter2;	}
	public boolean isParameter3() 	{	return parameter3;	}
	public boolean isParameter4() 	{	return parameter4;	}
	public boolean isParameter5() 	{	return parameter5;	}
	public boolean isParameter6() 	{	return parameter6;	}
	public boolean isParameter7() 	{	return parameter7;	}
	public boolean isParameter8() 	{	return parameter8;	}
	public Date getDate_time() 		{	return date_time;	}
	
	public EmpFeedback setFeedback_id(String feedback_id) 	{	this.feedback_id = feedback_id;		return this;	}
	public EmpFeedback setToilet_id(String toilet_id) 		{	this.toilet_id = toilet_id;			return this;	}
	public EmpFeedback setEmp_id(String emp_id) 			{	this.emp_id = emp_id;				return this;	}
	public EmpFeedback setParameter1(boolean parameter1)	{	this.parameter1 = parameter1;		return this;	}
	public EmpFeedback setParameter2(boolean parameter2) 	{	this.parameter2 = parameter2;		return this;	}
	public EmpFeedback setParameter3(boolean parameter3) 	{	this.parameter3 = parameter3;		return this;	}
	public EmpFeedback setParameter4(boolean parameter4) 	{	this.parameter4 = parameter4;		return this;	}
	public EmpFeedback setParameter5(boolean parameter5)	{	this.parameter5 = parameter5;		return this;	}
	public EmpFeedback setParameter6(boolean parameter6) 	{	this.parameter6 = parameter6;		return this;	}
	public EmpFeedback setParameter7(boolean parameter7) 	{	this.parameter7 = parameter7;		return this;	}
	public EmpFeedback setParameter8(boolean parameter8)	{	this.parameter8 = parameter8;		return this;	}
	public EmpFeedback setDate_time(Date date_time) 		{	this.date_time = date_time;			return this;	}


	
}