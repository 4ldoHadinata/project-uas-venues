<?php
// required headers
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// database connection will be here
// files needed to connect to database
include_once '../config/database.php';
include_once 'admin.php';

// get database connection
$database = new Database();
$db = $database->getConnect();

// instantiate product object
$admin = new Admin($db);

// submitted data will be here
// get posted data
$data = json_decode(file_get_contents("php://input"));

// set product property values
$admin->username_admin = $data->username_admin;
$admin->password_admin = $data->password_admin;

$admin->login();

if ($admin->id_admin != null) {
    // create array
    $listadmin_arr = array(
        "id_admin" =>  $admin->id_admin
    );

    // set response code - 200 OK
    http_response_code(200);

    // make it json format
    echo json_encode($listadmin_arr);
} else {
    // set response code - 404 Not found
    http_response_code(404);

    // tell the user product does not exist
    echo json_encode(array("message" => "Login was failed."));
}
