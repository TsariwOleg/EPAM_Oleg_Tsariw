package services.Entity;

import services.BlobToString;

import java.sql.Blob;

public class BusDrivers_Entity  extends WorkHours_Entity{
    private int id;
    private String workBus;
    private String driverLicense;
    private int idBus;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    BlobToString blobToString = new BlobToString();

    public String getWorkBus() {
        return workBus;
    }

    public void setWorkBus(String workBus) {
        this.workBus = workBus;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(Blob blob) {
        this.driverLicense = blobToString.getStringFromBlob(blob);
    }


    @Override
    public String toString() {
        return "BusDrivers_Entity{" +
                "id=" + id +
                ", workBus='" + workBus + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", idBus=" + idBus +
                ", blobToString=" + blobToString +
                '}';
    }
}
