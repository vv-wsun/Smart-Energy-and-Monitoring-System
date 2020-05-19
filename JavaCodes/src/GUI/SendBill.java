package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SendBill extends JFrame{
	public SendBill(){
		go();
	}
	public void go(){
		this.setTitle("Tip");
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		JTextPane txtpnSetSuccessfully = new JTextPane();
		txtpnSetSuccessfully.setFont(new Font("Arial", Font.PLAIN, 30));
		txtpnSetSuccessfully.setText("Bill has been sent to user successfully!");
		txtpnSetSuccessfully.setBounds(114, 93, 277, 76);
		this.getContentPane().add(txtpnSetSuccessfully);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,350);
		this.setVisible(true);
	}
}
