package com.gui;

import javax.swing.*;
import com.dao.RegisterDao;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	public Login()
	{
		 JFrame jf = new JFrame();
		setTitle("Login Form");
		setSize(600, 400);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.YELLOW);
		
		Font f=new Font("Monospace", Font.BOLD, 18);
		l1 = new JLabel("Login Form");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 30));

		l2 = new JLabel("Enter Email:");
		l2.setFont(f);
		l3 = new JLabel("Enter Password:");
		l3.setFont(f);
		tf1 = new JTextField();
		tf1.setFont(f);
		p1 = new JPasswordField();
		p1.setFont(f);
		btn1 = new JButton("Submit");
		btn1.setFont(f);
		btn1.setBackground(Color.cyan);
		
		l1.setBounds(100, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);
		tf1.setBounds(300, 70, 200, 30);
		p1.setBounds(300, 110, 200, 30);
		btn1.setBounds(200, 190, 100, 30);

		add(l1);
		add(l2);
		add(tf1);
		add(l3);
		add(p1);
		add(btn1);
		btn1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		showData();
	}

	public void showData()
	{
		JFrame f1 = new JFrame();
		JLabel l, l0;

		String str1 = tf1.getText();
		char[] p = p1.getPassword();
		String str2 = new String(p);
		RegisterDao rdao=new RegisterDao();
		ResultSet rs=rdao.validateUser(str1,str2);
		try {
			if(rs.next()) {
				/*f1.setVisible(true);
				f1.setSize(600, 600);
				f1.setLayout(null);
				l = new JLabel();
				l0 = new JLabel("you are succefully logged in..");
				l0.setForeground(Color.blue);
				l0.setFont(new Font("Serif", Font.BOLD, 30));
				l.setBounds(60, 50, 400, 30);
				l0.setBounds(60, 100, 400, 40);

				f1.add(l);
				f1.add(l0);
				l.setText("Welcome " + rs.getString(2));
				l.setForeground(Color.red);
				l.setFont(new Font("Serif", Font.BOLD, 30));*/
				int regno=rs.getInt(1);
				DashBoard d=new DashBoard(regno);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Incorrect email-Id or password..Try Again with correct detail");
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}