-- Database: tempnavup

-- DROP DATABASE tempnavup;

CREATE DATABASE tempnavup
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;

DROP TABLE EMB_building
DROP TABLE IT_building
DROP TABLE buildings

CREATE TABLE buildings (
  id SERIAL primary key  NOT NULL,
  name varchar(128) NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  description varchar(500) NOT NULL
);

CREATE TABLE EMB_building (
  id SERIAL primary key NOT NULL,
  roomName varchar(50) NOT NULL,
  level int NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  build_id SERIAL int references buildings(id)
);

CREATE TABLE IT_building (
  id SERIAL primary key NOT NULL,
  roomName varchar(50) NOT NULL,
  level int NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  build_id SERIAL int references buildings(id)
);

INSERT INTO buildings (id, name, latitude, longitude, description)
VALUES (1, 'IT Building', 28.2319014,-25.7556415, 'This is the IT buidling'),
	(2, 'EMB Building', 28.2310267,-25.7556051, 'This is the EMB buidling'),
	(3, 'Client Service Centre', 28.2312997,-25.7556689, 'This is the CSC buidling'),
	(4, 'Theology Building',28.2286827 ,-25.7546087, 'This is the Theology buidling'),
	(5, 'Centenary Building', 28.2306343,-25.7552513, 'This is the Centenary buidling'),
	(6, 'Humanities Building', 28.2310199,-25.7554519, 'This is the Humanities building'),
	(7,'University of Pretoria Library', 28.22898,-25.7541143, 'This is the University of Pretoria Library'),
	(8,'Engineering 1', 28.22898,-25.7541143, 'This is the Engineering 1 building'),
	(9,'Engineering 2', 28.2271783, -25.754131 ,'This is the Engineering 2 building'),
	(10,'Engineering 3', 28.2274766, -25.7545643, 'This is the Engineering 3 building'),
	(11,'Amphitheater', 28.2287364, -25.7539051,'This is the Amphitheater'),
	(12,'Music Library',28.2285545,  -25.7543309,'This is the Music Library'),
	(13,'Music Building',28.2285545, -25.7543309,'This is the Music Building'),
	(14,'Aula lawn', 28.228805, -25.7534427, 'This is the Music Library'),
	(15,'AE du Toit auditorium / Annexe',28.2286147,-25.7524122,'This is the AE du Toit auditorium / Annexe building'),
	(16,'Mathematics Building', 28.2292829,-25.751948,'This is the Mathematics and Applied Mathematics building'),
	(17,'Sci-Enza',28.2304551,-25.751849,'This is the Sci-Enza building'),
	(18,'Thuto Building', 28.2308628,-25.7528588,'This is the Thuto building'),
	(19,'Chemistry Building',28.2308628,-25.7529892,'This is the Chemistry building'),
	(20,'Chancelors Building',28.2301117, -25.7546368,'This is the Chancelors building'),
	(21,'Architecture building',28.2288752,-25.7558761,'This is the Architecture building'),
	(22,'Law Building',28.2334839,-25.7541874,'This is the Law building'),
	(23,'Drama Building',28.2337066, -25.7563423,'This is the Drama building'),
	(24,'JJ Theron',28.2337066,-25.7563423,'This is the JJ Theron building'),
	(25,'Law Building',28.2334839,-25.7541874,'This is the Law building'),
	(26, 'Natural and Agricultural Sciences Building',28.2344415,-25.7561925,'This is the Natural and Agricultural Sciences building');

INSERT INTO IT_building(id, roomName, level, latitude, longitude, build_id)
VALUES (1, 'IT 2-24', 2, 28.2319000, -25.7556400, 1),
	(2, 'IT 2-25', 2, 28.2319005, -25.7556405, 1),
	(3, 'IT 2-26', 2, 28.2319010, -25.75564010, 1),
	(4, 'IT 4-1', 4, 28.2319000, -25.75564000, 1),
	(5, 'IT 4-2', 4, 28.2319005, -25.75564005, 1),
	(6, 'IT 4-3', 4, 28.2319010, -25.75564010, 1),
	(7, 'IT 4-4', 4, 28.2319015, -25.75564015, 1),
	(8, 'IT 4-5', 4, 28.2319020, -25.75564020, 1);

INSERT INTO EMB_building(id, roomName, level, latitude, longitude, build_id)
VALUES (1, 'EB/EMB 2-150', 2, 28.2310200,-25.7556000, 2),
	(2, 'EB/EMB 2-151', 2, 28.2310205,-25.7556005, 2),
	(3, 'EB/EMB 4-150', 4, 28.2310200,-25.7556000, 2),
	(4, 'EB/EMB 4-151', 4, 28.2310205,-25.7556005, 2),
	(5, 'EB/EMB 4-152', 4, 28.2310210,-25.7556010, 2);
	
SELECT * FROM EMB_building
SELECT * FROM buildings
SELECT * FROM IT_building
