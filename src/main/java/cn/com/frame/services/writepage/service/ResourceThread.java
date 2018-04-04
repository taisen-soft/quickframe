package cn.com.frame.services.writepage.service;

import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.model.SfBQuickcommon;
import cn.com.frame.service.QuickcommonManagerService;
import cn.com.frame.services.common.once.AbstractThreads;
import cn.com.frame.spring.SpringContextDB1;

import java.util.Map;

/**
 * 资源拷贝服务
 * 
 * @author Sirius
 * 
 */
public class ResourceThread extends AbstractThreads {

	public ResourceThread(Map params) {
		// TODO Auto-generated constructor stub
		super(params);
	}

	@Override
	public void servicesStart() {
		// TODO Auto-generated method stub
		try {
			// 站点id
			String id = (String) this.RUNNING_PARAMS.get("id");
			// 获取站点路径
			QuickcommonManagerService quickbusiness = (QuickcommonManagerService) SpringContextDB1
					.getContext().getBean("quickCommonpublishBusiness");
			SfBQuickcommon baseid = (SfBQuickcommon) quickbusiness
					.findByCondition("id = '"+Long.parseLong(id)+"'",0,0,null);
			String director = null;
			if (baseid.getA14().indexOf(":") == -1) {
				director = ParamUtil.WEBROOT_PATH;
			} else {
				director = baseid.getA14();
			}
			director += "/" + baseid.getA10();
			// 开始拷贝
			// 拷贝fileupload目录

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
