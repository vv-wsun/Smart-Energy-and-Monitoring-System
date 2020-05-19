package function;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>description:user log in function</p>
 * @author group37
 * @param 
 */
public class Login {
	public static List<String> idPassword = new ArrayList<String>();
	private static String id;
	private static String password;
	//public static String pass;
	public Login(String id, String password) {
		this.id = id;
		this.password = password;
	}

	/**  
	
	 * <p>Title: judgment</p>  
	
	 * <p>Description: judge whether there is a mistake</p>  
	
	 * @return  
	
	 */ 
	public static String judgment() {
		boolean isIdExist = false;
		for(int i = 0;i <idPassword.size(); i=i+2) {
			if(id.equals(idPassword.get(i))){
				isIdExist = true;
				if(password.equals(idPassword.get(i+1))) {
					return id;
				} else {
					System.out.println("Your password is wrong, please input again!");
					return "pssfail";
				}	
			}
		}
		if(isIdExist==false) {
			System.out.println("Your id doesn't exist, please check again");
		}
		return "idfail";
	}
	
	/**  
	
	 * <p>Title: load</p>  
	
	 * <p>Description: load id and password</p>  
	
	 * @return
	 * @throws IOException  
	
	 */ 
	public static List<String> load() throws IOException {		//read user_info.txt, return idPassword
		ArrayList<User> userlist = new ArrayList<User>();
		userlist = file.read_from_user();
		for(int i=0;i<userlist.size();i++){
			idPassword.add(userlist.get(i).get_id());
			idPassword.add(userlist.get(i).get_password());	
		}
		return idPassword;
		
	}
	
}

