package com.cabemv1.connections;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MysqlDataSourceConn {

	public static MysqlDataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
    	dataSource.setUser("sql12211685");
    	dataSource.setPassword("eU7XBtIU1c");
    	dataSource.setServerName("sql12.freemysqlhosting.net");
    	dataSource.setDatabaseName("sql12211685");
    	return dataSource;
	}
}
