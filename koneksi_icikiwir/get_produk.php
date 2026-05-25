<?php

require_once __DIR__ . '/koneksi.php'; 

if (!$con) {
    die(json_encode(["status" => "error", "message" => "Koneksi database gagal"]));
}

// Mengambil data dari tabel products
$sql = "SELECT * FROM products";
$query = mysqli_query($con, $sql);

$result = array();
if ($query) {
    while($row = mysqli_fetch_assoc($query)){
       
        $result[] = array(
            'Id'    => (int)$row['Id'], 
            'Name'  => $row['Name'],
            'Price' => $row['Price'],
            'Stock' => (int)$row['Stock'] 
        );
    }
    
    header('Content-Type: application/json');
    
    echo json_encode([
        "status" => "success",
        "data"   => $result
    ]);
} else {
    header('Content-Type: application/json');
    echo json_encode([
        "status" => "error",
        "message" => "Gagal mengambil data dari database: " . mysqli_error($con)
    ]);
}
?>
