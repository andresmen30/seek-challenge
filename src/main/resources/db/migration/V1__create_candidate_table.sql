CREATE TABLE candidate (id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
                           salary_expected DECIMAL(10, 2) NOT NULL
);