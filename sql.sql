

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

update user
set Password = '{noop}Dla13579!!'
where Row_Id = 1;

insert into user(User_Id, User_Nm, Password, User_Phone_Num, User_Email, Birth_Dt, Created_Dt, Updated_Dt, Last_Connect_Dt) value("jh0508","임준형","Dla13579!!","01094537706", "limjun920508@naver.com", "920508",now(),now(),now());
insert into user(User_Id, User_Nm, Password, User_Phone_Num, User_Email, Birth_Dt, Created_Dt, Updated_Dt, Last_Connect_Dt) value("js1226","임준성","{noop}Dla13579!!","01053377706", "sung@naver.com", "931226",now(),now(),now());

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
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","14번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","15번째게시물","힘들어요 피곤해요",now(),now());
insert into board(User_Id, Title, Content, Created_Dt, Updated_Dt) value("jh0508","15번째게시물","* 힘들어요 피곤해요 ** ㅋㅋㅋㅋ ",now(),now());

select *
from board;

SELECT * FROM board ORDER BY created_dt desc limit 0, 5;

drop table coinKey;
      

CREATE TABLE coinKey
(
    `User_Id`      VARCHAR(50)      NOT NULL, 
    `Access_Key`       VARCHAR(300)      NOT NULL, 
    `Secret_Key`     VARCHAR(300)    NOT NULL
);

insert into coinKey(User_Id, Access_Key, Secret_Key) value(1,"qehbMeAi05fyx3RxhNpcIIh8cdV2pVzk17lvSR2Q","mhn5mmuWOdJz0Au8ObxJAwNYKj6bv0ILf8ypdCpu");

select *
from coinKey;

drop TABLE coinAllInfo;

CREATE TABLE coinAllInfo
(
	`Coin_Id`    INT              NOT NULL    AUTO_INCREMENT COMMENT '코인ID', 
    `User_Id`    INT       NOT NULL    COMMENT 'User_Id', 
    `Coin_Nm`    VARCHAR(50)      NOT NULL    COMMENT '이름', 
    `Type` 	     VARCHAR(50)      NOT NULL    default 'N' COMMENT '타입', 
    `Buy_Cnt`    INT      default 0,
    `Sell_Cnt`   INT      default 0,
    `Use_Yn`     VARCHAR(10)      NOT NULL    COMMENT '사용유무', 
    `Price`      INT      default 0           COMMENT '현재가격',
    `Fix`        INT      default 0           COMMENT '구매시점가격',
    `Lock_Top`   INT      default 0           COMMENT '구매제한가격상단',
    `Lock_Bottom`       INT      default 0    COMMENT '구매제한가격하단',
    `Use_Krw`    INT      default 0           COMMENT '사용금액',
    `Back_Krw`   INT      default 0           COMMENT '회수금액',
    `Re_Get`     INT      default 0           COMMENT '순이익',
    `Created_dt`  DATETIME         default now(),
    `Updated_dt`  DATETIME         default now(),
    PRIMARY KEY (Coin_Id)
);

select *
from coinAllInfo;

insert into coinAllInfo(Coin_Nm, User_Id, Type, Use_Yn) value("KRW-BTC",1, "TEST", "Y");
insert into coinAllInfo(Coin_Nm, User_Id, Type, Use_Yn) value("KRW-ETH",1, "TEST", "Y");

