/* �μ� ���̺� dml*/
--�߰�
insert into members values('user01','ȫ�浿','password','�����','010-7852-6100','����');
insert into members values('user02','�����','password','��õ�� ','010-4612-7498',null);
insert into members values('user03','���ֿ�','password','���ֽ�','010-7170-9721',null);
insert into members values('user04','�̹���','password','�λ��','010-3060-8952','����');
insert into members values('user05','��Ҹ�','password','�����','010-8745-1740',null);
insert into members values('user06','���̺�','password','�����','010-7852-6110',null);
insert into members values('user07','�����','password','��õ�� ','010-4612-7418',null);
insert into members values('user08','ȫ�ֿ�','password','���ֽ�','010-7170-9741',null);
insert into members values('user09','��浿','password','�λ��','010-3060-8972',null);
insert into members values('user10','�����','password','�����','010-8745-1540',null);

--insert into dept values(?,?,?,?,?);

commit;

--Ư�� ȸ�� �� ��ȸ
select * from members where userid='user01';

--select * from dept userid=?;

--ȸ�� ��ü ��ȸ
select * from members;

--Ư��  ���̵��� ��й�ȣ��, ��ȭ��ȣ ����
update members set userpw='0000',mobile='010-1234-1234'  where userid='user02';

--update dept set dname=?, loc=?  where deptno=?;

--Ư�� ���̵� ����
delete from members where userid='user03';

--delete from dept where deptno=?;

--��й�ȣ�� ����
update members set userpw='1234' where userid='user04';

--��ȣ�о߰� ���� ȸ�� ��ü ��ȸ
select * from members where preference like ('%����%'); 
--dao�޼��忡�� ��ȯŸ�� arraylist -->hashmap<integer,dept>

--ȸ������ ����
update members set userpw='1234',addr ='',mobile='',preference='' where userid='user04';

--���̵� �ߺ� ���� ��ȸ
select * from members where userid='user01';
select userid from members where userid='user01';



