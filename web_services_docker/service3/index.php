<?php
header('Content-Type: application/json charset=utf-8');

$response = array();
$response["erro"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	include 'dbConnection.php';
	
	$conn = new mysqli($HostName, $HostUser,
					$HostPass, $DatabaseName, $Port);
		
	mysqli_set_charset($conn, "utf8");
	
	if ($conn->connect_error) { 
	
		die("Não conectou....: " . $conn->connect_error);
	}
	
	$login = "'".$_POST['login']."'";
	$semestre = "'".$_POST['semestre']."'";
	
	$sql = "SELECT aluno.nome AS aluno,
				   disciplina.nome AS disciplina, 
				   turma.nome AS turma, 
				   IF(notasdosemestre.a1 IS NULL, '-', notasdosemestre.a1) AS a1, 
				   IF(notasdosemestre.a2 IS NULL, '-', notasdosemestre.a2) AS a2, 
				   IF(notasdosemestre.sub IS NULL, '-', notasdosemestre.sub) AS sub, 
				   IF(notasdosemestre.a3 IS NULL, '-', notasdosemestre.a3) AS a3, 
				   IF(notasdosemestre.faltas1 IS NULL, '-', notasdosemestre.faltas1) AS faltasA1, 
				   IF(notasdosemestre.faltas2 IS NULL, '-', notasdosemestre.faltas2) AS faltasA2 
			FROM usuario, aluno, semestre, disciplina, turma, notasdosemestre 
			WHERE usuario.login = $login
			AND semestre.descricao = $semestre
			AND notasdosemestre.fk_id_usuario = usuario.id
			AND notasdosemestre.fk_id_aluno = aluno.id
			AND notasdosemestre.fk_id_semestre = semestre.id
			AND notasdosemestre.fk_id_disciplina = disciplina.id
			AND notasdosemestre.fk_id_turma = turma.id";
	
	$result = $conn->query($sql);
	
//	print_r($result);
	
	if ($result->num_rows > 0) {

		$response["erro"] = false;
		$response["registros"] = $result->num_rows;

		while($row = mysqli_fetch_object($result)){
				$response["data"][] = $row;
		}
		
	} else {

		$response["mensagem"] = "Você não se matriculou no semestre selecionado!";
		
	}
	
	$conn->close();
	
}
echo json_encode($response);
?>