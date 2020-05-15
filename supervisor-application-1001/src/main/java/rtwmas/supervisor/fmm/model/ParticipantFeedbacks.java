package rtwmas.supervisor.fmm.model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantFeedbacks
{
	private List<UserFeedback> userFeedback = new ArrayList<UserFeedback>();
	private List<EmpFeedback> empFeedback = new ArrayList<EmpFeedback>();
	
	public ParticipantFeedbacks()	{	}	
	
	public ParticipantFeedbacks(List<UserFeedback> userFeedback, List<EmpFeedback> empFeedback)
	{
		this.userFeedback = userFeedback;
		this.empFeedback = empFeedback;
	}
	public void setUserFeedback(List<UserFeedback> feedbackList)	{	this.userFeedback = feedbackList;	}
	public void setEmpFeedback(List<EmpFeedback> empFeedback) 		{	this.empFeedback = empFeedback;		}
	
	public List<UserFeedback> getUserFeedback() 					{	return userFeedback;	}
	public List<EmpFeedback> getEmpFeedback() 						{	return empFeedback;		}

}
