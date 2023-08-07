package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dao.RegisterDao;

public class changePassword extends JFrame implements ActionListener{
	int regno;
	JLabel l1,l2;
	JPasswordField t1,t2;
	JPanel p1,p2;
	JButton b1,b2,b3;
	public changePassword(int regno)
	{
		this.regno=regno;
		setSize(400,400);
		setVisible(true);
		BorderLayout bl=new BorderLayout();
		Font f=new Font("Monospace", Font.BOLD, 24);
		p1=new JPanel();
		p2=new JPanel();
		p1.setBackground(Color.yellow);
		add(p1,bl.CENTER);
		l1=new JLabel("Enter Old Password");
		l1.setFont(f);
		t1=new JPasswordField(10);
		t1.setFont(f);
		b1=new JButton("Submit");
		b1.setFont(f);
		b1.setBackground(Color.cyan);
		b2=new JButton("Back to Dashboard");
		b2.setFont(f);
		b2.setBackground(Color.cyan);
		p1.add(l1);
		p1.add(t1);
		add(p2,bl.SOUTH);
		p2.add(b1);
		p2.add(b2);
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==b1)
		{
			String s1=t1.getText();
			Font f=new Font("Monospace", Font.BOLD, 24);
			RegisterDao rdao=new RegisterDao();
			ResultSet rs=rdao.profile(regno);
			try {
				if(rs.next())
				{
					if(s1.equals(rs.getString(4)))
					{

						updatePass u=new updatePass(regno);
						this.dispose();
						
					}
					else
					{
						JOptionPane.showMessageDialog(b1, "Enter Valid Old Password."); 
					}
				}
				
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==b2)
		{
			DashBoard d=new DashBoard(regno);
			this.dispose();
		}
	}

}
