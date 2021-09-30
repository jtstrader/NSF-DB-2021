package com.nsfdb.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name="CS2020CensusComplete")
public class Monkey {
    @Id
    String AnimalId;
    Integer SequenceNo;
    Date DateOfBirth;
    Integer MonthDOB;
    Integer YearDOB;
    Character Sex;
    String BirthGroup;
    String BehaviourMom;
    String CurrentGroup;
    Integer BirthSeason;
    String Status;
    Date DateOfDeath;
    Date DateOfRemove;
    Float AgeOfDelivery;
    Date MomDOB;
    Float AgeOfDOD;
    String SireGenetic;
    String DamGenetic;

    public Monkey() {

    }

    public String getAnimalId() {
        return AnimalId;
    }

    public void setAnimalId(String animalId) {
        AnimalId = animalId;
    }

    public Integer getSequenceNo() {
        return SequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        SequenceNo = sequenceNo;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public Integer getMonthDOB() {
        return MonthDOB;
    }

    public void setMonthDOB(Integer monthDOB) {
        MonthDOB = monthDOB;
    }

    public Integer getYearDOB() {
        return YearDOB;
    }

    public void setYearDOB(Integer yearDOB) {
        YearDOB = yearDOB;
    }

    public Character getSex() {
        return Sex;
    }

    public void setSex(Character sex) {
        Sex = sex;
    }

    public String getBirthGroup() {
        return BirthGroup;
    }

    public void setBirthGroup(String birthGroup) {
        BirthGroup = birthGroup;
    }

    public String getBehaviourMom() {
        return BehaviourMom;
    }

    public void setBehaviourMom(String behaviourMom) {
        BehaviourMom = behaviourMom;
    }

    public String getCurrentGroup() {
        return CurrentGroup;
    }

    public void setCurrentGroup(String currentGroup) {
        CurrentGroup = currentGroup;
    }

    public Integer getBirthSeason() {
        return BirthSeason;
    }

    public void setBirthSeason(Integer birthSeason) {
        BirthSeason = birthSeason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getDateOfDeath() {
        return DateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        DateOfDeath = dateOfDeath;
    }

    public Date getDateOfRemove() {
        return DateOfRemove;
    }

    public void setDateOfRemove(Date dateOfRemove) {
        DateOfRemove = dateOfRemove;
    }

    public Float getAgeOfDelivery() {
        return AgeOfDelivery;
    }

    public void setAgeOfDelivery(Float ageOfDelivery) {
        AgeOfDelivery = ageOfDelivery;
    }

    public Date getMomDOB() {
        return MomDOB;
    }

    public void setMomDOB(Date momDOB) {
        MomDOB = momDOB;
    }

    public Float getAgeOfDOD() {
        return AgeOfDOD;
    }

    public void setAgeOfDOD(Float ageOfDOD) {
        AgeOfDOD = ageOfDOD;
    }

    public String getSireGenetic() {
        return SireGenetic;
    }

    public void setSireGenetic(String sireGenetic) {
        SireGenetic = sireGenetic;
    }

    public String getDamGenetic() {
        return DamGenetic;
    }

    public void setDamGenetic(String damGenetic) {
        DamGenetic = damGenetic;
    }
}
