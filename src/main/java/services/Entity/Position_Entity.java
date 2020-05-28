package services.Entity;

public class Position_Entity {
    private String position;
    private int salary;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Department_Entity{" +
                "position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
