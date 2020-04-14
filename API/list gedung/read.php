<?php
    header("Access-Controll-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");

    include_once '../config/database.php';
    include_once 'list-gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $listgedung= new ListGedung($db);

    $stmt = $listgedung->read();
    $num = $stmt->rowCount();

    if($num>0){
        $gedung_arr = array();
        $gedung_arr["records"]= array();

        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);
            $gedung_item=array(
                "id_gedung" => $id_gedung,
                "mulai_sewa" => $mulai_sewa,
                "selesai_sewa" => $selesai_sewa);

            array_push($gedung_arr["records"], $gedung_item);
        }

        http_response_code(200);

        echo json_encode($gedung_arr);

    }else{

        http_response_code(404);

        echo json_encode(
            array("message" => "Gedung tidak ditemukan.")
        );

    }
?>