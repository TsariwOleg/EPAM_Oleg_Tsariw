package services.Entity;




public class StaffEntity{
    private int id ;
    private String name;
    private String surname;
    private String ptronymic;
    private int idDepartment;
    private int age;


    public StaffEntity(int id, String name, String surname, String ptronymic, int age ,int idDepartment ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ptronymic = ptronymic;
        this.idDepartment = idDepartment;
        this.age = age;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ptronymic='" + ptronymic + '\'' +
                ", idDepartment=" + idDepartment +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPtronymic() {
        return ptronymic;
    }

    public void setPtronymic(String ptronymic) {
        this.ptronymic = ptronymic;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
