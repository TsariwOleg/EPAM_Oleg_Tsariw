package services.CRUD_DB.Person_CRUD;

import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Read_Person {
    ConnectionBD connectionBD = new ConnectionBD();
    private String className = this.getClass().getSimpleName();
    private ResultSet resultSet;
    //TODO close result set

    public Staff_Entity getStaff(Statement statement, int id) {
        String sql = "SELECT * FROM STAFF where id=" + id;

        Staff_Entity staff_entity = null;
        try {
            resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                staff_entity = new Staff_Entity(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("patronymic"),
                        resultSet.getInt("age")
//                        resultSet.getString("department")
                );
                staff_entity.setId(resultSet.getInt("id"));

                if (resultSet.getBlob("photo") != null) {
                staff_entity.setPhoto(resultSet.getBlob("photo"));}
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                connectionBD.closeResultSet(resultSet, className);
            }
        }
        return staff_entity;
    }

    public Department_Entity getDepartment(Statement statement, int id) {
        String sql = "SELECT d.id ,d.DEPaRTMENT FROM DEPaRTMENT d inner join staff s on s.department_id=d.id WHERE s.ID=" + id;

        Department_Entity departmentEntity = new Department_Entity();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                departmentEntity.setId(resultSet.getInt("ID"));
                departmentEntity.setDepartment(resultSet.getString("DEPARTMENT"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return departmentEntity;
    }


    public Passport_Entity getPassport(Statement statement, int id) {
        String sql = "Select * from PASSPORT where id=" + id;
        Passport_Entity passport_entity = null;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                passport_entity = new Passport_Entity(
                        resultSet.getString("date_Of_Birth"),
                        resultSet.getString("country_Of_Birth"),
                        resultSet.getString("region_Of_Birth"),
                        resultSet.getString("city_Of_Birth"),
                        resultSet.getInt("document_No")
                );
                passport_entity.setId(resultSet.getInt("id"));
                if (resultSet.getBlob("Scanned_passport") != null) {
                    passport_entity.setScannedPassport(resultSet.getBlob("Scanned_passport"));
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                connectionBD.closeResultSet(resultSet, className);
            }
        }

        return passport_entity;
    }


    public TaxpayerCard_Entity getTaxpayerCard(Statement statement, int id) {
        TaxpayerCard_Entity taxpayerCard_entity = null;
        String sql = "select * from taxpayer_card where id=" + id;


        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                taxpayerCard_entity = new TaxpayerCard_Entity(
                        resultSet.getInt("Taxpayer_number")
                );

                taxpayerCard_entity.setId(resultSet.getInt("id"));

                if (resultSet.getBlob("scanned_taxpayer_card") != null) {
                    taxpayerCard_entity.setScannedTaxpayerCard(resultSet.getBlob("scanned_taxpayer_card"));
                }

            }

        } catch (SQLException e) {
            System.out.println("TaxpayerCard:");
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                connectionBD.closeResultSet(resultSet, className);
            }
        }

        return taxpayerCard_entity;
    }


    public MedicalBook_Entity getMedicalBook(Statement statement, int id) {
        String sql = "Select * from Medical_book where id=" + id;
        MedicalBook_Entity medicalBook_entity = null;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                medicalBook_entity = new MedicalBook_Entity(
                        resultSet.getString("date_OF_MEDICAL_EXAMINATION"),
                        resultSet.getString("date_OF_NEXT_MEDICAL_EXAMINATION")
                );
                medicalBook_entity.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("Medical book:");
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                connectionBD.closeResultSet(resultSet, className);
            }
        }
        return medicalBook_entity;
    }

    //todo rename title of method
    public ParametersOfDriver_Entity getParametersOfDrivers(Statement statement, int id) {
        String sql = "SELECT wh.start_work_hours,end_work_hours, pos.position ,bp.BUS , pos.SALARY FROM STAFF s " +
                "INNER JOIN BUS_DRIVERS b ON s.DEPARTMENT_ID = b.ID " +
                "INNER JOIN WORK_HOURs wh ON wh.work_hour_id = b.WORK_HOUR_ID " +
                "INNER JOIN BUS_PARK bp ON bp.BUS_ID = b.WORK_BUS_ID " +
                "INNER JOIN position pos ON pos.position_id = b.position_id " +
                "WHERE s.id=" + id;
        ParametersOfDriver_Entity parametersOfDriver_entity = null;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                parametersOfDriver_entity = new ParametersOfDriver_Entity(resultSet.getString("start_work_hours"),
                        resultSet.getString("end_work_hours"),
                        resultSet.getString("position"),
                        resultSet.getString("BUS"),
                        resultSet.getInt("salary")
                );

            }

        } catch (SQLException e) {
            System.out.println("Medical book:");
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                connectionBD.closeResultSet(resultSet, className);
            }
        }
        return parametersOfDriver_entity;
    }


    public Position_Entity getPosition(Statement statement, String department, int id) {
        Position_Entity position_entity = new Position_Entity();
        String deprt = "";
        if (department != null) {
            switch (department) {
                case "Адміністрація":
                    deprt = "ADMINISTRATION";
                    break;
                case "Водії":
                    deprt = "BUS_DRIVERS";
                    break;

                case "Автомеханіки":
                    deprt = "CAR_MECHANICS";
                    break;

                case "Медперсонал":
                    deprt = "DOCTORS";
                    break;
            }


            if (deprt != null) {
                String sql = "  SELECT p.POSITION, p.SALARY  FROM " + deprt + " a " +
                        "INNER JOIN POSITION p ON a.POSITION_ID =p.POSITION_ID WHERE a.ID =" + id;
                try {
                    resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        position_entity.setPosition(resultSet.getString("position"));
                        position_entity.setSalary(resultSet.getInt("salary"));
                    }
                } catch (SQLException e) {
                    System.out.println("getDepartment");
                    System.out.println(e);
                } finally {
                    if (resultSet != null) {
                        connectionBD.closeResultSet(resultSet, className);
                    }
                }
            }
        }
        return position_entity;
    }


    public BusDrivers_Entity getBusDriver(Statement statement,int id){
        String sql = "select * from BUS_DRIVERS BD LEFT JOIN WORK_HOURS WH  ON WH.WORK_HOUR_ID=BD.WORK_HOUR_ID " +
                "LEFT JOIN BUS_PARK BP ON BP.BUS_ID=BD.WORK_BUS_ID where BD.id="+id;
        BusDrivers_Entity busDriversEntity = null;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                busDriversEntity = new BusDrivers_Entity(resultSet.getString("START_WORK_HOURS"),
                        resultSet.getString("END_WORK_HOURS"),
                        resultSet.getString("BUS")
                );
                busDriversEntity.setId(resultSet.getInt("id"));
                busDriversEntity.setIdBus(resultSet.getInt("BUS_ID"));


                if (resultSet.getBlob("DRIVER_LICENSE")!=null){
                busDriversEntity.setDriverLicense(resultSet.getBlob("DRIVER_LICENSE"));
            }
        }

        }catch (SQLException e){
            System.out.println(e);
        }

        return  busDriversEntity;
    }


}
