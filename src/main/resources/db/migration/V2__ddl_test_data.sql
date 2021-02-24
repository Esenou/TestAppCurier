
INSERT INTO  users(id,create_date,password,roles,user_status,username)
VALUES ((SELECT nextval('user_seq')),'2021-02-24 01:28:38','$2a$04$ZBya/UE6PBgGcML3nC1Jn.EfCLESZOk8.89lPdHgdiBRDM.MTxyRS','Admin','ACTIVE','Admin');
/*
UserName:Admin
Password:123*/

INSERT INTO  users(id,create_date,password,roles,user_status,username)
VALUES ((SELECT nextval('user_seq')),'2021-02-24 01:28:38','$2a$04$ZBya/UE6PBgGcML3nC1Jn.EfCLESZOk8.89lPdHgdiBRDM.MTxyRS','User','ACTIVE','Esen');
/*
UserName:Esen
Password:123*/

INSERT INTO  users(id,create_date,password,roles,user_status,username)
VALUES ((SELECT nextval('user_seq')),'2021-02-24 01:28:38','$2a$04$ZBya/UE6PBgGcML3nC1Jn.EfCLESZOk8.89lPdHgdiBRDM.MTxyRS','User','ACTIVE','Aman');
/*
UserName:Aman
Password:123*/


INSERT INTO  orders(id,create_date,orders_status,product,target_location,courier_id,users_id)
VALUES ((SELECT nextval('orders_seq')),'2021-02-24 01:28:38','AWAITING','Мячик','12-Мкр №12',2,2);

INSERT INTO  orders(id,create_date,orders_status,product,target_location,courier_id,users_id)
VALUES ((SELECT nextval('orders_seq')),'2021-02-24 01:28:38','AWAITING','Фудболка','12-Мкр №12',2,2);

INSERT INTO  orders(id,create_date,orders_status,product,target_location,courier_id,users_id)
VALUES ((SELECT nextval('orders_seq')),'2021-02-24 01:28:38','AWAITING','Шапка','12-Мкр №12',2,2);

INSERT INTO  orders(id,create_date,orders_status,product,target_location,courier_id,users_id)
VALUES ((SELECT nextval('orders_seq')),'2021-02-24 01:28:38','AWAITING','Сумка','12-Мкр №12',2,2);
