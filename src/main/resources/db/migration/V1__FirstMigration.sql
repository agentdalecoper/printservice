create table device
(
	id bigserial not null
		constraint device_pkey
			primary key,
	name varchar(255)
		constraint uk_oesoxj95nuiic4anb0i5fh9b1
			unique
);

alter table device owner to postgres;

create table "user"
(
	id bigserial not null
		constraint user_pkey
			primary key,
	name varchar(255)
		constraint uk_gj2fy3dcix7ph7k8684gka40c
			unique
);

alter table "user" owner to postgres;

create table job
(
	id bigserial not null
		constraint job_pkey
			primary key,
	amount integer not null,
	time timestamp,
	type integer,
	device_id bigint
		constraint fkb9ygn20vju9eu9pmrjxo3txwq
			references device,
	user_id bigint
		constraint fkihd6m3auwpenduntl3e1opcoq
			references "user"
);

alter table job owner to postgres;

