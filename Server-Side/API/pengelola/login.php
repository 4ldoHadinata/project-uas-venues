<?php
// required headers
header("Access-Control-Allow-Origin: http://localhost/project-uas/API/");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// database connection will be here
// files needed to connect to database
include_once '../config/database.php';
include_once 'pengelola.php';

// get database connection
$database = new Database();
$db = $database->getConnect();

// instantiate product object
$pengelola = new Pengelola($db);

// submitted data will be here
// get posted data
$data = json_decode(file_get_contents("php://input"));

// set product property values
$pengelola->nama_pengelola = $data->nama_pengelola;
$pengelola->password_pengelola = $data->password_pengelola;

$pengelola->login();

if ($pengelola->id_pengelola != null) {
    // create array
    $listpengelola_arr = array(
        "id_pengelola" =>  $pengelola->id_pengelola
    );

    // set response code - 200 OK
    http_response_code(200);

    // make it json format
    echo json_encode($listpengelola_arr);
} else {
    // set response code - 404 Not found
    http_response_code(404);

    // tell the user product does not exist
    echo json_encode(array("message" => "Login was failed."));
}
