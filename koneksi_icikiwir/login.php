<?php
// Gunakan path lengkap agar file ditemukan
require_once __DIR__ . '/koneksi.php'; 

// Mengambil data (bisa dari POST maupun GET untuk tes)
$email    = $_REQUEST['Email'] ?? '';
$password = $_REQUEST['Password'] ?? '';

if (!empty($email) && !empty($password)) {
    // Pastikan pakai $con (satu huruf 'n') agar cocok dengan koneksi.php
    $sql = "SELECT * FROM Users WHERE Email = '$email' AND Password = '$password'";
    $query = mysqli_query($con, $sql); 

    if ($query) {
        $check = mysqli_num_rows($query);
        if ($check > 0) {
            $data = mysqli_fetch_assoc($query);
            echo json_encode([
                "status" => "success",
                "message" => "Login Berhasil",
                "full_name" => $data['FullName']
            ]);
        } else {
            echo json_encode(["status" => "error", "message" => "Email atau Password salah"]);
        }
    } else {
        // Jika ada error pada query (misal nama tabel salah)
        echo json_encode(["status" => "error", "message" => mysqli_error($con)]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Data tidak lengkap"]);
}
?>
