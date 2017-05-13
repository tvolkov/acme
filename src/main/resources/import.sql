insert into customer(id, name) values (21, 'ivan');
insert into customer(id, name) values (22, 'john');

insert into address(id, customer_id, address) values (11, 21, 'Moscow, Red square');
insert into address(id, customer_id, address) values (12, 21, 'London, Downing str');
insert into address(id, customer_id, address) values (13, 21, 'North Pole');
insert into address(id, customer_id, address) values (14, 22, 'Los Angeles');
insert into address(id, customer_id, address) values (15, 22, 'Miami');

insert into invoice(id, invoice_type, amount, address_id) values (31, 0, 20.99, 11);
insert into invoice(id, invoice_type, amount, address_id) values (32, 0, 14.95, 12);
insert into invoice(id, invoice_type, amount, address_id) values (33, 1, 99.90, 13);
insert into invoice(id, invoice_type, amount, address_id) values (34, 1, 99.90, 14);
insert into invoice(id, invoice_type, amount, address_id) values (35, 1, 99.90, 14);
insert into invoice(id, invoice_type, amount, address_id) values (36, 0, 99.90, 15);
insert into invoice(id, invoice_type, amount, address_id) values (37, 0, 99.90, 15);