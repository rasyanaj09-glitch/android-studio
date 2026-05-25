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

// Menangkap parameter ID dari Volley Android
$id = $_POST['Id'] ?? '';

if (!empty($id)) {
    // Amankan parameter ID dari ancaman SQL Injection
    $id = mysqli_real_escape_string($con, $id);

    // Query eksekusi hapus baris
    $sql = "DELETE FROM products WHERE Id = '$id'";
    
    if (mysqli_query($con, $sql)) {
        echo json_encode(["status" => "success", "message" => "Produk sukses dihapus"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Gagal menghapus: " . mysqli_error($con)]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Aduh ID nya kosong cik"]);
}
?>
