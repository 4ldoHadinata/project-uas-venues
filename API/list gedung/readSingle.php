<?php
    header("Access-Controll-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");

    include_once '../config/database.php';
    include_once 'list-gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $listgedung= new ListGedung($db);

    // set ID property of record to read
    $listgedung->id_gedung = isset($_GET['id_gedung']) ? $_GET['id_gedung'] : die();
    
    // read the details of product to be edited
    $listgedung->readSingle();
    
    if($listgedung->mulai_sewa!=null){
        // create array
        $listgedung_arr = array(
            "id_gedung" =>  $listgedung->id_gedung,
            "mulai_sewa" => $listgedung->mulai_sewa,
            "selesai_sewa" => $listgedung->selesai_sewa,  
        );
    
        // set response code - 200 OK
        http_response_code(200);
    
        // make it json format
        echo json_encode($listgedung_arr);
    }
    
    else{
        // set response code - 404 Not found
        http_response_code(404);
    
        // tell the user product does not exist
        echo json_encode(array("message" => "Gedung does not exist."));
    }
?>