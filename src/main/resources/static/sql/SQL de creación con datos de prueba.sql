SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE DATABASE IF NOT EXISTS tienda DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE tienda;

CREATE TABLE customer (
  `customer_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO customer (customer_id, `name`, email) VALUES
(1, 'Lina Marcela Malaver Gómez', 'marcela9409@gmail.com'),
(2, 'Yessica Alexandra Castillo', 'yesscastillo@gmail.com'),
(3, 'Jeisson Guerrero Quezada', 'jeisson@gmail.com'),
(4, 'Jose Hernán Castañeda', 'hernan@live.com'),
(5, 'Lina Maria Leon Blanco', 'lina@yahoo.es');

CREATE TABLE customer_product (
  `customer_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO customer_product (customer_id, product_id) VALUES
(1, 1),
(3, 1),
(2, 2),
(5, 2),
(1, 3),
(2, 3),
(3, 3),
(2, 4),
(4, 4),
(3, 5),
(5, 5),
(4, 6),
(5, 7),
(2, 8),
(4, 8),
(1, 9),
(1, 10);

CREATE TABLE order_detail (
  `order_detail_id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  `product_description` varchar(255) NOT NULL,
  `price` double(11,2) NOT NULL,
  `quantity` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE orders (
  `order_id` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `delivery_address` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `total` double(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE product (
  `product_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO product (product_id, `name`, price) VALUES
(1, 'Tablet LENOVO Yoga 3 8""  N', 430000.00),
(2, 'Tablet HUAWEI T1-701W WiFi - G', 300000.00),
(3, 'Tablet HUAWEI T3-10 9.6"" 4G 16Gb', 650000.00),
(4, 'iMac MNDY2E/A 1TB 21.5""', 5000000.00),
(5, 'PC All in One LENOVO - 300 - Intel Core i3 - 23"" Pulgadas', 1600000.00),
(6, 'Morral ASUS 16"" Argo Negro', 70000.00),
(7, 'Celular Asus Zenfone 3 4G Negro', 900000.00),
(8, 'Portátil ASUS - K556UQ - Intel Core i7 - 15.6"" Pulgadas', 2900000.00),
(9, 'Monitor Gamer ASUS VG245 H 24""', 850000.00),
(10, 'Mouse LOGITECH Inalámbrico Óptico M185 ', 30000.00);


ALTER TABLE customer
  ADD PRIMARY KEY (`customer_id`);

ALTER TABLE customer_product
  ADD PRIMARY KEY (`customer_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

ALTER TABLE order_detail
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

ALTER TABLE orders
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

ALTER TABLE product
  ADD PRIMARY KEY (`product_id`);


ALTER TABLE customer
  MODIFY `customer_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
ALTER TABLE order_detail
  MODIFY `order_detail_id` int(10) NOT NULL AUTO_INCREMENT;
ALTER TABLE orders
  MODIFY `order_id` int(10) NOT NULL AUTO_INCREMENT;
ALTER TABLE product
  MODIFY `product_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE customer_product
  ADD CONSTRAINT `customer_product_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES customer (`customer_id`),
  ADD CONSTRAINT `customer_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES product (`product_id`);

ALTER TABLE order_detail
  ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES product (`product_id`);

ALTER TABLE orders
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES customer (`customer_id`);