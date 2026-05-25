<?php

error_reporting(0);
ini_set('display_errors', 0);

header('Content-Type: application/json');
ob_clean(); 

if (!file_exists(__DIR__ . '/koneksi.php')) {
    echo json_encode(["status" => "error", "message" => "File koneksi.php tidak ditemukan!"]);
    exit();
}

require_once __DIR__ . '/koneksi.php'; 

if (!$con) {
    echo json_encode(["status" => "error", "message" => "Koneksi database gagal: " . mysqli_connect_error()]);
    exit();
}

// Menangkap parameter POST yang valid dari Volley Android
$name  = $_POST['Name'] ?? '';
$price = $_POST['Price'] ?? '';
$stock = $_POST['Stock'] ?? '';

// Validasi: Memastikan nama produk tidak kosong saat proses input
if (!empty($name)) {

    // Amankan data dari SQL Injection
    $name  = mysqli_real_escape_string($con, $name);
    $price = !empty($price) ? mysqli_real_escape_string($con, $price) : '0'; 
    $stock = !empty($stock) ? mysqli_real_escape_string($con, $stock) : '0'; 

    // Query INSERT disederhanakan hanya memasukkan kolom Name, Price, dan Stock
    $sql = "INSERT INTO products (Name, Price, Stock) 
            VALUES ('$name', '$price', '$stock')";
    
    if (mysqli_query($con, $sql)) {
        echo json_encode(["status" => "success", "message" => "Produk berhasil ditambahkan"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Gagal Query: " . mysqli_error($con)]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Nama produk wajib diisi!"]);
}
?>
