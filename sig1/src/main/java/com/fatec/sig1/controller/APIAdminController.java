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

import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.User;
import com.fatec.sig1.model.Admin;
import com.fatec.sig1.model.AdminDTO;
import com.fatec.sig1.model.Exclusao;
import com.fatec.sig1.services.MantemAdmin;
import com.fatec.sig1.services.MantemExclusao;
import com.fatec.sig1.services.MantemOng;
import com.fatec.sig1.services.MantemUser;


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
			logger.info(">>>>>> apicontroller validacao da entrada dados invalidos  %s" , result.getFieldError());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
		}

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
		Optional<Admin> adminConsultadoD = mantemAdmin.consultaPorId(id);
		if (adminConsultadoD.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado para deletar admin");
		}
		mantemAdmin.delete(adminConsultadoD.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("Administrador excluido");
	}
	 
	
	@CrossOrigin // desabilita o cors do spring security
	@GetMapping("/{id}")
	public ResponseEntity<Object> consultaPorId(@PathVariable Long id) {
		logger.info(">>>>>> apicontroller consulta por id chamado");
		Optional<Admin> adminConsultadoC = mantemAdmin.consultaPorId(id);
		if (adminConsultadoC.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado para consultar");
		}
		return ResponseEntity.status(HttpStatus.OK).body(adminConsultadoC.get());
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado para atualizar");
		}

		Optional<Admin> adminAtualizado = mantemAdmin.atualiza(id, adminDTO.retornaUmCliente());

		if (adminAtualizado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(adminAtualizado.get());			
		}
		
	}

	@Autowired
	MantemExclusao mantemExclusao;
	
	@Autowired
	MantemUser mantemUser;
	
	@CrossOrigin // desabilita o cors do spring security
	@DeleteMapping("deletarUsuario/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Long id) {
		Optional<User> user = mantemUser.consultaPorId(id);

		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado para deletar usuario pelo admin");
		}
		mantemUser.delete(user.get().getId());
		
		Optional <Exclusao> excluiID = mantemExclusao.consultaPorId((long) 1);
		Optional<Exclusao> userExclui = mantemExclusao.atualiza((long) 1, new Exclusao(excluiID.get().getOngExcluidas(), excluiID.get().getUsuariosExcluidos() + 1));
		
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido");
	}
	
	@Autowired
	MantemOng mantemOng;
	
	@CrossOrigin // desabilita o cors do spring security
	@DeleteMapping("deletarOng/{id}")
	public ResponseEntity<Object> deleteONG(@PathVariable(value = "id") Long id) {
		Optional<Ong> ong = mantemOng.consultaPorId(id);
		
		if (ong.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado para deletar ong pelo admin");
		}
		mantemOng.delete(ong.get().getId());
		
		Optional <Exclusao> excluiID = mantemExclusao.consultaPorId((long) 1);
		Optional<Exclusao> ongExclui = mantemExclusao.atualiza((long) 1, new Exclusao(excluiID.get().getOngExcluidas() + 1, excluiID.get().getUsuariosExcluidos()));
		
		return ResponseEntity.status(HttpStatus.OK).body("ONG excluida");
	}
	
	@CrossOrigin // desabilita o cors do spring security
	@GetMapping("/todasAsOngsExcluidas")
	public ResponseEntity<Integer> consultaTodasAsOngExcluidas() {
		Optional <Exclusao> excluiID = mantemExclusao.consultaPorId((long) 1);
		
		return ResponseEntity.status(HttpStatus.OK).body(excluiID.get().getOngExcluidas());
	}
	
	
	@CrossOrigin // desabilita o cors do spring security
	@GetMapping("/todasAsUserExcluidas")
	public ResponseEntity<Integer> consultaTodasAsUsuariosExcluidas() {
		Optional <Exclusao> excluiID = mantemExclusao.consultaPorId((long) 1);
		
		return ResponseEntity.status(HttpStatus.OK).body(excluiID.get().getUsuariosExcluidos());
	}
	

	
}
