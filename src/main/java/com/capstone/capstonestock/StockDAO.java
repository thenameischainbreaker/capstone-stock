package com.capstone.capstonestock;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public interface StockDAO {
	public boolean postStock(Stock stock) throws SQLException;
	public boolean addStock(int id, int add) throws SQLException;
	public boolean subStock(int id, int sub) throws SQLException;
	public Stock getStock(int id) throws SQLException;
	
}
