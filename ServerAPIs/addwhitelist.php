<?php

if(!isset($_GET['hash'])){
	die();
}
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "cse323";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("-1");
}

$hash=$_GET['hash'];

$sql = "INSERT INTO whitelist (hash) VALUES ('$hash')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();


?>