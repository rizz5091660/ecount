package com.sanisoft.ecount.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration extends HikariConfig {
	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.dataSourceClassName}")
	private String dataSourceClassName;

	@Value("${spring.datasource.poolName}")
	private String poolName;

	@Value("${spring.datasource.connectionTimeout}")
	private int connectionTimeout;

	@Value("${spring.datasource.maxLifetime}")
	private int maxLifetime;

	@Value("${spring.datasource.maximumPoolSize}")
	private int maximumPoolSize;

	@Value("${spring.datasource.minimumIdle}")
	private int minimumIdle;

	@Value("${spring.datasource.idleTimeout}")
	private int idleTimeout;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		Properties dsProps = new Properties();
		dsProps.put("url", dataSourceUrl);
		dsProps.put("user", user);
		dsProps.put("password", password);
		dsProps.put("prepStmtCacheSize", 250);
		dsProps.put("prepStmtCacheSqlLimit", 2048);
		dsProps.put("cachePrepStmts", Boolean.TRUE);
		dsProps.put("useServerPrepStmts", Boolean.TRUE);

		Properties configProps = new Properties();
		configProps.put("dataSourceClassName", dataSourceClassName);
		configProps.put("poolName", poolName);
		configProps.put("maximumPoolSize", maximumPoolSize);
		configProps.put("minimumIdle", minimumIdle);
		configProps.put("minimumIdle", minimumIdle);
		configProps.put("connectionTimeout", connectionTimeout);
		configProps.put("idleTimeout", idleTimeout);
		configProps.put("dataSourceProperties", dsProps);

		HikariConfig hc = new HikariConfig(configProps);
		HikariDataSource ds = new HikariDataSource(hc);
		return ds;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

}
