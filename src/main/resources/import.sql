insert into customer(id, name) values (1, 'ivan');
insert into customer(id, name) values (2, 'john');

insert into address(id, customer_id, address) values (1, 1, 'Moscow, Red square');
insert into address(id, customer_id, address) values (2, 1, 'London, Downing str');
insert into address(id, customer_id, address) values (3, 1, 'North Pole');
insert into address(id, customer_id, address) values (4, 2, 'Los Angeles');
insert into address(id, customer_id, address) values (5, 2, 'Miami');

insert into invoice(id, invoice_type, amount, address_id) values (1, 0, 20.99, 1);
insert into invoice(id, invoice_type, amount, address_id) values (2, 0, 14.95, 2);
insert into invoice(id, invoice_type, amount, address_id) values (3, 1, 99.90, 3);