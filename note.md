
# 2022-12-30_1
* 게시판 Detail 틀생성
* paging 처리

# 2022-12-30_2
##  resultMap사용법

* 커스텀이 가능하다.
* 다른 mapper를 참조해서 가져와서 이어 붙이는 느낌으로 사용한다.

### 순서

1. 참조할 mapper가 mapper.java에 작성되어 있는지 확인한다.
2. property = User가 가지고 있는 객체변수명 board
3. column = User가 BoardMapper.getSelectBoardList 로 넘겨줄 파라미터 변수 ex)user_id 변수명 


# 할일

* 게시판 접근시 에서 내가 작성한 글만 보일수 있도록 수정