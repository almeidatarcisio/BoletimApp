<?php
$ambiente = true;

if ($ambiente) { // Ambiente de Produção
	
	$HostName = "y5svr1t2r5xudqeq.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";
	$HostUser = "tgq6tv81thy58lbx";
	$HostPass = "el4ehp6cvkkac0el";
	$DatabaseName = "bledi7rw3if6j9p3";
	
} else { // Ambiente de Desenvolvimento

	$HostName = "localhost";
	$HostUser = "root";
	$HostPass = "";
	$DatabaseName = "unibrasba";
	
}
?>