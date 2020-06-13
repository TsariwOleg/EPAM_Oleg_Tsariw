package services.Entity;

public class SignIn_Entity {
    //todo delete unneccesary variable
    private int id;
    private String login;
    private String password;
    private String department;
    private int departmentId;
    private String NSP;


    public String getNSP() {
        return NSP;
    }

    public void setNSP(String NSP) {
        this.NSP = NSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "SignIn_Entity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", departmentId=" + departmentId +
                ", NSP='" + NSP + '\'' +
                '}';
    }


}
