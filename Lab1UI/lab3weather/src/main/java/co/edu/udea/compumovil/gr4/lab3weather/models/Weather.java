package co.edu.udea.compumovil.gr4.lab3weather.models;

import java.util.Calendar;

/**
 * Created by steven on 20/09/16.
 */
public class Weather {

    String main;
    String description;
    String icon;


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
