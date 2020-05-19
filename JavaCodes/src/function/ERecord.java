package function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


/**
 * <p>description:Entity class, inherit from Record</p>
 * @author group 37
 * @param 
 */
public class ERecord extends Record implements RecordControl{

//	public ERecord(String id) {
//		super(id);
//		this.setUid(id);
//	}
	public ERecord(User u) {
		super(u);
		this.setU(u);
		// TODO Auto-generated constructor stub
	}
	
	//calculate current usage
	/* (non-Javadoc)
	 * @see function.RecordControl#calUsage()
	 */
	public double calUsage() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Record> record = file.read_from_record(this.getU(),"e");
		int month = file.calMonth();
		double usage = 0;
		for(int i = 0;i<record.size();i = i+1) {
			if(record.get(i).getDate()>30*month+1&&record.get(i).getDate()<=(month+1)*30) {
				usage = usage + record.get(i).getUsage();
			}
		}
		return usage;
	}

	/* (non-Javadoc)
	 * @see function.RecordControl#calCost()
	 */
	public double calCost() throws FileNotFoundException,IOException {			
		ArrayList<Record> record = file.read_from_record(this.getU(), "e");
		int month = file.calMonth();
		double cost = 0;
		double price = 0;
		System.out.println(month);
		for(int i = 0;i<record.size();i = i+1) {
			if(record.get(i).getDate()>30*month+1&&record.get(i).getDate()<=(month+1)*30) {
				price = file.find_price(record.get(i).getDate(),"e"); 	
				cost = cost + record.get(i).getUsage() * price;
			}
		}
//		System.out.println("E cost: "+String.valueOf(cost));
		return cost;
	}
//	public static void main(String[] args) throws FileNotFoundException, IOException{
//		User u = new User();
//		u.set_id("2015204238");
//		ERecord e = new ERecord(u);
//		double cost = e.calCost();
//		System.out.println(cost);
//}
	/* (non-Javadoc)
	 * @see function.RecordControl#get_record_list(function.User, java.lang.String)
	 */
	public ArrayList<Record> get_record_list(User usr,String type) throws NumberFormatException, IOException {
		ArrayList<Record> recordlist = file.read_from_record(this.getU(), "e");
		ArrayList<Record> elist = new ArrayList<Record>();
		
		if(type.equals("dayly")){ 
			elist = recordlist;
		}
		if(type.equals("weekly")){ 
			int week=0;
			double e_usage=0;
			for(int i = 0;i<recordlist.size();i=i+1) {
				e_usage = e_usage + recordlist.get(i).getUsage();
				if(i%7 == 0||i==recordlist.size()) {
//					System.out.print(week +"	"+e_usage);
					Record r =new ERecord(usr);
					r.setDate(recordlist.get(i).getDate());
					r.setUsage(e_usage);
					elist.add(r);
					week++;
					System.out.println(week +"   "+ r.getDate()+"	"+r.getUsage());
				}
			} 
		}
		if(type.equals("monthly")){ 
			double e_usage=0;
			int month=1;
				for(int i = 0;i<recordlist.size();i=i+1) {
					e_usage = e_usage + recordlist.get(i).getUsage();
					if(i%30 == 0||i==recordlist.size()) {
//						System.out.print(month +"	"+e_usage );
						Record r =new ERecord(usr);
						r.setDate(month);
						r.setUsage(e_usage);
						elist.add(r);
						month++;
					}
				}
			}
	return elist;
	}
	
	
}
