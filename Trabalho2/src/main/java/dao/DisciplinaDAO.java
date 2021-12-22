package dao;

import entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {


    //Dado um código de disciplina, mostrar todos os alunos que a cursaram.


}
