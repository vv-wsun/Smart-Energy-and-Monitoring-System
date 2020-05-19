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
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import function.*;


public class ViewHistory extends JFrame implements ActionListener {
	private static JButton button1 = new JButton("OK");
	private static JButton button2 = new JButton("Go back");
	private static JButton button3 = new JButton("Home");
	static User u;
	
	String [] energy = {"electricity", "gas"};	
	private JComboBox comboBox1 = new JComboBox(energy);    //下拉�?

	String [] period = {"dayly", "weekly", "monthly"};	
	private JComboBox comboBox2 = new JComboBox(period);
	
	public ViewHistory(User u){
		this.u = u;
		this.setSize(1000,800);
		this.setTitle("change budget (gas)");
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
		
		JTextPane textp4 = new JTextPane();
		textp4.setFont(new Font("Arial", Font.PLAIN, 30));
		textp4.setBounds(190, 300, 150, 44);
		textp4.setText("Period: ");
		QueryPanel1.add(textp4);

		QueryPanel1.add(comboBox2);
		comboBox2.setBounds(450, 300, 150, 44);
		comboBox2.setFont(new Font("Arial", Font.PLAIN, 20));

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
			if(e.getSource() == ViewHistory.button1){    //OK
//				Record r = new Record(u);
				ArrayList<Record> recordlist = new ArrayList<Record>();
				String energy = (String) comboBox1.getSelectedItem();
				String period = (String)comboBox2.getSelectedItem();
				
				if(energy.equals("electricity")) {
					try {
						ERecord r = new ERecord(u);
						recordlist=r.get_record_list(u, period);
						this.setVisible(false);
						new History_Table(energy,period,u,recordlist);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(energy.equals("gas"))
					try {
						GRecord r = new GRecord(u);
						recordlist=r.get_record_list(u, period);
						this.setVisible(false);
						new History_Table(energy,period,u,recordlist);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				
			}
			else if(e.getSource() == ViewHistory.button2){   //go back
				this.setVisible(false);
				try {
					new uhome(u.get_id());
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
			else if(e.getSource() == ViewHistory.button3){   //home
				this.setVisible(false);
				try {
					new uhome(u.get_id());
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
//			new history_record(u);
//		}
}
