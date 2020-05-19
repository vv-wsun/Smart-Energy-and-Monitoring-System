package function;
import java.lang.Thread;
import java.util.Random;

import GUI.MessageBill;
import GUI.messageSuccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * <p>description: time thread</p>
 * @author group37
 */
public class Time extends Thread{
	private static String time;
	private static int t;
	String i_d;
	public Time(String id) {
		try {
			File f =new File("src/function/time.txt");
			i_d = id;
			time = file.read_from_time();
			t = Integer.parseInt(time);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		//FileOutputStream in = new FileOutputStream(file,true);  
		try {
			while(true)
		    {
			Thread.sleep(1000);//1s per day
			t++;
			time = String.valueOf(t);
			file.write_into_time(time);
			int counter = 0;
			String gas_record;
			String ele_record;
			Random rand = new Random();
			byte bt[] = new byte[1024];
			String path = "src/function/history/";
			File file = new File(path,i_d+".txt");
			ele_record = String.valueOf(rand.nextInt(30));
			try {
					FileOutputStream in = new FileOutputStream(file,true);
					gas_record = String.valueOf(rand.nextInt(10));
					if(Integer.parseInt(time)%3 == 0) {
					ele_record = String.valueOf(rand.nextInt(30));
					}
					bt = time.getBytes();
					in.write(bt);
					in.write("	".getBytes());
					bt = gas_record.getBytes();
					in.write(bt);
					in.write("	".getBytes());
					bt = ele_record.getBytes();
					in.write(bt);
					in.write("\r\n".getBytes());
					in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(t%30==0) {
				System.out.println("in Time: "+t);
				User.generate_bill(i_d, "e");
				User.generate_bill(i_d, "g");
				new messageSuccess();
				new MessageBill();
			}
		    }
		} catch (IOException e) {   
			e.printStackTrace();  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String get_time() {
		return this.time;
	}
}
