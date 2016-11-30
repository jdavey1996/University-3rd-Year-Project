<?php

function sendFCM($id) {
$url = 'https://fcm.googleapis.com/fcm/send';
$fields = array (
        'to' => $id,
        'data' => array (
                "notif1" => "test",
                "notif2" => "Title"
        )
);

$fields = json_encode ( $fields );
$headers = array (
        'Authorization: key=' . "_____",
        'Content-Type: application/json'
);

$ch = curl_init ();
curl_setopt ( $ch, CURLOPT_URL, $url );
curl_setopt ( $ch, CURLOPT_POST, true );
curl_setopt ( $ch, CURLOPT_HTTPHEADER, $headers );
curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
curl_setopt ( $ch, CURLOPT_POSTFIELDS, $fields );

$result = curl_exec ( $ch );
echo($result);
curl_close ( $ch );
}

sendFCM("____device ID___");

?>