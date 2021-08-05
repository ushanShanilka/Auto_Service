package view.tm;

import com.jfoenix.controls.JFXButton;

public class VehicleTM {
    private String Vnum;
    private String Model;
    private String Milage;
    private String YEAR;
    private JFXButton btn;

    @Override
    public String toString() {
        return "VehicleTM{" +
                "Vnum='" + Vnum + '\'' +
                ", Model='" + Model + '\'' +
                ", Milage='" + Milage + '\'' +
                ", YEAR='" + YEAR + '\'' +
                ", btn=" + btn +
                '}';
    }

    public VehicleTM() {
    }

    public VehicleTM(String vnum, String model, String milage, String YEAR, JFXButton btn) {
        this.Vnum = vnum;
        this.Model = model;
        this.Milage = milage;
        this.YEAR = YEAR;
        this.btn = btn;
    }

    public String getVnum() {
        return Vnum;
    }

    public void setVnum(String vnum) {
        Vnum = vnum;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getMilage() {
        return Milage;
    }

    public void setMilage(String milage) {
        Milage = milage;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
