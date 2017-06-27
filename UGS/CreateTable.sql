CREATE DEFINER=`root`@`localhost` PROCEDURE `createTable`(IN tableName varchar(50))
BEGIN
	SET @tableName=tableName;
    SET @createquery=CONCAT(' 
    CREATE TABLE IF NOT EXISTS ' , @tableName, ' (
            id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
            StudentID INT NOT NULL,
            StudentName VARCHAR(50) NOT NULL,
            QMark DOUBLE NOT NULL,
            Assignment1 DOUBLE NOT NULL,
            Assignment2 DOUBLE NOT NULL,
            Assignment3 DOUBLE NOT NULL,
            Exam DOUBLE NOT NULL,
            Results DOUBLE NOT NULL,
            Grade VARCHAR(50) NOT NULL,
            PRIMARY KEY (id)
        )
        
    ');
    
	PREPARE stmt FROM @createquery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt; 
    SELECT TRUE;
END