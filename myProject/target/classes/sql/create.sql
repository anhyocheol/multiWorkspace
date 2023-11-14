create table member (
 id varchar2(20) constraint member_id_pk primary key
, passwd varchar2(20) constraint member_passwd_nn not null
, name varchar2(15) constraint member_name_nn not null
, regdate date default sysdate
, grade number(1) default 3
, constraint member_grade_ck check (grade in (1, 2, 3))
);

insert into member
values ('longlee@daum.net', 6789, '이성구', default, default);

commit;

select * from member;