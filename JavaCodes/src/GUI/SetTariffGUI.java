package GUI;

import javax.swing.*;
import function.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SetTariffGUI extends JFrame implements ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private static JButton btnSet = new JButton("Set");				//elec
	private static JButton btnCancel = new JButton("Cancel");		//elec
	private static JButton button = new JButton("Set");				//gas
	private static JButton button_1 = new JButton("Cancel");		//gas
	
	private JTextField textField_2;
	private JTextField textField_3;
	private static JButton btnGoBack = new JButton("go back");		//go back  ���������������������������������������������button
	
	Manage m = new Manage();
	
	public SetTariffGUI(){
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 25));
		go();
		btnSet.addActionListener(this);
		btnCancel.addActionListener(this);
		button.addActionListener(this);
		button_1.addActionListener(this);
		btnGoBack.addActionListener(this);
	}
	public void go(){
		this.setTitle("Set Tariff Page");
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(138, 19, 578, 171);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnCurrentTariff = new JTextPane();
		txtpnCurrentTariff.setBounds(7, 5, 286, 41);
		txtpnCurrentTariff.setFont(new Font("Arial", Font.PLAIN, 30));
		txtpnCurrentTariff.setText("Current elec Tariff:");
		panel.add(txtpnCurrentTariff);
		
		textField = new JTextField();				//current  elec
		textField.setBounds(298, 5, 195, 41);
		textField.setFont(new Font("Arial", Font.PLAIN, 30));
		String[] Price = file.read_from_price();
		textField.setText(Price[0]);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnSetNewTariff = new JTextPane();
		txtpnSetNewTariff.setFont(new Font("Arial", Font.PLAIN, 30));
		txtpnSetNewTariff.setText("Set New elec Tariff:");
		txtpnSetNewTariff.setBounds(7, 99, 286, 72);
		panel.add(txtpnSetNewTariff);
		
		textField_1 = new JTextField();				//new  elec
		textField_1.setFont(new Font("Arial", Font.PLAIN, 30));
		textField_1.setBounds(298, 99, 195, 42);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(194, 576, 752, 68);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnSet.setBackground(SystemColor.activeCaption);
		btnSet.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSet.setBounds(17, 20, 131, 31);
		panel_1.add(btnSet);
		
		btnCancel.setBackground(SystemColor.activeCaption);
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 25));
		btnCancel.setBounds(324, 20, 131, 31);
		panel_1.add(btnCancel);
		
		btnGoBack.setBackground(SystemColor.activeCaption);
		btnGoBack.setFont(new Font("Arial", Font.PLAIN, 25));
		btnGoBack.setBounds(607, 20, 131, 31);
		panel_1.add(btnGoBack);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(138, 337, 578, 151);
		getContentPane().add(panel_2);
		
		JTextPane txtpnCurrentGasTariff = new JTextPane();
		txtpnCurrentGasTariff.setText("Current gas Tariff:");
		txtpnCurrentGasTariff.setFont(new Font("Arial", Font.PLAIN, 30));
		txtpnCurrentGasTariff.setBounds(7, 5, 286, 41);
		panel_2.add(txtpnCurrentGasTariff);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 30));		//current gas
		textField_2.setColumns(10);
		textField_2.setBounds(298, 5, 195, 41);
		textField_2.setText(Price[1]);
		panel_2.add(textField_2);
		
		JTextPane txtpnSetNewGas = new JTextPane();
		txtpnSetNewGas.setText("Set New gas Tariff:");
		txtpnSetNewGas.setFont(new Font("Arial", Font.PLAIN, 30));
		txtpnSetNewGas.setBounds(7, 99, 286, 72);
		panel_2.add(txtpnSetNewGas);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 30));   //new gas
		textField_3.setColumns(10);
		textField_3.setBounds(298, 99, 195, 42);
		panel_2.add(textField_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(194, 236, 522, 68);
		getContentPane().add(panel_3);
		

		button.setFont(new Font("Arial", Font.PLAIN, 25));
		button.setBackground(SystemColor.activeCaption);
		button.setBounds(17, 20, 131, 31);
		panel_3.add(button);
		

		button_1.setFont(new Font("Arial", Font.PLAIN, 25));
		button_1.setBackground(SystemColor.activeCaption);
		button_1.setBounds(334, 20, 131, 31);
		panel_3.add(button_1);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000,800);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SetTariffGUI.button){    //elec set
			try {
				String price = textField_1.getText();
				//String path = "function/price_e.txt";		//elec price
				m.set_price(price,"e");
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//this.setVisible(false);
			new messageSuccess();	
			
		}
		else if(e.getSource() == SetTariffGUI.button_1){   //elec cancel
			
			textField_1.setText("");
		}
		else if(e.getSource() == SetTariffGUI.btnSet){   //gas    set
		    try {
				String price = textField_3.getText();
				String path = "function/price_g.txt";  //gas price
				m.set_price(price,"g");
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//this.setVisible(false);
			new messageSuccess();	
		}
		else if(e.getSource() == SetTariffGUI.btnCancel){   //gas   cancel
			textField_3.setText("");
		}
		else if(e.getSource() == SetTariffGUI.btnGoBack){   //go back
			this.setVisible(false);
			try {
				new MHomeGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	public static void main(String[] args){
		SetTariffGUI  s = new SetTariffGUI();
	}
}
