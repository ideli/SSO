/**
 * 
 */
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author chailiangzhi
 * @date 2016-3-4
 * 
 */
public class HiveJdbc {
	//基础配置
	private static final String IMPALAD_HOST = "192.168.1.104";
	//	private static final String IMPALAD_JDBC_PORT = "9083";
	private static final String IMPALAD_JDBC_PORT = "21050";
	private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT
			+ "/;auth=noSasl;";
	//phonefraud
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

	//SQL语句
	private static final String SQL_STATEMENTIn = "select distinct(c.bank_card_number) from phonefraud.parquet_all_trading p join phonefraud.card_num c on c.bank_card_number=p.bank_card_number";
	//查询对方账号
	private static final String SQL_STATEMENTIn_F = "select distinct(c.bank_card_number) from phonefraud.parquet_all_trading p join phonefraud.card_num c on c.bank_card_number=p.target_bank_card_number";
	//private static final String SQL_STATEMENTIn = "select distinct(c.bank_card_number) from phonefraud.parquet_all_trading p join phonefraud.card_num c on c.bank_card_number=p.target_bank_card_number";

	private static final String SQL_STATEMENTAll = "select * from phonefraud.card_num";
	public static Connection con = null;
	public static Statement stmt = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Class.forName(JDBC_DRIVER_NAME);
		Connection con = DriverManager.getConnection(CONNECTION_URL, "xxx", "xxx");
		Statement stmt = con.createStatement();
		//		ResultSet rs = stmt.executeQuery(SQL_STATEMENTAll + " where bank_card_number like '%17107'");
		long startTime = System.currentTimeMillis();
		ResultSet rs = stmt.executeQuery("select count(0) bank_card_number from phonefraud.parquet_all_trading");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		while (rs.next()) {
			endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
			System.out.println(rs.getString("bank_card_number"));
		}

	}

}
