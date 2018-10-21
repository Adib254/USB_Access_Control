<?php
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

if(!isset($_GET['P']) || $_GET['P']!="12345") die('invalid');

$sql = "UPDATE remoteunlock SET unlockSystem='0' WHERE id='1'";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();


?>