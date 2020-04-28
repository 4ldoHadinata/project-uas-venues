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
    include_once 'gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $gedung = new Gedung($db);

    //get posted data
    $data = json_decode(file_get_contents("php://input")); 

    //make sure data empty
    if(
        !empty($data->id_gedung)
    ){
        //set product property values
        $gedung->id_gedung = $data->id_gedung;

        //create the product

        if($gedung->delete()){
            //set response code = 201 created
            http_response_code(201);

            //tell the user
            echo json_encode(array("message" => "Gedung was deleted."));
        }

        //if unable to create the product, tell the user 
        else{
            //set response code = 503 service
            http_response_code(503);

            //tell the user 
            echo json_encode(array("message" => "Unable to delete gedung."));
        }
    }
    //tell the user data is incomplete
    else{
        //set response code - r00 bad request
        http_response_code(400);

        //tell the user
        echo json_encode(array("message" => "Unable delete gedung. Data is incomplete"));
    }
    ?>