package com.gui;

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

import com.dao.RegisterDao;

public class chkPass extends JFrame implements ActionListener{
	int regno,s1;
	String sp,temp_mob;
	JLabel l1,l2;
	JPasswordField t1,t2;
	JPanel p1;
	JButton b1,b2,b3;
	public chkPass(int regno,int s1,String temp_mob,String sp)
	{
		this.regno=regno;
		this.s1=s1;
		this.sp=sp;
		this.temp_mob=temp_mob;
		setSize(400,400);
		setVisible(true);
		Font f=new Font("Monospace", Font.BOLD, 24);
		p1=new JPanel();
		p1.setBackground(Color.yellow);
		add(p1);
		l1=new JLabel("Enter  Password");
		l1.setFont(f);
		t1=new JPasswordField(10);
		t1.setFont(f);
		b1=new JButton("Submit");
		b1.setBackground(Color.cyan);		
		b1.setFont(f);
		p1.add(l1);
		p1.add(t1);
		p1.add(b1);

		b1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==b1)
		{
			String s2=t1.getText();
			String type="recharge";
			Font f=new Font("Monospace", Font.BOLD, 24);
			RegisterDao rdao=new RegisterDao();
			ResultSet rs=rdao.profile(regno);
			try {
				if(rs.next())
				{
					if(s2.equals(rs.getString(4)))
					{
						int i=rdao.updateBal(regno, s1);
						if(i>0)
						{
							JOptionPane.showMessageDialog(b1, "Recharge Successfull...!!");
							i=rdao.transactionHistory(regno,s1,type);
							if(i>0)
							{
								System.out.println("Recharge history Saved");
							}
							DashBoard d=new DashBoard(regno);
							this.dispose();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(b1, "Enter Valid  Password."); 
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
		
	}


}
