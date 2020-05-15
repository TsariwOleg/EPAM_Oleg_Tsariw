package services.Entity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class Staff_Entity {
    private int id ;
    private String name;
    private String surname;
    private String patronymic;
    private int idDepartment;
    private int age;
    private String department;
    private String photo;

    BlobToString blobToString = new BlobToString();

    public Staff_Entity(int id, String name, String surname, String patronymic, int idDepartment, int age, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.idDepartment = idDepartment;
        this.age = age;
        this.department = department;
    }

    public String getPhoto() {
        return photo;
    }


    public void setPhoto(Blob blob) {
       photo= blobToString.getStringFromBlob(blob);

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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", idDepartment=" + idDepartment +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }
}
