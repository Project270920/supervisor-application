package rtwmas.supervisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import rtwmas.supervisor.fmm.repository.DataRepository;

@SpringBootApplication
@EnableEurekaClient
public class SupervisorApplication {

	@Bean
	public DataRepository getDataRepository()	{
		return new DataRepository();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SupervisorApplication.class, args);
	}

}
