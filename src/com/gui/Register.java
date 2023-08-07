package com.gui;
import javax.swing.*;

import com.dao.RegisterDao;

import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  

public class Register extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6, l7, l8,l9,l10;  
	JTextField tf1, tf2, tf5, tf6, tf7,tf8,tf9;  
	JButton btn1, btn2;  
	JPasswordField p1, p2;  
	public Register()  
	{  
		JFrame jf=new JFrame();
		setVisible(true);  
		setSize(700, 700);  
		setLayout(null); 

		getContentPane().setBackground(Color.YELLOW);
		Font f=new Font("Monospace", Font.BOLD, 18);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setTitle("Registration Form ");  
		l1 = new JLabel("Registration Here");
		l1.setForeground(Color.blue);  
		l1.setFont(new Font("Serif", Font.BOLD, 30));
		l9=new JLabel("Registeration Id : ");
		l9.setFont(f);
		l2 = new JLabel("Name:");  
		l2.setFont(f);
		l3 = new JLabel("Email-ID:");
		l3.setFont(f);
		l4 = new JLabel("Create Passowrd:");  
		l4.setFont(f);
		l5 = new JLabel("Confirm Password:");  
		l5.setFont(f);
		l6 = new JLabel("Country:");  
		l6.setFont(f);
		l7 = new JLabel("State:");  
		l7.setFont(f);
		l8 = new JLabel("Phone No:"); 
		l8.setFont(f);
		l10=new JLabel("Balance:");
		l10.setFont(f);

		tf8=new JTextField();
		tf8.setFont(f);
		tf1 = new JTextField();  
		tf1.setFont(f);
		tf2 = new JTextField();  
		tf2.setFont(f);
		tf9=new JTextField();
		tf9.setFont(f);
		p1 = new JPasswordField();  
		p1.setFont(f);
		p2 = new JPasswordField(); 
		p2.setFont(f);
		tf5 = new JTextField();  
		tf5.setFont(f);
		tf6 = new JTextField();  
		tf6.setFont(f);
		tf7 = new JTextField();  
		tf7.setFont(f);
		btn1 = new JButton("Submit");  
		btn1.setFont(f);
		btn1.setBackground(Color.cyan);
		btn2 = new JButton("Reset"); 
		btn2.setFont(f);
		btn2.setBackground(Color.cyan);
		btn1.addActionListener(this);  
		btn2.addActionListener(this); 
		l1.setBounds(100, 30, 400, 30);  
		l9.setBounds(80, 70, 400, 30);  

		l2.setBounds(80, 110, 200, 30);  
		l3.setBounds(80, 150, 200, 30);  
		l4.setBounds(80, 190, 200, 30);  
		l5.setBounds(80, 230, 200, 30);  
		l6.setBounds(80, 270, 200, 30);  
		l7.setBounds(80, 310, 200, 30);  
		l8.setBounds(80, 350, 200, 30); 
		l10.setBounds(80,390,200,30);
		tf8.setBounds(300, 70, 200, 30);
		tf1.setBounds(300, 110, 200, 30);  
		tf2.setBounds(300, 150, 200, 30);  
		p1.setBounds(300, 190, 200, 30);  
		p2.setBounds(300, 230, 200, 30);  
		tf5.setBounds(300, 270, 200, 30);  
		tf6.setBounds(300, 310, 200, 30);  
		tf7.setBounds(300, 350, 200, 30);  
		tf9.setBounds(300, 390, 200, 30);
		btn1.setBounds(170, 450, 100, 30);  
		btn2.setBounds(290, 450, 100, 30);
		add(l1);  
		add(l9);
		add(tf8);
		add(l2);  
		add(tf1);  
		add(l3);  
		add(tf2);  
		add(l4);  
		add(p1);  
		add(l5);  
		add(p2);  
		add(l6);  
		add(tf5);  
		add(l7);  
		add(tf6);  
		add(l8);  
		add(tf7); 
		add(l10);
		add(tf9);
		add(btn1);  
		add(btn2);  
	}  
	public  boolean isValid(String mobNo)
	{

		// The given argument to compile() method
		// is regular expression. With the help of
		// regular expression we can validate mobile
		// number.
		// 1) Begins with 0 or 91
		// 2) Then contains 7 or 8 or 9.
		// 3) Then contains 9 digits
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

		// Pattern class contains matcher() method
		// to find matching between given number
		// and regular expression
		Matcher m = p.matcher(mobNo);
		return (m.find() && m.group().equals(mobNo));
	}
	public void actionPerformed(ActionEvent e)   
	{  
		if (e.getSource() == btn1)  
		{  
			int x = 0;  
			int s10=Integer.parseInt(tf8.getText());
			String s1 = tf1.getText();  
			String s2 = tf2.getText();  
			char[] s3 = p1.getPassword();  
			char[] s4 = p2.getPassword();   
			String s8 = new String(s3);  
			String s9 = new String(s4);  
			String s5 = tf5.getText();  
			String s6 = tf6.getText();  
			String s7 = tf7.getText();
			int s11=Integer.parseInt(tf9.getText());
			int temp_id=Integer.parseInt(tf8.getText());
			String temp_mob=tf7.getText();
			RegisterDao rdao=new RegisterDao();
			ResultSet rs=rdao.validateID();
			try {
				while(rs.next())
				{
					if(rs.getInt(1)==temp_id)
					{
						JOptionPane.showMessageDialog(btn1, "ID already Exist...Enter Unique ID"); 
					}
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(s8.equals(s9))
			{
				if (isValid(temp_mob))  
				{  
					int i=rdao.createUser(s10,s1,s2,s8,s9,s5,s6,s7,s11);

					if (i > 0)   
					{  
						JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");  
						Login log=new Login();
						this.dispose();
					}  
				}  
				else  
				{  
					JOptionPane.showMessageDialog(btn1, "Enter valid Mobile number");  
				}  
			}
			else
			{
				JOptionPane.showMessageDialog(btn1, "Password Does Not Match");  
			}
		}   
		else  
		{  
			tf1.setText("");  
			tf2.setText("");  
			p1.setText("");  
			p2.setText("");  
			tf5.setText("");  
			tf6.setText("");  
			tf7.setText("");  
		}  
	}   

}  


