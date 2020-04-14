<?php
    header("Access-Controll-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");

    include_once '../config/database.php';
    include_once 'gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $gedung= new Gedung($db);

    // set ID property of record to read
    $gedung->id_gedung = isset($_GET['id_gedung']) ? $_GET['id_gedung'] : die();
    
    // read the details of product to be edited
    $gedung->readSingle();
    
    if($gedung->nama_gedung!=null){
        // create array
        $gedung_arr = array(
            "id_gedung" =>  $gedung->id_gedung,
            "nama_gedung" => $gedung->nama_gedung,
            "alamat_gedung" => $gedung->alamat_gedung,
            "harga_sewa_gedung" => $gedung->harga_sewa_gedung,
            "luas_gedung" => $gedung->luas_gedung,
            "daya_tampung" => $gedung->daya_tampung,
            "kontak" => $gedung->kontak,
            "created" => $gedung->created,    
        );
    
        // set response code - 200 OK
        http_response_code(200);
    
        // make it json format
        echo json_encode($gedung_arr);
    }
    
    else{
        // set response code - 404 Not found
        http_response_code(404);
    
        // tell the user product does not exist
        echo json_encode(array("message" => "Gedung does not exist."));
    }
?>