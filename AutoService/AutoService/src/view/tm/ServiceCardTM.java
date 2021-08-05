package view.tm;


import com.jfoenix.controls.JFXButton;

public class ServiceCardTM {
    private String ScardID;
    private String date;
    private String CID;
    private String Vnum;
    private String UsesCode;
    private String TypeID;
    private JFXButton btn;

    public ServiceCardTM ( ) {
    }

    public ServiceCardTM ( String scardID, String date, String CID, String vnum, String usesCode, String typeID, JFXButton btn ) {
        ScardID = scardID;
        this.date = date;
        this.CID = CID;
        Vnum = vnum;
        UsesCode = usesCode;
        TypeID = typeID;
        this.btn = btn;
    }

    public String getScardID ( ) {
        return ScardID;
    }

    public void setScardID ( String scardID ) {
        ScardID = scardID;
    }

    public String getDate ( ) {
        return date;
    }

    public void setDate ( String date ) {
        this.date = date;
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

    public String getUsesCode ( ) {
        return UsesCode;
    }

    public void setUsesCode ( String usesCode ) {
        UsesCode = usesCode;
    }

    public String getTypeID ( ) {
        return TypeID;
    }

    public void setTypeID ( String typeID ) {
        TypeID = typeID;
    }

    public JFXButton getBtn ( ) {
        return btn;
    }

    public void setBtn ( JFXButton btn ) {
        this.btn = btn;
    }

    @Override
    public String toString ( ) {
        return "ServiceCardTM{" +
               "ScardID='" + ScardID + '\'' +
               ", date='" + date + '\'' +
               ", CID='" + CID + '\'' +
               ", Vnum='" + Vnum + '\'' +
               ", UsesCode='" + UsesCode + '\'' +
               ", TypeID='" + TypeID + '\'' +
               ", btn=" + btn +
               '}';
    }
}

