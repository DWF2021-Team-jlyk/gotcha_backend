-- ws_seq
drop sequence ws_seq;

create sequence ws_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- gc_ws (workspace)
delete gc_ws;

insert into gc_ws values(ws_seq.nextval,'workspace1');
insert into gc_ws values(ws_seq.nextval,'workspace2');


select * from gc_ws;

-- gc_user
delete gc_user_role;

insert into gc_user_role values(1,'user01@naver.com',1,1);
insert into gc_user_role values(1,'user02@naver.com',2,0);
insert into gc_user_role values(1,'user03@naver.com',2,1);
insert into gc_user_role values(1,'user04@naver.com',2,0);
insert into gc_user_role values(1,'user05@naver.com',2,1);
insert into gc_user_role values(1,'user06@naver.com',2,0);
insert into gc_user_role values(1,'user07@naver.com',2,1);
insert into gc_user_role values(1,'user08@naver.com',2,0);
insert into gc_user_role values(1,'user09@naver.com',2,1);
insert into gc_user_role values(1,'user10@naver.com',2,0);

insert into gc_user_role values(2,'user11@naver.com',1,1);
insert into gc_user_role values(2,'user12@naver.com',2,0);
insert into gc_user_role values(2,'user13@naver.com',2,1);
insert into gc_user_role values(2,'user14@naver.com',2,0);
insert into gc_user_role values(2,'user15@naver.com',2,1);
insert into gc_user_role values(2,'user16@naver.com',2,0);
insert into gc_user_role values(2,'user17@naver.com',2,1);
insert into gc_user_role values(2,'user18@naver.com',2,0);
insert into gc_user_role values(2,'user19@naver.com',2,1);
insert into gc_user_role values(2,'user20@naver.com',2,0);

select * from gc_user_role;

-- gc_user
insert into gc_user values('user09@naver.com',1,1,'user09',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user10@naver.com',1,1,'user10',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user11@naver.com',1,1,'user11',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user12@naver.com',1,1,'user12',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user13@naver.com',1,1,'user13',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user14@naver.com',1,1,'user14',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user15@naver.com',1,1,'user15',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user16@naver.com',1,1,'user16',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user17@naver.com',1,1,'user17',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user18@naver.com',1,1,'user18',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user19@naver.com',1,1,'user19',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_user values('user20@naver.com',1,1,'user20',to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'));


select * from gc_user;

commit;

-- list_seq
drop sequence list_seq;

create sequence list_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- gc_list
delete gc_list;

insert into gc_list values(list_seq.nextval,'list1',1);
insert into gc_list values(list_seq.nextval,'list2',1);

insert into gc_list values(list_seq.nextval,'list3',2);
insert into gc_list values(list_seq.nextval,'list4',2);

select * from gc_list;

commit;

-- card_seq
drop sequence card_seq;

create sequence card_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- gc_card
delete gc_card;

alter table gc_card drop column card_start_date;
alter table gc_card drop column card_end_date;

alter table gc_card add(card_start_date varchar2(30));
alter table gc_card add(card_end_date varchar2(30));

insert into gc_card values(card_seq.nextval, 'card1' ,'desc1',1,1,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card2' ,'desc2',1,1,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card3' ,'desc3',2,1,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card4' ,'desc4',2,1,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));

insert into gc_card values(card_seq.nextval, 'card5' ,'desc5',3,2,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card6' ,'desc6',3,2,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card7' ,'desc7',4,2,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));
insert into gc_card values(card_seq.nextval, 'card8' ,'desc8',4,2,0,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'),to_char(sysdate+1,'yyyy.mm.dd hh24:mi:ss'));

select * from gc_card;

commit;

-- gc_noti ( 아직 수정 안함)
update gc_noti set ws_id = 2 where ws_id = 3;

alter table gc_noti drop column noti_time;
alter table gc_noti add(noti_time varchar2(50));

update gc_noti set noti_time = to_char(sysdate,'yyyy.mm.dd hh24:mi:ss')where noti_time is null;

alter table gc_noti drop column noti_id;
alter table gc_noti add(noti_id int);

update gc_noti set noti_id = seq;

select * from gc_noti;

-- act_seq
drop sequence act_seq;

create sequence act_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- file_seq
drop sequence file_seq;

create sequence file_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- todo_seq
drop sequence todo_seq;

create sequence todo_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- baord_seq
drop sequence board_seq;

create sequence board_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;

-- noti_seq
drop sequence noti_seq;

create sequence noti_seq
increment by 1
start with 1
minvalue 1
nocycle
nocache;


