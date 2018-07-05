
package ocr_3;

import java.sql.*;

public class DbCon {
    static public Connection connection;

    public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
    public static final String JDBC_URL="jdbc:mysql://localhost/ocr";
    public static final String DB_USER="root";
    public static final String DB_PASSWORD="root";
    
    public static Connection getDBConnection()
    {
        Connection conn=null;
        try{
            Class.forName(DRIVER_NAME);
            conn=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            System.out.print("Database is connected!\n");
            conn.close();
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(Exception e){
            System.out.print("Do not connect to DB - Error: "+e);
        }
       return conn;
    }
    
}
