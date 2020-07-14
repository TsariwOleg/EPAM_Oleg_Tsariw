package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BlobToString;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.PersonCRUD;
import services.Entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/person")
@MultipartConfig(maxFileSize = 16177215)
// indicates this servlet will handle multipart request. We restrict maximum size of the upload file up to 16 MB.
public class Person extends HttpServlet {
    PersonCRUD personCRUD = new PersonCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    private Map mapPerson;
    private Map mapConstantTable;
    int idSession = -1;
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId") != null) {
            req.setAttribute("access", req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");
        } else {
            idSession = -1;
        }

        mapPerson = personCRUD.readPerson(Integer.parseInt(req.getParameter("id")));


        req.setAttribute("staff", mapPerson.get("staff"));
        req.setAttribute("department", mapPerson.get("department"));
        req.setAttribute("passport", mapPerson.get("passport"));
        req.setAttribute("positionOfPerson", mapPerson.get("position"));
        req.setAttribute("taxpayerCard", mapPerson.get("taxpayerCard"));
        req.setAttribute("medicalBook", mapPerson.get("medicalBook"));

        if (((Department_Entity)mapPerson.get("department")).getDepartmentId()==2) {
            req.setAttribute("busDriver", mapPerson.get("busDriver"));
        }
        if (req.getParameter("regime") != null) {
            mapConstantTable = constantTablesCRUD.readConstantTable(req.getParameter("regime"));
            if (req.getParameter("regime").equals("UpdateInfoAboutPers")) {
                req.setAttribute("departments", mapConstantTable.get("departments"));
                req.setAttribute("positions", mapConstantTable.get("positions"));

            }

            if (req.getParameter("regime").equals("UpdateInfoOther")) {
                req.setAttribute("workHours", mapConstantTable.get("workHours"));
                req.setAttribute("workBuses", mapConstantTable.get("workBuses"));
            }

            req.setAttribute("regime", req.getParameter("regime"));
            logger.info("User want to " + req.getParameter("regime") + "(person_id=" + req.getParameter("id")
                    + ").User_id=" + idSession);
        } else {
            if (idSession != -1) {
                logger.info("User open person page(person_id=" + req.getParameter("id") + ").User_id=" + idSession);
            }
        }


        req.getRequestDispatcher("/Person.jsp").forward(req, resp);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        mapPerson = personCRUD.readPerson(Integer.parseInt(req.getParameter("id")));
        int id = Integer.parseInt(req.getParameter("id"));
        Part part;
        BlobToString blobToString = new BlobToString();


        if (req.getParameter("confirmPerson") != null) {
            Map newmap = new HashMap();
            Staff_Entity staffEntity = new Staff_Entity();
            staffEntity.setName(blobToString.getUTFString(req.getParameter("newName")));
            staffEntity.setSurname(blobToString.getUTFString(req.getParameter("newSurname")));
            staffEntity.setPatronymic(blobToString.getUTFString(req.getParameter("newPatronymic")));
            staffEntity.setAge(Integer.parseInt(req.getParameter("newAge")));
            staffEntity.setDepartment(blobToString.getUTFString(req.getParameter("newDepartment")));
            Position_Entity positionEntity = new Position_Entity();
            positionEntity.setPosition(blobToString.getUTFString(req.getParameter("newPosition")));

            newmap.put("staff", staffEntity);
            newmap.put("position", positionEntity);

            PersonCRUD personCRUD = new PersonCRUD();
            logger.info("User updated information about person(person_id=" + req.getParameter("id") + "):" +
                    "\n New name:" + staffEntity.getName() +
                    "\n New surname:" + staffEntity.getSurname() +
                    "\n New patronymic:" + staffEntity.getPatronymic() +
                    "\n New age:" + staffEntity.getAge() +
                    "\n New department:" + staffEntity.getDepartment() +
                    "\n New position:" + positionEntity.getPosition() + ".User_id=" + idSession);

            personCRUD.updatePerson(id, newmap, "staff", mapConstantTable);
        }

        if (req.getParameter("confirmPassport") != null) {
            Passport_Entity passportEntity = new Passport_Entity(req.getParameter("newDateOfBirth"),
                    req.getParameter("newCountryOfBirth"),
                    req.getParameter("newRegionOfBirth"),
                    req.getParameter("newCityOfBirth"),
                    Integer.parseInt(req.getParameter("newDocumentNo"))
            );

            logger.info("User updated information about passport(person_id=" + req.getParameter("id") + "):" +
                    "\n New date of birth:" + passportEntity.getDateOfBirth() +
                    "\n New country of birth:" + passportEntity.getCountryOfBirth() +
                    "\n New region of birth:" + passportEntity.getRegionOfBirth() +
                    "\n New city of birth:" + passportEntity.getCityOfBirth() +
                    "\n New documentNo:" + passportEntity.getDocumentNo() + ".User_id=" + idSession);


            personCRUD.updatePerson(id, passportEntity, "passport");

        }


        if (req.getParameter("confirmTaxpayerCard") != null) {
            TaxpayerCard_Entity taxpayerCardEntity =
                    new TaxpayerCard_Entity(Integer.parseInt(req.getParameter("newTaxpayerNumber")));

            logger.info("User updated information about taxpayer card(person_id=" + req.getParameter("id") + "):" +
                    "\n New taxpayer number:" + taxpayerCardEntity.getTaxpayerNumber() + ".User_id=" + idSession);

            personCRUD.updatePerson(id, taxpayerCardEntity, "taxpayerCard");
        }


        if (req.getParameter("confirmMedicalBook") != null) {
            MedicalBook_Entity medicalBookEntity = new MedicalBook_Entity(
                    req.getParameter("newDateOfMedicalExam"),
                    req.getParameter("newDateOfNextMedicalExam")
            );

            logger.info("User updated information about medical book(person_id=" + req.getParameter("id") + "):" +
                    "\n New date of medical exam:" + medicalBookEntity.getDateOfMedicalExam() +
                    "\n New country of next medical exam:" + medicalBookEntity.getDateOfNextMedicalExam()
                    + ".User_id=" + idSession);

            personCRUD.updatePerson(id, medicalBookEntity, "medicalBook");
        }

        if (req.getParameter("confirmBusDriver") != null) {
            String[] hours = req.getParameter("newStartWorkHour").split("-");
            BusDrivers_Entity busDriversEntity = new BusDrivers_Entity();
            busDriversEntity.setStartWorkHour(hours[0]);
            busDriversEntity.setEndWorkHour(hours[1]);
            busDriversEntity.setWorkBus(req.getParameter("newBus"));

            logger.info("User updated information about bus driver(person_id=" + req.getParameter("id") + "):" +
                    "\n New work hours:" + req.getParameter("newStartWorkHour") +
                    "\n New bus_id:" + busDriversEntity.getIdBus()
                    + ".User_id=" + idSession);

            personCRUD.updatePerson(id, busDriversEntity, "busDriver", mapConstantTable);

        }

        if (req.getParameter("deletePerson") != null) {
            logger.info("User cleared information about person(person_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            personCRUD.deletePerson(id, "staff");
        }

        if (req.getParameter("deletePassport") != null) {
            logger.info("User cleared information about passport of person(person_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            personCRUD.deletePerson(id, "passport");
        }

        if (req.getParameter("deleteTaxpayerCard") != null) {
            logger.info("User cleared information about taxpayer card of person(person_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            personCRUD.deletePerson(id, "taxpayerCard");
        }

        if (req.getParameter("deleteMedicalBook") != null) {
            logger.info("User cleared information about medical book of person(person_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            personCRUD.deletePerson(id, "medicalBook");
        }

        if (req.getParameter("deleteBusDriver") != null) {
            logger.info("User cleared information about bus driver(person_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            personCRUD.deletePerson(id, "busDriver");
        }


        try {
            if (req.getParameter("staff-button") != null) {
                part = req.getPart("staff-img");
                if (part.getSize() != 0) {
                    logger.info("User uploaded new photo of person(person_id="
                            + req.getParameter("id") + ").User_id=" + idSession);
                    personCRUD.updatePersonImg(id, part, "staff");
                } else {
                    logger.info("User wanted to uploaded empty file(person_id=" + req.getParameter("id") + ")+" +
                            ".User_id=" + idSession);
                }
            }

            if (req.getParameter("passport-button") != null) {
                part = req.getPart("passport-img");
                if (part.getSize() != 0) {
                    logger.info("User uploaded new scanned passport of person(person_id="
                            + req.getParameter("id") + ").User_id=" + idSession);
                    personCRUD.updatePersonImg(id, part, "passport");
                } else {
                    logger.info("User wanted to uploaded empty file(person_id=" + req.getParameter("id") + ")+" +
                            ".User_id=" + idSession);
                }
            }


            if (req.getParameter("taxpayerCard-button") != null) {
                part = req.getPart("taxpayerCard-img");
                if (part.getSize() != 0) {
                    logger.info("User uploaded new scanned taxpayer card of person(person_id="
                            + req.getParameter("id") + ").User_id=" + idSession);
                    personCRUD.updatePersonImg(id, part, "taxpayerCard");
                } else {
                    logger.info("User wanted to uploaded empty file(person_id=" + req.getParameter("id") + ")+" +
                            ".User_id=" + idSession);
                }
            }


            if (req.getParameter("driverLicense-button") != null) {
                part = req.getPart("driverLicense-img");
                if (part.getSize() != 0) {
                    logger.info("User uploaded new scanned driver license of person(person_id="
                            + req.getParameter("id") + ").User_id=" + idSession);
                    personCRUD.updatePersonImg(id, part, "driverLicense");
                } else {
                    logger.info("User wanted to uploaded empty file(person_id=" + req.getParameter("id") + ")+" +
                            ".User_id=" + idSession);
                }
            }

        } catch (IOException e) {
            logger.error("An I/O error occurred during the retrieval of the requested Part"
                    + "\n Exception:" + e
                    + "\n person_id=" + req.getParameter("id")
                    + "\n User_id=" + idSession);
        } catch (ServletException e) {
            logger.error("Request is not of type multipart/form-data"
                    + "\n Exception:" + e
                    + "\n person_id=" + req.getParameter("id")
                    + "\n User_id=" + idSession);
        } catch (IllegalStateException e) {
            logger.error("Part in the request is larger than maxFileSize"
                    + "\n Exception:" + e
                    + "\n person_id=" + req.getParameter("id")
                    + "\n User_id=" + idSession);
        }


        doGet(req, resp);
    }
}
