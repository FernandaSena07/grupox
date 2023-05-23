package com.fatec.sig1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.Admin;
import com.fatec.sig1.model.MantemAdminRepository;
import com.fatec.sig1.model.MantemOngRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;
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
						"Zona Norte",
						LocalDate.parse("2023-02-20")
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
						"Zona Leste",
						LocalDate.parse("2023-02-25")
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
						"Zona Oeste",
						LocalDate.parse("2023-02-10")
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
						"Banco Itaú",
						"GraoDaVida2000@gmail.com", 
						"221.346.182-71",
						"Zona Sul",
						LocalDate.parse("2023-04-01")
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
						"Centro",
						LocalDate.parse("2023-03-20")
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
						"Zona Sul",
						LocalDate.parse("2023-04-18")
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
						"Zona Leste",
						LocalDate.parse("2023-01-29")
				);
		ong7.setEndereco("Praça Dom Duarte Leopoldo, 137");
		log.info("Preloading " + repository.save(ong7));
		
		Ong ong8 = new Ong
				(
						"Obra Social São Mateus", 
						1129195894, 
						"03959030",
						"Casa", 
						"Fundada em 09 de Setembro de 1964, nasceu da preocupação de um grupo da Comunidade de São Mateus com a população do bairro, que crescia gradativamente e desprovida de recursos mínimos necessários como: água, luz, saneamento básico, transporte e alimentação.", 
						"Cidadania", 
						"sede@obrasocialsaomateus.org", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Santander",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-03-29")
				);
		ong8.setEndereco("Rua Alessandro Giulio Dell'Aringa, 126");
		log.info("Preloading " + repository.save(ong8));
		
		Ong ong9 = new Ong
				(
						"Rosa Mulher - Grupo de Apoio - Câncer de Mama", 
						1120129634, 
						"03963050",
						"Casa", 
						"Somos uma equipe formada por mulheres com histórias diferentes. Entre nós, temos psicóloga, assistente social, mulheres que estão enfrentando a batalha pela vida, outras que passaram e venceram o câncer de mama e outras simpatizantes da causa. Acreditamos que nossos caminhos não se cruzaram por acaso, por isso, decidimos nos unir para dar apoio, carinho e atenção à MULHER que enfrenta a luta contra o câncer.", 
						"Gênero e diversidade", 
						"apoio@associacaorosamulher.org", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Santander",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-03-21")
				);
		ong9.setEndereco("R. Dr. José Cioffi, 475");
		log.info("Preloading " + repository.save(ong9));
		
		Ong ong10 = new Ong
				(
						"Associação de apoio humanitário", 
						1120129634, //11997801429
						"04232000",
						"Casa", 
						"A Ong Associação de Apoio Humanitário, AAH, é uma organização criada com o intuito de arrecadar fundos e doações para famílias que estão passando por necessidades e também para moradores de rua.", 
						"Cidadania", 
						"contato@ongaah.com.br", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Santander",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-05-20")
				);
		ong10.setEndereco("Estr. das Lágrimas, 1712");
		log.info("Preloading " + repository.save(ong10));
		
		Ong ong11 = new Ong
				(
						"ONG Banco de Alimentos", 
						1120129634, //11950290006
						"01235010",
						"Casa", 
						"Somos o resultado da visão pioneira de nossa fundadora, a economista Luciana Chinaglia Quintão, e a soma de esforços de cidadãos, empresas e indústrias alimentícias dedicadas a reduzir o desperdício de alimentos e a combater a fome.", 
						"Cidadania", 
						"vanessa.godoy@printeccomunicacao.com.br", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Caixa",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Oeste",
						LocalDate.parse("2023-05-20")
				);
		ong11.setEndereco("Rua Atibaia, 218");
		log.info("Preloading " + repository.save(ong11));
		
		Ong ong12 = new Ong
				(
						"Agente Ajuda", 
						1121901465, //Não tem número de tel
						"04563013",
						"Casa", 
						"Somos uma causa.Somos um sentimento.Somos PESSOAS!", 
						"Cidadania", 
						"contato@agenteajuda.org.br", "FazUmaBoaAcaoRapa123455", 
						"90.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco Itaú",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Sul",
						LocalDate.parse("2023-03-02")
				);
		ong12.setEndereco("Av. Padre Antônio José dos Santos, 1140");
		log.info("Preloading " + repository.save(ong12));
		
		Ong ong13 = new Ong
				(
						"A Make-A-Wish Brasil", 
						1120129634, //(11) 5081.3601
						"04583911",
						"Casa", 
						"É uma das 39 afiliadas da Make-A-Wish® International, uma das instituições de apoio à criança mais conhecidas e respeitadas no mundo.", 
						"Cidadania", 
						"enquiries@makeawish.org.au", "FazUmaBoaAcaoRapa123455", 
						"10.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco Itaú",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Sul",
						LocalDate.parse("2023-01-30")
				);
		ong13.setEndereco("R. Álvaro Rodrigues, 152");
		log.info("Preloading " + repository.save(ong13));
		
		Ong ong14 = new Ong
				(
						"Associação Crescer Sempre", 
						1120129634, //(11) 3744-8573
						"05660000",
						"Casa", 
						"Somos uma Associação sem fins lucrativos localizada na comunidade de Paraisópolis, em São Paulo, com cinco projetos principais: uma escola regular de Educação Infantil e de Ensino Fundamental II, uma escola integral de Ensino Médio; Cursos Profissionalizantes e Biblioteca aberta à comunidade.", 
						"Cidadania", 
						"contato@contato.org", "FazUmaBoaAcaoRapa123455", 
						"20.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco do Brasil",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Sul",
						LocalDate.parse("2023-05-07")
				);
		ong14.setEndereco("Rua Pasquale Gallupi, 928");
		log.info("Preloading " + repository.save(ong14));
		
		Ong ong15 = new Ong
				(
						"APOIO – Associação de Auxílio Mútuo da Região Leste", 
						1120129634, //55 (11) 3361-5900
						"01214100",
						"Casa", 
						"APOIO – Associação de Auxílio Mútuo da Região Leste iniciou suas atividades em 1992  e foi fundada em 18/12/1993, organização civil sem fins lucrativos, reconhecida de Utilidade Pública Federal, resultou de um grupo formado por pessoas de vários segmentos sociais preocupados com a pobreza de amplas camadas populares. O contato permanente com famílias de baixa renda impulsionou a organização a participar em várias atividades de apoio às pessoas em situação de vulnerabilidade social.", 
						"Cidadania", 
						"apoio@apoio-sp.org.br", "FazUmaBoaAcaoRapa123455", 
						"30.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Caixa",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-05-09")
				);
		ong15.setEndereco("Av. Duque de Caxias, 325");
		log.info("Preloading " + repository.save(ong15));
		
		Ong ong16 = new Ong
				(
						"ONG Zoé - Saúde para quem cuida da Floresta Amazônica", 
						1120129634, //55(11)3231-0800
						"01308000",
						"Casa", 
						"A ONG ZOÉ é uma associação sem fins lucrativos que apoia populações amazônicas com acesso limitado aos cuidados com a saúde.", 
						"Cidadania", 
						"contato@ongzoe.org", "FazUmaBoaAcaoRapa123455", 
						"40.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Nubank",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-05-17")
				);
		ong16.setEndereco("R. Barata Ribeiro, 414");
		log.info("Preloading " + repository.save(ong16));
		
		Ong ong17 = new Ong
				(
						"Pela Vidda SP", 
						1120129634, //(11) 3259-2149
						"01223010",
						"Casa", 
						"O Grupo Pela Vidda/SP (Valorização, Integração e Dignidade do Doente de aids) é uma ONG sem fins lucrativos voltada a pessoas vivendo e convivendo com HIV/AIDS independente de gênero, orientação sexual, orientação político-partidária ou religiosa.", 
						"Saúde", 
						"gpvsp@uol.com.br", "FazUmaBoaAcaoRapa123455", 
						"50.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco Pam",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-02-01")
				);
		ong17.setEndereco("Rua Gen. Jardim, 566");
		log.info("Preloading " + repository.save(ong17));
		
		Ong ong18 = new Ong
				(
						"Grupo da Sopa / ONG Um Ato de Amor", 
						1120129634, //(11) 96332-4165
						"03110010",
						"Casa", 
						"O Grupo da Sopa foi criado em 1996, por iniciativa de um grupo de amigos que resolveu dedicar algumas horas semanais para ajudar o próximo. Durante os dois primeiros anos, esses amigos preparavam sopa para distribuir nas ruas de São Paulo.", 
						"Cidadania", 
						"contato@grupodasopa.org", "FazUmaBoaAcaoRapa123455", 
						"60.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco Original",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-03-18")
				);
		ong18.setEndereco("R. Borges de Figueiredo, 86");
		log.info("Preloading " + repository.save(ong18));
		
		Ong ong19 = new Ong
				(
						"ONG Desejando o Bem", 
						1120129634, //+55 11 2594-5268
						"04763060",
						"Casa", 
						"O desejo de ajudar crianças carentes surgiu em 2014, quando a idealizadora Simone e sua filha Bia conheceram a história de uma família que acabara de dar as boa-vindas a trigêmeas e que necessitava de enxoval não só para uma, mas para três meninas!", 
						"Cidadania", 
						"atendimento@desejandoobem.com.br", "FazUmaBoaAcaoRapa123455", 
						"80.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Banco inter",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Leste",
						LocalDate.parse("2023-05-22")
				);
		ong19.setEndereco("R. Amaro Leite, 88");
		log.info("Preloading " + repository.save(ong19));
		
		Ong ong20 = new Ong
				(
						"ONG Brasil Eco Planetário", 
						1120129634, //(11) 99970-1819
						"04776100",
						"Casa", 
						"Trabalhar pela melhoria da relação entre o ser humano, sociedade e a natureza e a promoção da cultura da paz.", 
						"Meio Ambiente", 
						"contato@contato.com.br", "FazUmaBoaAcaoRapa123455", 
						"91.111.124/1102-00", 
						"9430-8|00", 
						"112123", 
						"9901", 
						"Caixa",
						"contatoPix@cuidademim.com.br", 
						"991.001.182-71",
						"Zona Sul",
						LocalDate.parse("2023-01-02")
				);
		ong20.setEndereco("Av. Engenheiro José Salles, 333");
		log.info("Preloading " + repository.save(ong20));
		
		};
		
		
		
	}
	
	@Autowired
	MantemUserRepository UserRepository;
		
	@Bean
	CommandLineRunner initDatabaseUser(MantemUserRepository repository, MantemUser repoCliente) {
		return args -> {
		repository.deleteAll();
		
		List<Long> user1Favoritos = new ArrayList<>();
		User user1 = new User("Diogo", "Lima","DiogoLima50@gmail.com", "12345", LocalDate.parse("2023-05-20"),user1Favoritos) ;
		log.info("Preloading " + repository.save(user1));
		
		List<Long> user2Favoritos = new ArrayList<>(Arrays.asList((long) 1, (long) 5));
		
		User user2 = new User("Bianca", "Jesus","biancaJesus299@gmail.com", "98765*A", LocalDate.parse("2023-05-21"),user2Favoritos) ;
		log.info("Preloading " + repository.save(user2));
				
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
