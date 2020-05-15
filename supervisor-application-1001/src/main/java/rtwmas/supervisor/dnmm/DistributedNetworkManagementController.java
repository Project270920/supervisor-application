package rtwmas.supervisor.dnmm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import rtwmas.supervisor.dnmm.model.Participant;
import rtwmas.supervisor.dnmm.model.Role;

@RestController
@RequestMapping("rtwmas/supervisor/dnmm/initiator")
public class DistributedNetworkManagementController
{
	private final String cms_id = "CMS-1001";
	
	@Autowired
    private Environment env;
	@Autowired
	private RestTemplate restTemplate;
	
	private final String baseURL = "/rtwmas/supervisor/dnmm/initiator"; 
	
	private List<Participant> list = new ArrayList<>();
	
	@PostMapping("phase1")
	public ResponseEntity<String> phase1(@RequestBody String cms_id)
	{
		System.out.println("[PHASE-1] : .......................................[BEGIN]");
		if(cms_id == null)
			return new ResponseEntity<String> ("ERROR", HttpStatus.OK);
		else
		{
			if(cms_id.equals(this.cms_id))
				return new ResponseEntity<String> (env.getProperty("spring.application.name"), HttpStatus.OK);
			else
				return new ResponseEntity<String> ("FAILED", HttpStatus.OK);
		}
	}
	
	@PostMapping("phase2")
	public ResponseEntity<String> phase2(@RequestBody List<Participant> list)
	{
		System.out.println("[PHASE-2] : .......................................[BEGIN]");
		if(list == null)
			return new ResponseEntity<String> ("ERROR", HttpStatus.OK);
		else
		{
			this.list = list;
			
			for(Participant obj : this.list)
				System.out.println(obj);
			
			String msg = phase3(this.list);
//			String msg = "TEST";
			System.out.println(msg);
			return new ResponseEntity<String> (msg, HttpStatus.OK);
		}
	}
	
	public String phase3(List<Participant> list)
	{
		System.out.println("[PHASE-3] : .......................................[BEGIN]");
		int count = 0;
		for(Participant obj : this.list)
		{
			if(!obj.getParticipantRole().equals(Role.COMMITTER))
			{

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
			
				HttpEntity<List<Participant>> requestEntity = new HttpEntity<>(list, headers);
				ResponseEntity<String> response = null;

				try {
						response = restTemplate.exchange("http://"+ obj.getSupervisor_id() + baseURL + "/receiveList", HttpMethod.POST, requestEntity, String.class);
						System.out.println("RESPONSE : " + response.getBody());
				}
					catch(Exception ex)	{	System.out.println(ex);	}

				if(response != null)
				{
					if(response.getBody().equals("RECEIVED"))
					{
						++count;
						System.out.println("SUCCESS");
					}
					else
						System.out.println("FAILED");
				}
				else
					System.out.println("ERROR");
			}
		}

		System.out.println("Total Count : "+ count);
		return ("Total Count : "+ count);
	}
	
	@PostMapping("receiveList")
	public ResponseEntity<String> receiveParticipantList(@RequestBody List<Participant> list)
	{
		System.out.println("[PHASE-4] : .......................................[BEGIN]");
		
		if(list == null)
			return new ResponseEntity<String> ("ERROR", HttpStatus.OK);
		else
		{
			this.list = list;
			
			for(Participant obj : this.list)
				System.out.println(obj);

			System.out.println("RECEIVED");

			return new ResponseEntity<String> ("RECEIVED", HttpStatus.OK);
		}
	}

}
