package com.fatec.sig1.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
