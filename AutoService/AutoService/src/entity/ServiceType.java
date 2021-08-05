package entity;

import com.jfoenix.controls.JFXButton;

public class ServiceType implements SuperEntity{
    private String TypeId;
    private String Name;
    private String Details;
    private JFXButton btn;

    public ServiceType(String typeId, String name, String details) {
        TypeId = typeId;
        Name = name;
        Details = details;
        this.btn = btn;
    }

    @Override

    public String toString() {
        return "ServiceType{" +
                "TypeId='" + TypeId + '\'' +
                ", Name='" + Name + '\'' +
                ", Details='" + Details + '\'' +
                ", btn=" + btn +
                '}';
    }

    public ServiceType() {
    }



    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
