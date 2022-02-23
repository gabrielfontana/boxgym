CREATE DATABASE IF NOT EXISTS `boxgym`;

USE `boxgym`;

CREATE TABLE `supplier` (
  `supplierId` INT(11) NOT NULL AUTO_INCREMENT,
  `companyRegistry` CHAR(14) NOT NULL,
  `corporateName` VARCHAR(255) NOT NULL,
  `tradeName` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL, 
  `phone` VARCHAR(11) NULL, 
  `zipCode` CHAR(8) NULL,
  `address` VARCHAR(255) NULL, 
  `addressComplement` VARCHAR(255) NULL, 
  `district` VARCHAR(255) NULL, 
  `city` VARCHAR(255) NULL, 
  `federativeUnit` CHAR(2) NULL,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `updatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`supplierId`),
  UNIQUE KEY `supplierUnique` (`companyRegistry`) USING BTREE
)