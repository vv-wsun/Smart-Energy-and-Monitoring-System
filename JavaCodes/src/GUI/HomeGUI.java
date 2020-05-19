package GUI;
import function.Time;
import function.Login;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class HomeGUI extends JFrame implements ActionListener{

//	static String path = "src\\function\\";

	JPanel panel,panel_1,panel_2;
	JTextPane txt1;
	JTextField idField,pssField;
	JLabel lab1,lab2;
	private static JButton btnManageUser = new JButton("Login");
	public HomeGUI(){
		go();
		
		btnManageUser.addActionListener(this);
	}
	public void go(){
		this.setTitle("User Login");
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
		this.setBackground(Color.WHITE);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(19, 100, 900, 100);
		this.getContentPane().add(panel);
		
		txt1 = new JTextPane();
		txt1.setFont(new Font("Arial", Font.PLAIN, 40));  
		txt1.setText("Welcome to our smart energy system.\nPlease enter your correct ID and Password.\n");
		panel.add(txt1);
		
		panel_1 = new JPanel(new GridLayout(2,2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(250, 300, 400, 100);
		this.getContentPane().add(panel_1);
		
		lab1 = new JLabel("ID:");
	    lab1.setFont(new Font("Arial", Font.PLAIN, 30));
	    lab1.setBackground(SystemColor.activeCaption);
	    panel_1.add(lab1);
	    
	    idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 20));
        panel_1.add(idField);
        idField.setColumns(50);
        
        lab2 = new JLabel("PASSWORD:");
        lab2.setFont(new Font("Arial", Font.PLAIN, 30));
        panel_1.add(lab2);
        
        pssField = new JTextField();
        pssField.setFont(new Font("Arial", Font.PLAIN, 20));
        panel_1.add(pssField);
        pssField.setColumns(50);
	    
		panel_2 = new JPanel();
		panel_2.setBounds(346, 450, 250, 100);
		panel_2.setBackground(Color.WHITE);
		this.getContentPane().add(panel_2);
		panel_2.setLayout(null);

        btnManageUser.setBounds(80, 5, 100, 37);
        btnManageUser.setFont(new Font("Arial", Font.PLAIN, 25));
        btnManageUser.setBackground(SystemColor.activeCaption);
        panel_2.add(btnManageUser); 
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000,800);
		this.setVisible(true); 
	}
	public static void main(String[] args){
		HomeGUI home = new HomeGUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String pss = pssField.getText();
		System.out.printf(id,pss);
		Login l=new Login(id,pss);
		
		if(e.getSource() == HomeGUI.btnManageUser){ 
			if(!id.equals("")){
				if(!pss.equals("")) {
					String result = check(l); 
					if(result == "idfail") {
						idField.setText("ID doesn't exist");
					}
					else if(result == "pssfail") {
						pssField.setText("Wrong password");
					}else {
						this.setVisible(false);
						try {
							System.out.println("in HOMEGUI"+id);
							new uhome(id);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}		
				}else{
					pssField.setText("Please input password");
				}
			}else{
				idField.setText("Please input your ID");
			}	
		}
		
	}
	
	public static String check(Login l) {
		try {
			l.load();
		}  
		catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		String result = l.judgment();
		return result;
	}
}
