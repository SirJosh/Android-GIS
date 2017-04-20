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
  build_id int references buildings(id)
);

CREATE TABLE IT_building (
  id SERIAL primary key NOT NULL,
  roomName varchar(50) NOT NULL,
  level int NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  build_id int references buildings(id)
);

INSERT INTO buildings (name, latitude, longitude, description)
VALUES ('IT Building', 28.2319014,-25.7556415, 'This is the IT building'),
	('EMB Building', 28.2310267,-25.7556051, 'This is the EMB building'),
	('Client Service Centre', 28.2312997,-25.7556689, 'This is the CSC building'),
	('Theology Building',28.2286827 ,-25.7546087, 'This is the Theology building'),
	('Centenary Building', 28.2306343,-25.7552513, 'This is the Centenary building'),
	('Humanities Building', 28.2310199,-25.7554519, 'This is the Humanities building'),
	('University of Pretoria Library', 28.22898,-25.7541143, 'This is the University of Pretoria Library'),
	('Engineering 1', 28.22898,-25.7541143, 'This is the Engineering 1 building'),
	('Engineering 2', 28.2271783, -25.754131 ,'This is the Engineering 2 building'),
	('Engineering 3', 28.2274766, -25.7545643, 'This is the Engineering 3 building'),
	('Amphitheater', 28.2287364, -25.7539051,'This is the Amphitheater'),
	('Music Library',28.2285545,  -25.7543309,'This is the Music library'),
	('Music Building',28.2285545, -25.7543309,'This is the Music building'),
	('Aula lawn', 28.228805, -25.7534427, 'This is the Music Library'),
	('AE du Toit auditorium / Annexe',28.2286147,-25.7524122,'This is the AE du Toit auditorium / Annexe building'),
	('Mathematics Building', 28.2292829,-25.751948,'This is the Mathematics and Applied Mathematics building'),
	('Sci-Enza',28.2304551,-25.751849,'This is the Sci-Enza building'),
	('Thuto Building', 28.2308628,-25.7528588,'This is the Thuto building'),
	('Chemistry Building',28.2308628,-25.7529892,'This is the Chemistry building'),
	('Chancelors Building',28.2301117, -25.7546368,'This is the Chancelors building'),
	('Architecture building',28.2288752,-25.7558761,'This is the Architecture building'),
	('Law Building',28.2334839,-25.7541874,'This is the Law building'),
	('Drama Building',28.2337066, -25.7563423,'This is the Drama building'),
	('JJ Theron',28.2337066,-25.7563423,'This is the JJ Theron building'),
	('Law Building',28.2334839,-25.7541874,'This is the Law building'),
	('Natural and Agricultural Sciences Building',28.2344415,-25.7561925,'This is the Natural and Agricultural Sciences building');

INSERT INTO IT_building(roomName, level, longitude, latitude, build_id)
VALUES  ('IT 2-23', 2, -25.7559862, 28.2335508, 1),
	('IT 2-24', 2, -25.7559777,28.2335529, 1),
	('IT 2-25', 2, -25.7559635,28.2335687, 1),
	('IT 2-26', 2, -25.7559654,28.2335855, 1),
	('IT 2-27', 2, -25.7559654,28.2335855, 1),
	('IT 4-1', 4, -25.7559862, 28.2335508, 1),
	('IT 4-2', 4, -25.7559777,28.2335529, 1),
	('IT 4-3', 4, -25.7559635,28.2335687, 1),
	('IT 4-4', 4, -25.7559654,28.2335855, 1),
	('IT 4-5', 4, -25.7559654,28.2335855, 1);

INSERT INTO EMB_building(roomName, level, longitude, latitude, build_id)
VALUES ('EB/EMB 2-150', 2, -25.7554093,28.2334133, 2),
	('EB/EMB 2-151', 2, -25.7554103,28.2334122, 2),
	('EB/EMB 4-150', 4, -25.755355,28.2328985, 2),
	('EB/EMB 4-151', 4,-25.7552985,28.2333593, 2),
	('EB/EMB 4-152', 4, -25.755392, 28.233645, 2);
	
SELECT * FROM EMB_building
SELECT * FROM buildings
SELECT * FROM IT_building
