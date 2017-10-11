/* 부서 테이블 dml*/
--추가
insert into members values('user01','홍길동','password','서울시','010-7852-6100','교육');
insert into members values('user02','길라임','password','인천시 ','010-4612-7498',null);
insert into members values('user03','김주원','password','제주시','010-7170-9721',null);
insert into members values('user04','이민정','password','부산시','010-3060-8952','교육');
insert into members values('user05','김소망','password','서울시','010-8745-1740',null);
insert into members values('user06','데이브','password','서울시','010-7852-6110',null);
insert into members values('user07','김라임','password','인천시 ','010-4612-7418',null);
insert into members values('user08','홍주원','password','제주시','010-7170-9741',null);
insert into members values('user09','김길동','password','부산시','010-3060-8972',null);
insert into members values('user10','김민정','password','서울시','010-8745-1540',null);

--insert into dept values(?,?,?,?,?);

commit;

--특정 회원 상세 조회
select * from members where userid='user01';

--select * from dept userid=?;

--회원 전체 조회
select * from members;

--특정  아이디의 비밀번호와, 전화번호 변경
update members set userpw='0000',mobile='010-1234-1234'  where userid='user02';

--update dept set dname=?, loc=?  where deptno=?;

--특정 아이디 삭제
delete from members where userid='user03';

--delete from dept where deptno=?;

--비밀번호만 변경
update members set userpw='1234' where userid='user04';

--선호분야가 같은 회원 전체 조회
select * from members where preference like ('%교육%'); 
--dao메서드에서 반환타입 arraylist -->hashmap<integer,dept>

--회원정보 변경
update members set userpw='1234',addr ='',mobile='',preference='' where userid='user04';

--아이디 중복 여부 조회
select * from members where userid='user01';
select userid from members where userid='user01';



