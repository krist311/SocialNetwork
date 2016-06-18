/*manually create DB todonetworkdb */

DROP TABLE if exists users_passwords;
DROP TABLE if exists tasks;
DROP TABLE if exists followers;
DROP TABLE if exists users;

create table users (
id integer,
login text NOT NULL,
email text NOT NULL,
info text NOT NULL,
PRIMARY KEY (id)
);

create table users_passwords (
user_id integer references users(id),
password text NOT NULL,
PRIMARY KEY (user_id)
);

create table followers (
user_id_who integer references users(id),
user_id_on_whom integer references users(id),
PRIMARY KEY (user_id_who, user_id_on_whom)
);

create table tasks (
id integer,
date date,
user_id integer references users(id),
name text NOT NULL,
description text NOT NULL,
progress integer,
goal integer,
tags text,
PRIMARY KEY (id)
);

INSERT INTO users VALUES
(1, 'user1', 'user1@gmail.com', 'user1_info'),
(2, 'user2', 'user2@gmail.com', 'user2_info');

INSERT INTO users_passwords VALUES
(1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8'),
(2, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');

INSERT INTO followers VALUES
(1, 2);

INSERT INTO tasks VALUES
(1, TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 1', 'task1 for user 1', 1, 5, 'tasks'),
(2, TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 2', 'task2 for user 1', 0, 3, 'tasks'),
(3, TO_DATE('01012017', 'DDMMYYYY'), 1, 'task 3', 'task3 for user 1', 2, 4, 'tasks');