package GUI;
import javax.swing.*;

import function.Manage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MHomeGUI extends JFrame implements ActionListener {
	private static JButton btnManageUser = new JButton("Manage User");
	private static JButton btnNewButton = new JButton("Set Tariff");
	private static JButton btnCheckBill = new JButton("Check Bill");
//	private static JButton btnNewButton_1 = new JButton("you have received a message!");
	private static Manage m = new Manage();
	
	public MHomeGUI() throws IOException{
		go();
		btnManageUser.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnCheckBill.addActionListener(this);
//		btnNewButton_1.addActionListener(this);
		rcvBill("e");
		rcvBill("g");
	}
	public void rcvBill(String energy) throws IOException {
		ArrayList<String> info = m.find_unread(energy);
		new RcvRecord(info,energy);
		info = m.change_flag(energy);
		m.rewrite_bill(energy, info);
	}
	public void go(){
		
		this.setTitle("Provider Home Page");
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
		this.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(210, 19, 499, 78);
		this.getContentPane().add(panel);
		
		JTextPane txt1 = new JTextPane();
		txt1.setFont(new Font("Arial", Font.PLAIN, 40));
		txt1.setText("Welcome back, Provider! ");
		panel.add(txt1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(330, 116, 240, 51);
		this.getContentPane().add(panel_1);
		
		JTextPane txt2 = new JTextPane();
		txt2.setFont(new Font("Arial", Font.PLAIN, 40));
		txt2.setText("Smart Life");
		panel_1.add(txt2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(346, 287, 266, 271);
		panel_2.setBackground(Color.WHITE);
		this.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		btnManageUser.setBounds(8, 5, 214, 37);
		btnManageUser.setFont(new Font("Arial", Font.PLAIN, 25));
		btnManageUser.setBackground(SystemColor.activeCaption);
		panel_2.add(btnManageUser);

		btnNewButton.setBounds(8, 85, 214, 37);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNewButton.setBackground(SystemColor.activeCaption);
		panel_2.add(btnNewButton);
		

		btnCheckBill.setBounds(8, 159, 214, 37);
		btnCheckBill.setBackground(SystemColor.activeCaption);
		btnCheckBill.setForeground(new Color(0, 0, 0));
		btnCheckBill.setFont(new Font("Arial", Font.PLAIN, 25));
		panel_2.add(btnCheckBill);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(486, 634, 459, 59);
		this.getContentPane().add(panel_3);
		
//		btnNewButton_1.setBackground(SystemColor.activeCaption);
//		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 30));
//		panel_3.add(btnNewButton_1);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000,800);
		this.setVisible(true);
	}
	public static void main(String[] args) throws IOException{
		MHomeGUI home = new MHomeGUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == MHomeGUI.btnManageUser){    //manage user

			this.setVisible(false);
			try {
				new ManageUserGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource() == MHomeGUI.btnNewButton){   //set tariff
			this.setVisible(false);
			new SetTariffGUI();
		}
		else if(e.getSource() == MHomeGUI.btnCheckBill){   // check bill
			this.setVisible(false);
			new ViewBill();
		}
//		else if(e.getSource() == MHomeGUI.btnNewButton_1){   // check bill
////			this.setVisible(false);
//			new MessageBill();
//		}

	}
}
