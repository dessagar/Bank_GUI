package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.dao.RegisterDao;

public class history extends JFrame implements ActionListener{

	int regno;
	JTable table;
	JTable table2;
	JButton btn1;
	JLabel lb1;
	ResultSet rs=null;
	JPanel pnl1,pnl2,pnl3;
	public history(int regno)
	{
		super("Transaction History...");
		setSize(1050,600);
		setVisible(true);
		this.regno=regno;
		pnl1=new JPanel();
		pnl2=new JPanel();
		pnl3=new JPanel();
		BorderLayout bl=new BorderLayout();
		String column[]={"Transaction ID","Transaction Type","Transaction Amount","Date"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane s1 = new JScrollPane(table);
		JTableHeader head=table.getTableHeader();
		head.setBackground(Color.yellow);
		head.setForeground(Color.black);
		head.setFont(new Font("Monospace", Font.BOLD, 26));
		table.setBackground(Color.cyan);
		table.setForeground(Color.black);
		table.setRowHeight(30);	
		table.setFont(new Font("Monospace", Font.BOLD, 20));
		RegisterDao rdao=new RegisterDao();
		try {
			rs=rdao.retrive_transaction(regno);
			while (rs.next()) {
				int tr_id = rs.getInt(1);
				int amt=rs.getInt(2);
				String type=rs.getString(3);
				Timestamp date=rs.getTimestamp(4);

				model.addRow(new Object[]{tr_id, type, amt,date});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(pnl2,bl.SOUTH);
		add(pnl3,bl.NORTH);
		lb1=new JLabel("|| TRANSACTION HISTORY ||");
		lb1.setBackground(Color.gray);
		lb1.setFont(new Font("Monospace", Font.BOLD, 24));
		add(s1);
		btn1=new JButton("Back To Dashboard");
		btn1.setBackground(Color.GRAY);
		pnl2.add(btn1);
		pnl3.add(lb1);
		btn1.addActionListener(this);
		btn1.setFont(new Font("Monospace", Font.BOLD, 24));

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn1) {
			DashBoard dh=new DashBoard(regno);
			this.dispose();
		}
	}
}
