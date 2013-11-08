package bussiness;

import org.apache.log4j.Logger;
import run.GetWhiteListtask;

import com.util.ConfigConst;
import com.util.IpUtil;

public class BussinessTest {
	
	private static final Logger logger = Logger.getLogger(GetWhiteListtask.class);

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		BussinessUtil bussinessUtil = BussinessUtil.getInstance();		
		ConfigConst.getConfigConst();
		logger.info("程序开始运行");
		Thread r = new Thread(new GetWhiteListtask(bussinessUtil));
		r.start();
		
		boolean ipCheckFlag = bussinessUtil.checkIPwhitelist(IpUtil.ipToLong("111.62.90.254"));
		if(ipCheckFlag){
			System.out.println("111.62.90.254在其中");
		}else{
			System.out.println("111.62.90.254不在其中");
		}
	
	}

}
