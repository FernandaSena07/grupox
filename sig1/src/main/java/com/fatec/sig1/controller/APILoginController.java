package com.fatec.sig1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sig1.model.Ong;
import com.fatec.sig1.services.MantemOng;
import com.fatec.sig1.model.User;
import com.fatec.sig1.model.UserDTO;
import com.fatec.sig1.services.MantemUser;
import com.fatec.sig1.services.MantemAdmin;
import com.fatec.sig1.model.Admin;


@RestController
@RequestMapping("/api/v1/login")
public class APILoginController {

	@Autowired
	MantemOng mantemOng;
	Ong ong;
	
	@Autowired
	MantemUser mantemUser;
	User user;
	
	@Autowired
	MantemAdmin mantemAdmin;
	Admin admin;
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	
	@CrossOrigin // desabilita o cors do spring security
	@PostMapping
	public ResponseEntity<Object> login(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
		
		logger.info(">>>>>> email da requisicao:  %s" , userDTO.getEmail());
		logger.info(">>>>>> senha da requisicao:  %s" , userDTO.getSenha());

		Optional<User> userEmail = mantemUser.findByEmail(userDTO.getEmail());
		Optional<User> userSenha = mantemUser.findBySenha(userDTO.getSenha());
		
		if (userEmail.isEmpty() || userSenha.isEmpty()) {
			logger.info("!!Nada encontrado no banco de dados do tipo usuário!!");
		}else {
			try {
				logger.info(">>>>>> Encontrou Email no banco do usuario:  %s" , userEmail.get().getEmail());
				logger.info(">>>>>> Encontrou Senha no banco do usuario:  %s" , userSenha.get().getSenha());
			} catch (Exception e) {
				logger.info(">>>> ERRO:  %s" , e);
			}
			return ResponseEntity.status(HttpStatus.OK).body(userEmail.get());
		}
		

		Optional<Ong> ongEmail = mantemOng.findByEmail(userDTO.getEmail());
		Optional<Ong> ongSenha = mantemOng.findBySenha(userDTO.getSenha());

		if (ongEmail.isEmpty() || ongSenha.isEmpty()) {
			logger.info("!!Nada encontrado no banco de dados do tipo ong!!");
		}else {
			try {
				logger.info(">>>>>> Encontrou Email no banco da ong:  %s" , ongEmail.get().getEmail());
				logger.info(">>>>>> Encontrou Senha no banco da ong:  %s" , ongSenha.get().getSenha());
			} catch (Exception e) {
				logger.info(e);
			}
			return ResponseEntity.status(HttpStatus.OK).body(ongEmail.get());
		}
		
		Optional<Admin> adminEmail = mantemAdmin.findByEmail(userDTO.getEmail());
		Optional<Admin> adminSenha = mantemAdmin.findBySenha(userDTO.getSenha());
		
		if (adminEmail.isEmpty() || adminSenha.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email ou senha inválidos");
		}else {
			try {
				logger.info(">>>>>> Encontrou Email no banco do admin:  %s" , adminEmail.get().getEmail());
				logger.info(">>>>>> Encontrou Senha no banco do admin:  %s" , adminSenha.get().getSenha());
			} catch (Exception e) {
				logger.info(e);
			}
			return ResponseEntity.status(HttpStatus.OK).body(adminEmail.get());		
		}
	}
}
