/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.struct.queue;

import java.util.Vector;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-8-1
 */
public class Queue {

    protected Vector<QueueModel> queue = new Vector<QueueModel>();
    
    /**
     * 获取短信队列长度
     * @return
     */
    public synchronized int queueLength(){
        return queue.size();
    }
    
    /**
     * 队列是否为空
     * @return
     */
    public synchronized boolean isQueueEmpty(){
        return queue.isEmpty();
    }
    
    /**
     * 清空队列
     * @return
     */
    public synchronized void clearQueue(){
        queue.clear();
    }
    
    /**
     * 获取第一个队列元素
     * @return
     */
    public synchronized QueueModel frontQueue(){
        if(this.isQueueEmpty()){
            return null ;
        }
        return queue.elementAt(0);
    }
    
    /**
     * 队列元素出队
     * @return
     */
    public synchronized QueueModel deleteFront(){
        if(this.isQueueEmpty()){
            return null ;
        }
        QueueModel result = queue.elementAt(0);
        queue.removeElementAt(0);
        return result ;
    }
    
    
    /**
     * 队列元素入队
     * @param model
     * @return
     */
    public synchronized int joinQueue(QueueModel model){
        queue.addElement(model);
        return queue.size();
    }
    
    /**
     * 全部队列读取
     * @return
     */
    public synchronized Vector<QueueModel> getQueue(){
        try{
            return (Vector<QueueModel>)queue.clone() ;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Vector<QueueModel>() ;
    }
}
