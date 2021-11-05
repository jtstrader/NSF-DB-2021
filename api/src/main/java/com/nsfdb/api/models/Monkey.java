package com.nsfdb.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name="CSSubject")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Monkey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sequence_id;
    String animal_id;
    Date date_of_birth;
    Integer birth_season;
    Character sex;
    String birth_group;
    String current_group;
    String status;
    Date date_of_death;
    Date date_of_remove;
    Float age_of_delivery;
    String dam_genetic;
    Date mom_dob;
    Float age_of_remove;
    Float age_of_death;
    String sire_genetic;
    Integer pedigree;
    Integer sibling_no;
    String matriarch;

    public Monkey() {

    }

    public Integer getSequence_id() {
        return sequence_id;
    }

    public void setSequence_id(Integer sequence_id) {
        this.sequence_id = sequence_id;
    }

    public String getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(String animal_id) {
        this.animal_id = animal_id;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getBirth_season() {
        return birth_season;
    }

    public void setBirth_season(Integer birth_season) {
        this.birth_season = birth_season;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getBirth_group() {
        return birth_group;
    }

    public void setBirth_group(String birth_group) {
        this.birth_group = birth_group;
    }

    public String getCurrent_group() {
        return current_group;
    }

    public void setCurrent_group(String current_group) {
        this.current_group = current_group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_of_death() {
        return date_of_death;
    }

    public void setDate_of_death(Date date_of_death) {
        this.date_of_death = date_of_death;
    }

    public Date getDate_of_remove() {
        return date_of_remove;
    }

    public void setDate_of_remove(Date date_of_remove) {
        this.date_of_remove = date_of_remove;
    }

    public Float getAge_of_delivery() {
        return age_of_delivery;
    }

    public void setAge_of_delivery(Float age_of_delivery) {
        this.age_of_delivery = age_of_delivery;
    }

    public String getDam_genetic() {
        return dam_genetic;
    }

    public void setDam_genetic(String dam_genetic) {
        this.dam_genetic = dam_genetic;
    }

    public Date getMom_dob() {
        return mom_dob;
    }

    public void setMom_dob(Date mom_dob) {
        this.mom_dob = mom_dob;
    }

    public Float getAge_of_remove() {
        return age_of_remove;
    }

    public void setAge_of_remove(Float age_of_remove) {
        this.age_of_remove = age_of_remove;
    }

    public Float getAge_of_death() {
        return age_of_death;
    }

    public void setAge_of_death(Float age_of_death) {
        this.age_of_death = age_of_death;
    }

    public String getSire_genetic() {
        return sire_genetic;
    }

    public void setSire_genetic(String sire_genetic) {
        this.sire_genetic = sire_genetic;
    }

    public Integer getPedigree() {
        return pedigree;
    }

    public void setPedigree(Integer pedigree) {
        this.pedigree = pedigree;
    }

    public Integer getSibling_no() {
        return sibling_no;
    }

    public void setSibling_no(Integer sibling_no) {
        this.sibling_no = sibling_no;
    }

    public String getMatriarch() {
        return matriarch;
    }

    public void setMatriarch(String matriarch) {
        this.matriarch = matriarch;
    }
}
