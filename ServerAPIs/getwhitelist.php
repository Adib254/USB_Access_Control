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

$sql = "SELECT hash FROM whitelist";
$result = mysqli_query($conn, $sql);
$output="";
if (mysqli_num_rows($result) > 0) {
    // output data of each row

    while($row = mysqli_fetch_assoc($result)) {
    	$output = $output.$row["hash"]."|";
    }
}

mysqli_close($conn);

die($output);



?>