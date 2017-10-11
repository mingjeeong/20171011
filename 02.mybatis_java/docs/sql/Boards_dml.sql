--추가
insert into boards values(1,'언어의 온도','이기주','말글터','2016.08.19','9791195522125','13,800','베스트셀러라서 읽어보고 싶음');

--전체 게시글 변경
update boards set bname='',author='',publisher='',publishdate='',isbn='',price='',reason='' where index=1;

--삭제
delete from boards where index=1;

--내 아이디 조회
select userid from boards where username='';

--

