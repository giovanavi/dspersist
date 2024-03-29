package com.lista7.dao;

import com.lista7.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

    Funcionario save(Funcionario entity);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    List<Funcionario> findAll();

    @Query(name = "findByCpf")
    Funcionario findByCpf(@Param("cpf") String cpf);
    
    @Query("select f from Funcionario f where f.matricula = :matricula")
    Funcionario findByMatricula(@Param("matricula") String matricula);

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

}
