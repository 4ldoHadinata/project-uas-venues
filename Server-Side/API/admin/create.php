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

$admin = new Admin($db);

// submitted data will be here
// get posted data
$data = json_decode(file_get_contents("php://input"));

// set product property values
$admin->username_admin = $data->username_admin;
$admin->password_admin = $data->password_admin;

// use the create() method here
// create the user
if (
    !empty($admin->username_admin) &&
    !empty($admin->password_admin) &&
    $admin->create()
) {

    // set response code
    http_response_code(200);

    // display message: user was created
    echo json_encode(array("message" => "Admin was created."));
}

// message if unable to create user
else {

    // set response code
    http_response_code(400);

    // display message: unable to create user
    echo json_encode(array("message" => "Unable to create admin."));
}
