--게시판

--테이블생성
create table boards (
index number ,
username varchar2(40) ,
userid varchar2(40)
bname varchar2(30) not null,
author varchar2(30) not null,
publisher varchar2(30) not null,
publishdate varchar2(30) not null,
isbn varchar2(13) ,
price varchar2(30) not null,
reason varchar2(100) not null,
wdate date not null; 


);

--제약추가
alter table boards
add constraint pk_boards_index primary key(index);

alter table boards
add constraint u_boards_isbn unique(isbn);

alter table boards
add constraint fk_boards_username foreign key(username) reference members(username);

