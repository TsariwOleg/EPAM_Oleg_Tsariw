package services.Entity;

public class Department_Entity {
    private int id;
    private String department;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Department_Entity{" +
                "id=" + id +
                ", department='" + department + '\'' +
                '}';
    }
}
