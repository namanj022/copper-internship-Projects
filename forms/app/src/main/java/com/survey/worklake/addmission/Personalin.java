package com.survey.worklake.addmission;

/**
 * Created by copperadmin on 7/11/2016.
 */

import com.orm.SugarRecord;

public class Personalin extends SugarRecord<Personalin>{
    String name;
    String phone;
    String dob;
    String email;
    String password;
    String category;

public Personalin(){

}


    public Personalin(String name, String phone, String email, String password, String dob,String category) {
        super();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.category=category;
        this.dob=dob;
    }
    public String getName() {
        return this.name;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }

    public String getDob() {
        return this.dob;
    }

    public String getCategory() {
        return this.category;
    }

    public String toString() {
        return "Name - "+this.name+"\nEmail - "+this.email+"\nPhone Number - "+this.phone+"\n";
    }
}
