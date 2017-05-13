insert into customer(id, name) values (21, 'ivan');
insert into customer(id, name) values (22, 'john');

insert into address(id, customer_id, address) values (11, 21, 'Moscow, Red square');
insert into address(id, customer_id, address) values (12, 21, 'London, Downing str');
insert into address(id, customer_id, address) values (13, 21, 'North Pole');
insert into address(id, customer_id, address) values (14, 22, 'Los Angeles');
insert into address(id, customer_id, address) values (15, 22, 'Miami');

insert into invoice(id, invoice_type, amount, address_id, month) values (31, 0, 20.99, 11, 1);
insert into invoice(id, invoice_type, amount, address_id, month) values (32, 0, 14.95, 12, 2);
insert into invoice(id, invoice_type, amount, address_id, month) values (33, 1, 99.90, 13, 3);
insert into invoice(id, invoice_type, amount, address_id, month) values (34, 1, 99.90, 14, 4);
insert into invoice(id, invoice_type, amount, address_id, month) values (35, 1, 99.90, 14, 5);
insert into invoice(id, invoice_type, amount, address_id, month) values (36, 0, 99.90, 15, 6);
insert into invoice(id, invoice_type, amount, address_id, month) values (37, 1, 99.90, 15, 6);
insert into invoice(id, invoice_type, amount, address_id, month) values (38, 1, 99.90, 15, 7);
insert into invoice(id, invoice_type, amount, address_id, month) values (39, 0, 99.90, 15, 7);
insert into invoice(id, invoice_type, amount, address_id, month) values (310, 1, 99.90, 15, 7);
insert into invoice(id, invoice_type, amount, address_id, month) values (311, 0, 99.90, 15, 7);
insert into invoice(id, invoice_type, amount, address_id, month) values (312, 1, 99.90, 15, 7);