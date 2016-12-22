/*manually create DB todonetworkdb */

DROP TABLE if exists users_passwords;
DROP TABLE if exists tasks;
DROP TABLE if exists followers;
DROP TABLE if exists users;

create table users (
id serial NOT NULL,
login text NOT NULL,
email text NOT NULL,
info text NOT NULL,
password text NOT NULL,
PRIMARY KEY (id)
);


create table followers (
user_id_who integer references users(id),
user_id_on_whom integer references users(id),
PRIMARY KEY (user_id_who, user_id_on_whom)
);

create table tasks (
id serial NOT NULL,
date date,
user_id integer references users(id),
name text,
description text,
progress integer,
goal integer,
tags text,
PRIMARY KEY (id)
);

INSERT INTO users (login, email, info, password) VALUES
('user1', 'user1@gmail.com', 'user1 info', 'password'),
('user2', 'user2@gmail.com', 'user2 info', 'password'),
('user3', 'user3@gmail.com', 'user3 info', 'password'),
('user4', 'user4@gmail.com', 'user4 info', 'password'),
('user5', 'user5@gmail.com', 'user5 info', 'password');


INSERT INTO followers VALUES
(2, 1),
(3, 1),
(4, 1),
(3, 2),
(5, 2);

INSERT INTO tasks (date,user_id,name,description,progress, goal,tags) VALUES
(TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 1', 'task1 for user 1', 1, 5, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 2', 'task2 for user 1', 0, 3, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 3', 'task3 for user 1', 2, 4, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 2, 'task 1', 'task1 for user 2', 2, 4, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 2, 'task 2', 'task2 for user 2', 2, 4, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 3, 'task 3', 'task3 for user 2', 2, 4, 'tasks'),
(TO_DATE('01012017', 'DDMMYYYY'), 3, 'task 4', 'task4 for user 2', 2, 4, 'tasks');