package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {

    public ObservableList<Contact> fillList(){
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        Connector c = new Connector();
        Connection con = c.makeConnection();

        try{
            String q = "select * from contacts;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String uid = rs.getString("id");
                String name = rs.getString("name");
                String ph = rs.getString("phone");
                String ph2 = rs.getString("phone2");
                String email = rs.getString("email");
                String address = rs.getString("address");
                allContacts.add(new Contact(uid, name, ph, ph2, email, address));
            }
        }catch(Exception ex){
            System.out.println("eroor:::::" + ex);
        }
//        allContacts.add(new Contact("1", "a", "b", "c", "d", "e"));
        return allContacts;
    }
}
