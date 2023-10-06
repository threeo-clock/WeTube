# 📱 YoutubeApp - WeTube

## 💻 프로젝트 소개

유튜브의 의미가 당신을 뜻하는 You 와 TV를 뜻하는 Tube를 결합하였다고 하여,
저희 조는 우리가 함께 만들어 나간다는 의미로 We에 Tube를 붙여 프로젝트 명을 WeTube 짓게 되었습니다.

## 🔧 프로젝트 상세

### 📆 개발기간

23.09.25 ~ 23.10.06

### 📽️ 시연 영상

![gif](https://github.com/threeo-clock/WeTube/assets/104261048/41f0882f-b980-4658-b088-efc8ad2b4a65)
[영상 보기](https://drive.google.com/file/d/1_EMsnRt-oCDpO8iqGT0xrkmSBn3A3DQy/view?usp=sharing)

### 🎨 와이어프레임

![image](https://github.com/threeo-clock/WeTube/assets/104261048/99fc92ac-37e7-4571-925f-987f0ec81e55)
[Figma 확인 하기](https://www.figma.com/file/Bqq2ezqK8q1Hc5hqcRf54A?embed_host=notion&kind=&node-id=0-1&t=nfICqaZLxRWyHHRU-0&type=whiteboard&viewer=1)

### 👥 개발자

<table>
  <tr>
      <td align="center">🌟🧑‍💻🌟</td>
      <td align="center">‍👩‍💻</td>
      <td align="center">‍👩‍💻</td>
      <td align="center">👩‍💻</td>
      <td align="center">🧑‍💻</td>
   </tr>
   <tr>
      <td align="center">조병현</td>
      <td align="center">김민지</td>
      <td align="center">류연주</td>
      <td align="center">서수현</td>
      <td align="center">장재원</td>
   </tr>
    <tr>
      <td align="center"><a href="https://github.com/cbh1992">깃허브</a></td>
      <td align="center"><a href="https://github.com/minji-0420">깃허브</a></td>
      <td align="center"><a href="https://github.com/yeonjooRyu">깃허브</a></td>
      <td align="center"><a href="https://github.com/joye-seo">깃허브</a></td>
      <td align="center"><a href="https://github.com/jang0710">깃허브</a></td>
   </tr>
      <tr>
      <td align="center"><a href="https://velog.io/@whgg9786">블로그</a></td>
      <td align="center"><a href="https://www.notion.so/about-Kotlin-18bc152c266a40d3a1b0e0d7bc9ab5f8">블로그</a></td>
      <td align="center"><a href="https://yjily.tistory.com/">블로그</a></td>
      <td align="center"><a href="https://joye.tistory.com">블로그</a></td>
      <td align="center"><a href="https://velog.io/@janga19">블로그</a></td>

   </tr>
</table>

## 📌 주요 기능
### MainActivity

* 작업자 : 수현
* Navigation & Bottom Navigation View로 각 화면 전환

### HomeFragment

* 작업자 : 민지, 수현
* 선택한 category에 맞는 Popular Videos, Category Channel 보여줌
* 각 동영상 항목 클릭 시 Detail Activity로 전환
* API 연동 – popular videos(videos 엔드 포인트)
* API 연동 – category(videoCategories 엔드 포인트), category videos
* API 연동 – API 연동  - category channel(channels 엔드 포인트)

### SearchFragment

* 작업자 : 민지, 연주, 재원
* 검색어 입력 창 & 검색 결과 화면에 표시
* 검색어 입력 시 검색 쿼리 기반으로 비디오 정보 가져옴
* 검색어 미 입력 시 ‘검색 결과 없음＇화면 표시
* 예외 상황 고려, 사용자에게 오류 메시지 표시(네트워크 오류, 검색결과 없음 등)
* Infinity scroll

### MyPageFragment

* 작업자 : 민지, 연주
* 사용자 개인정보 및 좋아요 비디오 목록 표시
* 좋아요 비디오 항목 클릭 시 Detail Activity로 이동
* 좋아요 버튼 클릭 시 해당 비디오 정보 Mypage에서 삭제, Detail Activity에 반영

### DetailActivity

* 작업자 : 병현, 민지
* 선택된 비디오의 상세 정보 제공(thumbnail, like btn, info 등)
* 공유 fab 클릭시 url 공유
* Home Fragment ↔ Detail Activity 이동 시 animation 삽입
* 좋아요 버튼 클릭 시 해당 비디오 정보 Mypage Fragment에 data 전달

<img width="882" alt="image" src="https://github.com/threeo-clock/WeTube/assets/104261048/c33b3149-e006-4196-b124-7be0d1dc6353">

## 🖨️

![is](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![is](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)
![is](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![is](https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white)
![is](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![is](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)


-------------------------------------

