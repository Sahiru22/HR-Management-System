-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: employeedb
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allowances`
--

DROP TABLE IF EXISTS `allowances`;
CREATE TABLE `allowances` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `allowance_type` varchar(255) DEFAULT NULL,
  `allowance_date` DATE NOT NULL,
  `allowance_fee` decimal(10,2) NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `FK8hi3vprne9mtl4fmwqp1u2hmw` (`employee_id`),
   CONSTRAINT `FK8hi3vprne9mtl4fmwqp1u2hmw` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `bonuses`;
CREATE TABLE `bonuses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bonus_type` varchar(255) DEFAULT NULL,
  `bonus_amount` decimal(10,2) NOT NULL,
  `bonus_date` DATE NOT NULL,
  `employee_id` bigint NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ticdghq54eroux4oxss9hhdd` (`employee_id`),
  CONSTRAINT `FK8ticdghq54eroux4oxss9hhdd` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `dependent_details`;
CREATE TABLE `dependent_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `relationship` varchar(60) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjgp4jc4hk7rf1xbllkxtka7bo` (`employee_id`),
  CONSTRAINT `FKjgp4jc4hk7rf1xbllkxtka7bo` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `educational_qualifications`;
CREATE TABLE `educational_qualifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `degree` varchar(255) DEFAULT NULL,
  `diploma` varchar(255) DEFAULT NULL,
  `field_of_study` varchar(255) DEFAULT NULL,
  `institution_name` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs8ulv6r7mes3clsy0nb6axlu8` (`employee_id`),
  CONSTRAINT `FKs8ulv6r7mes3clsy0nb6axlu8` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `emergency_contacts`;
CREATE TABLE `emergency_contacts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `relationship` varchar(60) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdllve8ih07ir0a3ng8o5ubj4w` (`employee_id`),
  CONSTRAINT `FKdllve8ih07ir0a3ng8o5ubj4w` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `employee_designations`;
CREATE TABLE `employee_designations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_position` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) NOT NULL,
  `end_date` varchar(255) NOT NULL,
  `department_id` bigint NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKthxkaj844a7xke9315413ejiq` (`department_id`),
  KEY `FKrm1owp1dd6r5umhqbxrvughrh` (`employee_id`),
  CONSTRAINT `FKrm1owp1dd6r5umhqbxrvughrh` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `FKthxkaj844a7xke9315413ejiq` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `employee_leaves`;
CREATE TABLE `employee_leaves` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `leave_type` varchar(255) DEFAULT NULL,
  `leave_date` varchar(255) DEFAULT NULL,
  `leave_balance` int DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49gewuj53lf7foeyfpvcharf6` (`employee_id`),
  CONSTRAINT `FK49gewuj53lf7foeyfpvcharf6` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `birth_date` date NOT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `gender` enum('FEMALE','MALE','OTHER') NOT NULL,
  `marital_status` enum('DIVORCED','MARRIED','SEPARATED','SINGLE','UNKNOWN','WIDOWED') NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `department_id` bigint NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgy4qe3dnqrm3ktd76sxp7n4c2` (`department_id`),
  CONSTRAINT `FKgy4qe3dnqrm3ktd76sxp7n4c2` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
  CONSTRAINT `employees_chk_1` CHECK ((`gender` between 0 and 2)),
  CONSTRAINT `employees_chk_2` CHECK ((`marital_status` between 0 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `insurances`;
CREATE TABLE `insurances` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `insurance_type` varchar(255) DEFAULT NULL,
   `insurance_fee` decimal(10,2) NOT NULL,
   `period` int DEFAULT NULL,
   `monthly_deducted_amount` decimal(10,2) NOT NULL,
   `employee_id` bigint DEFAULT NULL,
   `created_at` bigint DEFAULT NULL,
   `created_by` varchar(60) DEFAULT NULL,
   `deleted_at` bigint DEFAULT NULL,
   `deleted_by` varchar(60) DEFAULT NULL,
   `modified_at` bigint DEFAULT NULL,
   `modified_by` varchar(60) DEFAULT NULL,
   `version` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `FKfi6petd13wlatfreoa8kh73mc` (`employee_id`),
   CONSTRAINT `FKfi6petd13wlatfreoa8kh73mc` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `issued_items`;
CREATE TABLE `issued_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `issued_date` varchar(255) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwq6gdq6wc5yq5k3mhseklpaw` (`employee_id`),
  CONSTRAINT `FKbwq6gdq6wc5yq5k3mhseklpaw` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `salaries`;
CREATE TABLE `salaries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `basic_salary` decimal(10,2) NOT NULL,
  `salary_date` varchar(255) DEFAULT NULL,
  `salary_schedule` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k6d98wl5t21yjtv0r560jqyq0` (`employee_id`),
  CONSTRAINT `FK1utmvufusgyktdtbmo4xfas10` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `work_histories`;
CREATE TABLE `work_histories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_title` varchar(255) DEFAULT NULL,
  `work_place` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `project` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `created_at` bigint DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `deleted_at` bigint DEFAULT NULL,
  `deleted_by` varchar(60) DEFAULT NULL,
  `modified_at` bigint DEFAULT NULL,
  `modified_by` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKld076p4ngmyhfabtbuh3nyc4a` (`employee_id`),
  CONSTRAINT `FKld076p4ngmyhfabtbuh3nyc4a` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-20 23:13:58
