package com.fatec.sig1.services;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.MantemOngRepository;

@Configuration
class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	@Autowired
	MantemOngRepository OngRepository;

	@Bean
	CommandLineRunner initDatabase(MantemOngRepository repository) {
		return args -> {
			repository.deleteAll();
			//Cliente cliente1 = new Cliente("Jose da Silva", "01/03/1964", "M", "59672555598", "03694000", "2983");
			//cliente1.setEndereco("Aguia de Haia");
			//log.info("Preloading " + repository.save(cliente1));
			
			Ong ong1 = new Ong("Adote sempre cabe mais um", 981151084, "13240-000","1", "2", "3", "adotesemprecabemaisum@gmail.com", "456", "33.605.926/0001-60" );
			ong1.setEndereco("I don't know what i'm doing");
			log.info("Preloading " + repository.save(ong1));
			
					
		};
	}
}