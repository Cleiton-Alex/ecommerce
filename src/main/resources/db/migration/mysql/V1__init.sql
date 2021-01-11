

CREATE TABLE `salesman` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `operation` bigint(20) NOT NULL,
  `account_type`bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `biography` varchar(255) NOT NULL,
  `getSelf_portrait` varchar(255) NOT NULL,
  `status` bigint(20) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `categories_id` bigint(20)  NOT NULL,
  `salesman_id` bigint(20)  NOT NULL,
  `name_products` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `value` bigint(20) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `stock` bigint(20) DEFAULT NULL,
  `photo` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `email` varchar(255)NOT NULL,
  `user_Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- --
-- -- Indexes for table `empresa`
-- --
ALTER TABLE `salesman`
  ADD PRIMARY KEY (`id`);

-- --
-- -- Indexes for table `funcionario`
-- --
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`id`);

-- --
-- -- Indexes for table `lancamento`
-- --
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`id`);


-- --
-- -- Indexes for table `lancamento`
-- --
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`id`);


-- --
-- -- AUTO_INCREMENT for table `empresa`
-- --
ALTER TABLE `salesman`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- -- AUTO_INCREMENT for table `funcionario`
-- --
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
-- --
-- -- AUTO_INCREMENT for table `lancamento`
-- --
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
--
--
-- --
-- -- AUTO_INCREMENT for table `lancamento`
-- --
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- --
-- -- Constraints for dumped tables
-- --
--
-- --
-- -- Constraints for table `funcionario`
-- --
ALTER TABLE `salesman`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
--
-- --
-- -- Constraints for table `lancamento`
-- --
ALTER TABLE `products`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);
--
--
-- --
-- -- Constraints for table `lancamento`
-- --
ALTER TABLE `products`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi6` FOREIGN KEY (`salesman_id`) REFERENCES `salesman` (`id`);