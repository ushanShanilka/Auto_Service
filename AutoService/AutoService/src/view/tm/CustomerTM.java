package view.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class CustomerTM {
    private String CID;
    private String Name;
    private String Address;
    private String Contact;
    private JFXButton btn;

    public CustomerTM() {
    }

    public CustomerTM(String CID, String name, String address, String contact, JFXButton btn) {
        this.CID = CID;
        Name = name;
        Address = address;
        Contact = contact;
        this.btn = btn;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "CID='" + CID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", btn=" + btn +
                '}';
    }
}
