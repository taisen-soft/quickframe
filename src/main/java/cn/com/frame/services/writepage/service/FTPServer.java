/*
 * Copyright (c) 1989-2016 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.writepage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import cn.com.frame.common.util.ShareData;
import org.apache.commons.net.ftp.FTPClient;

import cn.com.frame.model.SfBConfig;
import cn.com.frame.service.ConfigManagerService;
import cn.com.frame.services.common.once.AbstractThreads;
import cn.com.frame.spring.SpringContextDB1;
import org.apache.commons.io.IOUtils;
/**
 * @author rwj
 * @version 创建时间：2016-5-19 上午11:28:13 类说明
 * 
 */
public class FTPServer extends AbstractThreads {

	public FTPServer(Map params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void servicesStart() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.RUNNING_PARAMS.get("__SERVICENAME") + "启动ftp服务");
		// 获取所有的同步目录
		ConfigManagerService configBusiness = (ConfigManagerService) SpringContextDB1.getContext().getBean("configManagerService");
		ArrayList<SfBConfig> dispatches = (ArrayList<SfBConfig>) configBusiness.findByCondition( " a01 = '_dispatch'",0,0 , null);
		for (SfBConfig dispatch : dispatches) {
			String tempdir = "";
//			System.out.println("创建本条目录的根目录");
//			System.out.println(dispatch.getA13());
			FTPClient ftpClient = new FTPClient();
			FileInputStream fis = null;
			try {
				ftpClient.connect(dispatch.getA10());
				ftpClient.login(dispatch.getA11(), dispatch.getA12());
				// 设置上传目录
				String desDirectory = (dispatch.getA13().indexOf("/") == 0) ? dispatch.getA13() : ("/" + dispatch.getA13());
				if (!ftpClient.changeWorkingDirectory(desDirectory)) {
					// 创建ftp目标目录
					// desDirectory = desDirectory.replaceAll("\\", "/");
					String[] directorys = desDirectory.split("/");
					tempdir = directorys[1];
					for (int i = 1; i < directorys.length; i++) {
						if (!ftpClient.changeWorkingDirectory(tempdir)) {
							ftpClient.makeDirectory(tempdir);
//							System.out.println("创建目录" + tempdir);
						}
						if (i < directorys.length - 1) {
							tempdir += "/" + directorys[i + 1];
						}
					}
				}

				// 创建ftp具体文件目录
				for (Map.Entry<String, String> entry : ((Map<String, String>) ShareData.FTP_MAP).entrySet()) {
					String key = (String) entry.getKey();
					String value = entry.getValue();
//					 System.out.println(key);
//					 System.out.println(value);
//					 System.out.println();
					// 遍历目录层次，依次创建目录
					if (!ftpClient.changeWorkingDirectory(value)) {
						// key = key.replaceAll("\\", "/");
						String[] directorys1 = value.split("/");
						String tempdir1 = desDirectory + "/" + directorys1[1];
						for (int i = 1; i < directorys1.length; i++) {
							if (!ftpClient.changeWorkingDirectory(tempdir1)) {
								if (!tempdir1.contains(".")) {
									ftpClient.makeDirectory(tempdir1);
//									System.out.println("创建文件目录" + tempdir1);
								}
							}
							if (i < directorys1.length - 1) {
								tempdir1 += "/" + directorys1[i + 1];
							}
						}
//						System.out.println(key);
//						 拷贝该文件
						 ftpClient.setBufferSize(1024);
						 ftpClient.setControlEncoding("UTF-8");
						 // 设置文件类型（二进制）
						 File srcFile = new File(key);
						 fis = new FileInputStream(srcFile);
						 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
						 ftpClient.storeFile(tempdir1, fis);
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				IOUtils.closeQuietly(fis);
				try {
					ftpClient.disconnect();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ShareData.FTP_MAP.clear();
	}

}
