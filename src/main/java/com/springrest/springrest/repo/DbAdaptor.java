package com.springrest.springrest.repo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import com.springrest.springrest.modal.Content;
import com.springrest.springrest.modal.MailInfo;

@Component
public class DbAdaptor {

	BasicDataSource ds = new BasicDataSource();
	
	public DbAdaptor() throws IOException {
		Properties prop = new Properties();
		prop.load(DbAdaptor.class.getClassLoader().getResourceAsStream("application.properties"));
		
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(prop.getProperty("datasource.url"));
		ds.setPassword(prop.getProperty("datasource.password"));
		ds.setUsername(prop.getProperty("datasource.username"));
		System.out.println(prop.getProperty("datasource.url") + " " + prop.getProperty("datasource.password") + " " + prop.getProperty("datasource.username"));
		ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);  
        this.ds = ds;
	}
	
	/**Method to add user email
	 * @param email of the user
	 * @return true if user is created else false
	 */
	public Integer createUser(String email){
		String query = "insert into users (email_id) values(?) on duplicate key update email_id = ?";
        try (Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, email);
            statement.executeUpdate();
            
            String select_query = "SELECT id FROM users WHERE email_id = ?";
            PreparedStatement select_statement = connection.prepareStatement(select_query);
            select_statement.setString(1, email);
            ResultSet rs = select_statement.executeQuery();
            if(rs.next()) {
            	Integer user_id = rs.getInt(1);
            	return user_id;
            }
            
        } catch (SQLException sqle) {
        	System.out.println("error " + sqle);
        }
        return -1;
	}

	public boolean addTopicUserMapping(Integer user_id, List<Integer> topic_ids) {
		String query = "insert ignore into user_topic_mappings (user_id, topic_id) values(?,?)";
		try {
			Connection connection = this.ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			for(Integer topic : topic_ids) {
				statement.setLong(1, user_id);
				statement.setLong(2, topic);
				statement.executeUpdate();	
			}
		}catch (SQLException sqle) {
        	System.out.println("error " + sqle);
        	return false;
        }
		return true;
	}

	public boolean addContent(Content news_content) {
		String query = "insert into topic_content_mappings (topic_id, content_title, content_text, live_date) values(?,?,?,?)";
		try {
			Connection connection = this.ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, news_content.getTopic_id());
			statement.setString(2, news_content.getContent_title());
			statement.setString(3, news_content.getContent_textString());
			statement.setString(4, news_content.getLive_date());
			statement.executeUpdate();
		}catch (SQLException sqle) {
        	System.out.println("error " + sqle);
        	return false;
        }
		return true;
	}

	public List<MailInfo> getEmailData() {
		
		List<MailInfo> output = new ArrayList<MailInfo>();
		String query = "select u.email_id, tcm.content_title, tcm.content_text, tcm.live_date from topic_content_mappings tcm join topics t " 
				+ "on t.id = tcm.topic_id join user_topic_mappings utm on utm.topic_id = t.id join users u on u.id = utm.user_id and " 
				+ "live_date between (now() - interval 1 hour) and now() order by tcm.live_date";
		try (Connection connection = this.ds.getConnection();
	            PreparedStatement statement = connection.prepareStatement(query)) {
	            System.out.println("statement " + statement);
	            try (ResultSet rs = statement.executeQuery()) {
	                while(rs.next()) {
	                	MailInfo info = new MailInfo();
	                	info.setReceiver(rs.getString(1));
	                	info.setSubject(rs.getString(2));
	                	info.setBody(rs.getString(3));
	                	output.add(info);
	                }
	            }
	        } catch (SQLException sqle) {
	        	System.out.println("error " + sqle);
	        }
		
		return output;
	}
}
