package co.edu.udea.compumovil.gr4.lab3weather.models;

import java.util.Calendar;

/**
 * Created by steven on 20/09/16.
 */
public class Weather {

    String temp;
    String humidity;
    String description;
    Calendar lastView;

    public Weather(String temp, String humidity, String description, Calendar lastView) {
        this.temp = temp;
        this.humidity = humidity;
        this.description = description;
        this.lastView = lastView;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getLastView() {
        return lastView;
    }

    public void setLastView(Calendar lastView) {
        this.lastView = lastView;
    }
}
