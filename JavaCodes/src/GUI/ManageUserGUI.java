package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.*;

public class ManageUserGUI extends JFrame implements ActionListener{
	ArrayList<User> userlist = new ArrayList<User>();
	Manage m = new Manage();
		
	private JPanel p = new JPanel();
	private DefaultTableModel model = null;
	private JTable table = null;
	private JPanel panel = new JPanel();
	private JTextPane textPane = new JTextPane();
	private JTextField textField = new JTextField();
	private static JButton button = new JButton("Search");
	private JPanel panel_1 = new JPanel();
	private static JButton btnNewButton = new JButton("Add");
	private static JButton btnNewButton_1 = new JButton("Remove");
	private static JButton btnGoBack = new JButton("Go Back");
	
	public ManageUserGUI() throws IOException{
		this.setSize(1000,800);
		this.setTitle("ManageUserGUI");
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initFrame();
		button.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnGoBack.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);           
		
	}
	
	private void initFrame() throws IOException{
		getContentPane().setLayout(null);
	
		String[][] datas = {};
	    String[] titles = {"ID", "Name","E-mail"};
	    model = new DefaultTableModel(datas, titles);
	    
	    table = new JTable(model);
	    table.setShowGrid(false);     
	    //table.setRowHeight(50);
	    userlist =  file.read_from_user();
	    
	    for(int i=0; i<userlist.size(); i++){   	
	    	User user = userlist.get(i);
	    	model.addRow(new String[]{user.get_id(), user.get_username(),user.get_email()});
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
	    textPane.setText("Serach ID:");
	    textPane.setFont(new Font("Arial", Font.PLAIN, 30));
	    textPane.setBounds(17, 19, 166, 41);
	    
	    panel.add(textPane);
	    textField.setFont(new Font("Arial", Font.PLAIN, 15));
	    textField.setColumns(10);
	    textField.setBounds(200, 31, 205, 29);
	    
	    panel.add(textField);
	    button.setFont(new Font("Arial", Font.PLAIN, 30));
	    button.setBackground(SystemColor.activeCaption);
	    button.setBounds(456, 29, 131, 31);
	    
	    panel.add(button);
	    panel_1.setBackground(Color.WHITE);
	    panel_1.setBounds(159, 651, 746, 66);
	    
	    getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    btnNewButton.setBackground(SystemColor.activeCaption);
	    btnNewButton.setFont(new Font("Arial", Font.PLAIN, 30));
	    btnNewButton.setBounds(38, 19, 131, 31);
	    
	    panel_1.add(btnNewButton);
	    btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 30));
	    btnNewButton_1.setBackground(SystemColor.activeCaption);
	    btnNewButton_1.setBounds(262, 19, 182, 31);
	    
	    panel_1.add(btnNewButton_1);
	    btnGoBack.setBackground(SystemColor.activeCaption);
	    btnGoBack.setFont(new Font("Arial", Font.PLAIN, 30));
	    btnGoBack.setBounds(511, 19, 158, 31);
	    
	    panel_1.add(btnGoBack);
		
		
		
	}
	
	public String getNum(){
		Random rand = new Random();
		String num = String.valueOf(rand.nextInt(100) + 1);
		return num;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == ManageUserGUI.button){    //search
			String id = textField.getText();
			if(!id.equals("")){
				User u = m.search_id(userlist, id);
				if(u==null) {
					textField.setText("ID doesn't exist");
				}else {
					String name = u.get_username();
					String email = u.get_email();
					while (model.getRowCount() > 0) {
		                model.removeRow(0);
		            }
					model.addRow(new String[]{u.get_id(), u.get_username(),u.get_email()});
					table.setModel(model);
				}
			}else{
				textField.setText("Please input the searching ID");
			}

		}
		else if(e.getSource() == ManageUserGUI.btnNewButton){   //add
			this.setVisible(false);
			new Add();
		}
		else if(e.getSource() == ManageUserGUI.btnNewButton_1){   // remove
			this.setVisible(false);
			new Remove();
			
		}
		else if(e.getSource() == ManageUserGUI.btnGoBack){   // back to home page
			this.setVisible(false);
			try {
				this.add(new MHomeGUI());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}


}
