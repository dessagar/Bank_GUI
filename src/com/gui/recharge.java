package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.RegisterDao;

public class recharge extends JFrame implements ActionListener{
	JLabel l,l1,l2,l3;
	JTextField t1,t2,t3;
	JComboBox c1;
	JButton b1,b2;
	JPanel pnl,p1;
	int regno;
	public recharge(int regno)
	{
		setSize(400,400);
		setVisible(true);
		this.regno=regno;
		Font f=new Font("Monospace", Font.BOLD, 24);
		l=new JLabel("Recharge");
		l.setFont(f);
		GridLayout gl=new GridLayout(4,2,20,20);
		BorderLayout bl=new BorderLayout();
		pnl=new JPanel();
		p1=new JPanel();
		p1.setBackground(Color.GRAY);
		add(pnl);
		add(p1,bl.NORTH);
		pnl.setLayout(gl);
		pnl.setBackground(Color.yellow);
		
		l1=new JLabel("Mobile No ");
		l1.setFont(f);
		l2=new JLabel("Service Provider");
		l2.setFont(f);
		l3=new JLabel("Amount");
		l3.setFont(f);
		
		Font fo=new Font("Monospace", Font.BOLD, 18);
		t1=new JTextField(10);
		t1.setFont(fo);
		t2=new JTextField(10);
		t2.setFont(fo);
		String str[]= {"Jio","VI","Airtel","BSNL"};
		c1=new JComboBox(str);
		c1.setFont(fo);
		
		b1=new JButton("Recharge");
		b1.setFont(fo);
		b2=new JButton("Back to Dashboard");
		b2.setFont(fo);
		p1.add(l);
		pnl.add(l1);
		pnl.add(t1);
		pnl.add(l2);
		pnl.add(c1);
		pnl.add(l3);
		pnl.add(t2);
		pnl.add(b1);
		pnl.add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==b1)
		{
			int s1=Integer.parseInt(t2.getText());
			String temp_mob=t1.getText();
			String sp=(String) c1.getSelectedItem();
			RegisterDao rdao=new RegisterDao();
			ResultSet rs=rdao.profile(regno);
			try {
				if(rs.next())
				{
					if(isValid(temp_mob))
					{
						if(s1>rs.getInt(8))
						{
							JOptionPane.showMessageDialog(b1, "Oops..!!Amount exceeds Total Balance ");
						}
						else
						{
							
							chkPass c=new chkPass(regno,s1,temp_mob,sp);
							this.dispose();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(b1, "Oops..!!Mobile Number Does not Exist ");
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
		}
	}
	
}
