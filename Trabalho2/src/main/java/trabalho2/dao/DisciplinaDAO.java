package trabalho2.dao;

import org.springframework.stereotype.Repository;
import trabalho2.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {

    public List<Disciplina> findAll();

    public Disciplina findByCodigo(String codigo);

    public Disciplina findAlunosByCodigo(String codigo);

    public List<Disciplina> findByNomeContaining(String nome);
    //Dado um código de disciplina, mostrar todos os alunos que a cursaram.
//    @Query("select a.nome FROM Disciplina d, Aluno a, AD ad where d.codigo = :codigo AND ad.aluno_id = a.id")
    public List<Disciplina> findAlunosDisciplinasByCodigo(Integer codigo);

}
