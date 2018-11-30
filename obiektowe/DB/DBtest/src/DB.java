

import java.sql.*;
 
public class DB{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	 
  public void connect(){
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = 
        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/rychter",
                                    "rychter","zgPNWe7amcq2VsQ1");
 
 
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }catch(Exception e){e.printStackTrace();}	
  }
  
  public void listAll(){
	    try {
	      connect();
	      stmt = conn.createStatement();
	 
	      rs = stmt.executeQuery("SELECT * FROM books");
	 
	      while(rs.next()){
	    	 
	    		String name = rs.getString(1);
	    		String title = rs.getString(2);
	    		String author = rs.getString(3);
	    		String year = rs.getString(4);
	    		System.out.println(name+" "+title+" "+author+" "+year);
	    	  
	      }
	    }catch (SQLException ex){
	      // handle any errors
	 
	    }finally {
	      // zwalniamy zasoby, które nie będą potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }
  
  public void byNumber(String nr){
	    try {
	      connect();
	      stmt = conn.createStatement();
	 
	   
	      rs = stmt.executeQuery("SELECT * FROM books WHERE isbn LIKE '"+nr+"'");
	 
	      while(rs.next()){
	        String name = rs.getString(1);
	        System.out.println("książka o numerze: "+nr+": "+rs.getString(2));
	      }
	    }catch (SQLException ex){
	      // handle any errors
	 
	    }finally {
	      // zwalniamy zasoby, które nie będą potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }
  
  public void byAuthor(String au){
	    try {
	      connect();
	      stmt = conn.createStatement();
	 
	   
	      rs = stmt.executeQuery("SELECT title FROM books WHERE author LIKE '%"+au+"'");
	 
	      while(rs.next()){
	        String name = rs.getString(1);
	        System.out.println("książka autorstwa "+au+": "+name);
	      }
	    }catch (SQLException ex){
	      // handle any errors
	 
	    }finally {
	      // zwalniamy zasoby, które nie będą potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }
 /* 
  public void addBook(){
	    stmt = conn.createStatement();
	    stmt.executeUpdate(
	            "INSERT INTO tabela1 (nazwisko) "
	            + "values ('Bobek')");
	  }*/
  
}

