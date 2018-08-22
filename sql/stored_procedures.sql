create database if not exists demo;

use demo;

drop table if exists reindeer;

CREATE TABLE `reindeer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `owner` varchar(64) DEFAULT NULL,
  `salary` DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO reindeer VALUES (1,'Rudolph', 'Santa Claus', 800.00);
INSERT INTO reindeer VALUES (2,'Olive', 'Santa Claus', 500.00);
INSERT INTO reindeer VALUES (3,'Blitzen', 'Santa Claus', 500.00);
INSERT INTO reindeer VALUES (4,'Donner', 'Jack Frost', 300.00);
INSERT INTO reindeer VALUES (5,'Cupid', 'Jack Frost', 270.00);

DELIMITER $$

DROP PROCEDURE IF EXISTS `rob_the_rich_give_to_the_poor`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `rob_the_rich_give_to_the_poor`(IN poverty_treshold DECIMAL(10,2))
BEGIN

	UPDATE reindeer SET salary = salary + 100 where salary < poverty_treshold;
    UPDATE reindeer SET salary = salary - 100 where salary > poverty_treshold;

END$$
DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `raise_for_one_team`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `raise_for_one_team`(IN the_owner VARCHAR(64), IN raise INTEGER(5))
BEGIN

	UPDATE reindeer SET salary = salary + raise WHERE owner = the_owner ;
  
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `budget_for_one_team`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `budget_for_one_team`(IN the_owner VARCHAR(64), OUT the_sum INT)
BEGIN
	
	SELECT SUM(salary) INTO the_sum FROM reindeer where owner=the_owner;

END$$
DELIMITER ;






