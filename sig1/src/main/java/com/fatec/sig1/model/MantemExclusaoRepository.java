package com.fatec.sig1.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantemExclusaoRepository extends JpaRepository<Exclusao, Long>  {

}
