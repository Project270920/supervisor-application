package rtwmas.supervisor.tmm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
	@Autowired
	private TokenRepository tokenRepo;
	
	public boolean addToken(Token obj)
	{
		if(obj != null)
		{
			System.out.println("Entity Saved...");
			tokenRepo.save(obj);
			return true;
		}
		else
		{
			System.out.println("Entity Not Saved...");
			return false;
		}
	}
	
	public List<Token> getToken()
	{
		List<Token> obj = tokenRepo.findAll();
		if(obj != null)
		{
			System.out.println("Found All...");
			return obj;
		}
		else
		{
			System.out.println("Not Found All...");
			return new ArrayList<>();
		}
	}
}
