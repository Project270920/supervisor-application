package rtwmas.supervisor.fmm.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rtwmas.supervisor.fmm.model.Data;
import rtwmas.supervisor.fmm.model.EmpFeedback;
import rtwmas.supervisor.fmm.model.ParticipantFeedbacks;
import rtwmas.supervisor.fmm.model.UserFeedback;
import rtwmas.supervisor.fmm.repository.DataRepository;

@RequestMapping("rtwmas/supervisor/fmm/other")
@Controller
public class FeedbackEvaluationForOther
{
	@Autowired
	private DataRepository dataRepo;
	
	private Map<String, int[]> toiletlist = new HashMap<>();
	private Map<String, int[]> emplist = new HashMap<>();
	
	
	@PostMapping("addData")
	public ResponseEntity<String> receiveFeedbacksfromSupervisor(@RequestBody Data data)
	{
		if(data != null)
		{
			if(dataRepo.addData(data.getSupervisor_id(), data))
				return new ResponseEntity<String> ("ID",HttpStatus.OK);
			else
				return new ResponseEntity<String> ("ERROR",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String> ("EMPTY",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("evaluate")
	public ResponseEntity<String> evaluateFeedbacksfromSupervisor()
	{
//		GeneralReport report = new GeneralReport();		//	Transaction of the blockchain
		
		for(Entry<String, Data> obj : dataRepo.getMap().entrySet())
		{
			List<EmpFeedback> empFeedbackList = obj.getValue().getFeedbacks().getEmpFeedback();
			List<UserFeedback> userFeedbackList = obj.getValue().getFeedbacks().getUserFeedback();
			
			for(EmpFeedback obj1 : empFeedbackList)
				if(!emplist.containsKey(obj1.getToilet_id()))
					emplist.put(obj1.getToilet_id(), new int[8]);
			
			for(UserFeedback obj1 : userFeedbackList)
				if(!toiletlist.containsKey(obj1.getToilet_id()))
					toiletlist.put(obj1.getToilet_id(), new int[8]);
			
			System.out.println("Evaluating Data Proposed by Supervisor : " + obj.getKey());
			
//			System.out.println("USER FEEDBACKS");
			Map<String, int[]> userMap = calculateEachParameterUSER(userFeedbackList, userFeedbackList.size());
//			System.out.println("EMPLOYEE FEEDBACKS");
			Map<String, int[]> empMap = calculateEachParameterEMP(empFeedbackList, empFeedbackList.size());

			Data data = calculateFeedback(userMap, userFeedbackList,  empMap, empFeedbackList);
			System.out.println("REPORT DATE : " + data);
			
			if(obj.getValue().getNoOfCleanToilets() == data.getNoOfCleanToilets())
			{
				System.out.println("Total Number of Clean Toilets (Calculated) : "+ data.getNoOfCleanToilets());
				System.out.println("Total Number of Clean Toilets (Proposed)   : "+ obj.getValue().getNoOfCleanToilets()  + " .......... [STATUS] : TRUE");
			}
			else
			{
				System.out.println("Total Number of Clean Toilets (Calculated) : " + data.getNoOfCleanToilets());
				System.out.println("Total Number of Clean Toilets (Proposed)   : " + obj.getValue().getNoOfCleanToilets()  + " .......... [STATUS] : FALSE");
				System.out.println("Total Number of Invalid Entries            : " + (obj.getValue().getNoOfCleanToilets() - data.getNoOfCleanToilets()));
			}
			
			if(obj.getValue().getNoOfUncleanToilets() == data.getNoOfUncleanToilets())
			{
				System.out.println("Total Number of Un-Clean Toilets (Calculated) : "+ data.getNoOfUncleanToilets());
				System.out.println("Total Number of Un-Clean Toilets (Proposed)   : "+ obj.getValue().getNoOfUncleanToilets()  + " .......... [STATUS] : TRUE");
			}
			else
			{
				System.out.println("Total Number of Un-Clean Toilets (Calculated) : "+ data.getNoOfUncleanToilets());
				System.out.println("Total Number of Un-Clean Toilets (Proposed)   : "+ obj.getValue().getNoOfUncleanToilets()  + " .......... [STATUS] : FALSE");
				System.out.println("Total Number of Invalid Entries               : " + (obj.getValue().getNoOfUncleanToilets() - data.getNoOfUncleanToilets()));
			}
			
			/*
			 * 
			 * Supervisor_id
			 * total number of Toilets
			 * total number of Invalid entries
			 * 
			 */
			
		}
		
		return new ResponseEntity<String> ("DONE",HttpStatus.OK);
	}
/************************************************************************************************************************************/
	
//	Parsing Users Feedbacks and Normalizing them (min - max Normalization Technique)
	public Map<String, int[]> calculateEachParameterUSER(List<UserFeedback> list, int size)
	{	
		System.out.println("Inside 'calculate_Each_Parameter_USER' Function");
		for(Map.Entry<String, int[]> obj : toiletlist.entrySet())  	
		{
			System.out.print("[ KEY : " + obj.getKey() + ", VALUES : ");
				for(int i=0;i<obj.getValue().length; ++i)
					System.out.print(obj.getValue()[i] + ", ");
				System.out.println("]");
		}
		
//		for each Toilet
		for(Map.Entry<String, int[]> obj2 : toiletlist.entrySet())
		{  		
			int count = 0;
			int[] userRating = new int[8];

//			from every Feedback	
			for(UserFeedback obj3 : list)
			{	
//				having same toilet ID				
				if(obj2.getKey().equals(obj3.getToilet_id()))
				{
					++count;
//					calculate Each Parameter from USER for each Toilet
					if(obj3.isParameter1())
						userRating[0] = ++userRating[0];
					if(obj3.isParameter2())
						userRating[1] = ++userRating[1];
					if(obj3.isParameter3())
						userRating[0] = ++userRating[2];
					if(obj3.isParameter4())
						userRating[3] = ++userRating[3];
					if(obj3.isParameter5())
						userRating[4] = ++userRating[4];
					if(obj3.isParameter6())
						userRating[5] = ++userRating[5];
					if(obj3.isParameter7())
						userRating[6] = ++userRating[6];
					if(obj3.isParameter8())
						userRating[7] = ++userRating[7];					
				}
			}

			toiletlist.put(obj2.getKey(), normalizeEachParameter(userRating, count));

			for(Map.Entry<String, int[]> obj : toiletlist.entrySet())  	
			{
				System.out.print("[ KEY : " + obj.getKey() + ", VALUES : ");
					for(int i=0;i<obj.getValue().length; ++i)
						System.out.print(obj.getValue()[i] + ", ");
					System.out.println("]");
			}
		}	

		for(Map.Entry<String, int[]> obj : toiletlist.entrySet())
		{
			System.out.print("[ KEY : " + obj.getKey() + ", VALUES : ");
			System.out.print("[");
			for(int i=0;i<obj.getValue().length;++i)
				System.out.print(obj.getValue()[i] + ", ");
			System.out.println("]]");
		}
		
		System.out.println("End of 'calculate_Each_Parameter_USER' Function");
		return toiletlist;
	}
	
	
	//	Parsing Employee Feedbacks and Normalizing them (Min - Max Normalization Technique)
	public Map<String, int[]> calculateEachParameterEMP(List<EmpFeedback> list, int size)
	{		
		System.out.println("Inside 'calculate_Each_Parameter_EMP' Function");
//		for each Toilet
		for(Map.Entry<String, int[]> obj2 : emplist.entrySet())
		{  		
			int count = 0;
			int[] empRating = new int[8];
//			from every Feedback	
			for(EmpFeedback obj3 : list)
			{	
//				having same toilet ID				
				if(obj2.getKey().equals(obj3.getToilet_id()))
				{
					++count;
//					calculate Each Parameter from Employee for each Toilet
					if(obj3.isParameter1())
						empRating[0] = ++empRating[0];
					if(obj3.isParameter2())
						empRating[1] = ++empRating[1];
					if(obj3.isParameter3())
						empRating[0] = ++empRating[2];
					if(obj3.isParameter4())
						empRating[3] = ++empRating[3];
					if(obj3.isParameter5())
						empRating[4] = ++empRating[4];
					if(obj3.isParameter6())
						empRating[5] = ++empRating[5];
					if(obj3.isParameter7())
						empRating[6] = ++empRating[6];
					if(obj3.isParameter8())
						empRating[7] = ++empRating[7];
				}
			}

			emplist.put(obj2.getKey(), normalizeEachParameter(empRating, count));
		}


		for(Map.Entry<String, int[]> obj : emplist.entrySet())
		{
			System.out.print("[ KEY : " + obj.getKey() + ", VALUES : ");
			System.out.print("[");
			for(int i=0;i<obj.getValue().length;++i)
				System.out.print(obj.getValue()[i] + ", ");
			System.out.println("]]");
		}

		System.out.println("Ended 'calculate_Each_Parameter_USER' Function");
		return emplist;
	}
	
	//	 Min - Max Normalization Technique
	public int[] normalizeEachParameter(int[] rating, int size)
	{
		System.out.println("Inside Normalization Function");
		for(int i=0;i<rating.length;++i)
			System.out.print(rating[i] + ", ");
		System.out.println();
		
		int min = (size * 10)/100;
		int max = (size * 90)/100;

		System.out.println("Normalization Started");
		for(int i=0;i<rating.length; ++i)
		{
			System.out.print(rating[i] + ", ");
			rating[i] = ((rating[i] - min) * (10 - 0) + 0) / (max - min);
		}
		System.out.println();
		System.out.println("Normalization Ended");
		
		return rating;
	}
	
/***********************************************************************************************************************************************************************/
	
//	Evaluating Employee Feedbacks with 
	public Data calculateFeedback(Map<String, int[]> userList, List<UserFeedback> userFeedbacks, Map<String, int[]> empList, List<EmpFeedback> empFeedbacks)
	{

		Data data = new Data();
		Map<String, int[]> toilets = new HashMap<>();
		
		for(EmpFeedback obj1 : empFeedbacks)
			if(!toilets.containsKey(obj1.getToilet_id()))
				toilets.put(obj1.getToilet_id(), new int[8]);		
		
//		for each Toilet
		for(Map.Entry<String, int[]> feedbacks : toilets.entrySet())
		{	
//			from every Feedback of EMP
			for(EmpFeedback emp : empFeedbacks)
			{	
//				from every Feedback of USER
				for(UserFeedback user : userFeedbacks)
				
//				having same toilet ID				
				if(feedbacks.getKey().equals(emp.getToilet_id()) && feedbacks.getKey().equals(user.getToilet_id()))
				{
					
					int[] userRatings = userList.get(feedbacks.getKey());
					int[] empRatings = empList.get(feedbacks.getKey());
					int[] values = new int[8];
					
					// 	STEP-2 : calculating difference of user and emp feedback
					for(int i=0;i<values.length; ++i)		
						values[i] = userRatings[i] - empRatings[i];
			
					toilets.put(feedbacks.getKey(), values);
				}
			}
		}
		
		
		System.out.println("\nCONCLUDED RATINGS");
		
		for(Map.Entry<String, int[]> obj : toilets.entrySet())
		{
			System.out.print("[ KEY : " + obj.getKey() + ", VALUES : ");
			System.out.print("[");
			for(int i=0;i<obj.getValue().length;++i)
				System.out.print(obj.getValue()[i] + ", ");
			System.out.println("]]");
		}
		
	
		int clean = 0;
		int unclean = 0;
		
		// STEP-3 : Calculating which Parameter if accepted and rejected			
		for(Map.Entry<String, int[]> obj : toilets.entrySet())
		{
			int accept = 0;
			int reject = 0;
			int[] values = obj.getValue();
			
			for(int i=0;i<values.length; ++i)
			{
				if(-3<values[i] && values[i]<3 )
				{
					++accept;
					values[i] = 1;
				}
				else
				{
					 ++reject;
					values[i] = 0;
				}
			}
			
			System.out.println("[ ACCEPTED : " + accept + ", REJECTED : " + reject + " ]");


//			STEP-4 : Categorizing the item						
			if(values[0]==1 && (values[1]==1 || values[2]==1 || values[3]==1))	{
				System.out.println("[ Status of Toilet : CLEAN   ]");
				++clean;
			}
			else	{
				System.out.println("[ Status of Toilet : UN-CLEAN   ]");
				++unclean;
			}
		}

		System.out.println("[ CLEAN Toilet    : " + clean   +" ]");
		System.out.println("[ UN-CLEAN Toilet : " + unclean +" ]");
	

//				Parameter-1 : Total number of Toilets
			data.setTotalToilets(toilets.size());
//				Parameter-2 : Size of User Feedback
			data.setTotalUserFeedbacks(userFeedbacks.size());
//				Parameter-3 : Size of Employee Feedback
			data.setTotalEmpFeedbacks(empFeedbacks.size());
//				Parameter-4 : Date & Time of Evaluation
			data.setDate(new Date());
//				Parameter-5 : Total number of clean toilets
			data.setNoOfCleanToilets(clean);
//				Parameter-6 : Total number of unclean toilets
			data.setNoOfUncleanToilets(unclean);	
//				Parameter-7 : User Feedback & Employee (care taker) Feedbacks 			
			data.setFeedbacks(new ParticipantFeedbacks(userFeedbacks, empFeedbacks));	//getUserFeedback().set
			
			return data;
	}
}
