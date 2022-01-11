package trabalho2.dao;

import org.springframework.stereotype.Repository;
import trabalho2.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    public Aluno findByMatricula(String matricula);

    @Query(name = "Aluno.findById")
    public Aluno findAlunoId(@Param("id") Integer id);

    public List<Aluno> findByNomeContainingIgnoreCase(String nome);

    public List<Aluno> findAlunoAndDisciplinasByNomeContainingIgnoreCase(String nome);

    public List<Aluno> findByDataNascimentoAfter(LocalDate data);

    public NomeAndEmailAluno findNomeAndEmailByMatricula(String matricula);

    @Query( value = "select a.nome, a.matricula, count(ad.disciplinas_id) as quant " +
            "from alunos a, alunos_disciplinas ad " +
            "where a.id = ad.alunos_id group by a.nome, a.matricula", nativeQuery = true)
    public List<AlunosAndQuantDisciplinas> findAlunosAndQuantDisciplinas();

}
