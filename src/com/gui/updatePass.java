package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dao.RegisterDao;

public class updatePass extends JFrame implements ActionListener {
	JLabel l1;
	JPasswordField t1;
	JButton b1;
	JPanel pnl,pnl2;
	int regno;

	public updatePass(int regno)
	{
		Font f=new Font("Monospace", Font.BOLD, 24);
		setSize(400, 400);
		setVisible(true);
		this.regno=regno;
		BorderLayout bl=new BorderLayout();
		pnl=new JPanel();
		pnl2=new JPanel();
		pnl.setBackground(Color.yellow);
		add(pnl,bl.CENTER);
		l1 = new JLabel("Enter New Password");
		l1.setFont(f);
		t1=new JPasswordField(10);
		t1.setFont(f);
		add(pnl2,bl.SOUTH);
		b1= new JButton("Update Password");
		b1.setFont(f);
		pnl.add(l1);
		pnl.add(t1);
		pnl2.add(b1);
		b1.setBackground(Color.cyan);
		b1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1)
		{
			String s1=t1.getText();
			RegisterDao rdao=new RegisterDao();
			int i=rdao.updatePassword(s1, regno);
			if(i>0)
			{
				JOptionPane.showMessageDialog(b1, "Password Updates Successfully...!!"); 
			}
			DashBoard d=new DashBoard(regno);
			this.dispose();
		}
	}
}
