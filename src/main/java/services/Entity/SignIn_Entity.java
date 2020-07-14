package services.Entity;

public class SignIn_Entity extends Department_Entity{
    private int id;
    private String login;
    private String password;
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

    @Override
    public String toString() {
        return "SignIn_Entity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", NSP='" + NSP + '\'' +
                '}';
    }


}
