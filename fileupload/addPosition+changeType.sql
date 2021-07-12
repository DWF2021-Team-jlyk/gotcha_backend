  -- gc_list position 추가
  select * from gc_list;
  alter table gc_list add(position int);
  update gc_list set position = 0 where position is null;
  
  -- gc_card position 추가
  select * from gc_card;
  alter table gc_card add(position int);
  update gc_card set position = 0 where position is null;
  
  -- gc_card card_desc 타입변경
  alter table gc_card drop column card_desc;
  select * from gc_card;
  alter table gc_card add (card_desc varchar2(4000));
  update gc_card set card_desc = 'something' where card_desc is null;
  
-- gc_card_act act_desc 타입변경
alter table gc_card_act drop column act_desc;
alter table gc_card_act add(act_desc varchar2(4000));
update gc_card_act set act_desc = 'something' where act_desc is null;

-- gc_noti noti_desc 타입변경
alter table gc_noti drop column noti_desc;
alter table gc_noti add(noti_desc varchar2(4000));
update gc_noti set noti_desc = 'something' where noti_desc is null;

commit;
  