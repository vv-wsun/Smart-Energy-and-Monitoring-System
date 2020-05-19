package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import function.Manage;
import function.file;
import function.User;

public class Remove extends JFrame implements ActionListener{
	private static JButton cancel =new JButton("Cancel");
	private static JButton delete =new JButton("Delete");
	private static JButton goback =new JButton("go back");
	private static JButton sure = new JButton("search");
	JTextField search;
	JTable table;
	Font font;
	User u;
	ArrayList<User> userlist = new ArrayList<User>();
	Manage m = new Manage();
	
	public Remove(){

		super("Remove");
		this.setBounds(100, 100, 1000, 800);
		this.getContentPane().setBackground(Color.WHITE);
		
		Container cp = this.getContentPane();
		cp.setBackground(Color.WHITE);
		super.setLayout(null);
		font = new Font("Arial",Font.PLAIN,42);
		JLabel title = new JLabel("Search user ID:");
		search = new JTextField(20);
		search.setSize(20, 42);
		
		JPanel t = new JPanel();
		
	    t.add(title);
	    t.add(search);
	    t.add(sure);
	    
		title.setFont(font);
		cp.add(t);
		t.setBounds(0, 70, 1000, 100);
		t.setBackground(Color.WHITE);
		
		String[] columnNames= {"ID","Name","E-mail"};
		table =  new JTable(2, 3);
		table.setRowHeight(70);
		TableColumn firsetColumn = table.getColumnModel().getColumn(2);
		firsetColumn.setPreferredWidth(500);
		table.setBounds(50, 260, 900, 140);
		table.setFont(font); 
		for(int i=0;i<3;i++) {
			table.setValueAt(columnNames[i], 0, i);
		}
		
		cp.add(table);
		JPanel b= new JPanel();
		b.setLayout(null);
		b.setBackground(Color.WHITE);
		cancel.setBounds(90, 50, 100, 50);
		delete.setBounds(240, 50, 100, 50);
		goback.setBounds(410, 50, 100, 50);
		cancel.addActionListener(this);
		delete.addActionListener(this);
		goback.addActionListener(this);
		sure.addActionListener(this);
		b.add(cancel);
		b.add(delete);
		delete.setVisible(false);
		b.add(goback);
		b.setBounds(160, 450,800, 160);
		cp.add(b);
		this.setVisible(true);
	
	}
	
	
	public static void main(String[] args) {
		new Remove();
	}
	  @Override
		public void actionPerformed(ActionEvent e) {
		  	boolean flag = false;
		  	boolean flag2 = false;
			if(e.getSource() == Remove.cancel){    //cancel

				this.setVisible(false);
				try {
					new ManageUserGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if(e.getSource() == Remove.goback){   //go back
				this.setVisible(false);
				try {
					new ManageUserGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == Remove.delete){   //delete
				userlist = m.removeFromList(u, userlist);
				try {
					m.removeFromInfo(u,userlist);
					flag = m.removeBills(u,"e");
					flag2 = m.removeBills(u,"g");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if( m.search_id(file.read_from_user(),u.get_id())==null) {
//						new success();
						if(flag&&flag2){					
							table.setValueAt("", 1, 0);
							table.setValueAt("", 1, 1);
							table.setValueAt("", 1, 2);
							new success();
						}else{
							search.setText("Remove user record error! Please try again");
							file.write_into_user(u,true);
						}
					}
					else
						search.setText("Remove user error! Please try again");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			
			else if(e.getSource() == Remove.sure){   //search
				String id = search.getText();
				try {
					userlist =  file.read_from_user();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!id.equals("")){
					u = m.search_id(userlist, id);
					if(u==null) {
						search.setText("ID doesn't exist");
					}else {
						String name = u.get_username();
						String email = u.get_email();
						table.setValueAt(u.get_id(), 1, 0);
						table.setValueAt(u.get_username(), 1, 1);
						table.setValueAt(u.get_email(), 1, 2);
						delete.setVisible(true);
					}
				}else{
					search.setText("Please input the searching ID");
				}
			}
		}
	
}
