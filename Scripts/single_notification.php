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
        'Authorization: key=' . "AAAA6cjZLe0:APA91bGBk5a5tTVzEjwFAOrJBOx5r1lI-qomtwgaGRNliHK81ChMrMnpVxgayJvgqORQnTfodzOyBZogopvikspAXUJIYDG7z1qMnyn8ZSUxvtnfPWut5H21dcFFM8Tr_N4QMx_faAs6UwXZPdh0o-KPuFtnwY2sZQ",
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

sendFCM("cbHBFwi4rHY:APA91bGNGnbPTYLcf1f6Q8Qkdo-1goC4I3pvemmoOiE_olXMIKBXLvRVi_CgBbEIbspsd77aQCs7fLL5o9ftrTVwMgOfLWxvQPpFpAbjNjUJ9_fdh2oUQjc3FkRoHdqwddy5KCbIjj-u");

?>