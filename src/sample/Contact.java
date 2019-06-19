package sample;

public class Contact {
    private String uid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String phone2;


    public Contact(String uid, String name, String phone, String phone2, String email, String address) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.phone2 = phone2;
    }
    public String toString(){
        return name;
    }
    public Contact() {
        this.uid = "";
        this.name = "";
        this.phone = "";
        this.email = "";
        this.address = "";
        this.phone2 = "";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }


}
