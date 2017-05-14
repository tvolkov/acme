insert into customer(id, name) values (21, 'ivan');
insert into customer(id, name) values (22, 'john');

insert into address(id, customer_id, address) values (11, 21, 'Moscow, Red square');
insert into address(id, customer_id, address) values (12, 21, 'London, Downing str');
insert into address(id, customer_id, address) values (13, 21, 'North Pole');
insert into address(id, customer_id, address) values (14, 22, 'Los Angeles');
insert into address(id, customer_id, address) values (15, 22, 'Miami');

insert into invoice(id, invoice_type, amount, address_id, month) values (31, 'a', 20.99, 11, '2017-01');
insert into invoice(id, invoice_type, amount, address_id, month) values (32, 'a', 14.95, 12, '2017-02');
insert into invoice(id, invoice_type, amount, address_id, month) values (33, 's', 99.90, 13, '2017-03');
insert into invoice(id, invoice_type, amount, address_id, month) values (34, 's', 99.90, 14, '2017-04');
insert into invoice(id, invoice_type, amount, address_id, month) values (35, 's', 99.90, 14, '2017-05');
insert into invoice(id, invoice_type, amount, address_id, month) values (36, 'a', 99.90, 15, '2017-06');
insert into invoice(id, invoice_type, amount, address_id, month) values (37, 's', 99.90, 15, '2017-06');
insert into invoice(id, invoice_type, amount, address_id, month) values (38, 's', 99.90, 15, '2017-07');
insert into invoice(id, invoice_type, amount, address_id, month) values (39, 'a', 99.90, 15, '2017-07');
insert into invoice(id, invoice_type, amount, address_id, month) values (310, 's', 99.90, 15, '2017-07');
insert into invoice(id, invoice_type, amount, address_id, month) values (311, 'a', 99.90, 15, '2017-07');
insert into invoice(id, invoice_type, amount, address_id, month) values (312, 's', 99.90, 15, '2017-07');