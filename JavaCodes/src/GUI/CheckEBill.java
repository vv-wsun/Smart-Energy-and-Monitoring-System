package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.Record;
import function.User;
import function.file;

public class CheckEBill extends JFrame implements ActionListener{
	private User u;
	JPanel p = new JPanel();
	private DefaultTableModel model = null;
	private JTable table = null;
	private static JPanel panel = new JPanel();
	private static JTextPane textPane = new JTextPane();
//	private static JTextField textField = new JTextField();
//	private static JButton button = new JButton("Search");
	private static JPanel panel_1 = new JPanel();

	private static JButton btnGoBack = new JButton("Go Back");
	
	public CheckEBill(User u) throws IOException{
		this.u = u;
		this.setSize(1000,800);
		this.setTitle("Check Electric Bill");
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initFrame();
//		button.addActionListener(this);
		btnGoBack.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);          
	}
	
	private void initFrame() throws IOException{
		getContentPane().setLayout(null);
		
		
		String[][] datas = {};
	    String[] titles = {"User", "Month","Usage","Cost"};
	    model = new DefaultTableModel(datas, titles);
	    
	    table = new JTable(model);
	    table.setShowGrid(false);     
	    //table.setRowHeight(50);
	    
	    ArrayList<Record> bill = file.read_from_bill("e");
	    for(int i=0; i<bill.size(); i++){   					//row of table
	    	if(bill.get(i).getUid().equals(u.get_id()))
	    		model.addRow(new String[]{u.get_id(), String.valueOf(bill.get(i).getDate()), String.valueOf(bill.get(i).getUsage()), String.valueOf(bill.get(i).getCost())});
		}

	    DefaultTableCellRenderer r = new DefaultTableCellRenderer();     
	    r.setHorizontalAlignment(JLabel.CENTER);     
	    table.setDefaultRenderer(Object.class, r);
		//table.setBounds(200, 172, 675, 250);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(159, 150, 746, 461);
	    getContentPane().add(scrollPane);
	    panel.setLayout(null);
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(235, 19, 604, 73);
	    
	    getContentPane().add(panel);
	    textPane.setText("Electric Bills of User "+u.get_username()+":");
	    textPane.setFont(new Font("Arial", Font.PLAIN, 30));
	    textPane.setBounds(17, 19, 500, 41);
	    
	    panel.add(textPane);
//	    textField.setFont(new Font("Arial", Font.PLAIN, 30));
//	    textField.setColumns(10);
//	    textField.setBounds(200, 31, 205, 29);
	    
//	    panel.add(textField);
//	    button.setFont(new Font("Arial", Font.PLAIN, 30));
//	    button.setBackground(SystemColor.activeCaption);
//	    button.setBounds(456, 29, 131, 31);
//	    
//	    panel.add(button);
//	    panel_1.setBackground(Color.WHITE);
//	    panel_1.setBounds(159, 651, 746, 66);
//	    
	    getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    
	    btnGoBack.setBackground(SystemColor.activeCaption);
	    btnGoBack.setFont(new Font("Arial", Font.PLAIN, 30));
	    btnGoBack.setBounds(511, 19, 158, 31);
	    
	    panel_1.add(btnGoBack);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == CheckEBill.btnGoBack){    //go back
			this.setVisible(false);
			try {
				new MHomeGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
//		else if(e.getSource() == CheckEBill.button){   //search
//
//		}

	}
	

}
