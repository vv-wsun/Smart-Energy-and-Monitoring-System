package function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * <p>description:Entity class user</p>
 * @author group37
 * @param 
 */
public class User {
	private String id;	
	private String user_name;
	private String password;
	private String email;
	private double ebudget;
	private double gbudget;
	private ERecord curERecord;
	private GRecord curGRecord;
	
	public User() {
//		System.out.println("initial "+this.id);
		this.curERecord=new ERecord(this);
//		System.out.println("In USER "+this.curERecord.getUid());
		this.curGRecord=new GRecord(this);
		this.set_ebudget(0.0);
		this.set_gbudget(0.0);
	}

	
	public ERecord getCurERecord() {
		return curERecord;
	}


	public GRecord getCurGRecord() {
		return curGRecord;
	}


	public void setCurERecord(ERecord curERecord) {
		this.curERecord = curERecord;
	}


	public void setCurGRecord(GRecord curGRecord) {
		this.curGRecord = curGRecord;
	}


	public double get_ebudget() {
		return ebudget;
	}
	public double get_gbudget() {
		return gbudget;
	}
	public void set_ebudget(double ebudget) {
		this.ebudget = ebudget;
	}
	public void set_gbudget(double gbudget) {
		this.gbudget = gbudget;
	}
	public void set_id(String id) {
		this.id = id;
	}
	public void set_username(String username) {
		this.user_name = username;
	}
	public void set_password(String password) {
		this.password = password;
	}
	public void set_email(String email) {
		this.email = email;
	}
	public String get_id() {
		return this.id;
	}
	public String get_username() {
		return this.user_name;
	}
	public String get_password() {
		return this.password;
	}
	public String get_email() {
		return this.email;
	}

	/**  
	
	 * <p>Title: set_budget</p>  
	
	 * <p>Description: change budget</p>  
	
	 * @param energy
	 * @param budget
	 * @throws IOException  
	
	 */ 
	public void set_budget(String energy,String budget) throws IOException {   		//change elec budget, write user_info.txt
		ArrayList<User> userlist = new ArrayList<User>();
		userlist = file.read_from_user();
		User my = new User();
		for(int i=0;i<userlist.size();i++){
			if(userlist.get(i).get_id().equals(this.get_id())){
				my = userlist.get(i);
			}
		}
		if(energy.equals("e"))
			my.set_ebudget(Double.parseDouble(budget));
		else if(energy.equals("g"))
			my.set_gbudget(Double.parseDouble(budget));
		file.write_user(my);
	}
	
	/**  
	
	 * <p>Title: read_budget</p>  
	
	 * <p>Description: get current budget</p>  
	
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public String read_budget(String energy) throws IOException{		//read from user_info.txt, return elec budget
		ArrayList<User> userlist = new ArrayList<User>();
		userlist = file.read_from_user();
		String budget = null;			
		for(int i=0;i<userlist.size();i++){
			if(this.get_id().equals(userlist.get(i).get_id())) {
				if(energy.equals("e"))
					budget= Double.toString(userlist.get(i).get_ebudget());
				else if(energy.equals("g"))
					budget= Double.toString(userlist.get(i).get_gbudget());
			}
				
		}
		return budget;		
	}
	
	/**  
	
	 * <p>Title: generate_bill</p>  
	
	 * <p>Description: generate a bill</p>  
	
	 * @param id
	 * @param energy
	 * @throws FileNotFoundException
	 * @throws IOException  
	
	 */ 
	public static void generate_bill(String id,String energy) throws FileNotFoundException, IOException{
		ArrayList<User> userlist = file.read_from_user();
		User u = Manage.search_id(userlist,id);
		
		int month = file.calMonth();
		double use = 0;
		double cost = 0;
		String path = "src/function/"+energy+"bill.txt";
		if(energy.equals("e")) {
			ERecord r = new ERecord(u);
			r.setUid(u.get_id());
			use = r.calUsage();
			cost = r.calCost();
		}
		else if(energy.equals("g")) {
			GRecord r = new GRecord(u);
			r.setUid(u.get_id());
			use = r.calUsage();
			cost = r.calCost();			
		}
		File file = new File(path);
		byte bt[] = new byte[1024];
    	try {  
    		FileOutputStream in = new FileOutputStream(file,true);  
    		try {
    			bt = String.valueOf(month).getBytes();
	    		in.write(bt);
	    		in.write("	".getBytes());	
    			bt = u.get_id().getBytes();
	    		in.write(bt);
	    		in.write("	".getBytes());	
    			bt = String.valueOf(use).getBytes();
    			in.write(bt);
    			in.write("	".getBytes());
    			bt = String.valueOf(cost).getBytes();
    			in.write(bt);
    			in.write("	".getBytes());
    			in.write("0".getBytes());
    			in.write("\r\n".getBytes());
    			in.close();
    		}catch (IOException e) {   
    			e.printStackTrace();  
    		}
    	} catch (FileNotFoundException e) {  
    		e.printStackTrace();  
    	}
	}
//  }
}
