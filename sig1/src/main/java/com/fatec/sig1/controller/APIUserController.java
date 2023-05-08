package com.fatec.sig1.controller;


import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fatec.sig1.model.MantemUserRepository;
import com.fatec.sig1.model.User;
import com.fatec.sig1.model.UserDTO;
import com.fatec.sig1.services.MantemUser;

@RestController
@RequestMapping("/api/v1/user")

public class APIUserController {

	@Autowired
	MantemUser mantemUser;

	User user;
	Logger logger = LogManager.getLogger(this.getClass());

	@CrossOrigin // desabilita o cors do spring security
	@PostMapping
	public ResponseEntity<Object> saveCliente(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
		user = new User();

		if (result.hasErrors()) {
			logger.info(">>>>>> apicontroller validacao da entrada dados invalidos" + result.getFieldError());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
		}
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(mantemUser.save(userDTO.retornaUmCliente()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado");
		}

	}

	@CrossOrigin // desabilita o cors do spring security
	@GetMapping
	public ResponseEntity<List<User>> consultaTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(mantemUser.consultaTodos());
	}

	@CrossOrigin // desabilita o cors do spring security
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePorId(@PathVariable(value = "id") Long id) {
		Optional<User> user = mantemUser.consultaPorId(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}
		mantemUser.delete(user.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("ONG excluida");
	}
	
	@CrossOrigin // desabilita o cors do spring security
	@GetMapping("/{id}")
	public ResponseEntity<Object> consultaPorId(@PathVariable Long id) {
		logger.info(">>>>>> apicontroller consulta por id chamado");
		Optional<User> user = mantemUser.consultaPorId(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}
	
	@CrossOrigin // desabilita o cors do spring security
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualiza(@PathVariable long id, @RequestBody @Valid UserDTO userDTO,
			BindingResult result) {

		logger.info(">>>>>> api atualiza informações da ong chamado");

		if (result.hasErrors()) {
			logger.info(">>>>>> apicontroller atualiza informações da ong chamado dados invalidos");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
		}

		Optional<User> c = mantemUser.consultaPorId(id);

		if (c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}

		Optional<User> user = mantemUser.atualiza(id, userDTO.retornaUmCliente());

		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}


	
	@CrossOrigin // desabilita o cors do spring security
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
		logger.info(">>>>>> email da requisicao:" + userDTO.getEmail());
		logger.info(">>>>>> senha da requisicao:" + userDTO.getSenha());
		
		Optional<User> userEmail = mantemUser.findByEmail(userDTO.getEmail());
		Optional<User> userSenha = mantemUser.findBySenha(userDTO.getSenha());
		
		try {
			logger.info(">>>>>> Encontrou Email no banco: " + userEmail.get().getEmail());
			logger.info(">>>>>> Encontrou Senha no banco: " + userSenha.get().getSenha());
		} catch (Exception e) {
			logger.info(e);
		}
		
		if (userEmail.isEmpty() || userSenha.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email ou senha inválidos");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Bem vindo!");
	}


}
