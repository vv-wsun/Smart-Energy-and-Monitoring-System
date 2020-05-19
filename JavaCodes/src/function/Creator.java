package function;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * <p>description: create initial file</p>
 * @author group37
 * @param 
 */
public class Creator {
	/**  
	
	 * <p>Title: rand_id</p>  
	
	 * <p>Description: generate rand_id</p>  
	
	 * @return  
	
	 */ 
	public String rand_id() {
		Random rand = new Random();
		String section1;
		String section2;
		section1 = String.valueOf(200+rand.nextInt(40));
		section2 = String.valueOf(200+rand.nextInt(40));
		String rand_id = "2015"+section1+section2;
		return rand_id;
	}
	/**  
	
	 * <p>Title: rand_username</p>  
	
	 * <p>Description: generate rand_username</p>  
	
	 * @return  
	
	 */ 
	public String rand_username() {
		Random rand = new Random();
		int name_length;
		String rand_username = "";
		name_length = 3+rand.nextInt(10);
		for(int i=0;i<name_length;i++) {
			Random rand2 = new Random();
			rand_username = rand_username+(char)(97+rand2.nextInt(26));
		}
		return  rand_username;
	}
	/**  
	
	 * <p>Title: rand_mailbox</p>  
	
	 * <p>Description: generate e-mail address</p>  
	
	 * @return  
	
	 */ 
	public String rand_mailbox() {
		int number_or_letter;
		int account_length;
		int type;
		String rand_mailbox = "";
		Random rand = new Random();
		account_length = 6+rand.nextInt(10);
		for(int i=0;i<account_length;i++) {
			Random rand2 = new Random();
			number_or_letter = rand2.nextInt(2);
			if(number_or_letter == 0) {
				rand_mailbox = rand_mailbox + rand2.nextInt(10);
			}
			else {
				rand_mailbox = rand_mailbox + (char)(97+rand2.nextInt(26));
			}
		}
		Random rand3 = new Random();
		type = rand3.nextInt(4);
		if(type == 0) {
			rand_mailbox = rand_mailbox +"@qq.com";
		}
		if(type == 1) {
			rand_mailbox = rand_mailbox +"@163.com";
		}
		if(type == 2) {
			rand_mailbox = rand_mailbox +"@126.com";
		}
		if(type == 3) {
			rand_mailbox = rand_mailbox +"@gamil.com";
		}
		return rand_mailbox;
	}
	/**  
	
	 * <p>Title: creat_userfile</p>  
	
	 * <p>Description: create initial user file</p>  
	
	 * @param num  
	
	 */ 
	public void creat_userfile(int num){
		long t = System.currentTimeMillis();
		String path = "src/function/";
		String rid = null;
		String rand_username = null;
		String rand_mailbox = null;
		File file = new File(path,"user_info.txt");
		byte bt[] = new byte[1024];
    	try {  
    		FileOutputStream in = new FileOutputStream(file);  
    		try {
    			for(int i=0;i<num;i++)
                {
    				rid = rand_id();
        			rand_username = rand_username();
        			rand_mailbox = rand_mailbox();
        			bt =  rid.getBytes();
        			in.write(bt);
        			in.write("	".getBytes());
        			bt = rand_username.getBytes();
        			in.write(bt);
        			in.write("	".getBytes());
        			bt = rand_mailbox.getBytes();
        			in.write(bt);
        			in.write("	".getBytes());
        			in.write("000000".getBytes());
        			in.write("	".getBytes());
        			in.write("0".getBytes());
        			in.write("	".getBytes());
        			in.write("0".getBytes());
        			in.write("\r\n".getBytes());
        		}
    			in.close();
    		} catch (IOException e) {   
    			e.printStackTrace();  
    		}  
    	} catch (FileNotFoundException e) {  
    		e.printStackTrace();  
    	}
    }
	public User rand_user(User usr) {
		usr.set_id(rand_id());
		usr.set_username(rand_username());
		usr.set_email(rand_mailbox());
		usr.set_password("000000");
		return usr;
	}
	/**  
	
	 * <p>Title: generate_record</p>  
	
	 * <p>Description: generate a daily record</p>  
	
	 * @param usr_id
	 * @param time
	 * @throws InterruptedException
	 * @throws IOException  
	
	 */ 
	public static void generate_record(String usr_id,String time) throws InterruptedException, IOException {
		int counter = 0;
		String gas_record;
		String ele_record;
		Random rand = new Random();
		byte bt[] = new byte[1024];
		String path = "src/function/history/";
		File file = new File(path,usr_id+".txt");
		
		try {
				FileOutputStream in = new FileOutputStream(file,true);
				gas_record = String.valueOf(rand.nextInt(10));
				ele_record = String.valueOf(rand.nextInt(30));
				bt = time.getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = gas_record.getBytes();
				in.write(bt);
				in.write("	".getBytes());
				bt = ele_record.getBytes();
				in.write(bt);
				in.write("\r\n".getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

