package dto;

public class CustomerDTO {
    private String CID;
    private String Name;
    private String Address;
    private String Contact;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "CID='" + CID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }

    public CustomerDTO(String CID, String name, String address, String contact) {
        this.CID = CID;
        this.Name = name;
        this.Address = address;
        this.Contact = contact;
    }


    public CustomerDTO(String text, String txtVnumText, String txtSIDText, String txtDateText, String txtTimeText) {
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
}