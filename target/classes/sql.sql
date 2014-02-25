CREATE DATABASE studinfosys;
use studinfosys;
CREATE TABLE `users` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL unique,
  `password` varchar(200) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `role` int NOT NULL,
  `user_id` int(6) unique,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO users (username, password, active) VALUES ('admin', '$2a$10$iqYRJfme0EH8cnkkQKPFmOxpFs8SvzL4tXwnGf9LklCd6qr5NXvMy',true);
INSERT INTO user_roles (role, user_id) VALUES (0,(SELECT id from users where username = 'admin'));
