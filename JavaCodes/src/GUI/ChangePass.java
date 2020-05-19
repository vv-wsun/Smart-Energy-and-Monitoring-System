package GUI;
import function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

	public class ChangePass extends JFrame implements ActionListener {
		private static JButton btnManageUser = new JButton("OK");
		private static JButton btnGoBack = new JButton("Go Back");
		private static JTextField textField;
		static User u;
		public ChangePass(User u){
			this.u = u;
			this.setTitle("Change Password");
			this.getContentPane().setBackground(Color.WHITE);
			this.getContentPane().setLayout(null);
			this.setBackground(Color.WHITE);
			go();
			
			btnManageUser.addActionListener(this);
			btnGoBack.addActionListener(this);
			
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(1000,800);
			this.setVisible(true); 
		}
		public void go(){
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(19, 100, 900, 100);
			this.getContentPane().add(panel);
			
			JTextPane txt1 = new JTextPane();
			txt1.setFont(new Font("Arial", Font.PLAIN, 40));
			txt1.setText("Please enter your new Password!\n");
			panel.add(txt1);
			
			JPanel panel_1 = new JPanel(new GridLayout(1,1));
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(250, 300, 400, 40);
			this.getContentPane().add(panel_1);
			
			JLabel lab1 = new JLabel("New:");
		        //lab1.setBounds(100, 300, 10, 50);
		    lab1.setFont(new Font("Arial", Font.PLAIN, 30));
		    lab1.setBackground(SystemColor.activeCaption);
		    panel_1.add(lab1);
		    
		    textField = new JTextField();
	        //	textField.setBounds(150, 250, 10, 30);
	        textField.setFont(new Font("Arial", Font.PLAIN, 30));
	        panel_1.add(textField);
	        textField.setColumns(10);
	            
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(100, 450, 1000, 100);
			panel_2.setBackground(Color.WHITE);
			this.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			
	
	        btnManageUser.setBounds(100, 5, 200, 37);
	        btnManageUser.setFont(new Font("Arial", Font.PLAIN, 25));
	        btnManageUser.setBackground(SystemColor.activeCaption);
	        panel_2.add(btnManageUser); 

	        btnGoBack.setBounds(400, 5, 200, 37);
	        btnGoBack.setFont(new Font("Arial", Font.PLAIN, 25));
	        btnGoBack.setBackground(SystemColor.activeCaption);
	        panel_2.add(btnGoBack); 
	        

		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ChangePass.btnManageUser){    //OK
				String new_pss = textField.getText();
				if(new_pss != null) {
					u.set_password(new_pss);
					try {
						file.write_user(u);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					textField.setText("Please input new password");
				}
//				this.setVisible(false);
				new success();
				
			}
			else if(e.getSource() == ChangePass.btnGoBack){   //go back
				this.setVisible(false);
				try {
					new uhome(u.get_id());
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

		}
		
//		public static void main(String[] args){
//			new ChangePass(u);
//		}

}
