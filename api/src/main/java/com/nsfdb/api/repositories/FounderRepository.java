package com.nsfdb.api.repositories;

import com.nsfdb.api.models.Founder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FounderRepository extends JpaRepository<Founder, String> {
}
