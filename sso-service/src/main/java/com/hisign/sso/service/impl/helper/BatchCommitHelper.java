package com.hisign.sso.service.impl.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esotericsoftware.minlog.Log;



/**
 * @Title: 
 *    批量提交帮助类
 *    批量提交，防止数据量大而导致阻塞
 * @description:
 * @author lnj
 */
@Component
public class BatchCommitHelper {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public final int DEFAULT_COMMIT_COUNT = 1000;
	
	private SqlSessionFactory getSqlSessionFactory(){
		return SpringContextUtil.getBean("sqlSessionFactory");
	}
	
	
	/**
	 * 批量添加
	 * @param mapperClass
	 * @param list
	 */
	public <T> void addBatch(Class mapperClass, List<T> list) {
		this.batchCommit(mapperClass, "addBatch", list, DEFAULT_COMMIT_COUNT);
	}
	
	
	/**
	 * 批量修改
	 * @param mapperClass
	 * @param list
	 */
	public <T> void updateBatch(Class mapperClass, List<T> list) {
		this.batchCommit(mapperClass, "updateBatch", list, DEFAULT_COMMIT_COUNT);
	}
	
	/**
	 * 批量修改
	 * @param mapperClass
	 * @param list
	 */
	public <T> void deleteBatch(Class mapperClass, List<T> list) {
		this.batchCommit(mapperClass, "deleteBatch", list, DEFAULT_COMMIT_COUNT);
	}
	
	/**
	 * 批量添加
	 * @param mapperClass
	 * @param list
	 * @param commitCountEveryTime
	 */
	public <T> void addBatch(Class mapperClass, List<T> list, int commitCountEveryTime) {
		this.batchCommit(mapperClass, "addBatch", list, commitCountEveryTime);
	}
	
	/**
	 * @param mapperClass Mapper类名
	 * @param methodId  方法名
	 * @param list  批量提交的数据
	 * @param commitCountEveryTime 每次提交数量
	 */
	public <T> void batchCommit(Class mapperClass, String methodId, List<T> list){
		this.batchCommit(mapperClass, methodId, list,DEFAULT_COMMIT_COUNT);
	}

	/**
	 * @param mapperClass Mapper类名
	 * @param methodId  方法名
	 * @param list  批量提交的数据
	 * @param commitCountEveryTime 每次提交数量
	 */
	public <T> void batchCommit(Class mapperClass, String methodId, List<T> list, int commitCountEveryTime) {
		if(mapperClass == null || methodId == null){
			throw new RuntimeException("mapperClass or methodId is null");
		}

		String absClassPath = mapperClass.getName();
		String mybatisSQLId = absClassPath+"."+methodId;
		this.batchCommit(this.getSqlSessionFactory(), mybatisSQLId, commitCountEveryTime, list);
	}

	/*
	 * 批量提交数据
	 * @param sqlSessionFactory
	 * @param mybatisSQLId SQL语句在Mapper XML文件中的ID
	 * @param commitCountEveryTime 每次提交的记录数
	 * @param list 要提交的数据列表
	 * @param logger 日志记录器
	 */
	private <T> void batchCommit(SqlSessionFactory sqlSessionFactory, String mybatisSQLId, int commitCountEveryTime,
			List<T> list) {
		SqlSession session = null;
		try {
			int size = list.size();
			logger.info(mybatisSQLId+" begin to batchCommit size="+size+" ...");
			session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int commitCount = (int) Math.ceil(size / (double) commitCountEveryTime);
			List<T> tempList = new ArrayList<T>(commitCountEveryTime);
			int start, stop;
			Long startTime = System.currentTimeMillis();
			for (int i = 0; i < commitCount; i++) {
				tempList.clear();
				start = i * commitCountEveryTime;
				stop = Math.min(i * commitCountEveryTime + commitCountEveryTime - 1, list.size() - 1);
				for (int j = start; j <= stop; j++) {
					tempList.add(list.get(j));
				}
				session.insert(mybatisSQLId, tempList);
				session.commit();
				session.clearCache();
				Log.info(mybatisSQLId+" commit i="+(i+1)+" count="+commitCountEveryTime+" completely!");
			}
			Long endTime = System.currentTimeMillis();
			logger.info(mybatisSQLId+" complete batchCommit size="+size+" during：" + (endTime - startTime) + "ms");
		} catch (Exception ex) {
			logger.error("batchCommit error!", ex);
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
