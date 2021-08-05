package view.tm;

import com.jfoenix.controls.JFXButton;

public class ServiceTypeTM {
    private String TypeId;
    private String Name;
    private String Details;
    private JFXButton btn;

    public ServiceTypeTM(String typeId, String name, String details, JFXButton btn) {
        TypeId = typeId;
        Name = name;
        Details = details;
        this.btn = btn;
    }

    public ServiceTypeTM() {
    }

    @Override
    public String toString() {
        return "ServiceTypeTM{" +
                "TypeId='" + TypeId + '\'' +
                ", Name='" + Name + '\'' +
                ", Details='" + Details + '\'' +
                ", btn=" + btn +
                '}';
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
