package services.Entity;

public class Buses_Entity {
    private int id;
    private String busNo;
    private int yearOfIssue;
    private String model;
    private String fuelConsumption;
    private int routeId;
    private String route;


    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Buses_Entity{" +
                "id=" + id +
                ", busNo='" + busNo + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", model='" + model + '\'' +
                ", fuelConsumption='" + fuelConsumption + '\'' +
                ", routeId=" + routeId +
                '}';
    }

}
