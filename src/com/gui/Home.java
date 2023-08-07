package com.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Home extends JFrame implements ActionListener{

	JButton btnlogin,btnregister;
	JPanel pnl ,pnl2;
	JLabel l1;
	public Home(){
		super("Home Page..");
		setSize(400, 400);
		setVisible(true);
		BorderLayout bl=new BorderLayout();
		pnl=new JPanel();
		add(pnl,bl.CENTER);
		pnl2=new JPanel();
		add(pnl2,bl.NORTH);
		pnl.setBackground(Color.yellow);
		
		Font f=new Font("Sans serif",Font.BOLD,25);
		l1=new JLabel("  | Welcome To Home Page | ");
		l1.setFont(f);
		l1.setBackground(Color.gray);
		
		btnlogin=new JButton("LOGIN");
		btnregister=new JButton("REGISTER");
		
		btnlogin.setForeground(Color.black);
		btnlogin.setSize(50, 30);
		btnlogin.setFont(f);
		btnlogin.setBackground(Color.cyan);
		btnregister.setForeground(Color.black);
		btnregister.setSize(50, 30);
		btnregister.setFont(f);
		btnregister.setBackground(Color.cyan);
		
		pnl2.add(l1);
		pnl.add(btnlogin);
		pnl.add(btnregister);
		
		btnlogin.addActionListener(this);
		btnregister.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnlogin) {
			Login lui=new Login();
			this.dispose();
		}
		if(e.getSource()==btnregister) {
			Register rui=new Register();
			this.dispose();
		}
		
	}

	
}
