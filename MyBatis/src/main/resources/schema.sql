drop table if exists blog;

create table blog (
  id integer identity primary key,
  title varchar(100) not null,
  content varchar(2000) not null,
  postedTime datetime not null
);
