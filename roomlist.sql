-- 创建库
CREATE DATABASE IF NOT EXISTS dorm_management CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE dorm_management;

-- 用户表（系统登录用户，可包含学生和管理员）
CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(128) NOT NULL,
  `type` ENUM('student','admin') NOT NULL DEFAULT 'student',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 学生表（详细信息）
CREATE TABLE `students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL, -- 关联 users.id
  `student_no` VARCHAR(32) NOT NULL UNIQUE,
  `name` VARCHAR(64) NOT NULL,
  `gender` ENUM('M','F') DEFAULT 'M',
  `college` VARCHAR(128),
  `class` VARCHAR(64),
  `phone` VARCHAR(20),
  `dorm_id` INT DEFAULT NULL, -- 当前宿舍分配，关联 rooms.id
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`user_id`),
  CONSTRAINT `fk_students_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 宿舍楼表
CREATE TABLE `dormitories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `address` VARCHAR(255),
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 房间表
CREATE TABLE `rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dorm_id` INT NOT NULL,
  `room_no` VARCHAR(32) NOT NULL,
  `capacity` INT NOT NULL DEFAULT 4,
  `occupied` INT NOT NULL DEFAULT 0, -- 已入住人数
  `floor` INT DEFAULT 1,
  `remark` VARCHAR(255),
  PRIMARY KEY (`id`),
  KEY `idx_dorm` (`dorm_id`),
  CONSTRAINT `fk_rooms_dorm` FOREIGN KEY (`dorm_id`) REFERENCES `dormitories`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 分配/入住记录表
CREATE TABLE `allocations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `assign_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('assigned','moved_out') DEFAULT 'assigned',
  `remark` VARCHAR(255),
  PRIMARY KEY (`id`),
  KEY `idx_student` (`student_id`),
  KEY `idx_room` (`room_id`),
  CONSTRAINT `fk_alloc_student` FOREIGN KEY (`student_id`) REFERENCES `students`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_alloc_room` FOREIGN KEY (`room_id`) REFERENCES `rooms`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 报修表
CREATE TABLE `repairs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `room_id` INT,
  `title` VARCHAR(128) NOT NULL,
  `description` TEXT,
  `status` ENUM('pending','processing','done') DEFAULT 'pending',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `process_time` DATETIME NULL,
  `handler` VARCHAR(64) DEFAULT NULL, -- 处理人
  PRIMARY KEY (`id`),
  KEY `idx_rep_student` (`student_id`),
  CONSTRAINT `fk_rep_student` FOREIGN KEY (`student_id`) REFERENCES `students`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
