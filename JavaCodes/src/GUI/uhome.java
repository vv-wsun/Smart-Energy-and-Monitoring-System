package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import function.Manage;
import function.Time;
import function.Creator;
import function.file;
import function.User;
	public class uhome extends JFrame implements ActionListener {
		
		private static JButton btn1 = new JButton("Change");
		private static JButton btn2 = new JButton("Change");
		private static JButton btnNewButton_1 = new JButton("History record");
//		private static JButton btnNewButton_2 = new JButton("Bill");
		private static JButton btnGoBack = new JButton("Check tariff");
		private static JButton btnNewButton_11 = new JButton("Change Password?");
		private static User u;
		//
		double costss = 0;
		double budgetss = 0;
		
		double costsg = 0;
		double budgetsg = 0;
		
		ArrayList<User> userlist = new ArrayList<User>();
		Manage m = new Manage();
		
//		String time = file.read_from_time();;
		
		public uhome(String id) throws IOException, InterruptedException {
//			creator.generate_record(id, time);
			Time t = new Time(id);
			t.start();
			userlist = file.read_from_user();
			u = m.search_id(userlist,id);
			System.out.println("in UHome"+id);
//			u.generate_bill("e");
//			u.generate_bill("g");
			go();
			btn1.addActionListener(this);
			btn2.addActionListener(this);
			btnGoBack.addActionListener(this);
			btnNewButton_1.addActionListener(this);
//			btnNewButton_2.addActionListener(this);
			btnNewButton_11.addActionListener(this);
		}

		public void go() throws FileNotFoundException, IOException{
			this.setTitle("User Home");
			
			this.getContentPane().setLayout(null);
			this.getContentPane().setBackground(Color.WHITE);
			
			MyDrawPanel drawPanel = new MyDrawPanel();
			drawPanel.setSize(30,30);
			
			MyDrawPanel2 drawPanel2 = new MyDrawPanel2();
			drawPanel2.setSize(30,30);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(50, 43, 675, 50);
			this.getContentPane().add(panel);
			panel.setLayout(null);
			
			JTextPane txtId = new JTextPane();
			txtId.setFont(new Font("Arial", Font.PLAIN, 15));
			txtId.setBounds(43, 19, 219, 44);
			txtId.setText("Dear " + u.get_username());
			panel.add(txtId);
			
			JPanel panel_1 = new JPanel(new GridLayout(1,3));
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(400, 150, 675, 50);
			this.getContentPane().add(panel_1);
			
			
			JLabel lab1 = new JLabel("Usage");
			lab1.setFont(new Font("Arial", Font.PLAIN, 30));
			lab1.setBackground(SystemColor.activeCaption);
			panel_1.add(lab1);
	    
			
			JLabel lab2 = new JLabel("Cost");
			lab2.setFont(new Font("Arial", Font.PLAIN, 30));
			lab2.setBackground(SystemColor.activeCaption);
			panel_1.add(lab2);
			
			JLabel lab3 = new JLabel("Budget");
			lab3.setFont(new Font("Arial", Font.PLAIN, 30));
			lab3.setBackground(SystemColor.activeCaption);
			panel_1.add(lab3);
			
			JPanel panel_11 = new JPanel();
			panel_11.setBackground(Color.WHITE);
			panel_11.setBounds(100, 250, 1050, 200);
			this.getContentPane().add(panel_11);
			panel_11.setLayout(null);
			
			JLabel lab4 = new JLabel("Electricity:");
			lab4.setBounds(70,10, 150, 40);
			lab4.setFont(new Font("Arial", Font.PLAIN, 30));
			lab4.setBackground(SystemColor.activeCaption);
			panel_11.add(lab4);
			
		//	E usage
			JLabel lab41 = new JLabel(String.valueOf(u.getCurERecord().calUsage())+" Kwh");
			lab41.setBounds(295,10, 150, 40);
			lab41.setFont(new Font("Arial", Font.PLAIN, 15));
			lab41.setBackground(SystemColor.activeCaption);
			panel_11.add(lab41);
			
		//	E cost
			costss = u.getCurERecord().calCost();
			JLabel lab42 = new JLabel(u.getCurERecord().calCost()+" pounds");
		//	JLabel lab42 = new JLabel("10pounds");
			lab42.setBounds(525,10, 150, 40);
			lab42.setFont(new Font("Arial", Font.PLAIN, 15));
			lab42.setBackground(SystemColor.activeCaption);
			panel_11.add(lab42);
			
		//	E budget
			budgetss = Double.parseDouble(u.read_budget("e"));
			JLabel lab43 = new JLabel(u.read_budget("e")+" pounds");
			lab43.setBounds(770,10, 100, 40);
			lab43.setFont(new Font("Arial", Font.PLAIN, 15));
			lab43.setBackground(SystemColor.activeCaption);
			panel_11.add(lab43);
			
			
			btn1.setFont(new Font("Arial", Font.PLAIN, 30));
			btn1.setBackground(SystemColor.activeCaption);
			btn1.setBounds(900,10, 150, 40);
			panel_11.add(btn1);
			
			JLabel lab5 = new JLabel("Gas:");
			lab5.setBounds(70,100, 150, 40);
			lab5.setFont(new Font("Arial", Font.PLAIN, 30));
			lab5.setBackground(SystemColor.activeCaption);
			panel_11.add(lab5);
			
			//G usage
			JLabel lab51 = new JLabel(String.valueOf(u.getCurGRecord().calUsage())+" Kwh");
			lab51.setBounds(295,100, 150, 40);
			lab51.setFont(new Font("Arial", Font.PLAIN, 15));
			lab51.setBackground(SystemColor.activeCaption);
			panel_11.add(lab51);
			
			//G cost
			costsg = u.getCurGRecord().calCost();
			JLabel lab52 = new JLabel(u.getCurGRecord().calCost()+" pounds");
			//JLabel lab52 = new JLabel("10 pounds");
			lab52.setBounds(525,100, 150, 40);
			lab52.setFont(new Font("Arial", Font.PLAIN, 15));
			lab52.setBackground(SystemColor.activeCaption);
			panel_11.add(lab52);
			
			//G budget
			budgetsg = Double.parseDouble(u.read_budget("g"));
			JLabel lab53 = new JLabel(u.read_budget("g")+" pounds");
			lab53.setBounds(770,100, 100, 40);
			lab53.setFont(new Font("Arial", Font.PLAIN, 15));
			lab53.setBackground(SystemColor.activeCaption);
			panel_11.add(lab53);
			
			
			btn2.setFont(new Font("Arial", Font.PLAIN, 30));
			btn2.setBackground(SystemColor.activeCaption);
			btn2.setBounds(900,100, 150, 40);
			panel_11.add(btn2);
			
		
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setBounds(200, 478, 800, 63);
			this.getContentPane().add(panel_2);
			
			
			btnNewButton_1.setBackground(SystemColor.activeCaption);
			btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 30));
			btnNewButton_1.setBounds(30, 19, 200, 40);

			panel_2.setLayout(null);
			panel_2.add(btnNewButton_1);

//			btnNewButton_2.setBackground(SystemColor.activeCaption);
//			btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 30));
//			btnNewButton_2.setBounds(270, 19, 200, 40);
//			panel_2.add(btnNewButton_2);
//			
			
			btnGoBack.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				}
			});
			btnGoBack.setBackground(SystemColor.activeCaption);
			btnGoBack.setFont(new Font("Arial", Font.PLAIN, 30));
			btnGoBack.setBounds(520, 19, 200, 40);
			panel_2.add(btnGoBack);
	
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setBounds(10, 634, 500, 59);
			this.getContentPane().add(panel_3);
			
			btnNewButton_11.setBackground(SystemColor.activeCaption);
			btnNewButton_11.setFont(new Font("Arial", Font.PLAIN, 30));
			btnNewButton_11.setBorderPainted(false);
			panel_3.add(btnNewButton_11);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setBounds(36, 258, 97, 93);
			getContentPane().add(panel_4);
			panel_4.add(drawPanel);
			panel_4.setLayout(null);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setBounds(36, 370, 97, 93);
			getContentPane().add(panel_5);
			panel_5.add(drawPanel2);
			panel_5.setLayout(null);
			
			
			
			
			
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(1500,1000);
			this.setVisible(true);
			
			
		}
		
		class MyDrawPanel extends JPanel{
			public void paintComponent(Graphics g){
				Graphics2D g1 = (Graphics2D) g;
				
				if(costss > budgetss){
				g1.setColor(Color.red);
				}else{
					g1.setColor(Color.green);
					
				}
				
				g1.fillOval(10, 10, 10, 10);
				
			}
		}
		
		class MyDrawPanel2 extends JPanel{
			public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D) g;
				if(costsg > budgetsg){
					g2.setColor(Color.red);
					
				}else{
					g2.setColor(Color.green);
				}
				g2.fillOval(10, 10, 10, 10);
			}
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == uhome.btn1){    //change elec
				this.setVisible(false);
				try {
					new change_budget_e(u);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if(e.getSource() == uhome.btn2){   //change gas 
				this.setVisible(false);
				try {
					new change_budget_g(u);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == uhome.btnNewButton_1){   //history record
				this.setVisible(false);
				new ViewHistory(u);
			}
//			else if(e.getSource() == uhome.btnNewButton_2){   //bill
//				this.setVisible(false);
//				new ebill();
//			}
			else if(e.getSource() == uhome.btnNewButton_11){   //change pass
				this.setVisible(false);
				new ChangePass(u);
			}
			else if(e.getSource() == uhome.btnGoBack){   //tariff
				this.setVisible(false);
				new CheckTariffGUI(u);  
			}
			
		}
	}