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

import com.fatec.sig1.model.MantemAdminRepository;
import com.fatec.sig1.model.Admin;
import com.fatec.sig1.model.AdminDTO;
import com.fatec.sig1.services.MantemAdmin;
import com.fatec.sig1.services.MantemAdminI;


@RestController
@RequestMapping("/api/v1/admin")

public class APIAdminController {
	
	@Autowired
	MantemAdmin mantemAdmin;

	Admin admin;
	Logger logger = LogManager.getLogger(this.getClass());

	@CrossOrigin // desabilita o cors do spring security
	@PostMapping
	public ResponseEntity<Object> saveCliente(@RequestBody @Valid AdminDTO adminDTO, BindingResult result) {
		admin = new Admin();

		if (result.hasErrors()) {
			logger.info(">>>>>> apicontroller validacao da entrada dados invalidos" + result.getFieldError());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
		}

		/*if (ongDTO.getCnpj() != null) {
			if (mantemOng.consultaPorCnpj(ongDTO.getCnpj()).isPresent()) {
				logger.info(">>>>>> apicontroller consultaporcnpj CNPJ ja cadastrado");
				return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já cadastrado");
			}
		}*/
		

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(mantemAdmin.save(adminDTO.retornaUmCliente()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado");
		}

	}

	@CrossOrigin // desabilita o cors do spring security
	@GetMapping
	public ResponseEntity<List<Admin>> consultaTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(mantemAdmin.consultaTodos());
	}

	@CrossOrigin // desabilita o cors do spring security
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePorId(@PathVariable(value = "id") Long id) {
		Optional<Admin> admin = mantemAdmin.consultaPorId(id);
		if (admin.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}
		mantemAdmin.delete(admin.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("Administrador excluido");
	}
	
	
	@CrossOrigin // desabilita o cors do spring security
	@GetMapping("/{id}")
	public ResponseEntity<Object> consultaPorId(@PathVariable Long id) {
		logger.info(">>>>>> apicontroller consulta por id chamado");
		Optional<Admin> admin = mantemAdmin.consultaPorId(id);
		if (admin.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(admin.get());
	}
	
	
	@CrossOrigin // desabilita o cors do spring security
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualiza(@PathVariable long id, @RequestBody @Valid AdminDTO adminDTO,
			BindingResult result) {

		logger.info(">>>>>> api atualiza informações da ong chamado");

		if (result.hasErrors()) {
			logger.info(">>>>>> apicontroller atualiza informações do ADM chamado dados invalidos");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
		}

		Optional<Admin> c = mantemAdmin.consultaPorId(id);

		if (c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}

		/*if (adminDTO.getCep() != null) {
			Optional<Endereco> e = Optional.ofNullable(mantemAdmin.obtemEndereco(adminDTO.getCep()));
			if (e.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CEP não localizado.");
			}
		}*/

		Optional<Admin> admin = mantemAdmin.atualiza(id, adminDTO.retornaUmCliente());

		return ResponseEntity.status(HttpStatus.OK).body(admin.get());
	}

}
