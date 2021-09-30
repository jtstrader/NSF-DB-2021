package com.nsfdb.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name="CSFounders")
public class Founder {
    @Id
    String Tattoo;
    String Mcode;
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

    public String getMcode() {
        return Mcode;
    }

    public void setMcode(String mcode) {
        Mcode = mcode;
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
