package models;

import java.util.Date;
import java.util.List;

/**
 * Created by Steven on 12/08/2016.
 */
public class User {

    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String country;
    private String phone;
    private String address;
    private String email;
    private List<String> hobbies;
    private String favorite;

    public User() {

    }

    public User(String firstName, String country, String phone, String email) {
        this.firstName = firstName;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    public User(String phone, String firstName, String lastName, String gender,
                Date birthDate, String country, String address, String email, List<String> hobbies,
                String favorite) {

        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.address = address;
        this.email = email;
        this.hobbies = hobbies;
        this.favorite = favorite;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
