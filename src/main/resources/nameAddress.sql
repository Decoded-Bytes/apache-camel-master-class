create table name_address
(
    id           bigint auto_increment
        primary key,
    name         varchar(255) null,
    house_number varchar(255) null,
    city         varchar(255) null,
    province     varchar(255) null,
    postal_code  varchar(255) null
);
