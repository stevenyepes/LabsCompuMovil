package co.edu.udea.compumovil.gr4.lab3weather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by steven on 23/09/16.
 */
public class WeatherFull {

    Weather[] weather;
    @SerializedName("main")
    DataWeather dataWeather;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public DataWeather getDataWeather() {
        return dataWeather;
    }

    public void setDataWeather(DataWeather dataWeather) {
        this.dataWeather = dataWeather;
    }
}
