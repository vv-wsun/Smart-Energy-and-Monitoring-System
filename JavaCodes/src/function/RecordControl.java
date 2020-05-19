/**  
* <p>Title: RecordControl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: www.baidudu.com</p>  
* @author shenlan  
* @date 2018Äê5ÔÂ30ÈÕ  
* @version 1.0  
*/ 
package function;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>description: methods to control records</p>
 * @author group37
 * @param 
 */
public interface RecordControl {
	/**  
	
	 * <p>Title: get_record_list</p>  
	
	 * <p>Description: get all records</p>  
	
	 * @param usr
	 * @param type
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException  
	
	 */ 
	public abstract ArrayList<Record> get_record_list(User usr,String type) throws NumberFormatException, IOException;
	/**  
	
	 * <p>Title: calUsage</p>  
	
	 * <p>Description: calculate usage</p>  
	
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException  
	
	 */ 
	public abstract double calUsage() throws FileNotFoundException,IOException;
	/**  
	
	 * <p>Title: calCost</p>  
	
	 * <p>Description: calculate cost</p>  
	
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException  
	
	 */ 
	public abstract double calCost() throws FileNotFoundException,IOException;
//	public void write_bill() throws FileNotFoundException, IOException;
}
