--�߰�
insert into boards values(1,'����� �µ�','�̱���','������','2016.08.19','9791195522125','13,800','����Ʈ������ �о�� ����');

--��ü �Խñ� ����
update boards set bname='',author='',publisher='',publishdate='',isbn='',price='',reason='' where index=1;

--����
delete from boards where index=1;

--�� ���̵� ��ȸ
select userid from boards where username='';

--

