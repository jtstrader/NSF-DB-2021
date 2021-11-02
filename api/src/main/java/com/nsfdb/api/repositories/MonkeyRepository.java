package com.nsfdb.api.repositories;

import com.nsfdb.api.models.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonkeyRepository extends JpaRepository<Monkey, Integer> {

}
