/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.common.once;


import java.util.*;
import cn.com.frame.common.struct.queue.Queue;
import cn.com.frame.common.struct.queue.QueueModel;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-29
 */
public final class ThreadManager {

	/**
	 * 运行服务监控
	 */
	private static Map<String, ThreadDescripter> RUNNING_SERVICES = new HashMap<String, ThreadDescripter>();
	private static Queue THREADS_QUEUE = new Queue();
	private static Map<String, Integer> THREADS_QUEUE_STORE = new HashMap<String, Integer>();
	private static boolean THREADS_QUEUE_FLAG = false;

	/**
	 * 隐藏构造方法
	 */
	private ThreadManager() {

	}

	/**
	 * 获取所有键值
	 * 
	 * @return
	 */
	public synchronized static List servicesKeys() {
		List result = new ArrayList();
		for (Iterator<String> it = RUNNING_SERVICES.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			result.add(key);
		}
		return result;
	}

	/**
	 * 获取指定键值的服务信息
	 * 
	 * @param key
	 * @return
	 */
	public synchronized static ThreadDescripter findServices(String key) {
		return RUNNING_SERVICES.get(key);
	}

	/**
	 * 放入指定Key的值
	 * 
	 * @param key
	 * @param value
	 */
	public synchronized static void joinServices(String key, ThreadDescripter value) {
		RUNNING_SERVICES.put(key, value);
		if (THREADS_QUEUE_STORE.get(key) != null) {
			THREADS_QUEUE_FLAG = true;
		}
	}

	/**
	 * 移除服务
	 * 
	 * @param key
	 */
	public synchronized static void removeServices(String key) {
		RUNNING_SERVICES.remove(key);
		if (THREADS_QUEUE_STORE.get(key) != null) {
			THREADS_QUEUE_FLAG = false;
			THREADS_QUEUE_STORE.remove(key);
		}
	}

	/**
	 * 线程队列是否为空
	 * 
	 * @return
	 */
	public synchronized static boolean isQueueEmpty() {
		return THREADS_QUEUE.isQueueEmpty();
	}

	/**
	 * 线程出对
	 * 
	 * @return
	 */
	public synchronized static AbstractThreads deleteFront() {
		if (THREADS_QUEUE.isQueueEmpty()) {
			return null;
		}
		if (THREADS_QUEUE_FLAG == true) {
			return null;
		}
		QueueModel model = THREADS_QUEUE.deleteFront();
		return (AbstractThreads) model.getContentObject();
	}

	/**
	 * 线程入队
	 * 
	 * @param
	 */
	public synchronized static void joinQueue(AbstractThreads thread) {
		QueueModel model = new QueueModel();
		model.setContentObject(thread);
		THREADS_QUEUE.joinQueue(model);
		THREADS_QUEUE_STORE.put(thread.RUNNING_SERVICES_KEY, 1);
	}

	public synchronized static List queueAll() {
		List result = new ArrayList();
		for (Iterator it = THREADS_QUEUE.getQueue().iterator(); it.hasNext();) {
			QueueModel model = (QueueModel) it.next();
			result.add(model.getContentObject());
		}
		return result;
	}

	/**
	 * 判断队列中是否存在同类操作的线程。
	 * */
	public synchronized static boolean isInQueue(AbstractThreads thread) {
		Vector<QueueModel> queueVector = THREADS_QUEUE.getQueue();
		for (int i = 0; i < queueVector.size(); i++) {
			QueueModel tempmodel = queueVector.get(i);
			if((((AbstractThreads)tempmodel.getContentObject()).RUNNING_SERVICES_PARAMS.getServiceName().equals(thread.RUNNING_SERVICES_PARAMS.getServiceName()))&&(((AbstractThreads)tempmodel.getContentObject()).RUNNING_SERVICES_PARAMS.getUseruuid().equals(thread.RUNNING_SERVICES_PARAMS.getUseruuid()))&&(((AbstractThreads)tempmodel.getContentObject()).RUNNING_SERVICES_PARAMS.getUsername().equals( thread.RUNNING_SERVICES_PARAMS.getUsername()))){
				return true;
			}
		}
		return false;
	}
}
