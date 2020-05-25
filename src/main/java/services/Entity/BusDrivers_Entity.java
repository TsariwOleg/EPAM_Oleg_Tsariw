package services.Entity;

import java.sql.Blob;

public class BusDrivers_Entity {
    private int id;
    private String startWorkHours;
    private String endWorkHours;
    private String workBus;
    private String driverLicense;
    private int idBus;


    public BusDrivers_Entity(String startWorkHours, String endWorkHours, String workBus) {
        this.startWorkHours = startWorkHours;
        this.endWorkHours = endWorkHours;
        this.workBus = workBus;
    }


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
    public String getStartWorkHours() {
        return startWorkHours;
    }

    public void setStartWorkHours(String startWorkHours) {
        this.startWorkHours = startWorkHours;
    }

    public String getEndWorkHours() {
        return endWorkHours;
    }

    public void setEndWorkHours(String endWorkHours) {
        this.endWorkHours = endWorkHours;
    }

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
        this.driverLicense = blobToString.getStringFromBlob(blob);;
    }


    @Override
    public String toString() {
        return "BusDrivers_Entity{" +
                "startWorkHours='" + startWorkHours + '\'' +
                ", endWorkHours='" + endWorkHours + '\'' +
                ", workBus='" + workBus + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", idBus=" + idBus +
                ", blobToString=" + blobToString +
                '}';
    }
}
