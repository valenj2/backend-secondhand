-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.44 - MySQL Community Server (GPL)
-- SO del servidor:              Linux
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para secondhandDB
DROP DATABASE IF EXISTS `secondhandDB`;
CREATE DATABASE IF NOT EXISTS `secondhandDB` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `secondhandDB`;

-- Volcando estructura para tabla secondhandDB.categoria_prendas
DROP TABLE IF EXISTS `categoria_prendas`;
CREATE TABLE IF NOT EXISTS `categoria_prendas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria_prenda` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.categoria_prendas: ~8 rows (aproximadamente)
INSERT INTO `categoria_prendas` (`id`, `categoria_prenda`) VALUES
	(4, 'Camisas'),
	(5, 'Pantalones'),
	(6, 'Chaquetas'),
	(7, 'Vestidos'),
	(8, 'Faldas'),
	(9, 'Sueteres'),
	(10, 'Abrigos'),
	(11, 'zapatos');

-- Volcando estructura para tabla secondhandDB.compras
DROP TABLE IF EXISTS `compras`;
CREATE TABLE IF NOT EXISTS `compras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fechacompra` date DEFAULT NULL,
  `id_prenda` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm2fayrb53o53j4roth0f919kb` (`id_prenda`),
  KEY `FKsfjim1druo8tc28uvbb799tc4` (`id_usuario`),
  CONSTRAINT `FKm2fayrb53o53j4roth0f919kb` FOREIGN KEY (`id_prenda`) REFERENCES `prendas` (`id`),
  CONSTRAINT `FKsfjim1druo8tc28uvbb799tc4` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.compras: ~0 rows (aproximadamente)

-- Volcando estructura para tabla secondhandDB.favoritos
DROP TABLE IF EXISTS `favoritos`;
CREATE TABLE IF NOT EXISTS `favoritos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prenda_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKequahp2lrwakq4uyrik3oehiv` (`prenda_id`),
  KEY `FKq9wif2hcqfxj8t49wo613wm0h` (`usuario_id`),
  CONSTRAINT `FKequahp2lrwakq4uyrik3oehiv` FOREIGN KEY (`prenda_id`) REFERENCES `prendas` (`id`),
  CONSTRAINT `FKq9wif2hcqfxj8t49wo613wm0h` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.favoritos: ~2 rows (aproximadamente)
INSERT INTO `favoritos` (`id`, `prenda_id`, `usuario_id`, `estado`) VALUES
	(1, 8, 73, b'0'),
	(2, 9, 73, b'1');

-- Volcando estructura para tabla secondhandDB.prendas
DROP TABLE IF EXISTS `prendas`;
CREATE TABLE IF NOT EXISTS `prendas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anno` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tipo_tela` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `categoria_prenda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfqln844s4x88ee1qc0mgeell0` (`categoria_prenda`),
  CONSTRAINT `FKfqln844s4x88ee1qc0mgeell0` FOREIGN KEY (`categoria_prenda`) REFERENCES `categoria_prendas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.prendas: ~14 rows (aproximadamente)
INSERT INTO `prendas` (`id`, `anno`, `descripcion`, `estado`, `precio`, `tipo_tela`, `url`, `categoria_prenda`) VALUES
	(8, 2022, 'Camisa de lino blanca', 'Disponible', 29900, 'Lino', 'https://media.gotrendier.com.co/media/p/2022/11/27/n_ea0a5e60-6ebc-11ed-96a6-12b69a149c11.jpeg', 4),
	(9, 2021, 'Pantalón de mezclilla azul', 'Disponible', 15000, 'Denim', 'https://media.gotrendier.com.co/media/p/2024/06/10/n_A9IaiyBmzTES9o.69e971d84cccccc4.jpeg', 5),
	(10, 2023, 'Chaqueta de cuero negra Original', 'Disponible', 75000, 'Cuero', 'https://media.gotrendier.com.co/media/p/2023/01/23/n_a133d058-9b68-11ed-b52d-126f8de0944b.jpeg', 6),
	(11, 2020, 'Vestido floral ', 'Disponible', 15000, 'Algodón', 'https://media.gotrendier.com.co/media/p/2023/09/13/n_551b4680-525e-11ee-9a72-021dc08801a5.jpeg', 7),
	(12, 2023, 'Falda plisada roja', 'Disponible', 24.99, 'Poliéster', 'https://media.gotrendier.com.co/media/p/2023/07/03/n_69867a16-19df-11ee-8123-02fec7c0aaab.jpeg', 8),
	(13, 2022, 'Suéter de lana gris', 'Disponible', 39.99, 'Lana', 'https://media.gotrendier.com.co/media/p/2024/02/17/n_77d413f8-cdc1-11ee-b7d5-0a919114c241.jpeg', 9),
	(14, 2021, 'Abrigo largo de lana marrón', 'Disponible', 99.99, 'Lana', 'https://media.gotrendier.com.co/media/p/2024/07/11/n_3qM0VEKXiGCDCY.1213515452535357.jpeg', 10),
	(15, 2020, 'Zapatos deportivos blancos', 'Disponible', 49.99, 'Sintético', 'https://media.gotrendier.com.co/media/p/2021/08/02/n_efd9e430-f3b3-11eb-bb98-0a9f3b1a82b7.jpeg', 11),
	(16, 2023, 'Camisa de algodón azul', 'Disponible', 27.99, 'Algodón', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT12MqHUC1EhDh3p31nI3Ad-wyYRnjNVNcyzw&s', 4),
	(17, 2022, 'Pantalón cargo verde', 'Disponible', 22.99, 'Algodón', 'https://media.gotrendier.com.co/media/p/2022/12/12/n_bda2c890-7a37-11ed-8d0b-122bf66ae2e9.jpeg', 5),
	(18, 2021, 'Chaqueta vaquera', 'Disponible', 59.99, 'Denim', 'https://media.gotrendier.com.co/media/p/2024/01/05/n_f15db520-ac39-11ee-b77e-0a92f7420f79.jpeg', 6),
	(19, 2020, 'Vestido de seda azul marino', 'Disponible', 89.99, 'Seda', 'https://media.gotrendier.com.co/media/p/2024/01/29/n_a9c6cae2-beb0-11ee-a2d5-12091c2af1c5.jpeg', 7),
	(20, 2023, 'Falda de mezclilla azul', 'Disponible', 19.99, 'Denim', 'https://media.gotrendier.com.co/media/p/2023/05/19/n_83f16dde-f6a8-11ed-92b0-0adc31151565.jpeg', 8),
	(21, 2022, 'Suéter de cachemira beige', 'Disponible', 79.99, 'Cachemira', 'https://i.ebayimg.com/thumbs/images/g/IxYAAOSw-YdmUHx8/s-l1200.jpg', 9);

-- Volcando estructura para tabla secondhandDB.tipo_usuarios
DROP TABLE IF EXISTS `tipo_usuarios`;
CREATE TABLE IF NOT EXISTS `tipo_usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actualizado` datetime(6) DEFAULT NULL,
  `creado` datetime(6) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `tipo_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.tipo_usuarios: ~0 rows (aproximadamente)
INSERT INTO `tipo_usuarios` (`id`, `actualizado`, `creado`, `descripcion`, `tipo_usuario`) VALUES
	(4, '2024-09-16 01:16:40.000000', '2024-09-15 16:43:43.000000', 'Administrador del sistema', 'ROLE_USER');

-- Volcando estructura para tabla secondhandDB.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` tinyint(4) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `id_tipo_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoow8x2v17mkigyn5wqptgucu` (`id_tipo_usuario`),
  CONSTRAINT `FKoow8x2v17mkigyn5wqptgucu` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla secondhandDB.usuarios: ~0 rows (aproximadamente)
INSERT INTO `usuarios` (`id`, `apellido`, `contrasena`, `email`, `nombre`, `rol`, `usuario`, `id_tipo_usuario`) VALUES
	(73, 'ejemplo', '$2a$10$mpgXHq74q0.m4h9ptZVXZeu.GRpZY8bzI0uEgHGrUecgGKQQgv8xy', 'ejemplo2@gmail.com', 'Benitez', 1, 'unico2', 4);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
