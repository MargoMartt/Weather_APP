package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import org.json.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textField;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        String cityName = textField.getText();
        List<City> list = init();
        String lat = "", lon = "";
        for (City c : list) {
            if (c.getName().equals(cityName)) {
                lat = c.getLat();
                lon = c.getLon();
                break;
            }
        }

        String str1 = textField.getText();
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=494d5000434a28f092edc3d756a55849");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String str = bufferedReader.readLine();
        File file = new File("data.json");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(str.getBytes());

        JSONObject jsonObject = new JSONObject(str);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("main");
        String temp = String.valueOf(jsonObject1.get("temp"));
        String feelTemp = String.valueOf(jsonObject1.get("feels_like"));
        String minTemp = String.valueOf(jsonObject1.get("temp_min"));
        String maxTemp = String.valueOf(jsonObject1.get("temp_max"));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Температура воздуха: " + Math.round(Double.valueOf(temp) - 273) + "\n");
        stringBuilder.append("Ощущается как: " + Math.round(Double.valueOf(feelTemp) - 273) + "\n");
        stringBuilder.append("Минимальная температура: " + Math.round(Double.valueOf(minTemp) - 273) + "\n");
        stringBuilder.append("Максимальная температура: " + Math.round(Double.valueOf(maxTemp) - 273) + "\n");
        welcomeText.setText(stringBuilder.toString());
    }

    private List<City> init() {
        List<City> list = new ArrayList<>();
        list.add(new City("Минск", "53.893009", "27.567444"));
        list.add(new City("Гродно", "53.6688", "23.8223"));
        list.add(new City("Брест", "52.4313", "30.9937"));
        list.add(new City("Витебск", "55.1927", "30.2064"));
        list.add(new City("Могилев", "53.8981", "30.3325"));
        list.add(new City("Гомель", "52.4313", "30.9937"));
        list.add(new City("Москва", "55.7558", "37.6173"));
        list.add(new City("Нью-Йорк", "40.7128", "74.0060"));
        list.add(new City("Лондон", "51.5072", "0.1276"));
        list.add(new City("Париж", "48.8566", "2.3522"));
        list.add(new City("Дубай", "25.2048", "55.2708"));
        list.add(new City("Пекин", "39.9042", "116.4074"));
        list.add(new City("Берлин", "52.5200", "13.4050"));
        list.add(new City("Варшава", "52.2297", "21.0122"));
        list.add(new City("Стокгольм", "59.3293", "18.0686"));
        list.add(new City("Баку", "40.4093", "49.8671"));
        list.add(new City("Анталья", "36.8969", "30.7133"));
        list.add(new City("Будапешт", "47.4979", "19.0402"));
        return list;
    }
}