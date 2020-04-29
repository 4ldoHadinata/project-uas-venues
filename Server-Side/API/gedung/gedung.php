<?php
class Gedung{
    private $conn;
    private $table_name = "detail_gedung";

    public $id_gedung;
    public $nama_gedung;
    public $alamat_gedung;
    public $harga_sewa_gedung;
    public $luas_gedung;
    public $daya_tampung;
    public $kontak;
    public $created;

    public function __construct($db){
        $this->conn = $db;
    }

    function read(){
        $query = "SELECT 
                        g.id_gedung,
                        g.nama_gedung,
                        g.alamat_gedung,
                        g.harga_sewa_gedung,
                        g.luas_gedung,
                        g.daya_tampung,
                        g.kontak,
                        g.created
                FROM
                        " . $this->table_name . " g
                ORDER BY g.created DESC";
        
        $stmt  =$this->conn->prepare($query);

        $stmt->execute();
        return $stmt;
    }
    function readSingle(){
        $query = "SELECT 
                        g.id_gedung,
                        g.nama_gedung,
                        g.alamat_gedung,
                        g.harga_sewa_gedung,
                        g.luas_gedung,
                        g.daya_tampung,
                        g.kontak,
                        g.created
                FROM
                        " . $this->table_name . " g
                WHERE g.id_gedung = ?
                LIMIT 
                    0,1";
        
        $stmt  =$this->conn->prepare($query);

        // bind id of product to be updated
        $stmt->bindParam(1, $this->id_gedung);

        $stmt->execute();
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        // set values to object properties
        $this->nama_gedung = $row['nama_gedung'];
        $this->alamat_gedung = $row['alamat_gedung'];
        $this->harga_sewa_gedung = $row['harga_sewa_gedung'];
        $this->luas_gedung = $row['luas_gedung'];
        $this->daya_tampung = $row['daya_tampung'];
        $this->daya_tampung = $row['kontak'];
        $this->created = $row['created'];
    }
    function create(){
        //query to insert record
        $query ="INSERT INTO 
                    " . $this->table_name . " 
                SET 
                    nama_gedung=:nama_gedung, alamat_gedung=:alamat_gedung, harga_sewa_gedung=:harga_sewa_gedung, luas_gedung=:luas_gedung, daya_tampung=:daya_tampung, kontak=:kontak";
        //prepare query
        $stmt = $this->conn->prepare($query);

        //sanitize
        $this->nama_gedung=htmlspecialchars(strip_tags($this->nama_gedung));
        $this->alamat_gedung=htmlspecialchars(strip_tags($this->alamat_gedung));
        $this->harga_sewa_gedung=htmlspecialchars(strip_tags($this->harga_sewa_gedung));
        $this->luas_gedung=htmlspecialchars(strip_tags($this->luas_gedung));
        $this->daya_tampung=htmlspecialchars(strip_tags($this->daya_tampung));
        $this->kontak=htmlspecialchars(strip_tags($this->kontak));
        // $this->created=htmlspecialchars(strip_tags($this->created));

        // var_dump($this->price);

        //bind values
        $stmt->bindParam(":nama_gedung", $this->nama_gedung);
        $stmt->bindParam(":alamat_gedung", $this->alamat_gedung);
        $stmt->bindParam(":harga_sewa_gedung", $this->harga_sewa_gedung);
        $stmt->bindParam(":luas_gedung", $this->luas_gedung);
        $stmt->bindParam(":daya_tampung", $this->daya_tampung);
        $stmt->bindParam(":kontak", $this->kontak);
        // $stmt->bindParam(":created", $this->created);

        // var_dump($stmt);
        //execute query
        if($stmt->execute()){
            return true;
        }
        
        return false;
    }
    function update(){
        //query to insert record
        $query ="UPDATE  
                    " . $this->table_name . " 
                SET 
                nama_gedung=:nama_gedung, alamat_gedung=:alamat_gedung, harga_sewa_gedung=:harga_sewa_gedung, luas_gedung=:luas_gedung, daya_tampung=:daya_tampung, kontak=:kontak
                WHERE id_gedung=:id_gedung";
        //prepare query
        $stmt = $this->conn->prepare($query);

        //sanitize
        $this->nama_gedung=htmlspecialchars(strip_tags($this->nama_gedung));
        $this->alamat_gedung=htmlspecialchars(strip_tags($this->alamat_gedung));
        $this->harga_sewa_gedung=htmlspecialchars(strip_tags($this->harga_sewa_gedung));
        $this->luas_gedung=htmlspecialchars(strip_tags($this->luas_gedung));
        $this->daya_tampung=htmlspecialchars(strip_tags($this->daya_tampung));
        $this->kontak=htmlspecialchars(strip_tags($this->kontak));
        // $this->created=htmlspecialchars(strip_tags($this->created));
        $this->id_gedung=htmlspecialchars(strip_tags($this->id_gedung));

        // var_dump($this->price);

        //bind values
        $stmt->bindParam(":nama_gedung", $this->nama_gedung);
        $stmt->bindParam(":alamat_gedung", $this->alamat_gedung);
        $stmt->bindParam(":harga_sewa_gedung", $this->harga_sewa_gedung);
        $stmt->bindParam(":luas_gedung", $this->luas_gedung);
        $stmt->bindParam(":daya_tampung", $this->daya_tampung);
        $stmt->bindParam(":kontak", $this->kontak);
        // $stmt->bindParam(":created", $this->created);
        $stmt->bindParam(":id_gedung", $this->id_gedung);

        // var_dump($stmt);
        //execute query
        if($stmt->execute()){
            return true;
        }
        
        return false;
    }
    function delete(){
        //query to insert record
        $query ="DELETE FROM   
                    " . $this->table_name . " 
                WHERE id_gedung=:id_gedung";
        //prepare query
        $stmt = $this->conn->prepare($query);

        //sanitize
        $this->id_gedung=htmlspecialchars(strip_tags($this->id_gedung));

        //bind values
        $stmt->bindParam(":id_gedung", $this->id_gedung);

        // var_dump($stmt);
        //execute query
        if($stmt->execute()){
            return true;
        }
        
        return false;
    }
}
?>