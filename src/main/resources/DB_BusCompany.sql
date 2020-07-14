CREATE TABLE position(
	position_id int PRIMARY KEY ,
position varchar(40),
salary int
);


create table route (
id int primary key,
route varchar(255),
avarage_profit int,
avarage_fuel_consuption int

);


CREATE table work_hours (
work_hour_id int PRIMARY KEY,
start_work_hours varchar(5),
end_work_hours varchar(5)

);

create table department(
	id int primary key auto_increment,
department varchar(30)
);

CREATE TABLE Staff (
id int auto_increment PRIMARY KEY,
name varchar(30) NOT NULL,
surname varchar(30) NOT NULL,
patronymic varchar(30) NOT NULL,
age int,
department_id int ,
photo blob,
foreign key (department_id) REFERENCES department(id)
);

CREATE TABLE Contact(
id int PRIMARY KEY ,
tel varchar(20)  ,
region varchar(30)  ,
city varchar(20)  ,
adress varchar(30),
FOREIGN KEY (id) REFERENCES STAFF(id)
);

CREATE TABLE taxpayer_card(
id int PRIMARY KEY,
taxpayer_number int(12) unique,
scaned_taxpayer_card blob,
FOREIGN KEY (id) REFERENCES STAFF(id)
);

CREATE TABLE medical_book(
id int PRIMARY KEY,
date_of_medical_examination DATE,
date_of_next_medical_examination DATE,
FOREIGN key(id) REFERENCES Staff(id)
);



CREATE TABLE Passport(
id int PRIMARY KEY,
date_of_birth varchar(10) ,
country_of_birth varchar(20),
region_of_birth varchar(20),
city_of_birth varchar(20),
document_no int(8),
scanned_passport blob,
FOREIGN KEY (id) REFERENCES staff(id)
);




create table doctors (
id int primary key auto_increment,
position_id int ,
foreign key (position_id) REFERENCES position(position_id)
);


CREATE TABLE bus_park (
bus_id int primary key auto_increment,	
bus varchar(15) unique ,
year_of_issue varchar(4) ,
model varchar(30),
Fuel_consumption varchar(10),
route_id int,
foreign key (route_id) references route(id)
);

CREATE TABLE Bus_drivers(
id int PRIMARY KEY ,
position_id int ,
WORK_BUS_ID int,
work_hour_id int,
driver_license blob,
foreign key (position_id) REFERENCES position(position_id),
foreign key (work_hour_id) REFERENCES work_hour(work_hour_id),
foreign key (own_bus_id) REFERENCES bus_park(bus_id)


);

CREATE TABLE administration (
id int PRIMARY KEY ,
position_id int ,
access_to_web int ,
foreign key (position_id) REFERENCES position(position_id)
);

create table access_to_web(
id int primary key,
login varchar(30),
password varchar(30),
foreign key (id) REFERENCES administration(id)
);






create table  technical_condition(
id int primary key auto_increment,
condition varchar(255),
note varchar(255),
foreign key(id) references bus_park(bus_id)
) ;


create table car_mechanics (
id int primary key auto_increment,
position_id int ,
foreign key(position_id) references position(position_id)

);

create table repaired_bus(
repaireNo int primary key auto_increment,
malfunction varchar(255),
cost_of_repaire int ,
note varchar(255),
bus_id int ,  
mechanic_id int ,
foreign key(bus_id) references bus_park(bus_id),
foreign key(mechanic_id) references car_mechanics(id)


);

insert into DEPARTMENT values(
1,'Адміністрація');


insert into DEPARTMENT values(
2,'Водії');


insert into DEPARTMENT values(
3,'Автомеханіки');


insert into DEPARTMENT values(
4,'Медперсонал');

insert into access_to_web values (1,'admin','admin');









