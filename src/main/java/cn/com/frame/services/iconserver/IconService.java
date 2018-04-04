package cn.com.frame.services.iconserver;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.model.SfSDatacode;
import cn.com.frame.service.DatacodeManagerService;
import cn.com.frame.services.common.once.AbstractThreads;
import cn.com.frame.spring.SpringContextDB1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图标服务
 * 
 * @author Sirius
 * 
 */
public class IconService extends AbstractThreads {

	public IconService() {
		super(null);
	}

	private String[] iconpath = { "imgs/icon", "imgs/32icon" };
	// 资源文件位置
	private String fileproperty = "config/sysloader/install.properties";
	// 目标写入文件位置
	private String cssfile = "css/system-icon.css";

	@Override
	public void servicesStart() {
		// TODO Auto-generated method stub
		// 读取资源文件，查看是否已安装
		try {
			Map property = Sys.getXmlTools().readProperty(
					ParamUtil.CLASSES_PATH + "/" + fileproperty);
			// 0为未安装状态
			if (((String) property.get("iconinstall")).equals("1")) {
				System.out.println("系统图标无需安装，如需重新安装请修改"
						+ ParamUtil.CLASSES_PATH + "/" + fileproperty + "文件。");
				return;
			}
			// 删除所有icon
			DatacodeManagerService databusiness = (DatacodeManagerService) SpringContextDB1
					.getContext().getBean("datacodeManagerService");
			if (((String) property.get("iconinstall")).equals("0")) {
				databusiness.delete(" a01 = '_iconmanager' ");
			}
			// 读取目录下所有icon
			StringBuffer resultWrite = new StringBuffer();
			String datanow = Sys.getConverter().formatForceToString(new Date());
			for (int i = 0; i < iconpath.length; i++) {
				File filepath = new File(ParamUtil.WEBROOT_PATH + "/"
						+ iconpath[i]);
				// 获取目录下所有文件
				File[] fileread = filepath.listFiles();
				// 文件读取
				for (int j = 0; j < fileread.length; j++) {
					File tempread = fileread[j];
					String imageurl = "/" + ParamUtil.SYSTEM_ID + "/"
							+ iconpath[i] + "/" + tempread.getName();
					String css = ".sicon-"
							+ tempread.getName().replaceAll("\\.", "_");

					resultWrite.append(css);
					resultWrite.append("{");
					resultWrite.append("background-image:");
					resultWrite.append("url(" + imageurl + ") !important;}");
					// 写入数据库
					SfSDatacode savedata = new SfSDatacode();
					if (!((String) property.get("iconinstall")).equals("0")) {
						List templist = (List) databusiness
								.findByCondition(
										" a01 = '_iconmanager' and a10='"
												+ tempread.getName() + "' ",0,
										0, null);
						if (templist.size() > 0) {
							SfSDatacode temp = (SfSDatacode) templist.get(0);
							savedata.setId(temp.getId());
						}
					}
					savedata.setUuid(Sys.getConverter().getUUID());
					savedata.setA01("_iconmanager");
					savedata.setA10(tempread.getName());
					savedata.setA11(imageurl);
					savedata.setA12(css);
					savedata.setOwner("system");
					savedata.setUpdater("system");
					savedata.setUpdatetime(datanow);
					if (savedata.getId() == null) {
						databusiness.saveOrUpdate(savedata);
					} else {
						databusiness.saveOrUpdate(savedata);
					}
				}
			}
			List content = new ArrayList();
			content.add(resultWrite.toString());
			// 写入css
			Sys.getFileOperator()
					.writeFile(ParamUtil.WEBROOT_PATH + "/" + cssfile, content,
							"override");

			property.put("iconinstall", "1");
			Sys.getXmlTools().writeProperty(
					ParamUtil.CLASSES_PATH + "/" + fileproperty, property);
			System.out.println("系统图标安装完成。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
