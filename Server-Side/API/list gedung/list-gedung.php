<?php
class ListGedung{
    private $conn;
    private $table_name = "list_gedung";

    public $id_gedung;
    public $mulai_sewa;
    public $selesai_sewa;

    public function __construct($db){
        $this->conn = $db;
    }

    function read(){
        $query = "SELECT 
                        g.id_gedung,
                        g.mulai_sewa,
                        g.selesai_sewa
                FROM
                        " . $this->table_name . " g ";
        
        $stmt  =$this->conn->prepare($query);

        $stmt->execute();
        return $stmt;
    }
    function readSingle(){
        $query = "SELECT 
                        g.id_gedung,
                        g.mulai_sewa,
                        g.selesai_sewa
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
        $this->mulai_sewa = $row['mulai_sewa'];
        $this->selesai_sewa = $row['selesai_sewa'];
    }
    function create(){
        //query to insert record
        $query ="INSERT INTO 
                    " . $this->table_name . " 
                SET 
                    mulai_sewa=:mulai_sewa, selesai_sewa=:selesai_sewa";
        //prepare query
        $stmt = $this->conn->prepare($query);

        //sanitize
        $this->mulai_sewa=htmlspecialchars(strip_tags($this->mulai_sewa));
        $this->selesai_sewa=htmlspecialchars(strip_tags($this->selesai_sewa));
        // var_dump($this->price);

        //bind values
        $stmt->bindParam(":mulai_sewa", $this->mulai_sewa);
        $stmt->bindParam(":selesai_sewa", $this->selesai_sewa);
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
                mulai_sewa=:mulai_sewa, selesai_sewa=:selesai_sewa
                WHERE id_gedung=:id_gedung";
        //prepare query
        $stmt = $this->conn->prepare($query);

        //sanitize
        $stmt->bindParam(":mulai_sewa", $this->mulai_sewa);
        $stmt->bindParam(":selesai_sewa", $this->selesai_sewa);
        $this->id_gedung=htmlspecialchars(strip_tags($this->id_gedung));

        // var_dump($this->price);

        //bind values
        $stmt->bindParam(":mulai_sewa", $this->mulai_sewa);
        $stmt->bindParam(":selesai_sewa", $this->selesai_sewa);
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