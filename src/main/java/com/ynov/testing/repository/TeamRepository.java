package com.ynov.testing.repository;

import com.ynov.testing.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);

    List<Team> findByRegion(String region);

    List<Team> findByActiveTrue();
}
