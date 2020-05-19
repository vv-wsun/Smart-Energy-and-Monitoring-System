package function;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * <p>description: Entity class, inherit from Record</p>
 * @author group37
 * @param 
 */
public class GRecord extends Record implements RecordControl{
	
	public GRecord(User u) {
		super(u);
		this.setU(u);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see function.RecordControl#get_record_list(function.User, java.lang.String)
	 */
	public ArrayList<Record> get_record_list(User usr,String type) throws NumberFormatException, IOException {

		ArrayList<Record> recordlist = file.read_from_record(this.getU(), "g");
		ArrayList<Record> glist = new ArrayList<Record>();
		
		if(type.equals("dayly")){ 
			glist = recordlist;
		}
		if(type.equals("weekly")){ 
			int week=0;
			double g_usage=0;
			for(int i = 0;i<recordlist.size();i=i+1) {
				g_usage = g_usage + recordlist.get(i).getUsage();
				if(i%7 == 0||i==recordlist.size()) {
//					System.out.print(week +"	"+e_usage);
					Record r =new GRecord(usr);
					r.setDate(recordlist.get(i).getDate());
					r.setUsage(g_usage);
					glist.add(r);
					week++;
					System.out.println(week +"   "+ r.getDate()+"	"+r.getUsage());
				}
			} 
		}
		if(type.equals("monthly")){ 
			double g_usage=0;
			int month=1;
				for(int i = 0;i<recordlist.size();i=i+1) {
					g_usage = g_usage + recordlist.get(i).getUsage();
					if(i%30 == 0||i==recordlist.size()) {
//						System.out.print(month +"	"+e_usage );
						Record r =new GRecord(usr);
						r.setDate(month);
						r.setUsage(g_usage);
						glist.add(r);
						month++;
					}
				}
			}
	return glist;
	}
	
	/* (non-Javadoc)
	 * @see function.RecordControl#calUsage()
	 */
	public double calUsage() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Record> record = file.read_from_record(this.getU(),"g");
		int month = file.calMonth();
		double usage = 0;
		for(int i = 0;i<record.size();i = i+1) {
			if(record.get(i).getDate()>30*month+1&&record.get(i).getDate()<=(month+1)*30) {
				usage = usage + record.get(i).getUsage();
			}
		}
		System.out.println("G usage: "+String.valueOf(usage));
		return usage;
	}
	
	/* (non-Javadoc)
	 * @see function.RecordControl#calCost()
	 */
	public double calCost() throws FileNotFoundException,IOException {			

		ArrayList<Record> record = file.read_from_record(this.getU(), "g");
		int month = file.calMonth();
		double cost = 0;
		double price = 0;
		
		for(int i = 0;i<record.size();i = i+1) {
			if(record.get(i).getDate()>30*month+1&&record.get(i).getDate()<=(month+1)*30) {
				price = file.find_price(record.get(i).getDate(),"g"); 			
				cost = cost + record.get(i).getUsage() * price;
			}
		}
		System.out.println("G cost: "+String.valueOf(cost));
		return cost;
	}
}
