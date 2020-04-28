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
        !empty($data->nama_gedung) &&
        !empty($data->alamat_gedung) &&
        !empty($data->harga_sewa_gedung) &&
        !empty($data->luas_gedung) &&
        !empty($data->daya_tampung) &&
        !empty($data->kontak)
    ){
        //set product property values
        $gedung->nama_gedung = $data->nama_gedung;
        $gedung->alamat_gedung = $data->alamat_gedung;
        $gedung->harga_sewa_gedung = $data->harga_sewa_gedung;
        $gedung->luas_gedung = $data->luas_gedung;
        $gedung->daya_tampung = $data->daya_tampung;
        $gedung->kontak = $data->kontak;

        //create the product

        if($gedung->create()){
            //set response code = 201 created
            http_response_code(201);

            //tell the user
            echo json_encode(array("message" => "Gedung was created."));
        }

        //if unable to create the product, tell the user 
        else{
            //set response code = 503 service
            http_response_code(503);

            //tell the user 
            echo json_encode(array("message" => "Unable to create gedung."));
        }
    }
    //tell the user data is incomplete
    else{
        //set response code - r00 bad request
        http_response_code(400);

        //tell the user
        echo json_encode(array("message" => "Unable create gedung. Data is incomplete"));
    }
    ?>