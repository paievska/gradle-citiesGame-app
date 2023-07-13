package cities;

import java.util.Collections;
import java.util.List;

public class CitiesSearch {
    private final Cities cities;
    private List<String> cityList;
    private String lastCity = "";

    public CitiesSearch() {
        cities = new Cities();
        cityList = cities.getCityList();
    }

    public String findCity(char firstChar) {
        cityList = cities.getCityList();
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

    public List<String> getCityList() {
        return cityList;
    }

    public String getPrettyName(String nameCity) {
        return Character.toUpperCase(nameCity.charAt(0)) + nameCity.substring(1);
    }

    public char getLastChar(String cityName) {
        char lastChar = cityName.charAt(cityName.length() - 1);
        if (lastChar == 'ь') lastChar = cityName.charAt(cityName.length() - 2);
        return lastChar;
    }
}
