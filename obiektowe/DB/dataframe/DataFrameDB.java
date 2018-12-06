package dataframe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class DataFrameDB extends DataFrame{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private String tableName;
	private String table;

	 
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
  
  
  /*public DataFrameDB(String tableName){
      //super(tableName);
      this.tableName = tableName;
      connect();
      System.out.println("Connected successfully");
  }*/

  public DataFrameDB(String fileName, Class<? extends Value>[] types, boolean header, String tableName) throws IOException{
      super(fileName,types);
      connect();
      this.tableName = tableName;
      DataFrame dataFrame = new DataFrame(fileName,types);
      this.lista_typow = types;
      outer = dataFrame.ramka();
      createTable();
      insertDataFrame(dataFrame);

      System.out.println(this.tableName);
  }

  /*public DataFrameDB(DataFrame dataFrame, String tableName){
      this.tableName = tableName;
      connect();
      this.lista_typow = dataFrame.zwroc_typy();
      outer = dataFrame.ramka();
      createTable();
      insertDataFrame(dataFrame);
  }*/
  private void insertDataFrame(DataFrame dataFrame){
      PreparedStatement pstmt = null;
      for (int i=0; i< dataFrame.size(); i++){
          Value[] row = dataFrame.wiersz(i);

          String sql = "INSERT INTO " + tableName + " VALUES (";
          for(int k=0; k<this.size(); k++) {
              sql += "?,";
          }
          sql = sql.substring(0,sql.length()-1);
          sql += ")";

          try {
              pstmt = conn.prepareStatement(sql);


              int k = 1;
              for (Value value : row) {
                //  pstmt.setObject(k, value.getValue());
                  k++;
              }
              pstmt.execute();

          } catch (SQLException e) {
              e.printStackTrace();
          }

      }
  }
  
  
  public void createTable(){

      try {
          System.out.println(conn);
        //  System.out.println(outer.get(i));
          stmt = conn.createStatement();
          String query = "CREATE TABLE " + tableName + " (";
          for (int i = 0; i < this.size(); i++) {
              if (lista_typow[i] ==IntegerV.class) {
                  query += outer.get(i) + " INT ";
              } else if (lista_typow[i] == DoubleV.class) {
                  query += outer.get(i)  + " DOUBLE ";
              } else if (lista_typow[i] == FloatV.class) {
                  query += outer.get(i)  + " FLOAT ";
              } else if (lista_typow[i] == StringV.class) {
                  query += outer.get(i)  + " VARCHAR(256) ";
             // } else if (lista_typow[i] == BooleanV.class) {
             //     query += outer.get(i) + " BOOLEAN ";
              } else if (lista_typow[i] == DateTimeV.class) {
                  query += outer.get(i)  + " DATE ";
              } else {
                  query += outer.get(i) + " NULL ";
              }
              query += ", ";
          }
          query = query.substring(0,query.length()-2);
          query += " ) ";
          System.out.println(query);
          stmt.executeUpdate(query);

      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public void dropTable() {
      try {
          stmt=conn.createStatement();
          String query="DROP TABLE " + tableName;
          stmt.executeUpdate(query);

      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
