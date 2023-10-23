create table if not exists clients
(
    id bigserial primary key,
    passport_number varchar(7) not null unique,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);


create table if not exists payment_systems
(
    id bigserial primary key,
    system_name varchar(50) unique not null,
    percent double precision default 0
);

create table if not exists cards
(
    id bigserial primary key,
    phone_number varchar(13) not null,
    card_number varchar(16) not null,
    issue_date date not null,
    expiration_date date not null,
    balance double precision default 0,
    pin_code varchar(4) not null,
    payment_system_id bigint references payment_systems(id) on update cascade on delete cascade,
    client_id bigint references clients(id) on update cascade on delete cascade,
    unique (card_number, payment_system_id),
    unique (phone_number, payment_system_id)
);