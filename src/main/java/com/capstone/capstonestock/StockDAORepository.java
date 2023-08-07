package com.capstone.capstonestock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockDAORepository implements StockDAO {
	@Autowired
	JdbcTemplate jt;
	@Autowired
	DataSource ds;
	@Override
	public boolean postStock(Stock stock) throws SQLException {
		// TODO Auto-generated method stub
		String query = "insert into stock values (?,?)";
		int i = jt.update(query, new Object[] {stock.getP_id(),stock.getS_quantity()});
		if(i>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean addStock(int id, int add) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from stock where p_id = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		if(ps.execute())
		{
			ResultSet rs = ps.executeQuery();
			int original = 0;
			while(rs.next())
			{
				original = rs.getInt(2);
			}
			query = "update stock set s_quantity = ? where p_id = " + id;
			ps = con.prepareStatement(query);
			ps.setInt(1, original + add);
			if(ps.executeUpdate()>0) {
				con.close();
				return true;
			}
			else {
				con.close();
				return false;
			}
		}
		else {
			con.close();
			return false;
		}
	}
	
	@Override
	public boolean subStock(int id, int sub) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from stock where p_id = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		if(ps.execute())
		{
			ResultSet rs = ps.executeQuery();
			int original = 0;
			while(rs.next())
			{
				original = rs.getInt(2);
			}
			query = "update stock set s_quantity = ? where p_id = " + id;
			ps = con.prepareStatement(query);
			ps.setInt(1, original - sub);
			if(ps.executeUpdate()>0) {
				con.close();
				return true;
			}
			else {
				con.close();
				return false;
			}
		}
		else {
			con.close();
			return false;
		}
	}

	@Override
	public Stock getStock(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from stock where p_id = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Stock s = new Stock();
		while(rs.next())
		{
			s.setP_id(rs.getInt(1));
			s.setS_quantity(rs.getInt(2));
		}
		con.close();
		return s;
	}
}
