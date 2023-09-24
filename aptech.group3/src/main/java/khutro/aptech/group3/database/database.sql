/* 
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
*/
/**
* Author:  CLD
* Created: Sep 23, 2023
*/

CREATE DATABASE IF NOT EXISTS khutro;

USE khutro;

-- Tạo bảng User nếu nó chưa tồn tại
CREATE TABLE IF NOT EXISTS User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name NVARCHAR(255),
    phone INT,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS Contact (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,  -- Thay đổi 255 bằng chiều dài tối đa cho email của bạn
    title VARCHAR(255),  -- Cũng có thể thay đổi 255 bằng giá trị tối đa cho title
    phone INT,
    content TEXT
);


-- Tạo bảng Room nếu nó chưa tồn tại
CREATE TABLE IF NOT EXISTS Room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    room_name NVARCHAR(255),
    room_description TEXT,
    price FLOAT,
    max_occupancy INT,
    status BOOLEAN,
    room_area DECIMAL(10, 2),
    type VARCHAR(255),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng RoomImages nếu nó chưa tồn tại
CREATE TABLE IF NOT EXISTS RoomImages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    image_url VARCHAR(255),
    room_id INT,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES Room(id)
);

-- Tạo bảng HoaDon nếu nó chưa tồn tại
CREATE TABLE IF NOT EXISTS HoaDon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tien_dien INT,
    tien_nuoc INT,
    tien_dich_vu INT,
    FOREIGN KEY (id) REFERENCES Room(id)
);

-- Tạo bảng Customers nếu nó chưa tồn tại
CREATE TABLE IF NOT EXISTS Customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(255),
    room_id INT,
    phone INT,
    FOREIGN KEY (room_id) REFERENCES Room(id)
);
