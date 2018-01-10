SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE DATABASE IF NOT EXISTS tienda DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE tienda;

CREATE TABLE customer (
  `customer_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE customer_product (
  `customer_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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