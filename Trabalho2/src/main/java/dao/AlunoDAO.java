package dao;

import entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

    public Aluno save(Aluno entity);

    public List<Aluno> findAll();

    public Optional<Aluno> findById(Integer id);

    //Dada uma matrícula, mostrar o nome e email do aluno.
    @Query("select a.nome, a.email from Aluno a where a.matricula = :matricula")
    public Aluno findByMatricula(@Param("matricula") String matricula);

    //Dada uma data, mostrar somente os alunos que nasceram depois dela.
    public List<Aluno> findByDataNascimentoAfter(Date data);

    //Mostrar os nomes de todos os alunos com suas respectivas quantidades de disciplinas cursadas.


    public void deleteById(Integer id);

    //Mostrar os nomes dos alunos e todas as suas disciplinas cursadas somente para alunos com nomes contendo
    //determinada string. Ou seja: a string de busca deve ser um parâmetro nomeado da consulta.
    @Query("select a.nome, a.disciplinas from Aluno a where a.nome like :nome")
    public List<Aluno> findAlunoAndDiciplinasByNome(@Param("nome") String nome);


}
