create table if not exists clients
(
    id bigserial primary key,
    passport_number varchar not null unique,
    first_name varchar not null,
    last_name varchar not null,
    phone_number varchar
);


create table if not exists payment_systems
(
    id bigserial primary key,
    system_name varchar unique not null,
    percent double precision default 0
);

create table if not exists cards
(
    id bigserial primary key,
    card_number varchar not null,
    expiration_date date,
    balance double precision default 0,
    cvv varchar(3),
    payment_system_id bigint references payment_systems(id) on update cascade on delete cascade,
    client_id bigint references clients(id) on update cascade on delete cascade,
    UNIQUE (card_number, payment_system_id)
);