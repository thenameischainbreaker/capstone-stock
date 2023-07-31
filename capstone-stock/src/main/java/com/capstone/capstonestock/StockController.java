package com.capstone.capstonestock;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockDAO repo;
	
	@PostMapping("/register")
	public String postStock(@RequestBody Stock stock) throws SQLException
	{
		if(repo.postStock(stock))
			return "Stock Registered";
		else
			return "Stock Not Registered";
	}
	
	@PutMapping("/add")
	public String addStock(@RequestBody Stock stock) throws SQLException
	{
		if(repo.addStock(stock.getP_id(), stock.getS_quantity()))
			return "Stock Added";
		else
			return "Stock Not Added";
	}
	
	@PutMapping("/sub")
	public String subStock(@RequestBody Stock stock) throws SQLException
	{
		if(repo.subStock(stock.getP_id(), stock.getS_quantity()))
			return "Stock Removed";
		else
			return "Stock Not Removed";
	}
	
	@GetMapping("/get/{id}")
	public Stock getStock(@PathVariable int id) throws SQLException
	{
		return repo.getStock(id);
	}
}
