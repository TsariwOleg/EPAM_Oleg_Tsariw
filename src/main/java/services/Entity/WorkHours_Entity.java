package services.Entity;

public class WorkHours_Entity {
    private int id;
    private String startWorkHour;
    private String endWorkHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartWorkHour() {
        return startWorkHour;
    }

    public void setStartWorkHour(String startWorkHour) {
        this.startWorkHour = startWorkHour;
    }

    public String getEndWorkHour() {
        return endWorkHour;
    }

    public void setEndWorkHour(String endWorkHour) {
        this.endWorkHour = endWorkHour;
    }


    @Override
    public String toString() {
        return "WorkHours_Entity{" +
                "id=" + id +
                ", startWorkHour='" + startWorkHour + '\'' +
                ", endWorkHour='" + endWorkHour + '\'' +
                '}';
    }
}
