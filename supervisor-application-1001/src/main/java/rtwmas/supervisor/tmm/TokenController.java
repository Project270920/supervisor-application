package rtwmas.supervisor.tmm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedback")
public class TokenController
{	
	@Autowired
	private TokenService tokenService;

	@PostMapping("token")
	public ResponseEntity<String> employeeFeedback(@RequestBody Token token)
	{
		System.out.println("REQUEST : " + token);

		if(token != null)
		{
			if(tokenService.addToken(token))
				return new ResponseEntity<String>(token.getToken_id(), HttpStatus.OK);
			else
				return new ResponseEntity<String>("FAILED", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("ERROR", HttpStatus.NOT_FOUND);
	}

	@GetMapping("token")
	public ResponseEntity<List<Token>> listEmployeeFeedback()
	{
		List<Token> list = tokenService.getToken();
		
		if(list != null)
			return new ResponseEntity<List<Token>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<List<Token>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}
}