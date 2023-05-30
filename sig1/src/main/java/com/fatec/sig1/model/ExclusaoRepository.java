package com.fatec.sig1.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExclusaoRepository extends JpaRepository<Exclusao, Long>{

	Optional<Exclusao> findById(long id);
}
