SET GLOBAL general_log_file='blogger-create.log';
SET GLOBAL general_log=1;

SET autocommit=0;

DROP USER IF EXISTS 'blogadmin'@'localhost';
CREATE USER 'blogadmin'@'localhost' IDENTIFIED BY 'c#@dT6ai^1^g';

DROP DATABASE IF EXISTS cmadblog;
CREATE DATABASE cmadblog;

GRANT ALL PRIVILEGES ON *.* TO 'blogadmin'@'localhost';

USE cmadblog;

COMMIT;

SELECT '*** CMAD Blog database created ***';