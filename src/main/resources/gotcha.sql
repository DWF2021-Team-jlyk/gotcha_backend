-- gs_ws insert
insert into gc_ws values(1,'a');
insert into gc_ws values(2,'b');
insert into gc_ws values(3,'c');
insert into gc_ws values(4,'d');
insert into gc_ws values(5,'e');
insert into gc_ws values(6,'f');
insert into gc_ws values(7,'g');

-- gs_list insert
insert into gc_list values(1,'list1',1);
insert into gc_list values(2,'list2',1);
insert into gc_list values(3,'list3',1);

insert into gc_list values(4,'list1',3);
insert into gc_list values(5,'list2',3);
insert into gc_list values(6,'list3',3);

commit;

-- gs_card insert
insert into gc_card values(1,'card1','something',sysdate+1,1,1,sysdate,0);
insert into gc_card values(2,'card2','something',sysdate+1,2,1,sysdate,0);
insert into gc_card values(3,'card3','something',sysdate+1,3,1,sysdate,0);

insert into gc_card values(4,'card4','something',sysdate+1,4,3,sysdate,0);
insert into gc_card values(5,'card5','something',sysdate+1,5,3,sysdate,0);
insert into gc_card values(6,'card6','something',sysdate+1,6,3,sysdate,0);

--gs_card_todo insert
insert into gc_card_todo values(1,'todo1',1,sysdate,sysdate+1,0);
insert into gc_card_todo values(2,'todo2',2,sysdate,sysdate+1,0);
insert into gc_card_todo values(3,'todo3',3,sysdate,sysdate+1,0);
insert into gc_card_todo values(4,'todo4',4,sysdate,sysdate+1,0);
insert into gc_card_todo values(5,'todo5',5,sysdate,sysdate+1,0);
insert into gc_card_todo values(6,'todo6',6,sysdate,sysdate+1,0);

commit;

-- gc_board insert
alter table gc_board rename column board_writer to user_id;
alter table gc_board modify (user_id varchar2(50));
delete  gc_board;

insert into gc_board values(1,'board1','user01@naver.com','something',sysdate, null, 1);
insert into gc_board values(2,'board2','user02@naver.com','something',sysdate, null, 1);
insert into gc_board values(3,'board3','user03@naver.com','something',sysdate, null, 1);
insert into gc_board values(4,'board4','user04@naver.com','something',sysdate, null, 1);

insert into gc_board values(5,'board5','user05@naver.com','something',sysdate, null, 3);
insert into gc_board values(6,'board6','user06@naver.com','something',sysdate, null, 3);
insert into gc_board values(7,'board7','user07@naver.com','something',sysdate, null, 3);
insert into gc_board values(8,'board8','user08@naver.com','something',sysdate, null, 3);

commit;
-- gc_noti insert
alter table gc_noti modify(noti_type_id int);
insert into gc_noti values(1,1,'b',1,'something',sysdate);
insert into gc_noti values(1,2,'c',1,'something',sysdate);
insert into gc_noti values(1,3,'t',1,'something',sysdate);

insert into gc_noti values(3,4,'b',5,'something',sysdate);
insert into gc_noti values(3,5,'c',4,'something',sysdate);
insert into gc_noti values(3,6,'t',4,'something',sysdate);

--gc_noti_user insert
insert into gc_noti_user values(1,'user01@naver.com');
insert into gc_noti_user values(2,'user01@naver.com');
insert into gc_noti_user values(3,'user01@naver.com');
insert into gc_noti_user values(4,'user06@naver.com');
insert into gc_noti_user values(5,'user06@naver.com');
insert into gc_noti_user values(6,'user06@naver.com');

commit;

-- gc_fav
insert into gc_fav values(1,'user01@naver.com');
insert into gc_fav values(1,'user02@naver.com');
insert into gc_fav values(1,'user03@naver.com');
insert into gc_fav values(1,'user04@naver.com');

insert into gc_fav values(3,'user05@naver.com');
insert into gc_fav values(3,'user06@naver.com');
insert into gc_fav values(3,'user07@naver.com');
insert into gc_fav values(3,'user08@naver.com');