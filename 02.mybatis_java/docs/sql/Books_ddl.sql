create table books(
bname varchar2(40) not null,
author varchar2(40) not null,
publisher varchar2(40) not null , 
publishdate varchar2(10) not null,
isbn varchar2(13) primary key,
loc varchar2(20) not null
);