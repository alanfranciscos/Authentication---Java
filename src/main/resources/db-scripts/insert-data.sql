CREATE EXTENSION IF NOT EXISTS pgcrypto;

insert into user_model (email, fullname, password, role) values ('a@a', 'tiburssin tiburssius', crypt('a', gen_salt('bf')), 'MASTER');
insert into user_model (email, fullname, password, role) values ('s@s', 'aroldo aroldus', crypt('a', gen_salt('bf')), 'MASTER');
insert into user_model (email, fullname, password, role)  values ('d@d', 'cabral cabralzius', crypt('a', gen_salt('bf')), 'CLIENT');
insert into user_model (email, fullname, password, role)  values ('f@f', 'tonin toninhus', crypt('a', gen_salt('bf')), 'CLIENT');
insert into user_model (email, fullname, password, role) values ('g@g', 'g g', crypt('a', gen_salt('bf')), 'CLIENT');