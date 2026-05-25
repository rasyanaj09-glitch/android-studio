<?php
error_reporting(0);
ini_set('display_errors', 0);
header('Content-Type: application/json');

require_once __DIR__ . '/koneksi.php';


$sql = "SELECT Id, Name FROM Categories"; 
$query = mysqli_query($con, $sql);

$response = array();

if ($query) {
    while ($row = mysqli_fetch_assoc($query)) {
        $response[] = $row;
    }
    echo json_encode(["status" => "success", "data" => $response]);
} else {
    echo json_encode(["status" => "error", "message" => "Gagal mengambil data kategori"]);
}
?>
