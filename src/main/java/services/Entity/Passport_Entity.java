package services.Entity;

import java.io.InputStream;
import java.sql.Blob;

public class Passport_Entity {
    private int id ;
    private String dateOfBirth;
    private String countryOfBirth;
    private String regionOfBirth;
    private String cityOfBirth;
    private int documentNo;
    private String scannedPassport;
    private InputStream inputStream;


    public Passport_Entity(int id, String dateOfBirth, String countryOfBirth, String regionOfBirth, String cityOfBirth, int documentNo) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.regionOfBirth = regionOfBirth;
        this.cityOfBirth = cityOfBirth;
        this.documentNo = documentNo;
    }

    BlobToString blobToString = new BlobToString();
    public String getScannedPassport() {
        return scannedPassport;
    }

    public void setScannedPassport(Blob blob) {
        scannedPassport = blobToString.getStringFromBlob(blob);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getRegionOfBirth() {
        return regionOfBirth;
    }

    public void setRegionOfBirth(String regionOfBirth) {
        this.regionOfBirth = regionOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public int getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(int documentNo) {
        this.documentNo = documentNo;
    }

    @Override
    public String toString() {
        return "Passport_Entity{" +
                "id=" + id +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", regionOfBirth='" + regionOfBirth + '\'' +
                ", documentNo=" + documentNo +
                '}';
    }
}
