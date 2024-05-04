package com.springrest.springrest.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

@Component
public class DbAdaptor {

	BasicDataSource ds = new BasicDataSource();
	
	public DbAdaptor() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/news_letter");
		ds.setPassword("root");
		ds.setUsername("root");
		ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);  
        this.ds = ds;
	}
	
	/**Method to add user email
	 * @param email of the user
	 * @return true if user is created else false
	 */
	public boolean createUser(String email){
		String query = "SELECT id FROM users WHERE email_id = ?";
        try (Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            System.out.println("statement " + statement);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    return false;
                }
                else {
                	String insert_query = "insert into users (email_id) values(?)";
                	PreparedStatement insert_statement = connection.prepareStatement(insert_query);
                	
                	insert_statement.setString(1, email);
                	System.out.println("stmt " + insert_statement);
                	insert_statement.executeUpdate();
                }
            }
        } catch (SQLException sqle) {
        	System.out.println("error " + sqle);
        }
        return true;
	}
}
