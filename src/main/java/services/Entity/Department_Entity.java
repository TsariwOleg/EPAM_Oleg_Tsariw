package services.Entity;

public class Department_Entity {
    private int departmentId;
    private String department;


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
                "id=" + departmentId +
                ", department='" + department + '\'' +
                '}';
    }
}
