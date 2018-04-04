package cn.com.frame.services.sign;

import cn.com.frame.model.SfBConfig;
import cn.com.frame.service.ConfigManagerService;
import cn.com.frame.services.common.timer.SystemTimerTask;
import cn.com.frame.spring.SpringContextDB2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;


/**
 * 系统监控服务
 * 
 * @author Sirius
 * 
 */
public class SignServer extends SystemTimerTask {
	private Map params;
	public SignServer(ServletContextEvent sce, Map params) {
		super(sce);
		// TODO Auto-generated constructor stub
		this.params = params;
	}

	@Override
	public void taskRun() throws Exception {

	}

	/*	private Map params;
//	private String httpUrl = "http://apis.baidu.com/xiaogg/holiday/holiday";
//	private String apikey = "fafbe807fcbd2bb79e3802f16743df31";

	public SignServer(ServletContextEvent sce, Map params) {
		super(sce);
		// TODO Auto-generated constructor stub
		this.params = params;
	}

	@Override
	public void taskRun() throws Exception {
		try {
			Thread.sleep(15000);
		}
		catch (Exception e) {

		}
		// 获取配置
		ConfigManagerService configbusiness = (ConfigManagerService) SpringContextDB2.getContext().getBean("configManagerService");
		// 操作
		SignBusiness signbusiness = (SignBusiness) SpringContextDB2.getContext().getBean("signpublish");
		// 获取人员及参数
		List config = (List) configbusiness.findCommonSetByPage(0, 0, " a01 = '_user' ", null, null);
		
		System.out.println("开始一次");
		Calendar cal = Calendar.getInstance();
		Calendar yesterday = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time = df.format(date);
		// 读取完成
		if (SpringContextDB1.getContext() == null || SpringContextDB2.getContext() == null) {
			return;
		}
		// 计算今天是否为节假日
//		URL url = new URL(httpUrl + "?d=" + time);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
		// 填入apikey到HTTP header
//		connection.setRequestProperty("apikey", apikey);
//		connection.connect();
//		InputStream is = connection.getInputStream();
//		BufferedReader reader;
//		String result = "";
//		try {
//			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//			StringBuffer sbf = new StringBuffer();
//			String strRead = null;
//			while ((strRead = reader.readLine()) != null) {
//				sbf.append(strRead);
//				sbf.append("\r\n");
//			}
//			reader.close();
//			result = sbf.toString();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}

		// 计算星期几
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		week = week < 0 ? 0 : week;
		// 昨天
		if (((String) params.get("key")).equals("up")) {
			yesterday.set(
					yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH),
					8, new Double(Math.random() * 13 + 15).intValue(), 0);
		}
		else {
			yesterday.set(
					yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH),
					17, new Double(Math.random() * 25 + 1).intValue(), 0);
		}
		// // 今天
		// Calendar today = Calendar.getInstance();
		// today.add(Calendar.DAY_OF_YEAR, 1);
		// today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH), 8, new
		// Double(
		// Math.random() * 28 + 20).intValue(), 0);
		// 工作日 0 ，休息日1，节假日2
		System.out.println(yesterday.getTime());
		String result = "1";
		if (result.indexOf("2") != -1) {
			return;
		}
		else if (result.indexOf("1") != -1) {
			// 星期六
			if (week == 6) {
				if (((String) params.get("key")).equals("up")) {

				}
				else {
					yesterday.set(
							yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH),
							yesterday.get(Calendar.DAY_OF_MONTH), 12, new Double(Math.random() * 25 + 1).intValue(), 0);
				}
			}// 星期日
			else if (week == 0) {
//				return;
			}
		}

		// 开始

		for (Iterator it = config.iterator(); it.hasNext();) {
			SfBConfig temp = (SfBConfig) it.next();
			String b01 = temp.getB01();
			String[] levelsplit = b01.split(";");

			CmpLbsBase base = new CmpLbsBase();
			base.setId(new Double(Math.random() * 1000000000000000000D).longValue());
			base.setOwnerId(Long.parseLong(temp.getA11()));
			base.setDptId(-5458693611303121892L);
			base.setLevelId(Long.parseLong(temp.getA12()));
			base.setLbsLongitude((Double.parseDouble(levelsplit[0])));
			base.setLbsLatitude((Double.parseDouble(levelsplit[1])));
			base.setLbsAddr(levelsplit[2]);
			base.setLbsProvince(levelsplit[3]);
			base.setLbsCity(levelsplit[4]);
			base.setLbsTown(levelsplit[5]);
			base.setLbsStreet(levelsplit[6]);
			base.setLbsAddressType((short) 2);
			base.setCreateDate(new Timestamp(yesterday.getTime().getTime()));
			base.setCategory((short) 36);
			base.setReferenceFormId(0L);
			base.setReferenceFormmasterdataId(0L);
			base.setReferenceRecordId(0L);
			base.setReferenceSummaryId(0L);
			base.setReferenceTemplateId(0L);
			base.setState((short) 2);
			signbusiness.saveCommonInstance(base);

		}

		}*/

}
