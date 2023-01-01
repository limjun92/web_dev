

use world;

drop table user;

CREATE TABLE user (
	 Row_Id           	int not null auto_increment,
     User_Id      		varchar(20),
     User_Nickname		varchar(20),
     User_Nm      		varchar(20),
	 Password     		varchar(20),
     User_Phone_Num 	varchar(20),
     User_Email 		varchar(50),
     Board_Num			int default 0,
     Birth_Dt 			varchar(20),
     Created_Dt 		datetime,
     Updated_Dt 		datetime,
     Last_Connect_Dt 	datetime,
     primary key(Row_Id)
);

insert into user(User_Id, User_Nm, Password, User_Phone_Num, User_Email, Birth_Dt, Created_Dt, Updated_Dt, Last_Connect_Dt) value("jh0508","임준형","Dla13579!!","01094537706", "limjun920508@naver.com", "920508",now(),now(),now());

select *
from user;

rollback;

CREATE TABLE board
(
    `Board_id`    INT              NOT NULL    AUTO_INCREMENT COMMENT '게시글ID', 
    `User_Id`      VARCHAR(50)      NOT NULL    COMMENT '작성자', 
    `Title`       VARCHAR(50)      NOT NULL    COMMENT '제목', 
    `Content`     VARCHAR(1000)    NOT NULL    COMMENT '내용', 
    `Created_dt`  DATETIME         NOT NULL    COMMENT '등록일자', 
    `Updated_dt`  DATETIME         NULL        COMMENT '수정일자', 
    PRIMARY KEY (board_id)
);

insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","첫게시물","안녕하세요 반가워요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","두번째게시물","싫어요 안해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","세번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","4번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","5번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","6번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","7번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","8번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","9번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","10번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","11번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","12번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","13번째게시물","힘들어요 피곤해요",now(),now());

select *
from board;

SELECT * FROM board ORDER BY created_dt desc limit 0, 5;

drop table board;

SELECT user.User_Id,
	  board.Title,
	  board.User_Id,
	  board.Content,
	  board.Created_Dt,
      (select count(*) from board) as a
FROM board JOIN user ON board.User_Id = user.User_Id
WHERE board.User_Id = "jh0508" 
ORDER BY board.Created_Dt desc 
limit 0, 5;