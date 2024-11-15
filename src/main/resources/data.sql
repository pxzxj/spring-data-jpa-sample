insert into student(name, age, gender, create_time, creator) values ('Tom', 11, 'BOY', '2024-11-15 00:00:00', 'admin');
insert into student_nickname(student_id, nickname) values (1, 'tommy');
insert into student_nickname(student_id, nickname) values (1, 'sweetie');
insert into student_nickname(student_id, nickname) values (1, 'cat');
insert into student_family(student_id, name, age, relation) values (1, 'Jerry', 2, 'Mom');
insert into student_family(student_id, name, age, relation) values (1, 'Paul', 3, 'Dad');
insert into dog(name, student_id) values ('small yellow', 1);
insert into dog(name, student_id) values ('small black', 1);
insert into dog(name, student_id) values ('small white', 1);