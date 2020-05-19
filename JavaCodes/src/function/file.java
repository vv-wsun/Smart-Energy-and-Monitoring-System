package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

/**
 * <p>description: contain all operation with files</p>
 * @author group37
 * @param 
 */
public class file {
	
	//read from user_info.txt
	public static ArrayList<User> userlist = new ArrayList<User>();
	public static ArrayList<Record> recordlist = new ArrayList<Record>();
	public static String path1 = "src/function/user_info.txt";
	public static String path2 = "src/function/time.txt";
	public static String path3 = "src/function/price.txt";
	
	
	/**  
	
	 * <p>Title: calMonth</p>  
	
	 * <p>Description: calculate month</p>  
	
	 * @return
	 * @throws FileNotFoundException  
	
	 */ 
	public static int calMonth() throws FileNotFoundException {
		String t = "src/function/time.txt";
		String date = "0";
		int month = 0;
		String wtf = null;
		RandomAccessFile time = new RandomAccessFile(t,"r");
		try {
			while((date = time.readLine()) != null) {
				wtf = date;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((Integer.parseInt(wtf))%30==0) {
			month = (Integer.parseInt(wtf)/30)-1;
		}
		else {
			month = Integer.parseInt(wtf)/30;
		}
		return month;
	}

	
	/**  
	
	 * <p>Title: read_from_bill</p>  
	
	 * <p>Description: get total bill list</p>  
	
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public static ArrayList<Record> read_from_bill(String energy) throws IOException{	
		ArrayList<Record> billlist= new ArrayList<>();
		
		String path = "src/function/"+energy+"bill.txt";
		RandomAccessFile br = new RandomAccessFile(path, "r");
		String str = null;
		while((str = br.readLine())!=null) {
			String[] result = str.split("\t");
				Record bill = new Record();
				bill.setUid(result[1]);
				bill.setDate(Integer.parseInt(result[0]));
				bill.setUsage(Double.parseDouble(result[2]));
				bill.setCost(Double.parseDouble(result[3]));
				billlist.add(bill);	
				System.out.println("F id: "+bill.getUid());
				System.out.println("F usage: "+bill.getUsage()+" cost: "+bill.getCost());
		}
		br.close();
		return billlist;
	}
	
	/**  
	
	 * <p>Title: write_into_bill</p>  
	
	 * <p>Description: write one bill </p>  
	
	 * @param r
	 * @param energy
	 * @param add  
	
	 */ 
	public static void write_into_bill(Record r,String energy,boolean add){							//write one user into user_info.txt
		String path = "src/function/"+energy+"bill.txt";
		File file = new File(path); 
		try{
			FileWriter fw = new FileWriter(file, add);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(r.getDate()+"\t"+r.getUid()+"\t"+r.getUsage()+"\t"+r.getCost()+"\t0\r\n");
			bw.close();
			fw.close();
		}catch(IOException e){
			 e.printStackTrace();  
		}	
	}
	
	/**  
	
	 * <p>Title: read_from_record</p>  
	
	 * <p>Description: get record list</p>  
	
	 * @param u
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public static ArrayList<Record> read_from_record(User u,String energy) throws IOException{	
		recordlist.clear();
		String path = "src/function/history/"+u.get_id()+".txt";
		RandomAccessFile br = new RandomAccessFile(path, "r");
		String str = null;
		while((str = br.readLine())!=null) {
			String[] result = str.split("\t");
			if(energy.equals("g")) {
				GRecord r = new GRecord(u);
				r.setDate(Integer.parseInt(result[0]));
				r.setUsage(Integer.parseInt(result[1]));
				recordlist.add(r);
			}			
			else if(energy.equals("e")) {
				ERecord r = new ERecord(u);
				r.setDate(Integer.parseInt(result[0]));
				r.setUsage(Integer.parseInt(result[2]));
				recordlist.add(r);
			}
		}
		br.close();
		return recordlist;
	}
	
	/**  
	
	 * <p>Title: read_from_user</p>  
	
	 * <p>Description: get user list</p>  
	
	 * @return
	 * @throws IOException  
	
	 */ 
	public static ArrayList<User> read_from_user() throws IOException{		//read user_info.txt, return userlist
		userlist.clear();
		RandomAccessFile br = new RandomAccessFile(path1, "r");
		String str = null;
		while((str = br.readLine())!=null) {
				String[] result = str.split("\t");
				User user = new User();
				user.set_id(result[0]);
				user.set_username(result[1]);
				user.set_email(result[2]);
				user.set_password(result[3]);
				user.set_gbudget(Double.parseDouble(result[4]));
				user.set_ebudget(Double.parseDouble(result[5]));
				userlist.add(user);
		}	
		br.close();
//		int i;
//		for(i=0;i<userlist.size();i++) {
//			System.out.println(userlist.get(i).get_id());
//		}
		return userlist;		
	}
	
	/**  
	
	 * <p>Title: write_into_user</p>  
	
	 * <p>Description: write one user </p>  
	
	 * @param user
	 * @param add  
	
	 */ 
	public static void write_into_user(User user,boolean add){							//write one user into user_info.txt
		File file = new File(path1); 
		try{
			FileWriter fw = new FileWriter(file, add);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(user.get_id()+"\t"+user.get_username()+"\t"+user.get_email()+"\t"+user.get_password()+"\t"+user.get_gbudget()+"\t"+user.get_ebudget()+"\r\n");
			bw.close();
			fw.close();
		}catch(IOException e){
			 e.printStackTrace();  
		}	
	}
	
	/**  
	
	 * <p>Title: write_user</p>  
	
	 * <p>Description: write total user list</p>  
	
	 * @param user
	 * @throws IOException  
	
	 */ 
	public static void write_user(User user) throws IOException{			//write whole user_info.txt
		File file = new File(path1); 
//		File temp = new File("src/function/temp.txt");
		userlist.clear();
		userlist = read_from_user();
		for (int i = 0; i < userlist.size(); i++) {				//remove original user in userlist
	        if(userlist.get(i).get_id().equals(user.get_id())){
	            userlist.remove(i);
	        }
	    }
		userlist.add(user);
		write_into_user(userlist.get(0),false);
		for(int i = 1; i < userlist.size(); i++) {
			User u = new User();
			u = userlist.get(i);
			write_into_user(u,true);
		}
	}
	
	/**  
	
	 * <p>Title: read_from_time</p>  
	
	 * <p>Description: get current time</p>  
	
	 * @return
	 * @throws IOException  
	
	 */ 
	public static String read_from_time() throws IOException{		//read time.txt, return latest time
		String time = null;
		File file = new File(path2);
		RandomAccessFile br = new RandomAccessFile(file, "r");
		String str = null;
		while((str = br.readLine()) != null) {
			time = str;// Get latest date
		}
		br.close();	
		return time;
	}
	/**  
	
	 * <p>Title: write_into_time</p>  
	
	 * <p>Description: write new time</p>  
	
	 * @param time
	 * @throws IOException  
	
	 */ 
	public static void write_into_time(String time) throws IOException{							//write new time into time.txt
		File file = new File(path2);
		FileOutputStream in = new FileOutputStream(file,true);  
		byte bt[] = new byte[1024];
		bt = time.getBytes();
		in.write(bt);
		in.write("\r\n".getBytes());
		in.close();
	}
	
	/**  
	
	 * <p>Title: write_price</p>  
	
	 * <p>Description: write new price</p>  
	
	 * @param time
	 * @param price
	 * @param type
	 * @throws IOException  
	
	 */ 
	public static void write_price(String time,String price,String type) throws IOException{			//write price.txt
		File file = new File(path3);
		byte bt[] = new byte[1024];
		try {
			FileOutputStream in = new FileOutputStream(file,true);
			String[] Price = read_from_price();
			if(type.equals("e")){
				bt = time.getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = price.getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = Price[1].getBytes();
				in.write(bt);
				in.write("\r\n".getBytes());
			}
			else if(type.equals("g")){
				bt = time.getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = Price[0].getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = price.getBytes();
				in.write(bt);
				in.write("\r\n".getBytes());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**  
	
	 * <p>Title: read_from_price</p>  
	
	 * <p>Description: get latest price</p>  
	
	 * @return  
	
	 */ 
	public static String[] read_from_price(){			//read from price.txt, return latest price
		File file = new File(path3);
		String[] price = new String[2];
		try{  
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String s = null;  
            while((s = br.readLine())!=null){   
            	String[] result = s.split("\t");
            	price[0]=result[1];	
            	price[1]=result[2];
            }  
            br.close();      
        }catch(IOException e){  
            e.printStackTrace();  
        }   
		return price;
	}
	
	/**  
	
	 * <p>Title: find_price</p>  
	
	 * <p>Description: find price</p>  
	
	 * @param t
	 * @param energy
	 * @return  
	
	 */ 
	public static double find_price(int t,String energy){
		ArrayList<String> pricelist = new ArrayList<String>();
		double price =0;
		File file = new File(path3);
		try{  
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String s = null;  
            while((s = br.readLine())!=null){   
            	String[] result = s.split("\t");
            	if(energy.equals("g")) {
            		pricelist.add(result[0]);
            		pricelist.add(result[1]);
            	}
            	else if(energy.equals("e")) {
            		pricelist.add(result[0]);
            		pricelist.add(result[2]);
            	}	
            }  
            br.close();  
            
            for(int i=0;i<pricelist.size();i=i+2) {
    			if(t>=Integer.parseInt(pricelist.get(i))) {
    				if(energy.equals("g")) {
    					price = Double.parseDouble(pricelist.get(i+1));
    				}
    				if(energy.equals("e")) {
    					price = Double.parseDouble(pricelist.get(i+1));
    				}
    			}
    		}
        }catch(IOException e){  
            e.printStackTrace();  
        } 
//		System.out.print(energy+" price "+price);
		return price;
	}
//	public static void main(String[] args) throws IOException{
//		
//		write_price("30","23","e");
//		write_price("30","12","g");
////		System.out.println(price);
//	}
	
}
