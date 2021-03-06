package services.CRUD_DB.Person_CRUD;


import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read_Person {

    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();

    public Staff_Entity getStaff(int id) {
        String sql = "SELECT * FROM STAFF s  LEFT join DEPARTMENT d  on s.department_id=d.id where s.id=" + id;

        Staff_Entity staffEntity = new Staff_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                staffEntity.setName(resultSet.getString("NAME"));
                staffEntity.setSurname(resultSet.getString("SURNAME"));
                staffEntity.setPatronymic(resultSet.getString("PATRONYMIC"));
                staffEntity.setAge(resultSet.getInt("AGE"));
                staffEntity.setId(resultSet.getInt("ID"));

                if (resultSet.getBlob("photo") != null) {
                    staffEntity.setPhoto(resultSet.getBlob("photo"));
                }
            }

        } catch (SQLException e) {
            logger.error("SQLException in getStaff block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getStaff block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getStaff block(close statement):" + e);
            }
        }
        return staffEntity;
    }

    public Department_Entity getDepartment(int id) {
        String sql = "SELECT d.id ,d.DEPaRTMENT FROM DEPaRTMENT d inner join staff s on s.department_id=d.id WHERE s.ID=" + id;

        Department_Entity departmentEntity = new Department_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                departmentEntity.setDepartmentId(resultSet.getInt("ID"));
                departmentEntity.setDepartment(resultSet.getString("DEPARTMENT"));
            }

        } catch (SQLException e) {
            logger.error("SQLException in getDepartment block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getDepartment block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getDepartment block(close statement):" + e);
            }
        }


        return departmentEntity;
    }


    public Passport_Entity getPassport(int id) {
        String sql = "Select * from PASSPORT where id=" + id;
        Passport_Entity passport_entity = null;

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
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
            logger.error("SQLException in getPassport block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getPassport block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getPassport block(close statement):" + e);
            }
        }

        return passport_entity;
    }


    public TaxpayerCard_Entity getTaxpayerCard(int id) {
        TaxpayerCard_Entity taxpayerCard_entity = null;
        String sql = "select * from taxpayer_card where id=" + id;


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
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
            logger.error("SQLException in getTaxpayerCard block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getTaxpayerCard block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getTaxpayerCard block(close statement):" + e);
            }
        }

        return taxpayerCard_entity;
    }


    public MedicalBook_Entity getMedicalBook(int id) {
        String sql = "Select * from Medical_book where id=" + id;
        MedicalBook_Entity medicalBook_entity = null;

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                medicalBook_entity = new MedicalBook_Entity(
                        resultSet.getString("date_OF_MEDICAL_EXAMINATION"),
                        resultSet.getString("date_OF_NEXT_MEDICAL_EXAMINATION")
                );
                medicalBook_entity.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            logger.error("SQLException in getMedicalBook block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getMedicalBook block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getMedicalBook block(close statement):" + e);
            }
        }
        return medicalBook_entity;
    }

    public Position_Entity getPosition(String department, int id) {
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
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        position_entity.setPosition(resultSet.getString("position"));
                        position_entity.setSalary(resultSet.getInt("salary"));
                    }
                } catch (SQLException e) {
                    logger.error("SQLException in getPosition block:" + e);

                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (SQLException e) {
                        logger.error("SQLException in getPosition block(close statement):" + e);
                    }

                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                    } catch (SQLException e) {
                        logger.error("SQLException in getPosition block(close statement):" + e);
                    }
                }
            }
        }
        return position_entity;
    }


    public BusDrivers_Entity getBusDriver(int id) {
        String sql = "select * from BUS_DRIVERS BD LEFT JOIN WORK_HOURS WH  ON WH.WORK_HOUR_ID=BD.WORK_HOUR_ID " +
                "LEFT JOIN BUS_PARK BP ON BP.BUS_ID=BD.WORK_BUS_ID where BD.id=" + id;
        BusDrivers_Entity busDriversEntity = new BusDrivers_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {


                busDriversEntity.setStartWorkHour(resultSet.getString("START_WORK_HOURS"));
                busDriversEntity.setEndWorkHour(resultSet.getString("END_WORK_HOURS"));
                busDriversEntity.setWorkBus(resultSet.getString("BUS"));
                busDriversEntity.setId(resultSet.getInt("id"));
                busDriversEntity.setIdBus(resultSet.getInt("BUS_ID"));


                if (resultSet.getBlob("DRIVER_LICENSE") != null) {
                    busDriversEntity.setDriverLicense(resultSet.getBlob("DRIVER_LICENSE"));
                }
            }

        } catch (SQLException e) {
            logger.error("SQLException in getBusDriver block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getBusDriver block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getBusDriver block(close statement):" + e);
            }
        }

        return busDriversEntity;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

