package com.fatec.sig1.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fatec.sig1.model.Exclusao;
import com.fatec.sig1.model.ExclusaoRepository;

@Service
public class MantemExclusao {

	Logger logger = LogManager.getLogger(this.getClass());
	
	ExclusaoRepository repository;
	
	public Optional<Exclusao> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}
	
	public Optional<Exclusao> atualiza(Long id, Exclusao exclusao) {

		logger.info(">>>>>> Alguem foi excluido");
		
		Exclusao exclusaoModificado = new Exclusao(exclusao.getOngExcluidas(), exclusao.getUsuariosExcluidos());

		Optional<Exclusao> excluiGetIdConsulta = this.repository.findById(id);
		Exclusao exclusaoGetId;
		
		if (!(excluiGetIdConsulta.isEmpty())) {
			exclusaoGetId = excluiGetIdConsulta.get();
		}else {
			return Optional.empty();
		}
		
		exclusaoModificado.setId(id);
		
		if (exclusaoModificado.getOngExcluidas() == 0) {
			exclusaoModificado.setOngExcluidas(exclusaoGetId.getOngExcluidas());
		}
		
		if (exclusaoModificado.getUsuariosExcluidos() == 0) {
			exclusaoModificado.setUsuariosExcluidos(exclusaoGetId.getUsuariosExcluidos());
		}
		
		return Optional.ofNullable(repository.save(exclusaoModificado));
	}
}
