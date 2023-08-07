package com.dao;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class RegisterDao {

	Connection con=null; 
	int i=0;
	public int createUser(int s10,String s1, String s2, String s8, String s9, String s5, String s6, String s7,int s11) {
		try {
			con=dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?,?,?,?,?)");  
			ps.setInt(1, s10);
			ps.setString(2, s1);  
			ps.setString(3, s2);  
			ps.setString(4, s8);  
			ps.setString(5, s5);  
			ps.setString(6, s6);  
			ps.setString(7, s7); 
			ps.setInt(8, s11);
			i =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;

	}
	public ResultSet validateUser(String str1,String str2) {
		ResultSet rs=null;
		con=dbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from reg where email=? and password=?");
			ps.setString(1, str1);
			ps.setString(2, str2);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet profile(int regno)
	{
		ResultSet rs=null;
		con=dbConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select *from reg where regid=?");
			ps.setInt(1, regno);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int updatePassword(String s,int regno)
	{
		con=dbConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("update reg set password=? where regid=?");
			ps.setString(1, s);
			ps.setInt(2, regno);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int updateBal(int regno,int amt)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=dbConnection.getConnection();
		try {
			rs=profile(regno);
			if(rs.next())
			{
				ps=con.prepareStatement("update reg set balance=? where regid=?");
				ps.setInt(1, rs.getInt(8)-amt);
				ps.setInt(2, regno);
				i=ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int depositAmt(int regno,int amt)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=dbConnection.getConnection();
		try {
			rs=profile(regno);
			if(rs.next())
			{
				ps=con.prepareStatement("update reg set balance=? where regid=?");
				ps.setInt(1, rs.getInt(8)+amt);
				ps.setInt(2, regno);
				i=ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public ResultSet validateID()
	{
		ResultSet rs=null;
		java.sql.Statement st=null;
		con=dbConnection.getConnection();
		String str="select *from reg";
		try {
			st= con.createStatement();
			rs=st.executeQuery(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int transactionHistory(int regno,int amt,String type)
	{
		i=0;
		con=dbConnection.getConnection();
		Timestamp date=new Timestamp(new Date().getTime());
		Random rd=new Random();
		//String type="Recharge";
		int tr_id=rd.nextInt(200000000);
		try {
			PreparedStatement ps=con.prepareStatement("insert into transaction values(?,?,?,?,?)");
			ps.setInt(1, tr_id);
			ps.setInt(2, amt);
			ps.setString(3, type);
			ps.setTimestamp(4,date);
			ps.setInt(5, regno);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet retrive_transaction(int regno)
	{
		ResultSet rs=null;
		con=dbConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select *from transaction where regid=?");
			ps.setInt(1, regno);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
