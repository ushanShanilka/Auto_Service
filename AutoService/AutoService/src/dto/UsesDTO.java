package dto;

public class UsesDTO {
    private String UseCode;
    private String Code;
    private String Discription;
    private String UnitPrice;
    private String Qty;
    private String Total;

    @Override
    public String toString() {
        return "UsesDTO{" +
                "UseCode='" + UseCode + '\'' +
                ", Code='" + Code + '\'' +
                ", Discription='" + Discription + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", Qty='" + Qty + '\'' +
                ", Total='" + Total + '\'' +
                '}';
    }

    public UsesDTO(String text, String txtCodeText, String txtDiscriptionText, String txtUnitPriceText, String txtTotalText) {
    }

    public UsesDTO(String useCode, String code, String discription, String unitPrice, String qty, String total) {
        UseCode = useCode;
        Code = code;
        Discription = discription;
        UnitPrice = unitPrice;
        Qty = qty;
        Total = total;
    }

    public String getUseCode() {
        return UseCode;
    }

    public void setUseCode(String useCode) {
        UseCode = useCode;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
