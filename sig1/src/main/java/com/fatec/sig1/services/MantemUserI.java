package com.fatec.sig1.services;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import com.fatec.sig1.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fatec.sig1.model.Endereco;
import com.fatec.sig1.model.MantemUserRepository;
import com.fatec.sig1.model.Ong;

@Service
public class MantemUserI implements MantemUser {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MantemUserRepository repository;
	
	@Override
	public List<User> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");

		return repository.findAll();
	}

	
	// ----------------------------------------------------- PARA RELATÓRIO -----------------------------------------------------

	public Long todosOsUsuarioCadastrados() {
		logger.info(">>>>>> Pesquisando todas as ongs");
		return repository.count();
	}

	// ----------------------------------------------------- PARA RELATÓRIO -----------------------------------------------------

	@Override
	public Optional<User> consultaPorId(Long id) {

		logger.info(">>>>>> servico consultaPorId chamado");

		return repository.findById(id);
	}

	@Override
	public Optional<User> consultaPorEmail(String email) {

		logger.info(">>>>>> servico constultaPorEmail chamado");

		return repository.findByEmail(email);
	}

	
	@Override
	public Optional<User> save(User user) {
		
		logger.info(">>>>>> servico save do usuário chamado ");

		return Optional.ofNullable(repository.save(user));
	}

	
	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");

		repository.deleteById(id);

	}
	@Override
	public Optional<User> atualiza(Long id, User user) {

		logger.info(">>>>>> 1.servico atualiza informações da ong chamado");


		// Colocar if para verificar quantas informações tem??
		// Dependendo de quantas tem chama outro construtor

		User userModificado = new User(user.getNome(), user.getSobrenome(),user.getEmail(), user.getSenha(), user.getDataCadastro() ,user.getFavoritos());

		User userGetId = this.repository.findById(id).get();

		userModificado.setId(id);
		
		logger.info(
				">>>>>> 2. servico atualiza informações do usuario => " + userModificado.getId());

		if (userModificado.getNome() == null) {
			userModificado.setNome(userGetId.getNome());
		}

		if (userModificado.getSobrenome() == null) {
			userModificado.setSobrenome(userGetId.getSobrenome());
		}

		if (userModificado.getEmail() == null) {
			userModificado.setEmail(userGetId.getEmail());
		}

		if (userModificado.getSenha() == null) {
			userModificado.setSenha(userGetId.getSenha());
		}
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		String dataAtual = new DateTime().toString(fmt);

		if (userModificado.getDataCadastro().equalsIgnoreCase(dataAtual)) {
			userModificado.setDataCadastro(userGetId.getDataCadastro());
		}
		
		return Optional.ofNullable(repository.save(userModificado));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		logger.info(">>>>>> servico consulta Email do usuario chamado");

		return repository.findByEmail(email);
	}

	@Override
	public Optional<User> findBySenha(String senha) {
		logger.info(">>>>>> servico consulta Senha do usuario chamado");

		return repository.findBySenha(senha);
	}



}


	
	



		