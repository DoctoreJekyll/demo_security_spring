INSERT INTO employee VALUES
                         (1,'john@gmail.com','john','$2a$12$.DNBDk4w3VlG6Fn3sNmUOeCw6VXuVa7O3oDCfvRrfMucwMn5VthQK','john'),
                         (2,'sam@gmail.com','sam','$2a$12$FujPv5E0QKVRVt1zIIu3GuTMxkvLs9dEgTQOx0z8WYfyjYJAZF0Zm','sam'),
                         (3,'alba@gmail.com','alba','$2y$10$94gU.Y7CnIKh7rZdbNPHGOB5P7QqWJ.6GlaX5yeQmUv/CkXR6kbYS','alba');


INSERT INTO roles VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO employee_roles VALUES (1,1),(2,2),(3,2);