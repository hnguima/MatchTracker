import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBase {

  private static DataBase dataBase;
  private Connection conn = null;

  private DataBase() {
  }

  private Connection connect() {

    String url = "jdbc:sqlite:./db/matchtracker.db";

    if (this.conn == null) {
      try {
  
        this.conn = DriverManager.getConnection(url);
        System.out.println("DataBase connected");
  
      } catch (SQLException e) {
  
        System.out.println(e.getMessage());
      }
    }

    return this.conn;
  }

  public static DataBase getInstance() {

    if (dataBase == null) {
      dataBase = new DataBase();
    }

    return dataBase;
  }

  public ResultSet query(String sql){

    ResultSet res = null;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = connect();
      pstmt = conn.prepareStatement(sql);
      res = pstmt.executeQuery();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return res;
  }

  // public String[] query(String column, String table) {

  //   try (PreparedStatement stmt = conn.prepareStatement("SELECT " + column + " FROM " + table)) {

  //     ResultSet res = stmt.executeQuery();
  //     ArrayList<String> list = new ArrayList<String>();

  //     while (res.next()) {
  //       list.add(res.getString(column));
  //     }

  //     return list.toArray(new String[0]);

  //   } catch (SQLException e) {

  //     System.out.println(e.getMessage());

  //   }

  //   return null;
  // }

  // public int[] queryInt(String column, String table) {

  //   try (PreparedStatement stmt = conn.prepareStatement("SELECT " + column + " FROM " + table)) {

  //     ResultSet res = stmt.executeQuery();
  //     ArrayList<Integer> list = new ArrayList<Integer>();

  //     while (res.next()) {
  //       list.add(res.getInt(column));
  //     }

  //     return list.stream().mapToInt(i -> i).toArray();

  //   } catch (SQLException e) {

  //     System.out.println(e.getMessage());

  //   }

  //   return null;
  // }

}
