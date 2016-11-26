<?php

$data = json_decode(file_get_contents('php://input'));
$command= $data->{"command"};

if($command == "on")
{
	$url = 'http://192.168.0.17:8080';
	$senddat["com"] = "on";

	$ch = curl_init( $url );
	curl_setopt( $ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
	curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($senddat));
	curl_setopt( $ch, CURLOPT_FOLLOWLOCATION, 1);
	curl_setopt( $ch, CURLOPT_HEADER, 0);
	curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1);

	$response = curl_exec( $ch );

	echo($response);	
}
else if ($command =="off")
{
	echo "turnedOff";
}
else
{
	echo "test";
}

?>