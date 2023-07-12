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
![image](https://github.com/paievska/gradle-citiesGame-app/assets/71642076/6e8d6694-9353-41ba-a576-76f373f4d3c5)

![image](https://github.com/paievska/gradle-citiesGame-app/assets/71642076/8d51f8b0-b722-43ff-a27d-490b45fee08c)

![image](https://github.com/paievska/gradle-citiesGame-app/assets/71642076/95f0e1b5-610f-46f8-888d-adb8e50827ee)

![image](https://github.com/paievska/gradle-citiesGame-app/assets/71642076/e7b8de3f-173a-4efb-9796-f68f0e533aa1)



