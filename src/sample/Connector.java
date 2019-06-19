package sample;
import java.sql.*;
public class Connector {

    public static Connection makeConnection(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kauntacts", "root", "");
            return con;
        }catch (Exception e){
            System.out.println("Connection Error: " + e);
        }
        return null;
    }

}
