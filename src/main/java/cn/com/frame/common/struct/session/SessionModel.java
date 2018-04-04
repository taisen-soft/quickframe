/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.struct.session;


import cn.com.frame.common.struct.queue.Queue;
import cn.com.frame.common.struct.queue.QueueModel;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-4
 */
public class SessionModel {

    private String uuid;
    private String ip;
    private String username;
    private String useruuid;
    private String time ;
    private String enterURI;
    private Queue messageQueue;

    public SessionModel() {
        messageQueue = new Queue();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Queue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEnterURI() {
		return enterURI;
	}

	public void setEnterURI(String enterURI) {
		this.enterURI = enterURI;
	}

	/**
     * 增加队列
     * 
     * @param object
     */
    public void joinQueue(Object object) {
        QueueModel model = new QueueModel();
        model.setContentObject(object);
        messageQueue.joinQueue(model);
    }

    /**
     * 获取队头
     * 
     * @return
     */
    public Object deleteFront() {
        QueueModel model = messageQueue.deleteFront();
        return model == null ? "" : model.getContentObject();
    }

    /**
     * 是否队空
     * 
     * @return
     */
    public boolean isQueueEmpty() {
        return messageQueue.isQueueEmpty();
    }

}
