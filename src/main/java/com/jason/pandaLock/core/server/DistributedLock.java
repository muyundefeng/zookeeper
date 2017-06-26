package com.jason.pandaLock.core.server;
/**
 * 分布式文件锁的思想：
 * 进程需要访问共享数据时, 就在"/locks"节点下创建一个sequence类型的子节点, 称为thisPath.
 * 当thisPath在所有子节点中最小时, 说明该进程获得了锁.
 * 进程获得锁之后, 就可以访问共享资源了. 访问完成后, 需要将thisPath删除. 锁由新的最小的子节点获得.
 * 为什么不能创建一个节点，创建成功的节点就可以实现访问？
 */

import org.apache.zookeeper.KeeperException;

import com.jason.pandaLock.core.exception.PandaLockException;


/**
 * @author 作者 E-mail:ruanjianlxm@sina.com
 * @version 创建时间：2015年9月11日 下午6:27:55
 * 类说明     定义分布式锁需要的方法
 */
public abstract class DistributedLock {
	/**
	 * 释放锁
	 * @throws PandaLockException 
	 * @throws KeeperException 
	 * @throws InterruptedException 
	 */
	public abstract void releaseLock();
	/**
	 * 尝试获得锁，能获得就立马获得锁，如果不能获得就立马返回
	 * @throws PandaLockException 
	 * @throws ConnectException 
	 */
	public abstract boolean tryLock();
	/**
	 * 尝试获得锁，如果有锁就返回，如果没有锁就等待，如果等待了一段时间后还没能获取到锁，那么就返回
	 * @param timeout 单位：秒
	 * @return
	 */
	public abstract boolean tryLock(int timeout);
	/**
	 * 尝试获得锁，一直阻塞，直到获得锁为止
	 * @param timeout 单位：秒
	 * @throws PandaLockException 
	 * @throws ConnectException 
	 */
	public abstract void lock() ;
}
