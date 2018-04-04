package cn.com.frame.dto;

/**
 * 树形结构对象的父类
 *
 */
public class TreeDto {
	private long id ; //id
	private String pId ;//父id
	private String name ;//名称
	private String describe ;//描述
	private String icon ;//图标
	private String iconOpen ;//展开图标
	private String iconClose ;//关闭图标
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
}