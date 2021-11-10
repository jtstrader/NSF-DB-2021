package com.nsfdb.api.repositories;

import com.nsfdb.api.models.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonkeyRepository extends JpaRepository<Monkey, Integer> {

    @Query("SELECT m FROM CSSubject m WHERE m.matriarch = :mom_id")
    public List<Monkey> getChildren(@Param("mom_id") String mom_id);
}
