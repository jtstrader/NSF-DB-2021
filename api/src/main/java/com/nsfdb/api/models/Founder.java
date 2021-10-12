package com.nsfdb.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name="CSFounders")
public class Founder {
    @Id
    String MCode;
    String Tattoo;
    Integer BirthSeason;
    Date DateOfBirth;

    public Founder(){

    }

    public String getTattoo() {
        return Tattoo;
    }

    public void setTattoo(String tattoo) {
        Tattoo = tattoo;
    }

    public String getMCode() {
        return MCode;
    }

    public void setMCode(String mCode) {
        MCode = mCode;
    }

    public Integer getBirthSeason() {
        return BirthSeason;
    }

    public void setBirthSeason(Integer birthSeason) {
        BirthSeason = birthSeason;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
