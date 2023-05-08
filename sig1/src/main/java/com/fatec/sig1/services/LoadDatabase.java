package com.fatec.sig1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.MantemOngRepository;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fatec.sig1.model.User;
import com.fatec.sig1.model.MantemUserRepository;

@Configuration 
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Autowired
	MantemOngRepository OngRepository;
	
	@Bean
	CommandLineRunner initDatabase(MantemOngRepository repository, MantemOng repoCliente) {
	return args -> {
		repository.deleteAll();
		
		Ong ong1 = new Ong
				(
						"Adote sempre cabe mais um", 
						981151084, 
						"03694000",
						"Casa", 
						"O Instituto Adote Sempre Cabe Mais Um resgata cães e gatos abandonados ou em situação de maus-tratos em Jarinu (SP). Os animais acolhidos são castrados, vacinados e encaminhados para a adoção, que também é acompanhada pela ONG.", 
						"Proteção Animal", 
						"adotesemprecabemaisum@gmail.com", "456", 
						"33.605.926/0001-60", 
						"6612-6|05", 
						"089527", 
						"0191", 
						"emailpixadote@gmail.com", 
						"302.206.482-71",
						"Zona Norte"
				);
		//6612-6|05
		//7020-4|00
		
		ong1.setEndereco("Aguia de Haia");
		log.info("Preloading " + repository.save(ong1));
		
		};
	}
	@Autowired
	MantemUserRepository UserRepository;
		
	@Bean
	CommandLineRunner initDatabaseUser(MantemUserRepository repository, MantemUser repoCliente) {
		return args -> {
		repository.deleteAll();
				
		User user1 = new User("Diogo", "Lima","DiogoLima50@gmail.com", "12345");
		log.info("Preloading " + repository.save(user1));
				
		};

	}

}
