package com.nsfdb.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name="CSFounder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Founder {
    @Id
    String m_code;
    String tattoo;
    Integer birth_season;
    Date date_of_birth;

    public Founder(){

    }

    public String getM_code() {
        return m_code;
    }

    public void setM_code(String m_code) {
        this.m_code = m_code;
    }

    public String getTattoo() {
        return tattoo;
    }

    public void setTattoo(String tattoo) {
        this.tattoo = tattoo;
    }

    public Integer getBirth_season() {
        return birth_season;
    }

    public void setBirth_season(Integer birth_season) {
        this.birth_season = birth_season;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
