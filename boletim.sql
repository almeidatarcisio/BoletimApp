-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 14-Set-2021 às 01:26
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `boletim`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id`, `fk_id_usuario`, `nome`, `datainclusao`) VALUES
(2, 21, 'Joao Silva Souza', '2020-12-30 10:47:12'),
(4, 23, 'Maria Santos Oliveira', '2020-12-30 10:47:52');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `id` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cargahoraria` int(3) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `nome`, `cargahoraria`, `datainclusao`) VALUES
(1, 'Calculo I', 60, '2020-12-30 17:07:27'),
(2, 'Desenho Tecnico I', 60, '2020-12-30 17:07:43'),
(3, 'Fisica I', 60, '2020-12-30 17:07:53'),
(4, 'Geometria Analitica', 60, '2020-12-30 17:08:14'),
(5, 'Introducao a Engenharia Civil', 60, '2020-12-30 17:08:41'),
(6, 'Quimica Geral', 60, '2020-12-30 17:08:57'),
(7, 'Algebra Linear', 60, '2020-12-30 17:09:23'),
(8, 'Algoritmo', 60, '2020-12-30 17:09:23'),
(9, 'Calculo II', 60, '2020-12-30 17:09:53'),
(10, 'Desenho Tecnico II', 60, '2020-12-30 17:09:53'),
(11, 'Fisica II', 60, '2020-12-30 17:10:23'),
(12, 'Meio Ambiente e Sustentabilida', 60, '2020-12-30 17:10:23');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinasdaturma`
--

CREATE TABLE `disciplinasdaturma` (
  `id` int(11) NOT NULL,
  `fk_id_turma` int(11) NOT NULL,
  `fk_id_disciplina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `disciplinasdaturma`
--

INSERT INTO `disciplinasdaturma` (`id`, `fk_id_turma`, `fk_id_disciplina`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 2, 7),
(8, 2, 8),
(9, 2, 9),
(10, 2, 10),
(11, 2, 11),
(12, 2, 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasdosemestre`
--

CREATE TABLE `notasdosemestre` (
  `id` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  `fk_id_aluno` int(11) NOT NULL,
  `fk_id_semestre` int(11) NOT NULL,
  `fk_id_turma` int(11) NOT NULL,
  `fk_id_disciplina` int(11) NOT NULL,
  `a1` float DEFAULT NULL,
  `a2` float DEFAULT NULL,
  `sub` float DEFAULT NULL,
  `a3` float DEFAULT NULL,
  `faltas1` int(2) DEFAULT NULL,
  `faltas2` int(2) DEFAULT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `notasdosemestre`
--

INSERT INTO `notasdosemestre` (`id`, `fk_id_usuario`, `fk_id_aluno`, `fk_id_semestre`, `fk_id_turma`, `fk_id_disciplina`, `a1`, `a2`, `sub`, `a3`, `faltas1`, `faltas2`, `datainclusao`) VALUES
(1, 23, 4, 2, 1, 5, 6, 6, 2, 8, 0, 0, '2021-01-01 18:04:52'),
(2, 23, 4, 2, 1, 4, 5, 6, 2, 7, 3, 0, '2021-01-01 18:05:25'),
(3, 23, 4, 2, 1, 6, 5, 6, 2, 7, 3, 3, '2021-01-01 19:29:54'),
(4, 23, 4, 3, 2, 8, 5, 6, 2, 9, 6, 3, '2021-01-16 20:02:05'),
(5, 23, 4, 3, 2, 7, 8, 7, 2, 0, 0, 0, '2021-01-17 11:33:54'),
(6, 23, 4, 3, 2, 1, 5, 5, 2, 6, 3, 0, '2021-01-17 11:34:44'),
(7, 23, 4, 3, 2, 2, 5, 5, 2, 6, 3, 0, '2021-01-17 11:35:27'),
(8, 23, 4, 3, 2, 3, 5, 5.5, 2, 6, 3, 0, '2021-01-17 11:36:07'),
(22, 23, 4, 2, 1, 12, 8, 7, NULL, NULL, 0, 0, '2021-01-27 23:28:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `semestre`
--

CREATE TABLE `semestre` (
  `id` int(11) NOT NULL,
  `descricao` varchar(7) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `semestre`
--

INSERT INTO `semestre` (`id`, `descricao`, `datainclusao`) VALUES
(1, '012020', '2021-01-01 18:00:40'),
(2, '022020', '2021-01-01 18:00:47'),
(3, '012021', '2021-01-01 18:00:56');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tpusuario`
--

CREATE TABLE `tpusuario` (
  `id` int(11) NOT NULL,
  `descricao` varchar(15) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tpusuario`
--

INSERT INTO `tpusuario` (`id`, `descricao`, `datainclusao`) VALUES
(1, 'Aluno', '2020-12-27 18:40:58'),
(2, 'Professor', '2020-12-27 18:40:58'),
(3, 'Outros', '2020-12-27 18:41:13');

-- --------------------------------------------------------

--
-- Estrutura da tabela `turma`
--

CREATE TABLE `turma` (
  `id` int(11) NOT NULL,
  `nome` varchar(7) NOT NULL,
  `vagas` int(3) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `turma`
--

INSERT INTO `turma` (`id`, `nome`, `vagas`, `datainclusao`) VALUES
(1, 'ENG-1NA', 50, '2020-12-30 17:16:54'),
(2, 'ENG-2NA', 50, '2020-12-30 17:16:54');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `fk_id_tpusuario` int(11) NOT NULL,
  `login` varchar(11) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `datainclusao` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `fk_id_tpusuario`, `login`, `senha`, `datainclusao`) VALUES
(21, 1, '12345678901', '123', '2020-12-30 10:40:55'),
(22, 2, '00646246500', '321', '2020-12-30 10:41:19'),
(23, 1, '11111111111', '123456', '2020-12-30 10:41:33'),
(24, 2, '321', '321', '2020-12-30 10:41:43');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_usuario` (`fk_id_usuario`);

--
-- Índices para tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `disciplinasdaturma`
--
ALTER TABLE `disciplinasdaturma`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_turma` (`fk_id_turma`),
  ADD KEY `fk_id_disciplina` (`fk_id_disciplina`);

--
-- Índices para tabela `notasdosemestre`
--
ALTER TABLE `notasdosemestre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_usuario` (`fk_id_usuario`),
  ADD KEY `fk_id_aluno` (`fk_id_aluno`),
  ADD KEY `fk_id_semestre` (`fk_id_semestre`),
  ADD KEY `fk_id_turma` (`fk_id_turma`),
  ADD KEY `fk_id_disciplina` (`fk_id_disciplina`);

--
-- Índices para tabela `semestre`
--
ALTER TABLE `semestre`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tpusuario`
--
ALTER TABLE `tpusuario`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `turma`
--
ALTER TABLE `turma`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_tpusuario` (`fk_id_tpusuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `disciplinasdaturma`
--
ALTER TABLE `disciplinasdaturma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `notasdosemestre`
--
ALTER TABLE `notasdosemestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de tabela `semestre`
--
ALTER TABLE `semestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tpusuario`
--
ALTER TABLE `tpusuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `turma`
--
ALTER TABLE `turma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `aluno_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `disciplinasdaturma`
--
ALTER TABLE `disciplinasdaturma`
  ADD CONSTRAINT `disciplinasdaturma_ibfk_1` FOREIGN KEY (`fk_id_turma`) REFERENCES `turma` (`id`),
  ADD CONSTRAINT `disciplinasdaturma_ibfk_2` FOREIGN KEY (`fk_id_disciplina`) REFERENCES `disciplina` (`id`);

--
-- Limitadores para a tabela `notasdosemestre`
--
ALTER TABLE `notasdosemestre`
  ADD CONSTRAINT `notasdosemestre_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `notasdosemestre_ibfk_2` FOREIGN KEY (`fk_id_aluno`) REFERENCES `aluno` (`id`),
  ADD CONSTRAINT `notasdosemestre_ibfk_3` FOREIGN KEY (`fk_id_semestre`) REFERENCES `semestre` (`id`),
  ADD CONSTRAINT `notasdosemestre_ibfk_4` FOREIGN KEY (`fk_id_turma`) REFERENCES `turma` (`id`),
  ADD CONSTRAINT `notasdosemestre_ibfk_5` FOREIGN KEY (`fk_id_disciplina`) REFERENCES `disciplina` (`id`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`fk_id_tpusuario`) REFERENCES `tpusuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
