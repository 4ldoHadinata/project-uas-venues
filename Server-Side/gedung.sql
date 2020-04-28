-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2020 at 10:38 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gedung`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_detail_gedung` ()  BEGIN
  SELECT nama_gedung, alamat_gedung, harga_sewa_gedung, luas_gedung, daya_tampung FROM detail_gedung;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_gedung` ()  BEGIN
  SELECT mulai_sewa, selesai_sewa FROM list_gedung;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `hapus_gedung` (IN `idn` INT(20))  BEGIN
DELETE FROM detail_gedung
WHERE detail_gedung.id_gedung = idn;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login_admin` (IN `usern` VARCHAR(100), IN `passdn` VARCHAR(32))  BEGIN
SELECT id_admin FROM admin WHERE admin.username_admin = usern AND admin.password_admin = md5(passdn);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login_pengelola` (IN `usern` VARCHAR(100), IN `passdn` VARCHAR(32))  BEGIN
SELECT id_pengelola FROM pengelola_gedung WHERE pengelola_gedung.nama_pengelola = usern AND pengelola_gedung.password_pengelola = md5(passdn);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `reg_pengelola` (IN `username` VARCHAR(50), IN `password` VARCHAR(32))  BEGIN
  INSERT INTO pengelola_gedung(nama_pengelola, password_pengelola) VALUES(username,MD5(password));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_detail_gedung` (IN `nama_gedungn` VARCHAR(200), IN `alamat_gedungn` VARCHAR(200), IN `harga_sewa_gedungn` INT(20), IN `luas_gedungn` INT(20), IN `daya_tampungn` INT(20))  BEGIN
INSERT INTO detail_gedung(nama_gedung, alamat_gedung, harga_sewa_gedung, luas_gedung, daya_tampung) VALUES(nama_gedungn, alamat_gedungn, harga_sewa_gedungn, luas_gedungn, daya_tampungn);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_list_gedung` (IN `mulai_sewan` DATE, IN `selesai_sewan` DATE)  BEGIN
INSERT INTO list_gedung VALUES ( mulai_sewan, selesai_sewan );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_gedung` (IN `id_gedungn` INT(20), IN `nama_gedungn` VARCHAR(200), IN `alamat_gedungn` VARCHAR(200), IN `harga_sewan` VARCHAR(200), IN `luas_gedungn` INT(200), IN `daya_tampungn` INT(200))  BEGIN
UPDATE detail_gedung SET nama_gedung = nama_gedungn, alamat_gedung = alamat_gedungn, harga_sewa_gedung = harga_sewan, luas_gedung = luas_gedungn, daya_tampung = daya_tampungn WHERE detail_gedung.id_gedung = id_gedungn;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(20) NOT NULL,
  `username_admin` int(100) NOT NULL,
  `password_admin` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detail_gedung`
--

CREATE TABLE `detail_gedung` (
  `id_gedung` int(20) NOT NULL,
  `nama_gedung` varchar(200) NOT NULL,
  `alamat_gedung` varchar(200) NOT NULL,
  `harga_sewa_gedung` int(20) NOT NULL,
  `luas_gedung` int(20) NOT NULL,
  `daya_tampung` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `detail_gedung`
--
DELIMITER $$
CREATE TRIGGER `log_delete_detail` AFTER DELETE ON `detail_gedung` FOR EACH ROW INSERT INTO update_log VALUES("DEL-detail", old.id_gedung, old.nama_gedung, now())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `log_tambah_detail` AFTER INSERT ON `detail_gedung` FOR EACH ROW INSERT INTO update_log VALUES("INS-detail", new.id_gedung, new.nama_gedung, now())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `log_update_detail` AFTER UPDATE ON `detail_gedung` FOR EACH ROW INSERT INTO update_log VALUES("UPD-detail", new.id_gedung, new.nama_gedung, now())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `list_gedung`
--

CREATE TABLE `list_gedung` (
  `id_gedung` int(20) NOT NULL,
  `mulai_sewa` datetime NOT NULL,
  `selesai_sewa` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `list_gedung`
--
DELIMITER $$
CREATE TRIGGER `log_tambah_list` AFTER INSERT ON `list_gedung` FOR EACH ROW INSERT INTO update_log VALUES("INS-list", new.id_gedung, new.mulai_sewa, now())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pengelola_gedung`
--

CREATE TABLE `pengelola_gedung` (
  `id_pengelola` int(20) NOT NULL,
  `nama_pengelola` varchar(100) NOT NULL,
  `password_pengelola` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengelola_gedung`
--

INSERT INTO `pengelola_gedung` (`id_pengelola`, `nama_pengelola`, `password_pengelola`) VALUES
(1, '0', '6'),
(2, 'user1', '6ad14ba9986e3615423dfca256d04e3f'),
(3, 'user1', '6ad14ba9986e3615423dfca256d04e3f');

-- --------------------------------------------------------

--
-- Table structure for table `update_log`
--

CREATE TABLE `update_log` (
  `aksi` varchar(200) NOT NULL,
  `id` int(20) NOT NULL,
  `new_value` varchar(200) NOT NULL,
  `waktu` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `update_log`
--

INSERT INTO `update_log` (`aksi`, `id`, `new_value`, `waktu`) VALUES
('INS-detail', 0, 'malang geudng', '2020-03-10 17:13:38'),
('DEL-detail', 0, 'malang geudng', '2020-03-10 17:54:23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `detail_gedung`
--
ALTER TABLE `detail_gedung`
  ADD PRIMARY KEY (`id_gedung`);

--
-- Indexes for table `list_gedung`
--
ALTER TABLE `list_gedung`
  ADD KEY `id_gedung` (`id_gedung`);

--
-- Indexes for table `pengelola_gedung`
--
ALTER TABLE `pengelola_gedung`
  ADD PRIMARY KEY (`id_pengelola`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pengelola_gedung`
--
ALTER TABLE `pengelola_gedung`
  MODIFY `id_pengelola` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `list_gedung`
--
ALTER TABLE `list_gedung`
  ADD CONSTRAINT `list_gedung_ibfk_1` FOREIGN KEY (`id_gedung`) REFERENCES `detail_gedung` (`id_gedung`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
