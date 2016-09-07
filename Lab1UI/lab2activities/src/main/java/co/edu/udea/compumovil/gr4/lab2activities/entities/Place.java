package co.edu.udea.compumovil.gr4.lab2activities.entities;

import com.orm.SugarRecord;

/**
 * Created by Steven on 06/09/2016.
 */
public class Place extends SugarRecord{

    private String name;
    private String description;
    private int stars;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Place(String name, String description, int stars) {
        this.name = name;
        this.description = description;
        this.stars = stars;
    }

    public Place() {
    }
}
