<?php

$mysqli = new mysqli("127.0.0.1", "root", "root", "data");

if ($mysqli->connect_error) {
    die('Connect Error (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error);
}

// $result = $mysqli->query('select * from info order by id desc limit 1');
// $row = $result->fetch_array(MYSQLI_ASSOC);

$result_t = $mysqli->query('select * from temperature order by id desc limit 1');
$row_t = $result_t->fetch_array(MYSQLI_ASSOC);

$result_i = $mysqli->query('select * from illumination order by id desc limit 1');
$row_i = $result_i->fetch_array(MYSQLI_ASSOC);

$result_p = $mysqli->query('select * from pm order by id desc limit 1');
$row_p = $result_p->fetch_array(MYSQLI_ASSOC);

// $rs = $row["id"] . $row["temperature"];

// $rs = '{"success":true,"msg":"temperature：' . $row["temperature"] . 
//                             '，illumination：' . $row["illumination"] . 
//                             '，pm：' . $row["pm"] . 
//                             '，co：' . $row["co"] . '"}';


// $rs = '{
//     "success" : true,
//     "temperature" : " '. $row["temperature"] .' ",
//     "illumination" : " '. $row["illumination"] .' ",
//     "pm" : " '. $row["pm"] .' ",
//     "co" : "接口暂未开放"
// }';

$rs = '{
    "success" : true,
    "temperature" : " '. $row_t["value"] .' ",
    "illumination" : " '. $row_i["value"] .' ",
    "pm" : " '. $row_p["value"] .' ",
    "co" : "接口暂未开放"
}';

print_r($rs);

$mysqli->close();

?>