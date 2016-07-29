package com.survey.worklake.addmission;

import com.orm.SugarRecord;

/**
 * Created by copperadmin on 7/11/2016.
 */
public class Scoreinfo extends SugarRecord<Scoreinfo> {


    String mainRank;
    String scoremain;
    String advanceRank;
    String scoreadvance;
public Scoreinfo(){

}
    public Scoreinfo(String mainRank,
               String scoremain,
               String scoreadvance,
               String advanceRank) {
        super();

        this.scoremain=scoremain;
        this.mainRank = mainRank;
        this.scoremain = scoreadvance;
        this.advanceRank=advanceRank;
    }

    public String getMainRank() {
        return this.mainRank;
    }
    public String getScoremain() {
        return this.scoremain;
    }
    public String getScoreadvance() {
        return this.scoreadvance;
    }
    public String getAdvanceRank() {
        return this.advanceRank;
    }
    public String toString() {
        return "Main Rank - "+this.mainRank+"\nAdvance Rank - "+this.advanceRank;
    }
}
