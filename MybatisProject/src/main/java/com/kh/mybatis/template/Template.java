package com.kh.mybatis.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	/**
	 * 기존 JDBC
	 * 
	 * public static Connection getConnection() {
	 * 		// driver.properties 파일을 읽어서
	 * 		// 해당 DB와 접속된 Connection 객체 생성 후 반환
	 * }
	 * 
	 * public static void close(JDBC 객체) {
	 * 		// 전달받은 객체 반납
	 * }
	 * 
	 * public static void commit | rollback(Connection 객체) {
	 * 		// 트랜잭션 처리
	 * }
	 */
	
	// Mybatis
	public static SqlSession getSqlSession() {
		// ibatis : mybatis의 이전 이름
		
		// mybatis-config.xml 파일을 읽어서
		// 해당 DB와 접속된 SqlSession 객체 생성 후 반환
		
		SqlSession sqlSession = null;
		
		/**
		 * SqlSession 객체를 생성하기 위해서 -> SqlSessionFactory 객체 필요
		 * SqlSessionFactory 객체를 생성하기 위해서 -> SqlSessionFactoryBuilder 객체 필요
		 */
		
		String resource = "/mybatis-config.xml"; // source folder 내에 해당 파일 위치

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			// SqlSession 객체 생성
			sqlSession = sqlSessionFactory.openSession();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
