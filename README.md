# Cities game
**The Cities** is a popular wordplay game where players take turns naming cities.
The objective is to come up with a city name that starts with the last letter of the previously mentioned city.
## About
This app is developed using *Java* programming language and built with *Gradle*.
The game incorporates the usage of the *Jsoup* library for retrieving city names from Wikipedia. 
It offers an engaging and challenging experience, testing players' knowledge of cities around Ukraine.
## Rules
* The game starts with the first player entering the name of a city.
* The next player (computer) must then enter the city whose name starts with the last letter of the previously mentioned city and so on.
* Each city name can be used only once in a single game without repetition.
* Players must provide valid city names that appear on the [Wikipedia](https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)).
* If the player enters "Здаюсь" or when the computer runs out of all words in the database, the programs end.
## Features
* **City Name Retrieval:** The program utilizes the Jsoup library to extract city names from Wikipedia, ensuring a comprehensive and reliable list of cities.
* **Hint:** Players have the option to use a hint function, which suggests a city name starting with the correct letter. However, this function comes at the expense of deducting 150 points from the player's score.
* **Timer:** A timer is integrated into the game to create a sense of urgency and encourage quick thinking.
* **Scoring System:** Players earn points for correctly entering city names, while incorrect or invalid entries result in no points.
* **High Score Tracking:** The program records the highest score achieved during a game session, adding a competitive element for players to strive for.
## Screenshots
<div>
  <img width="483px" height="116px" hspace="15" src="https://github.com/paievska/Cities-game/assets/71642076/e40cf163-9d78-4120-98cd-769e8bcfdad3">
</div>
<div>
  <img width="583px" height="429px" hspace="15" src="https://github.com/paievska/Cities-game/assets/71642076/4e992920-326a-4fa1-9375-d15cbc246dbc">
</div>
<div>
  <img width="329px" height="154px" hspace="15" src="https://github.com/paievska/Cities-game/assets/71642076/0d142afe-c153-40ab-9929-d881d20d7117">
</div>
<div>
  <img width="329px" height="202px" hspace="15" src="https://github.com/paievska/Cities-game/assets/71642076/d27f13a4-00a2-41a9-8a6f-178d0afa02d1">
</div>
