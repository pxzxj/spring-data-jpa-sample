create table student(
    id integer auto_increment primary key ,
    name varchar(200),
    age integer,
    gender varchar(200)
);

create table student_family(
    student_id integer,
    name varchar(200),
    age integer,
    relation varchar(200)
);