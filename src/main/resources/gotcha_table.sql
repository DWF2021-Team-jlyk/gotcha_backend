/*

>> 명명 규칙

- gotcha --> gc
- workspace --> ws
- members --> mem
- activity --> act
- regist --> reg
- modigy --> mod
- islog / isdone 은 띄우지 않고 쓸게용
- 각 테이블에서 참조관계에 해당하는 열은 같은 이름을 쓰기로 했습니다 (테이블이름.열 로 구분!)
- 각 테이블 명은 gc_단수형
- primary key 는 'gc 뒤의 나머지 테이블명_pk'

*/


-- gotcha User
drop table gc_user;

create table gc_user(
    user_id varchar2(50) not null,
    user_pwd varchar(70) not null,
    user_enabled char(1) not null,
    user_last_login date not null,
    user_name varchar2(50),
    constraint user_pk primary key(user_id)
    );
    

-- Sequence
--drop sequence seq ;
--create sequence seq
--    start with 0
--    increment by 1
--    minvalue 0
--    nocycle
--    nocache;
    

-- gotcha Workspace
drop table gc_ws;

create table gc_ws(
    ws_id int not null,
    ws_name varchar2(50) not null,
    constraint ws_pk primary key(ws_id)
    );


-- gotcha List
drop table gc_list;

create table gc_list(
    list_id int not null,
    list_name varchar2(50) not null,
    ws_id int not null,
    constraint list_pk primary key(list_id)
    );


-- gotcha Card
drop table gc_card;

create table gc_card(
    card_id int not null,
    card_name varchar2(50) not null,
    card_desc clob,
    card_end_date date,
    list_id int not null,
    ws_id int not null,
    card_start_date date,
    card_isdone char(1),
    constraint card_pk primary key(card_id)
    );
    

-- gotcha Card Members
drop table gc_card_mem;

create table gc_card_mem(
    card_id int not null,
    user_id varchar2(50) not null
    );


-- gotcha Card Activity
drop table gc_card_act;

create table gc_card_act(
    card_id int not null,
    act_id int not null,
    user_id varchar(50) not null,
    act_desc clob not null,
    created_date date not null,
    isLog char(1) not null,
    constraint card_act_pk primary key(act_id)
    );
    
    
-- gotcha Card To do
drop table gc_card_todo;

create table gc_card_todo(
    todo_id int not null,
    todo_name varchar2(50) not null,
    card_id int not null,
    todo_start_date date,
    todo_end_date date,
    todo_isdone char(1),
    constraint card_todo_pk primary key(todo_id)
    );
    
    
-- gotcha Card Files
drop table gc_card_file;

create table gc_card_file(
    card_id int not null,
    file_id varchar(100) not null,
    file_name varchar(255) not null,
    constraint card_file_pk primary key(file_id)
    );
    

-- gotcha Board
drop table gc_board;

create table gc_board(
    board_id int not null,
    board_title varchar(50) not null,
    user_id varchar(50) not null,
    board_content clob not null,
    board_reg_time date not null,
    board_mod_time date,
    ws_id int not null,
    constraint board_pk primary key(board_id)
    );
    
    
-- gotcha Notification
drop table gc_noti;

create table gc_noti(
    ws_id int not null,
    noti_id int not null,
    noti_type char(1) not null,
    noti_type_id varchar2(30) not null,
    noti_desc clob not null,
    noti_time date not null,
    noti_checked char(1) not null,
    constraint noti_pk primary key(noti_id)
    );

    
-- gotcha Notification-User
drop table gc_noti_user;
create table gc_noti_user(
    noti_id int not null,
    user_id varchar(50) not null
    );
 
     
-- gotcha Role
drop table gc_role;

create table gc_role(
    role_id int not null,
    role_type varchar2(50) not null,
    constraint role_pk primary key(role_id)
    );
    
    
-- gotcha USer-Role
drop table gc_user_role;

create table gc_user_role(
    ws_id int not null,
    user_id varchar2(50) not null,
    role_id int not null,
    is_fav char(1) not null,
    constraint role_user_pk primary key(ws_id, user_id)
);

-- gotcha Favorite
drop table gc_fav;
create table gc_fav(
    ws_id int not null,
    user_id varchar2(50) not null
);