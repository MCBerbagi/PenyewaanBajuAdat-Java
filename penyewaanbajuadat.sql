-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2017 at 09:54 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penyewaanbajuadat`
--

-- --------------------------------------------------------

--
-- Table structure for table `bajuadat`
--

CREATE TABLE `bajuadat` (
  `id` int(11) NOT NULL,
  `nama_paket` varchar(45) NOT NULL,
  `harga` double NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bajuadat`
--

INSERT INTO `bajuadat` (`id`, `nama_paket`, `harga`, `stok`) VALUES
(14, 'sumbawa barat', 3000, 0),
(17, 'jawa timur', 5000, 0),
(18, 'jawa barat', 390000, 0),
(19, 'Nusa Tenggara Barat', 6000, 7);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `KTP` varchar(16) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `alamat` varchar(45) DEFAULT NULL,
  `nohp` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`KTP`, `nama`, `alamat`, `nohp`) VALUES
('111111111', 'dsfs', 'dfsd', '878767676'),
('12121212', 'ada', 'masak', '1212121212'),
('1234', 'sdfdsfd', 'sdfdfdfd', '32432'),
('1234567890123324', 'afade', 'sadasd', '3434433423'),
('1234567890123456', 'dsfds', 'dssdsd', '455');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id` int(11) NOT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `denda` double DEFAULT NULL,
  `dibayar` double DEFAULT NULL,
  `kembali` double DEFAULT NULL,
  `penyewaan_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`id`, `tgl_kembali`, `denda`, `dibayar`, `kembali`, `penyewaan_id`) VALUES
(1, '2017-07-16', 300000, 4000000, 3700000, 84);

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `id` int(11) NOT NULL,
  `tgl_sewa` date DEFAULT NULL,
  `Batas_kembali` date DEFAULT NULL,
  `total_harga` double DEFAULT NULL,
  `dibayar` double DEFAULT NULL,
  `kembali` double DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `Customer_KTP` varchar(16) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `penyewaan`
--

INSERT INTO `penyewaan` (`id`, `tgl_sewa`, `Batas_kembali`, `total_harga`, `dibayar`, `kembali`, `status`, `Customer_KTP`, `user_id`) VALUES
(84, '2017-07-05', '2017-07-01', 6000, 7000, 0, 'Sudah Kembali', '111111111', 15),
(85, '2017-07-06', '2017-07-27', 6000, 70000, 64000, 'Belum Kembali', '1234567890123456', 14);

-- --------------------------------------------------------

--
-- Table structure for table `penyewaanitem`
--

CREATE TABLE `penyewaanitem` (
  `id` int(11) NOT NULL,
  `jumlah_sewa` int(11) DEFAULT NULL,
  `subTotal` double DEFAULT NULL,
  `pakaianTradisional_id` int(11) NOT NULL,
  `penyewaan_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `penyewaanitem`
--

INSERT INTO `penyewaanitem` (`id`, `jumlah_sewa`, `subTotal`, `pakaianTradisional_id`, `penyewaan_id`) VALUES
(25, 1, 6000, 19, 84),
(26, 1, 6000, 19, 85);

--
-- Triggers `penyewaanitem`
--
DELIMITER $$
CREATE TRIGGER `insert_penyewaanitem` BEFORE INSERT ON `penyewaanitem` FOR EACH ROW BEGIN
update bajuadat set stok = stok - new.jumlah_sewa
 where id= new.pakaianTradisional_id ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `Alamat` varchar(45) DEFAULT NULL,
  `nohp` varchar(13) DEFAULT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `HakAkses` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`, `Alamat`, `nohp`, `username`, `password`, `HakAkses`) VALUES
(14, 'Muhammad', 'jhjh', 'ijuh', '1', '1', 'Admin'),
(15, 'budi', 'jhhj', 'jhh', '2', '2', 'Kasir'),
(16, 'aiman', 'dsfs', '32', '3', '3', 'Kasir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bajuadat`
--
ALTER TABLE `bajuadat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`KTP`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id`,`penyewaan_id`),
  ADD KEY `fk_pengembalian_penyewaan1_idx` (`penyewaan_id`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`id`,`Customer_KTP`,`user_id`),
  ADD KEY `fk_penyewaan_Customer_idx` (`Customer_KTP`),
  ADD KEY `fk_penyewaan_user1_idx` (`user_id`);

--
-- Indexes for table `penyewaanitem`
--
ALTER TABLE `penyewaanitem`
  ADD PRIMARY KEY (`id`,`pakaianTradisional_id`,`penyewaan_id`),
  ADD KEY `fk_penyewaanItem_pakaianTradisional1_idx` (`pakaianTradisional_id`),
  ADD KEY `fk_penyewaanitem_penyewaan1_idx` (`penyewaan_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bajuadat`
--
ALTER TABLE `bajuadat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `penyewaan`
--
ALTER TABLE `penyewaan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;
--
-- AUTO_INCREMENT for table `penyewaanitem`
--
ALTER TABLE `penyewaanitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD CONSTRAINT `fk_pengembalian_penyewaan1` FOREIGN KEY (`penyewaan_id`) REFERENCES `penyewaan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD CONSTRAINT `fk_penyewaan_Customer` FOREIGN KEY (`Customer_KTP`) REFERENCES `customer` (`KTP`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_penyewaan_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `penyewaanitem`
--
ALTER TABLE `penyewaanitem`
  ADD CONSTRAINT `fk_penyewaanItem_pakaianTradisional1` FOREIGN KEY (`pakaianTradisional_id`) REFERENCES `bajuadat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_penyewaanitem_penyewaan1` FOREIGN KEY (`penyewaan_id`) REFERENCES `penyewaan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
