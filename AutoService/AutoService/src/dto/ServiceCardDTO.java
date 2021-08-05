package dto;

public class ServiceCardDTO {
    private String ScardID;
    private String date;
    private String CID;
    private String Vnum;
    private String UsesCode;
    private String TypeID;

    public ServiceCardDTO ( ) {
    }

    public ServiceCardDTO ( String scardID, String date, String CID, String vnum, String usesCode, String typeID ) {
        ScardID = scardID;
        this.date = date;
        this.CID = CID;
        Vnum = vnum;
        UsesCode = usesCode;
        TypeID = typeID;
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

    @Override
    public String toString ( ) {
        return "ServiceCardDTO{" +
               "ScardID='" + ScardID + '\'' +
               ", date='" + date + '\'' +
               ", CID='" + CID + '\'' +
               ", Vnum='" + Vnum + '\'' +
               ", UsesCode='" + UsesCode + '\'' +
               ", TypeID='" + TypeID + '\'' +
               '}';
    }
}