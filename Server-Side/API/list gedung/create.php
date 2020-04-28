<?php
    //require headers
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Age: 3600");
    header("Access-Control_Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    //get database connection
    include_once '../config/database.php';

    //instantiate product obejct
    include_once 'list-gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $listgedung = new ListGedung($db);

    //get posted data
    $data = json_decode(file_get_contents("php://input"));

    //make sure data empty
    if(
        !empty($data->mulai_sewa) &&
        !empty($data->selesai_sewa)
    ){
        //set product property values
        $listgedung->mulai_sewa = $data->mulai_sewa;
        $listgedung->selesai_sewa = $data->selesai_sewa;

        //create the product

        if($listgedung->create()){
            //set response code = 201 created
            http_response_code(201);

            //tell the user
            echo json_encode(array("message" => "List Gedung was created."));
        }

        //if unable to create the product, tell the user 
        else{
            //set response code = 503 service
            http_response_code(503);

            //tell the user 
            echo json_encode(array("message" => "Unable to create list gedung."));
        }
    }
    //tell the user data is incomplete
    else{
        //set response code - r00 bad request
        http_response_code(400);

        //tell the user
        echo json_encode(array("message" => "Unable create list gedung. Data is incomplete"));
    }
    ?>