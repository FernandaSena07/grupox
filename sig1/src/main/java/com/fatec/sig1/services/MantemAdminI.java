package com.fatec.sig1.services;

import java.util.List;

import java.util.Optional;

import com.fatec.sig1.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fatec.sig1.model.Admin;
import com.fatec.sig1.model.Endereco;
import com.fatec.sig1.model.MantemAdminRepository;
import com.fatec.sig1.services.MantemAdmin;

@Service

public class MantemAdminI implements MantemAdmin {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MantemAdminRepository repository;
	
	@Override
	public List<Admin> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");

		return repository.findAll();
	}

	@Override
	public Optional<Admin> consultaPorId(Long id) {

		logger.info(">>>>>> servico consultaPorId chamado");

		return repository.findById(id);
	}

	@Override
	public Optional<Admin> consultaPorEmail(String email) {

		logger.info(">>>>>> servico constultaPorEmail chamado");

		return repository.findByEmail(email);
	}

	
	@Override
	public Optional<Admin> save(Admin admin) {
		
		logger.info(">>>>>> servico save do usuário chamado ");

		return Optional.ofNullable(repository.save(admin));
	}

	
	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");

		repository.deleteById(id);

	}
	@Override
	public Optional<Admin> atualiza(Long id, Admin admin) {

		logger.info(">>>>>> 1.servico atualiza informações da ong chamado");


		// Colocar if para verificar quantas informações tem??
		// Dependendo de quantas tem chama outro construtor

		Admin adminModificado = new Admin(admin.getNome(), admin.getSobrenome(),admin.getEmail(), admin.getSenha());

		Admin adminGetId = this.repository.findById(id).get();

		adminModificado.setId(id);
		
		logger.info(
				">>>>>> 2. servico atualiza informacoes da ong cep valido para o id => " + adminModificado.getId());

		if (adminModificado.getNome() == null) {
			adminModificado.setNome(adminGetId.getNome());
		}

		if (adminModificado.getSobrenome() == null) {
			adminModificado.setSobrenome(adminGetId.getSobrenome());
		}

		if (adminModificado.getEmail() == null) {
			adminModificado.setEmail(adminGetId.getEmail());
		}

		if (adminModificado.getSenha() == null) {
			adminModificado.setSenha(adminGetId.getSenha());
		}
		
		return Optional.ofNullable(repository.save(adminModificado));
	}

	@Override
	public Optional<Admin> findByEmail(String email) {
		logger.info(">>>>>> servico consulta Email do usuario chamado");

		return repository.findByEmail(email);
	}

	@Override
	public Optional<Admin> findBySenha(String senha) {
		logger.info(">>>>>> servico consulta Senha do usuario chamado");

		return repository.findBySenha(senha);
	}

}
