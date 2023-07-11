package cities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cities {
    private List<String> cityList = new ArrayList<>();
    private String lastCity = "";
    //private int userScore = 0;

    public Cities() {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_cities_in_Ukraine").get();
            Element table = doc.select("table.wikitable.sortable").first();
            Elements rows = Objects.requireNonNull(table).select("tr:gt(0)");
            for (Element row : rows) {
                Element secondColumn = row.select("td:eq(1)").first();
                String lastCity = Objects.requireNonNull(secondColumn).text();
                int index = lastCity.indexOf('(');
                String ukrCity = index != -1 ? lastCity.substring(0, index).trim() : lastCity;
                cityList.add(ukrCity.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findCity(char firstChar) {
        char firstCharLowerCase = Character.toLowerCase(firstChar);
        Collections.shuffle(cityList);
        for (String city : cityList) {
            if (city.charAt(0) == firstCharLowerCase) {
                lastCity = city;
                return city;
            }
        }
        return null;
    }

    public void removeCity(String name) {
        cityList.remove(name);
    }
    public String getLastCity() {
        return lastCity;
    }

//    public int getUserScore() {
//        return userScore;
//    }

    public List<String> getCityList() {
        return cityList;
    }

//    public void incrementUserScore() {
//        userScore++;
//    }
}
