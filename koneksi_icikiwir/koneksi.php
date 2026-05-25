<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "icikiwir";
$port = 3307;

// Menonaktifkan laporan eror teks agar tidak merusak JSON Android
mysqli_report(MYSQLI_REPORT_OFF); 

$con = mysqli_connect($host, $user, $pass, $db, $port);

// JANGAN gunakan die(), biarkan dicek di file register.php
if (!$con) {
    // Koneksi gagal tetap tersimpan di variabel $con untuk divalidasi nanti
}
?>
