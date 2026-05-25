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

$fullName = $_POST['FullName'] ?? '';
$email    = $_POST['Email'] ?? '';
$password = $_POST['Password'] ?? '';

if (!empty($fullName) && !empty($email) && !empty($password)) {


    $fullName = mysqli_real_escape_string($con, $fullName);
    $email    = mysqli_real_escape_string($con, $email);
    $password = mysqli_real_escape_string($con, $password);

    $sql = "INSERT INTO Users (FullName, Email, Password) VALUES ('$fullName', '$email', '$password')";
    
    if (mysqli_query($con, $sql)) {
        echo json_encode(["status" => "success", "message" => "User berhasil didaftarkan"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Gagal Query: " . mysqli_error($con)]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Data tidak lengkap"]);
}
?>
