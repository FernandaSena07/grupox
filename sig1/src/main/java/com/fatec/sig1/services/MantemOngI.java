package com.fatec.sig1.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.MantemOngRepository;
import com.fatec.sig1.model.Cnae;
import com.fatec.sig1.model.Endereco;

/**
 * 
 * A classe mantem cliente implementa o padrao Service. Servce eh um padrao que
 * basicamente encapsula
 * 
 * o processo de obtencao de serviços(objetos). O Service cria uma camada de
 * abstracao neste
 * 
 * processo. Ao inves da classe dependente instanciar suas dependencias
 * diretamente, eles são
 * 
 * solicitados a partir de um objeto centralizado que atua como localizador de
 * serviços.
 * 
 * @author
 *
 * 
 * 
 */

@Service

public class MantemOngI implements MantemOng {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MantemOngRepository repository;

	public List<Ong> consultaTodos() {

		logger.info(">>>>>> servico consultaTodos chamado");

		return repository.findAll();

	}

	@Override

	public Optional<Ong> consultaPorCnpj(String cnpj) {

		logger.info(">>>>>> servico consultaPorCnpj chamado");

		return repository.findByCnpj(cnpj);

	}

	@Override

	public Optional<Ong> consultaPorId(Long id) {

		logger.info(">>>>>> servico consultaPorId chamado");

		return repository.findById(id);

	}
	@Override

	public Optional<Ong> consultaPorEmail(String email) {

		logger.info(">>>>>> servico constultaPorEmail chamado");

		return repository.findByEmail(email);

	}

	@Override

	public Optional<Ong> save(Ong ong) {

		logger.info(">>>>>> servico save chamado ");

		if (ong.getCep() != null) {
			Endereco endereco = obtemEndereco(ong.getCep());

			ong.setEndereco(endereco.getLogradouro());
		}

		return Optional.ofNullable(repository.save(ong));

	}

	@Override

	public void delete(Long id) {

		logger.info(">>>>>> servico delete por id chamado");

		repository.deleteById(id);

	}

	@Override

	public Optional<Ong> atualiza(Long id, Ong ong) {

		logger.info(">>>>>> 1.servico atualiza informações da ong chamado");

		Endereco endereco = obtemEndereco(ong.getCep());

		// Colocar if para verificar quantas informações tem??
		// Dependendo de quantas tem chama outro construtor

		Ong ongModificado = new Ong(ong.getNome(), ong.getTelefone(), ong.getCep(), ong.getComplemento(),
				ong.getDescricao(), ong.getSegmento(), ong.getEmail(), ong.getSenha(), ong.getCnpj(), ong.getCnae(), 
				ong.getContaCorrente(),ong.getAgencia(), ong.getPix(), ong.getCpf(), ong.getRegiao());

		Ong ongGetId = this.repository.findById(id).get();

		ongModificado.setId(id);

		if (ongModificado.getCep() == null) {
			ongModificado.setEndereco(ongGetId.getEndereco());
		}else {			
			ongModificado.setEndereco(endereco.getLogradouro());
		}
		
		logger.info(
				">>>>>> 2. servico atualiza informacoes da ong cep valido para o id => " + ongModificado.getId());

		if (ongModificado.getNome() == null) {
			ongModificado.setNome(ongGetId.getNome());
		}

		if (ongModificado.getTelefone() == 0) {
			ongModificado.setTelefone(ongGetId.getTelefone());
		}

		if (ongModificado.getCnpj() == null) {
			ongModificado.setCnpj(ongGetId.getCnpj());
		}

		if (ongModificado.getCnae() == null) {
			ongModificado.setCnae(ongGetId.getCnae());
		}

		if (ongModificado.getComplemento() == null) {
			ongModificado.setComplemento(ongGetId.getComplemento());
		}

		if (ongModificado.getDescricao() == null) {
			ongModificado.setDescricao(ongGetId.getDescricao());
		}

		if (ongModificado.getSegmento() == null) {
			ongModificado.setSegmento(ongGetId.getSegmento());
		}

		if (ongModificado.getEmail() == null) {
			ongModificado.setEmail(ongGetId.getEmail());
		}

		if (ongModificado.getSenha() == null) {
			ongModificado.setSenha(ongGetId.getSenha());
		}

		if (ongModificado.getCep() == null) {
			ongModificado.setCep(ongGetId.getCep());
		}
		
		if (ongModificado.getContaCorrente() == null) {
			ongModificado.setContaCorrente(ongGetId.getContaCorrente());
		}
		
		if (ongModificado.getAgencia() == null) {
			ongModificado.setAgencia(ongGetId.getAgencia());
		}
		
		if (ongModificado.getPix() == null) {
			ongModificado.setPix(ongGetId.getPix());
		}
		
		if (ongModificado.getCpf() == null) {
			ongModificado.setCpf(ongGetId.getCpf());
		}
		
		if (ongModificado.getRegiao() == null) {
			ongModificado.setRegiao(ongGetId.getRegiao());
		}
		
		return Optional.ofNullable(repository.save(ongModificado));

	}

	public Cnae obtemCnae(String cnae) {
		RestTemplate template = new RestTemplate();

		String url = "https://servicodados.ibge.gov.br/api/v2/cnae/classes/{cnae}";
		logger.info("Consultar CNAE: " + cnae);
		ResponseEntity<Cnae> resposta = null;

		try {
			resposta = template.getForEntity(url, Cnae.class, cnae);
			return resposta.getBody();
		} catch (ResourceAccessException e) {
			logger.info(">>>>>> consulta CNAE erro nao esperado ");
			return null;
		} catch (HttpClientErrorException e) {
			logger.info(">>>>>> consulta CNAE inválido erro HttpClientErrorException =>" + e.getMessage());
			return null;
		}
	}

	public Endereco obtemEndereco(String cep) {

		RestTemplate template = new RestTemplate();

		String url = "https://viacep.com.br/ws/{cep}/json/";

		logger.info(">>>>>> servico consultaCep - " + cep);

		ResponseEntity<Endereco> resposta = null;

		try {

			resposta = template.getForEntity(url, Endereco.class, cep);

			return resposta.getBody();

		} catch (ResourceAccessException e) {

			logger.info(">>>>>> consulta CEP erro nao esperado ");

			return null;

		} catch (HttpClientErrorException e) {

			logger.info(">>>>>> consulta CEP inválido erro HttpClientErrorException =>" + e.getMessage());

			return null;

		}

	}

	@Override
	public Optional<Ong> findByEmail(String email) {
		logger.info(">>>>>> servico consulta Email chamado");

		return repository.findByEmail(email);
	}

	@Override
	public Optional<Ong> findBySenha(String senha) {
		logger.info(">>>>>> servico consulta Senha chamado");

		return repository.findBySenha(senha);
	}

}
