import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class A02MiddleTier {
	public A02MiddleTier() {
		

	}

	// This class will contain your code for interacting with Database
	public String query(String from, String to, Boolean conference, Boolean journal, Boolean book) {
		String commandStr = "";
		String output = "";
		
		if(!from.equals("")) {
			//Date Range Selected 
			ResultSet data = null;
			
			System.out.println("CONNECTING TO DB");
			try {
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a02schema", "root", "root1234");

				
				System.out.println(c.getCatalog());
				System.out.println(c.toString());
				Statement command = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				commandStr = "Select Name, ActivityDate  FROM Event Join ActivityHappens on ID = EventID WHERE ActivityDate between '" + from + "' AND '" + to + "'";
				output = output + "Query:\n" + commandStr + "\nOutput:\n";
				data = command.executeQuery(commandStr);
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
							output =  output + "Event Name: " + data.getString("Name") + "\t" + data.getString("ActivityDate") + "\n";

						}
					}	
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return output;
		}else {
			//All Events Selected
			System.out.println("NO DATE SELECTED ");
			// Connect to database
			ResultSet data = null;
			commandStr = "SELECT Name FROM EVENT";
			
			System.out.println("CONNECTING TO DB");
			try {
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a02schema", "root", "root1234");

				
				System.out.println(c.getCatalog());
				System.out.println(c.toString());
				Statement command = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				data = command.executeQuery(commandStr);
				output = output + "Query:\n" + commandStr + "\nOutput:\n";
				System.out.println("OUT: " + output);
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
							output =  output + "Event Name: " + data.getString("Name") + "\n";
						}
					}	
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return output;

		}
	}
}
