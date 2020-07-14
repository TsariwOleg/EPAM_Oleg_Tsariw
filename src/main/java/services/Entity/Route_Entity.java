package services.Entity;

public class Route_Entity {
    private int id;
    private String route;
    private int avarageProfit;
    private int avarageFuelConsuption;
    private String fullRoute;
    private int countOfBuses;

    public int getCountOfBuses() {
        return countOfBuses;
    }

    public void setCountOfBuses(int countOfBuses) {
        this.countOfBuses = countOfBuses;
    }

    public String getFullRoute() {
        return fullRoute;
    }

    public void setFullRoute(String fullRoute) {
        this.fullRoute = fullRoute;
    }

    public int getAvarageProfit() {
        return avarageProfit;
    }

    public void setAvarageProfit(int avarageProfit) {
        this.avarageProfit = avarageProfit;
    }

    public int getAvarageFuelConsuption() {
        return avarageFuelConsuption;
    }

    public void setAvarageFuelConsuption(int avarageFuelConsuption) {
        this.avarageFuelConsuption = avarageFuelConsuption;
    }

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

    @Override
    public String toString() {
        return "Route_Entity{" +
                "id=" + id +
                ", route='" + route + '\'' +
                ", avarageProfit=" + avarageProfit +
                ", avarageFuelConsuption=" + avarageFuelConsuption +
                ", fullRoute='" + fullRoute + '\'' +
                ", countOfBuses=" + countOfBuses +
                '}';
    }
}
