package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.RegisterDao;

public class Profile extends JFrame implements ActionListener 
{
	JLabel l,l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JPanel p1,p2,p3;
	JButton but1;
	int regno;
	public Profile(int regno)
	{
		setSize(700,700);
		setVisible(true);
		Font f=new Font("Monospace", Font.BOLD, 24);
		l=new JLabel("Profile");
		l.setFont(f);
		GridLayout gl=new GridLayout(8,2,30,30);
		BorderLayout bl=new BorderLayout();
		p1=new JPanel();
		p1.setBackground(Color.yellow);
		p2=new JPanel();
		p2.setBackground(Color.GRAY);
		p3=new JPanel();
		p3.setBackground(Color.GRAY);
		add(p1,bl.CENTER);
		add(p2,bl.SOUTH);
		add(p3,bl.NORTH);
		p1.setLayout(gl);
		l1=new JLabel("Registration ID ");
		l2=new JLabel("Account holder Name ");
		l3=new JLabel("Email ID ");
		l4=new JLabel("Password ");
		l5=new JLabel("country");
		l6=new JLabel("State ");
		l7=new JLabel("Mobile Number");
		l8=new JLabel("Balance");

		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		l5.setFont(f);
		l6.setFont(f);
		l7.setFont(f);
		l8.setFont(f);

		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextField();
		t5=new JTextField();
		t6=new JTextField();
		t7=new JTextField();
		t8=new JTextField();

		t1.setFont(f);
		t2.setFont(f);
		t3.setFont(f);
		t4.setFont(f);
		t5.setFont(f);
		t6.setFont(f);
		t7.setFont(f);
		t8.setFont(f);
		
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		
		but1=new JButton("Back To Dashboard");
		but1.setFont(f);
		but1.setBackground(Color.cyan);

		RegisterDao rdao=new RegisterDao();
		ResultSet rs= rdao.profile(regno);
		try {
			if(rs.next())
			{
				this.regno=rs.getInt(1);
				t1.setText(Integer.toString(rs.getInt(1)));
				t2.setText(rs.getString(2));
				t3.setText(rs.getString(3));
				t4.setText(rs.getString(4));
				t5.setText(rs.getString(5));
				t6.setText(rs.getString(6));
				t7.setText(rs.getString(7));
				t8.setText(Integer.toString(rs.getInt(8)));


			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p3.add(l);
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(l3);
		p1.add(t3);
		p1.add(l4);
		p1.add(t4);
		p1.add(l5);
		p1.add(t5);
		p1.add(l6);
		p1.add(t6);
		p1.add(l7);
		p1.add(t7);
		p1.add(l8);
		p1.add(t8);

		p2.add(but1);
		but1.setFont(f);

		/*	l1.setBounds(80, 70, 200, 30);  
		l2.setBounds(80, 110, 200, 30);  
		l3.setBounds(80, 150, 200, 30);  
		l4.setBounds(80, 190, 200, 30);  
		l5.setBounds(80, 230, 200, 30);  
		l6.setBounds(80, 270, 200, 30);  
		l7.setBounds(80, 310, 200, 30); 
		l8.setBounds(80,350,200,30);
		t1.setBounds(300, 70, 200, 30);
		t2.setBounds(300, 110, 200, 30);  
		t3.setBounds(300, 150, 200, 30);  
		t4.setBounds(300, 190, 200, 30);  
		t5.setBounds(300, 230, 200, 30);  
		t6.setBounds(300, 270, 200, 30);  
		t7.setBounds(300, 310, 200, 30);  
		t8.setBounds(300, 350, 200, 30);  
		but1.setBounds(170, 450, 100, 30);  */

		but1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but1)
		{
			DashBoard d=new DashBoard(regno);
			this.dispose();
		}

	}
}
