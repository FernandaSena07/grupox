package com.fatec.sig1.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fatec.sig1.model.Ong;
import com.fatec.sig1.model.MantemOngRepository;
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

		logger.info(">>>>>> servico consultaPorCpf chamado");

		return repository.findByCnpj(cnpj);

	}

	@Override

	public Optional<Ong> consultaPorId(Long id) {

		logger.info(">>>>>> servico consultaPorId chamado");

		return repository.findById(id);

	}

	@Override

	public Optional<Ong> save(Ong ong) {

		logger.info(">>>>>> servico save chamado ");

		Endereco endereco = obtemEndereco(ong.getCep());

		ong.setEndereco(endereco.getLogradouro());

		return Optional.ofNullable(repository.save(ong));

	}

	@Override

	public void delete(Long id) {

		logger.info(">>>>>> servico delete por id chamado");

		repository.deleteById(id);

	}

	@Override

	public Optional<Ong> atualiza(Long id, Ong ong) {

		logger.info(">>>>>> 1.servico atualiza informações de cliente chamado");

		Endereco endereco = obtemEndereco(ong.getCep());

		Ong ongModificado = new Ong(ong.getNome(), ong.getTelefone(), ong.getCnpj(), ong.getDescricao(), ong.getSegmento(), 
				ong.getCep(), ong.getComplemento(), null, null);
		
		ongModificado.setId(id);

		ongModificado.setEndereco(endereco.getLogradouro());

		logger.info(">>>>>> 2. servico atualiza informacoes de cliente cep valido para o id => "
				+ ongModificado.getId());

		return Optional.ofNullable(repository.save(ongModificado));

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
			/** Verificar se é correto manter dessa  forma, pois a referencia acima é de client. 
			**/

		} catch (HttpClientErrorException e) {

			logger.info(">>>>>> consulta CEP inválido erro HttpClientErrorException =>" + e.getMessage());

			return null;

		}

	}

}
