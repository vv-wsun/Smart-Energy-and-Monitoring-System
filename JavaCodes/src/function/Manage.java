package function;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>description:Control class of provider</p>
 * @author group37
 * @param 
 */
public class Manage {	

	//check whether the ID is unique
	/**  
	
	 * <p>Title: check_unique</p>  
	
	 * <p>Description: check whether id is unique</p>  
	
	 * @param id
	 * @param userlist
	 * @return  
	
	 */ 
	public int check_unique(String id,ArrayList<User> userlist) {
		int flag = 0;
			for(int i = 0;i < userlist.size(); i ++){
				User user = userlist.get(i);
				if(user.get_id().equals(id)){
					//id = rand_id();
					flag = 1;
					break;
				}
				else flag = 0;
			}	
		return flag;
	}
	
	//add new user to txt file
	/**  
	
	 * <p>Title: add_user</p>  
	
	 * <p>Description: add_user</p>  
	
	 * @param u  
	
	 */ 
	public void add_user(User u){						
		String password = "000000";	//initial password
		u.set_password(password);
		//write into txt file
		file.write_into_user(u,true);	
	}
	
	//generate random ID
	public String rand_id() {			
		Random rand = new Random();
		String section1;
		String section2;
		section1 = String.valueOf(200+rand.nextInt(20));
		section2 = String.valueOf(200+rand.nextInt(20));
		String randid = "2015"+section1+section2;
		return randid;
	}
	
	//list all of userInfo
	/**  
	
	 * <p>Title: view_user_info</p>  
	
	 * <p>Description: list all of userInfo</p>  
	
	 * @param userlist  
	
	 */ 
	public void view_user_info(ArrayList<User> userlist){				
		 System.out.println("All of the user:");
		 for(int i = 0;i < userlist.size(); i ++){
	           User user = userlist.get(i);
	           System.out.println(user.get_id()+" "+user.get_username()+" "+user.get_password()+" "+user.get_email()); 
		 }
	}
	
	//search for user with ID
	/**  
	
	 * <p>Title: search_id</p>  
	
	 * <p>Description:search for user with ID </p>  
	
	 * @param userlist
	 * @param id
	 * @return  
	
	 */ 
	public static User search_id(ArrayList<User> userlist,String id){					
		User x = null;
		for(int i = 0;i < userlist.size(); i ++){
			User user = userlist.get(i);
			if(user.get_id().equals(id)){
				x = user;
				//System.out.println(user.get_id()+" "+user.get_username()+" "+user.get_password()+" "+user.get_email());
			}
		}	
		return x;
	}
	//remove user from userList
	/**  
	
	 * <p>Title: removeFromList</p>  
	
	 * <p>Description: remove user from userList</p>  
	
	 * @param u
	 * @param uList
	 * @return  
	
	 */ 
	public ArrayList<User> removeFromList(User u,ArrayList<User> uList) {
		int flag = 0;
		for(int i=0;i<uList.size();i++){  
            if(uList.get(i).equals(u)){  
                uList.remove(i);  
                flag = 1;
                break;
            }  
        } 
		return uList;
	}
	
	//remove user from txt file
	/**  
	
	 * <p>Title: removeFromInfo</p>  
	
	 * <p>Description: remove user from txt file</p>  
	
	 * @param u
	 * @param userlist
	 * @throws IOException  
	
	 */ 
	public void removeFromInfo(User u,ArrayList<User> userlist) throws IOException {
		String path = "src/function/user_info.txt";
		File f = new File(path);
		if (f.exists()) {
            f.delete();
        }
		file.write_into_user(userlist.get(0),false);
		for(int i = 1; i < userlist.size(); i++) {
			User usr = new User();
			usr = userlist.get(i);
			file.write_into_user(usr,true);
		}
	}
	
	//remove the corresponding bill records
	/**  
	
	 * <p>Title: removeBills</p>  
	
	 * <p>Description: remove the corresponding bill records</p>  
	
	 * @param u
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public boolean removeBills(User u,String energy) throws IOException {
		String id = u.get_id();
		boolean flag = false;
		ArrayList<Record> bill= file.read_from_bill(energy);
		System.out.println("U id: "+ u.get_id());
		System.out.println("bill size: "+ bill.size());
		for(int i=0;i<=bill.size();i++) {
			System.out.println(i+" id: "+bill.get(i).getUid());
			System.out.println(i+" usage: "+bill.get(i).getUsage()+" cost: "+bill.get(i).getCost());

			if(bill.get(i).getUid().equals(u.get_id()))
				bill.remove(i);
		}
//		for(int i=0;i<bill.size();i++) {
//			System.out.println("AFTER id: "+bill.get(i).getUid());
//			System.out.println("AFTER usage: "+bill.get(i).getUsage()+" cost: "+bill.get(i).getCost());
//		}
		String path = "src/function/"+energy+"bill.txt";
		File f = new File(path);
		f.delete();
		if (!f.exists()) {
          flag = true;
        }
		
		file.write_into_bill(bill.get(0),energy,false);
		for(int i = 1; i < bill.size(); i++) {
			Record r = bill.get(i);
			file.write_into_bill(r,energy,true);
		}
		if (!f.exists()) {
	          flag = false;
	        }
		return flag;
	}
	
	/**  
	
	 * <p>Title: find_unread</p>  
	
	 * <p>Description: find new record</p>  
	
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public ArrayList<String> find_unread(String energy) throws IOException {
		ArrayList<String> info = new ArrayList<String>();
		RandomAccessFile br = new RandomAccessFile("src/function/"+energy+"bill.txt", "r");
		String str = null;
		while((str = br.readLine())!=null) {
			String[] result = str.split("\t");
			if(result[4].equals("0")) {
				info.add(result[0]);
				info.add(result[1]);
				info.add(result[2]);
				info.add(result[3]);
				info.add(result[4]);
			}
		}
		br.close();
		for(int i=0;i<info.size();i++)
			System.out.println(info.get(i));
		return info;
	}
	
	/**  
	
	 * <p>Title: change_flag</p>  
	
	 * <p>Description: change unread to read</p>  
	
	 * @param energy
	 * @return
	 * @throws IOException  
	
	 */ 
	public ArrayList<String> change_flag(String energy) throws IOException {
		ArrayList<String> info = new ArrayList<String>();
//		ArrayList<String> g_info = new ArrayList<String>();
		RandomAccessFile br = new RandomAccessFile("src/function/"+energy+"bill.txt", "r");
		String str = null;
		while((str = br.readLine())!=null) {
			String[] result = str.split("\t");
			for(int i = 0;i<5;i++){	
				if(i !=4) {
				info.add(result[i]);
				}
				else if(i==4){
				if(result[i].equals("0")) {
				result[i] = "1";
				}
				info.add(result[i]);
			}
			}
		}
		br.close();
		for(int i=0;i<info.size();i++)
			System.out.println(info.get(i));
		return info;
	}
	
	/**  
	
	 * <p>Title: rewrite_bill</p>  
	
	 * <p>Description: record the change</p>  
	
	 * @param energy
	 * @param info
	 * @throws IOException  
	
	 */ 
	public void rewrite_bill(String energy,ArrayList<String> info) throws IOException {
		File file = new File("src/function/"+energy+"bill.txt");
		byte bt[] = new byte[1024];
    	try {  
    		FileOutputStream in1 = new FileOutputStream(file);  
    		try {
    			for(int i = 0;i<info.size();i++) {
    				bt = info.get(i).getBytes();
    				in1.write(bt);
    				in1.write("	".getBytes());
    				if((i+1)%5 == 0) {
    					in1.write("\r\n".getBytes());
    				}
    			}
    			in1.close();
    		}catch (IOException e) {   
    			e.printStackTrace();  
    		}
    	} catch (FileNotFoundException e) {  
    		e.printStackTrace();  
    	}
	}
	
	/**  
	
	 * <p>Title: set_price</p>  
	
	 * <p>Description: set tariff</p>  
	
	 * @param price
	 * @param path
	 * @throws IOException  
	
	 */ 
	public void set_price(String price,String path) throws IOException{					//set tariff
		String time = null;
		time = file.read_from_time();				//read from time.txt, return latest time
		file.write_price(path, time, price);		//write into price_e(g).txt
	}
	
}
