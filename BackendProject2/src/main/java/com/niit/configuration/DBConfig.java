package com.niit.configuration;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;









import com.niit.modal.BlogComment;
import com.niit.modal.BlogPicture;
import com.niit.modal.BlogPost;
import com.niit.modal.Chat;
import com.niit.modal.Friend;
import com.niit.modal.Job;
import com.niit.modal.ProfilePic;
//import com.fasterxml.classmate.AnnotationConfiguration;
import com.niit.modal.User;



@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig 
{
	//create an instance
	       @Autowired
			@Bean
			public SessionFactory sessionFactory() {
				LocalSessionFactoryBuilder lsf=new LocalSessionFactoryBuilder(getDataSource());
				Properties hibernateProperties=new Properties();
				hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
				hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
				hibernateProperties.put("hibernate.show_sql", "true");
				lsf.addProperties(hibernateProperties);
				Class classes[]=new Class[]{User.class,Job.class,BlogPost.class,ProfilePic.class,BlogComment.class,Friend.class,BlogPicture.class};


			//	new AnnotationConfiguration().configure().buildSessionFactory();
			    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
			}
			@Bean
			public DataSource getDataSource() 
			{
				 DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			    dataSource.setUsername("hr");
			    dataSource.setPassword("hr");
				
			    return dataSource;
			    
			}
			

			
			@Bean
			public HibernateTransactionManager hibTransManagement()
			{
				return new HibernateTransactionManager(sessionFactory());
			}

}
