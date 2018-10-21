<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "cse323";
if(!isset($_GET['P']) || $_GET['P']!="12345") die('invalid');
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("-1");
}

$sql = "INSERT INTO log (status) VALUES ('Blocked')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();


?>