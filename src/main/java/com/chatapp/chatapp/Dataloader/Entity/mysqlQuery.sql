CREATE TABLE `chatapp.tbl_application_status` (
                                          `application_id` int NOT NULL AUTO_INCREMENT,
                                          `created_date` datetime(6) DEFAULT NULL,
                                          `updated_date` datetime(6) DEFAULT NULL,
                                          `status` varchar(20) DEFAULT NULL,
                                          `type` varchar(30) DEFAULT NULL,
                                          `name` varchar(50) DEFAULT NULL,
                                          `description` varchar(80) DEFAULT NULL,
                                          PRIMARY KEY (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `chatapp.tbl_otp_token` (
                                 `otp_id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_time` datetime(6) DEFAULT NULL,
                                 `email` varchar(100) DEFAULT NULL,
                                 `expiration_time` datetime(6) DEFAULT NULL,
                                 `otp` varchar(10) DEFAULT NULL,
                                 PRIMARY KEY (`otp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- 1. Create `user_role` table
CREATE TABLE chatapp.user_role (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Create `user_cred` table (avoid naming it 'user' as it's a reserved word)
CREATE TABLE chatapp.user_cred (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   username VARCHAR(100) NOT NULL UNIQUE,
                                   password VARCHAR(255) NOT NULL
);

-- 3. Create `user_roles` join table
CREATE TABLE chatapp.user_roles (
                                    user_id BIGINT NOT NULL,
                                    role_id BIGINT NOT NULL,
                                    PRIMARY KEY (user_id, role_id),
                                    FOREIGN KEY (user_id) REFERENCES user_cred(id) ON DELETE CASCADE,
                                    FOREIGN KEY (role_id) REFERENCES user_role(id) ON DELETE CASCADE
);