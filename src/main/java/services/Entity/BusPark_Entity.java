package services.Entity;

public class BusPark_Entity {
    private int id;
    private String bus;
    private String model;
    private String route;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    @Override
    public String toString() {
        return "BusPark_Entity{" +
                "id=" + id +
                ", bus='" + bus + '\'' +
                ", model='" + model + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
