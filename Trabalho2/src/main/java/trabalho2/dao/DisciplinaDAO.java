package trabalho2.dao;

import org.springframework.stereotype.Repository;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {

    public Disciplina findByCodigo(String codigo);

    public List<Disciplina> findByNomeContainingIgnoreCase(String nome);

    public Disciplina findDisciplinaAndAlunosByCodigo(String codigo);

}
