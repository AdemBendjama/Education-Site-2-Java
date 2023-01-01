Create database TQL_APP_BD ;
use TQL_APP_BD;

create table users(
user_email varchar(40) not null primary key,
user_name varchar(40) not null,
user_password varchar(40) not null,
user_rank varchar(40) not null	
);

create table admins(
admin_email varchar(40) not null primary key,
admin_name varchar(40) not null,
admin_password varchar(40) not null
);


