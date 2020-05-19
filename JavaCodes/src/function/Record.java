package function;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>description: entity class: record</p>
 * @author group 37
 * @param 
 */
public class Record {
	private User u = null;
	private String uid;
	private int date;
	private double usage;
	private double cost;
	
	public Record() {
	}
	public Record(User u) {
		super();
		this.u = u;
	}
	
	//getters and setters
	public int getDate() {
		return date;
	}

	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public double getUsage() {
		return usage;
	}

	public double getCost() {
		return cost;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

//	public abstract ArrayList<Record> get_record_list(User usr,String type) throws NumberFormatException, IOException;
//	public abstract double calUsage() throws FileNotFoundException,IOException;
//	public abstract double calCost() throws FileNotFoundException,IOException;
//	
}
