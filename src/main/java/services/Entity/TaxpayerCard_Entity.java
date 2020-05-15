package services.Entity;

import java.sql.Blob;

public class TaxpayerCard_Entity {
    private int id;
    private int taxpayerNumber;
    private String scannedTaxpayerCard;

    public TaxpayerCard_Entity(int id, int taxpayerNumber) {
        this.id = id;
        this.taxpayerNumber = taxpayerNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(int taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public String getScannedTaxpayerCard() {
        return scannedTaxpayerCard;
    }

    BlobToString blobToString = new BlobToString();
    public void setScannedTaxpayerCard(Blob blob) {
        this.scannedTaxpayerCard = blobToString.getStringFromBlob(blob);
    }



    @Override
    public String toString() {
        return "TaxpayerCard_Entity{" +
                "id=" + id +
                ", taxpayerNumber=" + taxpayerNumber +
                '}';
    }
}
