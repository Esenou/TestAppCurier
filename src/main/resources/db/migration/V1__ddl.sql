create sequence orders_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;
create table orders (id int8 not null, create_date timestamp not null, orders_status varchar(255), product varchar(255) not null, target_location varchar(255) not null, courier_id int8, users_id int8, primary key (id));
create table users (id int8 not null, create_date timestamp, password varchar(255) not null, roles varchar(255), user_status varchar(255), username varchar(255) not null, primary key (id));
alter table if exists users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table if exists orders add constraint FKkda753b42924l6hhnyxt75n6c foreign key (courier_id) references users;
alter table if exists orders add constraint FKe6k45xxoin4fylnwg2jkehwjf foreign key (users_id) references users;
