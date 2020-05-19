package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.Record;
import function.User;

public class History_Table extends JFrame implements ActionListener{
	private DefaultTableModel model = null;
	private JTable table = null;
	//private static JButton button1 = new JButton("Go back");
	//private static JButton button2 = new JButton("Home");
	static User u;
	private ArrayList<Record> list = new ArrayList<Record>();
	JButton btnGoback = new JButton("goback");
	JButton btnHome = new JButton("home");
	public History_Table(String energy,String period,User u,ArrayList<Record> recordlist){
		this.list = recordlist;
		this.u = u;
		this.setSize(1000,800);
		this.setTitle(energy+" history for "+period);
		this.getContentPane().setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		initFrame();
		
//		button1.addActionListener(this);
//		button2.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null);           
		
	}
	
	private void initFrame(){
		getContentPane().setLayout(null);
		JPanel QueryPanel1 = new JPanel();
		QueryPanel1.setBounds(0, 777, 1000, 1);
		QueryPanel1.setBackground(Color.WHITE);
		this.getContentPane().add(QueryPanel1);
		
//		button2.setBackground(SystemColor.activeCaption);   //home
//		button2.setFont(new Font("Arial", Font.PLAIN, 30));
//		button2.setBounds(357,5,125,44);
//		button1.setBackground(SystemColor.activeCaption);   //go back
//		button1.setFont(new Font("Arial", Font.PLAIN, 30));
//		button1.setBounds(487,5,156,44);
//		button1.addActionListener(this);
//		button2.addActionListener(this);
//		QueryPanel1.setLayout(null);
//		
//		QueryPanel1.add(button2);
//		QueryPanel1.add(button1);
		
		JPanel Panel1 = new JPanel();
		Panel1.setLayout(new FlowLayout());			
		
		String[][] datas = {};
	    String[] titles = {"No.", "date", "Usage(kWh)"};
	    model = new DefaultTableModel(datas, titles);
	    
	    for(int i=0; i<list.size(); i++){   					
	    	model.addRow(new String[]{Integer.toString(i+1),Integer.toString(list.get(i).getDate()), Double.toString(list.get(i).getUsage())});
		}
	    
	    table = new JTable(model);	    
	    table.setShowGrid(false);     
	    
	    DefaultTableCellRenderer r = new DefaultTableCellRenderer();     
	    r.setHorizontalAlignment(JLabel.CENTER);     
	    table.setDefaultRenderer(Object.class, r);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0, 0, 1000, 420);
	    getContentPane().add(scrollPane);
	    
	    
	    btnGoback.addActionListener(this);
	    btnGoback.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	    btnGoback.setBounds(159, 516, 309, 118);
	    getContentPane().add(btnGoback);
	    
	    
	    btnHome.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	    btnHome.setBounds(579, 508, 288, 118);
	    btnHome.addActionListener(this);
	    getContentPane().add(btnHome);
//	    getContentPane().add(QueryPanel1);
	}
	
//	public String getNum(){
//		Random rand = new Random();
//		String num = String.valueOf(rand.nextInt(100) + 1);
//		return num;
//	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnGoback){    //Go back

				this.setVisible(false);
				new ViewHistory(u);
			}
			else if(e.getSource() == btnHome){   //home
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
	}
