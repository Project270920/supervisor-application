package rtwmas.supervisor.fmm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rtwmas.supervisor.fmm.model.EmpFeedback;
import rtwmas.supervisor.fmm.model.UserFeedback;
import rtwmas.supervisor.fmm.repository.EmpFeedbackRepository;
import rtwmas.supervisor.fmm.repository.UserFeedbackRepository;

@Service
public class FeedbackService
{
	@Autowired
	private EmpFeedbackRepository empRepo;
	@Autowired
	private UserFeedbackRepository userRepo;
	
	public boolean addNewEmployeeFeedback(EmpFeedback obj)
	{
		if(obj != null)
		{
			System.out.println("Entity Saved...");
			empRepo.save(obj);
			return true;
		}
		else
		{
			System.out.println("Entity Not Saved...");
			return false;
		}
	}
	
	public List<EmpFeedback> getEmployeeFeedback()
	{
		List<EmpFeedback> list = empRepo.findAll();
		if(list != null)
		{
			System.out.println("Found all...");
			return list;
		}
		else
		{
			System.out.println("Not Found all...");
			return new ArrayList<>();
		}
	}
	
	public boolean addNewUserFeedback(UserFeedback obj)
	{
		if(obj != null)
		{
			System.out.println("Entity Saved...");
			userRepo.save(obj);
			return true;
		}
		else
		{
			System.out.println("Entity Not Saved...");
			return false;
		}
	}
	
	public List<UserFeedback> getUserFeedback()
	{
		List<UserFeedback> list = userRepo.findAll();
		if(list != null)
		{
			System.out.println("Found all...");
			return list;
		}
		else
		{
			System.out.println("Not Found all...");
			return new ArrayList<>();
		}
	}
}
