package com.fatec.sig1.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.sig1.model.Exclusao;
import com.fatec.sig1.model.MantemExclusaoRepository;

@Service
public class MantemExclusaoI implements MantemExclusao {

	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	MantemExclusaoRepository repository;

	@Override
	public Optional<Exclusao> atualiza(Long id, Exclusao exclui) {
		logger.info(">>>>>> 1.servico de exclusao foi chamado");

		Exclusao exclusaoModificada = new Exclusao(exclui.getOngExcluidas(), exclui.getUsuariosExcluidos());

		
		Optional<Exclusao> excluiGetIdConsulta = this.repository.findById(id);
		Exclusao excluiGetId;
		
		if (!(excluiGetIdConsulta.isEmpty())) {
			excluiGetId = excluiGetIdConsulta.get();
		}else {
			return Optional.empty();
		}

		exclusaoModificada.setId(id);
		

		if (exclusaoModificada.getOngExcluidas() == 0) {
			exclusaoModificada.setOngExcluidas(excluiGetId.getOngExcluidas());
		}

		if (exclusaoModificada.getUsuariosExcluidos() == 0) {
			exclusaoModificada.setUsuariosExcluidos(excluiGetId.getUsuariosExcluidos());
		}
		
		return Optional.ofNullable(repository.save(exclusaoModificada));
		
	}

	@Override
	public Optional<Exclusao> consultaPorId(Long id) {
		logger.info(">>>>>> 1.servico de consulta por exclusao");
		return repository.findById(id);
	}
	

	
	
	
}
