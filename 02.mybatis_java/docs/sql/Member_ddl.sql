/* 부서 테이블 ddl*/


drop table dept cascade constraints purge;

create table members(
userid varchar2(20) primary key,
username varchar2(20) not null,
userpw varchar2(40) not null , 
address varchar2(60) not null,
mobile varchar2(13) not null,
preference varchar2(40)  
);
