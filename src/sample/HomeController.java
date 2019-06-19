import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Connector;
import sample.Contact;
import sample.Controller;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_search;

    @FXML
    private Button btn_find;

    @FXML
    private ListView<Contact> list_list;

    @FXML
    private TableView<Contact> tbl_list;

    @FXML
    private TableColumn<Contact, String> col_conts;

    @FXML
    private Button btn_view;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_refresh;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_ph;

    @FXML
    private TextField txt_ph2;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_address;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_clear;
    Controller h = new Controller();

    @FXML
    void onClick_refresh(ActionEvent event){
        populateList();
    }

//    list_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    @FXML
    void onClick_add(ActionEvent event) {

        try {
            String nname = txt_name.getText();
            String nphone = txt_ph.getText();
            String nphone2 = txt_ph2.getText();
            String nemail = txt_email.getText();
            String naddress = txt_address.getText();
            Long id = System.currentTimeMillis();
            String uid = Long.toString(id);

            Connector c = new Connector();
            Connection con = c.makeConnection();

            PreparedStatement st = con.prepareStatement("insert into contacts values(?, ?, ?, ?, ?, ?)");
            st.setString(1, uid);
            st.setString(2, nname);
            st.setString(3, nphone);
            st.setString(4, nphone2);
            st.setString(5, nemail);
            st.setString(6, naddress);

            int i = st.executeUpdate();
            if(i != 0) System.out.println("entered value!");
            else System.out.println("entry failure");
            st.close();
            clearFields();
            populateList();
        }catch (Exception ex){
            System.out.println(ex);

        }
    }

    @FXML
    void onClick_view(ActionEvent event){
        Contact ct = list_list.getSelectionModel().getSelectedItem();
        txt_name.setText(ct.getName());
        txt_email.setText(ct.getEmail());
        txt_ph.setText(ct.getPhone());
        txt_ph2.setText(ct.getPhone2());
        txt_address.setText(ct.getAddress());

    }
    void clearFields(){
        txt_address.setText("");
        txt_email.setText("");
        txt_name.setText("");
        txt_ph.setText("");
        txt_ph2.setText("");
    }

    @FXML
    void onClick_delete(){
        try {
            Contact ct = list_list.getSelectionModel().getSelectedItem();
            String uid = ct.getUid();
            String q = "delete from contacts where id = '" + uid + "';";
            Connector c = new Connector();
            Connection con = c.makeConnection();

            PreparedStatement st = con.prepareStatement(q);

            int i = st.executeUpdate();
            if(i != 0) System.out.println("removed value!");
            else System.out.println("remove failure");
            st.close();
            clearFields();
            populateList();
        }catch (Exception ex){
            System.out.println(ex);

        }
    }
    @FXML
    void onClick_update(){
        try {
            String nname = txt_name.getText();
            String nphone = txt_ph.getText();
            String nphone2 = txt_ph2.getText();
            String nemail = txt_email.getText();
            String naddress = txt_address.getText();
//            Long id = System.currentTimeMillis();
            Contact ct = list_list.getSelectionModel().getSelectedItem();
            String uid = ct.getUid();
            String q = "update contacts set name = ?, phone = ?, phone2 = ?, email = ?, address = ? " +
                    "where id = '" + uid + "';";
            Connector c = new Connector();
            Connection con = c.makeConnection();

            PreparedStatement st = con.prepareStatement(q);
            st.setString(1, nname);
            st.setString(2, nphone);
            st.setString(3, nphone2);
            st.setString(4, nemail);
            st.setString(5, naddress);

            int i = st.executeUpdate();
            if(i != 0) System.out.println("updated value!");
            else System.out.println("update failure");
            st.close();
            clearFields();
            populateList();
        }catch (Exception ex){
            System.out.println(ex);

        }
    }
    @FXML
    public void onClick_find(ActionEvent event){
        ObservableList<Contact> searchResults = FXCollections.observableArrayList();
        try {
            String entry = txt_search.getText();
            String q = "select * from contacts where name like '%" + entry + "%';";
            Connector c = new Connector();
            Connection con = c.makeConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);

            while (rs.next()) {
                String uid = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String phone2 = rs.getString("phone2");
                String email = rs.getString("email");
                String address = rs.getString("address");
                searchResults.add(new Contact(uid, name, phone, phone2, email, address));
            }
        }catch (Exception ex){
            System.out.println("search Error" + ex);
        }
        list_list.setItems(searchResults);
    }
    @FXML
    void onClick_clear(ActionEvent event){
        clearFields();
    }
//    void populateTable(){
//        col_conts.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
//        tbl_list.setItems(h.fillList());
//    }
    void populateList(){
        list_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
          list_list.setItems(h.fillList());
    }
    @FXML
    void initialize() {

        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_find != null : "fx:id=\"btn_find\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_view != null : "fx:id=\"btn_view\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_delete != null : "fx:id=\"btn_delete\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_refresh != null : "fx:id=\"btn_refresh\" was not injected: check your FXML file 'home.fxml'.";
        assert tbl_list != null : "fx:id=\"tbl_list\" was not injected: check your FXML file 'home.fxml'.";
        assert col_conts != null : "fx:id=\"col_conts\" was not injected: check your FXML file 'home.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'home.fxml'.";
        assert txt_ph != null : "fx:id=\"txt_ph\" was not injected: check your FXML file 'home.fxml'.";
        assert txt_ph2 != null : "fx:id=\"txt_ph2\" was not injected: check your FXML file 'home.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'home.fxml'.";
        assert txt_address != null : "fx:id=\"txt_address\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_add != null : "fx:id=\"btn_add\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_update != null : "fx:id=\"btn_update\" was not injected: check your FXML file 'home.fxml'.";
        assert btn_clear != null : "fx:id=\"btn_clear\" was not injected: check your FXML file 'home.fxml'.";
        assert list_list != null : "fx:id=\"list_list\" was not injected: check your FXML file 'home.fxml'.";
      //  populateTable();
        populateList();
    }
}
