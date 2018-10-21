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

$sql = "SELECT unlockSystem FROM remoteunlock WHERE id='1' AND unlockSystem='1'";
$result = mysqli_query($conn, $sql);
$output="";
if (mysqli_num_rows($result) > 0) {
	$sql = "UPDATE remoteunlock SET unlockSystem='0' WHERE id='1'";
	$conn->query($sql);
	$output="unlock";
}

mysqli_close($conn);



die($output);


?>