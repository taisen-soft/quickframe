/*
package cn.com.frame.services.collectserver.services;


import cn.com.frame.model.SfBQuickcommon;
import cn.com.frame.services.common.timer.SystemTimerTask;

import javax.servlet.ServletContextEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

*/
/**
 * 数据删除服务
 * 
 * @author Sirius
 * 
 *//*

public class CollectServer extends SystemTimerTask {

	private Map params;

	public CollectServer(ServletContextEvent sce, Map params) {
		super(sce);
		// TODO Auto-generated constructor stub
		this.params = params;
	}

	@Override
	public void taskRun() throws Exception {
		// TODO Auto-generated method stub
		// 读取完成
		if (SpringContextDB1.getContext() == null) {
			return;
		}
		System.out.println("统计服务运行：" + Sys.getConverter().formatForceToString(new Date()));
		// 统计数据
		// 1、获取统计实体,index_all
		SmallcommonBusiness business = (SmallcommonBusiness) SpringContextDB1.getContext().getBean(
				"smallCommonpublishBusiness");
		QuickcommonBusiness quickbusiness = (QuickcommonBusiness) SpringContextDB1.getContext().getBean(
				"quickCommonpublishBusiness");
		List collectList = (List) quickbusiness.findCommonSetByPage(
				0, 0, " a01 = 'collectpic' and a02 = 'index_all' ", null, null);
		SfBQuickcommon collectmain = null;
		// 获取统计标题
		if (collectList.size() <= 0) {
			collectmain = new SfBQuickcommon();
			collectmain.setA01("collectpic");
			collectmain.setA02("index_all");
			collectmain.setA03("1");
			collectmain.setUuid(Sys.getConverter().getUUID());
			collectmain.setA10("寿阳网站群整体发布量汇总");
			collectmain.setA12("发布量:d01");
		}
		else {
			collectmain = (SfBQuickcommon) collectList.get(0);
		}
		// 删除统计值
		quickbusiness.deleteByCondition(" a01 = 'collectpic_detail' and a02 = 'index_all' ");
		// 创建统计值
		List stationList = (List) quickbusiness.findCommonSetByPage(0, 0, " a01 = '_station' ", null, null);
		long alltotal = 0;
		for (Iterator it = stationList.iterator(); it.hasNext();) {
			SfBQuickcommon temp = (SfBQuickcommon) it.next();
			String a10 = temp.getA11();
			String a03 = temp.getA03();
			long total = business.findCommonListCount(" a01 = '_document' and a03 like '" + a03 + "%0X' ");
			SfBQuickcommon tjdetail = new SfBQuickcommon();
			tjdetail.setA01("collectpic_detail");
			tjdetail.setA02("index_all");
			tjdetail.setA10(a10);
			tjdetail.setD01(new Long(total).doubleValue());
			quickbusiness.saveCommonInstance(tjdetail);
			alltotal += total;
		}
		collectmain.setA11("共发布文章" + alltotal + "篇");
		if (collectmain.getId() == null) {
			quickbusiness.saveCommonInstance(collectmain);
		}
		else {
			quickbusiness.updateCommonInstance(collectmain, "all");
		}

	}
}
*/
