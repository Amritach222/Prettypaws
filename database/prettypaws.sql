-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2022 at 10:07 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prettypaws`
--

-- --------------------------------------------------------

--
-- Table structure for table `adopting_person`
--

CREATE TABLE `adopting_person` (
  `user_id` int(10) NOT NULL,
  `q1` varchar(100) NOT NULL,
  `q2` varchar(100) NOT NULL,
  `q3` varchar(100) NOT NULL,
  `q4` varchar(100) NOT NULL,
  `q5` varchar(100) NOT NULL,
  `q6` varchar(100) NOT NULL,
  `q7` varchar(100) NOT NULL,
  `q8` varchar(100) NOT NULL,
  `q9` varchar(100) NOT NULL,
  `q10` varchar(100) NOT NULL,
  `dog_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adopting_person`
--

INSERT INTO `adopting_person` (`user_id`, `q1`, `q2`, `q3`, `q4`, `q5`, `q6`, `q7`, `q8`, `q9`, `q10`, `dog_id`) VALUES
(9, '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '0'),
(10, 'Yes', 'No', 'Yes', 'No', 'No', 'No', 'Yes', 'Yes', 'No', 'Yes', '0'),
(12, 'Yes', 'No', 'No', 'Yes', 'No', 'No', 'Yes', 'No', 'Yes', 'Yes', '0');

-- --------------------------------------------------------

--
-- Table structure for table `adoption`
--

CREATE TABLE `adoption` (
  `user_id` varchar(10) NOT NULL,
  `q1` varchar(100) NOT NULL,
  `q2` varchar(100) NOT NULL,
  `q3` varchar(100) NOT NULL,
  `q4` varchar(100) NOT NULL,
  `q5` varchar(100) NOT NULL,
  `q6` varchar(100) NOT NULL,
  `q7` varchar(100) NOT NULL,
  `q8` varchar(100) NOT NULL,
  `q9` varchar(100) NOT NULL,
  `q10` varchar(100) NOT NULL,
  `dog_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adoption`
--

INSERT INTO `adoption` (`user_id`, `q1`, `q2`, `q3`, `q4`, `q5`, `q6`, `q7`, `q8`, `q9`, `q10`, `dog_id`) VALUES
('9', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '1'),
('10', 'Yes', 'No', 'Yes', 'No', 'No', 'Yes', 'No', 'Yes', 'No', 'Yes', '5'),
('10', 'Yes', 'No', 'Yes', 'No', 'Yes', 'Yes', 'No', 'Yes', 'No', 'Yes', '5'),
('10', 'Yes', 'No', 'No', 'No', 'No', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', '4'),
('10', 'Yes', 'No', 'Yes', 'Yes', 'No', 'Yes', 'Yes', 'No', 'Yes', 'Yes', '4'),
('13', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'No', 'Yes', 'Yes', '21'),
('13', 'Yes', 'No', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'No', 'Yes', 'Yes', '16'),
('15', 'Yes', 'Yes', 'No', 'Yes', 'Yes', 'No', 'Yes', 'No', 'Yes', 'Yes', '17'),
('13', 'No', 'Yes', 'No', 'Yes', 'Yes', 'Yes', 'No', 'Yes', 'No', 'Yes', '18');

-- --------------------------------------------------------

--
-- Table structure for table `approved_dogs`
--

CREATE TABLE `approved_dogs` (
  `id` int(10) NOT NULL,
  `dog_name` varchar(40) NOT NULL,
  `dog_address` varchar(50) NOT NULL,
  `dog_picture` varchar(50) NOT NULL,
  `dog_age` varchar(40) NOT NULL,
  `dog_gender` varchar(40) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `dog_breed` varchar(50) NOT NULL,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `approved_dogs`
--

INSERT INTO `approved_dogs` (`id`, `dog_name`, `dog_address`, `dog_picture`, `dog_age`, `dog_gender`, `description`, `dog_breed`, `user_id`) VALUES
(16, 'Hello', 'locattion', '851552183_1626092347.jpeg', '12 m onth', 'Male', 'message', 'breed', '13'),
(17, 'jdjsbds', 'jsdjjsk', '1913770345_1626092543.jpeg', 'dsndmnsmd', 'Female', 'message', 'nmndmnmdn', '13'),
(18, 'jhjhjh', 'njmnmnn', '731179967_1626093288.jpeg', 'jjhkjhjh', 'Male', 'jikjkj', 'bnbnbn', '13'),
(19, 'snjsns', 'xjjxjxj', '1211992144_1626175265.jpeg', 'rjhx', 'Female', 'nxnxn', 'hzhzb', '13'),
(20, 'max', 'Kathmandu', '1864006510_1626175238.jpeg', '12 month', 'Male', 'sksjbdhzdnsks', 'unknown', '13'),
(22, 'Tiger', 'Pokhara Lakeside', '644587691_1626175148.jpeg', '4 month', 'Male', 'Such a pretty dog.', 'unknown', '13');

-- --------------------------------------------------------

--
-- Table structure for table `dog_table`
--

CREATE TABLE `dog_table` (
  `id` int(11) NOT NULL,
  `dog_name` varchar(30) NOT NULL,
  `dog_address` varchar(40) NOT NULL,
  `dog_picture` varchar(40) NOT NULL,
  `dog_age` varchar(10) NOT NULL,
  `dog_gender` varchar(30) NOT NULL,
  `description` varchar(500) NOT NULL,
  `dog_breed` varchar(30) NOT NULL,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dog_table`
--

INSERT INTO `dog_table` (`id`, `dog_name`, `dog_address`, `dog_picture`, `dog_age`, `dog_gender`, `description`, `dog_breed`, `user_id`) VALUES
(20, 'Name', 'Chitwan Nepal', '2093353768_1626091681.jpeg', '6 month', 'Female', 'Hello Im from chitwan', 'Breed', '10'),
(29, 'Tiger', 'Pokhara', '1758800255_1626426252.jpeg', '12 month', 'Male', 'zhsjzzjzyzgskshz hdhshshs dheb dh', 'German seffer', '13'),
(30, 'dnmsndm', 'Pokhara', '216883398_1626495595.jpeg', '23 month', 'Male', 'hdsjhdjshdjshjdhsj', 'unknown', '13');

-- --------------------------------------------------------

--
-- Table structure for table `donator_table`
--

CREATE TABLE `donator_table` (
  `id` int(11) NOT NULL,
  `don_name` varchar(40) NOT NULL,
  `don_contact_number` varchar(30) NOT NULL,
  `don_email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `likedislke`
--

CREATE TABLE `likedislke` (
  `dog_id` varchar(10) NOT NULL,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `likedislke`
--

INSERT INTO `likedislke` (`dog_id`, `user_id`) VALUES
('2', '10'),
('3', '1'),
('1', '1'),
('8', '11'),
('9', '11'),
('10', '11'),
('7', '11'),
('6', '11'),
('11', '11'),
('4', '12'),
('7', '12'),
('11', '12'),
('4', '10'),
('5', '13'),
('1', '13'),
('2', '13'),
('3', '10'),
('8', '10'),
('11', '13'),
('21', '13'),
('22', '13'),
('18', '13'),
('17', '13'),
('16', '13'),
('17', '15'),
('20', '15');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `admin_email` varchar(25) NOT NULL,
  `code` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `admin_email`, `code`) VALUES
(1, 'admin', '5cb616fbe7de452f6dfa234a3230717e', 'amritach222@gmail.com', 23810);

-- --------------------------------------------------------

--
-- Table structure for table `update_table`
--

CREATE TABLE `update_table` (
  `user_id` varchar(10) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `update_table`
--

INSERT INTO `update_table` (`user_id`, `message`) VALUES
('10', 'Hello How are you? '),
('11', 'Welcome to our application from here you can adopt a dog.'),
('10', 'Welcome to our application. From here you can adopt a dog. You are notified if any updates from administrator'),
('13', 'Hello Amrit Acharya Welcome to our Application '),
('13', 'Hello Amrit Acharya Welcome to our Application ');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `imagename` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`id`, `name`, `email`, `contact_number`, `address`, `password`, `imagename`) VALUES
(1, 'Sujan Acharya', 'sujanach222@gmail.com', '9805229108', 'pokhara', '813be9143cdf629c4c839ffd10fe19ad', '944621585_1619632332.jpeg'),
(2, 'Bikram Bastola', 'bikram@gmail.com', '9866726372', 'Pokhara', '726abdc3a467947c6fd2fdb163c2b0ff', ''),
(3, 'Amrit', 'hjhdjsh@gmail.com', '9814119894', 'pkhgdvd', '5cb616fbe7de452f6dfa234a3230717e', ''),
(4, 'bikram', 'bikram@gmail.com', '9812345678', 'pokhara', 'e418a50117a5720f00dac3acb39f4a7e', ''),
(5, 'Arjun Acharya', 'arjunacharya23@gmail.com', '9815445779', 'Pokhara,Nepal 33700', '3ff066afa3a1c2a9ecfcdab581036249', ''),
(6, 'Subash Acharya', 'subash00@gmail.com', '9804388733', 'pokhara', 'de262e6ce6a39413a228b2693c3f4625', ''),
(7, 'Arjun Acharya', 'arjun123@gmail.com', '9815445779', 'Pokhara, Nepal 33700', '3ff066afa3a1c2a9ecfcdab581036249', '966663993_1620570006.jpeg'),
(8, 'Asmita Acharya Pathak', 'motherland17102@gmail.com', '9863224245', 'Bachchha,Bihadi-1 Parbat(Nepal)', '42cde7345bd6490ee8d316fc5040bd57', '651373795_1618768395.jpeg'),
(9, 'Yam Kumari Thapa', 'yamkumari@gmail.com', '9856025123', 'Pokhara, Nepal 33700', '41c8a0d0d686dce6b9912f2eccbbbd17', '867095041_1619320916.jpeg'),
(10, 'Amrit Ach', 'amrit123@gmail.com', '9814119703', 'Bachchha, Bihadi, Parbat 33400', '5cb616fbe7de452f6dfa234a3230717e', '1048288191_1624648692.jpeg'),
(11, 'Sujan Acharya', 'sujan123@gmail.com', '9843996311', 'Bachchha parbat 33400', 'b737e7b6d804c47093ff04d3014e892b', '501188060_1622821280.jpeg'),
(12, 'Rameshor yadav', 'ramesh123@gmail.com', '9867735453', 'Pokhara Newtown, 33700', 'de7cbbdd1b746025d12317fb93389b61', '761356037_1624649037.jpeg'),
(13, 'Amrit Acharya', 'amritach222@gmail.com', '9843886343', 'Pokhara, Lakeside 33700', '5cb616fbe7de452f6dfa234a3230717e', '2140384935_1625323917.jpeg'),
(14, 'Sujan Acharya', 'sujanach321@gmail.com', '9727282788', 'Bachchha parbat', 'b737e7b6d804c47093ff04d3014e892b', ''),
(15, 'Amit Gurung', 'amitgrg821@gmail.com', '9848536133', 'Bachchha 1 Bihadi', 'c71160f45e077e6e56a7415cbf8ec1ec', '55477998_1630572111.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `veterinary_table`
--

CREATE TABLE `veterinary_table` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `veterinary_table`
--

INSERT INTO `veterinary_table` (`id`, `name`, `address`, `contact`) VALUES
(1, 'Amrit Acharya', 'Pokhara Nepal', '9814119703'),
(5, 'Balkrishna Acharya', 'Chitwan Neapl', '9843886343'),
(7, 'Sugam Acharya', 'pokhara,nepal', '+9779814119703'),
(8, 'Bikram Bastola', 'Kathmandu Nepal', '9856043212');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adopting_person`
--
ALTER TABLE `adopting_person`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `approved_dogs`
--
ALTER TABLE `approved_dogs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dog_table`
--
ALTER TABLE `dog_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donator_table`
--
ALTER TABLE `donator_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `veterinary_table`
--
ALTER TABLE `veterinary_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `approved_dogs`
--
ALTER TABLE `approved_dogs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `dog_table`
--
ALTER TABLE `dog_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `donator_table`
--
ALTER TABLE `donator_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `veterinary_table`
--
ALTER TABLE `veterinary_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
