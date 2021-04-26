# SlidingPuzzle-Android

## 프로젝트 소개

- 이 프로젝트는 '모바일 앱 프로그래밍 실습' 수업의 첫 번째 과제입니다.
- 주어진 정사각형 이미지를 이용해서 슬라이딩 퍼즐을 구현하는 과제입니다.
  - 퍼즐은 3×3 혹은  4×4 형태를 선택할 수 있습니다.
  - SHUFFLE 버튼을 눌러 게임을 시작할 수 있습니다.
- 해당 프로젝트는 안드로이드 스튜디오에서 작성하였고, Minimum sdk 는 API 16입니다.

***

## 실행화면 보기

- __Pixel 3a API 29__ 기기에서 테스트되었습니다.



### 1. Select 3×3 or 4×4

![image-20210426154350653](C:\Users\PSY\AppData\Roaming\Typora\typora-user-images\image-20210426154350653.png)

- 3×3 혹은  4×4 버튼을 클릭하여 선택할 수 있다.



### 2. Click SHUFFLE button

![image-20210426155948551](C:\Users\PSY\AppData\Roaming\Typora\typora-user-images\image-20210426155948551.png)

- SHUFFLE 버튼을 클릭하면 퍼즐이 무작위로 섞인다.



### 3. Move puzzle

![1](C:\Users\PSY\Desktop\1.gif)

- 클릭한 퍼즐 조각 상,하,좌,우에 빈 칸이 있을 경우 이동할 수 있는 퍼즐이다.
- 따라서 이동할 수 있는 퍼즐을 클릭하면 빈 칸으로 이동한다.
- 움직일 곳이 없는 퍼즐을 클릭하면 아무 일도 일어나지 않는다.



### 4. Complete Puzzle

![image-20210426160513705](C:\Users\PSY\AppData\Roaming\Typora\typora-user-images\image-20210426160513705.png)



- 섞인 퍼즐을 적절히 이동하여 정답을 맞추면 Toast 메시지를 볼 수 있다.



***



## Check

- 현재 퍼즐 SHUFFLE은 아무 기준없이 무작위로 섞인다.
- 따라서 정답의 형태로 만들 수 없는 퍼즐이 생성되기도 한다.
- 이 부분을 개선할 예정이다.