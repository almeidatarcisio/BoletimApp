<?php
$ambiente = true;

if ($ambiente) { // Ambiente de Produção
	
	$HostName = "web-services-production-8c5e.up.railway.app";
	$HostUser = "root";
	$HostPass = "fasnFlzeUGlilYdXnknBqrdXdrMIVrLe";
	$DatabaseName = "railway";
	$Port = 31021;
	
} else { // Ambiente de Desenvolvimento

	$HostName = "localhost";
	$HostUser = "root";
	$HostPass = "";
	$DatabaseName = "unibrasba";
	
}
?>