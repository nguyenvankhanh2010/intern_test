<?php
// advanced_stats.php
header('Content-Type: application/json');

// Đường dẫn tới file CSV
$csvFile = __DIR__ . '/diem_thi_thpt_2024.csv';

if (!file_exists($csvFile)) {
    http_response_code(500);
    echo json_encode(['error' => 'File CSV not found']);
    exit;
}

$scores = ['math' => [], 'physics' => [], 'chemistry' => []];
$file = fopen($csvFile, 'r');

// Đọc dòng tiêu đề (nếu có)
$header = fgetcsv($file);
if ($header === false) {
    http_response_code(500);
    echo json_encode(['error' => 'Unable to read CSV header']);
    exit;
}

// Giả định cột: id, registrationNumber, name, math, physics, chemistry
while (($row = fgetcsv($file)) !== false) {
    if (count($row) >= 6) {
        $scores['math'][] = (float)$row[3];
        $scores['physics'][] = (float)$row[4];
        $scores['chemistry'][] = (float)$row[5];
    }
}
fclose($file);

// Hàm tính trung bình
function calculateMean($arr) {
    return count($arr) > 0 ? array_sum($arr) / count($arr) : 0;
}

// Hàm tính độ lệch chuẩn
function calculateStdDev($arr) {
    $mean = calculateMean($arr);
    if (count($arr) <= 1) return 0;
    $variance = array_sum(array_map(function($x) use ($mean) { return pow($x - $mean, 2); }, $arr)) / (count($arr) - 1);
    return sqrt($variance);
}

// Hàm tính phân vị (Q1, Q2, Q3)
function calculateQuartiles($arr) {
    sort($arr);
    $count = count($arr);
    if ($count === 0) return [0, 0, 0];

    $q2 = $count % 2 == 0 ? ($arr[$count/2 - 1] + $arr[$count/2]) / 2 : $arr[($count-1)/2];
    $q1 = $count % 4 == 0 ? ($arr[$count/4 - 1] + $arr[$count/4]) / 2 : $arr[floor($count/4)];
    $q3 = $count % 4 == 0 ? ($arr[3*$count/4 - 1] + $arr[3*$count/4]) / 2 : $arr[floor(3*$count/4)];
    return [$q1, $q2, $q3];
}

// Tính toán thống kê cho mỗi môn
$stats = [];
foreach (['math', 'physics', 'chemistry'] as $subject) {
    $stats[$subject] = [
        'mean' => round(calculateMean($scores[$subject]), 2),
        'stdDev' => round(calculateStdDev($scores[$subject]), 2),
        'quartiles' => array_map('round', calculateQuartiles($scores[$subject]), [2, 2, 2]) // [Q1, Q2, Q3]
    ];
}

echo json_encode($stats);
?>