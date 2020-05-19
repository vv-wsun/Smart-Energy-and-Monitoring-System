package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import function.file;
import function.User;

public class CheckTariffGUI extends JFrame implements ActionListener {
	
	public static void main(String args[]) {
		User u = new User();
		CheckTariffGUI check = new CheckTariffGUI(u);
	}
	
	private static JButton home =new JButton("Home");
	private static JButton goback = new JButton("Go back");
	static User u;
	CheckTariffGUI(User u){
		this.u =u;
		this.setTitle("CheckTriff");
		this.setBounds(100, 100, 1000, 800);
		this.getContentPane().setBackground(Color.WHITE);
		Container cp = this.getContentPane();
		cp.setLayout(null);

		home.addActionListener(this);
		home.setBounds(850, 0, 100, 50);
		home.setBackground(SystemColor.activeCaption);
		cp.add(home);

		JLabel e = new JLabel("electricity: ");
		JLabel g = new JLabel("gas: ");
		String[] Price = file.read_from_price();
		JLabel op= new JLabel(Price[0]+" Pounds");
		JLabel op1= new JLabel(Price[1]+" Pounds");
		
		Font font  = new Font("Arial",Font.PLAIN,42);
		e.setBounds(100, 200, 300, 50);
		e.setFont(font);
		g.setBounds(100, 300, 300, 50);
		g.setFont(font);
		op.setBounds(400, 200, 500, 50);
		op.setFont(font);
		op1.setBounds(400, 300, 500, 50);
		op1.setFont(font);
		cp.add(e);
		cp.add(g);
		cp.add(op);
		cp.add(op1);
		
		goback.setBounds(420, 400, 150, 50);
		goback.setBackground(SystemColor.activeCaption);
		goback.addActionListener(this);
		cp.add(goback);
		this.setVisible(true);
	}

  @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == CheckTariffGUI.home){    //home

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
		else if(e.getSource() == CheckTariffGUI.goback){   //go back
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
}
