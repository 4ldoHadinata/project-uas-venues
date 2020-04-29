<?php
class Admin
{
    // database connection and table name
    private $conn;

    // object properties
    public $id_admin;
    public $username_admin;
    public $password_admin;

    // constructor
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // create new user record
    function create()
    {

        // insert query
        $query = "CALL reg_admin(:username_admin,:password_admin)";

        // prepare the query
        $stmt = $this->conn->prepare($query);

        // sanitize
        $this->username_admin = htmlspecialchars(strip_tags($this->username_admin));
        $this->password_admin = htmlspecialchars(strip_tags($this->password_admin));

        // bind the values
        $stmt->bindParam(':username_admin', $this->username_admin);
        $stmt->bindParam(':password_admin', $this->password_admin);

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
        $query = "CALL login_admin(:username_admin,:password_admin)";

        $stmt  = $this->conn->prepare($query);

        // bind id of product to be updated
        // sanitize
        $this->username_admin = htmlspecialchars(strip_tags($this->username_admin));
        $this->password_admin = htmlspecialchars(strip_tags($this->password_admin));

        // bind the values
        $stmt->bindParam(':username_admin', $this->username_admin);
        $stmt->bindParam(':password_admin', $this->password_admin);

        $stmt->execute();
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // set values to object properties
        $this->id_admin = $row['id_admin'];
        // $this->password_pengelola = $row['password_pengelola'];
    }
}
