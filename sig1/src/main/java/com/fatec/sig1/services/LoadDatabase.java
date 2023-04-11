package com.fatec.sig1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fatec.sig1.model.Cliente;
import com.fatec.sig1.model.MantemOngRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration 
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	@Autowired
	MantemOngRepository OngRepository;
	@Bean
	CommandLineRunner initDatabase(MantemOngRepository repository, MantemCliente repoCliente) {
	return args -> {
		repository.deleteAll();
		Cliente cliente1 = new Cliente("Jose da Silva", "01/03/1964", "M", "59672555598", "03694000", "2983");
		cliente1.setEndereco("Aguia de Haia");
		log.info("Preloading " + repoCliente.save(cliente1));
		};
		
		
	}

}
