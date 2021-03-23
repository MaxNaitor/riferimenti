package util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapFactory {
	
	private final static String resource = "config/sql-map-config.xml";
	
	private static SqlSessionFactory sqlSessionFactory;
	
	 static
	 {
	  Reader reader = null;
	  try {
	   reader = Resources.getResourceAsReader(resource);
	  } catch (IOException e) {
	   throw new RuntimeException(e.getMessage());
	  }
	  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	 }
	 
	 public static SqlSessionFactory getSqlSessionFactory () {
		 return sqlSessionFactory;
	 }
	 
	private SqlMapFactory () {
		
	}
}
