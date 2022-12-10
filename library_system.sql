-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28-Set-2022 às 21:01
-- Versão do servidor: 10.4.21-MariaDB
-- versão do PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `library_system`
--

CREATE DATABASE library_system;
USE library_system;

-- --------------------------------------------------------

--
-- Estrutura da tabela `article`
--

CREATE TABLE `article` (
  `idArticle` int(11) NOT NULL,
  `FK_idUser` int(11) NOT NULL,
  `title` varchar(60) NOT NULL,
  `year` int(10) NOT NULL,
  `author` varchar(50) NOT NULL,
  `doi` varchar(55) NOT NULL,
  `journal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `article`
--

INSERT INTO `article` (`idArticle`, `FK_idUser`, `title`, `year`, `author`, `doi`, `journal`) VALUES
(2, 9, 'dsfasdfasd', 1231, 'dsfadsa', 'sagffdsg', 'asdfasdfas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `book`
--

CREATE TABLE `book` (
  `idBook` int(11) NOT NULL,
  `FK_idUser` int(11) NOT NULL,
  `title` varchar(10) NOT NULL,
  `year` int(10) NOT NULL,
  `author` varchar(10) NOT NULL,
  `language` varchar(10) NOT NULL,
  `isbn` varchar(10) NOT NULL,
  `pages` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `book`
--

INSERT INTO `book` (`idBook`, `FK_idUser`, `title`, `year`, `author`, `language`, `isbn`, `pages`) VALUES
(2, 9, 'aaa', 1234, 'bbb', 'asdfasd', '1231241', 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `login` varchar(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(10) NOT NULL,
  `publication_limit` int(11) NOT NULL DEFAULT 50
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`idUser`, `login`, `email`, `password`, `publication_limit`) VALUES
(9, 'adm', 'emailzao@mail.com', '123', 49);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`idArticle`),
  ADD KEY `FK_Article_User` (`FK_idUser`);

--
-- Índices para tabela `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`idBook`),
  ADD KEY `FK_Book_User` (`FK_idUser`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `article`
--
ALTER TABLE `article`
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `book`
--
ALTER TABLE `book`
  MODIFY `idBook` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `FK_Article_User` FOREIGN KEY (`FK_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK_Book_User` FOREIGN KEY (`FK_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

CREATE TABLE `magazine` (
  `idMagazine` int(11) NOT NULL,
  `FK_idUser` int(11) NOT NULL,
  `title` varchar(60) NOT NULL,
  `year` int(10) NOT NULL,
  `author` varchar(50) NOT NULL,
  `issue` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `magazine`
  ADD PRIMARY KEY (`idMagazine`),
  ADD KEY `FK_idUser` (`FK_idUser`);
  
ALTER TABLE `magazine`
  MODIFY `idMagazine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `magazine`
  ADD CONSTRAINT `FK_Magazine_User` FOREIGN KEY (`FK_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
