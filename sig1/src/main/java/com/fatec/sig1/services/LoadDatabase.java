package com.fatec.sig1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.Admin;
import com.fatec.sig1.model.MantemAdminRepository;
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
						"Bradesco",
						"emailpixadote@gmail.com", 
						"302.206.482-71",
						"Zona Norte"
				);
		ong1.setEndereco("Aguia de Haia");
		log.info("Preloading " + repository.save(ong1));
		
		Ong ong2 = new Ong
				(
						"Projeto Esperança", 
						1106944784, 
						"08011030",
						"Casa", 
						"O Projeto Esperança de São Miguel Paulista - PROJESP - foi criado a partir de uma grande necessidade de atuação social na década de 80 para a assistência às pessoas vítimas de uma forte epidemia de AIDS. Pela falta de informações, recursos financeiros e os devidos direcionamentos, a população acometida pela doença nessa região era uma das mais fragilizadas.", 
						"Cidadania", 
						"projesp@projesp.org.br", "Oi9*121A", 
						"12.121.323/0002-61", 
						"9430-8|00", 
						"12212", 
						"0154", 
						"Bradesco",
						"esperanzaProjeto@gmail.com", 
						"102.206.482-71",
						"Zona Leste"
				);
		ong2.setEndereco("Guilherme de Aguiar");
		log.info("Preloading " + repository.save(ong2));
		
		Ong ong3 = new Ong
				(
						"Associação Fala Mulher", 
						1130630807, 
						"05417000",
						"Casa", 
						"Somos uma Organização sem fins lucrativos, que atua no enfrentamento à violência doméstica contra a mulher, na construção da equidade de gênero, na promoção da independência financeira feminina, na facilitação do acesso às informações sobre a Lei Maria da Penha e os direitos das mulheres. Além do trabalho na defesa e garantia dos direitos humanos, de pessoas em situação de vulnerabilidade social ou violação de direitos.", 
						"Gênero e diversidade", 
						"falaMulher@hotmail.com", "Oi29*121B", 
						"11.111.333/0002-61", 
						"9430-8|00", 
						"12200", 
						"0004", 
						"Nubank",
						"67084387776011847635893856138508", 
						"200.006.182-71",
						"Zona Oeste"
				);
		ong3.setEndereco("Mourato Coelho");
		log.info("Preloading " + repository.save(ong3));
		
		Ong ong4 = new Ong
				(
						"Grão da Vida", 
						1155231772, 
						"04775120",
						"Casa", 
						"O Grão da Vida é uma Instituição não governamental, que há mais de 30 anos promove o Desenvolvimento Infantil. Em convênio com a Prefeitura Municipal de São Paulo, atualmente respondemos pela educação de 290 crianças de 0 a 3 anos de idade em 2 Centros de Educação Infantil localizados nas regiões de Capela do Socorro (CEI Manoel Bispo dos Santos) e Grajaú (CEI Marina Villares da Silva Novaes)", 
						"Educação", 
						"GraoDaVida2000@gmail.com", "VidaGrao", 
						"44.111.343/1102-00", 
						"9430-8|00", 
						"99200", 
						"1001", 
						"Ítau",
						"GraoDaVida2000@gmail.com", 
						"221.346.182-71",
						"Zona Sul"
				);
		ong4.setEndereco("Olímpio Carr Ribeiro");
		log.info("Preloading " + repository.save(ong4));
		
		
		Ong ong5 = new Ong
				(
						"Nós do Bem", 
						1140023892, 
						"01011100",
						"13° andar", 
						"A Associação Voluntários Nós do Bem é uma Organização Não Governamental (ou seja, privada e sem fins lucrativos), legalmente instituída com o objetivo de promover o desenvolvimento social. Nascida em 07 de maio de 2016, visa levar motivação da cultura e da arte nos locais que consiste na visita regular de nossos participantes nestes locais, onde promovem práticas de caráter lúdico, de contato humano.", 
						"Cultura e esporte", 
						"contato@nosdobem.org", "FazOBemRapa200*", 
						"99.999.124/1102-00", 
						"9430-8|00", 
						"91311", 
						"1002", 
						"Banco do Brasil",
						"contato@nosdobem.org", 
						"222.121.182-71",
						"Centro"
				);
		ong5.setEndereco("Rua São Bento, 329");
		log.info("Preloading " + repository.save(ong5));
		
		Ong ong6 = new Ong
				(
						"Kor Ambiental", 
						1199325819, 
						"04757000",
						"Casa", 
						"A Kor Ambiental foi fundada com objetivo de fornecer serviços de excelência em soluções de engenharia ambiental, focadas nas áreas de Gerenciamento de Áreas Contaminadas, Gerenciamento de Áreas Verdes e Licenciamento Ambiental. ​ Composta por uma equipe multidisciplinar de profissionais qualificados, graduados em instituições renomadas e com mais de 10 anos de experiência, a Kor Ambiental está pronta para oferecer soluções econômicas e sustentáveis de acordo com a necessidade do cliente, público ou privado, dos mais diversos setores.", 
						"Meio Ambiente", 
						"contato@korambiental.com.br", "Kor123455", 
						"11.399.124/1102-00", 
						"9430-8|00", 
						"89450", 
						"1001", 
						"Caixa",
						"contato@korambiental.com.br", 
						"111.121.182-71",
						"Zona Sul"
				);
		ong6.setEndereco("Bento Branco de Andrade Filho");
		log.info("Preloading " + repository.save(ong6));
		
		Ong ong7 = new Ong
				(
						"Cuida de Mim", 
						1196366761, 
						"03511060",
						"Casa", 
						"O projeto do Instituto Cuida de Mim surgiu em 2007 a partir de reflexões e questionamentos de um grupo multidisciplinar de profissionais com experiência e prática clínica, obtidos no Instituto Sedes Sapientiae, bem como através de atividades em consultórios particulares e outras instituições.", 
						"Saúde", 
						"contato@cuidademim.com.br", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Santander",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste"
				);
		ong7.setEndereco("Praça Dom Duarte Leopoldo, 137");
		log.info("Preloading " + repository.save(ong7));
		
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
	
	@Autowired
	MantemAdminRepository AdminRepository;
		
	@Bean
	CommandLineRunner initDatabaseAdmin(MantemAdminRepository repository, MantemAdmin repoCliente) {
		return args -> {
		repository.deleteAll();
				
		Admin admin1 = new Admin("Fernanda", "Sena de Souza","ScrumMaster4K@gmail.com", "secretyPassword12");
		log.info("Preloading " + repository.save(admin1));
				
		};

	}
	
	

}
