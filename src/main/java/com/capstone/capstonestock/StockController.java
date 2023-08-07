package com.capstone.capstonestock;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = {"https://domainofchain.s3.us-east-2.amazonaws.com","http://localhost:4200/", "http://localhost:4200/","https://capstone-angular-jj.s3.us-east-2.amazonaws.com"})
public class StockController {
	@Autowired
	StockDAO repo;
	@Value("${gateway-host}")
	private String gatewayHost;	
	@PostMapping("/register")
	public String postStock(@RequestHeader("googleBearerToken") String headerValue, @RequestBody Stock stock) 
	{
		//check is user is admin and logged in 
		try {
			  RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == Admin.FALSE)
				throw new SecurityException();
			
			
			
			
			if(repo.postStock(stock))
				return "\"Stock Registered\"";
			else
				return "\"Stock Not Registered\"";
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"Error in adding stock. Check permissions.\"";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"Error in adding stock. Check if making stock negative.\"";
		}
	}
	
	@PutMapping("/add")
	public String addStock(@RequestHeader("googleBearerToken") String headerValue, @RequestBody Stock stock) 
	{

		
	
		//check if user is admin and logged in
		try {
			  RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == Admin.FALSE)
				throw new SecurityException();
			
			
			
			if(repo.addStock(stock.getP_id(), stock.getS_quantity()))
				return "\"Stock Added\"";
			else
				return "\"Stock Not Added\"";
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"Error in adding stock. Check permissions.\"";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"Error in adding stock. Check if making stock negative.\"";
		}
	}
	
	@PutMapping("/sub")
	public String subStock( @RequestBody Stock stock) 
	{
		//check if user is admin and logged in 
		try {
			/*
			 * RestTemplate restTemplate = new RestTemplate(); String url = "http://" +
			 * gatewayHost + "/user/getUserRole";
			 * 
			 * UserRole response = restTemplate.postForObject(url, headerValue,
			 * UserRole.class); if(response.getRole() == Admin.FALSE) throw new
			 * SecurityException();
			 */
			
			
			
			if(repo.subStock(stock.getP_id(), stock.getS_quantity()))
				return "\"Stock Removed\"";
			else
				return "\"Stock Not Removed\"";
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  "\"Error in adding stock. Check permissions.\"";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"Error in adding stock. Check if making stock negative.\"";
		}
	}
	
	@GetMapping("/get/{id}")
	public int getStock(@PathVariable int id) throws SQLException
	{
		return repo.getStock(id).getS_quantity();
	}
	
	
}
