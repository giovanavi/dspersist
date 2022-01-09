package trabalho2.dao;

import org.springframework.stereotype.Repository;
import trabalho2.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

    public Aluno save(Aluno entity);

    public List<Aluno> findAll();

    public void deleteById(Integer id);

    public Aluno findByMatricula(String matricula);

    @Query("select a from Aluno a where a.id = :id")
    public Aluno findAlunoId(@Param("id") Integer id);

    public List<Aluno> findByNomeContaining(String nome);

    public List<Aluno> findAlunoAndDisciplinasByNomeContaining(String nome);

    public List<Aluno> findByDataNascimentoAfter(LocalDate data);

    @Query(name = "Aluno.findNomeAndEmail")
    public Aluno findNomeAndEmail(@Param("matricula") String matricula);

    @Query( value = "SELECT a.nome, count(ad.disciplina_fk) as quant " +
            "FROM alunos a, alunos_disciplinas ad " +
            "WHERE a.aluno_id = ad.aluno_fk GROUP BY a.nome", nativeQuery = true)
    public List<AlunosAndQuantDisciplinas> findAlunosAndQuantDisciplinas();

}
