package com.nsfdb.api.repositories;

import com.nsfdb.api.models.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonkeyRepository extends JpaRepository<Monkey, Integer> {

    //Query to retreive all children of a monkey
    @Query("SELECT m FROM CSSubject m WHERE m.behavior_mom = :mom_id")
    public List<Monkey> getChildren(@Param("mom_id") String mom_id);

    //get ages of monkeys
    @Query("SELECT m.age_of_death, count(m) FROM CSSubject m WHERE LOWER(m.status) = 'dead' GROUP BY m.age_of_death ORDER BY m.age_of_death ASC")
    public List<List<Double>> getAges();
}
