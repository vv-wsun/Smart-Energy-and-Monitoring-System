package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import function.User;


public class change_budget_g extends JFrame implements ActionListener {
	private static JButton button1 = new JButton("OK");
	private static JButton button2 = new JButton("Go back");
	private static JButton button3 = new JButton("Home");
	static JTextField text1;
	static User u;
	
	public change_budget_g(User u) throws IOException {
		this.u = u;
		this.setSize(1000,800);
		this.setTitle("change budget (gas)");
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initFrame();
		
		text1.addKeyListener(new KeyAdapter(){  
  			 public void keyTyped(KeyEvent e) {  
   			 int keyChar = e.getKeyChar();                 
          			 if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
          			 }
			 else{  
               		 e.consume(); 						 //can only type in numerical value  
           		 }  
   		 }  
  		 });
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);           //������������������������������������������������������������������������������������������?
	}
	
	private void initFrame() throws IOException{
		JPanel QueryPanel1 = new JPanel();
		QueryPanel1.setLayout(null);
		QueryPanel1.setBackground(Color.WHITE);
		QueryPanel1.setBounds(134, 20, 900, 500);
		this.getContentPane().add(QueryPanel1);
		
		JTextPane textp1 = new JTextPane();
		textp1.setFont(new Font("Arial", Font.PLAIN, 30));
		textp1.setBounds(140, 20, 450, 44);
		textp1.setText("Please enter your new budget!");
		QueryPanel1.add(textp1);
		
		button3.setBackground(SystemColor.activeCaption);   //home
		button3.setFont(new Font("Arial", Font.PLAIN, 30));
		button3.setBounds(700, 0, 153, 31);
		QueryPanel1.add(button3);
		
		JTextPane textp2 = new JTextPane();
		textp2.setFont(new Font("Arial", Font.PLAIN, 30));
		textp2.setBounds(180, 150, 150, 44);
		textp2.setText("Current : ");
		QueryPanel1.add(textp2);
		
		JTextPane textp3 = new JTextPane();
		textp3.setFont(new Font("Arial", Font.PLAIN, 30));
		textp3.setBounds(450, 150, 250, 44);
		textp3.setText(u.read_budget("g"));				
		QueryPanel1.add(textp3);
		
		JTextPane textp4 = new JTextPane();
		textp4.setFont(new Font("Arial", Font.PLAIN, 30));
		textp4.setBounds(220, 300, 150, 44);
		textp4.setText("New : ");
		QueryPanel1.add(textp4);
		
		text1 = new JTextField(10);
		text1.setBounds(450, 300, 250, 44);
		text1.setBackground(SystemColor.activeCaption);   
		QueryPanel1.add(text1);
		
		/*
		JPanel Panel2 = new JPanel();
	    Panel2.setLayout(null);
		Panel2.setBackground(Color.WHITE);
		Panel2.setBounds(134, 478, 675, 63);
		*/
		button1.setBackground(SystemColor.activeCaption);   //ok
		button1.setFont(new Font("Arial", Font.PLAIN, 30));
		button1.setBounds(300, 530, 150, 31);		
		this.getContentPane().add(button1);
		
		button2.setBackground(SystemColor.activeCaption);   //go back
		button2.setFont(new Font("Arial", Font.PLAIN, 30));
		button2.setBounds(550, 530, 150, 31);
		this.getContentPane().add(button2);
		//Panel2.add(button2);
		
		//this.getContentPane().add(Panel2);
		
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == change_budget_g.button1){    //OK
				
				String budget = text1.getText();
				try {
					
					u.set_budget("g", budget);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new success();

			}
			else if(e.getSource() == change_budget_g.button2){   //go back
				this.dispose();
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
			else if(e.getSource() == change_budget_g.button3){   //home
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

	
//	public String getNum(){
//		Random rand = new Random();
//		String num = String.valueOf(rand.nextInt(100) + 1);
//		return num;
//	}
//	
//	public static void main(String[] args) throws IOException{
//		new change_budget_g();
//	}
//	
	
}
