# EPAM_Oleg_Tsariw
## About
This is java web application for bus companies. In this application is possible to :
- read/create/update/delete information about staff and buses of company
- create/update/delete information about check up staff before their job(only doctor can does it)
- read/create/update/delete information about route on which buses work
- read/create/update/delete information about repairs buses
- read/create/delete user of this web aplication

## Use
Everyone is able to read all information on this web application(except UserOfSite page). But for creating/updating/deleting information you must be registred.
At once only one person will be registred : admin. Admin can register a new user from administration or doctors(departments of bus company).
- administration(department of bus company) and admin can change all information (except check ups)
- doctors can change information about check ups of staff

## Code
- for logging every step of users in project , we use log4j. Data of logging save in .log file. These file create every day.
- for testing functionality code , we use jUnit and Mockito
- as web containe ,  we use Apache Tomcat7
- as data base ,we use embedded db H2