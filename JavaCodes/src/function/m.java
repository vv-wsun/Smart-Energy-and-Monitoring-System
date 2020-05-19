package function;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.Thread;
public class m {
//	public int removeFromInfo(String ID) {
//		int flag = 0;
//		String path = "funtion\\user_info.txt";
//		File file = new File(path);
//		String path2 = "funtion\\temp.txt";
//		//File file = new File(path); 
//		File temp = null;
//        BufferedReader br = null;
//        PrintWriter pw = null;
//        String read;
//
//		try {
//			temp = new File(path2);
//            pw = new PrintWriter(temp);
//			br = new BufferedReader(new FileReader(file));
//			 while((read=br.readLine())!= null) {
//	                if(!read.contains(ID)) {
//	                	pw.println(read);
//	                }
//	                else continue;
//	            }
//			 br.close();
//			 pw.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (temp != null) {
//            temp.renameTo(file);
//            file.delete();
//            flag = 1;
//        }
//		return flag;
//	}
	
	public static void main(String args[]) throws IOException, InterruptedException{
		m a = new m();
		Creator c = new Creator();
//		c.creat_userfile(30);
//		c.creat_userfile(5);
//		c.generate_record("2015233221", "0");
//		file.read_from_user();
//		String h = "function/history/2015204237.txt";
//		File file = new File(h);
//		Thread time = new Thread();
//		time.start();
		//section1
		/*user usr = new user();
		FileInputStream fileInput = null;
		String path = "C:\\Users\\82345\\desktop\\numenor\\";
		String num = null;
		creator c = new creator();
		c.creat_userfile(30);
		login l = new login();
		modifier m = new modifier();
		try {
			l.load(path+"test.txt");
		}  
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usr.id = l.check();
		//System.out.println(usr.id);
		try {
			m.password_modifier(usr.id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//section2
		Manage m = new Manage();
		ArrayList<user> userlist = new ArrayList<user>();
		String path = "C:\\Users\\82345\\desktop\\numenor\\test.txt";
		File file = new File(path); 
		m.read_from_txt(file, userlist);
		m.view_user_info(userlist);
		m.add_user(userlist, file);*/
		//section3
		/*creator c = new creator();
		try {
			c.history_generator("2015213215");
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//section4
		/*Manage m = new Manage();
		m.set_price();*/
		//section5
		/*Time t = new Time();
		t.start();
		creator c= new creator();
		while(true) {
		Thread.sleep(1000);
		c.generate_record("2015213215",Time.time);
		}*/
	}
}
