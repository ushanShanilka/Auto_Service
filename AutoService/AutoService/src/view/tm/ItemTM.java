package view.tm;

import com.jfoenix.controls.JFXButton;

public class ItemTM {
    private String code;
    private String description;
    private String unitprice;
    private String QtyOnHand;
    private JFXButton btn;

    public ItemTM(String code, String description, String unitprice, String qtyOnHand, JFXButton btn) {
        this.code = code;
        this.description = description;
        this.unitprice = unitprice;
        QtyOnHand = qtyOnHand;
        this.btn = btn;
    }

    public ItemTM() {
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitprice='" + unitprice + '\'' +
                ", QtyOnHand='" + QtyOnHand + '\'' +
                ", btn=" + btn +
                '}';
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public String getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
