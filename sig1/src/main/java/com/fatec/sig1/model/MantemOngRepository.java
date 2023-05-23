package com.fatec.sig1.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
/**
 * Esta classe abstrai a programação de consultas para acesso ao banco de dados.
 * O nome dos metodos para consulta têm duas partes separadas pela palavra-chave
 * By. A primeira parte é o “find” seguido de By. A segunda parte é o nome do
 * atributo na tabela por exemplo Cpf - findByCpf
 * 
 * @author
 */

@Repository
public interface MantemOngRepository extends JpaRepository<Ong, Long> {
    
    Optional<Ong> findByCnpj(String cnpj);

    List<Ong> findAllByNomeIgnoreCaseContaining(String nome);

    Optional<Ong> findBySegmento(String segmento);
  
    Optional<Ong> findByEmail(String email);
    
    Optional<Ong> findBySenha(String senha);

    // ----------------------------------------------------- PARA RELATÓRIO -----------------------------------------------------

    //SELECT count(id)  as ONG_DA_ZONA_NORTE FROM ONG  where REGIAO = 'Zona Norte' 
    // SELECT t1.ONG_ZONA_NORTE, t2.ONG_CENTRO from (select count(id) as ONG_ZONA_NORTE from ong where REGIAO = 'Zona Norte' ) as t1, (select count(id) as ONG_CENTRO from ong where REGIAO = 'Centro' ) as t2
    //@Query(value = "SELECT count(*) as ONG_DA_ZONA_NORTE FROM ONG  where REGIAO = 'Zona Norte'", nativeQuery = true)
    Long countByRegiao(String regiao);

    long count();

    Long countBySegmento(String segmento);
    
    String teste = "SELECT count(*) FROM ONG where data_cadastro like '2023-05-%';";
	@Query(value = teste, nativeQuery = true)
	public int getCadastroMes(@Param("month")String month);
    
    // ----------------------------------------------------- PARA Favoritos  -----------------------------------------------------
    
	@Query(value = "select * from ONG WHERE id IN (:numbers);", nativeQuery = true)
	public List<Ong> getOngFavoritos(List<Long> numbers);
}
