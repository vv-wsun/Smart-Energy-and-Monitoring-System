package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import function.*;


public class ViewBill extends JFrame implements ActionListener {
	private static JButton button1 = new JButton("OK");
	private  JButton button2 = new JButton("Go back");
	private  JButton button3 = new JButton("Home");
	private static JTextField idField = new JTextField();
	
	String [] energy = {"electricity", "gas"};	
	private JComboBox comboBox1 = new JComboBox(energy);    //下拉�?
	
	public ViewBill(){
		this.setSize(1000,800);
		this.setTitle("Check Bill");
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initthis();
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);           //让窗体居中显�?
		
	}
	
	private void initthis(){
		JPanel QueryPanel1 = new JPanel();
		QueryPanel1.setLayout(null);
		QueryPanel1.setBackground(Color.WHITE);
		QueryPanel1.setBounds(134, 20, 900, 500);
//		QueryPanel1.setBounds(134, 20, 500, 300);
		
		this.getContentPane().add(QueryPanel1);  
		
		JTextPane textp1 = new JTextPane();
		textp1.setFont(new Font("Arial", Font.PLAIN, 30));
		textp1.setBounds(160, 20, 450, 44);
		textp1.setText("Please choose the standard!");
		QueryPanel1.add(textp1);
		
		button3.setBackground(SystemColor.activeCaption);   //home
		button3.setFont(new Font("Arial", Font.PLAIN, 30));
		button3.setBounds(700, 0, 153, 31);
		QueryPanel1.add(button3);
		
		JTextPane textp2 = new JTextPane();
		textp2.setFont(new Font("Arial", Font.PLAIN, 30));
		textp2.setBounds(180, 150, 150, 44);
		textp2.setText("Energy: ");
		QueryPanel1.add(textp2);
		
		QueryPanel1.add(comboBox1);
		comboBox1.setBounds(450, 150, 150, 44);
		comboBox1.setFont(new Font("Arial", Font.PLAIN, 20));
        
		JPanel panel_1 = new JPanel(new GridLayout(2,2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(180, 300, 400, 100);
		
		JLabel lab1 = new JLabel("User ID:");
	    lab1.setFont(new Font("Arial", Font.PLAIN, 30));
	    lab1.setBackground(SystemColor.activeCaption);
	    panel_1.add(lab1);
	    
	    idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 20));
        panel_1.add(idField);
        idField.setColumns(50);
        QueryPanel1.add(panel_1);
	    
		button1.setBackground(SystemColor.activeCaption);   //ok
		button1.setFont(new Font("Arial", Font.PLAIN, 30));
		button1.setBounds(300, 530, 150, 31);		
		this.getContentPane().add(button1);
		
		button2.setBackground(SystemColor.activeCaption);   //go back
		button2.setFont(new Font("Arial", Font.PLAIN, 30));
		button2.setBounds(550, 530, 150, 31);
		this.getContentPane().add(button2);

		
		
	}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<User> userlist;
			User u = null;
			String id = idField.getText();
	
			if(e.getSource() == ViewBill.button1){    //OK

				String energy = (String) comboBox1.getSelectedItem();
				if(!id.equals("")){
					try {
						userlist = file.read_from_user();
						u = Manage.search_id(userlist,id);
//						System.out.println("id: "+u.get_id());
						if(u==null)
							idField.setText("User doesn't exist!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					idField.setText("Please input the searched ID");
				}
				if(energy.equals("electricity")) {
					try {
						new CheckEBill(u);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				} else if(energy.equals("gas")) {
					try {
						new CheckGBill(u);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			if(e.getSource() == button2){   //go back
				this.setVisible(false);
				try {
					new MHomeGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource() == button3){   //home
				this.setVisible(false);
				try {
					new MHomeGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
//		public static void main(String[] args){
//			new ViewBill();
//		}
}
