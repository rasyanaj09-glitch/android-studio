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

// Hanya menangkap Id, Name, Price, dan Stock sesuai struktur tabel baru
$id    = $_POST['Id'] ?? '';
$name  = $_POST['Name'] ?? '';
$price = $_POST['Price'] ?? '';
$stock = $_POST['Stock'] ?? '';

// Validasi: Pastikan Id dan Name tidak kosong
if (!empty($id) && !empty($name)) {

    // Amankan data dari ancaman SQL Injection
    $id    = mysqli_real_escape_string($con, $id);
    $name  = mysqli_real_escape_string($con, $name);
    $price = !empty($price) ? mysqli_real_escape_string($con, $price) : '0';
    $stock = !empty($stock) ? mysqli_real_escape_string($con, $stock) : '0';

    // Query UPDATE disederhanakan hanya untuk kolom yang tersisa
    $sql = "UPDATE products SET 
            Name = '$name', 
            Price = '$price', 
            Stock = '$stock' 
            WHERE Id = '$id'";
    
    if (mysqli_query($con, $sql)) {
        echo json_encode(["status" => "success", "message" => "Perubahan produk berhasil disimpan"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Gagal Update: " . mysqli_error($con)]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Data perubahan produk belum lengkap"]);
}
?>
