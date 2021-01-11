-- MySQL Script generated by MySQL Workbench
-- Mon Dec 14 16:18:11 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shopzoid
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `shopzoid` ;

-- -----------------------------------------------------
-- Schema shopzoid
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shopzoid` DEFAULT CHARACTER SET utf8 ;
USE `shopzoid` ;

-- -----------------------------------------------------
-- Table `shopzoid`.`USUARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`USUARIO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`USUARIO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(45) NOT NULL,
  `NOMBRE` VARCHAR(255) NOT NULL,
  `APELLIDO1` VARCHAR(255) NOT NULL,
  `APELLIDO2` VARCHAR(255) NULL,
  `FECHA_SUB` DATETIME NOT NULL DEFAULT now(),
  `LAST_LOGIN` DATETIME NULL,
  `USUARIO` VARCHAR(255) NOT NULL,
  `PSSWD` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  UNIQUE INDEX `USUARIO_UNIQUE` (`USUARIO` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`CARRITO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`CARRITO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`CARRITO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CARRITO_USUARIO_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_CARRITO_USUARIO`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`VENDEDOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`VENDEDOR` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`VENDEDOR` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PRODUCTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PRODUCTO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PRODUCTO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(255) NOT NULL,
  `PRECIO_BASE` DOUBLE NOT NULL,
  `DESCRIPCION` VARCHAR(255) NOT NULL,
  `ID_VENDEDOR` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PRODUCTO_VENDEDOR1_idx` (`ID_VENDEDOR` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_VENDEDOR1`
    FOREIGN KEY (`ID_VENDEDOR`)
    REFERENCES `shopzoid`.`VENDEDOR` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`DESCUENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`DESCUENTO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`DESCUENTO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `COD` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`LINEA_CARRITO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`LINEA_CARRITO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`LINEA_CARRITO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_CARRITO` INT NOT NULL,
  `CANTIDAD` INT NOT NULL,
  `ID_PRODUCTO` INT NOT NULL,
  `DESCUENTO_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_LINEA_CARRITO_CARRITO1_idx` (`ID_CARRITO` ASC) VISIBLE,
  INDEX `fk_LINEA_CARRITO_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  INDEX `fk_LINEA_CARRITO_DESCUENTO1_idx` (`DESCUENTO_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LINEA_CARRITO_CARRITO1`
    FOREIGN KEY (`ID_CARRITO`)
    REFERENCES `shopzoid`.`CARRITO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LINEA_CARRITO_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LINEA_CARRITO_DESCUENTO1`
    FOREIGN KEY (`DESCUENTO_ID`)
    REFERENCES `shopzoid`.`DESCUENTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`ESPECIFICACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`ESPECIFICACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`ESPECIFICACION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  `ID_ESPECIFICACION` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ESPECIFICACION_ESPECIFICACION1_idx` (`ID_ESPECIFICACION` ASC) VISIBLE,
  CONSTRAINT `fk_ESPECIFICACION_ESPECIFICACION1`
    FOREIGN KEY (`ID_ESPECIFICACION`)
    REFERENCES `shopzoid`.`ESPECIFICACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PRODUCTO_ESPECIFICACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PRODUCTO_ESPECIFICACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PRODUCTO_ESPECIFICACION` (
  `ID_PRODUCTO` INT NOT NULL,
  `ID_ESPECIFICACION` INT NOT NULL,
  `VALOR` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID_PRODUCTO`, `ID_ESPECIFICACION`),
  INDEX `fk_PRODUCTO_has_ESPECIFICACION_ESPECIFICACION1_idx` (`ID_ESPECIFICACION` ASC) VISIBLE,
  INDEX `fk_PRODUCTO_has_ESPECIFICACION_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_has_ESPECIFICACION_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTO_has_ESPECIFICACION_ESPECIFICACION1`
    FOREIGN KEY (`ID_ESPECIFICACION`)
    REFERENCES `shopzoid`.`ESPECIFICACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`CATEGORIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`CATEGORIA` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`CATEGORIA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(255) NOT NULL,
  `ID_CATEGORIA` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CATEGORIA_CATEGORIA1_idx` (`ID_CATEGORIA` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORIA_CATEGORIA1`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `shopzoid`.`CATEGORIA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PRODUCTO_CATEGORIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PRODUCTO_CATEGORIA` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PRODUCTO_CATEGORIA` (
  `ID_PRODUCTO` INT NOT NULL,
  `ID_CATEGORIA` INT NOT NULL,
  PRIMARY KEY (`ID_PRODUCTO`, `ID_CATEGORIA`),
  INDEX `fk_PRODUCTO_has_CATEGORIA_CATEGORIA1_idx` (`ID_CATEGORIA` ASC) VISIBLE,
  INDEX `fk_PRODUCTO_has_CATEGORIA_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_has_CATEGORIA_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTO_has_CATEGORIA_CATEGORIA1`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `shopzoid`.`CATEGORIA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`MODIFICACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`MODIFICACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`MODIFICACION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PRODUCTO_MODIFICACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PRODUCTO_MODIFICACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PRODUCTO_MODIFICACION` (
  `PRODUCTO_ID` INT NOT NULL AUTO_INCREMENT,
  `MODIFICACION_ID` INT NOT NULL,
  `PRECIO_MOD` DOUBLE NOT NULL DEFAULT 1,
  `STOCK` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`PRODUCTO_ID`, `MODIFICACION_ID`),
  INDEX `fk_PRODUCTO_has_MODIFICACION_MODIFICACION1_idx` (`MODIFICACION_ID` ASC) VISIBLE,
  INDEX `fk_PRODUCTO_has_MODIFICACION_PRODUCTO1_idx` (`PRODUCTO_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_has_MODIFICACION_PRODUCTO1`
    FOREIGN KEY (`PRODUCTO_ID`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTO_has_MODIFICACION_MODIFICACION1`
    FOREIGN KEY (`MODIFICACION_ID`)
    REFERENCES `shopzoid`.`MODIFICACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`MODIFICACION_MODIFICACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`MODIFICACION_MODIFICACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`MODIFICACION_MODIFICACION` (
  `ID_MODIFICACION` INT NOT NULL,
  `ID_MODIFICACION_PRIN` INT NOT NULL,
  PRIMARY KEY (`ID_MODIFICACION`, `ID_MODIFICACION_PRIN`),
  INDEX `fk_MODIFICACION_has_MODIFICACION_MODIFICACION2_idx` (`ID_MODIFICACION` ASC) VISIBLE,
  INDEX `fk_MODIFICACION_has_MODIFICACION_MODIFICACION1_idx` (`ID_MODIFICACION_PRIN` ASC) VISIBLE,
  CONSTRAINT `fk_MODIFICACION_has_MODIFICACION_MODIFICACION1`
    FOREIGN KEY (`ID_MODIFICACION_PRIN`)
    REFERENCES `shopzoid`.`MODIFICACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MODIFICACION_has_MODIFICACION_MODIFICACION2`
    FOREIGN KEY (`ID_MODIFICACION`)
    REFERENCES `shopzoid`.`MODIFICACION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`ESTADO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`ESTADO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ESTADO` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PEDIDO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PEDIDO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PEDIDO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` INT NOT NULL,
  `ID_ESTADO` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PEDIDO_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  INDEX `fk_PEDIDO_ESTADO1_idx` (`ID_ESTADO` ASC) VISIBLE,
  CONSTRAINT `fk_PEDIDO_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PEDIDO_ESTADO1`
    FOREIGN KEY (`ID_ESTADO`)
    REFERENCES `shopzoid`.`ESTADO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`LINEA_PEDIDO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`LINEA_PEDIDO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`LINEA_PEDIDO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_PEDIDO` INT NOT NULL,
  `ID_PRODUCTO` INT NOT NULL,
  `CANTIDAD` INT NOT NULL,
  `PRECIO` DOUBLE NOT NULL,
  `DESCUENTO` DOUBLE NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_LINEA_PEDIDO_PEDIDO1_idx` (`ID_PEDIDO` ASC) VISIBLE,
  INDEX `fk_LINEA_PEDIDO_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  CONSTRAINT `fk_LINEA_PEDIDO_PEDIDO1`
    FOREIGN KEY (`ID_PEDIDO`)
    REFERENCES `shopzoid`.`PEDIDO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LINEA_PEDIDO_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PRODUCTO_DESCUENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PRODUCTO_DESCUENTO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PRODUCTO_DESCUENTO` (
  `ID_PRODUCTO` INT NOT NULL,
  `ID_DESCUENTO` INT NOT NULL,
  `DESCUENTO` DOUBLE NOT NULL,
  `CANT_MIN` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID_PRODUCTO`, `ID_DESCUENTO`),
  INDEX `fk_PRODUCTO_has_DESCUENTO_DESCUENTO1_idx` (`ID_DESCUENTO` ASC) VISIBLE,
  INDEX `fk_PRODUCTO_has_DESCUENTO_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_has_DESCUENTO_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTO_has_DESCUENTO_DESCUENTO1`
    FOREIGN KEY (`ID_DESCUENTO`)
    REFERENCES `shopzoid`.`DESCUENTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PERMISO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PERMISO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PERMISO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PERMISO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`USUARIO_PERMISO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`USUARIO_PERMISO` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`USUARIO_PERMISO` (
  `ID_USUARIO` INT NOT NULL,
  `ID_PERMISO` INT NOT NULL,
  PRIMARY KEY (`ID_USUARIO`, `ID_PERMISO`),
  INDEX `fk_USUARIO_has_PERMISO_PERMISO1_idx` (`ID_PERMISO` ASC) VISIBLE,
  INDEX `fk_USUARIO_has_PERMISO_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_has_PERMISO_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_PERMISO_PERMISO1`
    FOREIGN KEY (`ID_PERMISO`)
    REFERENCES `shopzoid`.`PERMISO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`PAIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`PAIS` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`PAIS` (
  `ID` VARCHAR(2) NOT NULL,
  `NOMBRE` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`DIRECCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`DIRECCION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`DIRECCION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DIRECCION` VARCHAR(255) NOT NULL,
  `NUMERO` VARCHAR(255) NOT NULL DEFAULT 'S/N',
  `LETRA` VARCHAR(45) NULL,
  `PISO` INT NULL,
  `COD_POSTAL` VARCHAR(45) NOT NULL,
  `ID_PAIS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_DIRECCION_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  CONSTRAINT `fk_DIRECCION_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `shopzoid`.`PAIS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`USUARIO_DIRECCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`USUARIO_DIRECCION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`USUARIO_DIRECCION` (
  `ID_USUARIO` INT NOT NULL,
  `ID_DIRECCION` INT NOT NULL,
  PRIMARY KEY (`ID_USUARIO`, `ID_DIRECCION`),
  INDEX `fk_USUARIO_has_DIRECCION_DIRECCION1_idx` (`ID_DIRECCION` ASC) VISIBLE,
  INDEX `fk_USUARIO_has_DIRECCION_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_has_DIRECCION_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_DIRECCION_DIRECCION1`
    FOREIGN KEY (`ID_DIRECCION`)
    REFERENCES `shopzoid`.`DIRECCION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`USUARIO_VENDEDOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`USUARIO_VENDEDOR` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`USUARIO_VENDEDOR` (
  `ID_USUARIO` INT NOT NULL,
  `ID_VENDEDOR` INT NOT NULL,
  PRIMARY KEY (`ID_USUARIO`, `ID_VENDEDOR`),
  INDEX `fk_USUARIO_has_VENDEDOR_VENDEDOR1_idx` (`ID_VENDEDOR` ASC) VISIBLE,
  INDEX `fk_USUARIO_has_VENDEDOR_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_has_VENDEDOR_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_has_VENDEDOR_VENDEDOR1`
    FOREIGN KEY (`ID_VENDEDOR`)
    REFERENCES `shopzoid`.`VENDEDOR` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shopzoid`.`VALORACION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopzoid`.`VALORACION` ;

CREATE TABLE IF NOT EXISTS `shopzoid`.`VALORACION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `VAL` DOUBLE NOT NULL DEFAULT 0,
  `ID_PRODUCTO` INT NOT NULL,
  `ID_USUARIO` INT NOT NULL,
  `COMENTARIO` LONGTEXT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_VALORACION_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  INDEX `fk_VALORACION_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_VALORACION_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `shopzoid`.`PRODUCTO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VALORACION_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `shopzoid`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;