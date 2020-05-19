package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.Record;
import function.User;
import function.file;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
public class RcvRecord extends JFrame {
	private String energy;
	JPanel p = new JPanel();
	private DefaultTableModel model = null;
	private JTable table = null;
	private static JPanel panel = new JPanel();
	private static JTextPane textPane = new JTextPane();
//	private static JTextField textField = new JTextField();
//	private static JButton button = new JButton("Search");
	private static JPanel panel_1 = new JPanel();
	ArrayList<String> info = null;

//	private static JButton btnGoBack = new JButton("Go Back");
	
	public RcvRecord(ArrayList<String> info,String energy) throws IOException{
		this.energy = energy;
		this.info = info;
		this.setSize(1000,800);
		if(energy.equals("e"))
			this.setTitle("Newly Received Electric Record");
		if(energy.equals("g"))
			this.setTitle("Newly Received Gas Record");
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initFrame();
//		button.addActionListener(this);
//		btnGoBack.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);          
	}
	
	private void initFrame() throws IOException{
		getContentPane().setLayout(null);
		
		
		String[][] datas = {};
	    String[] titles = {"User", "Month","Usage"};
	    model = new DefaultTableModel(datas, titles);
	    
	    table = new JTable(model);
	    table.setShowGrid(false);     
	    //table.setRowHeight(50);
//	    boolean hasRecord = false;
	    for(int i=0; i<info.size(); i=i+5){   					//row of table
//	    	if(info.get(i+4).equals("0")) {
	    		model.addRow(new String[]{info.get(i+1), info.get(i), info.get(i+2)});
//	    		hasRecord = true;
//	    	}
		}
	    if(info.size()>0)
	    	textPane.setText("You have received some records from users!");
	    else textPane.setText("No new records have been received!");
	    
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
	    textPane.setFont(new Font("Arial", Font.PLAIN, 30));
	    textPane.setBounds(17, 19, 500, 41);
	    
	    panel.add(textPane);	    
	    getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    
	}

}

