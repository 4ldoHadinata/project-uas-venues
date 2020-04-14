<?php
    header("Access-Controll-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");

    include_once '../config/database.php';
    include_once 'gedung.php';

    $database = new Database();
    $db = $database->getConnect();

    $gedung= new Gedung($db);

    $stmt = $gedung->read();
    $num = $stmt->rowCount();

    if($num>0){
        $product_arr = array();
        $product_arr["records"]= array();

        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);
            $product_item=array(
                "id_gedung" => $id_gedung,
                "nama_gedung" => $nama_gedung,
                "alamat_gedung" => $alamat_gedung,
                "harga_sewa_gedung" => $harga_sewa_gedung,
                "luas_gedung" => $luas_gedung,
                "daya_tampung" => $daya_tampung,
                "kontak" => $kontak,
                "created" => $created,
            );

            array_push($product_arr["records"], $product_item);
        }

        http_response_code(200);

        echo json_encode($product_arr);

    }else{

        http_response_code(404);

        echo json_encode(
            array("message" => "Gedung tidak ditemukan.")
        );

    }
?>