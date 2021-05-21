package spring5_webmvc_bean_validation_study.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"spring5_webmvc_bean_validation_study.spring"})
@EnableTransactionManagement
public class MemberConfig {	
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5?useSSL=false");
		ds.setUsername("user_spring5");
		ds.setPassword("rootroot");
		ds.setInitialSize(2);		//초기 커넥션 개수 기본값 10개 //조금 느려지면 더 많이 생성해야 함
		ds.setMaxIdle(10);			//커넥션 풀에 유지할수 있는 최대 커넥션 개수 기본값은 maxActive와 같다
		ds.setMaxActive(10);		//커넥션 풀에서 가져올 수 있는 최대 커넥션 개수 기본값은 100
		ds.setTestWhileIdle(true); //유휴 커넥션 검사
		ds.setMinEvictableIdleTimeMillis(100*60 * 3); //최소 유휴시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 유휴 커넥션 검사 주기 10초 주기 
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
}
