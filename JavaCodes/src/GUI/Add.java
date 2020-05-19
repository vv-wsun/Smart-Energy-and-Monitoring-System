package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import function.Manage;
import function.file;
import function.User;

public class Add extends JFrame implements ActionListener{
	private static JButton cancel =new JButton("Cancel");
	private static JButton confirm =new JButton("Confirm");
	private static JButton goback =new JButton("Go Back");
	private static JButton  randID =new JButton("Generate an Id");
	private JTable table;
	private JLabel title;
	ArrayList<User> userlist = new ArrayList<User>();
	Manage m = new Manage();

	public Add(){
		
		super("Add");
		this.setBounds(100, 100, 1000, 800);
		this.getContentPane().setBackground(Color.WHITE);
	
		Container cp = this.getContentPane();

		super.setLayout(null);
		Font font = new Font("Arial",Font.PLAIN,34);
		Font font1 = new Font("Arial",Font.PLAIN,24);
		title = new JLabel("To add a new user, please input the following information");
		JPanel t = new JPanel();
	    t.add(title);
	    t.setBackground(Color.WHITE);
		title.setFont(font);

		cp.add(t);

		t.setBounds(0, 70, 1000, 100);

		//String[] columnNames= {"ID","Name","Email","Gas","electicity"};
		String[] columnNames= {"ID","Name","Email"};
		table =  new JTable(2, 3);
		table.setRowHeight(70);
		table.setBounds(50, 260, 900, 140);
		table.setFont(font1); 
		for(int i=0;i<3;i++) {
			table.setValueAt(columnNames[i], 0, i);
		}
		table.setValueAt("Click on 'Generate an ID'",1, 0);
		//setOneRowBackgroundColor(table,0,Color.gray);
		//setOneRowBackgroundColor(table,1,Color.BLUE);
		cp.add(table);
	
		JPanel b= new JPanel();
		b.setLayout(null);
		randID.setBounds(240, 50, 150, 50); //NEW BUTTON
		cancel.setBounds(90, 50, 100, 50);
		confirm.setBounds(240, 50, 100, 50);
//		confirm.setVisible(false);
		goback.setBounds(410, 50, 100, 50);
		randID.addActionListener(this);
		cancel.addActionListener(this);
		confirm.addActionListener(this);
		goback.addActionListener(this);
		b.add(randID);
		randID.setVisible(true);
		b.add(confirm);
		confirm.setVisible(false);
		b.add(cancel);
		b.add(goback);
		b.setBackground(Color.WHITE);
		b.setBounds(160, 450,800, 160);
		cp.add(b);
		this.setVisible(true);
	
	}
	
	 public static void setOneRowBackgroundColor(JTable table, int rowIndex,  
	            Color color) {  
	        try {  
	            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {  
	  
	                /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
  
	            };  
	            int columnCount = table.getColumnCount();  
	            for (int i = 0; i < columnCount; i++) {  
	                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);  
	            }  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	        }  
	    }  

	  @Override
		public void actionPerformed(ActionEvent e) {
		  	if(e.getSource() == Add.randID) {
		  		table.setValueAt(m.rand_id(),1,0);
		  		confirm.setVisible(true);
		  		randID.setVisible(false);
		  	}
		  	else if(e.getSource() == Add.cancel){    //cancel
				for(int i=0;i<3;i++) {
					table.setValueAt("",1, i);
				}
			}
			else if(e.getSource() == Add.goback){   //go back 
				this.setVisible(false);
				try {
					new ManageUserGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == Add.confirm){   //confirm to add
				String id = (String)table.getValueAt(1,0);
				String name = (String)table.getValueAt(1,1);
				String email = (String)table.getValueAt(1,2);
				if(id==null || name==null || email==null) {
					title.setText("Every field must be filled in!");
				}else {
					User u = new User();
					u.set_id(id);
					u.set_username(name);
					u.set_email(email);
					try {
						userlist = file.read_from_user();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int flag = m.check_unique(id, userlist);
					if(flag==0) {
						m.add_user(u);
						new success();
					}
					else {
						title.setText("This ID is not unique, please generate again!");
					}
				}
			}
		}
	
}
