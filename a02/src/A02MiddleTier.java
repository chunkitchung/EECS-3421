import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Properties;
import java.sql.*;

public class A02MiddleTier {
	public A02MiddleTier() {
		// Connect to database
		ResultSet data = null;
		
		System.out.println("CONNECTING TO DB");
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a02schema", "root", "root1234");

			
			System.out.println(c.getCatalog());
			System.out.println(c.toString());
			Statement command = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			data = command.executeQuery("Select Name FROM EVENT");
		} catch (SQLException e) {
			System.out.println("SQL CONNECTION ERROR");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			System.out.println("CONNECTION AND QUERY SUCESS");
			try {
				if(data.first()) {
					while(data.next()) {
						System.out.println("Event Name: " + data.getString("Name"));
					}
				}	
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	// This class will contain your code for interacting with Database
	public void Query() {
		
	}
}
