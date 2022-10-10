<?php
header('Content-Type: application/json charset=utf-8');

$response = array();
$response["erro"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	include 'dbConnection.php';
	
	$conn = new mysqli($HostName, $HostUser,
					$HostPass, $DatabaseName);
		
	mysqli_set_charset($conn, "utf8");
	
	if ($conn->connect_error) { 
	
		die("Não conectou....: " . $conn->connect_error);
	}
	
//	$login = "'".$_POST['login']."'";
//	$senha = "'".$_POST['senha']."'";
	
	$sql = "SELECT descricao 
			FROM semestre
			ORDER BY semestre.id DESC";
	
	$result = $conn->query($sql);
	
//	print_r($result);
	
	if ($result->num_rows > 0) {

		$response["erro"] = false;
		$response["registros"] = $result->num_rows;

		while($row = mysqli_fetch_object($result)){
				$response["data"][] = $row;
		}

	} else {

		$response["mensagem"] = "Não há semestre cadastrado!";
		
	}
	
	$conn->close();
	
}
echo json_encode($response);

?>