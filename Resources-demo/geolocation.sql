CREATE TABLE `eyetalix_testing`.`geolocation` ( 
`id` INT NOT NULL AUTO_INCREMENT , 
`building` VARCHAR(100) NOT NULL , 
`latitude` VARCHAR(100) NOT NULL , 
`longitude` VARCHAR(100) NOT NULL , 
PRIMARY KEY (`id`) ) 
ENGINE = MyISAM CHARACTER SET utf8 COLLATE utf8_general_ci;


INSERT INTO `eyetalix_testing`.`geolocations` (`id`, `building`, `latitude`, `longitude`) 
VALUES (NULL, 'Humanities Building', '-25.7554519', '28.2310199'), 
(NULL, 'Centenary Building ', '-25.7552513', '28.2306343'),
(NULL, 'Information Technology Building', '-25.7556415', '28.2319014'),
(NULL, 'Client Service Centre', '-25.7556689', '28.2312997'),
(NULL, 'Theology Building', '-25.7550951', '28.229788'),
(NULL, 'Economic and Management Sciences Building', '-25.7556051', '28.2310267');