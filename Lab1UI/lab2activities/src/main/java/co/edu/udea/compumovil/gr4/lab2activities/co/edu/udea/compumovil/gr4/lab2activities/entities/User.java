package co.edu.udea.compumovil.gr4.lab2activities.co.edu.udea.compumovil.gr4.lab2activities.entities;

import com.orm.SugarRecord;

/**
 * Created by Steven on 06/09/2016.
 */
public class User extends SugarRecord {

    private String username;
    private String email;
    private String password;
    private int age;

    public User(String username, String email, String password, int age) {
        this.username = username;
        this.email = email;
        this.password = password;

        this.age = age;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
