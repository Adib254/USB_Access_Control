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

$sql = "SELECT timestamp, status FROM log";
$result = mysqli_query($conn, $sql);
$output='{"data": [';
if (mysqli_num_rows($result) > 0) {
    // output data of each row

    while($row = mysqli_fetch_assoc($result)) {
        $output = $output.'{"timestamp": "'.$row["timestamp"].'", "status": "'.$row["status"].'"}, ';
    }
}

mysqli_close($conn);

$output = substr($output, 0, -2)."]}";


die($output);



?>