create table student(
    id integer auto_increment primary key ,
    name varchar(200),
    age integer,
    gender varchar(200),
    create_time datetime,
    creator varchar(200),
    modify_time datetime,
    modifier varchar(200)
);

create table student_nickname(
    student_id integer,
    nickname varchar(200)
);

create table student_family(
    student_id integer,
    name varchar(200),
    age integer,
    relation varchar(200)
);

create table dog(
    id integer auto_increment primary key ,
    name varchar(200),
    student_id integer
)