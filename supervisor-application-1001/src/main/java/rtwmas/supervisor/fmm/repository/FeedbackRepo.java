package rtwmas.supervisor.fmm.repository;

import java.util.ArrayList;
import java.util.List;

import rtwmas.supervisor.fmm.model.EmpFeedback;
import rtwmas.supervisor.fmm.model.UserFeedback;

public class FeedbackRepo
{
	private List<EmpFeedback> empFeedbackList = new ArrayList<>();
	private List<UserFeedback> userFeedbackList = new ArrayList<>();

	public void addNewEmployeeFeedback(EmpFeedback obj)	{	empFeedbackList.add(obj);	}
	public void addNewUserFeedback(UserFeedback obj)	{	userFeedbackList.add(obj);	}
	
	public List<EmpFeedback> getEmpFeedbackList() 		{	return empFeedbackList;		}
	public List<UserFeedback> getUserFeedbackList() 	{	return userFeedbackList;	}
	
	public void setEmpFeedbackList(List<EmpFeedback> empFeedbackList) 		{	this.empFeedbackList = empFeedbackList;		}
	public void setUserFeedbackList(List<UserFeedback> userFeedbackList)	{	this.userFeedbackList = userFeedbackList;	}
}
