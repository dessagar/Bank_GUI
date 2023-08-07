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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.RegisterDao;

public class withdrawl extends JFrame implements ActionListener{
	JLabel l,l1;
	JTextField t1;
	JButton b1,b2;
	JPanel pnl,pnl2,pnl3;
	int regno;
	public withdrawl(int regno)
	{
		setSize(400,400);
		setVisible(true);
		this.regno=regno;
		Font f=new Font("Monospace", Font.BOLD, 24);
		Font fo=new Font("Monospace", Font.BOLD, 18);
		BorderLayout bl=new BorderLayout();
		//GridLayout gl=new GridLayout(2,2,20,20);
		pnl3=new JPanel();
		add(pnl3,bl.NORTH);
		
		pnl=new JPanel();
		add(pnl,bl.CENTER);
		
		pnl.setBackground(Color.yellow);
		//pnl.setLayout(gl);
		pnl2=new JPanel();
		add(pnl2,bl.SOUTH);
		l=new JLabel("Withdrwl");
		l.setBackground(Color.gray);
		l1=new JLabel("withdrawl Amount ");
		l1.setFont(f);
		
		t1=new JTextField(10);
		t1.setFont(fo);
		
		
		b1=new JButton("withdrawl");
		b1.setFont(fo);
		b1.setBackground(Color.cyan);
		b2=new JButton("Back to Dashboard");
		b2.setFont(fo);
		b2.setBackground(Color.cyan);
		
		pnl3.add(l);
		pnl.add(l1);
		pnl.add(t1);
		pnl2.add(b1);
		pnl2.add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==b1)
		{
			int s1=Integer.parseInt(t1.getText());
			RegisterDao rdao=new RegisterDao();
			ResultSet rs=rdao.profile(regno);
			try {
				if(rs.next())
				{
					if(s1>rs.getInt(8))
					{
						JOptionPane.showMessageDialog(b1, "Oops..!!Amount exceeds Total Balance ");
					}
					else
					{
						chkWithdrawl cw=new chkWithdrawl(regno,s1);
						this.dispose();
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
