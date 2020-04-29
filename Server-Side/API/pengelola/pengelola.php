<?php
class Pengelola
{
    // database connection and table name
    private $conn;

    // object properties
    public $id_pengelola;
    public $nama_pengelola;
    public $password_pengelola;

    // constructor
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // create new user record
    function create()
    {

        // insert query
        $query = "CALL reg_pengelola(:nama_pengelola,:password_pengelola)";

        // prepare the query
        $stmt = $this->conn->prepare($query);

        // sanitize
        $this->nama_pengelola = htmlspecialchars(strip_tags($this->nama_pengelola));
        $this->password_pengelola = htmlspecialchars(strip_tags($this->password_pengelola));

        // bind the values
        $stmt->bindParam(':nama_pengelola', $this->nama_pengelola);
        $stmt->bindParam(':password_pengelola', $this->password_pengelola);

        // hash the password before saving to database
        // $password_hash = password_hash($this->password_pengelola, PASSWORD_BCRYPT);
        // $stmt->bindParam(':password_pengelola', $password_hash);

        // execute the query, also check if query was successful
        if ($stmt->execute()) {
            return true;
        }

        return false;
    }

    function login()
    {
        $query = "CALL login_pengelola(:nama_pengelola,:password_pengelola)";

        $stmt  = $this->conn->prepare($query);

        // bind id of product to be updated
        // sanitize
        $this->nama_pengelola = htmlspecialchars(strip_tags($this->nama_pengelola));
        $this->password_pengelola = htmlspecialchars(strip_tags($this->password_pengelola));

        // bind the values
        $stmt->bindParam(':nama_pengelola', $this->nama_pengelola);
        $stmt->bindParam(':password_pengelola', $this->password_pengelola);

        $stmt->execute();
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // set values to object properties
        $this->id_pengelola = $row['id_pengelola'];
        // $this->password_pengelola = $row['password_pengelola'];
    }
}
