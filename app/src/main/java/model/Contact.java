package model;

/**
 * Created by aDMIN on 5/11/2016.
 */
public class Contact {

    String name;
    String phone;
    String email;

    public Contact() {

    }

    public Contact(String name, String phone, String email) {
        this.email = email;
        this.phone = phone;
        this.name = name;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
