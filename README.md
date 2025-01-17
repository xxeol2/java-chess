# java-chess

체스 미션 저장소

# 기능 목록

- 사용자
    - 이름으로 구분한다
        - 사용자의 이름은 중복될 수 없다.
    - 기존에 있는 사용자를 선택하거나 새로운 사용자를 입력받는다
    - 사용자의 이름은 중복될 수 없다
    - 사용자의 이름은 5자 이하이다
    - 자신의 게임 기록을 볼 수 있다
- 게임방
    - 한 사용자의 게임방은 이름으로 구분한다
        - 한 사용자의 게임방 이름은 중복될 수 없다.
    - 진행중인 게임방을 보여준다.
    - 진행중인 게임방을 선택해서 게임을 이어가거나 새로운 게임방을 생성한다.
    - 게임방 생성시 이름을 입력받는다.
- 게임
    - 턴을 가진다
        - 턴에 맞는 기물인지 확인한다
        - 한 움직임 이후 턴을 바꾼다
        - 백부터 시작한다.
    - 기물을 움직이게 한다.
    - 킹이 죽으면 게임이 종료된다.
- 체스판
    - 체스판 시작 배열로 초기화한다
    - 위치 별 기물을 가진다
    - 현재 턴에 일치하는 말인지 확인한다
    - 기물을 움직인다
        - 목표 위치에 같은 색상의 말이 있으면 움직일 수 없다
        - 다른 말을 뛰어넘을 수 없다
        <details>
            <summary>기물 이동 로직</summary>

        0. 출발 위치에 기물이 존재하는지 확인한다.
        1. 목표 위치에 기물이 존재하는지 확인한다.
        2. 기물이 목표 위치에 도달할 수 있는지 확인한다.
        3. 목표에 도달하는중 다른 기물이 있는지 확인한다.
        4. 위치를 바꾼다.
        </details>
    - 특수 룰
        - [ ] 폰이 상대진영 끝까지 도달하면 퀸으로 교체된다
        - [ ] 킹은 다른 말의 공격 가능 경로로 이동할 수 없다
- 기물
    - 폰, 비숍, 나이트, 룩, 퀸, 킹이 있다.
        - 하단 기물 클래스다이어그램 참조
    - 이동 및 공격 가능한 수인지 판단한다
    - 색을 구분한다
    - `Touched` 상태로 만든다.
- 기물별 특성
    - 폰
        - 처음에는 1칸 혹은 2칸 앞으로 움직일 수 있다.
        - 첫 수 이후 1칸 앞으로 움직일 수 있다.
        - 공격은 앞대각선으로 할 수 있다.
            - 앞 방향으로는 공격할 수 없다
        - 흑은 아래방향, 백은 윗방향으로 움직인다.
    - 비숍
        - 대각선 무한으로 움직일 수 있다.
    - 나이트
        - 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다.
    - 룩
        - 가로/세로 무한으로 움직일 수 있다.
    - 퀸
        - 가로/세로/대각선 무한으로 움직일 수 있다.
    - 킹
        - 가로/세로/대각선 한 칸 움직일 수 있다.
- 위치
    - file,rank 를 가진다
    - 이동할 수 있다
- 수
    - 시작위치, 도착위치로 수를 만들 수 있다.
    - 움직임이 없는 수는 만들 수 없다.
    - 판단한다.
        - 대각선인지
        - 수평/수직선인지
    - 경로를 탐색한다
    - 변화량을 구할 수 있다
        - 파일 변화량
        - 랭크 변화량
- file
    - a~h
    - 좌우로 이동 할 수 있다
        - 범위를 벗어난 이동은 예외를 던진다
- rank
    - 1~8
    - 위 아래로 이동 할 수 있다
        - 범위를 벗어난 이동은 예외를 던진다
- 점수
    - 한 진영의 점수를 계산한다.
    - 기물별 점수
        - queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
        - pawn의 기본 점수는 1점이다.
            - 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
        - king은 점수가 없다.
    - 두 진영의 점수 비교 후 승리진영을 반환한다
- 입력
    - 입력시 현재 턴을 알려준다

### 기물 클래스 다이어그램

```mermaid
classDiagram
    Piece <-- Pawn
    Piece <-- StrategyPiece
    Piece: +Color color
    Piece: +PieceType type
    Piece: +canMove() {abstract} boolean 
    Pawn: +canMove() boolean
    StrategyPiece: +canMove() boolean
    
    class PieceType
    PieceType: -double score
    PieceType: -MoveStrategy moveStrategy
    
    class MoveStrategy
    MoveStrategy: +canMove() boolean
    
    <<abstract>> Piece
    <<enum>> PieceType
    <<interface>> MoveStrategy
```

### 테이블 DDL

```sql
CREATE TABLE User
(
    id   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE Room
(
    id      INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT         NOT NULL,
    name    VARCHAR(10) NOT NULL,
    winner  VARCHAR(10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE Move
(
    id         INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    room_id    INT         NOT NULL,
    source     VARCHAR(10) NOT NULL,
    target     VARCHAR(10) NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES room (id),
    INDEX (created_at)
)

```

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
