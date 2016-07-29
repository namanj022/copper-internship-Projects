package com.survey.worklake.addmission;

import com.orm.SugarRecord;

/**
 * Created by copperadmin on 7/11/2016.
 */
public class Parentsin extends SugarRecord {


    private String fatherName;
    private String motherName;
    private String phone;
    private String emailid;
    private String fatheroccupation;
    private String motheroccupation;

    public Parentsin(){

    }

    public Parentsin(String fatherName, String motherName, String phone, String emailid, String fatheroccupation,String motheroccupation) {
        super();
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phone = phone;
        this.emailid=emailid;
        this.fatheroccupation=fatheroccupation;
        this.motheroccupation=motheroccupation;
    }
    private String getFatherName() {
        return this.fatherName;
    }
    private String getMotherName() {
        return this.motherName;
    }
    private String getPhone() {
        return this.phone;
    }
    private String getEmailid(){
        return this.emailid;
    }
    private String getFatheroccupation(){
      return   this.fatheroccupation;
    }

    private String getMotheroccupation(){
        return this.motheroccupation;
    }
    public String toString() {
        return "Father's Name - "+this.fatherName+"\nMother's Name - "+this.motherName;
    }
}
