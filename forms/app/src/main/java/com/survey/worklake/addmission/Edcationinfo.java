package com.survey.worklake.addmission;

import com.orm.SugarRecord;

/**
 * Created by copperadmin on 7/11/2016.
 */



public class Edcationinfo extends SugarRecord<Edcationinfo> {
    String schoolName10;
    String schoolName12;
    String  board10;
    String board12;
    String per10;
    String per12;
    public Edcationinfo() {
    }

    public Edcationinfo(String schoolName10,String per10,  String board10,String schoolName12,String per12, String board12){
        super();
        this.schoolName10 = schoolName10;
        this.schoolName12 = schoolName12;
        this.board10 = board10;
        this.board12 = board12;
        this.per10 = per10;
        this.per12 = per12;

    }




    public String getSchoolName10() {
        return this.schoolName10;
    }
    public String getSchoolName12() {
        return this.schoolName12;
    }
    public String getBoard10 (){
        return this.board10;
    }
    public String getBoard12 (){
        return this.board12;
    }
    public String getPer10() {
        return this.per10;
    }
    public String getPer12() {
        return this.per10;
    }
    public String toString() {
        return "School Name - "+this.schoolName10+"\n10th Marks - "+this.per10+"\n School Name"+this.schoolName12+"\n12th Marks - "+this.per12;
    }
}
