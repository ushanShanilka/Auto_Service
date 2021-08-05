package view.tm;

import com.jfoenix.controls.JFXButton;

public class PaymentTM {
    private String PID;
    private String ScardID;
    private String CID;
    private String Vnum;
    private String UseCode;
    private String Total;

    public PaymentTM ( ) {
    }

    public PaymentTM ( String PID, String scardID, String CID, String vnum, String useCode, String total ) {
        this.PID = PID;
        ScardID = scardID;
        this.CID = CID;
        Vnum = vnum;
        UseCode = useCode;
        Total = total;
    }

    public String getPID ( ) {
        return PID;
    }

    public void setPID ( String PID ) {
        this.PID = PID;
    }

    public String getScardID ( ) {
        return ScardID;
    }

    public void setScardID ( String scardID ) {
        ScardID = scardID;
    }

    public String getCID ( ) {
        return CID;
    }

    public void setCID ( String CID ) {
        this.CID = CID;
    }

    public String getVnum ( ) {
        return Vnum;
    }

    public void setVnum ( String vnum ) {
        Vnum = vnum;
    }

    public String getUseCode ( ) {
        return UseCode;
    }

    public void setUseCode ( String useCode ) {
        UseCode = useCode;
    }

    public String getTotal ( ) {
        return Total;
    }

    public void setTotal ( String total ) {
        Total = total;
    }

    @Override
    public String toString ( ) {
        return "PaymentTM{" +
               "PID='" + PID + '\'' +
               ", ScardID='" + ScardID + '\'' +
               ", CID='" + CID + '\'' +
               ", Vnum='" + Vnum + '\'' +
               ", UseCode='" + UseCode + '\'' +
               ", Total='" + Total + '\'' +
               '}';
    }
}
