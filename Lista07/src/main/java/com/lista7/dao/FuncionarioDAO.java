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
// (create - update) - (deleteByCpf - deleteByMatricula)    <- OK
//  findByName - (findAll - findByCpf - findByMatricula)   <- OK

    Funcionario save(Funcionario entity);

    @Transactional
    @Modifying
    @Query(name = "removeById")
    void removeById(@Param("id") Integer id);

    List<Funcionario> findAll();

    Funcionario findByCpf(String cpf);
    
    @Query("select f from Funcionario f where f.matricula = :matricula")
    Funcionario findByMatricula(@Param("matricula") String matricula);

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);


//    void create(Funcionario f);
//    void update(Funcionario f);
//    void deleteByCpf(String cpf);
//    void deleteByMatricula(String matricula);
//    List<Funcionario> findAll();
//    List<Funcionario> findByName(String name);
//    Funcionario findByCpf(String cpf);
//    Funcionario findByMatricula(String matricula);

}
