package rtwmas.supervisor.fmm.model;

import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.Id;

//@Entity
public class Data
{
//	@Id
//	private String data_id;
	private int totalToilets;
	private int noOfCleanToilets;
	private int noOfUncleanToilets;
	private int totalUserFeedbacks;
	private int totalEmpFeedbacks;

	private ParticipantFeedbacks feedbacks;
	private Date date;
	private String supervisor_id;

	
	public int getTotalToilets() 				{	return totalToilets;		}
	public int getNoOfCleanToilets() 			{	return noOfCleanToilets;	}
	public int getNoOfUncleanToilets() 			{	return noOfUncleanToilets;	}
	public int getTotalUserFeedbacks()			{	return totalUserFeedbacks;	}
	public int getTotalEmpFeedbacks() 			{	return totalEmpFeedbacks;	}
	public ParticipantFeedbacks getFeedbacks() 	{	return feedbacks;			}
	public Date getDate() 						{	return date;				}
	public String getSupervisor_id()			{	return this.supervisor_id;	}


	public void setTotalToilets(int totalToilets) 				{	this.totalToilets = totalToilets;				}
	public void setNoOfCleanToilets(int noOfCleanToilets) 		{	this.noOfCleanToilets = noOfCleanToilets;		}
	public void setNoOfUncleanToilets(int noOfUncleanToilets)	{	this.noOfUncleanToilets = noOfUncleanToilets;	}
	public void setTotalUserFeedbacks(int totalUserFeedbacks)	{	this.totalUserFeedbacks = totalUserFeedbacks;	}
	public void setTotalEmpFeedbacks(int totalEmpFeedbacks)		{	this.totalEmpFeedbacks = totalEmpFeedbacks;		}
	public void setFeedbacks(ParticipantFeedbacks feedbacks)	{	this.feedbacks = feedbacks;						}
	public void setDate(Date date) 								{	this.date = date;								}
	public void setSupervisor_id(String supervisor_id)			{	this.supervisor_id = supervisor_id;				}

}
