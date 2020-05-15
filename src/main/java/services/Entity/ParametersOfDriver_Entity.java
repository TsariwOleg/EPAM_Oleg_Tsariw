package services.Entity;

public class ParametersOfDriver_Entity {
    private String startWorkHours;
    private String endWorkHours;
    private String category;
    private String busNumber;
    private int salary;


    public ParametersOfDriver_Entity(String startWorkHours, String endWorkHours, String category, String busNumber, int salary) {
        this.startWorkHours = startWorkHours;
        this.endWorkHours = endWorkHours;
        this.category = category;
        this.busNumber = busNumber;
        this.salary = salary;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ParametersOfDriver_Entity{" +
                "startWorkHours='" + startWorkHours + '\'' +
                ", endWorkHours='" + endWorkHours + '\'' +
                ", category='" + category + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}
/*

    SELEC cbd.category, cbd.SALARY FROM STAFF s
        INNER JOIN BUS_DRIVERS b ON s.DEPARTMENT_ID = b.ID
        INNER JOIN WORK_HOURs wh ON wh.id_work_hour = b.WORK_HOUR_ID
        INNER JOIN BUS_PARK bp ON bp.BUS_ID = b.WORK_BUS_ID
        INNER JOIN CATEGORY_BUS_DRIVER cbd ON cbd.CATEGORY_ID = b.CATEGORY_ID
        WHERE s.id=4;*/
