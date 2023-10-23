create table if not exists log_entries
(
    id bigserial primary key,
    client_passport_number varchar,
    card_number varchar(16),
    payment_system_name varchar,
    action varchar not null,
    date date not null,
    response varchar not null
);