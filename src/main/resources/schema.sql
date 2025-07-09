create table if not exists Employee (
    id identity,
    email varchar(25) not null,
    username varchar(25) not null,
    password varchar(255) not null,
    name varchar(10) not null
);

create table if not exists Producto(
    id identity,
    nombre varchar(50) not null,
    precio numeric not null
);

create table if not exists Roles (
    id identity,
    name varchar(50) not null
);

create table if not exists Employee_Roles (
    employee_id bigint not null,
    role_id bigint not null
);

alter table Employee_Roles
    add foreign key (employee_id) references Employee(id);
alter table Employee_Roles
    add foreign key (role_id) references Roles(id);
