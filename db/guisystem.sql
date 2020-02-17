-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2020 at 01:32 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guisystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `accNo` varchar(5) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `passWord` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accNo`, `userName`, `passWord`) VALUES
('ACC01', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custNo` varchar(5) NOT NULL,
  `custname` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custNo`, `custname`) VALUES
('C01', 'Homer Reyes'),
('C02', 'Bon Tura'),
('C03', 'Troy Gallardo'),
('C04', 'Jared Gotgotao'),
('C05', 'Mary Jane Lima');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `prodCode` varchar(5) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`prodCode`, `description`, `price`, `weight`) VALUES
('P01', 'Pencil', '10.00', '10.00'),
('P02', 'Ballpen', '20.00', '10.00'),
('P03', 'Notebook', '25.00', '20.00'),
('P04', 'Calculator', '250.00', '35.00'),
('P05', 'Ruler', '15.00', '5.00');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `transNo` varchar(5) NOT NULL,
  `salesDate` date DEFAULT NULL,
  `custNo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`transNo`, `salesDate`, `custNo`) VALUES
('TR01', '2020-01-19', 'C01'),
('TR02', '2020-02-01', 'C02'),
('TR03', '2020-02-05', 'C03'),
('TR04', '2020-01-25', 'C04'),
('TR05', '2020-01-30', 'C05');

-- --------------------------------------------------------

--
-- Table structure for table `salesdetail`
--

CREATE TABLE `salesdetail` (
  `transNo` varchar(5) NOT NULL,
  `prodCode` varchar(5) NOT NULL,
  `quantity` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salesdetail`
--

INSERT INTO `salesdetail` (`transNo`, `prodCode`, `quantity`) VALUES
('TR01', 'P01', 4),
('TR01', 'P02', 3),
('TR01', 'P03', 3),
('TR01', 'P04', 1),
('TR01', 'P05', 3),
('TR02', 'P01', 3),
('TR02', 'P02', 3),
('TR02', 'P03', 1),
('TR02', 'P04', 1),
('TR02', 'P05', 1),
('TR03', 'P01', 2),
('TR03', 'P02', 2),
('TR03', 'P03', 2),
('TR03', 'P04', 1),
('TR03', 'P05', 3),
('TR04', 'P01', 1),
('TR04', 'P02', 2),
('TR04', 'P03', 2),
('TR04', 'P05', 1),
('TR05', 'P01', 1),
('TR05', 'P02', 1),
('TR05', 'P03', 1),
('TR05', 'P04', 1),
('TR05', 'P05', 1),
('TR04', 'P04', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accNo`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custNo`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`prodCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
