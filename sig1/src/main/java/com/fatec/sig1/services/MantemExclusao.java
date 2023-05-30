package com.fatec.sig1.services;

import java.util.Optional;

import com.fatec.sig1.model.Exclusao;

public interface MantemExclusao {
	Optional<Exclusao> atualiza(Long id, Exclusao exclui);
	
	Optional<Exclusao> consultaPorId(Long id);
	

}
