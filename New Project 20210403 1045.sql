-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.31


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_bambo
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ db_bambo;
USE db_bambo;

--
-- Table structure for table `db_bambo`.`access_group`
--

DROP TABLE IF EXISTS `access_group`;
CREATE TABLE `access_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_title` varchar(255) NOT NULL DEFAULT '0',
  `group_des` varchar(255) NOT NULL DEFAULT '0',
  `group_creator` int(10) unsigned NOT NULL DEFAULT '0',
  `group_c01` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c02` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c03` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c04` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c05` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c06` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c07` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c08` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c09` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c10` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c11` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c12` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c13` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c14` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c15` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c16` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c17` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c18` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c19` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c20` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c21` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c22` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c23` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c24` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c25` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c26` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c27` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c28` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c29` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c30` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c31` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c32` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c33` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c34` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c35` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c36` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c37` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c38` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c39` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c40` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c41` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c42` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c43` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c44` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c45` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c46` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c47` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c48` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c49` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c50` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c51` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c52` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c53` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c54` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c55` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c56` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c57` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c58` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c59` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c60` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c61` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c62` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c63` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c64` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c65` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c66` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c67` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c68` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c69` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c70` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c71` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c72` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c73` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c74` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c75` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c76` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c77` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c78` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c79` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c80` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c81` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c82` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c83` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c84` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c85` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c86` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c87` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c88` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c89` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c90` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c91` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c92` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c93` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c94` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c95` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c96` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c97` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c98` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c99` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c100` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c101` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c102` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c103` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c104` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c105` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c106` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c107` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c108` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c109` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c110` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c111` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c112` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c113` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c114` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c115` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c116` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c117` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c118` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c119` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c120` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c121` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c122` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c123` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c124` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c125` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c126` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c127` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c128` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c129` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c130` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c131` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c132` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c133` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c134` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c135` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c136` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c137` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c138` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c139` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c140` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c141` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c142` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c143` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c144` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c145` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c146` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c147` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c148` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c149` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c150` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c151` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c152` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c153` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c154` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c155` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c156` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c157` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c158` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c159` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c160` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c161` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c162` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c163` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c164` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c165` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c166` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c167` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c168` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c169` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c170` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c171` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c172` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c173` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c174` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c175` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c176` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c177` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c178` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c179` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c180` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c181` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c182` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c183` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c184` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c185` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c186` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c187` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c188` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c189` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c190` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c191` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c192` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c193` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c194` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c195` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c196` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c197` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c198` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c199` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c200` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c201` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c202` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c203` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c204` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c205` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c206` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c207` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c208` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c209` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c210` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c211` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c212` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c213` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c214` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c215` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c216` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c217` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c218` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c219` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c220` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c221` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c222` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c223` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c224` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c225` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c226` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c227` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c228` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c229` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c230` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c231` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c232` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c233` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c234` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c235` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c236` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c237` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c238` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c239` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c240` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c241` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c242` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c243` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c244` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c245` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c246` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c247` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c248` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c249` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c250` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c251` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c252` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c253` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c254` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c255` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c256` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c257` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c258` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c259` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c260` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c261` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c262` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c263` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c264` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c265` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c266` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c267` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c268` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c269` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c270` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c271` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c272` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c273` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c274` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c275` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c276` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c277` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c278` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c279` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c280` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c281` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c282` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c283` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c284` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c285` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c286` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c287` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c288` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c289` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c290` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c291` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c292` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c293` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c294` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c295` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c296` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c297` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c298` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c299` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c300` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c301` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c302` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c303` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c304` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c305` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c306` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c307` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c308` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c309` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c310` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c311` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c312` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c313` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c314` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c315` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c316` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c317` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c318` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c319` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c320` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c321` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c322` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c323` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c324` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c325` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c326` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c327` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c328` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c329` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c330` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c331` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c332` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c333` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c334` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c335` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c336` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c337` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c338` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c339` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c340` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c341` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c342` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c343` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c344` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c345` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c346` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c347` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c348` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c349` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c350` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c351` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c352` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c353` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c354` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c355` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c356` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c357` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c358` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c359` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c360` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c361` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c362` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c363` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c364` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c365` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c366` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c367` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c368` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c369` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c370` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c371` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c372` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c373` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c374` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c375` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c376` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c377` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c378` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c379` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c380` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c381` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c382` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c383` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c384` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c385` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c386` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c387` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c388` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c389` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c390` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c391` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c392` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c393` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c394` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c395` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c396` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c397` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c398` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c399` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c400` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c401` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c402` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c403` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c404` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c405` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c406` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c407` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c408` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c409` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c410` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c411` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c412` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c413` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c414` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c415` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c416` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c417` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c418` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c419` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c420` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c421` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c422` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c423` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c424` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c425` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c426` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c427` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c428` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c429` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c430` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c431` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c432` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c433` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c434` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c435` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c436` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c437` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c438` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c439` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c440` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c441` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c442` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c443` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c444` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c445` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c446` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c447` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c448` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c449` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c450` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c451` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c452` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c453` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c454` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c455` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c456` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c457` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c458` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c459` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c460` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c461` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c462` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c463` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c464` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c465` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c466` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c467` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c468` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c469` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c470` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c471` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c472` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c473` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c474` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c475` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c476` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c477` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c478` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c479` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c480` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c481` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c482` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c483` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c484` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c485` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c486` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c487` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c488` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c489` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c490` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c491` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c492` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c493` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c494` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c495` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c496` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c497` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c498` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c499` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c500` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c501` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c502` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c503` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c504` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c505` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c506` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c507` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c508` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c509` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c510` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c511` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c512` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c513` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c514` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c515` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c516` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c517` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c518` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c519` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c520` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c521` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c522` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c523` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c524` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c525` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c526` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c527` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c528` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c529` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c530` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c531` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c532` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c533` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c534` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c535` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c536` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c537` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c538` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c539` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c540` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c541` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c542` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c543` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c544` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c545` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c546` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c547` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c548` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c549` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c550` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c551` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c552` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c553` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c554` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c555` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c556` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c557` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c558` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c559` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c560` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c561` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c562` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c563` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c564` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c565` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c566` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c567` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c568` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c569` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c570` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c571` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c572` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c573` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c574` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c575` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c576` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c577` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c578` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c579` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c580` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c581` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c582` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c583` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c584` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c585` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c586` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c587` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c588` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c589` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c590` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c591` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c592` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c593` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c594` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c595` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c596` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c597` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c598` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c599` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c600` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c601` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c602` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c603` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c604` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c605` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c606` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c607` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c608` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c609` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c610` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c611` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c612` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c613` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c614` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c615` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c616` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c617` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c618` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c619` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `group_c620` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`access_group`
--

/*!40000 ALTER TABLE `access_group` DISABLE KEYS */;
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (1,'برنامه نویس','$',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (2,'کاربر پیشرفته','$',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (3,'دانشجویان وفادار','',6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (4,'خریداران دوره ی مارکس','',6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (5,'خریداراان دوره ی هگل','',6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (6,'خریداران دوره ی هایدگر','',6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO `access_group` (`id`,`group_title`,`group_des`,`group_creator`,`group_c01`,`group_c02`,`group_c03`,`group_c04`,`group_c05`,`group_c06`,`group_c07`,`group_c08`,`group_c09`,`group_c10`,`group_c11`,`group_c12`,`group_c13`,`group_c14`,`group_c15`,`group_c16`,`group_c17`,`group_c18`,`group_c19`,`group_c20`,`group_c21`,`group_c22`,`group_c23`,`group_c24`,`group_c25`,`group_c26`,`group_c27`,`group_c28`,`group_c29`,`group_c30`,`group_c31`,`group_c32`,`group_c33`,`group_c34`,`group_c35`,`group_c36`,`group_c37`,`group_c38`,`group_c39`,`group_c40`,`group_c41`,`group_c42`,`group_c43`,`group_c44`,`group_c45`,`group_c46`,`group_c47`,`group_c48`,`group_c49`,`group_c50`,`group_c51`,`group_c52`,`group_c53`,`group_c54`,`group_c55`,`group_c56`,`group_c57`,`group_c58`,`group_c59`,`group_c60`,`group_c61`,`group_c62`,`group_c63`,`group_c64`,`group_c65`,`group_c66`,`group_c67`,`group_c68`,`group_c69`,`group_c70`,`group_c71`,`group_c72`,`group_c73`,`group_c74`,`group_c75`,`group_c76`,`group_c77`,`group_c78`,`group_c79`,`group_c80`,`group_c81`,`group_c82`,`group_c83`,`group_c84`,`group_c85`,`group_c86`,`group_c87`,`group_c88`,`group_c89`,`group_c90`,`group_c91`,`group_c92`,`group_c93`,`group_c94`,`group_c95`,`group_c96`,`group_c97`,`group_c98`,`group_c99`,`group_c100`,`group_c101`,`group_c102`,`group_c103`,`group_c104`,`group_c105`,`group_c106`,`group_c107`,`group_c108`,`group_c109`,`group_c110`,`group_c111`,`group_c112`,`group_c113`,`group_c114`,`group_c115`,`group_c116`,`group_c117`,`group_c118`,`group_c119`,`group_c120`,`group_c121`,`group_c122`,`group_c123`,`group_c124`,`group_c125`,`group_c126`,`group_c127`,`group_c128`,`group_c129`,`group_c130`,`group_c131`,`group_c132`,`group_c133`,`group_c134`,`group_c135`,`group_c136`,`group_c137`,`group_c138`,`group_c139`,`group_c140`,`group_c141`,`group_c142`,`group_c143`,`group_c144`,`group_c145`,`group_c146`,`group_c147`,`group_c148`,`group_c149`,`group_c150`,`group_c151`,`group_c152`,`group_c153`,`group_c154`,`group_c155`,`group_c156`,`group_c157`,`group_c158`,`group_c159`,`group_c160`,`group_c161`,`group_c162`,`group_c163`,`group_c164`,`group_c165`,`group_c166`,`group_c167`,`group_c168`,`group_c169`,`group_c170`,`group_c171`,`group_c172`,`group_c173`,`group_c174`,`group_c175`,`group_c176`,`group_c177`,`group_c178`,`group_c179`,`group_c180`,`group_c181`,`group_c182`,`group_c183`,`group_c184`,`group_c185`,`group_c186`,`group_c187`,`group_c188`,`group_c189`,`group_c190`,`group_c191`,`group_c192`,`group_c193`,`group_c194`,`group_c195`,`group_c196`,`group_c197`,`group_c198`,`group_c199`,`group_c200`,`group_c201`,`group_c202`,`group_c203`,`group_c204`,`group_c205`,`group_c206`,`group_c207`,`group_c208`,`group_c209`,`group_c210`,`group_c211`,`group_c212`,`group_c213`,`group_c214`,`group_c215`,`group_c216`,`group_c217`,`group_c218`,`group_c219`,`group_c220`,`group_c221`,`group_c222`,`group_c223`,`group_c224`,`group_c225`,`group_c226`,`group_c227`,`group_c228`,`group_c229`,`group_c230`,`group_c231`,`group_c232`,`group_c233`,`group_c234`,`group_c235`,`group_c236`,`group_c237`,`group_c238`,`group_c239`,`group_c240`,`group_c241`,`group_c242`,`group_c243`,`group_c244`,`group_c245`,`group_c246`,`group_c247`,`group_c248`,`group_c249`,`group_c250`,`group_c251`,`group_c252`,`group_c253`,`group_c254`,`group_c255`,`group_c256`,`group_c257`,`group_c258`,`group_c259`,`group_c260`,`group_c261`,`group_c262`,`group_c263`,`group_c264`,`group_c265`,`group_c266`,`group_c267`,`group_c268`,`group_c269`,`group_c270`,`group_c271`,`group_c272`,`group_c273`,`group_c274`,`group_c275`,`group_c276`,`group_c277`,`group_c278`,`group_c279`,`group_c280`,`group_c281`,`group_c282`,`group_c283`,`group_c284`,`group_c285`,`group_c286`,`group_c287`,`group_c288`,`group_c289`,`group_c290`,`group_c291`,`group_c292`,`group_c293`,`group_c294`,`group_c295`,`group_c296`,`group_c297`,`group_c298`,`group_c299`,`group_c300`,`group_c301`,`group_c302`,`group_c303`,`group_c304`,`group_c305`,`group_c306`,`group_c307`,`group_c308`,`group_c309`,`group_c310`,`group_c311`,`group_c312`,`group_c313`,`group_c314`,`group_c315`,`group_c316`,`group_c317`,`group_c318`,`group_c319`,`group_c320`,`group_c321`,`group_c322`,`group_c323`,`group_c324`,`group_c325`,`group_c326`,`group_c327`,`group_c328`,`group_c329`,`group_c330`,`group_c331`,`group_c332`,`group_c333`,`group_c334`,`group_c335`,`group_c336`,`group_c337`,`group_c338`,`group_c339`,`group_c340`,`group_c341`,`group_c342`,`group_c343`,`group_c344`,`group_c345`,`group_c346`,`group_c347`,`group_c348`,`group_c349`,`group_c350`,`group_c351`,`group_c352`,`group_c353`,`group_c354`,`group_c355`,`group_c356`,`group_c357`,`group_c358`,`group_c359`,`group_c360`,`group_c361`,`group_c362`,`group_c363`,`group_c364`,`group_c365`,`group_c366`,`group_c367`,`group_c368`,`group_c369`,`group_c370`,`group_c371`,`group_c372`,`group_c373`,`group_c374`,`group_c375`,`group_c376`,`group_c377`,`group_c378`,`group_c379`,`group_c380`,`group_c381`,`group_c382`,`group_c383`,`group_c384`,`group_c385`,`group_c386`,`group_c387`,`group_c388`,`group_c389`,`group_c390`,`group_c391`,`group_c392`,`group_c393`,`group_c394`,`group_c395`,`group_c396`,`group_c397`,`group_c398`,`group_c399`,`group_c400`,`group_c401`,`group_c402`,`group_c403`,`group_c404`,`group_c405`,`group_c406`,`group_c407`,`group_c408`,`group_c409`,`group_c410`,`group_c411`,`group_c412`,`group_c413`,`group_c414`,`group_c415`,`group_c416`,`group_c417`,`group_c418`,`group_c419`,`group_c420`,`group_c421`,`group_c422`,`group_c423`,`group_c424`,`group_c425`,`group_c426`,`group_c427`,`group_c428`,`group_c429`,`group_c430`,`group_c431`,`group_c432`,`group_c433`,`group_c434`,`group_c435`,`group_c436`,`group_c437`,`group_c438`,`group_c439`,`group_c440`,`group_c441`,`group_c442`,`group_c443`,`group_c444`,`group_c445`,`group_c446`,`group_c447`,`group_c448`,`group_c449`,`group_c450`,`group_c451`,`group_c452`,`group_c453`,`group_c454`,`group_c455`,`group_c456`,`group_c457`,`group_c458`,`group_c459`,`group_c460`,`group_c461`,`group_c462`,`group_c463`,`group_c464`,`group_c465`,`group_c466`,`group_c467`,`group_c468`,`group_c469`,`group_c470`,`group_c471`,`group_c472`,`group_c473`,`group_c474`,`group_c475`,`group_c476`,`group_c477`,`group_c478`,`group_c479`,`group_c480`,`group_c481`,`group_c482`,`group_c483`,`group_c484`,`group_c485`,`group_c486`,`group_c487`,`group_c488`,`group_c489`,`group_c490`,`group_c491`,`group_c492`,`group_c493`,`group_c494`,`group_c495`,`group_c496`,`group_c497`,`group_c498`,`group_c499`,`group_c500`,`group_c501`,`group_c502`,`group_c503`,`group_c504`,`group_c505`,`group_c506`,`group_c507`,`group_c508`,`group_c509`,`group_c510`,`group_c511`,`group_c512`,`group_c513`,`group_c514`,`group_c515`,`group_c516`,`group_c517`,`group_c518`,`group_c519`,`group_c520`,`group_c521`,`group_c522`,`group_c523`,`group_c524`,`group_c525`,`group_c526`,`group_c527`,`group_c528`,`group_c529`,`group_c530`,`group_c531`,`group_c532`,`group_c533`,`group_c534`,`group_c535`,`group_c536`,`group_c537`,`group_c538`,`group_c539`,`group_c540`,`group_c541`,`group_c542`,`group_c543`,`group_c544`,`group_c545`,`group_c546`,`group_c547`,`group_c548`,`group_c549`,`group_c550`,`group_c551`,`group_c552`,`group_c553`,`group_c554`,`group_c555`,`group_c556`,`group_c557`,`group_c558`,`group_c559`,`group_c560`,`group_c561`,`group_c562`,`group_c563`,`group_c564`,`group_c565`,`group_c566`,`group_c567`,`group_c568`,`group_c569`,`group_c570`,`group_c571`,`group_c572`,`group_c573`,`group_c574`,`group_c575`,`group_c576`,`group_c577`,`group_c578`,`group_c579`,`group_c580`,`group_c581`,`group_c582`,`group_c583`,`group_c584`,`group_c585`,`group_c586`,`group_c587`,`group_c588`,`group_c589`,`group_c590`,`group_c591`,`group_c592`,`group_c593`,`group_c594`,`group_c595`,`group_c596`,`group_c597`,`group_c598`,`group_c599`,`group_c600`,`group_c601`,`group_c602`,`group_c603`,`group_c604`,`group_c605`,`group_c606`,`group_c607`,`group_c608`,`group_c609`,`group_c610`,`group_c611`,`group_c612`,`group_c613`,`group_c614`,`group_c615`,`group_c616`,`group_c617`,`group_c618`,`group_c619`,`group_c620`) VALUES 
 (7,'خریداران دوره ی جامعه شناسی دین','',6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `access_group` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`access_user`
--

DROP TABLE IF EXISTS `access_user`;
CREATE TABLE `access_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_pass` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_family` varchar(100) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_is_active` int(10) unsigned NOT NULL DEFAULT '0',
  `user_createDate` int(10) unsigned DEFAULT NULL,
  `user_question` varchar(255) DEFAULT NULL,
  `user_answer` varchar(255) DEFAULT NULL,
  `user_no1` varchar(255) DEFAULT NULL,
  `user_no2` varchar(255) DEFAULT NULL,
  `user_parent` varchar(100) DEFAULT NULL,
  `user_birthdate` int(10) unsigned DEFAULT NULL,
  `user_weblog` longtext,
  `user_char1` varchar(255) DEFAULT NULL,
  `user_char2` varchar(255) DEFAULT NULL,
  `user_char3` varchar(255) DEFAULT NULL,
  `user_int1` int(10) unsigned DEFAULT NULL,
  `user_int2` int(10) unsigned DEFAULT NULL,
  `user_int3` int(10) unsigned DEFAULT NULL,
  `user_password_hint` varchar(225) DEFAULT NULL,
  `user_mobile` varchar(100) DEFAULT NULL,
  `user_AccountInformation` varchar(100) DEFAULT NULL,
  `user_address` varchar(100) DEFAULT NULL,
  `user_jensiat` varchar(45) DEFAULT NULL,
  `user_codeMeli` varchar(100) DEFAULT NULL,
  `user_shomareShenasname` varchar(100) DEFAULT NULL,
  `user_grade` varchar(100) DEFAULT NULL,
  `user_passwordReminder` varchar(255) DEFAULT NULL,
  `user_file_personal` varchar(45) DEFAULT NULL,
  `user_file_Signature` varchar(45) DEFAULT NULL,
  `user_upload_file` varchar(45) DEFAULT NULL,
  `user_attachFile` varchar(255) DEFAULT NULL,
  `user_attachPicPersonal` varchar(45) DEFAULT NULL,
  `user_attachPicPersonnelCard` varchar(45) DEFAULT NULL,
  `user_attachPicSignature` varchar(45) DEFAULT NULL,
  `user_token` varchar(100) DEFAULT NULL,
  `user_adminDescription` longtext,
  `user_postalCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`access_user`
--

/*!40000 ALTER TABLE `access_user` DISABLE KEYS */;
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (0,'system','کاربر','مهمان','system',1,13910822,'','','11','22','0',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09133126753',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'aaaa','aaaa','aaaa','aaaa',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09132311464',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'bbbb','bbbb','bbbb','bbbb',1,13930202,'1','morteza','1270428020','1270428020','0',13681013,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09133368036',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (4,'cccc','cccc','cccc','cccc',1,13930304,'نام بهترین دوست شما چیست؟','Atena','','','0',13631118,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09133126753','',NULL,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'dddd','dddd','dddd','dddd',1,13930416,'نام بهترین دوست شما چیست؟','علی','','','0',19930711,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09133126753','','','','','','','',NULL,NULL,NULL,'p7074313598.jpg','','','',NULL,NULL,NULL),
 (6,'123456','ادمین','ادمین','admin@yahoo.com',1,NULL,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','کارشناسی ارشد','1-6',NULL,NULL,NULL,'','p0434244342.png','p1493770215.jpg','p1493770215.jpg','0',NULL,NULL);
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (7,'123456','افسانه','کیانی','a@yahoo.com',1,NULL,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL),
 (8,'123456','m','m','m',1,NULL,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','خانم','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL),
 (9,'Parsa17mehr','سمیه','صلصالی','somaye.salsali@gmail.com',1,13990719,NULL,NULL,NULL,NULL,'6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Parsa17mehr','03136305395',NULL,'اصفهان خیابان آبشار سوم',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (10,'1qaz!QAZ','enamdad','enamad','enamad',1,NULL,NULL,NULL,NULL,NULL,'6',13990720,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','خانم','','','','',NULL,NULL,NULL,NULL,'','','',NULL,NULL,NULL),
 (11,'654321','کودک','شاد','happykids@gmail.com',1,NULL,NULL,NULL,NULL,NULL,'6',13990721,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','','خانم','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL),
 (12,'1323','فریبا','ماه','mah.fariba@yahoo.com',1,13990801,NULL,NULL,NULL,NULL,'6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1323','09131291323',NULL,'سیخ صندوق سمالی',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (13,'mm123456','محمد','ثالثی','mrsalesi@gmail.com',1,13990823,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ءئ123456','','','اصفهان- سه راه حکیم نظامی','','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL),
 (14,'2551377','سیده الهه','رحمانی حصار','elaheh.rahmani@yahoo.com',1,13990823,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2551377','','','مشهد، رضاشهر','','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL),
 (15,'123456','میلاد','دخانچی','milad@gmail.com',1,13990824,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456','','','تهران','','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL);
INSERT INTO `access_user` (`id`,`user_pass`,`user_name`,`user_family`,`user_email`,`user_is_active`,`user_createDate`,`user_question`,`user_answer`,`user_no1`,`user_no2`,`user_parent`,`user_birthdate`,`user_weblog`,`user_char1`,`user_char2`,`user_char3`,`user_int1`,`user_int2`,`user_int3`,`user_password_hint`,`user_mobile`,`user_AccountInformation`,`user_address`,`user_jensiat`,`user_codeMeli`,`user_shomareShenasname`,`user_grade`,`user_passwordReminder`,`user_file_personal`,`user_file_Signature`,`user_upload_file`,`user_attachFile`,`user_attachPicPersonal`,`user_attachPicPersonnelCard`,`user_attachPicSignature`,`user_token`,`user_adminDescription`,`user_postalCode`) VALUES 
 (16,'mm123456','محمد','ثالثی2','mohammad_ms_ms@yahoo.com',1,13990916,NULL,NULL,NULL,NULL,'6',90000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Mm123456','','','اصفهان','','','','','',NULL,NULL,NULL,'','','','',NULL,NULL,NULL);
/*!40000 ALTER TABLE `access_user` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`access_user_group`
--

DROP TABLE IF EXISTS `access_user_group`;
CREATE TABLE `access_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `group_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`access_user_group`
--

/*!40000 ALTER TABLE `access_user_group` DISABLE KEYS */;
INSERT INTO `access_user_group` (`id`,`user_id`,`group_id`) VALUES 
 (57,1,1),
 (143,2,1),
 (152,3,2),
 (155,6,2),
 (157,8,9),
 (158,8,11),
 (171,11,0),
 (179,14,6),
 (180,15,6),
 (182,13,4),
 (183,13,6),
 (187,16,7);
/*!40000 ALTER TABLE `access_user_group` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`account_cust`
--

DROP TABLE IF EXISTS `account_cust`;
CREATE TABLE `account_cust` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_cust_name` varchar(45) DEFAULT NULL,
  `account_cust_famil` varchar(45) DEFAULT NULL,
  `account_cust_ful_name` varchar(45) DEFAULT NULL,
  `account_cust_code` varchar(45) DEFAULT NULL,
  `account_cust_birth` int(10) unsigned DEFAULT NULL,
  `account_cust_tell` varchar(45) DEFAULT NULL,
  `account_cust_mob` varchar(45) DEFAULT NULL,
  `account_cust_fax` varchar(45) DEFAULT NULL,
  `account_cust_val1` varchar(45) DEFAULT NULL,
  `account_cust_val2` varchar(45) DEFAULT NULL,
  `account_cust_val3` varchar(45) DEFAULT NULL,
  `account_cust_val4` varchar(45) DEFAULT NULL,
  `account_cust_val5` varchar(45) DEFAULT NULL,
  `account_cust_val6` varchar(100) DEFAULT NULL,
  `account_cust_val7` varchar(100) DEFAULT NULL,
  `account_cust_val8` varchar(100) DEFAULT NULL,
  `account_cust_val9` varchar(100) DEFAULT NULL,
  `account_cust_val10` longtext,
  `account_cust_val11` varchar(255) DEFAULT NULL,
  `account_cust_val12` varchar(255) DEFAULT NULL,
  `account_cust_val13` varchar(255) DEFAULT NULL,
  `account_cust_val14` varchar(255) DEFAULT NULL,
  `account_cust_val15` varchar(255) DEFAULT NULL,
  `account_cust_val16` int(10) unsigned DEFAULT NULL,
  `account_cust_val17` int(10) unsigned DEFAULT NULL,
  `account_cust_val18` int(10) unsigned DEFAULT NULL,
  `account_cust_val19` int(10) unsigned DEFAULT NULL,
  `account_cust_val20` int(10) unsigned DEFAULT NULL,
  `account_cust_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`account_cust`
--

/*!40000 ALTER TABLE `account_cust` DISABLE KEYS */;
INSERT INTO `account_cust` (`id`,`account_cust_name`,`account_cust_famil`,`account_cust_ful_name`,`account_cust_code`,`account_cust_birth`,`account_cust_tell`,`account_cust_mob`,`account_cust_fax`,`account_cust_val1`,`account_cust_val2`,`account_cust_val3`,`account_cust_val4`,`account_cust_val5`,`account_cust_val6`,`account_cust_val7`,`account_cust_val8`,`account_cust_val9`,`account_cust_val10`,`account_cust_val11`,`account_cust_val12`,`account_cust_val13`,`account_cust_val14`,`account_cust_val15`,`account_cust_val16`,`account_cust_val17`,`account_cust_val18`,`account_cust_val19`,`account_cust_val20`,`account_cust_email`) VALUES 
 (1,'','','محمد ثالثی','',13920528,'','09133368036','','','','','','','','','','','','','','','','',0,0,0,0,0,NULL),
 (2,'محمد','ثالثی','محمد ثالثی',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dgdfgdfg',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),
 (3,'افسانه','کیانی','افسانه کیانی',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,NULL,NULL),
 (4,'امید','نجدی','امید نجدی',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_cust` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`account_factor`
--

DROP TABLE IF EXISTS `account_factor`;
CREATE TABLE `account_factor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_factor_code` varchar(45) DEFAULT NULL,
  `account_factor_user_id` int(10) unsigned DEFAULT NULL,
  `account_factor_user_name` varchar(255) DEFAULT NULL,
  `account_factor_cust_id` int(10) unsigned DEFAULT NULL,
  `account_factor_cust_name` varchar(255) DEFAULT NULL,
  `account_factor_type` int(10) unsigned DEFAULT NULL,
  `account_factor_date` int(10) unsigned DEFAULT NULL,
  `account_factor_sum` int(10) unsigned DEFAULT NULL,
  `account_factor_discount` int(10) unsigned DEFAULT NULL,
  `account_factor_pay` int(10) unsigned DEFAULT NULL,
  `account_factor_remainder` int(10) unsigned DEFAULT NULL,
  `account_factor_comment` varchar(255) DEFAULT NULL,
  `account_factor_pr_id_1` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_2` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_3` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_4` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_5` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_6` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_7` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_8` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_9` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_10` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_11` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_12` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_13` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_14` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_15` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_16` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_17` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_18` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_19` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_id_20` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_code_1` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_2` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_3` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_4` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_5` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_6` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_7` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_8` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_9` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_10` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_11` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_12` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_13` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_14` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_15` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_16` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_17` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_18` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_19` varchar(45) DEFAULT NULL,
  `account_factor_pr_code_20` varchar(45) DEFAULT NULL,
  `account_factor_pr_name_1` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_2` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_3` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_4` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_5` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_6` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_7` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_8` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_9` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_10` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_11` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_12` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_13` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_14` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_15` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_16` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_17` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_18` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_19` varchar(100) DEFAULT NULL,
  `account_factor_pr_name_20` varchar(100) DEFAULT NULL,
  `account_factor_pr_count_1` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_2` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_3` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_4` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_5` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_6` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_7` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_8` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_9` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_10` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_11` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_12` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_13` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_14` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_15` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_16` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_17` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_18` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_19` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_count_20` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_unit_1` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_2` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_3` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_4` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_5` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_6` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_7` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_8` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_9` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_10` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_11` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_12` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_13` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_14` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_15` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_16` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_17` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_18` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_19` varchar(45) DEFAULT NULL,
  `account_factor_pr_unit_20` varchar(45) DEFAULT NULL,
  `account_factor_pr_fee_1` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_2` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_3` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_4` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_5` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_6` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_7` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_8` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_9` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_10` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_11` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_12` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_13` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_14` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_15` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_16` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_17` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_18` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_19` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_fee_20` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_1` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_2` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_3` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_4` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_5` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_6` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_7` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_8` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_9` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_10` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_11` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_12` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_13` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_14` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_15` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_16` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_17` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_18` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_19` int(10) unsigned DEFAULT NULL,
  `account_factor_pr_sum_20` int(10) unsigned DEFAULT NULL,
  `account_factor_is_deliver` int(10) unsigned DEFAULT NULL,
  `account_factor_status` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`account_factor`
--

/*!40000 ALTER TABLE `account_factor` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_factor` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`account_product`
--

DROP TABLE IF EXISTS `account_product`;
CREATE TABLE `account_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_product_page` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `account_product_name` varchar(500) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `account_product_quantity` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'تعداد موجودی',
  `account_product_pic1` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_pic_ext` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_price1` int(11) DEFAULT NULL,
  `account_product_price2` int(11) DEFAULT NULL,
  `account_product_currency` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `account_product_code` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val1` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val2` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val3` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val4` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val5` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val6` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val7` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val8` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val9` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val10` longtext CHARACTER SET utf8,
  `account_product_val11` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val12` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val13` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val14` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val15` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val16` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val17` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val18` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val19` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_val20` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_lang` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `account_product_parent` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `account_product_priority` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `account_product_visit` int(11) NOT NULL DEFAULT '1',
  `account_product_order` int(11) NOT NULL DEFAULT '0' COMMENT 'order numbers by customers',
  `account_product_rating` double NOT NULL DEFAULT '0',
  `account_product_like` int(11) NOT NULL DEFAULT '0',
  `account_product_dislike` int(11) NOT NULL DEFAULT '0',
  `account_product_date` int(10) unsigned NOT NULL DEFAULT '10000000',
  `account_product_category_id` int(10) unsigned NOT NULL DEFAULT '0',
  `account_products_abstract` longtext CHARACTER SET utf8,
  `account_products_content` longtext CHARACTER SET utf8,
  `account_product_contentWithWikiLinks` longtext,
  `account_product_pic2` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_pic3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_pic4` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_pic5` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_pic6` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop1` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop2` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop3` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop4` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop5` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop6` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop7` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop8` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop9` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop10` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop11` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop12` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop13` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop14` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop15` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop16` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop17` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop18` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop19` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_prop20` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_tags` longtext CHARACTER SET utf8,
  `account_product_titleTag` varchar(250) DEFAULT NULL,
  `account_product_description` varchar(500) DEFAULT NULL,
  `account_product_style` longtext,
  `account_product_script` longtext,
  `account_products_relatedProducts` longtext CHARACTER SET utf8,
  `account_products_active` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `account_products_block` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `account_product_groupPrice1` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_groupPrice2` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_groupPrice3` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_groupPrice4` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_groupPrice5` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_userGroup1` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_userGroup2` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_userGroup3` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_userGroup4` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_userGroup5` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_taxPercent` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `account_product_discount` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_discount_Date` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_discount_Time` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `account_product_has_link` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `account_product_hasInContentAutoWiki` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `account_product_autoWikIsUpdate` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `account_product_htmlBeforBuy` longtext,
  `account_product_htmlAfterBuy` longtext,
  `account_product_scriptBeforBuy` longtext,
  `account_product_scriptAfterBuy` longtext,
  `account_product_creator` int(10) unsigned NOT NULL DEFAULT '0',
  `account_product_startDate` varchar(100) DEFAULT NULL,
  `account_product_startTime` varchar(100) DEFAULT NULL,
  `account_product_endDate` varchar(100) DEFAULT NULL,
  `account_product_endTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`account_product`
--

/*!40000 ALTER TABLE `account_product` DISABLE KEYS */;
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (83,'','شیک',1100,'shake.jpg','',200000,250000,'ریال','','300 گرم','شنبه 15 آان','شنبه 22 آبان','شنبه 30 آبان','','','','','','','','','','','','0','0','0','0','0','1','0','1',26,0,0,0,0,13990708,40,'','<p><br></p>','<p><br></p>','p6708965080.jpg','p0653222848.jpg','','','','افزودنی','افزودنی','افزودنی','افزودنی','','','','','','','','','','','','','','','','','null','','','','','',1,0,'220000','230000','250000','250000','250000','1','2','0','0','0','0','240000','13990618','17:15',1,1,0,NULL,NULL,NULL,NULL,6,'0','1753','90000000','1753');
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (92,'','بستنی ویژه',1200,'specialIceCream.jpg','',200000,200000,'ریال','','200 گرم','شنبه 24 آبان','شنبه 7 آذر','شنبه 15 آذر','','','','','','','','','','','','0','0','0','0','0','1','0','1',0,0,0,0,0,13990708,40,'','<p style=\"color:\" red;=\"\">در صورت خریداری، و اطلاع به پرسنل دسترسی این دوره برای شما فعال می گردد.<br></p>','new jj(\'<p style=\'direction:ltr\'>cms.cms.Product.ConvertToWiki > line:7160</p><p style=\'direction:ltr\'>cms.cms.Product.insert > line:408</p><p style=\'direction:ltr\'>java.lang.NullPointerException</p>\').jjModal(\'systemException\');','p1554169563.jpg','p3177906356.jpg','p1018936376.png','','','افزودنی','افزودنی','افزودنی','افزودنی','','','','','','','','','','','','','','','','','null','','','','','',1,0,'200000','200000','200000','200000','200000','0','0','0','0','0','0','','13990708','16:16',1,1,0,NULL,NULL,NULL,NULL,6,'0','2256','90000000','2256');
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (93,'','پیتزا مخصوص بامبو',0,'chikenPitza.jpg','',20000000,670000,'ریال','','300 گرم','شنبه 25 آبان','','','','','','','','','','','','','','0','0','0','0','0','1','0','1',0,0,0,0,0,13990819,44,'','<p>در صورت خریداری، و اطلاع به پرسنل دسترسی این دوره برای شما فعال می گردد.</p>','new jj(\'<p style=\'direction:ltr\'>cms.cms.Product.ConvertToWiki > line:7155</p><p style=\'direction:ltr\'>cms.cms.Product.insert > line:408</p><p style=\'direction:ltr\'>java.lang.NullPointerException</p>\').jjModal(\'systemException\');','','','','','','افزودنی','افزودنی','','','','','','','','','','','','','','','','','','','null','','','','','',1,0,'0','0','0','0','0','1','1','1','1','1','0','','13990819','16:16',1,1,0,NULL,NULL,NULL,NULL,6,'0','1051','90000000','1051');
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (94,'','پیتزا ویژه یونانی',100,'chikenPitza.jpg','',200000,200000,'ریال','','300 گرم','شنبه 18 آذر','','','','','','','','','','','','','','0','0','0','0','0','1','0','1',0,0,0,0,0,13990819,44,'','<p>در صورت خریداری، و اطلاع به پرسنل دسترسی این دوره برای شما فعال می گردد.</p>','new jj(\'<p style=\'direction:ltr\'>cms.cms.Product.ConvertToWiki > line:7155</p><p style=\'direction:ltr\'>cms.cms.Product.insert > line:408</p><p style=\'direction:ltr\'>java.lang.NullPointerException</p>\').jjModal(\'systemException\');','','','','','','افزودنی','افزودنی','','','','','','','','','','','','','','','','','','','null','','','','','',1,0,'200000','200000','200000','200000','200000','1','2','0','0','0','0','240000','13990819','11:09',1,1,0,NULL,NULL,NULL,NULL,6,'0','1051','90000000','1051');
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (95,'','استیک مرغ',100,'chikenSteak.png','',200000,200000,'ریال','','نان اضافه','شنبه 25 آذر','','','','','','','','','','','','','','0','0','0','0','0','1','0','1',0,0,0,0,0,13990819,45,'','<p>در صورت خریداری، و اطلاع به پرسنل دسترسی این دوره برای شما فعال می گردد.</p>','new jj(\'<p style=\'direction:ltr\'>cms.cms.Product.ConvertToWiki > line:7155</p><p style=\'direction:ltr\'>cms.cms.Product.insert > line:408</p><p style=\'direction:ltr\'>java.lang.NullPointerException</p>\').jjModal(\'systemException\');','','','','','','افزودنی','افزودنی','','','','','','','','','','','','','','','','','','','null','','','','','',1,0,'','','','','','1','2','0','0','0','0','240000','13990819','11:11',1,1,1,NULL,NULL,NULL,NULL,6,'0','1051','90000000','1051');
INSERT INTO `account_product` (`id`,`account_product_page`,`account_product_name`,`account_product_quantity`,`account_product_pic1`,`account_product_pic_ext`,`account_product_price1`,`account_product_price2`,`account_product_currency`,`account_product_code`,`account_product_val1`,`account_product_val2`,`account_product_val3`,`account_product_val4`,`account_product_val5`,`account_product_val6`,`account_product_val7`,`account_product_val8`,`account_product_val9`,`account_product_val10`,`account_product_val11`,`account_product_val12`,`account_product_val13`,`account_product_val14`,`account_product_val15`,`account_product_val16`,`account_product_val17`,`account_product_val18`,`account_product_val19`,`account_product_val20`,`account_product_lang`,`account_product_parent`,`account_product_priority`,`account_product_visit`,`account_product_order`,`account_product_rating`,`account_product_like`,`account_product_dislike`,`account_product_date`,`account_product_category_id`,`account_products_abstract`,`account_products_content`,`account_product_contentWithWikiLinks`,`account_product_pic2`,`account_product_pic3`,`account_product_pic4`,`account_product_pic5`,`account_product_pic6`,`account_product_prop1`,`account_product_prop2`,`account_product_prop3`,`account_product_prop4`,`account_product_prop5`,`account_product_prop6`,`account_product_prop7`,`account_product_prop8`,`account_product_prop9`,`account_product_prop10`,`account_product_prop11`,`account_product_prop12`,`account_product_prop13`,`account_product_prop14`,`account_product_prop15`,`account_product_prop16`,`account_product_prop17`,`account_product_prop18`,`account_product_prop19`,`account_product_prop20`,`account_product_tags`,`account_product_titleTag`,`account_product_description`,`account_product_style`,`account_product_script`,`account_products_relatedProducts`,`account_products_active`,`account_products_block`,`account_product_groupPrice1`,`account_product_groupPrice2`,`account_product_groupPrice3`,`account_product_groupPrice4`,`account_product_groupPrice5`,`account_product_userGroup1`,`account_product_userGroup2`,`account_product_userGroup3`,`account_product_userGroup4`,`account_product_userGroup5`,`account_product_taxPercent`,`account_product_discount`,`account_product_discount_Date`,`account_product_discount_Time`,`account_product_has_link`,`account_product_hasInContentAutoWiki`,`account_product_autoWikIsUpdate`,`account_product_htmlBeforBuy`,`account_product_htmlAfterBuy`,`account_product_scriptBeforBuy`,`account_product_scriptAfterBuy`,`account_product_creator`,`account_product_startDate`,`account_product_startTime`,`account_product_endDate`,`account_product_endTime`) VALUES 
 (96,'','فیله مرغ سوخاری',1000,'chikenStrips.png','',200000,10000,'ریال','','خوشمزه و لذیذ','3','دکتر حسن محدثی','جمعه 14 آذر، یکشنبه 16آذر، چهارشنبه 19 آذر، ساعت 20-22','لینک آنلاین','','','','','','','','','','','0','0','0','0','0','1','0','1',0,0,0,0,0,13990916,42,'نظر دورکیم، وبر، نیچه و دیگر متفکران جهان غرب درباره‌ دین چیست؟ آیا برای آنها دین راه هدایت بشر است یا عکس آن؟ پاسخ آنها در مقام جامعه شناسان حوزه دین به این سوال احتمال این است:هیچکدام! جامعه‌شناس حوزه دین نه از داخل بلکه از بیرون پدیده‌های مربوط به مذهب را مطالعه می‌کند، او مطالعه درباره مذهب را از باورهای شخصی خود جدا می‌کند و سعی می‌کند پدیده‌ها را آنگونه که هست ببیند. در طعم موضوع بعدی میخواهیم طعمی از جامعه شناسی دین را بچشیم','<p>مرحله‌ی اول: در سایت ثبت نام کنید یا اگر قبلا ثبت نام کرده اید وارد شوید</p><p>مرحله‌ی دوم: دوره را به سبد خرید خود اضافه کنید و سبد خرید را پرداخت کنید</p><p>مرحله‌ی سوم: کارشناسان مدرسه دسترسی شما به محتوای آموزشی دوره را ایجاد می کنند</p><p>مرحله‌ی چهارم: در قسمت کلاس های من محتوای کلاس وجود دارد: زمان برگزاری دوره لینک کلاس را میتوانید ببینید و بعد از آن فایل ها صوتی و متنی دوره در قسمت دوره ها قرار خواهد گرفت</p>','new jj(\'<p style=\'direction:ltr\'>cms.cms.Product.ConvertToWiki > line:7155</p><p style=\'direction:ltr\'>cms.cms.Product.insert > line:408</p><p style=\'direction:ltr\'>java.lang.NullPointerException</p>\').jjModal(\'systemException\');','','','','','','افزودنی','افزودنی','افزودنی','افزودنی','افزودنی','','','','','','','','','','','','','','','','null','','','','','',1,0,'9000','0','0','0','0','3','1','1','1','1','0','','13990819','17:14',1,1,0,NULL,NULL,NULL,NULL,6,'0','1051','90000000','1051');
/*!40000 ALTER TABLE `account_product` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answer_text` text,
  `answer_date` int(10) unsigned DEFAULT NULL,
  `question_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`answer`
--

/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`attachment`
--

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `ID` bigint(20) NOT NULL,
  `FILE_NAME` varchar(50) NOT NULL,
  `FILE_DATA` blob NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`attachment`
--

/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_content`
--

DROP TABLE IF EXISTS `category_content`;
CREATE TABLE `category_content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_content_title` varchar(500) DEFAULT NULL,
  `category_content_parent` int(10) unsigned DEFAULT NULL,
  `category_content_lang` int(10) unsigned DEFAULT NULL,
  `category_content_level` int(10) unsigned DEFAULT NULL,
  `category_content_explain` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_content`
--

/*!40000 ALTER TABLE `category_content` DISABLE KEYS */;
INSERT INTO `category_content` (`id`,`category_content_title`,`category_content_parent`,`category_content_lang`,`category_content_level`,`category_content_explain`) VALUES 
 (1,'اخبار',0,1,1,''),
 (2,'اسلایدر',0,1,1,''),
 (3,'آموزش به کودک',0,1,1,''),
 (4,'مقالات',0,1,1,''),
 (5,'کارگاه ها',0,1,1,''),
 (6,'کلاس ها',0,1,1,'');
/*!40000 ALTER TABLE `category_content` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_forum`
--

DROP TABLE IF EXISTS `category_forum`;
CREATE TABLE `category_forum` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_forum_title` varchar(255) DEFAULT NULL,
  `category_forum_creator` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_forum`
--

/*!40000 ALTER TABLE `category_forum` DISABLE KEYS */;
INSERT INTO `category_forum` (`id`,`category_forum_title`,`category_forum_creator`) VALUES 
 (1,'تست',1);
/*!40000 ALTER TABLE `category_forum` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_gallery`
--

DROP TABLE IF EXISTS `category_gallery`;
CREATE TABLE `category_gallery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_gallery_title` varchar(100) DEFAULT NULL,
  `category_gallery_parent` int(10) unsigned DEFAULT NULL,
  `category_gallery_lang` int(10) unsigned DEFAULT NULL,
  `category_gallery_level` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_gallery`
--

/*!40000 ALTER TABLE `category_gallery` DISABLE KEYS */;
INSERT INTO `category_gallery` (`id`,`category_gallery_title`,`category_gallery_parent`,`category_gallery_lang`,`category_gallery_level`) VALUES 
 (1,'اسلایدر',0,1,1),
 (2,'تصاویر سایت',0,1,1),
 (4,'همه',0,1,1),
 (5,'مناسبت ها',0,1,1),
 (6,'اردو ها',0,1,1),
 (7,'کلاس های آموزشی',0,1,1);
/*!40000 ALTER TABLE `category_gallery` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_news`
--

DROP TABLE IF EXISTS `category_news`;
CREATE TABLE `category_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_news_title` varchar(255) DEFAULT NULL,
  `category_news_parent` int(10) unsigned DEFAULT NULL,
  `category_news_lang` int(10) unsigned NOT NULL DEFAULT '1',
  `category_news_level` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'as linking list or tree structure and for subset',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_news`
--

/*!40000 ALTER TABLE `category_news` DISABLE KEYS */;
INSERT INTO `category_news` (`id`,`category_news_title`,`category_news_parent`,`category_news_lang`,`category_news_level`) VALUES 
 (1,'kjnhkj',0,1,1);
/*!40000 ALTER TABLE `category_news` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_poll`
--

DROP TABLE IF EXISTS `category_poll`;
CREATE TABLE `category_poll` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_poll_which` int(10) unsigned DEFAULT NULL,
  `category_poll_user_id` int(10) unsigned DEFAULT NULL,
  `category_poll_answer` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_poll`
--

/*!40000 ALTER TABLE `category_poll` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_poll` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`category_product`
--

DROP TABLE IF EXISTS `category_product`;
CREATE TABLE `category_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_product_title` varchar(255) DEFAULT NULL,
  `category_product_parent` int(10) unsigned DEFAULT NULL,
  `category_product_lang` int(10) unsigned DEFAULT NULL,
  `category_product_level` int(10) unsigned DEFAULT NULL,
  `category_product_explain` varchar(500) DEFAULT NULL,
  `category_product_icon` varchar(45) DEFAULT NULL,
  `category_product_pic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`category_product`
--

/*!40000 ALTER TABLE `category_product` DISABLE KEYS */;
INSERT INTO `category_product` (`id`,`category_product_title`,`category_product_parent`,`category_product_lang`,`category_product_level`,`category_product_explain`,`category_product_icon`,`category_product_pic`) VALUES 
 (1,'محصولات',0,1,1,NULL,NULL,NULL),
 (40,'کافی شاپ',1,1,2,NULL,'template/images/icons/cafee.jpg',NULL),
 (42,'سوخاری',1,1,2,NULL,'template/images/icons/kentaki.jpg',NULL),
 (44,'پیتزا',1,1,2,NULL,'template/images/icons/pizza.jpg',NULL),
 (45,'ساندویچ',1,1,2,NULL,'template/images/icons/sandwich.jpg',NULL);
/*!40000 ALTER TABLE `category_product` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`click_tracker`
--

DROP TABLE IF EXISTS `click_tracker`;
CREATE TABLE `click_tracker` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `click_tracker_link` varchar(500) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `click_tracker_visitor` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `click_tracker_clickCount` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`click_tracker`
--

/*!40000 ALTER TABLE `click_tracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `click_tracker` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text,
  `comment_full_name` varchar(100) DEFAULT NULL,
  `comment_email` varchar(100) DEFAULT NULL,
  `comment_date` int(11) DEFAULT NULL,
  `comment_tell` varchar(30) DEFAULT NULL,
  `comment_title` varchar(255) DEFAULT NULL,
  `comment_answer` text,
  `comment_char1` varchar(255) DEFAULT NULL,
  `comment_char2` varchar(255) DEFAULT NULL,
  `comment_char3` varchar(255) DEFAULT NULL,
  `comment_int1` int(10) unsigned DEFAULT NULL,
  `comment_int2` int(10) unsigned DEFAULT NULL,
  `comment_int3` int(10) unsigned DEFAULT NULL,
  `comment_refrenceId` varchar(200) DEFAULT NULL COMMENT 'نام جدول و آی دی رکوردی که کامنت برای آن ثبت شده است',
  `comment_publish` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`comment`
--

/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (1,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (2,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (3,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (4,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (5,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (6,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (7,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (8,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (9,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (10,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (11,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (12,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (13,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (14,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (15,'<p style=\'direction:ltr\'>java.lang.NullPointerException</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (16,'<p style=\'direction:ltr\'>java.lang.NullPointerException</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (17,'<p style=\'direction:ltr\'>java.lang.NullPointerException</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (18,'<p style=\'direction:ltr\'>java.lang.NullPointerException</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991214,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (19,'<p style=\'direction:ltr\'>cms.cms.Category_Product.getOneLevelListSweperSlider > line:617</p><p style=\'direction:ltr\'>java.lang.RuntimeException: Uncompilable source code - Erroneous tree type: <any></p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',13991215,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (20,'<p style=\'direction:ltr\'>cms.cms.Product.getDetailsProduct > line:7116</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000108,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (21,'<p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3993</p><p style=\'direction:ltr\'>java.lang.RuntimeException: Uncompilable source code - Erroneous tree type: <any></p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (22,'<p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3989</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (23,'<p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3989</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 0, Size: 0</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (24,'<p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3991</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 1, Size: 1</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
INSERT INTO `comment` (`id`,`comment_text`,`comment_full_name`,`comment_email`,`comment_date`,`comment_tell`,`comment_title`,`comment_answer`,`comment_char1`,`comment_char2`,`comment_char3`,`comment_int1`,`comment_int2`,`comment_int3`,`comment_refrenceId`,`comment_publish`) VALUES 
 (25,'<p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3991</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 1, Size: 1</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (26,'<p style=\'direction:ltr\'>cms.cms.Product.getPriceProduct > line:6837</p><p style=\'direction:ltr\'>cms.cms.Product.preShoppingCartSite > line:3997</p><p style=\'direction:ltr\'>java.lang.IndexOutOfBoundsException: Index: 1, Size: 1</p>','Ø³ÛŒØ³ØªÙ…','mrsalesi@gmail.com',14000113,'03112683807','Ù…Ø´Ú©Ù„ÛŒ Ø¯Ø± Ø³ÛŒØ³ØªÙ…','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`comment_activity`
--

DROP TABLE IF EXISTS `comment_activity`;
CREATE TABLE `comment_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text,
  `comment_full_name` varchar(100) DEFAULT NULL,
  `comment_email` varchar(100) DEFAULT NULL,
  `comment_date` int(11) DEFAULT NULL,
  `comment_tell` varchar(30) DEFAULT NULL,
  `comment_title` varchar(255) DEFAULT NULL,
  `comment_answer` text,
  `comment_char1` varchar(255) DEFAULT NULL,
  `comment_char2` varchar(255) DEFAULT NULL,
  `comment_char3` varchar(255) DEFAULT NULL,
  `comment_int1` int(10) unsigned DEFAULT NULL,
  `comment_int2` int(10) unsigned DEFAULT NULL,
  `comment_int3` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`comment_activity`
--

/*!40000 ALTER TABLE `comment_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_activity` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_title` varchar(600) CHARACTER SET utf8 DEFAULT NULL,
  `content_content` longtext CHARACTER SET utf8,
  `content_contentWithWikiLinks` longtext,
  `content_parent` int(10) unsigned DEFAULT NULL,
  `content_lang` int(10) unsigned DEFAULT NULL,
  `content_privateGroupId` varchar(500) DEFAULT NULL,
  `content_privateUserId` varchar(500) DEFAULT NULL,
  `content_has_link` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `content_hasInContentAutoWiki` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `content_tags` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `content_titleTag` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `content_description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `content_headerTags` longtext CHARACTER SET utf8,
  `content_style` longtext CHARACTER SET utf8,
  `content_script` longtext CHARACTER SET utf8,
  `content_isAjax` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `content_onclick` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `content_link` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `content_date` int(10) unsigned NOT NULL DEFAULT '10000000',
  `content_priority` int(10) unsigned NOT NULL DEFAULT '1',
  `content_category_id` int(10) unsigned NOT NULL DEFAULT '0',
  `content_pic` varchar(45) DEFAULT NULL,
  `content_visit` int(11) NOT NULL DEFAULT '0',
  `content_likes` int(11) NOT NULL DEFAULT '0',
  `content_disLikes` int(11) NOT NULL DEFAULT '0',
  `content_userCommentsIsActive` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `content_target` varchar(45) NOT NULL DEFAULT '_blank',
  `content_isEditableOnlyByOwner` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `content_ownerId` int(10) unsigned NOT NULL DEFAULT '0',
  `content_autoWikIsUpdate` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `content_explain` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`content`
--

/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (12,'وجوهی از لیبرالیسم در گفتمان انقلاب وجود دارد','','<html> <head></head>  <body>   </body></html>',0,1,'0','6',1,1,'null','وجوهی از لیبرالیسم در گفتمان انقلاب وجود دارد','','','','',0,NULL,NULL,90000000,1,4,'',19,0,0,0,'',0,6,0,''),
 (14,'چرا پارادوکس؟','','<html> <head></head>  <body>   </body></html>',0,1,'0','6',1,1,'null','چرا پارادوکس؟','','','','',0,NULL,NULL,90000000,1,4,'',16,0,0,0,'',0,6,0,''),
 (16,'چقدر آگاهانه در لحظه ساخت برنامه به این مسائل فکر میکنی','','<html> <head></head>  <body>   </body></html>',0,1,'0','0',1,1,'null','چقدر آگاهانه در لحظه ساخت برنامه به این مسائل فکر میکنی','','','','',0,NULL,NULL,90000000,1,4,'',17,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (80,'جیوگی در کجای این منظومه قرار میگیرد','','<html> <head></head>  <body>   </body></html>',0,1,'0','0',1,1,'null','جیوگی در کجای این منظومه قرار میگیرد','','','','',0,NULL,NULL,90000000,1,4,'',2,0,0,0,'',0,6,0,''),
 (81,'صفحه اصلی','<section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">لینک سایت قدیم&nbsp; ف<br></h2>         <p>کاربرانی که با سامانه ی قبل وارد میشدند تا اطلاع ثانوی از این لینک برای مشاهده ی دروس خریداری شده استفاده کنند&nbsp;</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"/old/\">لینک سایت قدیم</a></span>        </div>       </div>      </div>     </div>    </div>   </section><section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">دوره‌های جاری</h2>         <p>برای مشاهده دوره‌های جاری ابتدا باید در قسمت <a href=\"product.jsp?category=42\" target=\"_blank\">خرید دوره ها</a>&nbsp;<span style=\"font-weight: bold;\">دوره را خریداری کنید</span>،<br><span style=\"color: rgb(255, 0, 0);\">&nbsp;سپس کمی صبوری کنید</span> و کارشناسان لینک کلاس آنلاین و دسترسی را در اختیار شما قرار میدهند.</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"product.jsp?category=42\">خرید دوره های جاری</a></span>        </div>       </div>      </div>     </div>    </div>   </section><section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">دوره‌های برگزار شده</h2>         <p>برای مشاهده دوره‌های جاری ابتدا باید خریداری کنید، سپس دسترسی شما ایجاد میشود.</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"product.jsp?category=0\">خرید دوره های برگزار شده</a></span>        </div>       </div>      </div>     </div>    </div>   </section>','<html> <head></head>  <body>   <section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">لینک سایت قدیم ف<br></h2>         <p>کاربرانی که با سامانه ی قبل وارد میشدند تا اطلاع ثانوی از این لینک برای مشاهده ی دروس خریداری شده استفاده کنند </p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"/old/\">لینک سایت قدیم</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">دورههای جاری</h2>         <p>برای مشاهده دورههای جاری ابتدا باید در قسمت <a href=\"product.jsp?category=42\" target=\"_blank\">خرید دوره ها</a> <span style=\"font-weight: bold;\">دوره را خریداری کنید</span>،<br><span style=\"color: rgb(255, 0, 0);\"> سپس کمی صبوری کنید</span> و کارشناسان لینک کلاس آنلاین و دسترسی را در اختیار شما قرار میدهند.</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"product.jsp?category=42\">خرید دوره های جاری</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"bg-pngimage--3 mt-1\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"\" style=\"direction;direction: rtl;text-align: right;\">دورههای برگزار شده</h2>         <p>برای مشاهده دورههای جاری ابتدا باید خریداری کنید، سپس دسترسی شما ایجاد میشود.</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a href=\"product.jsp?category=0\">خرید دوره های برگزار شده</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'','صفحه اصلی | مدرسه ی علوم انسانی جیوگی','','','','',0,NULL,NULL,90000000,1,0,'',3097,0,0,0,'',0,2,1,''),
 (82,'تماس با ما','<div class=\"ht__bradcaump__area\">            <div class=\"ht__bradcaump__container\">                            <div class=\"container\">                    <div class=\"row\">                        <div class=\"col-lg-12\">                            <div class=\"bradcaump__inner text-center\">                                <h2 class=\"bradcaump-title\">تماس با ما</h2>                                <nav class=\"bradcaump-inner\">                                  <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a>                                  <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span>                                  <span class=\"breadcrumb-item active\">تماس با ما</span>                                </nav>                            </div>                        </div>                    </div>                </div>            </div>        </div>        <!-- End Bradcaump area -->  <section class=\"dcare__choose__us__area section-padding--lg bg--white\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"section__title text-center\"><h2 class=\"title__line\">درباره جیوگی</h2><p class=\"text-justify pull-right\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p></div></div></div>  <br>  <div class=\"section__title text-center\">  <h2 class=\"title__line\" style=\"display:none;\">ویژگی های محیطی کودک شاد</h2>  </div><div class=\"row mt--40\"  style=\"display:none;\"><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12\"><div class=\"dacre__choose__option\"><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__inner\"><h4><a>شرایط محیطی</a></h4><p>کودک شاد فضایی کاملا ایمن و بهداشتی با رعایت کلیه استانداردها برای کودکان فراهم کرده است</p></div><div class=\"choose__icon\"><img src=\"template/images/choose/icon/1.png\" alt=\"choose icon\"></div></div><!-- End Single Choose --><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__inner\"><h4><a>تغذیه</a></h4><p>صبحانه و دو میان وعده سرد و گرم با برنامه ایی که توسط کارشناس تغذیه تهیه شده هر روز با مواد اولیه تازه و ارگانیک تهیه می شود و در سالن غذا خوری سرو می شود</p></div><div class=\"choose__icon\"><img src=\"template/images/choose/icon/2.png\" alt=\"choose icon\"></div></div><!-- End Single Choose --></div></div><!-- End Single Choose Option --><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12 d-block d-lg-block d-md-none\"><div class=\"dacre__choose__option\"><div class=\"choose__big__img\"><img src=\"template/images/choose/big-img/1.png\" alt=\"choose images\"></div></div></div><!-- End Single Choose Option --><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12\"><div class=\"dacre__choose__option text__align--left\"><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__icon\"><img src=\"template/images/choose/icon/4.png\" alt=\"choose icon\"></div><div class=\"choose__inner\"><h4><a>امکانات</a></h4><p>سالن غذا خوری اختصاصی، سالن ورزشی و کتابخانه، اتاق خواب کودک، سرویسهای بهداشتی اختصاصی کودکان می باشد، از آنجایی که محیط مهد کودک به سیستم مدار بسته مجهز می باشد و والدین عزیز در صورت لزوم قادر به مشاهده کودک خود حین انجام فعالیتها خواهند بود</p></div></div><!-- End Single Choose --><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__icon\"><img src=\"template/images/choose/icon/5.png\" alt=\"choose icon\"></div><div class=\"choose__inner\"><h4><a>گزارشات</a></h4><p>با توجه به اهمیت تعامل بین والدین و مربیان مهد کودک هر روز گزارش روزانه از فعالیتهای انجام شده کلاسی برای اطلاع والدین ارائه می شود</p></div></div><!-- End Single Choose --></div></div><!-- End Single Choose Option --></div></div></section><!-- End Choose Us Area --><!-- Start Team Area -->      <section class=\"page__contact bg--white section-padding--lg\">        <div class=\"container\">        <div class=\"row\">        <!-- Start Single Address -->        <div class=\"col-md-6 col-sm-6 col-12 col-lg-4\">        <div class=\"address location\">        <div class=\"ct__icon\">        <i class=\"fa fa-home\"></i>        </div><div class=\"address__inner\"><h2>شماره واتساپ</h2><p>09045051377</p><ul></ul></div>        </div>        </div>        <!-- End Single Address -->        <!-- Start Single Address -->        <div class=\"col-md-6 col-sm-6 col-12 col-lg-4 xs-mt-60\">        <div class=\"address phone\">        <div class=\"ct__icon\">        <i class=\"fa fa-phone\"></i>        </div><div class=\"address__inner\"><h2>شماره تلفن</h2><ul><li><a href=\"tell:09045051377\">09045051377</a></li></ul></div>        </div>        </div>        <!-- End Single Address -->        <!-- Start Single Address -->        <div class=\"col-md-6 col-sm-6 col-12 col-lg-4 md-mt-60 sm-mt-60\">        <div class=\"address email\">        <div class=\"ct__icon\">        <i class=\"fa fa-envelope\"></i>        </div><div class=\"address__inner\"><h2>آدرس ایمیل</h2><ul><li><a href=\"#\">jivegi.school@gmail.com</a></li></ul></div>        </div>        </div>        <!-- End Single Address -->        </div>        </div>        </section>','<html>  <head></head>  <body>   <div class=\"ht__bradcaump__area\">    <div class=\"ht__bradcaump__container\">     <div class=\"container\">      <div class=\"row\">       <div class=\"col-lg-12\">        <div class=\"bradcaump__inner text-center\">         <h2 class=\"bradcaump-title\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=تماس با ما\" class=\"p jjLink hoverAutoWiki\">تماس با ما</a></h2>         <nav class=\"bradcaump-inner\"> <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a> <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span> <span class=\"breadcrumb-item active\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=تماس با ما\" class=\"p jjLink hoverAutoWiki\">تماس با ما</a></span>         </nav>        </div>       </div>      </div>     </div>    </div>   </div> <!-- End Bradcaump area -->   <section class=\"dcare__choose__us__area section-padding--lg bg--white\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"section__title text-center\">        <h2 class=\"title__line\">درباره جیوگی</h2>        <p class=\"text-justify pull-right\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>       </div>      </div>     </div>     <br>     <div class=\"section__title text-center\">      <h2 class=\"title__line\" style=\"display:none;\">ویژگی های محیطی کودک شاد</h2>     </div>     <div class=\"row mt--40\" style=\"display:none;\"> <!-- Start Single Choose Option -->      <div class=\"col-lg-4 col-md-6 col-sm-12\">       <div class=\"dacre__choose__option\"> <!-- Start Single Choose -->        <div class=\"choose\">         <div class=\"choose__inner\">          <h4><a>شرایط محیطی</a></h4>          <p>کودک شاد فضایی کاملا ایمن و بهداشتی با رعایت کلیه استانداردها برای کودکان فراهم کرده است</p>         </div>         <div class=\"choose__icon\">          <img src=\"template/images/choose/icon/1.png\" alt=\"choose icon\">         </div>        </div><!-- End Single Choose --><!-- Start Single Choose -->        <div class=\"choose\">         <div class=\"choose__inner\">          <h4><a>تغذیه</a></h4>          <p>صبحانه و دو میان وعده سرد و گرم با برنامه ایی که توسط کارشناس تغذیه تهیه شده هر روز با مواد اولیه تازه و ارگانیک تهیه می شود و در سالن غذا خوری سرو می شود</p>         </div>         <div class=\"choose__icon\">          <img src=\"template/images/choose/icon/2.png\" alt=\"choose icon\">         </div>        </div><!-- End Single Choose -->       </div>      </div><!-- End Single Choose Option --><!-- Start Single Choose Option -->      <div class=\"col-lg-4 col-md-6 col-sm-12 d-block d-lg-block d-md-none\">       <div class=\"dacre__choose__option\">        <div class=\"choose__big__img\">         <img src=\"template/images/choose/big-img/1.png\" alt=\"choose images\">        </div>       </div>      </div><!-- End Single Choose Option --><!-- Start Single Choose Option -->      <div class=\"col-lg-4 col-md-6 col-sm-12\">       <div class=\"dacre__choose__option text__align--left\"> <!-- Start Single Choose -->        <div class=\"choose\">         <div class=\"choose__icon\">          <img src=\"template/images/choose/icon/4.png\" alt=\"choose icon\">         </div>         <div class=\"choose__inner\">          <h4><a>امکانات</a></h4>          <p>سالن غذا خوری اختصاصی، سالن ورزشی و کتابخانه، اتاق خواب کودک، سرویسهای بهداشتی اختصاصی کودکان می باشد، از آنجایی که محیط مهد کودک به سیستم مدار بسته مجهز می باشد و والدین عزیز در صورت لزوم قادر به مشاهده کودک خود حین انجام فعالیتها خواهند بود</p>         </div>        </div><!-- End Single Choose --><!-- Start Single Choose -->        <div class=\"choose\">         <div class=\"choose__icon\">          <img src=\"template/images/choose/icon/5.png\" alt=\"choose icon\">         </div>         <div class=\"choose__inner\">          <h4><a>گزارشات</a></h4>          <p>با توجه به اهمیت تعامل بین والدین و مربیان مهد کودک هر روز گزارش روزانه از فعالیتهای انجام شده کلاسی برای اطلاع والدین ارائه می شود</p>         </div>        </div><!-- End Single Choose -->       </div>      </div><!-- End Single Choose Option -->     </div>    </div>   </section><!-- End Choose Us Area --><!-- Start Team Area -->   <section class=\"page__contact bg--white section-padding--lg\">    <div class=\"container\">     <div class=\"row\"> <!-- Start Single Address -->      <div class=\"col-md-6 col-sm-6 col-12 col-lg-4\">       <div class=\"address location\">        <div class=\"ct__icon\"> <i class=\"fa fa-home\"></i>        </div>        <div class=\"address__inner\">         <h2>شماره واتساپ</h2>         <p>09045051377</p>         <ul></ul>        </div>       </div>      </div> <!-- End Single Address --> <!-- Start Single Address -->      <div class=\"col-md-6 col-sm-6 col-12 col-lg-4 xs-mt-60\">       <div class=\"address phone\">        <div class=\"ct__icon\"> <i class=\"fa fa-phone\"></i>        </div>        <div class=\"address__inner\">         <h2>شماره تلفن</h2>         <ul>          <li><a href=\"tell:09045051377\">09045051377</a></li>         </ul>        </div>       </div>      </div> <!-- End Single Address --> <!-- Start Single Address -->      <div class=\"col-md-6 col-sm-6 col-12 col-lg-4 md-mt-60 sm-mt-60\">       <div class=\"address email\">        <div class=\"ct__icon\"> <i class=\"fa fa-envelope\"></i>        </div>        <div class=\"address__inner\">         <h2>آدرس ایمیل</h2>         <ul>          <li><a href=\"#\">jivegi.school@gmail.com</a></li>         </ul>        </div>       </div>      </div> <!-- End Single Address -->     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','تماس با ما','','','','',0,NULL,NULL,90000000,1,0,'',40,0,0,0,'',0,6,1,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (83,'درباره ما','<div class=\"ht__bradcaump__area\">      <div class=\"ht__bradcaump__container\">                            <div class=\"container\">                    <div class=\"row\">                        <div class=\"col-lg-12\">                            <div class=\"bradcaump__inner text-center\">                                <h2 class=\"bradcaump-title\">درباره ما</h2>                                <nav class=\"bradcaump-inner\">                                  <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a>                                  <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span>                                  <span class=\"breadcrumb-item active\">درباره ما</span>                                </nav>                            </div>                        </div>                    </div>                </div>            </div> <!-- Start Choose Us Area --><section class=\"dcare__choose__us__area section-padding--lg bg--white\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"section__title text-center\"><h2 class=\"title__line\">درباره جیوگی</h2><p class=\"text-justify pull-right\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p></div></div></div>  <br>  <div class=\"section__title text-center\">  <h2 class=\"title__line\" style=\"display:none;\">ویژگی های محیطی کودک شاد</h2>  </div><div class=\"row mt--40\"  style=\"display:none;\"><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12\"><div class=\"dacre__choose__option\"><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__inner\"><h4><a>شرایط محیطی</a></h4><p>کودک شاد فضایی کاملا ایمن و بهداشتی با رعایت کلیه استانداردها برای کودکان فراهم کرده است</p></div><div class=\"choose__icon\"><img src=\"template/images/choose/icon/1.png\" alt=\"choose icon\"></div></div><!-- End Single Choose --><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__inner\"><h4><a>تغذیه</a></h4><p>صبحانه و دو میان وعده سرد و گرم با برنامه ایی که توسط کارشناس تغذیه تهیه شده هر روز با مواد اولیه تازه و ارگانیک تهیه می شود و در سالن غذا خوری سرو می شود</p></div><div class=\"choose__icon\"><img src=\"template/images/choose/icon/2.png\" alt=\"choose icon\"></div></div><!-- End Single Choose --></div></div><!-- End Single Choose Option --><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12 d-block d-lg-block d-md-none\"><div class=\"dacre__choose__option\"><div class=\"choose__big__img\"><img src=\"template/images/choose/big-img/1.png\" alt=\"choose images\"></div></div></div><!-- End Single Choose Option --><!-- Start Single Choose Option --><div class=\"col-lg-4 col-md-6 col-sm-12\"><div class=\"dacre__choose__option text__align--left\"><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__icon\"><img src=\"template/images/choose/icon/4.png\" alt=\"choose icon\"></div><div class=\"choose__inner\"><h4><a>امکانات</a></h4><p>سالن غذا خوری اختصاصی، سالن ورزشی و کتابخانه، اتاق خواب کودک، سرویسهای بهداشتی اختصاصی کودکان می باشد، از آنجایی که محیط مهد کودک به سیستم مدار بسته مجهز می باشد و والدین عزیز در صورت لزوم قادر به مشاهده کودک خود حین انجام فعالیتها خواهند بود</p></div></div><!-- End Single Choose --><!-- Start Single Choose --><div class=\"choose\"><div class=\"choose__icon\"><img src=\"template/images/choose/icon/5.png\" alt=\"choose icon\"></div><div class=\"choose__inner\"><h4><a>گزارشات</a></h4><p>با توجه به اهمیت تعامل بین والدین و مربیان مهد کودک هر روز گزارش روزانه از فعالیتهای انجام شده کلاسی برای اطلاع والدین ارائه می شود</p></div></div><!-- End Single Choose --></div></div><!-- End Single Choose Option --></div></div></section><!-- End Choose Us Area --><!-- Start Team Area --><section class=\"dcare__team__area pb--150 bg--white\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"section__title text-center\"><h2 class=\"title__line\">ارتباط</h2><p  style=\"display:none;\">حضور کادر متخصص در هر زمینه آموزشی از جمله مشاور و روانشانس کودک مشاور آموزشی و برگزاری کارگاههای تخصصی آموزشی برای مربیان و پرسنل مهد گودک و برگزاری کلاسهای فرزند پروری برای والدین</p></div></div></div><div class=\"row mt--40\"><!-- Start Single Team --><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"team\"><div class=\"team__thumb\"><a href=\"#\"><img src=\"template/images/team/1.jpg\" alt=\"team images\"></a>  <div class=\"team__hover__action\"><div class=\"team__hover__inner\"></div></div></div><div class=\"team__details\"><div class=\"team__info\"><h6><a target=\"_blank\" href=\"\">اینستاگرام</a></h6></div></div></div></div><!-- End Single Team --><!-- Start Single Team --><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"team\"><div class=\"team__thumb\"><a href=\"#\"><img src=\"template/images/team/2.jpg\" alt=\"team images\"></a><div class=\"team__hover__action\"><div class=\"team__hover__inner\"></div></div></div><div class=\"team__details\"><div class=\"team__info\"><h6><a href=\"#\">تلگرام</a></h6></div></div></div></div><!-- End Single Team --><!-- Start Single Team --><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"team\"><div class=\"team__thumb\"><a href=\"#\"><img src=\"template/images/team/3.jpg\" alt=\"team images\"></a><div class=\"team__hover__action\"><div class=\"team__hover__inner\"></div></div></div><div class=\"team__details\"><div class=\"team__info\"><h6><a href=\"\">تلفن</a></h6></div></div></div></div><!-- End Single Team --></div></div></section><!-- End Team Area --><!-- Start Counter Up Area --><section class=\"dcare__counterup__area section-padding--lg bg-image--6\"><div class=\"container\"><div class=\"row\"><div class=\"col-md-12 col-lg-12 col-sm-12\"><div class=\"counterup__wrapper d-flex flex-wrap flex-lg-nowrap flex-md-nowrap justify-content-between\"><!-- Start Single Fact -->                            <div class=\"funfact\">                                <div class=\"fact__icon\">                                    <img src=\"template/images/funfact/1.png\" alt=\"flat icon\">                                </div>                                <div class=\"fact__count \">                                    <span class=\"count\">17</span>                                </div>                                <div class=\"fact__title\">                                    <h2>تعداد کودک</h2>                                </div>                            </div>                             <!-- End Single Fact --><!-- Start Single Fact -->                            <div class=\"funfact\">                                <div class=\"fact__icon\">                                    <img src=\"template/images/funfact/2.png\" alt=\"flat icon\">                                </div>                                <div class=\"fact__count \">                                    <span class=\"count color--2\">4</span>                                </div>                                <div class=\"fact__title\">                                    <h2>مربی</h2>                                </div>                            </div>                             <!-- End Single Fact --><!-- Start Single Fact -->                            <div class=\"funfact\">                                <div class=\"fact__icon\">                                    <img src=\"template/images/funfact/3.png\" alt=\"flat icon\">                                </div>                                <div class=\"fact__count \">                                    <span class=\"count color--3\">3</span>                                </div>                                <div class=\"fact__title\">                                    <h2>کلاس</h2>                                </div>                            </div>                             <!-- End Single Fact --><!-- Start Single Fact -->                            <div class=\"funfact\">                                <div class=\"fact__icon\">                                    <img src=\"template/images/funfact/2.png\" alt=\"flat icon\">                                </div>                                <div class=\"fact__count\">                                    <span class=\"count color--4\">5</span>                                </div>                                <div class=\"fact__title\">                                    <h2>مربی فوق برنامه</h2>                                </div>                            </div>                             <!-- End Single Fact --></div></div></div></div></section>','<html> <head></head>  <body>   <div class=\"ht__bradcaump__area\">    <div class=\"ht__bradcaump__container\">     <div class=\"container\">      <div class=\"row\">       <div class=\"col-lg-12\">        <div class=\"bradcaump__inner text-center\">         <h2 class=\"bradcaump-title\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=درباره ما\" class=\"p jjLink hoverAutoWiki\">درباره ما</a></h2>         <nav class=\"bradcaump-inner\">          <a class=\"breadcrumb-item\" href=\"index.jsp\">صفحه اصلی</a>          <span class=\"brd-separetor\"><img src=\"template/images/icons/brad.png\" alt=\"separator images\"></span>          <span class=\"breadcrumb-item active\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=درباره ما\" class=\"p jjLink hoverAutoWiki\">درباره ما</a></span>         </nav>        </div>       </div>      </div>     </div>    </div>    <!-- Start Choose Us Area -->    <section class=\"dcare__choose__us__area section-padding--lg bg--white\">     <div class=\"container\">      <div class=\"row\">       <div class=\"col-lg-12 col-sm-12 col-md-12\">        <div class=\"section__title text-center\">         <h2 class=\"title__line\">درباره جیوگی</h2>         <p class=\"text-justify pull-right\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        </div>       </div>      </div>      <br>      <div class=\"section__title text-center\">       <h2 class=\"title__line\" style=\"display:none;\">ویژگی های محیطی کودک شاد</h2>      </div>      <div class=\"row mt--40\" style=\"display:none;\">       <!-- Start Single Choose Option -->       <div class=\"col-lg-4 col-md-6 col-sm-12\">        <div class=\"dacre__choose__option\">         <!-- Start Single Choose -->         <div class=\"choose\">          <div class=\"choose__inner\">           <h4><a>شرایط محیطی</a></h4>           <p>کودک شاد فضایی کاملا ایمن و بهداشتی با رعایت کلیه استانداردها برای کودکان فراهم کرده است</p>          </div>          <div class=\"choose__icon\">           <img src=\"template/images/choose/icon/1.png\" alt=\"choose icon\">          </div>         </div>         <!-- End Single Choose -->         <!-- Start Single Choose -->         <div class=\"choose\">          <div class=\"choose__inner\">           <h4><a>تغذیه</a></h4>           <p>صبحانه و دو میان وعده سرد و گرم با برنامه ایی که توسط کارشناس تغذیه تهیه شده هر روز با مواد اولیه تازه و ارگانیک تهیه می شود و در سالن غذا خوری سرو می شود</p>          </div>          <div class=\"choose__icon\">           <img src=\"template/images/choose/icon/2.png\" alt=\"choose icon\">          </div>         </div>         <!-- End Single Choose -->        </div>       </div>       <!-- End Single Choose Option -->       <!-- Start Single Choose Option -->       <div class=\"col-lg-4 col-md-6 col-sm-12 d-block d-lg-block d-md-none\">        <div class=\"dacre__choose__option\">         <div class=\"choose__big__img\">          <img src=\"template/images/choose/big-img/1.png\" alt=\"choose images\">         </div>        </div>       </div>       <!-- End Single Choose Option -->       <!-- Start Single Choose Option -->       <div class=\"col-lg-4 col-md-6 col-sm-12\">        <div class=\"dacre__choose__option text__align--left\">         <!-- Start Single Choose -->         <div class=\"choose\">          <div class=\"choose__icon\">           <img src=\"template/images/choose/icon/4.png\" alt=\"choose icon\">          </div>          <div class=\"choose__inner\">           <h4><a>امکانات</a></h4>           <p>سالن غذا خوری اختصاصی، سالن ورزشی و کتابخانه، اتاق خواب کودک، سرویسهای بهداشتی اختصاصی کودکان می باشد، از آنجایی که محیط مهد کودک به سیستم مدار بسته مجهز می باشد و والدین عزیز در صورت لزوم قادر به مشاهده کودک خود حین انجام فعالیتها خواهند بود</p>          </div>         </div>         <!-- End Single Choose -->         <!-- Start Single Choose -->         <div class=\"choose\">          <div class=\"choose__icon\">           <img src=\"template/images/choose/icon/5.png\" alt=\"choose icon\">          </div>          <div class=\"choose__inner\">           <h4><a>گزارشات</a></h4>           <p>با توجه به اهمیت تعامل بین والدین و مربیان مهد کودک هر روز گزارش روزانه از فعالیتهای انجام شده کلاسی برای اطلاع والدین ارائه می شود</p>          </div>         </div>         <!-- End Single Choose -->        </div>       </div>       <!-- End Single Choose Option -->      </div>     </div>    </section>    <!-- End Choose Us Area -->    <!-- Start Team Area -->    <section class=\"dcare__team__area pb--150 bg--white\">     <div class=\"container\">      <div class=\"row\">       <div class=\"col-lg-12 col-sm-12 col-md-12\">        <div class=\"section__title text-center\">         <h2 class=\"title__line\">ارتباط</h2>         <p style=\"display:none;\">حضور کادر متخصص در هر زمینه آموزشی از جمله مشاور و روانشانس کودک مشاور آموزشی و برگزاری کارگاههای تخصصی آموزشی برای مربیان و پرسنل مهد گودک و برگزاری کلاسهای فرزند پروری برای والدین</p>        </div>       </div>      </div>      <div class=\"row mt--40\">       <!-- Start Single Team -->       <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">        <div class=\"team\">         <div class=\"team__thumb\">          <a href=\"#\"><img src=\"template/images/team/1.jpg\" alt=\"team images\"></a>          <div class=\"team__hover__action\">           <div class=\"team__hover__inner\"></div>          </div>         </div>         <div class=\"team__details\">          <div class=\"team__info\">           <h6><a target=\"_blank\" href=\"\">اینستاگرام</a></h6>          </div>         </div>        </div>       </div>       <!-- End Single Team -->       <!-- Start Single Team -->       <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">        <div class=\"team\">         <div class=\"team__thumb\">          <a href=\"#\"><img src=\"template/images/team/2.jpg\" alt=\"team images\"></a>          <div class=\"team__hover__action\">           <div class=\"team__hover__inner\"></div>          </div>         </div>         <div class=\"team__details\">          <div class=\"team__info\">           <h6><a href=\"#\">تلگرام</a></h6>          </div>         </div>        </div>       </div>       <!-- End Single Team -->       <!-- Start Single Team -->       <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">        <div class=\"team\">         <div class=\"team__thumb\">          <a href=\"#\"><img src=\"template/images/team/3.jpg\" alt=\"team images\"></a>          <div class=\"team__hover__action\">           <div class=\"team__hover__inner\"></div>          </div>         </div>         <div class=\"team__details\">          <div class=\"team__info\">           <h6><a href=\"\">تلفن</a></h6>          </div>         </div>        </div>       </div>       <!-- End Single Team -->      </div>     </div>    </section>    <!-- End Team Area -->    <!-- Start Counter Up Area -->    <section class=\"dcare__counterup__area section-padding--lg bg-image--6\">     <div class=\"container\">      <div class=\"row\">       <div class=\"col-md-12 col-lg-12 col-sm-12\">        <div class=\"counterup__wrapper d-flex flex-wrap flex-lg-nowrap flex-md-nowrap justify-content-between\">         <!-- Start Single Fact -->         <div class=\"funfact\">          <div class=\"fact__icon\">           <img src=\"template/images/funfact/1.png\" alt=\"flat icon\">          </div>          <div class=\"fact__count \">           <span class=\"count\">17</span>          </div>          <div class=\"fact__title\">           <h2>تعداد کودک</h2>          </div>         </div>         <!-- End Single Fact -->         <!-- Start Single Fact -->         <div class=\"funfact\">          <div class=\"fact__icon\">           <img src=\"template/images/funfact/2.png\" alt=\"flat icon\">          </div>          <div class=\"fact__count \">           <span class=\"count color--2\">4</span>          </div>          <div class=\"fact__title\">           <h2>مربی</h2>          </div>         </div>         <!-- End Single Fact -->         <!-- Start Single Fact -->         <div class=\"funfact\">          <div class=\"fact__icon\">           <img src=\"template/images/funfact/3.png\" alt=\"flat icon\">          </div>          <div class=\"fact__count \">           <span class=\"count color--3\">3</span>          </div>          <div class=\"fact__title\">           <h2>کلاس</h2>          </div>         </div>         <!-- End Single Fact -->         <!-- Start Single Fact -->         <div class=\"funfact\">          <div class=\"fact__icon\">           <img src=\"template/images/funfact/2.png\" alt=\"flat icon\">          </div>          <div class=\"fact__count\">           <span class=\"count color--4\">5</span>          </div>          <div class=\"fact__title\">           <h2>مربی فوق برنامه</h2>          </div>         </div>         <!-- End Single Fact -->        </div>       </div>      </div>     </div>    </section>   </div>   </body></html>',0,1,'0','0',1,1,'null','درباره ما','','','','',0,NULL,NULL,90000000,1,0,'',35,0,0,0,'',0,6,0,''),
 (84,'جلسه ی اول هایدگر','<div class=\"card bd-primary\">              <div class=\"card-header bg-primary tx-white\">جلسه ی اول</div>              <div class=\"card-body pd-sm-30\">                <p class=\"mg-b-20 mg-sm-b-30\">فایل صوتی جلسه ی اول هایدگر </p>                <div class=\"card bd-0 wd-xs-300 col-lg-4 col-sm-12 col-md-4\">                  <img class=\"card-img-top img-fluid\" src=\"upload/p9413164924.jpg\" alt=\"هایدگر\" style=\"width: 100%;\">                  <div class=\"card-body bd bd-t-0\">                                        <span class=\"tx-12\">1399/07/21</span>                  </div>               <audio class=\"clo-12\" controls=\"nodownload\"> <source src=\"http://jivegi.school/audio/01_01.mp3\" type=\"audio/mpeg\"></audio> </div>              </div><!-- card-body -->            </div>','<html> <head></head>  <body>   <div class=\"card bd-primary\">    <div class=\"card-header bg-primary tx-white\">     جلسه ی اول    </div>    <div class=\"card-body pd-sm-30\">     <p class=\"mg-b-20 mg-sm-b-30\">فایل صوتی <a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه ی اول هایدگر\" class=\"p jjLink hoverAutoWiki\">جلسه ی اول هایدگر</a> </p>     <div class=\"card bd-0 wd-xs-300 col-lg-4 col-sm-12 col-md-4\">      <img class=\"card-img-top img-fluid\" src=\"upload/p9413164924.jpg\" alt=\"هایدگر\" style=\"width: 100%;\">      <div class=\"card-body bd bd-t-0\">       <span class=\"tx-12\">1399/07/21</span>      </div>      <audio class=\"clo-12\" controls=\"nodownload\">       <source src=\"http://jivegi.school/audio/01_01.mp3\" type=\"audio/mpeg\">      </audio>     </div>    </div>    <!-- card-body -->   </div>   </body></html>',0,1,'6','14',1,1,'null','جلسه ی اول هایدگر','','','','',0,NULL,NULL,90000000,1,5,'',68,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (113,'قالبمشاهده دوره ها','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>دوره ها</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" >خرید</span> </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\'Server?do=Content.sw&amp;panel=sw&amp;text=دوره ها\' class=\'p jjLink hoverAutoWiki\'>دوره ها</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\">خرید</span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','قالبمشاهده دوره ها','','','','',0,NULL,NULL,90000000,1,0,'',32,0,0,0,'',0,6,0,''),
 (119,'جلسه دوم هگل','','<html> <head></head>  <body>   </body></html>',0,1,'0','0',1,1,'null','جلسه دوم هگل','کارگاه قصه های رنگی مهد کودک دو زبانه کودک شاد','','','',0,NULL,NULL,90000000,1,5,'',11,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (124,'جلسه سوم هگل','','<html> <head></head>  <body>   </body></html>',0,1,'0','0',1,1,'null','جلسه سوم هگل','کارگاه من هنرمندم مهد کودک دو زبانه کودک شاد','','','',0,NULL,NULL,90000000,1,5,'',10,0,0,0,'',0,6,0,''),
 (125,'template1','<section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">چرا جیوگی؟</h2><p> به کارگیری روش های آموزشی و ترکیبی ما از ابزار هایی است که بر أساس نیازهای جامعه  می باشد که سبب افزایش سرعت و کیفیت یادگیری جوانان میشود</p></div><div class=\"callto__action__btn\"><span class=\"dcare__btn btn__white\" ><a>تماس با ما</a></span></div></div></div></div></div></section><section class=\"junior__service bg-image--1 section-padding--bottom section--padding--xlg--top\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-3 col-md-6 col-sm-6 col-12\"><div class=\"service bg--white border__color wow fadeInUp\"><div class=\"service__icon\"><img src=\"template/images/shape/sm-icon/1.png\" alt=\"icon images\"></div><div class=\"service__details\"><h6> چرا پارادوکس؟</h6><p>گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن</p>  </div></div></div><div class=\"col-lg-3 col-md-6 col-sm-6 col-12 xs-mt-60\"><div class=\"service bg--white border__color border__color--2 wow fadeInUp\" data-wow-delay=\"0.2s\"><div class=\"service__icon\"><img src=\"template/images/shape/sm-icon/2.png\" alt=\"icon images\"></div><div class=\"service__details\"><h6>وجوهی از لیبرالیسم در گفتمان انقلاب وجود دارد</h6><p>این بحث بشدت حساسی است و نباید باعث سوءتفاهم شود. اگر منظومه‌ای را که به کشف تکنیک‌ها برای حکمرانی مدرن می‌پردازد لیبرالیسم بنامیم آن وقت نمی‌توان به حکمرانی مدرن و ساخت یک دولت-ملت مقتدر مرکزی ایرانی قایل بود و به قواعد حکمرانی مدرن تن نداد. البته این را بگویم</p></div></div></div><div class=\"col-lg-3 col-md-6 col-sm-6 col-12 md-mt-60 sm-mt-60\"><div class=\"service bg--white border__color border__color--3 wow fadeInUp\" data-wow-delay=\"0.45s\"><div class=\"service__icon\"><img src=\"template/images/shape/sm-icon/3.png\" alt=\"icon images\"></div><div class=\"service__details\"><h6>چقدر آگاهانه در لحظه ساخت برنامه به این مسائل فکر می‌کنی</h6><p>حقیقتش را بخواهی به این مسائل موقع ساخت برنامه فکر نمی‌کنم ولی وقتی به عقب نگاه می‌کنم می‌توانم چنین منظومه‌ای را درک کنم. وقتی نسل، بحران، زن و تاریخ، بومی و جهانی یا محیط‌زیست مساله‌ام می‌شود یعنی به پروژه ملت‌سازی فکر می‌کنم. اینکه چقدر ما آماده‌ایم</p>  </div></div></div><div class=\"col-lg-3 col-md-6 col-sm-6 col-12 md-mt-60 sm-mt-60\"><div class=\"service bg--white border__color border__color--4 wow fadeInUp\" data-wow-delay=\"0.65s\"><div class=\"service__icon\"><img src=\"template/images/shape/sm-icon/4.png\" alt=\"icon images\"></div><div class=\"service__details\"><h6>جیوگی در کجای این منظومه قرار می‌گیرد</h6><p>من در برنامه دلینک در پرس‌تی‌وی رویکرد پسا استعمار داشتم و در مجموع مساله‌ام نقد وجوه فرهنگی غرب بود. در برنامه گره تحت تاثیر آوینی به رویکرد آنارشیستی از انقلاب یعنی نگاه جایگزینی گرایش پیدا کردم و در جیوگی مساله‌ام تقویت  فرهنگی برای ساخت ملت قوی ایرانی است</p>   </div></div></div></div></div></section><section class=\"page-class-details bg--white \">        <div class=\"container\">        <div class=\"row\">        <div class=\"col-lg-12\">        <div class=\"class__quality__desc\">        <div class=\"class__thumb\">                <div class=\"courses__images\">                </div>                </div><div class=\"class-details-inner\"><div class=\"class__nav nav justify-content-between\" role=\"tablist\">                            <a class=\"nav-item nav-link\" data-toggle=\"tab\" href=\"product.jsp?productId=40\" style=\"cursor: pointer\">دوره های برگزارشده</a>                            <a class=\"nav-item nav-link active\" data-toggle=\"tab\" href=\"product.jsp?productId=42\" style=\"cursor: pointer\">دوره های جاری</a>                                        </div>                        </div>        </div>        </div>                                </div>        </div>        </section><section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section__title text-center\"><h2 class=\"title__line\">به مدرسه ی علوم انسانی جیوگی خوش آمدید</h2><p>جیوه تنها فلز مایع است یعنی هم در آن اقتدار فلز وجود دارد و هم سیالیت فرهنگ</p></div></div></div><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>جیوگی</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" >درباره ما</span> </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section><section class=\"junior__gallery__area bg--white section-padding--lg\" id=\"gallery\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"section__title text-center\"><h2 class=\"title__line\"><a href=\"#\" >گالری</a></h2><p>جیوه تنها فلز مایع است یعنی هم در آن اقتدار فلز وجود دارد و هم سیالیت فرهنگ. و این همان چیزی است که ما الان به آن احتیاج داریم. در وجوه سخت، قدرت با کسی شوخی ندارد و نباید هم داشته باش</p></div></div></div><div class=\"row galler__wrap mt--40\"><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/5.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\"> <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/7.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\"><li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/4.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\"> <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/11.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\">  <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/12.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\"> <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div><div class=\"col-lg-4 col-md-6 col-sm-6 col-12\"><div class=\"gallery wow fadeInUp\"><div class=\"gallery__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/6.jpg\" alt=\"gallery images\"></a></div><div class=\"gallery__hover__inner\"><div class=\"gallery__hover__action\"><ul class=\"gallery__zoom\"> <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li></ul><h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4></div></div></div></div></div></div></section><section class=\"jnr__blog_area section-padding--lg bg-image--3\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"section__title text-center white--title\"><h2 class=\"title__line\">بخش های برنامه</h2></div></div></div><div class=\"row blog__wrapper mt--40\"> <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInLeft\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/8.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"\">کرونا و آینده روان ما</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\"></ul></div></article></div> <!-- End Single Classes --> <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInUp\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/9.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">آغاز کرونا</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\"></ul></div></article></div>  <!-- End Single Classes -->  <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\" ><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/10.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">وسواس و اضطراب</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>    <!-- End Single Classes -->    <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/1.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">قرنطینه هنرمندان</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>        <!-- End Single Classes -->  <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/2.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">الهیات و کرونا</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>      <!-- End Single Classes --><!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/3.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">کرونا از منظر عشق و حقیقت</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>    <!-- End Single Classes -->    <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/13.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">اشتغال زنان ایرانی</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>        <!-- End Single Classes -->  <!-- Start Single Classes --><div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/14.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">جیوگی گپ</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>   <!-- End Single Classes -->   <!-- Start Single Classes -->   <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\"><article class=\"blog\"><div class=\"blog__date\"><span></span></div><div class=\"blog__thumb\"><a href=\"#\"><img src=\"template/images/gallery/gallery-1/15.jpg\" alt=\"blog images\"></a></div><div class=\"blog__inner\"><span></span><h4><a href=\"#\">چند روایت از جای جای ایران</a></h4><ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">   </ul></div></article></div>   <!-- End Single Classes --></div></div></section>','<html> <head></head>  <body>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">چرا جیوگی؟</h2>         <p> به کارگیری روش های آموزشی و ترکیبی ما از ابزار هایی است که بر أساس نیازهای جامعه می باشد که سبب افزایش سرعت و کیفیت یادگیری جوانان میشود</p>        </div>        <div class=\"callto__action__btn\">         <span class=\"dcare__btn btn__white\"><a>تماس با ما</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"junior__service bg-image--1 section-padding--bottom section--padding--xlg--top\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-3 col-md-6 col-sm-6 col-12\">       <div class=\"service bg--white border__color wow fadeInUp\">        <div class=\"service__icon\">         <img src=\"template/images/shape/sm-icon/1.png\" alt=\"icon images\">        </div>        <div class=\"service__details\">         <h6> <a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=چرا پارادوکس؟\" class=\"p jjLink hoverAutoWiki\">چرا پارادوکس؟</a></h6>         <p>گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن</p>        </div>       </div>      </div>      <div class=\"col-lg-3 col-md-6 col-sm-6 col-12 xs-mt-60\">       <div class=\"service bg--white border__color border__color--2 wow fadeInUp\" data-wow-delay=\"0.2s\">        <div class=\"service__icon\">         <img src=\"template/images/shape/sm-icon/2.png\" alt=\"icon images\">        </div>        <div class=\"service__details\">         <h6><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=وجوهی از لیبرالیسم در گفتمان انقلاب وجود دارد\" class=\"p jjLink hoverAutoWiki\">وجوهی از لیبرالیسم در گفتمان انقلاب وجود دارد</a></h6>         <p>این بحث بشدت حساسی است و نباید باعث سوءتفاهم شود. اگر منظومهای را که به کشف تکنیکها برای حکمرانی مدرن میپردازد لیبرالیسم بنامیم آن وقت نمیتوان به حکمرانی مدرن و ساخت یک دولت-ملت مقتدر مرکزی ایرانی قایل بود و به قواعد حکمرانی مدرن تن نداد. البته این را بگویم</p>        </div>       </div>      </div>      <div class=\"col-lg-3 col-md-6 col-sm-6 col-12 md-mt-60 sm-mt-60\">       <div class=\"service bg--white border__color border__color--3 wow fadeInUp\" data-wow-delay=\"0.45s\">        <div class=\"service__icon\">         <img src=\"template/images/shape/sm-icon/3.png\" alt=\"icon images\">        </div>        <div class=\"service__details\">         <h6><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=چقدر آگاهانه در لحظه ساخت برنامه به این مسائل فکر میکنی\" class=\"p jjLink hoverAutoWiki\">چقدر آگاهانه در لحظه ساخت برنامه به این مسائل فکر میکنی</a></h6>         <p>حقیقتش را بخواهی به این مسائل موقع ساخت برنامه فکر نمیکنم ولی وقتی به عقب نگاه میکنم میتوانم چنین منظومهای را درک کنم. وقتی نسل، بحران، زن و تاریخ، بومی و جهانی یا محیطزیست مسالهام میشود یعنی به پروژه ملتسازی فکر میکنم. اینکه چقدر ما آمادهایم</p>        </div>       </div>      </div>      <div class=\"col-lg-3 col-md-6 col-sm-6 col-12 md-mt-60 sm-mt-60\">       <div class=\"service bg--white border__color border__color--4 wow fadeInUp\" data-wow-delay=\"0.65s\">        <div class=\"service__icon\">         <img src=\"template/images/shape/sm-icon/4.png\" alt=\"icon images\">        </div>        <div class=\"service__details\">         <h6><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جیوگی در کجای این منظومه قرار میگیرد\" class=\"p jjLink hoverAutoWiki\">جیوگی در کجای این منظومه قرار میگیرد</a></h6>         <p>من در برنامه دلینک در پرستیوی رویکرد پسا استعمار داشتم و در مجموع مسالهام نقد وجوه فرهنگی غرب بود. در برنامه گره تحت تاثیر آوینی به رویکرد آنارشیستی از انقلاب یعنی نگاه جایگزینی گرایش پیدا کردم و در جیوگی مسالهام تقویت فرهنگی برای ساخت ملت قوی ایرانی است</p>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"page-class-details bg--white \">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12\">       <div class=\"class__quality__desc\">        <div class=\"class__thumb\">         <div class=\"courses__images\">         </div>        </div>        <div class=\"class-details-inner\">         <div class=\"class__nav nav justify-content-between\" role=\"tablist\">          <a class=\"nav-item nav-link\" data-toggle=\"tab\" href=\"product.jsp?productId=40\" style=\"cursor: pointer\">دوره های برگزارشده</a>          <a class=\"nav-item nav-link active\" data-toggle=\"tab\" href=\"product.jsp?productId=42\" style=\"cursor: pointer\">دوره های جاری</a>         </div>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12\">       <div class=\"section__title text-center\">        <h2 class=\"title__line\">به مدرسه ی علوم انسانی جیوگی خوش آمدید</h2>        <p>جیوه تنها فلز مایع است یعنی هم در آن اقتدار فلز وجود دارد و هم سیالیت فرهنگ</p>       </div>      </div>     </div>     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span>جیوگی</h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=درباره ما\" class=\"p jjLink hoverAutoWiki\">درباره ما</a></span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   <section class=\"junior__gallery__area bg--white section-padding--lg\" id=\"gallery\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"section__title text-center\">        <h2 class=\"title__line\"><a href=\"#\">گالری</a></h2>        <p>جیوه تنها فلز مایع است یعنی هم در آن اقتدار فلز وجود دارد و هم سیالیت فرهنگ. و این همان چیزی است که ما الان به آن احتیاج داریم. در وجوه سخت، قدرت با کسی شوخی ندارد و نباید هم داشته باش</p>       </div>      </div>     </div>     <div class=\"row galler__wrap mt--40\">      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/5.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/7.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/4.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/11.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/12.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>      <div class=\"col-lg-4 col-md-6 col-sm-6 col-12\">       <div class=\"gallery wow fadeInUp\">        <div class=\"gallery__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/6.jpg\" alt=\"gallery images\"></a>        </div>        <div class=\"gallery__hover__inner\">         <div class=\"gallery__hover__action\">          <ul class=\"gallery__zoom\">           <li><a href=\"#\"><i class=\"fa fa-camera\"></i></a></li>          </ul>          <h4 class=\"gallery__title\"><a href=\"#\">مدرسه ی علوم انسانی جیوگی</a></h4>         </div>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__blog_area section-padding--lg bg-image--3\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"section__title text-center white--title\">        <h2 class=\"title__line\">بخش های برنامه</h2>       </div>      </div>     </div>     <div class=\"row blog__wrapper mt--40\">      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInLeft\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/8.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"\">کرونا و آینده روان ما</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\"></ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInUp\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/9.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">آغاز کرونا</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\"></ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/10.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">وسواس و اضطراب</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/1.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">قرنطینه هنرمندان</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/2.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">الهیات و کرونا</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/3.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">کرونا از منظر عشق و حقیقت</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/13.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">اشتغال زنان ایرانی</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/14.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">جیوگی گپ</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->      <!-- Start Single Classes -->      <div class=\"col-lg-4 col-md-6 col-sm-12 wow fadeInRight\">       <article class=\"blog\">        <div class=\"blog__date\">         <span></span>        </div>        <div class=\"blog__thumb\">         <a href=\"#\"><img src=\"template/images/gallery/gallery-1/15.jpg\" alt=\"blog images\"></a>        </div>        <div class=\"blog__inner\">         <span></span>         <h4><a href=\"#\">چند روایت از جای جای ایران</a></h4>         <ul class=\"blog__meta d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between\">         </ul>        </div>       </article>      </div>      <!-- End Single Classes -->     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','template1','','','','',0,NULL,NULL,90000000,1,0,'',1,0,0,0,'',0,6,0,''),
 (126,'قالبمشاهده طعم اندیشه بزرگان','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم اندیشه بزرگان</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" >خرید</span> </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span>طعم اندیشه بزرگان</h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\">خرید</span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','قالبمشاهده طعم اندیشه بزرگان','','','','',0,NULL,NULL,90000000,1,0,'',4,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (127,'قالبمشاهده طعم موضوع','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم موضوع</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\">خرید</span> </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=طعم موضوع\" class=\"p jjLink hoverAutoWiki\">طعم موضوع</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\">خرید</span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','قالبمشاهده طعم موضوع','','','','',0,NULL,NULL,90000000,1,0,'',3,0,0,0,'',0,6,0,''),
 (128,'قالبمشاهده طعم تجربه','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم تجربه</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" >خرید</span> </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=طعم تجربه\" class=\"p jjLink hoverAutoWiki\">طعم تجربه</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\">خرید</span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','قالبمشاهده طعم تجربه','','','','',0,NULL,NULL,90000000,1,0,'',1,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (129,'دوره ها','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>دوره ها</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" ><a href=\"product.jsp?category=42\">خرید</a></span><span class=\"dcare__btn\" >مشاهده دوره ها</span>  </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html>  <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\'Server?do=Content.sw&amp;panel=sw&amp;text=دوره ها\' class=\'p jjLink hoverAutoWiki\'>دوره ها</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p>        <div class=\"wel__btn\"> <span class=\"dcare__btn\"><a href=\"product.jsp?category=42\">خرید</a></span><span class=\"dcare__btn\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=مشاهده دوره ها\" class=\"p jjLink hoverAutoWiki\">مشاهده دوره ها</a></span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','دوره ها','','','','',0,NULL,NULL,90000000,1,0,'',46,0,0,0,'',0,6,1,''),
 (130,'مشاهده دوره ها','<section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه هگل</h2><p> برای مشاهده ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه هایدگر</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه کارل ماکس</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه هگل\" class=\"p jjLink hoverAutoWiki\">جلسه هگل</a></h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه هایدگر\" class=\"p jjLink hoverAutoWiki\">جلسه هایدگر</a></h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه کارل ماکس\" class=\"p jjLink hoverAutoWiki\">جلسه کارل ماکس</a></h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','مشاهده دوره ها','','','','',0,NULL,NULL,90000000,1,0,'',23,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (131,'طعم اندیشه بزرگان','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم اندیشه بزرگان</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" ><a href=\"product.jsp?category=40\">خرید</a></span><span class=\"dcare__btn\" >مشاهده طعم اندیشه بزرگان</span>  </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=طعم اندیشه بزرگان\" class=\"p jjLink hoverAutoWiki\">طعم اندیشه بزرگان</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\"><a href=\"product.jsp?category=40\">خرید</a></span>         <span class=\"dcare__btn\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=مشاهده طعم اندیشه بزرگان\" class=\"p jjLink hoverAutoWiki\">مشاهده طعم اندیشه بزرگان</a></span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','طعم اندیشه بزرگان','','','','',0,NULL,NULL,90000000,1,0,'',18,0,0,0,'',0,6,0,''),
 (132,'مشاهده طعم اندیشه بزرگان','<section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی مارکس</h2><p> برای مشاهده ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی هگل</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی هایدگر&nbsp;</h2><p>جلسه ی اول هایدگر</p><p>جلسه ی دوم هایدگر</p><p>جلسه ی سوم هایدگر</p><p>(برای مشاهده&nbsp; ابتدا باید وارد شوید و&nbsp;<span style=\"font-size: 1.3rem;\">باید دوره را خریداری کرده باشید</span><span style=\"font-size: 1.3rem;\">)</span></p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی مارکس</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی هگل</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">دوره ی هایدگر </h2>         <p><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه ی اول هایدگر\" class=\"p jjLink hoverAutoWiki\">جلسه ی اول هایدگر</a></p>         <p>جلسه ی دوم هایدگر</p>         <p>جلسه ی سوم هایدگر</p>         <p>(برای مشاهده ابتدا باید وارد شوید و <span style=\"font-size: 1.3rem;\">باید دوره را خریداری کرده باشید</span><span style=\"font-size: 1.3rem;\">)</span></p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','مشاهده طعم اندیشه بزرگان','','','','',0,NULL,NULL,90000000,1,0,'',13,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (133,'طعم موضوع','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم موضوع</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" ><a href=\"product.jsp?category=40\">خرید</a></span><span class=\"dcare__btn\" >مشاهده طعم موضوع</span>  </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=طعم موضوع\" class=\"p jjLink hoverAutoWiki\">طعم موضوع</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\"><a href=\"product.jsp?category=40\">خرید</a></span>         <span class=\"dcare__btn\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=مشاهده طعم موضوع\" class=\"p jjLink hoverAutoWiki\">مشاهده طعم موضوع</a></span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','طعم موضوع','','','','',0,NULL,NULL,90000000,1,0,'',10,0,0,0,'',0,6,0,''),
 (134,'مشاهده طعم موضوع','<section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه اول موضوع</h2><p> برای مشاهده ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه دوم موضوع</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه سوم موضوع</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه اول موضوع</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه دوم موضوع</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه سوم موضوع</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','مشاهده طعم موضوع','','','','',0,NULL,NULL,90000000,1,0,'',4,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (135,'طعم تجربه','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>طعم تجربه</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">  <span class=\"dcare__btn\" ><a href=\"product.jsp?category=45\">خرید</a></span><span class=\"dcare__btn\" >مشاهده طعم تجربه</span>  </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=طعم تجربه\" class=\"p jjLink hoverAutoWiki\">طعم تجربه</a></h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">         <span class=\"dcare__btn\"><a href=\"product.jsp?category=45\">خرید</a></span>         <span class=\"dcare__btn\"><a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=مشاهده طعم تجربه\" class=\"p jjLink hoverAutoWiki\">مشاهده طعم تجربه</a></span>        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','طعم تجربه','','','','',0,NULL,NULL,90000000,1,0,'',4,0,0,0,'',0,6,0,''),
 (136,'مشاهده طعم تجربه','<section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه اول تجربه</h2><p> برای مشاهده ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه دوم تجربه</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section><section class=\"jnr__call__to__action bg-pngimage--3 mt-5\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12 col-sm-12 col-md-12\"><div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\"><div class=\"callto__action__inner\"><h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه سوم تجربه</h2><p> برای مشاهده  ابتدا باید وارد شوید</p></div><div class=\"callto__action__btn\" style=\"display:none;\"><span class=\"dcare__btn btn__white\" ><a href=\"index.jsp\">صفحه اصلی</a></span></div></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه اول تجربه</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه دوم تجربه</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   <section class=\"jnr__call__to__action bg-pngimage--3 mt-5\">    <div class=\"container\">     <div class=\"row\">      <div class=\"col-lg-12 col-sm-12 col-md-12\">       <div class=\"jnr__call__action__wrap d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between align-items-center\">        <div class=\"callto__action__inner\">         <h2 class=\"wow flipInX\" data-wow-delay=\"0.95s\">جلسه سوم تجربه</h2>         <p> برای مشاهده ابتدا باید وارد شوید</p>        </div>        <div class=\"callto__action__btn\" style=\"display:none;\">         <span class=\"dcare__btn btn__white\"><a href=\"index.jsp\">صفحه اصلی</a></span>        </div>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','0',1,1,'null','مشاهده طعم تجربه','','','','',0,NULL,NULL,90000000,1,0,'',3,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (137,'جلسه هگل','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>هگل</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">   </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\"  target=\"_blank\" href=\"upload/jivegiVideo.mp4\" ><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span>هگل</h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','6',1,1,'null','جلسه هگل','','','','',0,NULL,NULL,90000000,1,0,'',3,0,0,0,'',0,6,0,''),
 (138,'جلسه هایدگر','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>هایدگر</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><p class=\"wow flipInX\">جلسه ی اول هایدگر</p><div class=\"wel__btn\">   </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"upload/p9413164924.jpg\" alt=\"images\"><a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span>هایدگر</h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <p class=\"wow flipInX\">جلسه ی اول هایدگر</p>        <div class=\"wel__btn\">        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"upload/p9413164924.jpg\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'0','6',1,1,'null','جلسه هایدگر','','','','',0,NULL,NULL,90000000,1,0,'',1,0,0,0,'',0,6,0,'');
INSERT INTO `content` (`id`,`content_title`,`content_content`,`content_contentWithWikiLinks`,`content_parent`,`content_lang`,`content_privateGroupId`,`content_privateUserId`,`content_has_link`,`content_hasInContentAutoWiki`,`content_tags`,`content_titleTag`,`content_description`,`content_headerTags`,`content_style`,`content_script`,`content_isAjax`,`content_onclick`,`content_link`,`content_date`,`content_priority`,`content_category_id`,`content_pic`,`content_visit`,`content_likes`,`content_disLikes`,`content_userCommentsIsActive`,`content_target`,`content_isEditableOnlyByOwner`,`content_ownerId`,`content_autoWikIsUpdate`,`content_explain`) VALUES 
 (139,'جلسه کارل ماکس','<section class=\"junior__welcome__area  bg-pngimage--2\"><div class=\"container\"><div class=\"row jn__welcome__wrapper align-items-center\"><div class=\"col-md-12 col-lg-6 col-sm-12\"><div class=\"welcome__juniro__inner\"><h3><span class=\"theme-color\"></span><span></span>کارل مارکس</h3><p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده می‌شود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که می‌خواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضت‌گونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفته‌اند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان می‌گیرد.</p><div class=\"wel__btn\">   </div></div></div><div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\"><div class=\"jnr__Welcome__thumb wow fadeInRight\"><img src=\"template/images/wel-come/1.png\" alt=\"images\"><a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a></div></div></div></div></section>','<html> <head></head>  <body>   <section class=\"junior__welcome__area  bg-pngimage--2\">    <div class=\"container\">     <div class=\"row jn__welcome__wrapper align-items-center\">      <div class=\"col-md-12 col-lg-6 col-sm-12\">       <div class=\"welcome__juniro__inner\">        <h3><span class=\"theme-color\"></span><span></span>کارل مارکس</h3>        <p class=\"wow flipInX\">گفتمان انقلاب یا آنچه در آکادمیا گفتمان اسلامگرایی یا اسلامیسم ایرانی خوانده میشود از یک طرف یک گفتمان پسا استعمار است و موضعی ضد مناسبات قدرت در فضای جهانی دارد، از یک منظر یک گفتمان پست مدرن است به این معنی که میخواهد از سنت شرقی، اسلامی و ایرانی یک چارچوب برای نقد جهان مدرن و ارائه یک جهان جایگزین بسازد و از طرفی گفتمانی است ناظر به حکمرانی مدرن. این یعنی انتظار ما از اسلامیسم ایرانی این است که نهضتگونه آرمانگرا باشد و با نگاهی به سنت فقهی، عرفانی و فلسفی که در بستر ایرانی شکل گرفتهاند به مصاف دنیای مدرن برود و از طرفی به هیولای پیچیده حکمرانی دنیای مدرن هم احترام بگذارد و اینجاست که آن تناقض سامان میگیرد.</p>        <div class=\"wel__btn\">        </div>       </div>      </div>      <div class=\"col-md-12 col-lg-6 col-sm-12 md-mt-40 sm-mt-40\">       <div class=\"jnr__Welcome__thumb wow fadeInRight\">        <img src=\"template/images/wel-come/1.png\" alt=\"images\">        <a class=\"play__btn\" target=\"_blank\" href=\"upload/jivegiVideo.mp4\"><i class=\"fa fa-play\"></i></a>       </div>      </div>     </div>    </div>   </section>   </body></html>',0,1,'4','6',1,1,'null','جلسه کارل ماکس','','','','',0,NULL,NULL,90000000,1,0,'',1,0,0,0,'',0,6,0,''),
 (141,'دوره ی جامعه شناسی دین','<div class=\"card bd-primary\">              <div class=\"card-header bg-primary tx-white\">جلسه ی اول</div>              <div class=\"card-body pd-sm-30\">                <p class=\"mg-b-20 mg-sm-b-30\">فایل صوتی جلسه ی اول جامعه شناسی دین</p>                <div class=\"card bd-0 wd-xs-300 col-lg-4 col-sm-12 col-md-4\">                  <img class=\"card-img-top img-fluid\" src=\"upload/p0946386997.jpg\" alt=\"جامعه شناسی دین\" style=\"width: 100%;\">                  <div class=\"card-body bd bd-t-0\">                                        <span class=\"tx-12\">1399/07/21</span>                  </div>                </div>              <main>        <h1>جلسه اول</h1>        <audio id=\"a1\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>        <h1>جلسه دوم</h1>        <audio id=\"a2\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>        <h1>جلسه سوم</h1>        <audio id=\"a2\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>    </main></div><!-- card-body -->            </div>','<html> <head></head>  <body>   <div class=\"card bd-primary\">    <div class=\"card-header bg-primary tx-white\">     جلسه ی اول    </div>    <div class=\"card-body pd-sm-30\">     <p class=\"mg-b-20 mg-sm-b-30\">فایل صوتی <a href=\"Server?do=Content.sw&amp;panel=sw&amp;text=جلسه ی اول جامعه شناسی دین\" class=\"p jjLink hoverAutoWiki\">جلسه ی اول جامعه شناسی دین</a></p>     <div class=\"card bd-0 wd-xs-300 col-lg-4 col-sm-12 col-md-4\">      <img class=\"card-img-top img-fluid\" src=\"upload/p0946386997.jpg\" alt=\"جامعه شناسی دین\" style=\"width: 100%;\">      <div class=\"card-body bd bd-t-0\">       <span class=\"tx-12\">1399/07/21</span>      </div>     </div>     <main>      <h1>جلسه اول</h1>      <audio id=\"a1\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>      <h1>جلسه دوم</h1>      <audio id=\"a2\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>      <h1>جلسه سوم</h1>      <audio id=\"a2\" class=\"audioJivegi\" src=\"template/js/simpleAudioPlayer/sample.mp3\" crossorigin=\"anonymous\"></audio>     </main>    </div>    <!-- card-body -->   </div>   </body></html>',0,1,'7','',1,1,'','دوره ی جامعه شناسی دین','','','','<script type=\"text/javascript\">$(document).ready(function () {            $(\".audioJivegi\").simpleAudioPlayer({                title: \"مدرسه علوم انسانی جیوگی\",                chapters: [                    {                        \"seconds\": 1800,                        \"title\": \"<strong>نیم ساعت اول:</strong> برو به ثانیه 1800\"                    },                    {                        \"seconds\": 3600,                        \"title\": \"<strong>نیم ساعت دوم:</strong> برو به ثانیه 3600\"                    },                    {                        \"seconds\": 5400,                        \"title\": \"<strong>نیم ساعت سوم:</strong> برو به ثانیه 5400\"                    }                ]            });        }); </script>',0,NULL,NULL,13990914,1,6,'',10,0,0,0,'',0,6,0,'جلسه ی اول دوره ی جامعه شناسی دین که در یکشنبه 14/آذر/1399 برگزار شد');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `department_title` varchar(45) DEFAULT NULL,
  `department_publicContent` longtext,
  `department_praivateContent` longtext,
  `department_organizationalCode` varchar(45) DEFAULT NULL,
  `department_description` varchar(45) DEFAULT NULL,
  `department_pic` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `department_icon` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `department_location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`,`department_title`,`department_publicContent`,`department_praivateContent`,`department_organizationalCode`,`department_description`,`department_pic`,`department_icon`,`department_location`) VALUES 
 (1,'بخش بهبود کیفیت','تست محتوای عمومی بخش داخلی ---<br>','تست محتوای اختصاصی بخش داخلی ***<br>','fA32ب','',NULL,'p2414935677.jpg','1'),
 (36,'بخش جراحی مردان','','','','',NULL,'','null'),
 (37,'بخش بستری داخلی','','','','',NULL,'','null'),
 (38,'بخش جراحی زنان','','','','',NULL,'','null'),
 (39,'بخش روان مردان 1','','','','',NULL,'','null'),
 (41,'بخش روان مردان 2','','','','',NULL,'','null'),
 (42,'بخش روان زنان','','','','',NULL,'','null'),
 (43,'بخش اورژانس','','','','',NULL,'','null'),
 (44,'بخش ICU','','','','',NULL,'','null'),
 (48,'تجهیزات پزشکی','','','','',NULL,'','null'),
 (49,'تغذیه','','','','',NULL,'','null'),
 (50,'تاسیسات','','','','',NULL,'','null');
INSERT INTO `department` (`id`,`department_title`,`department_publicContent`,`department_praivateContent`,`department_organizationalCode`,`department_description`,`department_pic`,`department_icon`,`department_location`) VALUES 
 (51,'بهداشت حرفه ای','','','','',NULL,'','null'),
 (52,'ترابری','','','','',NULL,'','null'),
 (53,'بهداشت محیط','','','','',NULL,'','null'),
 (54,'دبیرخانه','','','','',NULL,'','null'),
 (55,'کلینیک','','','','',NULL,'','null'),
 (56,'تصویربرداری','','','','',NULL,'','null'),
 (57,'آزمایشگاه','','','','',NULL,'','null'),
 (58,'داروخانه','','','','',NULL,'','null'),
 (59,'انتظامات','','','','',NULL,'','null'),
 (60,'حسابداری','','','','',NULL,'','null'),
 (61,'صندوق','','','','',NULL,'','null'),
 (62,'اموال','','','','',NULL,'','null'),
 (63,'انبار','','','','',NULL,'','null'),
 (64,'حقوق و دستمزد','','','','',NULL,'','null'),
 (65,'کارگزینی','','','','',NULL,'','null'),
 (66,'HIT','','','','',NULL,'','null');
INSERT INTO `department` (`id`,`department_title`,`department_publicContent`,`department_praivateContent`,`department_organizationalCode`,`department_description`,`department_pic`,`department_icon`,`department_location`) VALUES 
 (67,'IT','','','','',NULL,'','null'),
 (68,'درآمد','','','','',NULL,'','null'),
 (69,'مددکاری','','','','',NULL,'','null'),
 (70,'روابط عمومی','','','','',NULL,'','null'),
 (75,'تدارکات','','','','',NULL,'','null'),
 (76,'مدیریت منابع انسانی','','','','',NULL,'','null'),
 (77,'مدیریت مالی','','','','',NULL,'','null'),
 (78,'مدیریت پرستاری','','','','',NULL,'','null'),
 (79,'مدیریت فنی و مهندسی','','','','',NULL,'','null'),
 (80,'ایمنی','','','','',NULL,'','null'),
 (81,'آموزش','','','','',NULL,'','null'),
 (82,'حفاظت فنی و بهداشت کار','','','','',NULL,'','null'),
 (83,'مدیریت ورهبری','','','','',NULL,'','null'),
 (84,'بخش کنترل عفونت','','','','',NULL,'','null'),
 (85,'ارتقا سلامت','','','','',NULL,'','null');
INSERT INTO `department` (`id`,`department_title`,`department_publicContent`,`department_praivateContent`,`department_organizationalCode`,`department_description`,`department_pic`,`department_icon`,`department_location`) VALUES 
 (86,'آموزش سلامت','','','','',NULL,'','null'),
 (87,'کمیته','','','','',NULL,'','null'),
 (88,'واحد آمار','','','','',NULL,'','null'),
 (89,'اتاق عمل','','','','',NULL,'',''),
 (90,'مدیریت اطلاعات سلامت','','','','',NULL,'','null'),
 (91,'حقوق گیرنده خدمت','','','','',NULL,'','null');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`departmentposition`
--

DROP TABLE IF EXISTS `departmentposition`;
CREATE TABLE `departmentposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentposition_parent` varchar(45) DEFAULT NULL,
  `departmentposition_subcategory` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `departmentposition_level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`departmentposition`
--

/*!40000 ALTER TABLE `departmentposition` DISABLE KEYS */;
INSERT INTO `departmentposition` (`id`,`departmentposition_parent`,`departmentposition_subcategory`,`departmentposition_level`) VALUES 
 (1,'0','بیمارستان','1'),
 (2,'1','بخش چشم','2'),
 (3,'2','چشم مردان','3'),
 (4,'2','چشم زنان','3'),
 (5,'1','بخش قلب','2'),
 (6,'0','دانشگاه علوم پزشکی اصفهان','0');
/*!40000 ALTER TABLE `departmentposition` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`email`
--

DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email_body` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `email_host` longtext CHARACTER SET utf8,
  `email_status` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `email_date` int(10) unsigned DEFAULT NULL,
  `email_to` longtext CHARACTER SET utf8,
  `email_send_time` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `email_comment` longtext CHARACTER SET utf8,
  `email_subject` longtext CHARACTER SET utf8,
  `email_from` longtext CHARACTER SET utf8,
  `email_visit_status` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `email_visit_time` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `email_visit_date` int(10) unsigned DEFAULT NULL,
  `email_visit_count` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`email`
--

/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` (`id`,`email_body`,`email_host`,`email_status`,`email_date`,`email_to`,`email_send_time`,`email_comment`,`email_subject`,`email_from`,`email_visit_status`,`email_visit_time`,`email_visit_date`,`email_visit_count`) VALUES 
 (1,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (2,'خانم رویا درفكی  به سامانه وارد شدین',NULL,'true',NULL,'royadorofki@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (3,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (4,'خانم رویا درفكی  به سامانه وارد شدین',NULL,'true',NULL,'royadorofki@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (5,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL);
INSERT INTO `email` (`id`,`email_body`,`email_host`,`email_status`,`email_date`,`email_to`,`email_send_time`,`email_comment`,`email_subject`,`email_from`,`email_visit_status`,`email_visit_time`,`email_visit_date`,`email_visit_count`) VALUES 
 (6,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (7,'خانم فرشته باهری  به سامانه وارد شدین',NULL,'true',NULL,'fereshtehbaheri@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (8,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (9,'خانم فرشته باهری  به سامانه وارد شدین',NULL,'true',NULL,'fereshtehbaheri@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (10,'خانم فرشته باهری  به سامانه وارد شدین',NULL,'true',NULL,'fereshtehbaheri@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL);
INSERT INTO `email` (`id`,`email_body`,`email_host`,`email_status`,`email_date`,`email_to`,`email_send_time`,`email_comment`,`email_subject`,`email_from`,`email_visit_status`,`email_visit_time`,`email_visit_date`,`email_visit_count`) VALUES 
 (11,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (12,'خانم فرشته باهری  به سامانه وارد شدین',NULL,'true',NULL,'fereshtehbaheri@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (13,'خانم فرشته باهری  به سامانه وارد شدین',NULL,'true',NULL,'fereshtehbaheri@gmail.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (14,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (15,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL);
INSERT INTO `email` (`id`,`email_body`,`email_host`,`email_status`,`email_date`,`email_to`,`email_send_time`,`email_comment`,`email_subject`,`email_from`,`email_visit_status`,`email_visit_time`,`email_visit_date`,`email_visit_count`) VALUES 
 (16,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (17,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (18,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL),
 (19,'pass:123456',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'درخواست پسورد فراموش شده','sepanoweb@gmail.com',NULL,NULL,NULL,NULL),
 (20,'pass:12345',NULL,'true',NULL,'golenarges782@gmail.com',NULL,NULL,'درخواست پسورد فراموش شده','sepanoweb@gmail.com',NULL,NULL,NULL,NULL);
INSERT INTO `email` (`id`,`email_body`,`email_host`,`email_status`,`email_date`,`email_to`,`email_send_time`,`email_comment`,`email_subject`,`email_from`,`email_visit_status`,`email_visit_time`,`email_visit_date`,`email_visit_count`) VALUES 
 (21,' سید مجید شریعتی  به سامانه وارد شدین',NULL,'true',NULL,'shariatimajid@yahoo.com',NULL,NULL,'ورود به سامانه','mahdiafg1400@gmail.com',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`enrolment`
--

DROP TABLE IF EXISTS `enrolment`;
CREATE TABLE `enrolment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enrolment_val1` varchar(500) DEFAULT NULL,
  `enrolment_val2` varchar(500) DEFAULT NULL,
  `enrolment_val3` varchar(500) DEFAULT NULL,
  `enrolment_val4` varchar(500) DEFAULT NULL,
  `enrolment_val5` varchar(500) DEFAULT NULL,
  `enrolment_val6` varchar(500) DEFAULT NULL,
  `enrolment_val7` varchar(500) DEFAULT NULL,
  `enrolment_val8` varchar(500) DEFAULT NULL,
  `enrolment_val9` varchar(500) DEFAULT NULL,
  `enrolment_val10` varchar(500) DEFAULT NULL,
  `enrolment_val11` varchar(500) DEFAULT NULL,
  `enrolment_val12` varchar(500) DEFAULT NULL,
  `enrolment_val13` varchar(500) DEFAULT NULL,
  `enrolment_val14` varchar(500) DEFAULT NULL,
  `enrolment_val15` varchar(500) DEFAULT NULL,
  `enrolment_val16` varchar(500) DEFAULT NULL,
  `enrolment_val17` varchar(500) DEFAULT NULL,
  `enrolment_val18` varchar(500) DEFAULT NULL,
  `enrolment_val19` varchar(500) DEFAULT NULL,
  `enrolment_val20` varchar(500) DEFAULT NULL,
  `enrolment_val21` varchar(500) DEFAULT NULL,
  `enrolment_val22` varchar(500) DEFAULT NULL,
  `enrolment_val23` varchar(500) DEFAULT NULL,
  `enrolment_val24` varchar(500) DEFAULT NULL,
  `enrolment_val25` varchar(500) DEFAULT NULL,
  `enrolment_val26` varchar(500) DEFAULT NULL,
  `enrolment_val27` varchar(500) DEFAULT NULL,
  `enrolment_val28` varchar(500) DEFAULT NULL,
  `enrolment_val29` varchar(500) DEFAULT NULL,
  `enrolment_val30` varchar(500) DEFAULT NULL,
  `enrolment_val31` longtext,
  `enrolment_val32` longtext,
  `enrolment_title` varchar(500) DEFAULT NULL,
  `enrolment_file1` varchar(500) DEFAULT NULL,
  `enrolment_file2` varchar(500) DEFAULT NULL,
  `enrolment_pic1` varchar(255) DEFAULT NULL,
  `enrolment_pic2` varchar(255) DEFAULT NULL,
  `enrolment_pic3` varchar(255) DEFAULT NULL,
  `enrolment_pic4` varchar(255) DEFAULT NULL,
  `enrolment_send_date` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`enrolment`
--

/*!40000 ALTER TABLE `enrolment` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrolment` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`enrolment3`
--

DROP TABLE IF EXISTS `enrolment3`;
CREATE TABLE `enrolment3` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enrolment3_name` varchar(255) DEFAULT NULL,
  `enrolment3_tell` varchar(255) DEFAULT NULL,
  `enrolment3_mobile` varchar(45) DEFAULT NULL,
  `enrolment3_birthdate` int(10) unsigned DEFAULT NULL,
  `enrolment3_marital_status` varchar(255) DEFAULT NULL,
  `enrolment3_certificate` varchar(255) DEFAULT NULL,
  `enrolment3_major` varchar(255) DEFAULT NULL,
  `enrolment3_university` varchar(255) DEFAULT NULL,
  `enrolment3_starting_date` int(10) unsigned DEFAULT NULL,
  `enrolment3_end_date` int(10) unsigned DEFAULT NULL,
  `enrolment3_favorite_group` varchar(255) DEFAULT NULL,
  `enrolment3_cv` varchar(255) DEFAULT NULL,
  `enrolment3_publish` varchar(255) DEFAULT NULL,
  `enrolment3_live` varchar(255) DEFAULT NULL,
  `enrolment3_address` varchar(255) DEFAULT NULL,
  `enrolment3_pic` varchar(255) DEFAULT NULL,
  `enrolment3_file1` varchar(255) DEFAULT NULL,
  `enrolment3_file2` varchar(255) DEFAULT NULL,
  `enrolment3_elementary` varchar(255) DEFAULT NULL,
  `enrolment3_intermediate` varchar(255) DEFAULT NULL,
  `enrolment3_advanced` varchar(255) DEFAULT NULL,
  `enrolment3_specific_course` varchar(255) DEFAULT NULL,
  `enrolment3_speaking` varchar(255) DEFAULT NULL,
  `enrolment3_listening` varchar(255) DEFAULT NULL,
  `enrolment3_writing` varchar(255) DEFAULT NULL,
  `enrolment3_reading` varchar(255) DEFAULT NULL,
  `enrolment3_date` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`enrolment3`
--

/*!40000 ALTER TABLE `enrolment3` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrolment3` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`forum`
--

DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `forum_date` int(10) unsigned DEFAULT NULL,
  `forum_content` longtext,
  `forum_creator` int(10) unsigned DEFAULT NULL,
  `forum_category_id` int(10) unsigned DEFAULT NULL,
  `forum_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`forum`
--

/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_approved`
--

DROP TABLE IF EXISTS `hmis_approved`;
CREATE TABLE `hmis_approved` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `approved_isActive` int(10) unsigned NOT NULL DEFAULT '0',
  `approved_commettesId` int(10) unsigned NOT NULL DEFAULT '0',
  `approved_sessionsId` int(11) NOT NULL DEFAULT '0',
  `approved_offererId` int(10) unsigned NOT NULL DEFAULT '0',
  `approved_trackerId` varchar(45) DEFAULT NULL,
  `approved_executorRoleId` varchar(255) DEFAULT NULL,
  `approved_executorUserId` varchar(255) DEFAULT NULL,
  `approved_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `approved_startDate` int(10) unsigned NOT NULL DEFAULT '10000000',
  `approved_status` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `approved_file` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `approved_description` longtext CHARACTER SET utf8mb4,
  `approved_statusLog` longtext CHARACTER SET utf8mb4,
  `approved_descriptionExecutor` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `approved_descriptionTracker` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `approved_endDate` int(10) unsigned NOT NULL DEFAULT '10000000',
  `approved_filesTracker` varchar(255) DEFAULT NULL,
  `approved_filesExecutor` varchar(255) DEFAULT NULL,
  `approved_percentExecutor` varchar(20) DEFAULT NULL,
  `approved_percentTracker` varchar(45) DEFAULT NULL,
  `approved_percent` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='?????';

--
-- Dumping data for table `db_bambo`.`hmis_approved`
--

/*!40000 ALTER TABLE `hmis_approved` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_approved` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_commettes`
--

DROP TABLE IF EXISTS `hmis_commettes`;
CREATE TABLE `hmis_commettes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `commettes_isActive` int(10) unsigned NOT NULL DEFAULT '1',
  `commettes_creatorId` int(10) unsigned NOT NULL DEFAULT '0',
  `commettes_superwizar` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `commettes_secretary` varchar(255) DEFAULT NULL,
  `commettes_period` varchar(45) DEFAULT NULL,
  `commettes_members` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `commettes_regulationFile` varchar(255) DEFAULT NULL,
  `commettes_documentsFile` varchar(255) DEFAULT NULL,
  `commettes_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `commettes_description` longtext CHARACTER SET utf8mb4,
  `commettes_date` int(10) unsigned NOT NULL DEFAULT '10000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_commettes`
--

/*!40000 ALTER TABLE `hmis_commettes` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_commettes` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_createdocumentary`
--

DROP TABLE IF EXISTS `hmis_createdocumentary`;
CREATE TABLE `hmis_createdocumentary` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `createDocumentary_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_owner` varchar(45) DEFAULT NULL,
  `createDocumentary_owner_role` varchar(45) DEFAULT NULL,
  `createDocumentary_file1` varchar(45) DEFAULT NULL,
  `createDocumentary_file2` varchar(45) DEFAULT NULL,
  `createDocumentary_file3` varchar(45) DEFAULT NULL,
  `createDocumentary_htmlContent` longtext CHARACTER SET utf8mb4,
  `createDocumentary_signatory_user_1` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_2` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_3` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_4` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_5` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_6` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_7` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_8` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_9` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_10` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_11` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_12` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_13` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_14` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_15` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_16` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_17` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_18` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_19` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_user_20` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_title_1` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_2` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_3` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_4` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_5` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_6` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_7` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_8` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_9` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_10` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_11` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_12` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_13` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_14` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_15` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_16` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_17` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_18` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_19` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_title_20` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_signature_1` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_2` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_3` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_4` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_5` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_6` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_7` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_8` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_9` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_10` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_11` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_12` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_13` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_14` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_15` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_16` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_17` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_18` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_19` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_signature_20` int(11) NOT NULL DEFAULT '-1',
  `createDocumentary_signatory_comment_1` varchar(450) DEFAULT NULL,
  `createDocumentary_signatory_comment_2` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_3` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_4` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_5` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_6` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_7` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_8` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_9` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_10` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_11` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_12` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_13` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_14` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_15` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_16` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_17` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_18` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_19` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_comment_20` varchar(450) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_attachmentfile3` varchar(45) DEFAULT NULL,
  `createDocumentary_attachmentfile2` varchar(45) DEFAULT NULL,
  `createDocumentary_attachmentfile1` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_date` varchar(45) DEFAULT NULL,
  `createDocumentary_summary` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_category` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_titleFile3` varchar(45) DEFAULT NULL,
  `createDocumentary_titleFile2` varchar(45) DEFAULT NULL,
  `createDocumentary_titleFile1` varchar(45) DEFAULT NULL,
  `createDocumentary_signatory_role_1` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_2` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_3` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_4` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_5` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_6` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_7` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_8` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_9` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_10` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_11` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_12` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_13` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_14` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_15` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_16` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_17` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_18` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_19` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_signatory_role_20` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_attachmentfileTitle1` varchar(45) DEFAULT NULL,
  `createDocumentary_revisionDate` varchar(45) DEFAULT NULL,
  `createDocumentary_responsibleDocumentary` varchar(45) DEFAULT NULL,
  `createDocumentary_reciversRoles` varchar(500) DEFAULT NULL,
  `createDocumentary_reciversUsers` varchar(500) DEFAULT NULL,
  `createDocumentary_communicator` varchar(500) DEFAULT NULL,
  `createDocumentary_status` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createDocumentary_logStatus` longtext CHARACTER SET utf8mb4,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_createdocumentary`
--

/*!40000 ALTER TABLE `hmis_createdocumentary` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_createdocumentary` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_documentary`
--

DROP TABLE IF EXISTS `hmis_documentary`;
CREATE TABLE `hmis_documentary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `documentary_uploaderUserId` int(10) unsigned DEFAULT NULL,
  `documentary_responsibleLoadingRole` int(10) unsigned DEFAULT NULL,
  `documentary_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `documentary_nameName` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `documentary_LoadingPeriod` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `documentary_dateCreation` int(10) unsigned DEFAULT NULL,
  `documentary_status` varchar(455) CHARACTER SET utf8mb4 DEFAULT NULL,
  `documentary_attachFileDocumentary` varchar(455) DEFAULT NULL,
  `documentary_gaugeId` int(10) unsigned DEFAULT NULL,
  `documentary_logStatus` longtext,
  `documentary_comments` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`hmis_documentary`
--

/*!40000 ALTER TABLE `hmis_documentary` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_documentary` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_fmea`
--

DROP TABLE IF EXISTS `hmis_fmea`;
CREATE TABLE `hmis_fmea` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fmea_guideTable` longtext CHARACTER SET utf8mb4,
  `fmea_beforeCorrectiveAction` longtext CHARACTER SET utf8mb4,
  `fmea_afterCorrectiveAction` longtext CHARACTER SET utf8mb4,
  `fmea_member` varchar(100) DEFAULT NULL,
  `fmea_title` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_fmea`
--

/*!40000 ALTER TABLE `hmis_fmea` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_fmea` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_formanswers`
--

DROP TABLE IF EXISTS `hmis_formanswers`;
CREATE TABLE `hmis_formanswers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `formanswers_answerSet_id` varchar(45) NOT NULL DEFAULT '',
  `formanswers_questionId` varchar(20) NOT NULL DEFAULT '',
  `formanswers_answer` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='???? ????? ???? ?? ???? ?? ?? ?? ?? ??? ???? ???? ?? ????';

--
-- Dumping data for table `db_bambo`.`hmis_formanswers`
--

/*!40000 ALTER TABLE `hmis_formanswers` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_formanswers` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_formanswerset`
--

DROP TABLE IF EXISTS `hmis_formanswerset`;
CREATE TABLE `hmis_formanswerset` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `formAnswers_formId` int(10) unsigned NOT NULL DEFAULT '0',
  `formAnswers_userId` int(10) unsigned NOT NULL DEFAULT '0',
  `formAnswers_userName` varchar(45) DEFAULT NULL COMMENT '??? ?????? ?? ??? ?? ????? ???? ????? ?? ??? ?? ?? ???? ????? ?????? ???',
  `formAnswers_userMAC` varchar(200) NOT NULL DEFAULT '',
  `formAnswers_userIPV4` varchar(200) NOT NULL DEFAULT '',
  `formAnswers_userIPV6` varchar(200) NOT NULL DEFAULT '',
  `formAnswers_userRole` varchar(45) DEFAULT NULL,
  `formAnswers_date` int(10) unsigned NOT NULL DEFAULT '0',
  `formAnswers_time` int(10) unsigned NOT NULL DEFAULT '0',
  `formAnswers_status` varchar(45) NOT NULL DEFAULT '',
  `formAnswers_statusLog` varchar(2000) NOT NULL DEFAULT '',
  `hmis_formanswerset_sum` int(11) NOT NULL DEFAULT '0',
  `hmis_formanswerset_avg` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`hmis_formanswerset`
--

/*!40000 ALTER TABLE `hmis_formanswerset` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_formanswerset` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_formquestionoptions`
--

DROP TABLE IF EXISTS `hmis_formquestionoptions`;
CREATE TABLE `hmis_formquestionoptions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `formQuestionOptions_lable` varchar(1000) DEFAULT NULL,
  `formQuestionOptions_value` varchar(45) DEFAULT NULL,
  `formQuestionOptions_question_id` int(10) unsigned NOT NULL DEFAULT '0',
  `formQuestionOptions_icon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`hmis_formquestionoptions`
--

/*!40000 ALTER TABLE `hmis_formquestionoptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_formquestionoptions` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_formquestions`
--

DROP TABLE IF EXISTS `hmis_formquestions`;
CREATE TABLE `hmis_formquestions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `formQuestions_formID` int(10) unsigned NOT NULL DEFAULT '0',
  `formQuestions_title` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `formQuestions_defaultValue` varchar(1000) DEFAULT NULL,
  `formQuestions_placeHolder` varchar(1000) DEFAULT NULL,
  `formQuestions_isRequierd` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `formQuestions_icon` varchar(45) DEFAULT NULL,
  `formQuestions_weight` int(11) NOT NULL DEFAULT '1',
  `formQuestions_trueAnswer` varchar(1000) DEFAULT NULL,
  `formQuestions_answersType` varchar(20) NOT NULL DEFAULT 'text' COMMENT 'radio,text,number,...',
  `formQuestions_htmlDiscription` longtext CHARACTER SET utf8mb4,
  `formQuestions_htmlDiscriptionInResult` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`hmis_formquestions`
--

/*!40000 ALTER TABLE `hmis_formquestions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_formquestions` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_forms`
--

DROP TABLE IF EXISTS `hmis_forms`;
CREATE TABLE `hmis_forms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `forms_code` varchar(45) DEFAULT NULL,
  `forms_title` varchar(500) NOT NULL DEFAULT '',
  `forms_category_id` int(10) unsigned DEFAULT NULL,
  `forms_priority` int(10) unsigned DEFAULT NULL,
  `forms_departments` varchar(45) NOT NULL DEFAULT '0',
  `forms_isActive` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `forms_isDateEditable` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `forms_tags` varchar(45) DEFAULT NULL,
  `forms_icon` varchar(20) NOT NULL DEFAULT '',
  `forms_ownerId` varchar(10) NOT NULL DEFAULT '0',
  `forms_ownerRole` varchar(10) DEFAULT NULL,
  `forms_accesseRoles` varchar(500) DEFAULT NULL,
  `forms_accessesUsers` varchar(500) DEFAULT NULL,
  `forms_resultAccessRole` varchar(500) DEFAULT NULL,
  `forms_resultAccessUsers` varchar(500) DEFAULT NULL,
  `forms_uniqueComplete` tinyint(3) unsigned DEFAULT NULL,
  `forms_showResultToQuestioner` tinyint(3) unsigned DEFAULT NULL,
  `forms_showAllResultToQuestioner` tinyint(3) unsigned DEFAULT NULL,
  `forms_showCometePreAproveForm` tinyint(3) unsigned DEFAULT NULL,
  `forms_creationDate` int(10) unsigned NOT NULL DEFAULT '0',
  `forms_creationTime` int(10) unsigned NOT NULL DEFAULT '0',
  `forms_expireDate` int(10) unsigned DEFAULT NULL,
  `forms_expireTime` varchar(45) DEFAULT NULL,
  `forms_visit` int(11) NOT NULL DEFAULT '0',
  `forms_nextFormId` varchar(10) DEFAULT NULL,
  `forms_isAutoWiki` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `forms_hasAutoWikiInContent` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `forms_autoWikIsUpdate` tinyint(1) DEFAULT NULL,
  `forms_css` longtext,
  `forms_javaScript` longtext,
  `forms_htmlContent` longtext,
  `forms_htmlContentWithWikiLinks` longtext,
  `forms_discription` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`hmis_forms`
--

/*!40000 ALTER TABLE `hmis_forms` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_forms` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_indicatiordiscriptions`
--

DROP TABLE IF EXISTS `hmis_indicatiordiscriptions`;
CREATE TABLE `hmis_indicatiordiscriptions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `indicatiorDiscriptions_indicatorId` int(10) unsigned NOT NULL DEFAULT '0',
  `indicatiorDiscriptions_writerUserId` int(10) unsigned NOT NULL DEFAULT '0',
  `indicatiorDiscriptions_date` int(10) unsigned NOT NULL DEFAULT '0',
  `indicatiorDiscriptions_files` varchar(1000) DEFAULT NULL,
  `indicatiorDiscriptions_title` varchar(500) DEFAULT NULL,
  `indicatiorDiscriptions_discription` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`hmis_indicatiordiscriptions`
--

/*!40000 ALTER TABLE `hmis_indicatiordiscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_indicatiordiscriptions` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_indicatiors`
--

DROP TABLE IF EXISTS `hmis_indicatiors`;
CREATE TABLE `hmis_indicatiors` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `indicators_code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `indicators_title` varchar(255) DEFAULT NULL,
  `indicators_icon` varchar(45) DEFAULT NULL,
  `indicators_type` varchar(45) DEFAULT NULL,
  `indicators_scop` varchar(45) DEFAULT NULL,
  `indicators_department_id` int(10) unsigned DEFAULT NULL,
  `indicators_dimension` varchar(45) DEFAULT NULL,
  `indicators_priority` varchar(45) DEFAULT NULL,
  `indicators_difinition` varchar(1000) DEFAULT NULL,
  `indicators_collectingReason` varchar(450) DEFAULT NULL,
  `indicators_comments` longtext,
  `indicators_periodOfCollectiong` varchar(10) DEFAULT NULL,
  `indicators_periodOfAnalysis` varchar(10) DEFAULT NULL,
  `indicators_startDate` int(10) unsigned DEFAULT NULL,
  `indicators_resultAccessRols` varchar(1000) DEFAULT NULL,
  `indicators_resultAccessUsers` varchar(1000) DEFAULT NULL,
  `indicators_isAutoWiki` varchar(45) DEFAULT NULL,
  `indicators_goodFloor` varchar(45) DEFAULT NULL,
  `indicators_goodTop` varchar(45) DEFAULT NULL,
  `indicators_warnningFloor` varchar(45) DEFAULT NULL,
  `indicators_warnningTop` varchar(45) DEFAULT NULL,
  `indicators_dangerFloor` varchar(45) DEFAULT NULL,
  `indicators_dangerTop` varchar(45) DEFAULT NULL,
  `indicators_formula` varchar(400) DEFAULT NULL,
  `indicators_source_a` varchar(10) DEFAULT NULL,
  `indicators_form_a` varchar(10) DEFAULT NULL,
  `indicators_question_a` varchar(10) DEFAULT NULL,
  `indicators_a` varchar(45) DEFAULT NULL COMMENT 'exmple: sum(q1)  or avg(f3)',
  `indicators_source_b` varchar(10) DEFAULT NULL,
  `indicators_form_b` varchar(10) DEFAULT NULL,
  `indicators_question_b` varchar(10) DEFAULT NULL,
  `indicators_b` varchar(45) DEFAULT NULL,
  `indicators_source_c` varchar(10) DEFAULT NULL,
  `indicators_form_c` varchar(10) DEFAULT NULL,
  `indicators_question_c` varchar(10) DEFAULT NULL,
  `indicators_c` varchar(45) DEFAULT NULL,
  `indicators_source_d` varchar(10) DEFAULT NULL,
  `indicators_form_d` varchar(10) DEFAULT NULL,
  `indicators_question_d` varchar(10) DEFAULT NULL,
  `indicators_d` varchar(45) DEFAULT NULL,
  `indicators_source_e` varchar(10) DEFAULT NULL,
  `indicators_form_e` varchar(10) DEFAULT NULL,
  `indicators_question_e` varchar(10) DEFAULT NULL,
  `indicators_e` varchar(45) DEFAULT NULL,
  `indicators_source_f` varchar(10) DEFAULT NULL,
  `indicators_form_f` varchar(10) DEFAULT NULL,
  `indicators_question_f` varchar(10) DEFAULT NULL,
  `indicators_f` varchar(45) DEFAULT NULL,
  `indicators_source_g` varchar(10) DEFAULT NULL,
  `indicators_form_g` varchar(10) DEFAULT NULL,
  `indicators_question_g` varchar(10) DEFAULT NULL,
  `indicators_g` varchar(45) DEFAULT NULL,
  `indicators_ownerId` varchar(10) DEFAULT NULL,
  `indicators_ownerRole` varchar(10) DEFAULT NULL,
  `indicators_responsibleUser` varchar(45) DEFAULT NULL,
  `indicators_responsibleRole` varchar(45) DEFAULT NULL,
  `indicators_isActive` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `indicators_htmlContent` longtext,
  `indicators_htmlContentWithWikiLinks` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`hmis_indicatiors`
--

/*!40000 ALTER TABLE `hmis_indicatiors` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_indicatiors` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_managementgauges`
--

DROP TABLE IF EXISTS `hmis_managementgauges`;
CREATE TABLE `hmis_managementgauges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `managementGauges_department` varchar(1445) DEFAULT NULL,
  `managementGauges_axis` varchar(1445) DEFAULT NULL,
  `managementGauges_underTheAxis` varchar(1445) DEFAULT NULL,
  `managementGauges_standard` varchar(1445) DEFAULT NULL,
  `managementGauges_gauge` varchar(1445) DEFAULT NULL,
  `managementGauges_step` longtext,
  `managementGauges_date` varchar(20) DEFAULT NULL,
  `managementGauges_uploadPeriod` varchar(10) DEFAULT NULL,
  `managementGauges_responsibleLoading` varchar(45) DEFAULT NULL,
  `managementGauges_responsibleLoadingRole` int(10) unsigned NOT NULL DEFAULT '0',
  `managementGauges_comment` longtext,
  `managementGauges_isActive` varchar(4) DEFAULT NULL,
  `managementGauges_level` varchar(45) DEFAULT NULL,
  `managementGauges_responsibleGauge` varchar(10) DEFAULT NULL,
  `managementGauges_isDateEditable` varchar(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`hmis_managementgauges`
--

/*!40000 ALTER TABLE `hmis_managementgauges` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_managementgauges` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_messenger`
--

DROP TABLE IF EXISTS `hmis_messenger`;
CREATE TABLE `hmis_messenger` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `messenger_title` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_textMessage` longtext CHARACTER SET utf8mb4,
  `messenger_receiver` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_sender` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_postageDate` varchar(45) NOT NULL DEFAULT '',
  `messenger_dateOfCreation` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_displayed` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_status` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_sendingMethod` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_logStatus` longtext CHARACTER SET utf8mb4,
  `messenger_attachFile` longtext CHARACTER SET utf8mb4,
  `messenger_attachFileTitle` longtext CHARACTER SET utf8mb4,
  `messenger_titleFile` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_file` varchar(500) DEFAULT NULL,
  `messenger_textHTML` longtext CHARACTER SET utf8mb4,
  `messenger_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`,`messenger_postageDate`)
) ENGINE=InnoDB AUTO_INCREMENT=1916 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_messenger`
--

/*!40000 ALTER TABLE `hmis_messenger` DISABLE KEYS */;
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1740,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 13:03:50%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1741,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 16:44:52%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1742,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 19:45:44%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1743,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 19:47:29%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1744,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 19:48:18%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1745,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 19:49:18%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1746,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 19:55:31%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1747,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 20:01:42%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1748,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990531',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/05/31 20:20:11%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1749,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990601',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/01 09:36:23%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1750,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990603',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/03 12:06:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1751,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990605',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/05 09:35:11%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1752,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990605',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/05 09:40:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1753,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990605',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/05 10:33:32%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1754,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','6','','13990605',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/06/05 11:27:14%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1755,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990605',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/05 12:24:04%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1756,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990608',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/08 15:00:56%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1757,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990608',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/08 22:54:43%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1758,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990609',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/09 14:52:12%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1759,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990609',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/09 23:58:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1760,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990610',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/10 12:11:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1761,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990611',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/11 00:35:37%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1762,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990611',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/11 10:41:52%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1763,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990611',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/11 14:49:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1764,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990611',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/11 16:39:54%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1765,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990611',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/11 23:37:35%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1766,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990612',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/12 14:57:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1767,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990613',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/13 01:28:56%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1768,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990613',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/13 21:33:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1769,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990614',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/14 01:48:26%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1770,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990614',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/14 17:39:07%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1771,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990614',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/14 19:59:49%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1772,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990615',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/15 11:16:47%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1773,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990615',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/15 13:54:20%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1774,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990615',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/15 23:14:39%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1775,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 00:13:32%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1776,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 12:53:39%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1777,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 16:19:27%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1778,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 17:18:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1779,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','7','','13990616',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/06/16 17:23:51%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1780,'تغییر در پروفایل','ایمیل شما بهa@yahoo.com تغییر کرد','7','','13990616',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/06/16 17:23:52%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1781,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 17:35:37%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1782,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 17:38:21%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1783,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 23:16:33%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1784,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990616',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/16 23:43:38%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1785,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 00:21:33%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1786,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 00:58:55%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1787,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 09:07:31%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1788,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 09:27:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1789,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 10:18:12%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1790,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 10:27:00%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1791,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 10:30:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1792,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 10:45:30%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1793,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990617',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/17 11:02:37%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1794,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990618',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/18 00:00:06%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1795,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990618',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/18 11:25:09%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1796,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990618',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/18 15:47:24%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1797,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990618',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/18 23:47:38%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1798,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 00:06:20%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1799,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 00:15:03%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1800,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 09:28:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1801,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 09:48:27%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1802,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 10:10:02%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1803,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 11:43:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1804,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 11:59:19%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1805,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990619',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/19 14:58:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1806,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990621',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/21 01:55:58%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1807,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990621',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/21 20:53:27%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1808,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990622',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/22 09:48:21%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1809,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990622',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/22 11:13:07%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1810,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990622',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/22 14:56:33%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1811,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990622',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/22 23:59:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1812,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990623',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/23 21:07:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1813,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990624',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/24 01:07:09%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1814,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990624',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/24 14:33:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1815,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990624',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/24 15:41:27%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1816,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990624',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/24 22:45:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1817,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990624',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/24 22:57:10%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1818,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 00:18:15%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1819,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 10:03:14%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1820,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 10:05:05%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1821,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 11:52:32%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1822,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 22:22:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1823,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990625',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/25 22:49:00%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1824,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990626',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/26 13:54:32%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1825,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990626',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/26 15:18:08%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1826,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990626',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/26 15:32:26%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1827,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990627',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/27 19:27:47%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1828,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990627',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/27 22:17:28%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1829,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990628',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/28 19:48:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1830,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990629',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/29 10:13:43%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1831,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990629',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/29 23:30:49%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1832,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990630',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/30 23:08:54%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1833,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990630',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/30 23:25:49%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1834,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990631',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/06/31 16:37:02%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1835,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990704',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/04 02:20:56%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1836,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990705',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/05 12:49:26%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1837,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990705',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/05 13:13:47%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1838,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990705',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/05 22:32:07%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1839,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990706',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/06 21:13:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1840,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990706',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/06 22:19:48%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1841,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990707',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/07 10:26:02%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1842,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990707',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/07 10:50:18%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1843,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990708',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/08 00:29:12%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1844,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990708',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/08 15:51:40%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1845,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990708',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/08 17:01:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1846,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990708',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/08 17:40:54%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1847,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990708',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/08 22:56:47%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1848,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990709',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/09 09:23:01%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1849,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990709',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/09 13:26:24%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1850,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990710',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/10 23:57:14%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1851,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990711',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/11 02:02:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1852,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990713',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/13 11:52:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1853,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990714',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/14 11:59:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1854,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990714',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/14 12:36:06%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1855,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990716',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/16 11:28:16%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1856,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990719',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/19 13:22:51%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1857,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990719',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/19 14:24:28%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1858,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990720',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/20 12:39:03%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1859,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 10:11:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1860,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 12:49:02%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1861,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 14:54:38%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1862,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 14:57:30%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1863,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 15:03:11%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1864,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 15:11:12%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1865,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 15:17:12%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1866,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 15:36:30%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1867,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990721',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/21 16:14:13%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1868,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990722',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/22 09:33:20%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1869,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990722',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/22 10:00:36%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1870,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990722',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/22 10:15:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1871,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990722',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/22 15:15:42%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1872,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990723',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/23 10:43:58%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1873,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990723',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/07/23 13:44:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1874,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990808',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/08 20:41:21%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1875,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990809',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/09 17:10:26%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1876,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990810',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/10 15:57:22%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1877,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990810',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/10 22:35:34%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1878,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990811',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/11 14:19:01%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1879,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990811',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/11 23:10:24%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1880,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990811',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/11 23:56:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1881,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990812',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/12 09:24:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1882,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990812',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/12 12:39:29%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1883,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990812',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/12 15:49:18%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1884,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990814',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/14 14:52:44%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1885,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990816',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/16 00:34:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1886,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990816',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/16 17:51:02%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1887,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990816',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/16 19:24:32%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1888,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990816',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/16 20:27:09%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1889,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990817',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/17 10:20:22%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1890,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990817',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/17 13:27:30%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1891,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990817',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/17 13:40:58%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1892,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990817',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/17 16:31:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1893,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990819',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/19 10:44:59%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1894,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990819',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/19 15:11:00%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1895,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990820',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/20 22:13:28%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1896,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990820',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/20 22:35:21%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1897,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990820',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/20 23:34:52%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1898,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990821',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/21 10:15:57%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1899,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990821',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/21 10:28:35%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1900,'ورود به سامانه',' aaaa aaaa  به سامانه وارد شدید','2','0','13990823',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/23 18:08:27%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1901,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990823',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/23 18:19:55%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1902,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','13','','13990823',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/08/23 18:47:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1903,'تغییر در پروفایل','پسورد شما بهmm123456 تغییر کرد','13','','13990823',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/08/23 18:47:25%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1904,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990823',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/23 23:30:22%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1905,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','14','','13990823',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/08/23 23:30:47%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1906,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990823',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/23 23:54:49%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1907,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','15','','13990824',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/08/24 00:18:43%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1908,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990827',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/08/27 16:31:39%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1909,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990916',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/09/16 14:32:45%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1910,'تغییر در پروفایل','شماره موبایل شما به تغییر کرد','16','','13990916',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/09/16 17:46:52%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1911,'تغییر در پروفایل','پسورد شما بهmm123456 تغییر کرد','16','','13990916',NULL,NULL,'ارسال شده','email,sms','trueارسال شده-1399/09/16 17:46:54%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
INSERT INTO `hmis_messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`,`messenger_attachFile`,`messenger_attachFileTitle`,`messenger_titleFile`,`messenger_file`,`messenger_textHTML`,`messenger_type`) VALUES 
 (1912,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990917',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/09/17 17:55:35%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1913,'ورود به سامانه',' ادمین ادمین  به سامانه وارد شدید','6','0','13990930',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/09/30 19:34:13%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1914,'ورود به سامانه',' محمد ثالثی  به سامانه وارد شدید','13','0','13991017',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/10/17 16:58:22%23A%23',NULL,NULL,NULL,NULL,'','امنیتی'),
 (1915,'ورود به سامانه',' aaaa aaaa  به سامانه وارد شدید','2','0','13991017',NULL,NULL,'ارسال شده','sms,email','ارسال شده-1399/10/17 16:59:53%23A%23',NULL,NULL,NULL,NULL,'','امنیتی');
/*!40000 ALTER TABLE `hmis_messenger` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_plans`
--

DROP TABLE IF EXISTS `hmis_plans`;
CREATE TABLE `hmis_plans` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `plans_managerRoleId` int(10) unsigned NOT NULL DEFAULT '0',
  `plans_commettesId` int(10) unsigned NOT NULL DEFAULT '0',
  `plans_improveQualityId` int(10) unsigned NOT NULL DEFAULT '0',
  `plans_typeOfProgram` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_creatorId` int(10) unsigned NOT NULL DEFAULT '0',
  `plans_supervisorRolId` int(10) unsigned NOT NULL DEFAULT '0',
  `plans_minorGoal` varchar(45) DEFAULT NULL,
  `plans_hugeGoal` varchar(45) DEFAULT NULL,
  `plans_strategic` varchar(45) DEFAULT NULL,
  `plans_range` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_causeProblem` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_method` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_titleOfTheProblem` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_period` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_domain` varchar(255) DEFAULT NULL,
  `plans_department` varchar(255) DEFAULT NULL,
  `plans_status` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_statusLog` longtext CHARACTER SET utf8mb4,
  `plans_description` longtext CHARACTER SET utf8mb4,
  `plans_date` int(10) unsigned NOT NULL DEFAULT '10000000',
  `plans_correction` varchar(250) CHARACTER SET utf8mb4 DEFAULT NULL,
  `plans_files` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_plans`
--

/*!40000 ALTER TABLE `hmis_plans` DISABLE KEYS */;
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (1,1,0,39,'برنامه عملیاتی','اجرای برنامه های ایمنی',6,40,'63','47','13','مدیریتی','','','','90','48','48','ثبت اولیه','ثبت اولیه:-1398/07/28User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,09:38:30%23A%23','',13980728,NULL,NULL),
 (2,1,0,41,'برنامه عملیاتی','اجرای برنامه های ایمنی',6,40,'112','47','61','مدیریتی','','','','90','80','80','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,00:18:10%23A%23','',13980729,NULL,NULL),
 (3,1,0,7,'برنامه عملیاتی','آموزش تخصصي كاركنان',6,40,'87','50','37','مدیریتی','','','','90','81','81','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,21:31:21%23A%23','',13980729,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (4,1,0,7,'برنامه عملیاتی','آموزش تخصصي كاركنان',6,40,'88','50','38','مدیریتی','','','','90','81','81','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,21:47:58%23A%23','',13980729,NULL,NULL),
 (5,1,0,7,'برنامه عملیاتی','آموزش تخصصی کارکنان',6,40,'85','50','35','مدیریتی','','','','90','81','81','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,21:52:22%23A%23','',13980729,NULL,NULL),
 (6,1,0,7,'برنامه عملیاتی','آموزش تخصصی كاركنان',6,40,'86','50','36','مدیریتی','','','','90','81','81','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,21:53:31%23A%23','',13980729,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (7,1,0,44,'برنامه عملیاتی','ارتقاء كیفیت خدمات درمانی ارایه شده در بخش با مدیریت بهینه فرآیندها',6,40,'51','46','2','مدیریتی','','','','90','83','83','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:06:54%23A%23','',13980729,NULL,NULL),
 (8,1,0,7,'برنامه عملیاتی','آموزش عمومی بیماران',6,40,'89','50','39','مدیریتی','','','','90','81','81','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:18:52%23A%23','',13980729,NULL,NULL),
 (9,1,0,8,'برنامه عملیاتی','ارتقا شاخص هاي ايمني بيمارستان',6,40,'67','47','17','مدیریتی','','','','90','84','84','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:21:33%23A%23','',13980729,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (10,1,0,46,'برنامه عملیاتی','ارﺗﻘﺎء ﻓﺮاﻳﻨﺪ ﺳﻼﻣﺖ ﻫﻤﮕﺎﻧﻲ',6,40,'91','50','41','مدیریتی','','','','90','85','85','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:33:54%23A%23','',13980729,NULL,NULL),
 (11,1,0,8,'برنامه عملیاتی','ارتقا شاخص هاي ايمني بيمار',6,40,'66','47','16','مدیریتی','','','','90','84','84','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:35:21%23A%23','',13980729,NULL,NULL),
 (12,1,0,50,'برنامه عملیاتی','مديريت خطاهاي ايمني',6,40,'69','47','19','مدیریتی','','','','90','80','80','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:47:34%23A%23','',13980729,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (13,1,0,50,'برنامه عملیاتی','مديريت خطاهاي ايمني',6,40,'70','47','20','مدیریتی','','','','90','80','80','ثبت اولیه','ثبت اولیه:-1398/07/29User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,23:54:24%23A%23','',13980729,NULL,NULL),
 (14,1,0,8,'برنامه عملیاتی','بهبود رعايت اصول پيشگيري و كنترل عفونت',6,40,'72','47','22','مدیریتی','','','','90','84','84','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:01:43%23A%23','',13980730,NULL,NULL),
 (15,1,0,49,'برنامه عملیاتی','ارتقا ارائه خدمات  پرستاری در منزل(home care)',6,40,'78','49','62','مدیریتی','','','','90','86','86','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:01:44%23A%23','',13980730,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (16,1,0,51,'برنامه عملیاتی','استفاده از خرد جمعی در رهبری و مدیریت بیمارستان',6,40,'60','46','10','مدیریتی','','','','90','87','87','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:08:15%23A%23','',13980730,NULL,NULL),
 (17,1,0,46,'برنامه عملیاتی','ارتقا ارائه خدمات پرستاری در منزل(home care)',6,40,'114','52','63','مدیریتی','','','','90','85','85','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:14:15%23A%23','',13980730,NULL,NULL),
 (18,1,0,8,'برنامه عملیاتی','بهبود رعايت اصول پيشگيري و كنترل عفونت',6,40,'71','47','21','مدیریتی','','','','90','84','84','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:21:39%23A%23','',13980730,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (19,1,0,11,'برنامه عملیاتی','بهبود رعايت اصول پيشگيري و كنترل عفونت',6,40,'115','46','64','مدیریتی','','','','90','53','53','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:29:25%23A%23','',13980730,NULL,NULL),
 (20,1,0,11,'برنامه عملیاتی','بي خطر سازي پسماند و پساب هاي بيمارستاني',6,40,'117','46','65','مدیریتی','','','','90','53','53','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,00:36:17%23A%23','',13980730,NULL,NULL),
 (21,1,0,55,'برنامه عملیاتی','ارتقا سلامت كاركنان',6,40,'92','51','44','مدیریتی','','','','90','82','82','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,19:57:18%23A%23','',13980730,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (22,1,0,51,'برنامه عملیاتی','استفاده از خرد جمعی در رهبری و مدیریت بیمارستان',6,40,'61','46','11','مدیریتی','','','','90','87','87','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,20:06:44%23A%23','',13980730,NULL,NULL),
 (23,1,0,55,'برنامه عملیاتی','ارتقا سلامت كاركنان',6,40,'92','51','42','مدیریتی','','','','90','82','82','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,20:13:32%23A%23','',13980730,NULL,NULL),
 (24,1,0,55,'برنامه عملیاتی','ارتقا سلامت كاركنان',6,40,'118','51','66','مدیریتی','','','','90','82','82','ثبت اولیه','ثبت اولیه:-1398/07/30User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,22:29:27%23A%23','',13980730,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (25,1,0,35,'برنامه عملیاتی','ارتقاء جانشین پروری',6,40,'95','51','45','مدیریتی','','','','90','78','78','ثبت اولیه','ثبت اولیه:-1398/08/01User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,00:58:36%23A%23','',13980801,NULL,NULL),
 (26,1,0,39,'برنامه عملیاتی','مدیریت سرمایه های بیمارستانی',6,40,'97','51','47','مدیریتی','','','','90','50','50','ثبت اولیه','ثبت اولیه:-1398/08/01User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,01:11:15%23A%23','',13980801,NULL,NULL),
 (27,1,0,39,'برنامه عملیاتی','مديريت سرمايه هاي بيمارستاني',6,40,'96','51','46','مدیریتی','','','','90','48','48','ثبت اولیه','ثبت اولیه:-1398/08/02User_Id= 6Role_Id= 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54,13:29:57%23A%23','',13980802,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (28,1,0,3,'برنامه عملیاتی','توسعه استاندارد هاي الزامي بيمارستان دوستدار ايمني',0,36,'65','47','15','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/28User_Id= 0Role_Id= 23:26:01%23A%23','',13980828,NULL,NULL),
 (29,1,0,3,'برنامه عملیاتی','ازتقا فرآیندهای ایمنی',6,36,'62','47','27','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:09:25%23A%23','',13980829,NULL,NULL),
 (30,1,0,3,'برنامه عملیاتی','بهبود رعایت اصول پیشگیری و كنترل عفونت',6,36,'115','46','64','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:28:35%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (31,1,0,3,'برنامه عملیاتی','ارتقاء فرآیند سنجش رضایتمندی',6,36,'79','49','28','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:30:26%23A%23','',13980829,NULL,NULL),
 (32,1,0,3,'برنامه عملیاتی','ارتقاء فرآیند سنجش رضایتمندی',6,36,'81','49','31','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:32:45%23A%23','',13980829,NULL,NULL),
 (33,1,0,3,'برنامه عملیاتی','ارتقاء فرآیند سنجش رضایتمندی',6,36,'78','49','31','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:34:43%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (34,1,0,3,'برنامه عملیاتی','ارتقاء فرآیند سنجش رضایتمندی',6,36,'80','49','29','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:36:28%23A%23','',13980829,NULL,NULL),
 (35,1,0,3,'برنامه عملیاتی','مدیریت طرح تکریم ارباب رجوع',6,36,'83','49','33','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:38:16%23A%23','',13980829,NULL,NULL),
 (36,1,0,3,'برنامه عملیاتی','مدیریت طرح تکریم ارباب رجوع',6,36,'84','49','34','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:39:37%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (37,1,0,3,'برنامه عملیاتی','افزایش سود بیمارستانی',6,36,'107','51','57','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:41:32%23A%23','',13980829,NULL,NULL),
 (38,1,0,3,'برنامه عملیاتی','افزایش سود بیمارستانی',6,36,'106','51','56','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:43:23%23A%23','',13980829,NULL,NULL),
 (39,1,0,3,'برنامه عملیاتی','افزایش سود بیمارستانی',6,36,'98','51','48','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:44:54%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (40,1,0,3,'برنامه عملیاتی','افزایش سود بیمارستانی',6,36,'101','51','51','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:46:24%23A%23','',13980829,NULL,NULL),
 (41,1,0,3,'برنامه عملیاتی','افزایش سود بیمارستانی',6,36,'105','51','55','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,00:48:05%23A%23','',13980829,NULL,NULL),
 (42,1,0,3,'برنامه عملیاتی','آموزش مباني ايمني',6,36,'120','47','67','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,01:02:38%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (43,1,0,3,'برنامه عملیاتی',' اجراي استانداردهاي اعتبار بخشي نسل چهارم',6,36,'52','46','1','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,01:46:19%23A%23','',13980829,NULL,NULL),
 (44,1,0,3,'برنامه عملیاتی','توسعه استاندارد هاي الزامي بيمارستان دوستدار ايمني',6,36,'64','47','14','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,01:48:16%23A%23','',13980829,NULL,NULL),
 (45,1,0,35,'برنامه عملیاتی','ارتقا شاخص هاي عملكردي مركز',6,36,'57','46','7','مدیریتی','','','','90','78','78','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,01:50:40%23A%23','',13980829,NULL,NULL);
INSERT INTO `hmis_plans` (`id`,`plans_managerRoleId`,`plans_commettesId`,`plans_improveQualityId`,`plans_typeOfProgram`,`plans_title`,`plans_creatorId`,`plans_supervisorRolId`,`plans_minorGoal`,`plans_hugeGoal`,`plans_strategic`,`plans_range`,`plans_causeProblem`,`plans_method`,`plans_titleOfTheProblem`,`plans_period`,`plans_domain`,`plans_department`,`plans_status`,`plans_statusLog`,`plans_description`,`plans_date`,`plans_correction`,`plans_files`) VALUES 
 (46,1,0,35,'برنامه عملیاتی','ارتقا شاخص های عملکردی مرکز',6,36,'58','46','8','مدیریتی','','','','90','78','78','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,01:53:32%23A%23','',13980829,NULL,NULL),
 (47,1,0,3,'برنامه عملیاتی','اجرای برنامه های ایمنی',6,36,'121','47','68','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,07:32:13%23A%23','',13980829,NULL,NULL),
 (48,1,0,3,'برنامه عملیاتی','اجرای برنامه های ایمنی',6,36,'122','47','69','مدیریتی','','','','90','1','1','ثبت اولیه','ثبت اولیه:-1398/08/29User_Id= 6Role_Id= 38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72,07:33:19%23A%23','',13980829,NULL,NULL);
/*!40000 ALTER TABLE `hmis_plans` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_plansforassess`
--

DROP TABLE IF EXISTS `hmis_plansforassess`;
CREATE TABLE `hmis_plansforassess` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_plansforassess`
--

/*!40000 ALTER TABLE `hmis_plansforassess` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_plansforassess` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_positions`
--

DROP TABLE IF EXISTS `hmis_positions`;
CREATE TABLE `hmis_positions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `positions_parent` int(11) NOT NULL DEFAULT '-1',
  `positions_level` varchar(45) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `positions_user_id` varchar(45) NOT NULL DEFAULT '',
  `positions_title` varchar(45) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `positions_name` varchar(45) NOT NULL DEFAULT '',
  `positions_family` varchar(45) NOT NULL DEFAULT '',
  `positions_child` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`positions_parent`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_positions`
--

/*!40000 ALTER TABLE `hmis_positions` DISABLE KEYS */;
INSERT INTO `hmis_positions` (`id`,`positions_parent`,`positions_level`,`positions_user_id`,`positions_title`,`positions_name`,`positions_family`,`positions_child`) VALUES 
 (1,0,'0','2','فرمانده حادثه','','','2'),
 (2,1,'1','2',' ارشد روابط عمومی','','',NULL),
 (3,1,'1','2','ارشد رابط هماهنگی','','',NULL),
 (4,1,'1','2','ارشد ایمنی','','',NULL),
 (5,1,'1','2','ارشد متخصص فنی پزشکی','','',NULL),
 (6,1,'1','2','رئیس واحد عملیات','','',NULL),
 (7,1,'1','2','رئیس برنامه ریزی','','',NULL),
 (8,1,'1','2','رئیس واحد پشتیبانی','','',NULL),
 (9,1,'1','2','رئیس واحد اداری مالی','','',NULL),
 (10,6,'2','2','مدیر شاخه خدمات پزشکی','','',NULL),
 (11,6,'3','2','مسئول زیرشاخه بیماران بستری','','',NULL),
 (12,6,'4','2','مسئول زیرشاخه بیماران سرپایی','','',NULL),
 (13,6,'5','2','مسئول زیرشاخه مراقبت از مصدومین','','',NULL),
 (14,6,'6','2','مسئول زیرشاخه بهداشت روان','','',NULL);
INSERT INTO `hmis_positions` (`id`,`positions_parent`,`positions_level`,`positions_user_id`,`positions_title`,`positions_name`,`positions_family`,`positions_child`) VALUES 
 (15,7,'2','2','مدیر شاخه ارزیابی وضعیت','','',NULL),
 (16,7,'3','2','مدیر شاخه ارزیابی منابع','','',NULL),
 (17,7,'4','2','مدیر شاخه بایگانی و ثبت اسناد','','',NULL),
 (18,7,'5','2','مدیر شاخه بازگشت به وضیعت عادی','','',NULL),
 (19,8,'2','2','مسئول زیرشاخه تامین تجهیزات','','',NULL),
 (20,8,'3','2','مسئول زیرشاخه فناوری اطلاعات','','',NULL),
 (21,8,'4','2','مسئول زیرشاخه حمل و نقل','','',NULL),
 (22,8,'5','2','مدیر زیرشاخه ارتباطات','','',NULL),
 (23,9,'2','','مدیر محاسبه هزینه ها','','',NULL),
 (24,9,'3','2','مدیر خسارات و مطالبات','','',NULL),
 (25,9,'4','2','مدیر تامین هزینه ها','','',NULL),
 (26,14,'4','3','ارشد متخصص فنی پزشکی','bbbb','bbbb',NULL);
INSERT INTO `hmis_positions` (`id`,`positions_parent`,`positions_level`,`positions_user_id`,`positions_title`,`positions_name`,`positions_family`,`positions_child`) VALUES 
 (27,1,'1','5','رئیس واحد اداری مالی','dddd','dddd',NULL);
/*!40000 ALTER TABLE `hmis_positions` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_proprietarytarget`
--

DROP TABLE IF EXISTS `hmis_proprietarytarget`;
CREATE TABLE `hmis_proprietarytarget` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `proprietaryTarget_strategicId` int(10) unsigned NOT NULL DEFAULT '0',
  `proprietaryTarget_totalTargetId` int(10) unsigned NOT NULL DEFAULT '0',
  `proprietaryTarget_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_proprietarytarget`
--

/*!40000 ALTER TABLE `hmis_proprietarytarget` DISABLE KEYS */;
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (51,6,46,'افزايش ميزان ارتقاء فرآيندهاي باليني از25  درصددر سال 97به  80درصد در سال 98'),
 (52,6,46,'اجراي 100 درصدي استانداردهاي ابلاغي وزارت بهداشت تا پايان سال 98'),
 (53,6,46,'كاهش كنسلي اعمال جراحي اتاق عمل از7 موردميانگين  سه ماهه در سال 97 به  4 مورد ميانگين سه ماهه تا پايان سال 98 ،'),
 (54,6,46,'كاهش مدت اقامت بيمار بخشهاي جنرال از 4 به 3 روزتا پايان سال 98.'),
 (55,6,46,'كاهش مدت اقامت بيمار روان از 15.4 به 14 تا پايان سال 98.'),
 (56,6,46,'كاهش مدت اقامت بيمار در بخش ويژه از 7 به  4 روز تا پايان سال 98.'),
 (57,6,46,'استمرار شاخص ميانگين مدت زمان تعيين تكليف بيمار در اورژانس زير 6 ساعت تا پايان سال 98.'),
 (58,6,46,'کاهش شاخص درصد ترك با مسئوليت شخصي در اورژانس  از 11 درصد به 8 درصد تاپايان سال98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (59,6,46,'افزایش درصد اشغال تخت روان از 85 درصد به 100 درصد تاپایان سال98'),
 (60,6,46,'ارتقا تشكيل اثربخش كميته هاي بيمارستاني از 45 درصد به 70 درصد تا پايان سال 98'),
 (61,6,46,'تشكيل 100 درصد كميته تشويق و تنبيه كاركنان به صورت فصلي در شش ماهه دوم سال 98'),
 (62,6,47,'برنامه ريزي جهت راه اندازي كلينيك زخم در بيمارستان امام حسين (ع) تا پايان سال 98'),
 (63,6,47,'افزايش اجراي برنامه كاليبراسيون تجهيزات پزشكي از 80 درصد تا 100 درصد تا پايان سال 98'),
 (64,6,47,'ارﺗﻘﺎء و استقرار استانداردهاي ايمني بيمار به از60 درصد وضع موجود به 80درصد تاپايان سال98'),
 (65,6,47,'ارﺗﻘﺎء اﺧﺬ رﺿﺎﻳﺖ آﮔﺎﻫﺎﻧﻪ در اﻧﺠﺎم ﭘﺮوﺳﻴﺠﺮﻫﺎى ﺗﻬﺎﺟﻤﻲ و ﻧﻴﻤﻪ ﺗﻬﺎﺟﻤﻲاز 60درصد وضع موجود تا 70 درصد تاپایان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (66,6,47,'كاهش درصد بروز زخم بستردر واحد هاي جنرال و ويژه از  4تا  2 تاپایان سال 98'),
 (67,6,47,'ارتقا شاخص اقدام به خودكشي تا صفر درصد تاپایان سال 98'),
 (68,6,47,'ارتقا شاخص سقوط از تخت تا صفر درصد تاپایان سال 98'),
 (69,6,47,' ارﺗﻘﺎء ﭘﻴﺸﮕﻴﺮى از ﺧﻄﺎﻫﺎى داروﻳﻲ تا 70 درصد تاپایان سال 98'),
 (70,6,47,'اﻳﺠﺎد و ارﺗﻘﺎء ﻓﺮﻫﻨﮓ ﮔﺰارش دﻫﻲ ﺧﻄﺎ ﺑﻪ ﻣﻨﻈﻮر ﺣﺬف ﻳﺎ ﭘﻴﺸﮕﻴﺮى از ﺗﻜﺮار ﻣﺠﺪد آن تا 80درصد تاپایان سال 98'),
 (71,6,47,'ارتقا فرهنگ گزارش دهي عفونت بيمارستاني بميزان 100 درصد تا پايان سال 98'),
 (72,6,47,'ارﺗﻘﺎء ﭘﻴﺸﮕﻴﺮى از ﻋﻔﻮﻧﺖ ﻫﺎى ﺑﻴﻤﺎرﺳﺘﺎﻧﻲ تا 90 درصد تاپایان سال 98'),
 (73,6,47,'ارتقا استانداردهاي  فضاي ايزوله بيمارستان از جمله ايزوله فشار منفي به ميزان 30 درصد وضع موجود تاپايان سال98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (74,6,47,'مديريت مواجهه شغلي كاركنان تا 20 درصد نسبت به وضع موجود تا پايان سال 98'),
 (75,6,47,'ارتقاءفرآیند مديريت پسماند  ( از مرحله جمع آوري ، جداسازي تا مرحله دفع و امحاي پسماند) از 50 درصد به 80 درصد تا پايان سال 98'),
 (76,6,47,'مديريت فاضلاب بيمارستاني در محدوده استاندارد'),
 (78,6,49,'افزايش رضايتمندي بيماران و همراهان از 78 درصد به80 درصد تاپايان سال 98'),
 (79,6,49,'اصلاح فرآيند سنجش اثربخشي رضايت مندي گيرندگان خدمت و كاركنان تا 80درصد تا پايان سال 98'),
 (80,6,49,'افزايش رضايتمندي كاركنان از 60 درصد به70 درصد تاپايان سال 98'),
 (81,6,49,'افزايش رضايتمندي بيماران از مراقبت هاي پرستاري از 75 درصد به 80 درصد تاپايان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (82,6,49,'افزايش تحقق محور هاي 5 گانه منشور حقوق بيمار30 درصد نسبت به وضع موجود تاپايان سال 98'),
 (83,6,49,'برنامه ريزي جهت افزايش مهارت هاي ارتباطي كاركنان در برخورد با ارباب رجوع تاپايان سال 98'),
 (84,6,49,'ارتقا نظام رسيدگي به شكايات و پيشنهادات از 60 درصد به 70 درصد تاپايان سال98'),
 (85,6,50,'برگزاري كلاس هاي علمي و عملي اثربخش درون بيمارستاني كادر درمان از 25 درصد تا 75درصد تا پايان سال '),
 (86,6,50,'برگزاري كلاس هاي علمي و عملي اثربخش درون بيمارستاني كادر غيردرمان از 0 درصد تا50 درصد تا پايان سال 98'),
 (87,6,50,'آموزش چهره به چهره از 0درصد به 50 درصد در كادر درمان تا پايان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (88,6,50,'آموزش چهره به چهره از 0درصد به 40 درصد در كادر غير درمان تا پايان سال 98'),
 (89,6,50,'ارتقا آموزش به بيماراز 40 تا 70 درصد پايان سال 98'),
 (90,6,50,'اجراي 100 درصد برنامه هاي ابلاغي دانشگاه در راستاي ارتقا سلامت همگاني تا پايان سال 98'),
 (91,6,50,'برگزاري كلاس هاي آموزش سلامت همگاني در كلينيك از 0 تا 40 درصد تا پايان سال 98'),
 (92,6,51,'ارتقاء فرایند معاينات دوره‌اي طب كار درشاغلين تا 80درصد تاپایان سال 98'),
 (93,6,51,'ارﺗﻘﺎء ﺳﻄﺢ ﺳﻼﻣﺖ ﻛﺎرﻛﻨﺎن ( از ﻧﻈﺮ ﻣﻌﺎﻳﻨﺎت دوره اى ﻃﺐ ﻛﺎر ، اﻧﺪازه ﮔﻴﺮى ﻋﻮاﻣﻞ زﻳﺎن آور ﻣﺤﻴﻂ ﻛﺎر ) تا 80درصد تاپایان سال 98'),
 (94,6,51,'ارتقاء سطح سلامت کارکنان با اندازه گیری عوامل ارگونومی محیط کارمتناسب با فعالیت بخش ها/ واحدها به میزان 90%تاپایان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (95,6,51,'توانمندسازي پرستاران بخش درپست هاي مديريتي  به ميزان 50% نسبت به سال97'),
 (96,6,51,'مديريت و نگهداشت تجهيزات بيمارستاني از 0 درصدتا 50 درصد تا پايان سال 98'),
 (97,6,51,'مديريت و نگهداشت تاسيسات بيمارستاني از 70 تا 90 درصد تا پايان سال 98'),
 (98,6,51,'افزايش درامد بيمه اي بيمارستان با اصلاح ماده 38 تامين اجتماعي تا پايان سال 98'),
 (99,6,51,'افزايش درامد واحد ازمايشگاه به ميزان 80 درصد تا نيم فصل  اول سال 98'),
 (100,6,51,'افزايش درامد واحد داروخانه به ميزان 80 درصد تا پايان نيم فصل اول سال 98'),
 (101,6,51,'كاهش هزينه مصرفي بخش هاي درماني به ميزان 30 درصد تا پايان سال 98'),
 (102,6,51,'مديريت و كاهش هزينه هاي تغذيه ضمن رعايت كميت و كيفيت غذا در بازه 150 ميليون تومان تا پايان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (103,6,51,'مديريت و كاهش هزينه هاي دارويي تا پايان سال 98'),
 (104,6,51,'كاهش تعداد نيروي رسمي – طرحي پزشك از 21 نفر به 12 نفرتا پايان سال 98 (از 86ميليون تومان به 56 ميليون تومان '),
 (105,6,51,'كاهش هزينه پرسنل مالي از ماهيانه 50 ميليون تومان به 30 ميليون تومان تا پايان سال 98'),
 (106,6,51,'افزايش درآمد غير عملياتي بيمارستان از 7 درصد به 17 درصد تا پايان سال 98'),
 (107,6,51,'افزايش درآمد عملياتي بيمارستان از 50 درصد به 80 درصد تا پايان سال 98 (از 2 ميليارد و 600 ميليون تومان به 4 ميليارد و 680 ميليون تومان )'),
 (108,6,51,'افزايش درامد بيمارستاني از طريق احداث بخش قلب و عروق و واحدهاي مرتبط تا پايان سال 98'),
 (109,6,51,'افزايش درامد بيمارستاني از طريق احداث واحد day care  تا پايان نيم فصل اول سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (110,6,51,'حذف خدمت چشم پزشكي و اضافه كردن خدمت دندان پزشكي تا پايان سال 98'),
 (111,6,48,'ارتقا نظام مديريت بحران در بيمارستان تا 70 درصد تاپایان سال 98'),
 (112,6,47,'اجراي 100درصد بازديد هاي مديريتي تا پايان سال 98'),
 (113,6,49,'توسعه ارائه خدمات پرستاري در منزل (HOME CARE) به ميزان 20 درصد  تا پايان سال 98'),
 (114,6,52,'برگزاري كميته home care به ميزان100درصد تا پايان سال 98'),
 (115,6,46,'مديريت مواجهه شغلي كاركنان تا 20 درصد تا پايان سال 98'),
 (117,6,46,'ارتقاءفرایند مديريت پسماند ( از مرحله جمع آوري ، جداسازي تا مرحله دفع و امحاي پسماند ) از 50 درصد به 80 درصد تا پايان سال 98'),
 (118,6,51,'ارتقاء سطح سلامت کارکنان با اندازه گیری عوامل زیان آور محیط کارتمامی بخش ها/ واحدهای پرخطر تا 80درصد تاپایان سال 98');
INSERT INTO `hmis_proprietarytarget` (`id`,`proprietaryTarget_strategicId`,`proprietaryTarget_totalTargetId`,`proprietaryTarget_title`) VALUES 
 (119,6,51,'راه اندازی بخش قلب و عروق و بخش های مرتبط پایان سال 98'),
 (120,6,47,'ارتقاء استقرار مديريت بحران در بيمارستان تا 70 درصد تاپایان سال 98'),
 (121,6,47,'برنامه ریزی جهت پيشگيری از بروز زخم های فشاری در بيماران مستعد به ميزان از 80 درصد تا 90 درصد تا پایان سال 98'),
 (122,6,47,'ارتقا اجراي برنامه خدمت دهي به كودك 1-59 ماهه 10 درصد نسبت به وضع موجود تا پايان سال 98');
/*!40000 ALTER TABLE `hmis_proprietarytarget` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_role`
--

DROP TABLE IF EXISTS `hmis_role`;
CREATE TABLE `hmis_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_title` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_user_id` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_date` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_condition` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_comment` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_discription` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_email` varchar(45) DEFAULT NULL,
  `role_family` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_role`
--

/*!40000 ALTER TABLE `hmis_role` DISABLE KEYS */;
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (1,'رئیس بیمارستان','8','13980701','1','-','-',NULL,NULL,''),
 (2,'مسئول اعتباربخشی','43','13980701','1','-','-',NULL,NULL,''),
 (3,'مسئول بهبود کیفیت','29','13970701','1','-','-',NULL,NULL,''),
 (4,'کارشناس تغذیه','40',NULL,'1','','',NULL,NULL,''),
 (5,'مسئول تاسیسات','39',NULL,'1','','',NULL,NULL,''),
 (6,'کارشناس تجهیزات پزشکی','37',NULL,'1','','',NULL,NULL,''),
 (7,'سوپروایزر آموزشی','35',NULL,'1','','',NULL,NULL,''),
 (8,'سوپروایزر کنترل عفونت','34',NULL,'1','','',NULL,NULL,''),
 (9,'مسئول ایمنی','34',NULL,'1','','',NULL,NULL,''),
 (10,'مسئول بهداشت حرفه ای','33',NULL,'1','','',NULL,NULL,''),
 (11,'مسئول بهداشت محیط','32',NULL,'1','','',NULL,NULL,''),
 (12,'مسئول خدمات','31',NULL,'1','','',NULL,NULL,''),
 (13,'مسئول تدارکات','30',NULL,'1','','',NULL,NULL,'');
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (14,'مسئول مددکاری','27',NULL,'1','','',NULL,NULL,''),
 (15,'مسئول رسیدگی به شکایات','28',NULL,'1','','',NULL,NULL,''),
 (17,'مسئول مدیریت اطلاعات سلامت','25',NULL,'1','','',NULL,NULL,''),
 (18,'مسئول درآمد','24',NULL,'1','','',NULL,NULL,''),
 (19,'مسئول منابع انسانی','23',NULL,'1','','',NULL,NULL,''),
 (21,'مسئول حراست','21',NULL,'1','','',NULL,NULL,''),
 (22,'مسئول فنی و سوپروایزر فیزیوتراپی','20',NULL,'1','','',NULL,NULL,''),
 (23,'مسئول فنی داروخانه','42',NULL,'1','','',NULL,NULL,''),
 (24,'ناظر دارویی','61',NULL,'1','','',NULL,NULL,''),
 (25,'سوپروایزر تصویربرداری','64',NULL,'1','','',NULL,NULL,''),
 (26,'مسئول فنی تصویربرداری','17',NULL,'1','','',NULL,NULL,''),
 (27,'سوپروایزر آزمایشگاه','16',NULL,'1','','',NULL,NULL,'');
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (28,'مسئول فنی آزمایشگاه','15',NULL,'1','','',NULL,NULL,''),
 (29,'مسئول کلینیک','14',NULL,'1','','',NULL,NULL,''),
 (30,'مسئول دفترمدیریت پرستاری','13',NULL,'1','','',NULL,NULL,''),
 (31,'مسئول دفترریاست و مدیریت','12',NULL,'1','','',NULL,NULL,''),
 (32,'مسئول دبیرخانه','12',NULL,'1','','',NULL,NULL,''),
 (33,'مسئول روابط عمومی','12',NULL,'1','','',NULL,NULL,''),
 (34,'مدیرفنی ','11',NULL,'1','','',NULL,NULL,''),
 (35,'مدیرپرستاری','10',NULL,'1','','',NULL,NULL,''),
 (36,'مدیر بیمارستان','9',NULL,'1','','',NULL,NULL,''),
 (37,'مدیر عامل شرکت فرسار','7',NULL,'1','','',NULL,NULL,''),
 (38,'مسئول کارگزینی','6',NULL,'1','','',NULL,NULL,NULL),
 (39,'مسئول تجهیزات پزشکی','36',NULL,'1','','',NULL,NULL,''),
 (40,'مدیرعامل','9',NULL,'1','','',NULL,NULL,'');
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (41,'کارشناس هماهنگ کننده ایمنی','34',NULL,'1','','',NULL,NULL,''),
 (42,'تیم ایمنی','6',NULL,'1','','',NULL,NULL,NULL),
 (43,'رابط مرگ ومير کودک 1-59 ماهه','6',NULL,'1','','',NULL,NULL,NULL),
 (44,'سرپرستاران واحدهای درمانی','6',NULL,'1','','',NULL,NULL,''),
 (45,'سرپرستار','10',NULL,'1','','',NULL,NULL,''),
 (46,'سوپروایزر ارتقا سلامت','6',NULL,'1','','',NULL,NULL,''),
 (47,'کارشناس زخم','6',NULL,'1','','',NULL,NULL,NULL),
 (48,'متخصص جراح','41',NULL,'1','','',NULL,NULL,''),
 (49,'سوپروایزر ارتقا سلامت','35',NULL,'1','','',NULL,NULL,''),
 (50,'کارشناس ایمنی بیمار','6',NULL,'1','','',NULL,NULL,NULL),
 (51,'مسئول كمیته ها','6',NULL,'1','','',NULL,NULL,''),
 (52,'ریاست','8',NULL,'1','','',NULL,NULL,'');
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (53,'پرستار کنترل عفونت','6',NULL,'1','','',NULL,NULL,''),
 (54,'کارشناس بهداشت محیط','32',NULL,'1','','',NULL,NULL,''),
 (55,'مسئول واحد حفاظت فنی و بهداشت کار','6',NULL,'1','','',NULL,NULL,''),
 (56,'کارشناس بهداشت حرفه ای','33',NULL,'1','','',NULL,NULL,''),
 (57,'مسئول مهندسی پزشکی','6',NULL,'1','','',NULL,NULL,''),
 (58,'معاون درمان','6',NULL,'1','','',NULL,NULL,''),
 (59,'مسئول كمیته ها','29',NULL,'1','','',NULL,NULL,''),
 (60,'رابط اعتباربخشی  بین حوزه هاي مختلف','6',NULL,'1','','',NULL,NULL,NULL),
 (61,'مسئول حقوق گیرنده خدمت','28',NULL,'1','','',NULL,NULL,''),
 (62,'مسئول آمار','6',NULL,'1','','',NULL,NULL,NULL),
 (63,'مسئول پشتیبانی','6',NULL,'1','','',NULL,NULL,NULL),
 (64,'پزشک قانونی','6',NULL,'1','','',NULL,NULL,NULL);
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (65,'سرپرستار اورژانس','63',NULL,'1','','',NULL,NULL,''),
 (66,'مسئول آتش نشانی','6',NULL,'1','','',NULL,NULL,NULL),
 (67,'مسئول ساختمان','6',NULL,'1','','',NULL,NULL,NULL),
 (68,'مسئول مالی','22',NULL,'1','','',NULL,NULL,''),
 (69,'مسئول اسناد مرگ و میر','41',NULL,'1','','',NULL,NULL,''),
 (70,'مسئول کمیته مرگ کودکان 1 تا 59 ماه','45',NULL,'1','','',NULL,NULL,''),
 (71,'سوپروایزر بالینی','50',NULL,'1','','',NULL,NULL,''),
 (72,'رابط بحران','6',NULL,'1','','',NULL,NULL,NULL),
 (73,'مسئول امور اداری','23',NULL,'1','','',NULL,NULL,''),
 (74,'مسئول فناوري اطلاعات  (IT)','26',NULL,'1','','',NULL,NULL,''),
 (75,'کاردان مدارک پزشکی','48',NULL,'1','','',NULL,NULL,''),
 (76,'مسئول انبار','47',NULL,'1','','',NULL,NULL,'');
INSERT INTO `hmis_role` (`id`,`role_title`,`role_user_id`,`role_date`,`role_condition`,`role_comment`,`role_discription`,`role_email`,`role_family`,`role_name`) VALUES 
 (77,'مسئول بایگانی','49',NULL,'1','','',NULL,NULL,''),
 (78,'سرپرستار اتاق  عمل','62',NULL,'1','','',NULL,NULL,''),
 (80,'پرستاراورژانس1','52',NULL,'1','','',NULL,NULL,''),
 (81,'پرستاراورژانس2','51',NULL,'1','','',NULL,NULL,''),
 (82,'مسئول تریاژ','54',NULL,'1','','',NULL,NULL,NULL),
 (83,'متخصص داخلی','57',NULL,'1','','',NULL,NULL,''),
 (84,'جراح','56',NULL,'1','','',NULL,NULL,NULL),
 (85,'مسئول تجهیزات پزشکی 2','38',NULL,'1','','',NULL,NULL,NULL),
 (86,'مسئول درمانگاه','60',NULL,'1','','',NULL,NULL,'');
/*!40000 ALTER TABLE `hmis_role` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_sessions`
--

DROP TABLE IF EXISTS `hmis_sessions`;
CREATE TABLE `hmis_sessions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sessions_creatorId` int(11) NOT NULL DEFAULT '0',
  `sessions_commettesId` varchar(255) DEFAULT NULL,
  `sessions_communicatorRoleId` int(10) unsigned DEFAULT NULL,
  `sessions_title` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_contextInvitation` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_agenda` longtext CHARACTER SET utf8mb4,
  `sessions_date` int(10) unsigned NOT NULL DEFAULT '10000000',
  `sessions_time` varchar(45) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL,
  `sessions_timeReminder` varchar(45) DEFAULT NULL,
  `sessions_Invitees` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_InviteesOutSide` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_file` varchar(255) DEFAULT NULL,
  `sessions_InviteesFile` varchar(255) DEFAULT NULL,
  `sessions_status` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_statusLog` longtext CHARACTER SET utf8mb4,
  `sessions_nextSessionDate` int(10) unsigned NOT NULL DEFAULT '10000000',
  `sessions_otherDescription` longtext CHARACTER SET utf8mb4,
  `sessions_sessionDescription` longtext CHARACTER SET utf8mb4,
  `sessions_InviteesInSide` varchar(255) DEFAULT NULL,
  `sessions_invitationDate` int(10) unsigned NOT NULL DEFAULT '100000000',
  `sessions_audience` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_audienceInSide` varchar(255) DEFAULT NULL,
  `sessions_audienceOutSide` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_absentRole` varchar(255) DEFAULT NULL,
  `sessions_absentUserInSide` varchar(255) DEFAULT NULL,
  `sessions_absentOutSide` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sessions_signers` varchar(255) DEFAULT NULL,
  `sessions_signersUserInSide` varchar(255) DEFAULT NULL,
  `sessions_filePdfSessions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_sessions`
--

/*!40000 ALTER TABLE `hmis_sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_sessions` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_setting`
--

DROP TABLE IF EXISTS `hmis_setting`;
CREATE TABLE `hmis_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `setting_name` varchar(255) DEFAULT NULL,
  `setting_value` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_setting`
--

/*!40000 ALTER TABLE `hmis_setting` DISABLE KEYS */;
INSERT INTO `hmis_setting` (`id`,`setting_name`,`setting_value`) VALUES 
 (10,'reminder Days Before Steps',20),
 (11,'reminder Day',15);
/*!40000 ALTER TABLE `hmis_setting` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_settingautosms`
--

DROP TABLE IF EXISTS `hmis_settingautosms`;
CREATE TABLE `hmis_settingautosms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `settingAutoSms_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `settingAutoSms_isActive` int(10) unsigned DEFAULT NULL,
  `settingAutoSms_smsTitle` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_settingautosms`
--

/*!40000 ALTER TABLE `hmis_settingautosms` DISABLE KEYS */;
INSERT INTO `hmis_settingautosms` (`id`,`settingAutoSms_title`,`settingAutoSms_isActive`,`settingAutoSms_smsTitle`) VALUES 
 (11,'ارسال صورتجلسه به مسئول ابلاغ',1,'dccdc'),
 (13,'دعوتنامه کمیته',1,'لطفا در کمیته حضور یابید'),
 (14,'ارسال برنامه عملیاتی به مافوق',1,''),
 (15,'ابلاغ مصوبات صورتجلسه',1,''),
 (17,'ارسال برنامه عملیاتی به مسئول بهبود کیفیت',1,''),
 (18,'ارسال برنامه عملیاتی به مدیر',1,''),
 (19,'ابلاغ گام های برنامه عملیاتی',1,''),
 (20,'ارسال برنامه عملیاتی به کمیته',1,''),
 (21,'درخواست ویرایش برنامه عملیاتی',1,''),
 (22,'ارسال فرم پیشنهادی به کمیته',1,'');
/*!40000 ALTER TABLE `hmis_settingautosms` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_steps`
--

DROP TABLE IF EXISTS `hmis_steps`;
CREATE TABLE `hmis_steps` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `steps_isActive` int(10) unsigned NOT NULL DEFAULT '0',
  `steps_plansId` int(10) unsigned NOT NULL DEFAULT '0',
  `steps_title` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `steps_executorUserId` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `steps_executorRoleId` varchar(100) DEFAULT NULL,
  `steps_trackerId` int(10) unsigned NOT NULL DEFAULT '0',
  `steps_otherIndicators` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `steps_startDate` int(10) unsigned NOT NULL DEFAULT '10000000',
  `steps_endDate` int(10) unsigned NOT NULL DEFAULT '10000000',
  `steps_cost` varchar(20) DEFAULT NULL,
  `steps_files` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `steps_status` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `steps_statusLog` longtext CHARACTER SET utf8mb4,
  `steps_percent` varchar(45) DEFAULT NULL,
  `steps_descriptionTracker` longtext CHARACTER SET utf8mb4,
  `steps_descriptionExecutor` longtext CHARACTER SET utf8mb4,
  `steps_progressPercent` int(10) unsigned NOT NULL DEFAULT '0',
  `steps_trackerFiles` varchar(200) DEFAULT NULL,
  `steps_executorFiles` varchar(200) DEFAULT NULL,
  `steps_percentExecutor` varchar(45) DEFAULT NULL,
  `steps_percentTracker` varchar(45) DEFAULT NULL,
  `steps_description` longtext CHARACTER SET utf8mb4,
  `steps_descriptionImproveQuality` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_steps`
--

/*!40000 ALTER TABLE `hmis_steps` DISABLE KEYS */;
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (1,0,1,'تنظیم فهرست تجهیزات مشمول ازمون كالیبراسیون و كنترل كیفی','','39',39,'درصد تجهيزات كاليبره شده',13980301,13980330,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 09:41:07%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (2,0,1,'استعلام شركت های دارای مجوز','','39',39,'درصد تجهيزات كاليبره شده',13980301,13980330,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:21:50%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (3,0,1,'اخذ پیش فاكتور و كارشناسی های مربوطه بر اساس اسكوپ كاری در شركت','','39',39,'درصد تجهيزات كاليبره شده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:24:27%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (4,0,1,'تعیین برنامه زمان بندی انجام ازمون و پیش بینی زمان بندی اقدامات اصلاحی مورد نیاز','','39',39,'درصد تجهيزات كاليبره شده',13980330,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:27:26%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (5,0,1,'انجام ازمون كنترل كیفی','','39',39,'درصد تجهيزات كاليبره شده',13980330,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:29:28%23A%23','80.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (6,0,1,'تحلیل گزارشات اولیه كنترل كیفی و برنامه ریزی و انجام اقدامات اصلاحی در دستگاه های مشروط و مربوط','','39',39,'درصد تجهيزات كاليبره شده',13980330,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:31:52%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (7,0,1,'انجام ازمون مجدد برای دستگاه های مشروط و مربوط تا رسیدن به برچسب سبز برای هر دستگاه','','39',39,'درصد تجهيزات كاليبره شده',13980330,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/28/ user_id=0 / role_id= 13:33:35%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (8,0,2,'تشكیل تیم بازدید مدیریتی','','1',41,'تعداد بازدید های ایمنی',13980101,13980120,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:19:10%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (9,0,2,'تهيه تقويم زماني بازديد مديريتي','','41',41,'تعداد بازديد هاي ايمني',13980101,13980120,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:22:11%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (10,0,2,'ارسال برنامه به كليه بخشهاي موردبازديد واعضاي تيم بازديدكننده','','41',41,'تعداد بازديد هاي ايمني',13980120,13980131,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:23:05%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (11,0,2,'تاكيد مجددبه  سرپرستار ومسئول واحد مورد بازديد يك روز قبل جهت حضور تيم','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:24:21%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (12,0,2,'تاكيد مجدد به اعضاء تيم بازديد كننده جهت  زمان شروع بازديد','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:25:03%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (13,0,2,'حضوردر واحد ارزيابي','','42',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:26:36%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (14,0,2,'ارزیابی وتكميل چك ليست','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:27:23%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (15,0,2,'تعیین عوامل خطر ساز در مقوله ایمنی و موارد ارتقاء یافته نسبت به بازدید قبلی ','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:28:03%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (16,0,2,'تهيه گزارش بازديد با برنامه عملياتي تعيين زمان و تقسيم كار توسط هماهنگ كننده ايمني','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:28:42%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (17,0,2,'تحليل بازديد ها در كميته ايمني','','41',41,'تعداد بازديد هاي ايمني',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38, 00:29:07%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (18,0,6,'ابلاغ رابطين اموزشي بخشها ','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 21:56:27%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (19,0,6,'انجام نيازسنجي آموزشي','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 21:57:41%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (20,0,6,'تدوين تقويم آموزشي','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 21:58:44%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (21,0,6,'برگزاري كلاسهاي آموزشي درون مركزي','','7',7,'',13980130,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:01:52%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (22,0,6,'هماهنگي و نظارت بر شركت در كلاسهاي برون مركزي','','7',7,'',13980130,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:03:49%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (23,0,6,'سنجش اثربخشي كلاسهاي آموزشي درون مركزي','','7',7,'',13980130,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:04:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (24,0,6,'برنامه ريزي جهت آموزش مجدد افراد با اثربخشي كمتر از 75 درصد','','7',7,'',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:04:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (25,0,5,'ابلاغ رابطين اموزشي بخشها ','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:19:42%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (26,0,5,'انجام نيازسنجي آموزشي','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:20:21%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (27,0,5,'تدوين تقويم آموزشي','','7',7,'مستند',13980130,13980230,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 22:21:03%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (28,0,5,'برگزاري كلاسهاي آموزشي درون مركزي','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:03:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (29,0,5,'هماهنگي و نظارت بر شركت در كلاسهاي برون مركزي','','7',7,'مصاحبه',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:05:31%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (30,0,5,'سنجش اثربخشي كلاسهاي آموزشي درون مركزي','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:06:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (31,0,5,'برنامه ريزي جهت آموزش مجدد افراد با اثربخشي كمتر از 75 درصد','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:06:58%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (32,0,4,'تعيين موارد آموزشي ضروري چهره به چهره در بخش ها','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:09:21%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (33,0,4,'آموزش موارد ضروري به پرسنل توسط سرپرستار','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:09:52%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (34,0,4,'سنجش اثربخشي آموزش ها','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:10:32%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (35,0,4,'آموزش مجدد براي افراد با نمره كمتر از 75','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:10:56%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (36,0,4,'انجام چك راند رندومي فصلي','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:11:26%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (37,0,4,'اخذ اقدام اصلاحي / برنامه بهبود كيفيت','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:12:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (38,0,4,'گزارش موارد اصلاحي به سرپرستار واحد','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:12:39%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (39,0,3,'تعيين موارد آموزشي ضروري چهره به چهره در بخش ها','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:13:59%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (40,0,3,'آموزش موارد ضروري به پرسنل توسط سرپرستار','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:14:38%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (41,0,3,'سنجش اثربخشي آموزش ها','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:15:07%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (42,0,3,'آموزش مجدد براي افراد با نمره كمتر از 75','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:15:39%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (43,0,3,'انجام چك راند رندومي فصلي','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:16:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (44,0,7,'تشكیل تیم ارزیابی دربخش وصدور ابلاغ جهت كلیه اعضای تیم ارزیابی','','45',44,' مستندات',13980101,13980131,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:16:16%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (45,0,3,'اخذ اقدام اصلاحي / برنامه بهبود كيفيت','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:16:37%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (46,0,3,'گزارش موارد اصلاحي به سرپرستار واحد','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:17:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (47,0,7,'برنامه ریزی طبق جدول تقسیمی میان اعضای تیم ارزیابی جهت ممیزی بالینی فرآیندهای كلیدی بخش هر سه ماه یكبار','','45',44,'مشاهده مستندات',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:17:16%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (48,0,7,'برنامه ریزی جهت مقایسه وضعیت موجود با استانداردها','','45',44,'مشاهده',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:18:29%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (49,0,8,'چك راند كليه پرونده هاي بستري توسط سرپرستاران','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:19:22%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (50,0,8,'سنجش عملكرد پرسنل پرستاري در اموزش بيمار','','7',7,'مصاحبه رندوم',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:19:55%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (51,0,8,'اخذ اقدام اصلاحي / برنامه بهبود كيفيت','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:20:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (52,0,8,'سنجش مستمر اثربخشي اقدامات اصلاحي قبل','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:20:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (53,0,8,'گزارش موارد اصلاحي به مديريت پرستاري','','7',7,'مستند',13980230,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:21:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (54,0,9,'برنامه ریزی جهت نبروی همگن برای بازرسی بدنی','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:22:30%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (55,0,7,'انجام مداخلات اصلاحي در خصوص ارزيابيهاي صورت گرفته دربخش','','45',44,'مشاهده',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:23:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (56,0,9,'قفسه بندي و كمدبندي قسمت بيروني واحد هاي روان جهت گرفتن وسايل همراه بيماران در زمان ملاقات','','8',8,'مشاهده',1398120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:30:12%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (57,0,9,'تبديل ظروف غذا به ظروف يكبار مصرف ايمن','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:30:44%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (58,0,9,'نصب توري و حفاظ در كليه پنجره ها','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:31:11%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (59,0,9,'بازديد دوره اي بخش هاي روان ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:31:42%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (60,0,9,'انجام مشاوره هاي روانشناسي بر اي بيماران داراي سابقه خودكشي','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:32:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (61,0,10,'نياز سنجي موارد آموزش همگاني','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:34:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (62,0,10,'برگزاری كلاسهای اموزشی جهت بیمار و همراه','','48',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:35:13%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (63,0,11,'راه اندازی واحد زخم به صورت مرکزی برای تمام بخش ها','','47',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:35:52%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (64,0,11,'تأمین زیر ساخت لازم برای اقدامات پیشگیرانه و مراقبت از پوست ( تهیه تشک مواج ، بالشتک های تغییر پوزیشن و ... )','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:36:21%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (65,0,11,'ارتقاء آگاهی کارکنان در زمینه مراقبت از پوست و پیشگیری از بروز زخم با برگزاری کلاس های آموزشی','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:36:44%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (66,0,11,'ابلاغ دستورالعمل برادن','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:37:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (67,0,10,'برگزاري كلاسهاي اموزشي مومي در كلينيك توسط افراد متخصص','','48',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:37:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (68,0,11,'استفاده از روش مدل ساعت برای تغییر وضعیت 2 ساعته بیماران به منظور پیشگیری از بروز زخم بستر','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:37:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (69,0,11,'استفاده از روش مدل ساعت برای تغییر وضعیت 2 ساعته بیماران به منظور پیشگیری از بروز زخم بستر','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:38:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (70,0,11,'نظارت بر اجرای اقدامات مراقبت از پوست و پیشگیری از زخم بستر','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:38:36%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (71,0,11,'ارسال آمار زخم بستر بيماران به صورت ماهيانه','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:39:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (72,0,11,'ارزيابي آمار زخم بستر بخش ها به صورت درصد و نمودار','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:39:35%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (73,0,11,'ارسال نمودار و درصد شاخص هاي ايمني بيمار به دانشگاه ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:40:00%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (74,0,11,'پایش و تحلیل میزان بروز زخم بستر و اقدامات اصلاحی با هدف کاهش شاخص زخم بستر تا رسیدن به وضعیت ایده آل ( عدم  بروز زخم بستر در بیمارستان )','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:40:24%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (75,0,12,'تدوین اصول  8Right و فرایند دارو دهی ','','50',50,'فرم خطا',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:48:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (76,0,12,'تدوین لیست داروهای مشابه  و نمونه دارو های مشابه','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:48:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (77,0,12,'تدوین لیست داروهای پرخطر براساس آخرین دستورالعمل دانشگاه','','50',50,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:49:12%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (78,0,12,'تهیه برچسب داروهای پرخطر با هشدار بالا جهت الصاق برروی باکس یا قفسه دارویی','','50',50,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:49:39%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (79,0,12,'نظارت بر تهیه لبیل نواری برای 12 داروی پرخطر ، داروهای مشابه و یخچالی به تفکیک رنگ قرمز ، زرد و آبی ','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:50:05%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (80,0,12,'مشخص نمودن داروهای یخچالی با هماهنگی مسئول فنی داروخانه','','50',50,'مستندات',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:50:32%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (81,0,12,'تهیه باکس قرمز رنگ جهت جداسازی داروی کلراید پتاسیم','','50',50,'مستندات',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:50:57%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (82,0,12,'الصاق برچسب نواری بر روی داروهای یخچالی ، مشابه ، 12 داروی پر خطر از داروخانه','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:51:22%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (83,0,12,'الصاق برچسب دارو با هشدار بالا بر روی باکس ها ، ترالی کد ، قفسه ها در بخش ها و داروخانه و انبار دارویی','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:51:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (84,0,12,'اجرای دستورالعمل چک مستقل دوگانه دارو با هشدار های بالا طبق چک لیست','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:52:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (85,0,12,'انجام بازدیدهای میدانی جهت رعایت و اجرایی شدن دستورالعمل داروهای با هشدار بالا','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:52:42%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (86,0,13,'تدوین فرم گزارش دهی خطا  ','','50',50,'فرم خطا',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:54:57%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (87,0,13,'ارائه فرم خطا به بخشها / واحدهای مرتبط و آموزش به پرسنل جهت گزارش دهی خطاهای پزشکی','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:55:32%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (88,0,13,'هماهنگی جهت تهیه و تعبیه صندوق خطا در بخش ها / پاراکلینیک جهت گزارش دهی خطای دواطلبانه','','50',50,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:56:02%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (89,0,13,'ثبت سوابق خطاهای پزشکی در سامانه','','50',50,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:56:35%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (90,0,13,'شناسایی عوامل تهدید کننده ایمنی بیماربا راهکارهای پیشگیرانه   و الویت بندی و تایید توسط مسئول ایمنی','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:57:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (91,0,13,'برگزاری کلاس آموزشی عوامل تهدید کننده ایمنی بیمار برای کادر درمانی','','50',50,'مستندات',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:57:39%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (92,0,13,'برگزاری کلاس آموزشی خطاهای پزشکی و فرهنگ خود اظهاری خطا','','50',50,'مستندات',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:58:08%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (93,0,13,'تدوین روش اجرایی ارزیابی پیشگیرانه خطاهای پزشکی و  نحوه گزارش دهی همگانی خطا ','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:58:33%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (94,0,13,'بررسي خطاها گزارش شده جهت تحليل بر اساس ميزان الويت و اهميت به روش RCA','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:58:55%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (95,0,13,'مطرح نمودن خطاهای RCA  شده در کمیته مربوطه جهت تدوین اقدام اصلاحی ','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:59:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (96,0,13,'بازخوراند به بخشهاي مربوطه براي يادگيري مشترك در جهت حذف يا كاهش خطا و آموزش درسنامه ایمنی بیمار','','50',50,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/29/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 23:59:41%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (97,0,14,'تهيه استيكر رعايت بهداشت دست ','','8',8,'مستنداستیکر',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:02:15%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (98,0,15,'تشكيل كارگروه   Home Care','','49',49,'مستند و مصاحبه',13980101,13980429,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:02:26%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (99,0,14,'نصب استيكرها در بخش ها و جاهاي مناسب ','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:02:41%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (100,0,14,'در دسترس بودن دستورالعمل بهداشت دست ','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:03:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (101,0,15,'تعيين رابطين Home careو ابلاغ آن به بخش ها','','49',49,'مستند و مصاحبه',13980101,13980429,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:03:08%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (102,0,14,'تدوین خط مشی و روش اجرایی رعایت اصول بهداشت دست','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:03:34%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (103,0,15,'جلسه با رابطين بخش ها هردو ماه يكبار','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:03:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (104,0,14,'آموزش بهداشت دست در تمامی بخش ها/ واحدهای درمانی','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:04:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (105,0,15,'تهيه كتابچه آموزش خود مراقبتي ،فلوچارت home care،تعرفه ها و مراكز طرف قرارداد و پمفلت در بخش هاي درماني','','49',49,'مشاهده',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:04:23%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (106,0,14,'ارزیابی بخش ها / واحدهای درمانی از نظر تجهیزات مورد نیاز بهداشت دست ( محلول ضد عفونی کننده ، حوله یکبار مصرف ، راهنمای های بهداشت دست ) ','','8',8,'مستند چک لیست',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:04:44%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (107,0,15,'برنامه ريزي و زمان بندي كلاس هاي اموزشي جهت ارتقا سطح دانش پرسنل پرستاري','','49',49,'مشاهده و مصاحبه',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:04:55%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (108,0,14,'ارزیابی میزان رعایت بهداشت دست در تمام بخش ها / واحدهای درمانی در همه شیفتها  طبق چک لیست در 5 موقعیت ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:05:07%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (109,0,14,'ارزیابی اسکراب دست پرسنل اتاق عمل قبل از اعمال جراحی ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:05:31%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (110,0,15,'برگزاري كلاس آموزشي توانمند سازي پرسنل پرستاري','','49',49,'مشاهده و مصاحبه',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:05:33%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (111,0,15,'ارزيابي اثربخشي كلاس هاي آموزشي توانمندسازي ','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:05:59%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (112,0,15,'تعيين و تعريف پروتكل هاي مربوط به نحوه شناسايي بيماران نيازمند home care  در بخش هاي درماني طبق نظر سوپروايزر','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:06:23%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (113,0,15,'شناسايي بيماران نيازمند home carطبق پروتكل شناسايي شده','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:06:52%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (114,0,15,'ارجاع بيماران به واحد home care  و سپس مراكز ارائه دهنده خدمات در منزل','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:07:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (115,0,15,'ارزيابي ميزان رضايت بيماران از خدمات ارائه شده توسط مراكز و ارائه آمار','','49',49,'',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:07:42%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (116,0,15,'ارائه اموزش هاي پس از ترخيص و   flow up بيماران','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:08:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (117,0,15,'شناسايي مشكلات موجود و ارجاع موارد به پزشكان مربوطه ','','49',49,'مستند و مصاحبه',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:08:38%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (118,0,15,'ارزيابي ميزان اثربخشي آموزش ها و مداخلات انجام شده','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:09:01%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (119,0,15,'ارزيابي تاثير مداخلات و آموزش هاي انجام شده بر ميزان رضايتمندي بيماران','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:09:27%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (120,0,15,'افزايش سطح آگاهي همراهان و نزديكان درجه 1 بيمار پس از اخذ رضايت نامه از بيمار خصوصا در بيمار ي هاي واگير','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:09:53%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (121,0,15,'ارسال گزارشات به صورت فصلي ','','49',49,'مستند',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:10:17%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (122,0,17,'شركت سوپروایزر ارتقا سلامت در كلاس ها همایش ها و جلسات كارگروه دانشگاه','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:14:44%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (123,0,17,'تنظيم دستور كار و آئين نامه كمينه home care','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:15:36%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (124,0,17,'ارائه آمار ها مشكلات و مداخلات انجام شده در كميته  home care','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:16:20%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (125,0,17,'پيگيري مصوبات جلسه و ارائه گزارش به مديران اجرايي','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:16:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (126,0,17,'ارائه آمار و دستورالعمل هاي تصويب شده در كميته هدايت پرستاري ','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:17:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (127,0,17,'بررسي وتشويق بخش هاي درماني فعال در زمينه ارجاع بيماران نيازمند home care','','46',46,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:17:37%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (128,0,18,'انتخاب رابطین کنترل عفونت بخش ها و ارائه ابلاغ ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:22:12%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (129,0,18,'برگزاری برنامه های جلسات آموزشی در رابطه با شناسائی عفونت های بیمارستانی و تکمیل فرم های مربوطه ','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:22:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (130,0,18,'بازديد از بخش هاي بستري و مطابقت صحت گزارش دهی موارد  ','','8',8,'مشاهده',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:23:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (131,0,18,'مطابقت نتايج کشت هاي بیماران بستري با گزارشدهی عفونت ها','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:23:38%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (132,0,18,'طرح موارد عفونتهاي بیمارستانی در کمیته کنترل عفونت  و ارائه راهکارهای کاهش و کنترل عفونت ها','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:24:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (133,0,18,'مقایسه آماری سه ماهه در سامانه ثبت عفونت ها','','8',8,'مستند',13980120,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:24:26%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (134,0,20,'آموزش به کلیه پرسنل خدماتی جهت  جمع آوری ، تفكیك پسماندها','','54',11,'مستند آموزش',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:42:30%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (135,0,20,'آموزش به کلیه پرسنل درمانی جهت  نظارت بر كار خدمات در  اصول مدیریت پسماند','','54',11,'مستند آموزش',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:43:32%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (136,0,20,'تدوین و تهیه فهرست انواع پسماندها جهت','','54',11,'مستند فهرست',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:44:07%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (137,0,20,'ارائه فهرست تدوین شده به بخش ها / واحدها','','54',11,'مشاهده فهرست',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:44:40%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (138,0,20,'تدوین دستورالعمل پسماندها','','54',11,'مستند دستورالعمل',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:45:12%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (139,0,20,'تهيه كانتير يا چرخ دستي جهت جمع آوري و حمل ونقل بهداشتي پسماندها','','54',11,'مشاهده کانتینر',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:47:04%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (140,0,20,'پی گیری انعقاد قرارداد با سازمان مدیریت پسماند جهت جمع آوری و دفع پسماندها توسط ریاست و مدیر مالی','','54',11,'مستند قرارداد',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:47:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (141,0,20,'طرح در کمیته بهداشت و محیط جهت تعبیه اتاقک نگهداری پسماند و دستگاه امحای پسماند','','54',11,'صورت جلسه کمیته',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:48:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (142,0,20,'طرح در تیم حاکمیتی جهت تامین بودجه تعبیه اتاقک نگهداری پسماند ها ','','54',11,'صورت جلسه کمیته',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:48:55%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (143,0,20,'انجام اقدامات لازم جهت تعبيه  اتاقك نگهداري پسماندها پس از تصویب تیم حاکمیتی','','54',11,'مستند اقدامات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:49:31%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (144,0,20,'تعبیه اتاقک نگهداری پسماند توسط مسئول مربوطه','','54',11,'مشاهده اتاقک',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:50:01%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (145,0,20,'انجام مکاتبات با دانشگاه جهت تهیه دستگاه امحای پسماند ','','54',11,'مستند مکاتبات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43, 00:50:29%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (146,0,16,'باز نگری ابلاغات دبیر و اعضای كمیته ها','','51',51,'مستندات',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:14:44%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (147,0,16,'ابلاغ آئين نامه  كميته هاي ابلاغي معاونت درمان به دبيران','','52,51',51,'مستندات',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:25:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (148,0,16,'ابلاغ اسناد مرتبط كميته به دبيران ','','52,51',51,'مستندات',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:27:25%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (149,0,16,'ابلاغ تقويم تدوين شده توسط مديران اجرايي','','52,51',51,'مستندات',13980120,13980130,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:41:54%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (150,0,16,'نظارت بر تشكيل ماهانه كميته ها ','','51',51,'مستندات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:44:24%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (151,0,16,'نظارت بر انجام مصوبات كميته مديران اجرايي','','51',51,'مستندات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:45:55%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (152,0,16,'پايش كميته هاي بيمارستاني به صورت فصلي','','51',51,'مستندات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:51:24%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (153,0,16,'سنجش اثربخشي كميته هاي بيمارستاني','','51',51,'مستندات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:53:43%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (154,0,16,'نظارت بر اخذ و اجراي اقدامات اصلاحي / برنامه بهبود كيفيت ','','51',51,'مستندات',13980120,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=0 / role_id= 19:55:15%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (155,0,21,'اندازه گیری وارزیابی عوامل ارگونومیک محیط کارمتناسب با فعالیت بخشها / واحدها','','56',55,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 19:59:17%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (156,0,21,'تعیین مشاغل با ریسک فاکتورهای ارگونومیک بالا','','56',55,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 19:59:49%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (157,0,22,'ابلاغ دبير و اعضاي كميته ها','','52,51',51,'مستندات',13980401,13980429,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:08:26%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (158,0,21,'طرح در کمیته و برنامه ریزی جهت الویت بندی کنترل عوامل ارگونومیک محیط کار','','56',55,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:09:16%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (159,0,21,'آموزش جهت اصلاح پروسیجر مناسب بر اساس الویت بندی اندازه گیری عوامل ارگونومیک','','56',55,'مصاحبه',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:09:41%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (160,0,22,'ابلاغ تقويم تدوين شده توسط مديران اجرايي','','52,51',51,'مستندات',13980401,13980429,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:09:45%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (161,0,21,'بررسی اثر بخشی آموزش اصلاح پروسیجر ( چک لیست )','','56',55,'مستند',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:10:19%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (162,0,22,'نظارت بر تشكيل ماهانه كميته ها ','','51',51,'مستندات',13980401,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:11:01%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (163,0,22,'نظارت بر انجام مصوبات ','','51',51,'مستندات',13980401,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:12:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (164,0,22,'پايش كميته هاي بيمارستاني به صورت فصلي','','51',51,'مستندات',13980401,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:13:16%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (165,0,23,'طرح موضوع انجام معاینات دوره ای طب كار كاركنان در كمیته حفاظت فنی','','56',55,'صورت جلسه کمیته',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:13:57%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (166,0,22,'سنجش اثربخشي كميته هاي بيمارستاني','','51',51,'مستندات',13980401,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:14:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (167,0,23,'طرح در جلسه تیم حاکمیتی وتایید تامین بودجه جهت معاینات دوره ای طب کار','','56',55,'صور جلسه کمیته',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:14:23%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (168,0,23,'هماهنگی با مركز بهداشت جهت انجام معاینات','','56',55,'مستندات هماهنگی',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:14:50%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (169,0,23,'انجام معاینات طب کار بر اساس نتایج اندازه گیری عوامل زیان آور','','56',55,'مستند در پرونده',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:15:51%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (170,0,22,'نظارت بر اخذ و اجراي اقدامات اصلاحي / برنامه بهبود كيفيت ','','51',51,'مستندات',13980401,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:16:53%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (171,0,23,'شناسایی بیماریهای شغلی براساس سوابق معاینات دوره ای به تفکیک هر واحد','','56',55,'',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:17:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (172,0,23,'ارزیابی بیماریهای شغلی به تفکیک هر بخش  /واحد','','56',55,'',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:18:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (173,0,23,'تدوین برنامه آموزشی در خصوص بیماریهای شغلی و روش های پیشگیری از آنها','','56',55,'مستند برنامه',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:19:16%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (174,0,23,'اجرا و ارائه برنامه آموزشی در خصوص بیماریهای شغلی ','','56',55,'مستند آموزش',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 20:19:56%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (175,0,24,'طرح موضوع اندازه گيري عوامل زيان آور فیزیکی و شیمیایی محيط كار در كميته حفاظت فني و بهداشت كار ','','56',55,'صورت جلسه کمیته',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:30:22%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (176,0,24,'گرفتن پيش فاكتور از چند شركت داراي مجوز معاونت بهداشتي دانشگاه تحت پوشش و انتخاب يك شركت ','','56',55,'مستند استعلام',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:31:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (177,0,24,'طرح در جلسه تیم حاکمیتی وتایید تامین بودجه جهت اندازه گیری عوامل زیان آور محیط کار','','56',55,'صورت جلسه کمیته',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:32:19%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (178,0,24,'هماهنگي با مركز بهداشت و شرکت منتخب جهت انجام اندازه گیری عوامل زیان آور محیط کار','','56',55,'مصاحبه',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:33:15%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (179,0,24,'هماهنگي با شركت منتخب در زمينه تعيين عوامل زيان آوري كه قرار است اندازه گيري شود ','','56',55,'مصاحبه',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:33:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (180,0,24,'اندازه گیری عوامل زیان آور شیمیایی محیط کارحداقل در بخش های پرخطر   توسط شرکت منتخب با نظارت کارشناس بهداشت حرفه ای','','56',55,'مستندات اندازه گیری',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:35:07%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (181,0,24,'اندازه گیری عوامل زیان آور محیط کار از نظر صدا و نور توسط شرکت منتخب با نظارت کارشناس بهداشت حرفه ای','','56',55,'مستندات اندازه گیری',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:36:06%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (182,0,24,'دریافت نتایج از شركت اندازه گیری و ارائه به مدیریت و مركز بهداشت','','56',55,'مستندات نتایج',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:37:12%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (183,0,24,'ارائه نتایج حاصل از اندازه گیری در کمیته مربوطه  ','','56',55,'صورت جلسه کمیته',13980130,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/07/30/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 22:39:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (184,0,25,'تنظيم  بانك نيروي انساني بر اساس امتياز حاصل از ارزشيابي پرسنل','','35',35,'مشاهده مصاحبه',13980401,13980530,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 00:59:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (185,0,25,'تنظيم برنامه كارگاههاي آموزشي جهت بانك نيروي انساني','','35',35,'مشاهده مصاحبه',13980401,13980530,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:00:43%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (186,0,25,'تنظيم برنامه فعاليت كاري در پست هاي پيشنهادي جهت اعضاي بانك نيروي انساني','','35',35,'مشاهده مصاحبه',13980501,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:01:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (187,0,25,'ارزشيابي بانك نيروي انساني از طريق چك ليستهاي مرتبط','','35',35,'مشاهده مصاحبه',13980501,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:02:39%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (188,0,26,'تهیه لیست تاسیسات منتخب جهت PM','','5',39,'مستند',13980301,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:13:00%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (189,0,26,'تدوین چك لیست ارزیابی برای تجهیزات منتخب','','5',39,'مستند فرم',13980301,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:16:13%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (190,0,26,'ارزیابی تجهیزات طبق چك لیست','','5',39,'مصاحبه',13980301,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:18:09%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (191,0,26,'تحلیل و ارزیابی فرم های تكمیل شده','','5',39,'مستند',13980301,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:18:34%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (192,0,26,'اخذ اقدام اصلاحی/برنامه بهبود','','5',39,'مصاحبه',13980301,13981220,'0','','ثبت اولیه','ثبت اولیه-1398/08/01/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 01:18:58%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (193,0,27,'تهیه لیست تجهیزات پزشکی منتخب جهت PM','','57',39,'مستند',13980301,13980315,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:30:38%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (194,0,27,'تدوین فرم های PM  برای تجهیزات منتخب','','57',39,'مستند فرم',13980315,13980330,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:31:16%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (195,0,27,'تدوین برنامه زمانبندی شده جهت انجام PM  تجهیزات منتخب','','57',39,'مستند برنامه',13980315,13980330,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:31:56%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (196,0,27,'اطلاع رسانی برنامه زمان بندی به مسئولین بخش ها','','57',39,'مصاحبه',13980330,13980415,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:32:31%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (197,0,27,'انجام بازدید های نگهداشت طبق برنامه زمانبندی شده و تکمیل فرم ','','57',39,'مستند',13980415,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:33:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (198,0,27,'تحلیل و ارزیابی فرم های تکمیل شده','','57',39,'مصاحبه',13980701,13980801,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:34:38%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (199,0,27,'انجام اقدامات اصلاحی مورد نیاز بر اساس تحلیل های انجام شده','','57',39,'مستند',13980801,13980901,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:35:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (200,0,27,'ارائه گزارش بازدید ها و اقدامات انجام شده به مدیریت بیمارستان','','57',39,'مستند',13980801,13980901,'0','','ثبت اولیه','ثبت اولیه-1398/08/02/ user_id=6 / role_id=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42,43,47,50,54, 13:35:53%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (201,0,1,'گام آخر','','9',9,'',13980101,13981229,'0','p3516066932.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:22:25%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (202,0,2,'گام آخر','','9',9,'',13980101,13981229,'0','p1317659965.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:25:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (203,0,3,'گام آخر','','7',7,'',13980101,13981229,'0','p2679067574.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:28:59%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (204,0,4,'گام آخر','','7',7,'',13980101,13981229,'0','p0350453479.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:30:56%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (205,0,5,'گام آخر','','7',7,'',13980101,13981229,'0','p1715882629.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:38:56%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (206,0,6,'گام آخر','','7',7,'',13980101,13981229,'0','p4526104956.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 20:41:05%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (207,0,28,'گام آخر','','3',3,'',13980101,13981229,'0','p1767030979.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=0 / role_id= 23:29:43%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (208,0,28,'گام آخر','','3',3,'',13980101,13981229,'0','p0933178522.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=0 / role_id= 23:42:44%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (209,0,9,'گام آخر','','3',3,'',13980101,13981229,'0','p2097824085.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=0 / role_id= 23:44:24%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (210,0,12,'گام آخر','','3',3,'',9811,13981229,'0','p3544325883.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=0 / role_id= 23:45:47%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (211,0,19,'گام آخر','','3',3,'',13980101,13981229,'0','p6840427989.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 23:52:42%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (212,0,11,'گام آخر','','3',3,'',13980101,13981229,'0','p4400224153.pdf,','ثبت اولیه','ثبت اولیه-1398/08/28/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 23:53:59%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (213,0,29,'گام آخر','','3',3,'',13980101,13981229,'0','p7123499790.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:10:34%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (214,0,13,'گام آخر','','3',3,'',13980101,13981229,'0','p3480011100.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:12:41%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (215,0,14,'گام آخر','','3',3,'',13980101,13981229,'0','','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:15:22%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (216,0,18,'گام آخر','','3',3,'',13980101,13981229,'0','p5563689730.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:25:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (217,0,30,'گام آخر','','3',3,'',13980101,13981229,'0','p8232746232.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:29:10%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (218,0,31,'گام آخر','','3',3,'',13980101,13981229,'0','p3514495216.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:30:58%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (219,0,32,'گام آخر','','3',3,'',13980101,13981229,'0','p4455585576.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:33:21%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (220,0,33,'گام آخر','','3',3,'',13980101,13981229,'0','p1836031937.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:35:18%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (221,0,34,'گام آخر','','3',3,'',13980101,13981229,'0','p9029287982.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:37:12%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (222,0,35,'گام آخر','','3',3,'',13980101,13981229,'0','p4905024373.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:38:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (223,0,36,'گام آخر','','3',3,'',13980101,13981229,'0','p8214176839.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:40:13%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (224,0,37,'گام آخر','','3',3,'',13980101,13981229,'0','p5167107971.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:42:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (225,0,38,'گام آخر','','3',3,'',13980101,13981229,'0','p3316230594.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:43:57%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (226,0,39,'گام آخر','','3',3,'',13980101,13981229,'0','p3129438557.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:45:34%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (227,0,40,'گام آخر','','3',3,'',13980101,13981229,'0','p4959567068.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:46:58%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (228,0,41,'گام آخر','','3',3,'',13980101,13981229,'0','p9840454012.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:48:33%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (229,0,25,'گام آخر','','3',3,'',13980101,13981229,'0','p4052723934.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 00:49:40%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (230,0,8,'گام آخر','','7',7,'',13980101,13981229,'0','p6945529177.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:36:05%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (231,0,10,'گام آخر','','49',49,'',13980101,13981229,'0','p9559962640.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:38:33%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (232,0,17,'گام آخر','','49',49,'',13980101,13981229,'0','p4996222128.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:40:04%23A%23','0.0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (233,0,15,'گام آخر','','49',49,'',13980101,13981229,'0','p9353996529.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:42:47%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (234,0,43,'گام آخر','','3',3,'',13980101,13981229,'0','p5668085654.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:46:54%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (235,0,44,'گام آخر','','41',41,'',13980101,13981229,'0','p5234226242.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:49:23%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (236,0,45,'گام آخر','','35',35,'',13980101,13981229,'0','p4112006411.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:51:12%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (237,0,7,'گام آخر','','35',35,'',13980101,13981229,'0','p8999401763.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:52:35%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (238,0,46,'گام آخر','','35',35,'',13980101,13981229,'0','p8424578439.docx,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:54:11%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (239,0,1,'گام آخر','','39',39,'',13980101,13981229,'0','p6620068933.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:57:09%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (240,0,27,'گام آخر','','39',39,'',13980101,13981229,'0','p0635839272.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 01:58:23%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
INSERT INTO `hmis_steps` (`id`,`steps_isActive`,`steps_plansId`,`steps_title`,`steps_executorUserId`,`steps_executorRoleId`,`steps_trackerId`,`steps_otherIndicators`,`steps_startDate`,`steps_endDate`,`steps_cost`,`steps_files`,`steps_status`,`steps_statusLog`,`steps_percent`,`steps_descriptionTracker`,`steps_descriptionExecutor`,`steps_progressPercent`,`steps_trackerFiles`,`steps_executorFiles`,`steps_percentExecutor`,`steps_percentTracker`,`steps_description`,`steps_descriptionImproveQuality`) VALUES 
 (241,0,42,'گام آخر','','3',3,'',13980101,13981229,'0','p5354747189.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 07:22:52%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (242,0,47,'گام آخر','','3',3,'',13980101,13981229,'0','p3675191693.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 07:32:46%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL),
 (243,0,48,'گام آخر','','3',3,'',13980101,13981229,'0','p0032350404.pdf,','ثبت اولیه','ثبت اولیه-1398/08/29/ user_id=6 / role_id=38,42,43,44,46,47,50,51,53,55,57,58,60,62,63,64,66,67,72, 07:33:48%23A%23','0',NULL,NULL,0,NULL,NULL,NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `hmis_steps` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_strategic`
--

DROP TABLE IF EXISTS `hmis_strategic`;
CREATE TABLE `hmis_strategic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `strategic_title` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `strategic_introduction` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategic_prophecy` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategic_prospect` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategic_IFEInternal` longtext CHARACTER SET utf8mb4,
  `strategic_EFEOuter` longtext CHARACTER SET utf8mb4,
  `strategic_IFEandEFE` longtext CHARACTER SET utf8mb4,
  `strategic_matrixSWOT` longtext CHARACTER SET utf8mb4,
  `strategic_issuesStrategic` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategic_strategicSWOT` longtext CHARACTER SET utf8mb4,
  `strategic_matrixQSPM` longtext CHARACTER SET utf8mb4,
  `strategic_prioritized` longtext CHARACTER SET utf8mb4,
  `strategic_beneficiaries` longtext CHARACTER SET utf8mb4,
  `strategic_description` longtext CHARACTER SET utf8mb4,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_strategic`
--

/*!40000 ALTER TABLE `hmis_strategic` DISABLE KEYS */;
/*!40000 ALTER TABLE `hmis_strategic` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_strategy`
--

DROP TABLE IF EXISTS `hmis_strategy`;
CREATE TABLE `hmis_strategy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `strategy_strategicId` int(10) unsigned NOT NULL DEFAULT '0',
  `strategy_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategy_points` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `strategy_proprietaryTargetId` varchar(45) DEFAULT NULL,
  `strategy_indicatorUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_strategy`
--

/*!40000 ALTER TABLE `hmis_strategy` DISABLE KEYS */;
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (1,6,'اجراي استانداردهاي اعتبار بخشي نسل چهارم ',NULL,'52',''),
 (2,6,'ارتقاء كيفيت خدمات درماني ارايه شده در بخش با مديريت بهينه فرآيندها',NULL,'51',''),
 (3,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'53',''),
 (4,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'54',''),
 (5,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'55',''),
 (6,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'56',''),
 (7,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'57',''),
 (8,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'58',''),
 (9,6,'ارتقا شاخص هاي عملكردي مركز',NULL,'59',''),
 (10,6,'استفاده از خرد جمعي در رهبري و مديريت بيمارستان',NULL,'60',''),
 (11,6,'استفاده از خرد جمعي در رهبري و مديريت بيمارستان',NULL,'61','');
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (12,6,'اجراي برنامه هاي ايمني ',NULL,'62',''),
 (13,6,'اجراي برنامه هاي ايمني ',NULL,'63',''),
 (14,6,'توسعه استاندارد هاي الزامي بيمارستان دوستدار ايمني',NULL,'64',''),
 (15,6,'توسعه استاندارد هاي الزامي بيمارستان دوستدار ايمني',NULL,'65',''),
 (16,6,'ارتقا شاخص هاي ايمني  بيمارستان',NULL,'66',''),
 (17,6,'ارتقا شاخص هاي ايمني  بيمارستان',NULL,'67',''),
 (18,6,'ارتقا شاخص هاي ايمني  بيمارستان',NULL,'68',''),
 (19,6,'مديريت خطاهاي ايمني',NULL,'69',''),
 (20,6,'مديريت خطاهاي ايمني',NULL,'70',''),
 (21,6,'بهبود رعايت اصول پيشگيري و كنترل عفونت',NULL,'71',''),
 (22,6,'بهبود رعايت اصول پيشگيري و كنترل عفونت',NULL,'72',''),
 (23,6,'بهبود رعايت اصول پيشگيري و كنترل عفونت',NULL,'73','');
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (24,6,'بهبود رعايت اصول پيشگيري و كنترل عفونت',NULL,'74',''),
 (25,6,'بي خطرسازي پسماند و پساب هاي بيمارستاني',NULL,'75',''),
 (26,6,'بي خطرسازي پسماند و پساب هاي بيمارستاني',NULL,'76',''),
 (27,6,'استقرار مديريت بحران در بيمارستان ',NULL,'111',''),
 (28,6,'ارﺗﻘﺎء فرآيند سنجش رضايتمندي',NULL,'79',''),
 (29,6,'ارﺗﻘﺎء فرآيند سنجش رضايتمندي',NULL,'80',''),
 (30,6,'ارﺗﻘﺎء فرآيند سنجش رضايتمندي',NULL,'78',''),
 (31,6,'ارﺗﻘﺎء فرآيند سنجش رضايتمندي',NULL,'81',''),
 (32,6,'مديريت طرح تكريم ارباب رجوع ',NULL,'82',''),
 (33,6,'مديريت طرح تكريم ارباب رجوع ',NULL,'83',''),
 (34,6,'مديريت طرح تكريم ارباب رجوع ',NULL,'84','');
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (35,6,'آموزش تخصصي كاركنان',NULL,'85',''),
 (36,6,'آموزش تخصصي كاركنان',NULL,'86',''),
 (37,6,'آموزش تخصصي كاركنان',NULL,'87',''),
 (38,6,'آموزش تخصصي كاركنان',NULL,'88',''),
 (39,6,'آموزش عمومي بيماران  ',NULL,'89',''),
 (40,6,'ارﺗﻘﺎء ﻓﺮاﻳﻨﺪ ﺳﻼﻣﺖ ﻫﻤﮕﺎﻧﻲ',NULL,'90',''),
 (41,6,'ارﺗﻘﺎء ﻓﺮاﻳﻨﺪ ﺳﻼﻣﺖ ﻫﻤﮕﺎﻧﻲ',NULL,'91',''),
 (42,6,'ارتقا سلامت كاركنان',NULL,'92',''),
 (43,6,'ارتقا سلامت كاركنان',NULL,'93',''),
 (44,6,'ارتقا سلامت كاركنان',NULL,'94',''),
 (45,6,'ارتقا جانشین پروری',NULL,'95',''),
 (46,6,'مديريت سرمايه هاي بيمارستاني',NULL,'96',''),
 (47,6,'مديريت سرمايه هاي بيمارستاني',NULL,'97',''),
 (48,6,'افزايش سود بيمارستاني',NULL,'98','');
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (49,6,'افزايش سود بيمارستاني',NULL,'99',''),
 (50,6,'افزايش سود بيمارستاني',NULL,'100',''),
 (51,6,'افزايش سود بيمارستاني',NULL,'101',''),
 (52,6,'افزايش سود بيمارستاني',NULL,'102',''),
 (53,6,'افزايش سود بيمارستاني',NULL,'103',''),
 (54,6,'افزايش سود بيمارستاني',NULL,'104',''),
 (55,6,'افزايش سود بيمارستاني',NULL,'105',''),
 (56,6,'افزايش سود بيمارستاني',NULL,'106',''),
 (57,6,'افزايش سود بيمارستاني',NULL,'107',''),
 (58,6,'اجراي پروژه هاي توسعه اي',NULL,'108',''),
 (59,6,'اجراي پروژه هاي توسعه اي',NULL,'109',''),
 (60,6,'اجراي پروژه هاي توسعه اي',NULL,'110',''),
 (61,6,'اجراي برنامه هاي ايمني ',NULL,'112',''),
 (62,6,'ارتقا ارائه خدمات  پرستاري در منزل(home care)',NULL,'113','');
INSERT INTO `hmis_strategy` (`id`,`strategy_strategicId`,`strategy_title`,`strategy_points`,`strategy_proprietaryTargetId`,`strategy_indicatorUrl`) VALUES 
 (63,6,'ارتقا ارائه خدمات  پرستاري در منزل(home care)',NULL,'114',''),
 (64,6,'بهبود رعايت اصول پيشگيري و كنترل عفونت',NULL,'115',''),
 (65,6,'بي خطر سازي پسماند و پساب هاي بيمارستاني',NULL,'117',''),
 (66,6,'ارتقا سلامت كاركنان',NULL,'118',''),
 (67,6,'آموزش مباني ايمني',NULL,'120',''),
 (68,6,'اجرای برنامه های ایمنی',NULL,'121',''),
 (69,6,'اجرای برنامه های ایمنی',NULL,'122','');
/*!40000 ALTER TABLE `hmis_strategy` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`hmis_totaltarget`
--

DROP TABLE IF EXISTS `hmis_totaltarget`;
CREATE TABLE `hmis_totaltarget` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `totalTarget_strategicId` int(10) unsigned NOT NULL DEFAULT '0',
  `totalTarget_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`hmis_totaltarget`
--

/*!40000 ALTER TABLE `hmis_totaltarget` DISABLE KEYS */;
INSERT INTO `hmis_totaltarget` (`id`,`totalTarget_strategicId`,`totalTarget_title`) VALUES 
 (46,6,'ارتقاء مستمر بهبود كیفی وكمی در ارائه خدمات درسطح بیمارستان'),
 (47,6,'تامين،حفظ و ارتقا ايمني  بيماران و كاركنان  در ارائه خدمات سلامت'),
 (48,6,'استقرارمدیریت خطر دربیمارستان'),
 (49,6,'افزایش رضایتمندی بیماران ،همراهان وكاركنان'),
 (50,6,'آموزش و توانمندسازی كاركنان – بیماران وجامعه'),
 (51,6,'مدیریت وبرنامه ریزی بهینه منابع(انسانی- تجهیزاتی- مالی)'),
 (52,6,'آموزش سلامت کارکنان-بیماران و جامعه');
/*!40000 ALTER TABLE `hmis_totaltarget` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `language_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_no_item` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `language_available` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_not_available` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_remain_time` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_day` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_hour` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_minute` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_like` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_dis_like` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_visited` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_add_cart` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_related` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_detail` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_product` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_more` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_news` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_payment` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_cont_buy` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_is_user` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_is_not_user` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_product_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_number` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_price` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_sum_price` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_delete` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_sumPayment` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `language_address` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `language_key_word` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `language_content` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `language_recordNotAvalable` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`language`
--

/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` (`id`,`language_name`,`language_no_item`,`language_available`,`language_not_available`,`language_remain_time`,`language_day`,`language_hour`,`language_minute`,`language_like`,`language_dis_like`,`language_visited`,`language_add_cart`,`language_related`,`language_detail`,`language_product`,`language_more`,`language_news`,`language_payment`,`language_cont_buy`,`language_is_user`,`language_is_not_user`,`language_product_name`,`language_number`,`language_price`,`language_sum_price`,`language_delete`,`language_sumPayment`,`language_address`,`language_key_word`,`language_content`,`language_recordNotAvalable`) VALUES 
 (1,'Farsi','کالایی وجود ندارد.','موجود','ناموجود','زمان باقی مانده','روز','ساعت','دقیقه','موافق','مخالف','بار مشاهده','افزودن به سبد خرید','مطالب مرتبط','مشاهده جزئیات','محصولات','ادامه مطلب','خبر','پرداخت','ادامه خرید','کاربر عضو هستم','کاربر عضو نیستم','نام محصول','تعداد','قیمت','قیمت کل','حذف','مبلغ قابل پرداخت :','آدرس','نمایندگی انحصاری محصولات فیوره-جیادا',NULL,''),
 (2,'English','No Item!','Available','Not Available','Remain Time','Day','Hour','Minute','Like','Dislike','Visited','Add to shopping cart','Related','Details','Products','more','News','Payment','Continue.','I am user.','I am not user.','Product Name','Numbers','Price','Total Price','Remove','Total price','Address',NULL,NULL,'');
INSERT INTO `language` (`id`,`language_name`,`language_no_item`,`language_available`,`language_not_available`,`language_remain_time`,`language_day`,`language_hour`,`language_minute`,`language_like`,`language_dis_like`,`language_visited`,`language_add_cart`,`language_related`,`language_detail`,`language_product`,`language_more`,`language_news`,`language_payment`,`language_cont_buy`,`language_is_user`,`language_is_not_user`,`language_product_name`,`language_number`,`language_price`,`language_sum_price`,`language_delete`,`language_sumPayment`,`language_address`,`language_key_word`,`language_content`,`language_recordNotAvalable`) VALUES 
 (3,'Arabic','الکالایی الوجود الندارد.','الموجود','لا موجود','الزمان الباقیه!!!!','الیوم','الساعه','الدقیقه','الموافق','المخالف','المشاهده','الافزودن به السبد الخرید','المطالب المرتبط','الجزئیات','الانتاج','الادامه المطلب','الاخبار','','','','','','','','','','','',NULL,NULL,''),
 (4,'Germany','-','verfügbar','Nicht verfügbar','-','Tag','Stunde','Minute','Mögen','Abneigung','besucht','Zum Warenkorb hinzufügen','verwandte','Einzelheiten','Produkt','Mehr','Nachrichten','','','','','','','','','','','',NULL,NULL,'');
INSERT INTO `language` (`id`,`language_name`,`language_no_item`,`language_available`,`language_not_available`,`language_remain_time`,`language_day`,`language_hour`,`language_minute`,`language_like`,`language_dis_like`,`language_visited`,`language_add_cart`,`language_related`,`language_detail`,`language_product`,`language_more`,`language_news`,`language_payment`,`language_cont_buy`,`language_is_user`,`language_is_not_user`,`language_product_name`,`language_number`,`language_price`,`language_sum_price`,`language_delete`,`language_sumPayment`,`language_address`,`language_key_word`,`language_content`,`language_recordNotAvalable`) VALUES 
 (5,'Chinese','-','可用的','无法使用','-','日','小时','分钟','喜欢','反感','访问','放入购物车','有关','详情','产品','更多','新闻','','','','','','','','','','','',NULL,NULL,'');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location_haspitalname` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `location_level` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `location_parent` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `location_child` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `location_parentname` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`id`,`location_haspitalname`,`location_level`,`location_parent`,`location_child`,`location_parentname`) VALUES 
 (1,'بیمارستان','1','0','0',NULL),
 (2,'بخش چشم','2','1',NULL,NULL),
 (3,'چشم مردان','3','2',NULL,NULL),
 (4,'چشم زنان','3','2',NULL,NULL),
 (5,'بخش قلب','2','1',NULL,NULL),
 (6,NULL,'0','0',NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message_sender` int(10) unsigned DEFAULT NULL,
  `message_geter` int(10) unsigned DEFAULT NULL,
  `message_title` varchar(255) DEFAULT NULL,
  `message_content` longtext,
  `message_attachment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`messenger`
--

DROP TABLE IF EXISTS `messenger`;
CREATE TABLE `messenger` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `messenger_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_textMessage` longtext CHARACTER SET utf8mb4,
  `messenger_receiver` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_sender` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_postageDate` varchar(45) NOT NULL DEFAULT '',
  `messenger_dateOfCreation` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_displayed` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_status` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_sendingMethod` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `messenger_logStatus` longtext CHARACTER SET utf8mb4,
  PRIMARY KEY (`id`,`messenger_postageDate`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`messenger`
--

/*!40000 ALTER TABLE `messenger` DISABLE KEYS */;
INSERT INTO `messenger` (`id`,`messenger_title`,`messenger_textMessage`,`messenger_receiver`,`messenger_sender`,`messenger_postageDate`,`messenger_dateOfCreation`,`messenger_displayed`,`messenger_status`,`messenger_sendingMethod`,`messenger_logStatus`) VALUES 
 (5,'nnn','','1','','13980231','','',' دیده شده',NULL,'ایجاد شده-1398/02/24 12:14:50%23A%23 درصف ارسال-1398/02/24 12:14:50%23A%23 دیده شده-1398/02/24 12:22:37%23A%23 دیده شده-1398/02/24 12:22:53%23A%23 دیده شده-1398/02/24 12:23:46%23A%23 دیده شده-1398/02/24 12:29:59%23A%23 دیده شده-1398/02/24 12:30:46%23A%23 دیده شده-1398/02/24 12:30:54%23A%23'),
 (6,'kkkk','','null','','13980224','','',' ارسال شده',NULL,'ایجاد شده-1398/02/24 12:15:13%23A%23 ارسال شده-1398/02/24 12:15:13%23A%23'),
 (7,'ll','','18','','13980224','','','ایجاد شده',NULL,'ایجاد شده-1398/02/24 12:15:37%23A%23'),
 (8,'درخواست امضائدذدئذئد','لطفا امضا کنید','1',NULL,'13980224',NULL,NULL,NULL,NULL,NULL),
 (9,'درخواست امضائدذدئذئد','لطفا امضا کنید','1',NULL,'13980224',NULL,NULL,' دیده شده',NULL,'ایجاد شده-1398/02/24 12:49:03%23A%23 دیده شده-1398/02/24 12:49:17%23A%23');
/*!40000 ALTER TABLE `messenger` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`news`
--

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_date` int(11) DEFAULT NULL,
  `news_priority` int(10) unsigned DEFAULT NULL,
  `news_title` varchar(255) DEFAULT NULL,
  `news_content` longtext,
  `news_category_id` int(11) DEFAULT NULL,
  `news_parent` int(11) DEFAULT NULL,
  `news_lang` int(10) unsigned DEFAULT NULL,
  `news_pic` varchar(255) DEFAULT NULL,
  `news_abstract` longtext,
  `news_rating` int(11) DEFAULT '0' COMMENT 'out of this service(v1.5.0)',
  `news_visit` int(11) DEFAULT '0',
  `news_likes` int(11) DEFAULT '0',
  `news_disLikes` int(11) DEFAULT '0',
  `news_has_link` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_news_news_group` (`news_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`news`
--

/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `payment_order_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_factor_id` int(10) unsigned DEFAULT NULL,
  `payment_user_id` int(10) unsigned DEFAULT NULL,
  `payment_sale_refrence_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_amount` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `payment_status` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_date` int(10) unsigned DEFAULT NULL,
  `payment_user_Name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_statusLog` longtext,
  `payment_comments` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`id`,`payment_order_id`,`payment_factor_id`,`payment_user_id`,`payment_sale_refrence_id`,`payment_amount`,`payment_status`,`payment_date`,`payment_user_Name`,`payment_statusLog`,`payment_comments`) VALUES 
 (1,'',88,0,'A00000000000000000000000000228937401','450000','ثبت اولیه',13990914,'کاربر مهمان',NULL,NULL),
 (2,'89',89,0,'A00000000000000000000000000228937401','450000','ثبت اولیه',13990914,'کاربر مهمان',NULL,NULL),
 (3,'89',89,0,'A00000000000000000000000000228945613','450000','ثبت اولیه',13990914,'کاربر مهمان',NULL,NULL),
 (4,'89',89,0,'A00000000000000000000000000228945876','450000','ثبت اولیه',13990914,'کاربر مهمان',NULL,NULL),
 (5,'89',89,0,'A00000000000000000000000000228989714','10000','پرداخت شده',13990915,'کاربر مهمان',NULL,NULL),
 (6,'89',89,0,'A00000000000000000000000000228979519','450000','ثبت اولیه',13990914,'کاربر مهمان',NULL,NULL),
 (7,'89',89,0,'22898971401','10000','پرداخت شده',13990915,'کاربر مهمان',NULL,NULL),
 (8,'92',92,16,'A00000000000000000000000000229428216','10000','ثبت اولیه',13990916,'محمد ثالثی2',NULL,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`payment_setting`
--

DROP TABLE IF EXISTS `payment_setting`;
CREATE TABLE `payment_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `payment_setting_terminal_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_setting_merchant_id` varchar(100) DEFAULT NULL,
  `payment_setting_userName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_setting_pass` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `payment_setting_webService` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `payment_setting_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`payment_setting`
--

/*!40000 ALTER TABLE `payment_setting` DISABLE KEYS */;
INSERT INTO `payment_setting` (`id`,`payment_setting_terminal_id`,`payment_setting_merchant_id`,`payment_setting_userName`,`payment_setting_pass`,`payment_setting_webService`,`payment_setting_name`) VALUES 
 (1,'72e8e9a3-b212-4732-bb73-29438abc4d2e','','1ay34d4E0HT4uvpU2H66',NULL,'zarinpal','زرین پال');
/*!40000 ALTER TABLE `payment_setting` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`pdca`
--

DROP TABLE IF EXISTS `pdca`;
CREATE TABLE `pdca` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pdca_html` longtext,
  `pdca_subject` varchar(400) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`pdca`
--

/*!40000 ALTER TABLE `pdca` DISABLE KEYS */;
INSERT INTO `pdca` (`id`,`pdca_html`,`pdca_subject`) VALUES 
 (56,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\" value=\"بررسی حراست \">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \'#pdca_file1\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \'#pdca_file2\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \'#pdca_file3\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \'#pdca_file4\';                    var inputselector = \'input[type=file]\';                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\">C:/fakepath/1.jpg</span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><table id=\"tableFOCUSPDCA2\" class=\"table display responsive no-footer dtr-inline dataTable\" role=\"grid\" aria-describedby=\"tableFOCUSPDCA2_info\" style=\"width: 963px;\">                <thead>                    <tr role=\"row\"><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 154px;\" aria-label=\"موارد ارتقا: activate to sort column ascending\">موارد ارتقا</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"تاثیر ان بر فرایند: activate to sort column ascending\">تاثیر ان بر فرایند</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"اسان بودن: activate to sort column ascending\">اسان بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"کم هزینه بودن: activate to sort column ascending\">کم هزینه بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 173px;\" aria-label=\"بی نیازی به افراد خارج از سازمان: activate to sort column ascending\">بی نیازی به افراد خارج از سازمان</th><th class=\"r sorting_desc\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 72px;\" aria-label=\" جمع امتیازات: activate to sort column ascending\" aria-sort=\"descending\"> جمع امتیازات</th></tr>                </thead>                <tbody>                                                                                                                                        <tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"150000\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">150000</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"120\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">120</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">15</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"12\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">12</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"3\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">3</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\"></td>                    </tr></tbody>            </table><div class=\"dataTables_info\" id=\"tableFOCUSPDCA2_info\" role=\"status\" aria-live=\"polite\">Showing 1 to 6 of 6 entries</div></div><div class=\"dataTables_info\" id=\"tableFOCUSPDCA2_info\" role=\"status\" aria-live=\"polite\">Showing 1 to 6 of 6 entries</div></div><div class=\"dataTables_info\" id=\"tableFOCUSPDCA2_info\" role=\"status\" aria-live=\"polite\">Showing 1 to 6 of 6 entries</div></div>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>        </div>    </div></div>','بررسی حراست '),
 (57,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \"#pdca_file1\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \"#pdca_file2\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \"#pdca_file3\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \"#pdca_file4\";                    var inputselector = \"input[type=file]\";                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><table id=\"tableFOCUSPDCA2\" class=\"table display responsive dataTable no-footer dtr-inline\" role=\"grid\" aria-describedby=\"tableFOCUSPDCA2_info\" style=\"width: 967px;\">                <thead>                    <tr role=\"row\"><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"موارد ارتقا: activate to sort column ascending\">موارد ارتقا</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"تاثیر ان بر فرایند: activate to sort column ascending\">تاثیر ان بر فرایند</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"اسان بودن: activate to sort column ascending\">اسان بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 159px;\" aria-label=\"کم هزینه بودن: activate to sort column ascending\">کم هزینه بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 173px;\" aria-label=\"بی نیازی به افراد خارج از سازمان: activate to sort column ascending\">بی نیازی به افراد خارج از سازمان</th><th class=\"r sorting_desc\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 71px;\" aria-sort=\"descending\" aria-label=\" جمع امتیازات: activate to sort column ascending\"> جمع امتیازات</th></tr>                </thead>                <tbody>                                                                                                                                        <tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"45\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">45</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"10\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">10</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"7\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">7</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\"></td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\"></td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\"></td>                    </tr></tbody>            </table><div class=\"dataTables_info\" id=\"tableFOCUSPDCA2_info\" role=\"status\" aria-live=\"polite\">Showing 1 to 6 of 6 entries</div></div>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>                    <script type=\"text/javascript\">                $(\'.form-control\').keyup(function () {                    $(this).attr(\'value\', $(this).val());                });                $(\'.isok\').on(\'keyup\', function (ev) {                    var sum = 0;                    var j = 0;                    for (var i = 0; i <= 5; i++) {                        var nextInput = $(this).parent().parent().find(\"input\")[i];                        if ($(nextInput).val() != \"\" && $(nextInput).val() != \"0\") {                            sum += parseFloat($(nextInput).val());                            j++;                        }                    }                    var avgInput = $(this).parent().parent().find(\"input\")[i];// خانه آخر                     $(avgInput).attr(\'value\', sum);                });                $(\'.isoksort\').on(\'keyup\', function (ev) {                    var sum = 0;                    var j = 0;                    for (var i = 1; i <= 4; i++) {                        var nextInput = $(this).parent().parent().find(\"input\")[i];                        if ($(nextInput).val() != \"\") {                            sum += parseFloat($(nextInput).val());                            j++;                        }                    }                    var avgInput = $(this).parent().parent().find(\".sum\");// خانه آخر                     $(avgInput).html(sum);                });</script>        </div>    </div></div>','');
INSERT INTO `pdca` (`id`,`pdca_html`,`pdca_subject`) VALUES 
 (58,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \"#pdca_file1\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \"#pdca_file2\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \"#pdca_file3\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \"#pdca_file4\";                    var inputselector = \"input[type=file]\";                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <table id=\"tableFOCUSPDCA2\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\">موارد ارتقا</th>                    <th class=\" r\">تاثیر ان بر فرایند</th><th class=\" r\">اسان بودن</th><th class=\" r\">کم هزینه بودن</th><th class=\" r\">بی نیازی به افراد خارج از سازمان</th><th class=\" r\"> جمع امتیازات</th>                    </tr>                </thead>                <tbody>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                </tbody>            </table>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>                         </div>    </div></div>',''),
 (59,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"45\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"45\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \"#pdca_file1\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \"#pdca_file2\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \"#pdca_file3\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \"#pdca_file4\";                    var inputselector = \"input[type=file]\";                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><table id=\"tableFOCUSPDCA2\" class=\"table display responsive dataTable no-footer dtr-inline\" role=\"grid\" aria-describedby=\"tableFOCUSPDCA2_info\" style=\"width: 967px;\">                <thead>                    <tr role=\"row\"><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"موارد ارتقا: activate to sort column ascending\">موارد ارتقا</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"تاثیر ان بر فرایند: activate to sort column ascending\">تاثیر ان بر فرایند</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"اسان بودن: activate to sort column ascending\">اسان بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 159px;\" aria-label=\"کم هزینه بودن: activate to sort column ascending\">کم هزینه بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 173px;\" aria-label=\"بی نیازی به افراد خارج از سازمان: activate to sort column ascending\">بی نیازی به افراد خارج از سازمان</th><th class=\"r sorting_desc\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 71px;\" aria-sort=\"descending\" aria-label=\" جمع امتیازات: activate to sort column ascending\"> جمع امتیازات</th></tr>                </thead>                <tbody>                                                                                                                                        <tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">15</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"7\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">7</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"5\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">5</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"4\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">4</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"3\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">3</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">0</td>                    </tr></tbody>            </table><div class=\"dataTables_info\" id=\"tableFOCUSPDCA2_info\" role=\"status\" aria-live=\"polite\">Showing 1 to 6 of 6 entries</div></div>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>                         </div>    </div></div>','');
INSERT INTO `pdca` (`id`,`pdca_html`,`pdca_subject`) VALUES 
 (60,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"15\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \'#pdca_file1\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \'#pdca_file2\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \'#pdca_file3\';                    var inputselector = \'input[type=file]\';                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\" value=\"khjkjhjhjhkjl\">khjkjhjhjhkjl</textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \'#pdca_file4\';                    var inputselector = \'input[type=file]\';                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><div id=\"tableFOCUSPDCA2_wrapper\" class=\"dataTables_wrapper no-footer\"><table id=\"tableFOCUSPDCA2\" class=\"table display responsive no-footer dtr-inline dataTable\" role=\"grid\" style=\"width: 965px;\">                <thead>                    <tr role=\"row\"><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 156px;\" aria-label=\"موارد ارتقا: activate to sort column ascending\">موارد ارتقا</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"تاثیر ان بر فرایند: activate to sort column ascending\">تاثیر ان بر فرایند</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"اسان بودن: activate to sort column ascending\">اسان بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 158px;\" aria-label=\"کم هزینه بودن: activate to sort column ascending\">کم هزینه بودن</th><th class=\"r sorting\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 173px;\" aria-label=\"بی نیازی به افراد خارج از سازمان: activate to sort column ascending\">بی نیازی به افراد خارج از سازمان</th><th class=\"r sorting_desc\" tabindex=\"0\" aria-controls=\"tableFOCUSPDCA2\" rowspan=\"1\" colspan=\"1\" style=\"width: 72px;\" aria-label=\" جمع امتیازات: activate to sort column ascending\" aria-sort=\"descending\"> جمع امتیازات</th></tr>                </thead>                <tbody>                                                                                                                                        <tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"12\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"1000\"></td><td class=\"sum c sorting_1\">1012</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"15\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">30</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"20\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">20</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"16\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">16</td>                    </tr><tr role=\"row\" class=\"odd\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"8\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">8</td>                    </tr><tr role=\"row\" class=\"even\">                    <td tabindex=\"0\"><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"4\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c sorting_1\">4</td>                    </tr></tbody>            </table></div></div>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>                         </div>    </div></div>',''),
 (63,'<div class=\"card bd-primary mg-t-20\" id=\"newFormQuestion\">                   <div class=\"card-body pd-sm-30\">        <p class=\"mg-b-20 mg-sm-b-30\"></p>        <div class=\"col-lg-12\">            <div class=\"form-group has-success mg-b-0\">            </div><!-- form-group -->        </div><!-- col -->        موضوع        <input id=\"subject\" class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"موضوع را وارد کنید\">        <div class=\"card-header bg-primary tx-white\" style=\"margin-top: 10px;\">  Find پیدا کردن مسئله</div>        فرآیند        <input class=\"form-control is-valid onkeyup\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">        <br>        <div class=\"card-header bg-info tx-white c\">بارش افکار</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div align=\"right\">                <table id=\"tableFMEA1_1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\">کد</th>                        <td class=\" r\">راهکار پیشنهادی</td>                        <th class=\" r\">نفر&nbsp; اول</th><th class=\" r\">نفر دوم</th><td class=\" r\">نفر سوم</td><td class=\" r\">نفر چهارم</td><td class=\" r\">نفر پنج</td><td class=\" r\">جمع</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                            <td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isok\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">بیانیه فرصت</div>            <div class=\"col-lg-12 bg-primary\">                <textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\">بیان فرصتبرای ارتقا فرآیند .......................................................................................  فرصتی  فراهم است .( نام فرآیند ) فرآیند با ....................................................... شروع و به ..................................... ختم می شود ( ابتدای فرآیند )                                                ( انتهای فرآیند ) فرآیند جاری موجب ..............................................................................................................و ارتقا موجب ........................................................................................................خواهد شد .( عملکرد فرآیند پس از ارتقا )ارتقا به نفع .................................................................................................................می بلشد .( افراد یا واحد های ذینفع ) ارتقای فرآیند به دلایل زیر حایز اهمیت است : ......................................................................................................................................................................................................................................................................................................                </textarea>            </div><!-- col -->             <div class=\"card-header bg-info tx-white c\">نمودار قالبی</div>            <div class=\"card-body bg-primary tx-white\">                عنوان موضوع/فرآیند                <input class=\"form-control is-valid\" type=\"text\" placeholder=\"فرآیندی را برای ارتقا وارد کنید\">            </div><!-- col -->           </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12 tx-white\">                <script>                    var pdca_file1 = \"#pdca_file1\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری نمودار قالبی:<br>                <div id=\"showfiles1div\"></div>                <span id=\"pdca_file1\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile1\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile1\" name=\"pdca_sendfile1\" onchange=\"$(this).parent().parent().find(pdca_file1).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile1\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"padding-top: 10px;\">افراد حاضر در جلسه ORGANIZE</div>            <div class=\"card-body bd-primary mg-b-5 mg-t-5\">                <table id=\"tableFOCUSPDCA1\" class=\"table display responsive\">                    <thead>                        <tr>                        <th class=\" r\"></th>                        <th class=\" r\" style=\"text-align: center;\">نام و نام خانوادگی</th><th class=\" r\" style=\"text-align: center;\">سمت&nbsp;</th><td class=\" r\" style=\"text-align: center;\">نوع ارتباط با فرآیند</td>                        </tr>                    </thead>                    <tbody>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                        <tr><td><br></td>                        <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"> </td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                        </tr>                    </tbody>                </table>            </div>        </div>        <div class=\"card-header bg-info tx-white c\">Clarify شناسایی</div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file2 = \"#pdca_file2\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار جریان  فرآیند                <div id=\"showfiles2div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile2\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file2\" class=\"form-control col-lg-8 r\"></span>                <input id=\"pdca_sendfile2\" name=\"pdca_sendfile2\" onchange=\"$(this).parent().parent().find(pdca_file2).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile2\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"col-lg-12\">                <script>                    var pdca_file3 = \"#pdca_file3\";                    var inputselector = \"input[type=file]\";                </script>                بارگذاری فایل نمودار کنترل جریان داده ها                <div id=\"showfiles3div\"></div>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile3\" placeholder=\"عنوان فایل را تایپ کنید\" type=\"text\">                <span id=\"pdca_file3\" class=\"form-control col-lg-8\"></span>                <input id=\"pdca_sendfile3\" name=\"pdca_sendfile3\" onchange=\"$(this).parent().parent().find(pdca_file3).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <br><input class=\"btn btn-primary\" id=\"sendfile3\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->          </div>        <br>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\">Understand شناسایی علل تغییرات</div>            <div class=\"card-header bg-danger tx-white \" style=\"text-align: center;\">نمودار همگرایی</div>            <table id=\"tableFMEA1_1\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\"></th>                    <th class=\" r\">مدیریت</th><th class=\" r\">روش ها</th><th class=\" r\">لوازم</th><th class=\" r\">کارکنان</th><th class=\" r\">تجهیزات</th><th class=\" r\">فرآیند</th>                    </tr>                </thead>                <tbody>                    <tr><td><br></td>                    <td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\" value=\"kjhjbjhbjbjbk\">kjhjbjhbjbjbk</textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\" value=\"knjbnjmbjkbnknmbnmv bnmb n\">knjbnjmbjkbnknmbnmv bnmb n</textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td><td><textarea rows=\"15\" cols=\"70\" class=\"form-control is-valid onkeyup\" placeholder=\"\"></textarea></td>                    </tr>                </tbody>            </table>        </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">بارگزاری فایل استخوان ماهی</div>            <div class=\"col-lg-12\" style=\"margin-bottom: 5px;background-color: #ffffff;\">                <script>                    var pdca_file4 = \"#pdca_file4\";                    var inputselector = \"input[type=file]\";                </script>                                <div id=\"showfiles4div\"></div><br>                <span id=\"pdca_file4\" class=\"form-control col-lg-8\"></span>                <input class=\"form-control col-lg-8\" id=\"pdca_titleFile4\" placeholder=\"فایل شما با این عنوان در سامانه ذخیره میشود\" type=\"text\">                <input id=\"pdca_sendfile4\" name=\"pdca_sendfile4\" onchange=\"$(this).parent().parent().find(pdca_file4).html($(this).val().split(/[//|/]/).pop());\" style=\"display: none;\" type=\"file\">                <input class=\"btn btn-primary\" id=\"sendfile4\" type=\"submit\" value=\"ارسال\">                <span class=\"btn btn-primary\" onclick=\"$(this).parent().find(inputselector).click();\">انتخاب فایل</span>            </div><!-- col -->         </div>        <div class=\"card bd-primary mg-b-5 mg-t-5\">            <div class=\"card-header bg-info tx-white c\" style=\"text-align: center;\">انتخاب راهکار Select</div>            <table id=\"tableFOCUSPDCA2\" class=\"table display responsive\">                <thead>                    <tr>                    <th class=\" r\">موارد ارتقا</th>                    <th class=\" r\">تاثیر ان بر فرایند</th><th class=\" r\">اسان بودن</th><th class=\" r\">کم هزینه بودن</th><th class=\" r\">بی نیازی به افراد خارج از سازمان</th><th class=\" r\"> جمع امتیازات</th>                    </tr>                </thead>                <tbody>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                    <tr>                    <td><input class=\"form-control is-valid\" type=\"text\" value=\"\"></td>                                                        <td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td><input class=\"form-control is-valid isoksort\" type=\"text\" value=\"\"></td><td class=\"sum c\"></td>                    </tr>                </tbody>            </table>            <button id=\"sort_Pdca\" class=\"btn btn-outline-success btn-block mg-t-20\" onclick=\"hmisPdca.m_sort();\">مرتب کردن</button>                         </div>    </div></div>','');
/*!40000 ALTER TABLE `pdca` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`phonecall`
--

DROP TABLE IF EXISTS `phonecall`;
CREATE TABLE `phonecall` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phonecall_text` varchar(500) NOT NULL DEFAULT '0',
  `phonecall_sender` longtext,
  `phonecall_status` varchar(45) NOT NULL DEFAULT '0',
  `phonecall_date` int(10) unsigned NOT NULL DEFAULT '0',
  `phonecall_receiver` varchar(200) NOT NULL DEFAULT '0',
  `phonecall_receiver_name` varchar(100) NOT NULL DEFAULT '0',
  `phonecall_receiver_family` varchar(100) NOT NULL DEFAULT '0',
  `phonecall_comment` longtext,
  `phonecall_receiver_id` int(10) unsigned NOT NULL DEFAULT '0',
  `phonecall_subject` longtext,
  `phonecall_characters` varchar(45) NOT NULL DEFAULT '0',
  `phonecall_messageID` varchar(100) NOT NULL DEFAULT '0',
  `phonecall_send_time` varchar(45) NOT NULL DEFAULT '0',
  `phonecall_webService` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`phonecall`
--

/*!40000 ALTER TABLE `phonecall` DISABLE KEYS */;
INSERT INTO `phonecall` (`id`,`phonecall_text`,`phonecall_sender`,`phonecall_status`,`phonecall_date`,`phonecall_receiver`,`phonecall_receiver_name`,`phonecall_receiver_family`,`phonecall_comment`,`phonecall_receiver_id`,`phonecall_subject`,`phonecall_characters`,`phonecall_messageID`,`phonecall_send_time`,`phonecall_webService`) VALUES 
 (1,'هشدار هشدار قسمت اشپزخانه در حال سوختن','02138062','درخواست تایید شد',1568719469,'09388033127','0','0',NULL,0,NULL,'0','818123659','0','0');
/*!40000 ALTER TABLE `phonecall` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`pic`
--

DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gallery_id` int(11) DEFAULT NULL,
  `pic_title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pic_pic_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pic_pic_ex` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pic_parent` int(10) unsigned DEFAULT NULL,
  `pic_lang` int(10) unsigned DEFAULT NULL,
  `pic_discription` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `pic_html1` longtext,
  `pic_html2` longtext,
  `pic_html3` longtext,
  `pic_scriptOnClickImg` varchar(400) DEFAULT NULL,
  `pic_address` varchar(100) DEFAULT NULL,
  `pic_link` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`pic`
--

/*!40000 ALTER TABLE `pic` DISABLE KEYS */;
INSERT INTO `pic` (`id`,`gallery_id`,`pic_title`,`pic_pic_name`,`pic_pic_ex`,`pic_parent`,`pic_lang`,`pic_discription`,`pic_html1`,`pic_html2`,`pic_html3`,`pic_scriptOnClickImg`,`pic_address`,`pic_link`) VALUES 
 (53,1,'مدرسه ی علوم انسانی جیوگی','p1215515668','jpg',0,1,'','','','','','',NULL);
/*!40000 ALTER TABLE `pic` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`poll`
--

DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `poll_qu` varchar(255) DEFAULT NULL,
  `poll_an1` varchar(100) DEFAULT NULL,
  `poll_an2` varchar(100) DEFAULT NULL,
  `poll_an3` varchar(100) DEFAULT NULL,
  `poll_an4` varchar(100) DEFAULT NULL,
  `poll_an5` varchar(100) DEFAULT NULL,
  `poll_an6` varchar(100) DEFAULT NULL,
  `poll_lang` int(10) unsigned DEFAULT NULL,
  `poll_parent` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`poll`
--

/*!40000 ALTER TABLE `poll` DISABLE KEYS */;
INSERT INTO `poll` (`id`,`poll_qu`,`poll_an1`,`poll_an2`,`poll_an3`,`poll_an4`,`poll_an5`,`poll_an6`,`poll_lang`,`poll_parent`) VALUES 
 (1,'آیا ظاهر سایت را می پسندید؟','بله','خیر','','','','',1,0);
/*!40000 ALTER TABLE `poll` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`portal_post`
--

DROP TABLE IF EXISTS `portal_post`;
CREATE TABLE `portal_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `portal_post_title` varchar(255) DEFAULT NULL,
  `portal_post_ownerId` int(10) unsigned DEFAULT '0',
  `portal_post_ownerName` varchar(255) DEFAULT NULL,
  `portal_post_detail` longtext,
  `portal_post_date` int(10) unsigned DEFAULT '0',
  `portal_post_priority` int(10) unsigned DEFAULT '0',
  `portal_post_val1` varchar(50) DEFAULT NULL,
  `portal_post_val2` varchar(50) DEFAULT NULL,
  `portal_post_val3` varchar(50) DEFAULT NULL,
  `portal_post_val4` varchar(50) DEFAULT NULL,
  `portal_post_val5` varchar(50) DEFAULT NULL,
  `portal_post_val6` varchar(50) DEFAULT NULL,
  `portal_post_val7` varchar(50) DEFAULT NULL,
  `portal_post_val8` varchar(50) DEFAULT NULL,
  `portal_post_val9` varchar(50) DEFAULT NULL,
  `portal_post_val10` varchar(50) DEFAULT NULL,
  `portal_post_pic1` varchar(255) DEFAULT NULL,
  `portal_post_pic2` varchar(255) DEFAULT NULL,
  `portal_post_pic3` varchar(255) DEFAULT NULL,
  `portal_post_pic4` varchar(255) DEFAULT NULL,
  `portal_post_pic5` varchar(255) DEFAULT NULL,
  `portal_post_visit` int(11) NOT NULL DEFAULT '0',
  `portal_post_like` int(11) DEFAULT '0',
  `portal_post_dislike` int(11) NOT NULL DEFAULT '0',
  `portal_post_isActive` tinyint(1) NOT NULL DEFAULT '0',
  `portal_post_parent` varchar(45) DEFAULT NULL COMMENT 'for language',
  `portal_post_prop1` varchar(45) DEFAULT NULL,
  `portal_post_prop2` varchar(45) DEFAULT NULL,
  `portal_post_prop3` varchar(45) DEFAULT NULL,
  `portal_post_prop4` varchar(45) DEFAULT NULL,
  `portal_post_prop5` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`portal_post`
--

/*!40000 ALTER TABLE `portal_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `portal_post` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`portal_user`
--

DROP TABLE IF EXISTS `portal_user`;
CREATE TABLE `portal_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `portal_user_UserId` int(10) unsigned NOT NULL COMMENT '???? ??? ?? ???? ??????-??????? ???? ???',
  `portal_user_UserName` varchar(255) DEFAULT NULL,
  `portal_user_pass` varchar(255) DEFAULT NULL,
  `portal_user_firstName` varchar(255) DEFAULT NULL,
  `portal_user_lastName` varchar(255) DEFAULT NULL,
  `portal_user_pageTitle1` varchar(255) DEFAULT NULL,
  `portal_user_pageTitle2` varchar(255) DEFAULT NULL,
  `portal_user_pic` varchar(255) DEFAULT NULL,
  `portal_user_mobile` varchar(45) DEFAULT NULL,
  `portal_user_phone` varchar(45) DEFAULT NULL,
  `portal_user_val1` varchar(255) DEFAULT NULL,
  `portal_user_val2` varchar(255) DEFAULT NULL,
  `portal_user_val3` varchar(255) DEFAULT NULL,
  `portal_user_val4` varchar(255) DEFAULT NULL,
  `portal_user_val5` varchar(255) DEFAULT NULL,
  `portal_user_val6` varchar(255) DEFAULT NULL,
  `portal_user_val7` varchar(255) DEFAULT NULL,
  `portal_user_val8` varchar(255) DEFAULT NULL,
  `portal_user_val9` varchar(255) DEFAULT NULL,
  `portal_user_val10` varchar(255) DEFAULT NULL,
  `portal_user_address` varchar(255) DEFAULT NULL,
  `portal_user_comment` varchar(255) DEFAULT NULL,
  `portal_user_isActive` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `portal_user_blocked` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `portal_user_status` varchar(45) DEFAULT NULL,
  `portal_user_visit` int(11) NOT NULL DEFAULT '0',
  `portal_user_creationDate` int(10) unsigned DEFAULT '0',
  `portal_user_content` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`portal_user`
--

/*!40000 ALTER TABLE `portal_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `portal_user` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_title` varchar(255) DEFAULT NULL,
  `product_des` varchar(255) DEFAULT NULL,
  `product_pic` varchar(255) DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_persian_ci DEFAULT NULL,
  `product_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_persian_ci DEFAULT NULL,
  `product_lang` int(10) unsigned DEFAULT NULL,
  `product_parent` int(10) unsigned DEFAULT NULL,
  `product_detales` longtext COMMENT 'new in v1.5.0',
  `product_lable` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_numbers` int(11) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_field1` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_field2` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_field3` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_field4` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  `product_des2` varchar(255) DEFAULT NULL COMMENT 'new in v1.5.0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_bambo`.`product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`product_factor`
--

DROP TABLE IF EXISTS `product_factor`;
CREATE TABLE `product_factor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_factor_userId` int(10) unsigned NOT NULL DEFAULT '0',
  `product_factor_creator` int(10) unsigned NOT NULL DEFAULT '0',
  `product_factor_serialNumber` int(10) unsigned DEFAULT NULL,
  `product_factor_nationalCode` varchar(45) DEFAULT NULL,
  `product_factor_economicCode` varchar(45) DEFAULT NULL,
  `product_factor_companyName` varchar(300) DEFAULT NULL,
  `product_factor_address` longtext,
  `product_factor_address2` longtext,
  `product_factor_zipCode` varchar(45) DEFAULT NULL,
  `product_factor_zipCode2` varchar(45) DEFAULT NULL,
  `product_factor_tell` varchar(100) DEFAULT NULL,
  `product_factor_tell2` varchar(100) DEFAULT NULL,
  `product_factor_date` int(10) unsigned DEFAULT NULL,
  `product_factor_time` int(10) unsigned DEFAULT NULL,
  `product_factor_statuse` varchar(200) DEFAULT NULL,
  `product_factor_discription` longtext,
  `product_factor_totalAmountValueAdded` decimal(22,0) unsigned DEFAULT NULL,
  `product_factor_totalAmount` decimal(22,0) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`product_factor`
--

/*!40000 ALTER TABLE `product_factor` DISABLE KEYS */;
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (54,6,6,8,'','','','','','','','','',13990508,1254,'پرداخت نشده',NULL,'34425','416925'),
 (55,6,6,55,'','','','','','','','','',13990520,1155,'پرداخت نشده',NULL,'0','0'),
 (56,6,6,56,'','','','','','','','','',13990520,1208,'پرداخت نشده',NULL,'0','0'),
 (57,6,6,57,'','','','','','','','','',13990520,1208,'پرداخت نشده',NULL,'0','0'),
 (58,6,6,58,'','','','','','','','','',13990520,1216,'پرداخت نشده',NULL,'90','1090'),
 (59,6,6,59,'','','','','','','','','',13990520,1216,'پرداخت نشده',NULL,'90','1090'),
 (60,6,6,60,'','','','','','','','','',13990520,1223,'پرداخت نشده',NULL,'90','1090');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (61,6,6,61,'','','','','','','','','',13990520,1225,'پرداخت نشده',NULL,'90','1090'),
 (62,6,6,62,'','','','','','','','','',13990520,1230,'پرداخت نشده',NULL,'90','1090'),
 (63,6,6,63,'','','','','','','','','',13990520,1231,'پرداخت نشده',NULL,'90','1090'),
 (64,6,6,64,'','','','','','','','','',13990520,1232,'پرداخت نشده',NULL,'90','1090'),
 (65,6,6,65,'','','','','','','','','',13990520,1232,'پرداخت نشده',NULL,'90','1090'),
 (66,6,6,66,'','','','','','','','','',13990520,1232,'پرداخت نشده',NULL,'90','1090');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (67,6,6,67,'','','','','','','','','',13990520,1258,'پرداخت نشده',NULL,'90','1090'),
 (68,6,6,68,'','','','','','','','','',13990520,1303,'پرداخت نشده',NULL,'90','1090'),
 (69,6,6,69,'','','','','','','','','',13990520,1310,'پرداخت نشده',NULL,'90','1090'),
 (70,6,6,70,'','','','','','','','','',13990520,1312,'پرداخت نشده',NULL,'90','1090'),
 (71,6,6,71,'','','','','','','','','',13990520,1409,'پرداخت نشده',NULL,'90','1090'),
 (72,6,6,72,'','','','','','','','','',13990520,1420,'پرداخت نشده',NULL,'90','1090');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (73,6,6,73,'','','','','','','','','',13990520,1425,'پرداخت نشده',NULL,'72','872'),
 (74,6,6,74,'','','','','','','','','',13990520,1432,'پرداخت نشده',NULL,'0','1000'),
 (75,6,6,75,'','','','','','','','','',13990520,1434,'پرداخت نشده',NULL,'2430','29430'),
 (76,6,6,76,'','','','','','','','','',13990521,1058,'پرداخت نشده',NULL,'53190','644190'),
 (77,6,6,77,'','','','','','','','','',13990521,1059,'پرداخت نشده',NULL,'90','1090'),
 (78,6,6,78,'','','','','','','','','',13990521,1101,'پرداخت نشده',NULL,'90','1090');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (79,6,6,79,'','','','','','','','','',13990525,1728,'پرداخت نشده',NULL,'14490','175490'),
 (80,20,20,80,'','','','','','','','','',13990527,1639,'پرداخت نشده',NULL,'0','30000000'),
 (81,13,13,81,'','','','','','','','','',13990911,444,'پرداخت نشده',NULL,'0','0'),
 (82,13,13,82,'','','','','','','','','',13990911,445,'پرداخت نشده',NULL,'0','0'),
 (83,13,13,83,'','','','','','','','','',13990911,547,'پرداخت نشده',NULL,'0','0'),
 (84,13,13,84,'','','','','','','','','',13990911,550,'پرداخت نشده',NULL,'22500','272500');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (85,13,13,85,'','','','','','','','','',13990911,556,'پرداخت نشده',NULL,'22500','472500'),
 (86,13,13,86,'','','','','','','','','',13990911,559,'پرداخت نشده',NULL,'0','450000'),
 (87,13,13,87,'','','','','','','','','',13990911,1827,'پرداخت نشده',NULL,'0','450000'),
 (88,13,13,88,'','','','','','','','','',13990912,1756,'پرداخت نشده',NULL,'0','450000'),
 (89,13,13,89,'','','','','','','','','',13990912,1756,'پرداخت شده','شماره ارجاع:payment_sale_refrence_id','0','450000'),
 (90,13,13,90,'','','','','','','','','',13990912,1838,'پرداخت نشده',NULL,'0','450000');
INSERT INTO `product_factor` (`id`,`product_factor_userId`,`product_factor_creator`,`product_factor_serialNumber`,`product_factor_nationalCode`,`product_factor_economicCode`,`product_factor_companyName`,`product_factor_address`,`product_factor_address2`,`product_factor_zipCode`,`product_factor_zipCode2`,`product_factor_tell`,`product_factor_tell2`,`product_factor_date`,`product_factor_time`,`product_factor_statuse`,`product_factor_discription`,`product_factor_totalAmountValueAdded`,`product_factor_totalAmount`) VALUES 
 (91,13,13,91,'','','','','','','','','',13990913,33,'پرداخت نشده',NULL,'0','450000'),
 (92,16,16,92,'1292547899','','پژوهشگران سیستم های هوشمند سپانو','اصفهان، شهرک علمی و تحقیقاتی اصفهان','اصفهان، خیابان حکیم نظامی، پروژه ی گل نرگس، واحد 555','8491645513','','','',13990916,1851,'پرداخت شده','شماره ارجاع:payment_sale_refrence_id','0','10000');
/*!40000 ALTER TABLE `product_factor` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`product_factor_item`
--

DROP TABLE IF EXISTS `product_factor_item`;
CREATE TABLE `product_factor_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_factor_item_productId` int(10) unsigned NOT NULL DEFAULT '0',
  `product_factor_item_factorId` int(10) unsigned NOT NULL DEFAULT '0',
  `product_factor_item_quantity` int(10) unsigned DEFAULT NULL,
  `product_factor_item_originalPrice` decimal(22,0) unsigned DEFAULT NULL,
  `product_factor_item_discountPercent` decimal(6,2) unsigned DEFAULT NULL,
  `product_factor_item_priceAfterDiscount` decimal(22,0) unsigned DEFAULT NULL,
  `product_factor_item_percentageOfValueAdded` decimal(6,2) unsigned DEFAULT NULL,
  `product_factor_item_valueAdded` decimal(12,0) unsigned DEFAULT NULL,
  `product_factor_item_totalPrice` decimal(22,0) unsigned DEFAULT NULL,
  `product_factor_item_discription` longtext,
  `product_factor_item_date` int(10) unsigned DEFAULT NULL,
  `product_factor_item_time` int(10) unsigned DEFAULT NULL,
  `product_factor_item_statuse` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bambo`.`product_factor_item`
--

/*!40000 ALTER TABLE `product_factor_item` DISABLE KEYS */;
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (59,77,54,0,'450000','15.00','382500','9.00','34425','416925','',13990508,1255,'پرداخت نشده'),
 (60,79,58,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1216,NULL),
 (61,79,59,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1216,NULL),
 (62,79,60,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1223,NULL),
 (63,79,61,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1225,NULL),
 (64,79,62,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1230,NULL),
 (65,79,63,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1231,NULL),
 (66,79,64,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1232,NULL);
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (67,79,65,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1232,NULL),
 (68,79,66,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1232,NULL),
 (69,79,67,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1258,NULL),
 (70,79,68,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1303,NULL),
 (71,79,69,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1310,NULL),
 (72,79,70,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1312,NULL),
 (73,79,71,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1409,NULL),
 (74,79,72,1,'10000','90.00','1000','9.00','90','1090',NULL,13990520,1420,NULL);
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (75,79,73,1,'10000','92.00','800','9.00','72','872',NULL,13990520,1425,NULL),
 (76,79,74,1,'10000','90.00','1000','0.00','0','1000',NULL,13990520,1432,NULL),
 (77,79,75,3,'30000','10.00','27000','9.00','2430','29430','',13990520,1434,NULL),
 (78,77,76,1,'450000','4.44','430000','9.00','38700','468700',NULL,13990521,1058,NULL),
 (79,78,76,1,'180000','11.11','160000','9.00','14400','174400',NULL,13990521,1058,NULL),
 (80,79,76,1,'10000','90.00','1000','9.00','90','1090',NULL,13990521,1058,NULL),
 (81,79,77,1,'10000','90.00','1000','9.00','90','1090',NULL,13990521,1059,NULL),
 (82,79,78,1,'10000','90.00','1000','9.00','90','1090',NULL,13990521,1101,NULL);
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (83,78,79,1,'180000','11.11','160000','9.00','14400','174400',NULL,13990525,1728,NULL),
 (84,79,79,1,'10000','90.00','1000','9.00','90','1090',NULL,13990525,1728,NULL),
 (85,75,80,1,'30000000','0.00','30000000','0.00','0','30000000',NULL,13990527,1639,NULL),
 (86,83,84,1,'250000','0.00','250000','9.00','22500','272500',NULL,13990911,550,NULL),
 (87,83,85,1,'250000','0.00','250000','9.00','22500','272500',NULL,13990911,556,NULL),
 (88,92,85,1,'200000','0.00','200000','0.00','0','200000',NULL,13990911,556,NULL),
 (89,83,86,1,'250000','0.00','250000','0.00','0','250000',NULL,13990911,559,NULL);
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (90,92,86,1,'200000','0.00','200000','0.00','0','200000',NULL,13990911,559,NULL),
 (91,83,87,1,'250000','0.00','250000','0.00','0','250000',NULL,13990911,1827,NULL),
 (92,92,87,1,'200000','0.00','200000','0.00','0','200000',NULL,13990911,1827,NULL),
 (93,83,88,1,'250000','0.00','250000','0.00','0','250000',NULL,13990912,1756,NULL),
 (94,92,88,1,'200000','0.00','200000','0.00','0','200000',NULL,13990912,1756,NULL),
 (95,83,89,1,'250000','0.00','250000','0.00','0','250000',NULL,13990912,1756,'پرداخت شده'),
 (96,92,89,1,'200000','0.00','200000','0.00','0','200000',NULL,13990912,1756,'پرداخت شده');
INSERT INTO `product_factor_item` (`id`,`product_factor_item_productId`,`product_factor_item_factorId`,`product_factor_item_quantity`,`product_factor_item_originalPrice`,`product_factor_item_discountPercent`,`product_factor_item_priceAfterDiscount`,`product_factor_item_percentageOfValueAdded`,`product_factor_item_valueAdded`,`product_factor_item_totalPrice`,`product_factor_item_discription`,`product_factor_item_date`,`product_factor_item_time`,`product_factor_item_statuse`) VALUES 
 (97,83,90,1,'250000','0.00','250000','0.00','0','250000',NULL,13990912,1838,NULL),
 (98,92,90,1,'200000','0.00','200000','0.00','0','200000',NULL,13990912,1838,NULL),
 (99,83,91,1,'250000','0.00','250000','0.00','0','250000',NULL,13990913,33,NULL),
 (100,92,91,1,'200000','0.00','200000','0.00','0','200000',NULL,13990913,33,NULL),
 (101,96,92,1,'10000','0.00','10000','0.00','0','10000',NULL,13990916,1851,'پرداخت شده');
/*!40000 ALTER TABLE `product_factor_item` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_title` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_user_id` varchar(45) DEFAULT NULL,
  `role_date` varchar(45) DEFAULT NULL,
  `role_condition` varchar(10) DEFAULT NULL,
  `role_comment` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`sms`
--

DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sms_text` varchar(500) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_sender` longtext CHARACTER SET utf8,
  `sms_status` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_date` int(10) unsigned NOT NULL DEFAULT '0',
  `sms_receiver` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_receiver_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_receiver_family` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_comment` longtext CHARACTER SET utf8,
  `sms_receiver_id` int(11) NOT NULL DEFAULT '0',
  `sms_subject` longtext CHARACTER SET utf8,
  `sms_characters` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_messageID` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `sms_send_time` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `sms_webService` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`sms`
--

/*!40000 ALTER TABLE `sms` DISABLE KEYS */;
INSERT INTO `sms` (`id`,`sms_text`,`sms_sender`,`sms_status`,`sms_date`,`sms_receiver`,`sms_receiver_name`,`sms_receiver_family`,`sms_comment`,`sms_receiver_id`,`sms_subject`,`sms_characters`,`sms_messageID`,`sms_send_time`,`sms_webService`) VALUES 
 (1,'دوست عزیز درخواست شما درسایت موسسه زبان هفت اقلیم ثبت گردید.سربلندباشید','09125662205','شناسه ی پیامک نامعتبر است',13940522,'','BABAK','','',0,'پیامک  نظرات/پیام ها','0','0','','');
/*!40000 ALTER TABLE `sms` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`sms_config`
--

DROP TABLE IF EXISTS `sms_config`;
CREATE TABLE `sms_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sms_config_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_wsdl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_number` varchar(18) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_key` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_comment` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_status` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_system` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `sms_config_log` longtext CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`sms_config`
--

/*!40000 ALTER TABLE `sms_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_config` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`sms_setting`
--

DROP TABLE IF EXISTS `sms_setting`;
CREATE TABLE `sms_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `smsSetting_apiKey` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_userName` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_pass` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_exclusive_numbers` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_webService` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_wsdl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_domain` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `smsSetting_comment` longtext CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`sms_setting`
--

/*!40000 ALTER TABLE `sms_setting` DISABLE KEYS */;
INSERT INTO `sms_setting` (`id`,`smsSetting_apiKey`,`smsSetting_userName`,`smsSetting_pass`,`smsSetting_exclusive_numbers`,`smsSetting_webService`,`smsSetting_wsdl`,`smsSetting_domain`,`smsSetting_comment`) VALUES 
 (1,'-','atefeh_rashidi@outlook.com','-','30006703323323,300002525,5000160391,10008642,2000235','kavenegar.com','http://api.kavenegar.com/soap/v1.asmx?WSDL','--','');
/*!40000 ALTER TABLE `sms_setting` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tags_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`tags`
--

/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`tice_config`
--

DROP TABLE IF EXISTS `tice_config`;
CREATE TABLE `tice_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Tice_config_Name` varchar(100) NOT NULL DEFAULT '0',
  `Tice_config_value` longtext CHARACTER SET utf8,
  `Tice_config_type` varchar(100) NOT NULL DEFAULT '0',
  `Tice_config_lable` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`tice_config`
--

/*!40000 ALTER TABLE `tice_config` DISABLE KEYS */;
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (1,'config_smsNumber_name','1000596446','0','شماره پیامکی سامانه کاوه نگار'),
 (2,'config_regexPass_name','.*','0','عبارات منظم برای پسورد'),
 (3,'config_activeSms_name','1','0',' پیامک فعال باشد؟'),
 (4,'config_activeEmail_name','1','0','ایمیل فعال باشد؟'),
 (5,'config_apiKey_sms_name','767351382F2F31667550614A71467542514D55464D377757507256633745575358594958396A4E747253453D','0',' Api پیام'),
 (6,'config_activeSmsLogin_name','0','0','پیامک  هنگام ورود به سامانه فعال باشد؟'),
 (7,'config_activeEmailLogin_name','0','0','ایمیل هنگام ورود به سامانه فعال باشد؟'),
 (8,'config_activeSmsModuleCommittee_name','1','0','پیامک ماژول کمیته ها فعال باشد؟'),
 (9,'config_activeEmailModuleCommittee_name','1','0','ایمیل ماژول کمیته ها فعال باشد؟'),
 (10,'config_activeSmsModulePlans_name','1','0','پیامک ماژول برنامه عملیاتی فعال باشد؟');
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (11,'config_activeEmailModulePlans_name','1','0','ایمیل ماژول برنامه عملیاتی فعال باشد؟'),
 (12,'config_activeSmsModuleDocumentary_name','1','0','پیامک ماژول مستندات فعال باشد(امضا)؟'),
 (13,'config_activeEmailModuleDocumentary_name','1','0','ایمیل ماژول مستندات فعال باشد(امضا)؟'),
 (14,'config_activeSmsChangeProfile_name','1','0','پیامک تغییر پروفایل فعال باشد؟'),
 (15,'config_activeEmailChangeProfile_name','1','0','ایمیل تغییر پروفایل فعال باشد؟'),
 (16,'config_emailAccount_name','medyarwebofficial@gmail.com','0',' حساب ایمیل'),
 (17,'config_passEmail_name','Mm654321','0',' پسورد ایمیل'),
 (19,'config_reminderDayBeforeNextSessions_name','-1','0',' تعداد روز برای یادآوری قبل از تاریخ جلسه بعد هر صورتجلسه'),
 (20,'config_reminderDayBeforeStepsStartDate_name','-1','0','تعداد روز برای یادآوری قبل از شروع انجام فرصت گام');
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (21,'config_reminderDayBeforeStepsEndDate_name','-1','0','تعداد روز برای یادآوری قبل  از پایان انجام فرصت گام'),
 (22,'config_reminderDayBeforeApprovedStartDate_name','-1','0','تعداد روز برای یادآوری قبل ازشروع انجام فرصت مصوبه'),
 (23,'config_reminderDayBeforeApprovedEndDate_name','-1','0','تعداد روز برای یادآوری قبل از پایان انجام فرصت مصوبه'),
 (24,'config_communicateofSessionApprovals_name','لطفا نسبت  به اجرا و پیگیری این مصوبه در تاریخ مقرر اقدام فرمایید','0','پیام ابلاغ مصوبات صورتجلسه'),
 (25,'config_sendPlantoSupervisor_name','لطفا نسبت به بررسی و تایید این برنامه عملیاتی اقدام فرمایید','0','پیام ارسال برنامه عملیاتی به مافوق'),
 (26,'config_sendPlantoManager_name','لطفا نسبت به تایید و ابلاغ برنامه عملیاتی مذکور اقدام فرمایید','0','پیام  ارسال برنامه عملیاتی به مدیر');
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (27,'config_communicateofPlanSteps_name','لطفا نسبت به اجرای گام ها در تاریخ مقرر اقدام فرمایید','0','پیام   ابلاغ گام های برنامه عملیاتی'),
 (28,'config_sendPlantoCommittee_name','لطفا نسبت به بررسی و تایید  مصوبه پیشنهادی در کمیته مذکور اقدام فرمایید','0','پیام  ارسال برنامه عملیاتی به کمیته'),
 (29,'config_sendPlantoImproveQuality_name','لطفا نسبت به بررسی و تایید این برنامه عملیاتی /بهبود کیفیت  مذکوراقدام فرمایید','0','پیام ارسال برنامه عملیاتی به مسئول بهبود کیفیت'),
 (30,'config_requestEditingthePlan_name','لطفا نسبت به بررسی و تایید برنامه عملیاتی مذکور اقدام نمایید','0','پیام  درخواست ویرایش برنامه عملیاتی'),
 (31,'config_offerFormtotheCommittee_name',' لطفا نسبت به تایید  یا رد  مصوبه پیشنهادی در کمیته مذکور اقدام فرمایید','0','پیام ارسال فرم پیشنهادی به کمیته');
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (32,'config_invitationCommittee_name','','0',NULL),
 (33,'config_sendSessionToCommunicatorRole_name','لطفا نسبت به بررسی و تایید صورتجلسه مذکور اقدام نمایید','0',' پیام  ارسال صورتجلسه به مسئول ابلاغ'),
 (34,'config_rejectOffer_name','رد شد','0','پیام رد کردن مصوبه پیشنهادی'),
 (35,'config_accseptOffer_name',' تایید شد','0','پیام پذیرش مصوبه پیشنهادی'),
 (36,'config_activeSmsModuleIndicators_name','1','0','پیامک ماژول شاخص فعال باشد؟'),
 (37,'config_activeEmailModuleIndicators_name','1','0','ایمیل ماژول شاخص فعال باشد؟'),
 (38,'config_loginMobileWithToken_name','1','0','ورود با توکن در اپ'),
 (39,'config_loginMobileWithTokenJustUniqDevice_name','0','0','ورود با توکن فقط با یک گوشی همراه'),
 (40,'config_activeSmsModulePosition_name','1','0','پیامک ماژول جایگاه فعال باشد؟');
INSERT INTO `tice_config` (`id`,`Tice_config_Name`,`Tice_config_value`,`Tice_config_type`,`Tice_config_lable`) VALUES 
 (41,'config_activeEmailModulePosition_name','1','0','ایمیل ماژول جایگاه فعال باشد؟'),
 (42,'config_reminderDayBeforeNextCreateDocumentary_name','-1','0',' تعداد روز برای یادآوری قبل از تاریخ بازنگری مستند هر مستند'),
 (43,'config_activeSmsModuleStrategic_name','1','0','پیامک ماژول برنامه استراتژیک فعال باشد؟'),
 (44,'config_activeEmailModuleStrategic_name','1','0','ایمیل ماژول برنامه استراتژیک فعال باشد؟'),
 (45,'config_zipcodeCompany',NULL,'0','کد پستی '),
 (46,'config_tellCompany',NULL,'0','شماره تلفن'),
 (47,'config_addressCompany',NULL,'0','آدرس'),
 (48,'config_economicCode',NULL,'0','کد اقتصادی'),
 (49,'config_nationalCode',NULL,'0','شناسه ملی'),
 (50,'config_companyName',NULL,'0','نام بیمارستان'),
 (51,'config_exchange_unite','ریال','0','واحد پول');
/*!40000 ALTER TABLE `tice_config` ENABLE KEYS */;


--
-- Table structure for table `db_bambo`.`upload`
--

DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `upload_file_name` varchar(455) NOT NULL DEFAULT '',
  `upload_date` varchar(45) DEFAULT NULL,
  `upload_title` varchar(45) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `upload_comment` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `upload_time` varchar(45) DEFAULT NULL,
  `upload_loader` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `upload_logStatus` longtext CHARACTER SET utf8mb4,
  `upload_status` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `upload_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`upload_file_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1353 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_bambo`.`upload`
--

/*!40000 ALTER TABLE `upload` DISABLE KEYS */;
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (495,'p1164316422.png','13980209','',NULL,'11:32:01',' ','',NULL,''),
 (496,'p1417677412.png','13980209','',NULL,'11:58:42',' ','',NULL,'png'),
 (497,'p3629535824.png','13980209','',NULL,'12:08:59',' ','',NULL,'png'),
 (498,'p5806473760.png','13980209','',NULL,'12:11:48',' ','',NULL,'png'),
 (499,'p6802248842.png','13980209','',NULL,'12:29:01',' ','',NULL,'png'),
 (500,'p9185211304.png','13980209','',NULL,'12:37:53',' ','',NULL,'png'),
 (501,'p7991046891.png','13980209','',NULL,'12:42:00',' ','',NULL,'png'),
 (502,'p4953188238.jpg','13980209','',NULL,'14:59:01',' ','',NULL,'jpg'),
 (503,'p3875909136.jpg','13980209','',NULL,'15:06:22',' ','',NULL,'jpg'),
 (504,'p0938352939.jpg','13980209','',NULL,'15:12:52',' ','',NULL,'jpg'),
 (505,'p5579093666.jpg','13980209','',NULL,'15:13:51',' ','',NULL,'jpg'),
 (979,'p0045693458.docx','13981009','',NULL,'08:28:37','سید مجید شریعتی','','','docx'),
 (980,'p0045693458.docx','13981009','',NULL,'08:33:21','سید مجید شریعتی','',NULL,'docx');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (981,'p7639212242.jpg','13981009','',NULL,'09:41:30','سید مجید شریعتی','',NULL,'jpg'),
 (982,'p4009366172.jpg','13981009','',NULL,'09:44:22','سید مجید شریعتی','',NULL,'jpg'),
 (983,'p5163878639.jpg','13981009','',NULL,'09:45:57','سید مجید شریعتی','',NULL,'jpg'),
 (984,'p1158060987.png','13981009','',NULL,'09:46:41','سید مجید شریعتی','',NULL,'png'),
 (985,'p3999877425.jpg','13981009','',NULL,'09:54:20','سید مجید شریعتی','',NULL,'jpg'),
 (986,'p3217460170.jpg','13981009','',NULL,'09:55:14','سید مجید شریعتی','',NULL,'jpg'),
 (987,'p5291473149.jpg','13981009','',NULL,'09:57:45','سید مجید شریعتی','',NULL,'jpg'),
 (988,'p5294701281.jpg','13981009','',NULL,'10:03:34','سید مجید شریعتی','',NULL,'jpg'),
 (989,'p8842543255.jpg','13981009','',NULL,'10:10:00','سید مجید شریعتی','',NULL,'jpg'),
 (990,'p1358243616.jpg','13981009','',NULL,'10:26:38','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (991,'p6605705586.jpg','13981009','',NULL,'12:00:05','سید مجید شریعتی','',NULL,'jpg'),
 (992,'p1488909780.jpg','13981009','',NULL,'12:01:34','سید مجید شریعتی','',NULL,'jpg'),
 (993,'p2366545456.jpg','13981009','',NULL,'12:02:44','سید مجید شریعتی','',NULL,'jpg'),
 (994,'p4506978941.jpg','13981009','vnbvnbvnb',NULL,'13:25:13','سید مجید شریعتی','',NULL,'jpg'),
 (995,'p8705066643.jpg','13981009','',NULL,'14:25:52','سید مجید شریعتی','',NULL,'jpg'),
 (996,'p7892404819.jpg','13981009','',NULL,'14:27:11','سید مجید شریعتی','',NULL,'jpg'),
 (997,'p9655108545.jpg','13981009','',NULL,'14:27:40','سید مجید شریعتی','',NULL,'jpg'),
 (998,'p0609091958.jpg','13981009','',NULL,'14:27:53','سید مجید شریعتی','',NULL,'jpg'),
 (999,'p9590984329.jpg','13981009','',NULL,'14:31:55','سید مجید شریعتی','',NULL,'jpg'),
 (1000,'p1791523405.jpg','13981009','fvvsakjhdskajhkjahd',NULL,'14:33:07','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1001,'p6526733191.jpg','13981009','',NULL,'14:34:35','سید مجید شریعتی','',NULL,'jpg'),
 (1002,'p9713876936.jpg','13981009','',NULL,'14:39:33','سید مجید شریعتی','',NULL,'jpg'),
 (1003,'p3615469403.jpg','13981009','',NULL,'14:39:54','سید مجید شریعتی','',NULL,'jpg'),
 (1004,'p2458940595.jpg','13981009','',NULL,'14:41:31','سید مجید شریعتی','',NULL,'jpg'),
 (1005,'p6561291768.jpg','13981009','',NULL,'14:44:22','سید مجید شریعتی','',NULL,'jpg'),
 (1006,'p8997928310.jpg','13981009','',NULL,'14:44:33','سید مجید شریعتی','',NULL,'jpg'),
 (1007,'p7005192705.jpg','13981009','مامامامامامن',NULL,'14:46:05','سید مجید شریعتی','',NULL,'jpg'),
 (1008,'p9413679529.jpg','13981009','مرییم',NULL,'14:47:07','سید مجید شریعتی','',NULL,'jpg'),
 (1009,'p8978606902.jpg','13981009','',NULL,'14:48:27','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1010,'p3822535947.jpg','13981009','kjhgfgfhgfhgfhg',NULL,'14:49:19','سید مجید شریعتی','',NULL,'jpg'),
 (1011,'p8259062664.jpg','13981009','',NULL,'14:50:20','سید مجید شریعتی','',NULL,'jpg'),
 (1012,'p3840373013.jpg','13981009','kjhbvchjk',NULL,'14:56:06','سید مجید شریعتی','',NULL,'jpg'),
 (1013,'p0396170776.jpg','13981009','',NULL,'15:31:58','سید مجید شریعتی','',NULL,'jpg'),
 (1014,'p3447966259.jpg','13981010','',NULL,'08:22:16','سید مجید شریعتی','',NULL,'jpg'),
 (1015,'p0580976753.jpg','13981010','',NULL,'08:24:42','سید مجید شریعتی','',NULL,'jpg'),
 (1016,'p9188605756.jpg','13981010','',NULL,'08:25:20','سید مجید شریعتی','',NULL,'jpg'),
 (1017,'p1107363159.jpg','13981010','',NULL,'08:34:01','سید مجید شریعتی','',NULL,'jpg'),
 (1018,'p1244082481.jpg','13981010','',NULL,'08:37:56','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1019,'p6975077991.jpg','13981010','',NULL,'08:41:58','سید مجید شریعتی','',NULL,'jpg'),
 (1020,'p8726055884.jpg','13981010','',NULL,'08:45:46','سید مجید شریعتی','',NULL,'jpg'),
 (1021,'p0554971349.jpg','13981010','undefined',NULL,'08:50:58','سید مجید شریعتی','',NULL,'jpg'),
 (1022,'p2159828761.jpg','13981010','undefined',NULL,'08:52:24','سید مجید شریعتی','',NULL,'jpg'),
 (1023,'p0634156218.jpg','13981010','undefined',NULL,'08:52:59','سید مجید شریعتی','',NULL,'jpg'),
 (1024,'p5314608124.jpg','13981010','undefined',NULL,'15:05:53','سید مجید شریعتی','',NULL,'jpg'),
 (1025,'p0738761569.jpg','13981011','undefined',NULL,'11:50:57','سید مجید شریعتی','',NULL,'jpg'),
 (1026,'p9107339508.png','13981011','undefined',NULL,'11:54:12','سید مجید شریعتی','',NULL,'png'),
 (1027,'p0581079982.jpg','13981011','undefined',NULL,'11:55:58','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1028,'p4233293808.jpg','13981011','undefined',NULL,'12:00:21','سید مجید شریعتی','',NULL,'jpg'),
 (1029,'p7887686422.jpg','13981011','undefined',NULL,'12:03:59','سید مجید شریعتی','',NULL,'jpg'),
 (1030,'p5604072401.jpg','13981015','undefined',NULL,'15:19:40','سید مجید شریعتی','',NULL,'jpg'),
 (1031,'p7510318580.jpg','13981015','undefined',NULL,'15:21:46','سید مجید شریعتی','',NULL,'jpg'),
 (1032,'p2260137556.jpg','13981018','undefined',NULL,'08:35:38','سید مجید شریعتی','',NULL,'jpg'),
 (1033,'p8178801332.jpg','13981018','undefined',NULL,'08:37:32','سید مجید شریعتی','',NULL,'jpg'),
 (1034,'p3499493172.jpg','13981018','undefined',NULL,'08:38:15','سید مجید شریعتی','',NULL,'jpg'),
 (1035,'p8149406638.jpg','13981018','undefined',NULL,'08:40:15','سید مجید شریعتی','',NULL,'jpg'),
 (1036,'p9517985613.jpg','13981018','undefined',NULL,'08:46:24','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1037,'p4703552165.jpg','13981018','undefined',NULL,'08:48:27','سید مجید شریعتی','',NULL,'jpg'),
 (1038,'p1125153838.jpg','13981018','undefined',NULL,'08:55:50','سید مجید شریعتی','',NULL,'jpg'),
 (1039,'p5195566403.jpeg','13981018','undefined',NULL,'08:58:17','سید مجید شریعتی','',NULL,'jpeg'),
 (1040,'p9291597598.jpg','13981018','undefined',NULL,'08:58:54','سید مجید شریعتی','',NULL,'jpg'),
 (1041,'p7890366280.jpg','13981018','undefined',NULL,'09:00:03','سید مجید شریعتی','',NULL,'jpg'),
 (1042,'p7833644926.jpg','13981018','undefined',NULL,'09:00:45','سید مجید شریعتی','',NULL,'jpg'),
 (1043,'p2598616968.jpg','13981018','undefined',NULL,'09:01:40','سید مجید شریعتی','',NULL,'jpg'),
 (1044,'p5702593855.jpg','13981018','undefined',NULL,'09:06:06','سید مجید شریعتی','',NULL,'jpg'),
 (1045,'p8437498857.jpg','13981018','',NULL,'14:20:59','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1046,'p7456446766.jpg','13981018','',NULL,'14:28:42','سید مجید شریعتی','',NULL,'jpg'),
 (1047,'p3877588160.jpg','13981018','',NULL,'14:32:13','سید مجید شریعتی','',NULL,'jpg'),
 (1048,'p9662024086.jpg','13981018','undefined',NULL,'14:38:22','سید مجید شریعتی','',NULL,'jpg'),
 (1049,'p2179815493.jpg','13981018','undefined',NULL,'14:43:53',' ','',NULL,'jpg'),
 (1050,'p0292386964.jpg','13981018','undefined',NULL,'14:45:36','سید مجید شریعتی','',NULL,'jpg'),
 (1051,'p5389387416.jpg','13981021','undefined',NULL,'08:21:33','سید مجید شریعتی','',NULL,'jpg'),
 (1052,'p7500654460.jpg','13981025','undefined',NULL,'15:57:19','سید مجید شریعتی','',NULL,'jpg'),
 (1053,'p8421821396.jpg','13981030','undefined',NULL,'14:46:45','سید مجید شریعتی','',NULL,'jpg'),
 (1054,'p2390272950.jpg','13981030','',NULL,'14:49:03','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1055,'p9468453885.jpg','13981030','',NULL,'15:09:40','سید مجید شریعتی','',NULL,'jpg'),
 (1056,'p7348289342.jpg','13981030','undefined',NULL,'15:17:13','سید مجید شریعتی','',NULL,'jpg'),
 (1057,'p7222436653.jpg','13981030','undefined',NULL,'15:18:04','سید مجید شریعتی','',NULL,'jpg'),
 (1058,'p9003625771.jpg','13981030','undefined',NULL,'15:18:12','سید مجید شریعتی','',NULL,'jpg'),
 (1059,'p6634216930.jpg','13981030','undefined',NULL,'15:24:07','سید مجید شریعتی','',NULL,'jpg'),
 (1060,'p2733435745.jpg','13981030','undefined',NULL,'15:24:15','سید مجید شریعتی','',NULL,'jpg'),
 (1061,'p9881051667.jpg','13981030','undefined',NULL,'15:24:51','سید مجید شریعتی','',NULL,'jpg'),
 (1062,'p9638585598.jpg','13981030','',NULL,'15:30:22','سید مجید شریعتی','',NULL,'jpg'),
 (1063,'p9284026422.jpg','13981030','undefined',NULL,'15:31:33','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1064,'p3977008552.jpg','13981105','undefined',NULL,'09:09:11','سید مجید شریعتی','',NULL,'jpg'),
 (1065,'p4592043234.jpg','13981105','undefined',NULL,'09:09:49','سید مجید شریعتی','',NULL,'jpg'),
 (1066,'p5482602535.jpg','13981105','undefined',NULL,'09:14:55','سید مجید شریعتی','',NULL,'jpg'),
 (1067,'p4931096917.jpg','13981105','undefined',NULL,'09:15:28','سید مجید شریعتی','',NULL,'jpg'),
 (1068,'p2197829771.jpg','13981105','undefined',NULL,'09:18:53','سید مجید شریعتی','',NULL,'jpg'),
 (1069,'p1844815946.jpg','13981105','undefined',NULL,'09:54:38','سید مجید شریعتی','',NULL,'jpg'),
 (1070,'p1727555695.jpg','13981105','undefined',NULL,'14:29:06','سید مجید شریعتی','',NULL,'jpg'),
 (1071,'p1324335628.jpg','13981106','undefined',NULL,'10:41:14','سید مجید شریعتی','',NULL,'jpg'),
 (1072,'p3521256685.jpg','13981106','undefined',NULL,'10:41:43','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1073,'p0835728602.jpg','13981106','undefined',NULL,'11:36:03','سید مجید شریعتی','',NULL,'jpg'),
 (1074,'p1132014942.jpg','13981106','undefined',NULL,'13:45:29','سید مجید شریعتی','',NULL,'jpg'),
 (1075,'p3218921993.jpg','13981106','',NULL,'14:40:52','سید مجید شریعتی','',NULL,'jpg'),
 (1076,'p4187585903.jpg','13981106','undefined',NULL,'14:41:29','سید مجید شریعتی','',NULL,'jpg'),
 (1077,'p1226388934.txt','13981106','',NULL,'14:50:38','سید مجید شریعتی','',NULL,'txt'),
 (1078,'p7601200815.txt','13981106','',NULL,'14:52:08','سید مجید شریعتی','',NULL,'txt'),
 (1079,'p6289739437.jpg','13981213','undefined',NULL,'11:12:32','سید مجید شریعتی','',NULL,'jpg'),
 (1080,'p3867442850.jpg','13981213','undefined',NULL,'11:16:40','سید مجید شریعتی','',NULL,'jpg'),
 (1081,'p4021723540.jpg','13981213','undefined',NULL,'14:22:50','سید مجید شریعتی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1082,'p7053018071.jpg','13981224','undefined',NULL,'14:37:20','افسانه کیانی','',NULL,'jpg'),
 (1083,'p1478309740.jpg','13990207','undefined',NULL,'16:00:42','افسانه کیانی','',NULL,'jpg'),
 (1084,'p8582721830.png','13990208','undefined',NULL,'12:13:12','افسانه کیانی','',NULL,'png'),
 (1085,'p1092005044.jpg','13990224','',NULL,'10:52:09','افسانه کیانی','',NULL,'jpg'),
 (1086,'p8933731360.jpg','13990224','',NULL,'11:07:13','افسانه کیانی','',NULL,'jpg'),
 (1087,'p6710479623.jpg','13990224','',NULL,'11:13:39','افسانه کیانی','',NULL,'jpg'),
 (1088,'p1100907805.jpg','13990224','',NULL,'11:20:40','افسانه کیانی','',NULL,'jpg'),
 (1089,'p0742482695.jpg','13990224','',NULL,'11:30:59','افسانه کیانی','',NULL,'jpg'),
 (1090,'p9292298333.jpg','13990224','undefined',NULL,'16:34:30','افسانه کیانی','',NULL,'jpg'),
 (1091,'p7150380101.jpg','13990226','',NULL,'02:31:23','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1092,'p0752947472.jpg','13990226','',NULL,'02:43:11','افسانه کیانی','',NULL,'jpg'),
 (1093,'p1108214695.jpg','13990226','',NULL,'02:58:34','افسانه کیانی','',NULL,'jpg'),
 (1094,'p0800037158.jpg','13990226','',NULL,'22:28:38','افسانه کیانی','',NULL,'jpg'),
 (1095,'p1744219016.jpg','13990226','',NULL,'22:33:32','افسانه کیانی','',NULL,'jpg'),
 (1096,'p2431462652.png','13990226','',NULL,'22:51:40','افسانه کیانی','',NULL,'png'),
 (1097,'p2784123033.png','13990226','',NULL,'22:53:24','افسانه کیانی','',NULL,'png'),
 (1098,'p5347196682.png','13990226','',NULL,'22:55:25','افسانه کیانی','',NULL,'png'),
 (1099,'p7177013666.jpg','13990226','',NULL,'22:56:29','افسانه کیانی','',NULL,'jpg'),
 (1100,'p0714519205.jpg','13990226','',NULL,'22:57:05','افسانه کیانی','',NULL,'jpg'),
 (1101,'p2980425802.png','13990226','',NULL,'22:57:34','افسانه کیانی','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1102,'p1488563098.jpg','13990227','undefined',NULL,'09:51:08','افسانه کیانی','',NULL,'jpg'),
 (1103,'p9797540763.jpg','13990227','undefined',NULL,'10:35:19','افسانه کیانی','',NULL,'jpg'),
 (1104,'p6902309243.jpg','13990227','undefined',NULL,'10:42:18','افسانه کیانی','',NULL,'jpg'),
 (1105,'p2900346949.jpg','13990227','undefined',NULL,'12:57:22','افسانه کیانی','',NULL,'jpg'),
 (1106,'p3160653887.jpg','13990227','undefined',NULL,'13:05:12','افسانه کیانی','',NULL,'jpg'),
 (1107,'p6413962096.jpg','13990227','undefined',NULL,'13:10:51','افسانه کیانی','',NULL,'jpg'),
 (1108,'p9037313872.jpg','13990227','undefined',NULL,'13:17:29','افسانه کیانی','',NULL,'jpg'),
 (1109,'p9203756422.jpg','13990227','undefined',NULL,'13:31:13','افسانه کیانی','',NULL,'jpg'),
 (1110,'p8186402553.jpg','13990227','undefined',NULL,'13:34:02','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1111,'p3089393133.jpg','13990227','undefined',NULL,'13:36:25','افسانه کیانی','',NULL,'jpg'),
 (1112,'p3492680539.jpg','13990227','undefined',NULL,'13:45:07','افسانه کیانی','',NULL,'jpg'),
 (1113,'p4620131534.jpg','13990227','undefined',NULL,'13:49:21','افسانه کیانی','',NULL,'jpg'),
 (1114,'p2236386910.jpg','13990227','undefined',NULL,'13:52:42','افسانه کیانی','',NULL,'jpg'),
 (1115,'p6104478879.jpg','13990227','undefined',NULL,'15:10:05','افسانه کیانی','',NULL,'jpg'),
 (1116,'p4551977139.jpg','13990227','undefined',NULL,'15:12:19','افسانه کیانی','',NULL,'jpg'),
 (1117,'p3676194838.jpg','13990227','undefined',NULL,'15:25:30','افسانه کیانی','',NULL,'jpg'),
 (1118,'p9696905523.jpg','13990227','undefined',NULL,'15:25:39','افسانه کیانی','',NULL,'jpg'),
 (1119,'p2563041674.jpg','13990227','undefined',NULL,'15:32:29','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1120,'p8585061341.jpg','13990227','undefined',NULL,'15:35:47','افسانه کیانی','',NULL,'jpg'),
 (1121,'p6097406073.jpg','13990227','undefined',NULL,'15:44:36','افسانه کیانی','',NULL,'jpg'),
 (1122,'p4335103194.jpg','13990227','undefined',NULL,'15:47:08','افسانه کیانی','',NULL,'jpg'),
 (1123,'p7865608616.jpg','13990227','undefined',NULL,'15:51:55','افسانه کیانی','',NULL,'jpg'),
 (1124,'p8365525585.jpg','13990227','undefined',NULL,'15:55:21','افسانه کیانی','',NULL,'jpg'),
 (1125,'p4338930924.jpg','13990227','undefined',NULL,'16:01:10','افسانه کیانی','',NULL,'jpg'),
 (1126,'p6204990671.jpg','13990227','undefined',NULL,'16:02:43','افسانه کیانی','',NULL,'jpg'),
 (1127,'p2123861968.jpg','13990227','undefined',NULL,'16:04:12','افسانه کیانی','',NULL,'jpg'),
 (1128,'p4453648235.jpg','13990227','undefined',NULL,'16:05:22','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1129,'p1027812261.jpg','13990227','undefined',NULL,'16:13:43','افسانه کیانی','',NULL,'jpg'),
 (1130,'p1910481766.jpg','13990227','undefined',NULL,'16:17:25','افسانه کیانی','',NULL,'jpg'),
 (1131,'p3031855173.jpg','13990227','undefined',NULL,'16:58:30','افسانه کیانی','',NULL,'jpg'),
 (1132,'p3861079225.jpg','13990227','undefined',NULL,'17:01:50','افسانه کیانی','',NULL,'jpg'),
 (1133,'p2222413044.jpg','13990227','undefined',NULL,'17:04:42','افسانه کیانی','',NULL,'jpg'),
 (1134,'p7893725116.jpg','13990227','undefined',NULL,'17:08:02','افسانه کیانی','',NULL,'jpg'),
 (1135,'p1144005587.jpg','13990227','undefined',NULL,'17:11:35','افسانه کیانی','',NULL,'jpg'),
 (1136,'p2779788580.jpg','13990227','undefined',NULL,'17:13:35','افسانه کیانی','',NULL,'jpg'),
 (1137,'p1936210978.jpg','13990227','undefined',NULL,'17:16:58','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1138,'p3223643170.jpg','13990227','undefined',NULL,'17:19:40','افسانه کیانی','',NULL,'jpg'),
 (1139,'p3207146031.jpg','13990227','undefined',NULL,'17:21:59','افسانه کیانی','',NULL,'jpg'),
 (1140,'p3370701665.jpg','13990227','undefined',NULL,'17:24:30','افسانه کیانی','',NULL,'jpg'),
 (1141,'p5726543820.jpg','13990227','undefined',NULL,'17:29:35','افسانه کیانی','',NULL,'jpg'),
 (1142,'p0478734710.jpg','13990227','undefined',NULL,'17:31:45','افسانه کیانی','',NULL,'jpg'),
 (1143,'p6703673274.jpg','13990227','undefined',NULL,'17:34:15','افسانه کیانی','',NULL,'jpg'),
 (1144,'p8132834273.jpg','13990227','undefined',NULL,'17:36:18','افسانه کیانی','',NULL,'jpg'),
 (1145,'p6314921291.jpg','13990227','undefined',NULL,'17:38:11','افسانه کیانی','',NULL,'jpg'),
 (1146,'p0749836237.jpg','13990227','undefined',NULL,'17:40:25','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1147,'p4085107233.jpg','13990227','undefined',NULL,'17:42:33','افسانه کیانی','',NULL,'jpg'),
 (1148,'p9211631683.jpg','13990227','undefined',NULL,'17:44:42','افسانه کیانی','',NULL,'jpg'),
 (1149,'p2184028419.jpg','13990228','undefined',NULL,'00:12:09','افسانه کیانی','',NULL,'jpg'),
 (1150,'p1532677681.jpg','13990228','undefined',NULL,'00:16:03','افسانه کیانی','',NULL,'jpg'),
 (1151,'p8310132954.jpg','13990228','undefined',NULL,'00:20:50','افسانه کیانی','',NULL,'jpg'),
 (1152,'p4155604547.jpg','13990228','undefined',NULL,'00:25:27','افسانه کیانی','',NULL,'jpg'),
 (1153,'p0887570275.jpg','13990228','undefined',NULL,'00:29:07','افسانه کیانی','',NULL,'jpg'),
 (1154,'p3776409750.jpg','13990228','',NULL,'11:39:37','افسانه کیانی','',NULL,'jpg'),
 (1155,'p6738429456.png','13990228','',NULL,'16:35:05','افسانه کیانی','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1156,'p2206503730.png','13990228','',NULL,'16:38:53','افسانه کیانی','',NULL,'png'),
 (1157,'p4739981256.jpg','13990229','undefined',NULL,'12:36:36','افسانه کیانی','',NULL,'jpg'),
 (1158,'p8613663614.jpg','13990229','undefined',NULL,'12:36:41','افسانه کیانی','',NULL,'jpg'),
 (1159,'p7404438179.jpg','13990229','undefined',NULL,'12:37:00','افسانه کیانی','',NULL,'jpg'),
 (1160,'p9352579639.png','13990303','undefined',NULL,'13:13:07','افسانه کیانی','',NULL,'png'),
 (1161,'p0210254707.png','13990303','undefined',NULL,'13:13:16','افسانه کیانی','',NULL,'png'),
 (1162,'p3134473214.jpg','13990303','undefined',NULL,'13:13:33','افسانه کیانی','',NULL,'jpg'),
 (1163,'p0129507485.jpg','13990309','',NULL,'01:29:19','افسانه کیانی','',NULL,'jpg'),
 (1164,'p3289409067.jpg','13990309','',NULL,'01:59:28','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1165,'p8846450743.jpg','13990309','',NULL,'02:03:28','افسانه کیانی','',NULL,'jpg'),
 (1166,'p1394627816.jpg','13990309','',NULL,'02:06:52','افسانه کیانی','',NULL,'jpg'),
 (1167,'p1498733187.jpg','13990309','',NULL,'02:08:18','افسانه کیانی','',NULL,'jpg'),
 (1168,'p9612825899.jpg','13990309','',NULL,'02:12:01','افسانه کیانی','',NULL,'jpg'),
 (1169,'p9312572397.jpg','13990309','',NULL,'21:58:31','افسانه کیانی','',NULL,'jpg'),
 (1170,'p2700924114.jpg','13990309','',NULL,'23:27:28','افسانه کیانی','',NULL,'jpg'),
 (1171,'p8563983640.jpg','13990309','',NULL,'23:56:02','افسانه کیانی','',NULL,'jpg'),
 (1172,'p2339211159.jpg','13990310','',NULL,'16:03:55','افسانه کیانی','',NULL,'jpg'),
 (1173,'p8348286818.jpg','13990310','',NULL,'16:08:42','افسانه کیانی','',NULL,'jpg'),
 (1174,'p1038334577.jpg','13990310','',NULL,'16:54:26','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1175,'p5159056898.jpg','13990310','',NULL,'17:10:43','افسانه کیانی','',NULL,'jpg'),
 (1176,'p7924398935.jpg','13990311','',NULL,'15:30:19','افسانه کیانی','',NULL,'jpg'),
 (1177,'p0223393899.jpg','13990311','',NULL,'16:08:17','افسانه کیانی','',NULL,'jpg'),
 (1178,'p8155276596.jpg','13990312','',NULL,'16:08:46','افسانه کیانی','',NULL,'jpg'),
 (1179,'p8657452795.jpg','13990312','',NULL,'16:15:03','افسانه کیانی','',NULL,'jpg'),
 (1180,'p7097286025.jpg','13990312','',NULL,'16:21:58','افسانه کیانی','',NULL,'jpg'),
 (1181,'p3239826975.jpg','13990312','',NULL,'16:35:02','افسانه کیانی','',NULL,'jpg'),
 (1182,'p1515234986.jpg','13990312','',NULL,'16:47:28','افسانه کیانی','',NULL,'jpg'),
 (1183,'p6792925785.jpg','13990312','',NULL,'16:59:11','افسانه کیانی','',NULL,'jpg'),
 (1184,'p5589569385.jpg','13990312','',NULL,'17:06:39','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1185,'p7131871922.png','13990313','',NULL,'16:21:24','افسانه کیانی','',NULL,'png'),
 (1186,'p1345320553.jpg','13990317','',NULL,'10:13:07','افسانه کیانی','',NULL,'jpg'),
 (1187,'p2620546710.jpg','13990317','',NULL,'10:19:45','افسانه کیانی','',NULL,'jpg'),
 (1188,'p2126185803.jpg','13990317','',NULL,'11:00:39','افسانه کیانی','',NULL,'jpg'),
 (1189,'p9493153354.jpg','13990317','',NULL,'11:05:32','افسانه کیانی','',NULL,'jpg'),
 (1190,'p5392401736.jpg','13990317','',NULL,'11:07:32','افسانه کیانی','',NULL,'jpg'),
 (1191,'p8089187467.jpg','13990317','',NULL,'11:09:05','افسانه کیانی','',NULL,'jpg'),
 (1192,'p5668758808.jpg','13990317','',NULL,'13:09:49','افسانه کیانی','',NULL,'jpg'),
 (1193,'p0639816763.jpg','13990317','',NULL,'13:19:23','افسانه کیانی','',NULL,'jpg'),
 (1194,'p3214868282.jpg','13990317','',NULL,'14:52:00','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1195,'p6469962725.jpg','13990317','',NULL,'15:31:42','افسانه کیانی','',NULL,'jpg'),
 (1196,'p2511422533.jpg','13990319','',NULL,'15:43:38','افسانه کیانی','',NULL,'jpg'),
 (1197,'p6176965277.jpg','13990319','',NULL,'15:49:27','افسانه کیانی','',NULL,'jpg'),
 (1198,'p4201847824.jpg','13990319','',NULL,'15:52:56','افسانه کیانی','',NULL,'jpg'),
 (1199,'p9339365843.jpg','13990319','',NULL,'15:57:19','افسانه کیانی','',NULL,'jpg'),
 (1200,'p4781112139.jpg','13990319','',NULL,'15:59:16','افسانه کیانی','',NULL,'jpg'),
 (1201,'p6590004577.jpg','13990319','',NULL,'16:02:23','افسانه کیانی','',NULL,'jpg'),
 (1202,'p1026081106.jpg','13990319','',NULL,'16:04:13','افسانه کیانی','',NULL,'jpg'),
 (1203,'p5756504534.jpg','13990319','',NULL,'16:10:40','افسانه کیانی','',NULL,'jpg'),
 (1204,'p9611903013.jpg','13990320','',NULL,'11:42:20','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1205,'p3847576610.jpg','13990320','',NULL,'11:46:12','افسانه کیانی','',NULL,'jpg'),
 (1206,'p1231882178.jpg','13990320','',NULL,'11:48:09','افسانه کیانی','',NULL,'jpg'),
 (1207,'p4698307710.jpg','13990320','',NULL,'11:49:28','افسانه کیانی','',NULL,'jpg'),
 (1208,'p4568652579.jpg','13990320','',NULL,'11:50:39','افسانه کیانی','',NULL,'jpg'),
 (1209,'p6269755884.jpg','13990320','',NULL,'11:56:14','افسانه کیانی','',NULL,'jpg'),
 (1210,'p2429200661.jpg','13990320','',NULL,'11:57:42','افسانه کیانی','',NULL,'jpg'),
 (1211,'p5543698720.jpg','13990320','',NULL,'11:58:46','افسانه کیانی','',NULL,'jpg'),
 (1212,'p7403320883.jpg','13990320','',NULL,'11:59:48','افسانه کیانی','',NULL,'jpg'),
 (1213,'p9302239226.jpg','13990320','',NULL,'12:10:47','افسانه کیانی','',NULL,'jpg'),
 (1214,'p4101170485.jpg','13990320','',NULL,'12:11:13','افسانه کیانی','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1215,'p9143346847.jpg','13990320','',NULL,'16:51:14','افسانه کیانی','',NULL,'jpg'),
 (1216,'p8599234091.jpg','13990320','',NULL,'16:53:18','افسانه کیانی','',NULL,'jpg'),
 (1217,'p2851460617.jpg','13990320','',NULL,'17:03:39','افسانه کیانی','',NULL,'jpg'),
 (1218,'p7559992172.jpg','13990320','',NULL,'17:08:52','افسانه کیانی','',NULL,'jpg'),
 (1219,'p7117373349.jpg','13990320','',NULL,'17:14:03','افسانه کیانی','',NULL,'jpg'),
 (1220,'p7850539673.png','13990525','',NULL,'12:09:18','افسانه کیانی','',NULL,'png'),
 (1221,'p5788613986.png','13990525','',NULL,'23:33:23','افسانه کیانی','',NULL,'png'),
 (1222,'p2357661350.jpg','13990526','',NULL,'10:41:31','افسانه کیانی','',NULL,'jpg'),
 (1223,'p4467184252.png','13990526','',NULL,'16:34:11','افسانه کیانی','',NULL,'png'),
 (1224,'p7955142557.png','13990526','',NULL,'17:11:41','افسانه کیانی','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1225,'p4424001845.jpg','13990531','',NULL,'12:14:42','سید مجید شریعتی','',NULL,'jpg'),
 (1226,'p6938766413.jpg','13990531','',NULL,'12:14:49','سید مجید شریعتی','',NULL,'jpg'),
 (1227,'p9428607717.jpg','13990531','',NULL,'17:52:06','ادمین ادمین','',NULL,'jpg'),
 (1228,'p0548383097.jpg','13990531','',NULL,'17:52:13','ادمین ادمین','',NULL,'jpg'),
 (1229,'p1398101396.jpg','13990531','',NULL,'17:52:20','ادمین ادمین','',NULL,'jpg'),
 (1230,'p0962989446.jpg','13990531','p0548383097.jpg',NULL,'18:00:08','ادمین ادمین','',NULL,'jpg'),
 (1231,'p9979395482.jpg','13990531','p9428607717.jpg',NULL,'18:00:15','ادمین ادمین','',NULL,'jpg'),
 (1232,'p2069339924.jpg','13990531','p1398101396.jpg',NULL,'18:00:25','ادمین ادمین','',NULL,'jpg'),
 (1233,'p1511760233.jpg','13990531','p1398101396.jpg',NULL,'18:09:03','ادمین ادمین','',NULL,'jpg'),
 (1234,'p9887385707.jpg','13990531','p0548383097.jpg',NULL,'19:45:59','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1235,'p7371292866.jpg','13990531','p0548383097.jpg',NULL,'19:47:52','ادمین ادمین','',NULL,'jpg'),
 (1236,'p1786654452.jpg','13990531','p0548383097.jpg',NULL,'19:48:35','ادمین ادمین','',NULL,'jpg'),
 (1237,'p6643786805.jpg','13990531','p0548383097.jpg',NULL,'19:50:50','ادمین ادمین','',NULL,'jpg'),
 (1238,'p0055152484.jpg','13990531','p9428607717.jpg',NULL,'19:50:56','ادمین ادمین','',NULL,'jpg'),
 (1239,'p6211684532.jpg','13990531','p1398101396.jpg',NULL,'19:51:05','ادمین ادمین','',NULL,'jpg'),
 (1240,'p0434244342.png','13990605','p6938766413.jpg',NULL,'11:27:03','ادمین ادمین','',NULL,'png'),
 (1241,'p1067056359.jpg','13990613','',NULL,'21:36:08','ادمین ادمین','',NULL,'jpg'),
 (1242,'p6643382455.jpg','13990613','p1067056359.jpg',NULL,'21:40:48','ادمین ادمین','',NULL,'jpg'),
 (1243,'p4696313012.jpg','13990613','p6643382455.jpg',NULL,'21:42:04','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1244,'p2723907127.jpg','13990614','',NULL,'17:54:42','ادمین ادمین','',NULL,'jpg'),
 (1245,'p5810798675.jpg','13990614','p2723907127.jpg',NULL,'17:55:40','ادمین ادمین','',NULL,'jpg'),
 (1246,'p0540670886.jpg','13990614','p5810798675.jpg',NULL,'17:56:04','ادمین ادمین','',NULL,'jpg'),
 (1247,'p4346474382.jpg','13990614','p0540670886.jpg',NULL,'17:56:41','ادمین ادمین','',NULL,'jpg'),
 (1248,'p7139867177.jpg','13990614','p4346474382.jpg',NULL,'17:57:01','ادمین ادمین','',NULL,'jpg'),
 (1249,'p9286783462.jpg','13990614','p7139867177.jpg',NULL,'17:57:51','ادمین ادمین','',NULL,'jpg'),
 (1250,'p7128732742.jpg','13990614','p9286783462.jpg',NULL,'17:58:15','ادمین ادمین','',NULL,'jpg'),
 (1251,'p4256308146.jpg','13990614','p7128732742.jpg',NULL,'17:58:32','ادمین ادمین','',NULL,'jpg'),
 (1252,'p0011705272.jpg','13990614','p4256308146.jpg',NULL,'17:58:57','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1253,'p8046482633.jpg','13990614','p0011705272.jpg',NULL,'17:59:24','ادمین ادمین','',NULL,'jpg'),
 (1254,'p7426690757.jpg','13990614','p8046482633.jpg',NULL,'17:59:45','ادمین ادمین','',NULL,'jpg'),
 (1255,'p0437830718.jpg','13990618','',NULL,'11:40:59','ادمین ادمین','',NULL,'jpg'),
 (1256,'p8885073590.jpg','13990618','p0437830718.jpg',NULL,'11:41:32','ادمین ادمین','',NULL,'jpg'),
 (1257,'p1371016508.jpg','13990618','p8885073590.jpg',NULL,'11:42:27','ادمین ادمین','',NULL,'jpg'),
 (1258,'p7010611706.jpg','13990622','',NULL,'09:53:45','ادمین ادمین','',NULL,'jpg'),
 (1259,'p3375054327.jpg','13990622','p7010611706.jpg',NULL,'11:28:43','ادمین ادمین','',NULL,'jpg'),
 (1260,'p0956763388.jpg','13990622','',NULL,'11:28:54','ادمین ادمین','',NULL,'jpg'),
 (1261,'p8615276990.jpg','13990622','',NULL,'11:29:01','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1262,'p6095317640.jpg','13990622','',NULL,'11:41:43','ادمین ادمین','',NULL,'jpg'),
 (1263,'p1461349907.jpg','13990622','',NULL,'11:41:48','ادمین ادمین','',NULL,'jpg'),
 (1264,'p8305503630.png','13990622','',NULL,'11:41:53','ادمین ادمین','',NULL,'png'),
 (1265,'p7930129819.jpg','13990622','',NULL,'11:51:47','ادمین ادمین','',NULL,'jpg'),
 (1266,'p9220629272.png','13990622','',NULL,'11:51:52','ادمین ادمین','',NULL,'png'),
 (1267,'p0840193646.jpg','13990622','',NULL,'11:52:00','ادمین ادمین','',NULL,'jpg'),
 (1268,'p2470954135.jpg','13990622','',NULL,'12:07:28','ادمین ادمین','',NULL,'jpg'),
 (1269,'p2712909269.jpg','13990622','',NULL,'12:07:36','ادمین ادمین','',NULL,'jpg'),
 (1270,'p5722349860.jpg','13990622','',NULL,'12:07:43','ادمین ادمین','',NULL,'jpg'),
 (1271,'p0578240278.png','13990622','',NULL,'12:14:05','ادمین ادمین','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1272,'p4766783799.jpg','13990622','',NULL,'12:14:12','ادمین ادمین','',NULL,'jpg'),
 (1273,'p2330290715.jpg','13990622','',NULL,'12:14:18','ادمین ادمین','',NULL,'jpg'),
 (1274,'p8607368587.jpg','13990622','',NULL,'14:57:39','ادمین ادمین','',NULL,'jpg'),
 (1275,'p8199682003.png','13990623','',NULL,'00:00:36','ادمین ادمین','',NULL,'png'),
 (1276,'p6952292507.png','13990623','p8199682003.png',NULL,'00:01:51','ادمین ادمین','',NULL,'png'),
 (1277,'p2660023195.png','13990623','p6952292507.png',NULL,'00:02:55','ادمین ادمین','',NULL,'png'),
 (1278,'p6325149369.png','13990623','p2660023195.png',NULL,'00:03:19','ادمین ادمین','',NULL,'png'),
 (1279,'p7903973501.png','13990623','p6325149369.png',NULL,'00:03:40','ادمین ادمین','',NULL,'png'),
 (1280,'p9882857095.png','13990623','p7903973501.png',NULL,'00:04:01','ادمین ادمین','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1281,'p1584187570.png','13990623','p9882857095.png',NULL,'00:04:30','ادمین ادمین','',NULL,'png'),
 (1282,'p4824325325.png','13990623','p1584187570.png',NULL,'00:04:53','ادمین ادمین','',NULL,'png'),
 (1283,'p7375375495.png','13990623','p4824325325.png',NULL,'00:05:11','ادمین ادمین','',NULL,'png'),
 (1284,'p3048036176.png','13990623','p7375375495.png',NULL,'00:05:27','ادمین ادمین','',NULL,'png'),
 (1285,'p4459666294.png','13990623','p3048036176.png',NULL,'00:05:44','ادمین ادمین','',NULL,'png'),
 (1286,'p4734025109.png','13990625','',NULL,'22:23:51','ادمین ادمین','',NULL,'png'),
 (1287,'p9153292378.png','13990625','',NULL,'22:50:17','ادمین ادمین','',NULL,'png'),
 (1288,'p9542538645.png','13990625','p9153292378.png',NULL,'23:09:42','ادمین ادمین','',NULL,'png'),
 (1289,'p9926001931.png','13990625','p9542538645.png',NULL,'23:23:01','ادمین ادمین','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1290,'p8907563662.png','13990625','p9926001931.png',NULL,'23:36:25','ادمین ادمین','',NULL,'png'),
 (1291,'p3297161302.png','13990626','',NULL,'13:55:10','ادمین ادمین','',NULL,'png'),
 (1292,'p4725190611.png','13990626','p3297161302.png',NULL,'14:04:40','ادمین ادمین','',NULL,'png'),
 (1293,'p2138699148.png','13990626','p4725190611.png',NULL,'14:23:26','ادمین ادمین','',NULL,'png'),
 (1294,'p2318806171.png','13990626','p2138699148.png',NULL,'14:41:07','ادمین ادمین','',NULL,'png'),
 (1295,'p7089353622.png','13990708','',NULL,'15:59:20','ادمین ادمین','',NULL,'png'),
 (1296,'p9349964582.png','13990708','',NULL,'23:36:25','ادمین ادمین','',NULL,'png'),
 (1297,'p3045552265.png','13990708','p9349964582.png',NULL,'23:36:26','ادمین ادمین','',NULL,'png'),
 (1298,'p7715311145.png','13990708','',NULL,'23:37:57','ادمین ادمین','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1299,'p4274672940.png','13990708','',NULL,'23:40:16','ادمین ادمین','',NULL,'png'),
 (1300,'p2581918947.jpeg','13990708','',NULL,'23:45:04','ادمین ادمین','',NULL,'jpeg'),
 (1301,'p3912856482.jpg','13990713','',NULL,'11:57:04','ادمین ادمین','',NULL,'jpg'),
 (1302,'p4350747728.jpg','13990713','',NULL,'12:39:38','ادمین ادمین','',NULL,'jpg'),
 (1303,'p8639930649.jpg','13990714','',NULL,'12:08:21','ادمین ادمین','',NULL,'jpg'),
 (1304,'p7683334660.jpg','13990714','',NULL,'12:11:12','ادمین ادمین','',NULL,'jpg'),
 (1305,'p1045387930.jpg','13990714','p7683334660.jpg',NULL,'12:17:01','ادمین ادمین','',NULL,'jpg'),
 (1306,'p4158219906.jpg','13990714','p1045387930.jpg',NULL,'12:28:44','ادمین ادمین','',NULL,'jpg'),
 (1307,'p0355925373.jpg','13990716','',NULL,'11:29:22','ادمین ادمین','',NULL,'jpg'),
 (1308,'p7670363346.jpg','13990719','',NULL,'13:23:37','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1309,'p4538327371.jpg','13990719','',NULL,'13:30:51','ادمین ادمین','',NULL,'jpg'),
 (1310,'p9555422324.jpg','13990719','p4538327371.jpg',NULL,'13:31:24','ادمین ادمین','',NULL,'jpg'),
 (1311,'p6849218048.jpg','13990719','p9555422324.jpg',NULL,'13:31:52','ادمین ادمین','',NULL,'jpg'),
 (1312,'p3113656089.jpg','13990719','p6849218048.jpg',NULL,'13:33:53','ادمین ادمین','',NULL,'jpg'),
 (1313,'p2858292539.jpg','13990721','',NULL,'10:23:59','ادمین ادمین','',NULL,'jpg'),
 (1314,'p6038944537.jpg','13990721','p2858292539.jpg',NULL,'10:34:46','ادمین ادمین','',NULL,'jpg'),
 (1315,'p5746611109.jpg','13990721','p6038944537.jpg',NULL,'10:53:14','ادمین ادمین','',NULL,'jpg'),
 (1316,'p7389638011.jpg','13990721','p5746611109.jpg',NULL,'10:54:32','ادمین ادمین','',NULL,'jpg'),
 (1317,'p2906008541.jpg','13990721','p7389638011.jpg',NULL,'10:55:15','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1318,'p6949729787.jpg','13990721','p2906008541.jpg',NULL,'10:55:37','ادمین ادمین','',NULL,'jpg'),
 (1319,'p6796089614.jpg','13990721','',NULL,'13:45:14','ادمین ادمین','',NULL,'jpg'),
 (1320,'p1453798906.jpg','13990721','p6796089614.jpg',NULL,'13:48:04','ادمین ادمین','',NULL,'jpg'),
 (1321,'p0524457201.jpg','13990721','p1453798906.jpg',NULL,'13:51:22','ادمین ادمین','',NULL,'jpg'),
 (1322,'p5338560806.jpg','13990721','p0524457201.jpg',NULL,'13:51:50','ادمین ادمین','',NULL,'jpg'),
 (1323,'p9023779966.jpg','13990721','p5338560806.jpg',NULL,'13:53:34','ادمین ادمین','',NULL,'jpg'),
 (1324,'p3137016955.jpg','13990721','',NULL,'15:54:05','ادمین ادمین','',NULL,'jpg'),
 (1325,'p1215515668.jpg','13990809','',NULL,'18:03:33','ادمین ادمین','',NULL,'jpg'),
 (1326,'p3877789697.jpg','13990809','p1215515668.jpg',NULL,'18:08:34','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1327,'p1274599761.jpg','13990809','p3877789697.jpg',NULL,'18:13:01','ادمین ادمین','',NULL,'jpg'),
 (1328,'p6720111393.jpg','13990812','p0548383097.jpg',NULL,'11:46:07','ادمین ادمین','',NULL,'jpg'),
 (1329,'p9193431063.jpg','13990812','p6720111393.jpg',NULL,'11:46:18','ادمین ادمین','',NULL,'jpg'),
 (1330,'p3159652060.jpg','13990812','p9428607717.jpg',NULL,'11:46:44','ادمین ادمین','',NULL,'jpg'),
 (1331,'p2082391493.jpg','13990812','p1398101396.jpg',NULL,'11:46:55','ادمین ادمین','',NULL,'jpg'),
 (1332,'p9413164924.jpg','13990812','p6643786805.jpg',NULL,'11:49:11','ادمین ادمین','',NULL,'jpg'),
 (1333,'p6708965080.jpg','13990812','p0055152484.jpg',NULL,'11:49:23','ادمین ادمین','',NULL,'jpg'),
 (1334,'p0653222848.jpg','13990812','p6211684532.jpg',NULL,'11:49:34','ادمین ادمین','',NULL,'jpg'),
 (1335,'p0850392712.jpg','13990812','p6643786805.jpg',NULL,'11:52:03','ادمین ادمین','',NULL,'jpg');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1336,'p8795668146.jpg','13990812','p0055152484.jpg',NULL,'11:52:16','ادمین ادمین','',NULL,'jpg'),
 (1337,'p0187506274.jpg','13990812','p6211684532.jpg',NULL,'11:59:51','ادمین ادمین','',NULL,'jpg'),
 (1338,'p3019569691.jpg','13990812','p2581918947.jpeg',NULL,'12:05:02','ادمین ادمین','',NULL,'jpg'),
 (1339,'p1554169563.jpg','13990812','',NULL,'12:05:17','ادمین ادمین','',NULL,'jpg'),
 (1340,'p3177906356.jpg','13990812','',NULL,'12:05:32','ادمین ادمین','',NULL,'jpg'),
 (1341,'p5900747663.jpg','13990812','p4274672940.png',NULL,'12:07:18','ادمین ادمین','',NULL,'jpg'),
 (1342,'p4536167563.jpg','13990812','',NULL,'12:07:30','ادمین ادمین','',NULL,'jpg'),
 (1343,'p1102168358.jpg','13990812','p7715311145.png',NULL,'12:08:38','ادمین ادمین','',NULL,'jpg'),
 (1344,'p3048995130.png','13990816','',NULL,'19:25:15','ادمین ادمین','',NULL,'png');
INSERT INTO `upload` (`id`,`upload_file_name`,`upload_date`,`upload_title`,`upload_comment`,`upload_time`,`upload_loader`,`upload_logStatus`,`upload_status`,`upload_type`) VALUES 
 (1345,'p9187151567.png','13990816','p3048995130.png',NULL,'19:25:22','ادمین ادمین','',NULL,'png'),
 (1346,'p4989870419.png','13990819','p9187151567.png',NULL,'10:48:42','ادمین ادمین','',NULL,'png'),
 (1347,'p1018936376.png','13990819','p4989870419.png',NULL,'10:48:44','ادمین ادمین','',NULL,'png'),
 (1348,'p5424676119.png','13990819','',NULL,'11:06:27','ادمین ادمین','',NULL,'png'),
 (1349,'p6246747046.png','13990819','',NULL,'11:09:51','ادمین ادمین','',NULL,'png'),
 (1350,'p6945780898.png','13990819','',NULL,'11:11:38','ادمین ادمین','',NULL,'png'),
 (1351,'p0946386997.jpg','13990916','p5424676119.png',NULL,'16:30:59','ادمین ادمین','',NULL,'jpg'),
 (1352,'p8967919758.mp4','13990917','',NULL,'18:37:27','ادمین ادمین','',NULL,'mp4');
/*!40000 ALTER TABLE `upload` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
