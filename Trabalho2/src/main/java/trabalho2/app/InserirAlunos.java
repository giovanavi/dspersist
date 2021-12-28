package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import trabalho2.dao.AlunoDAO;
import trabalho2.entity.Aluno;

//3- Crie uma classe para adicionar via DAO pelo menos 6 alunos com suas respectivas disciplinas cursadas no
//        banco de dados. Pelo menos 50% dos alunos devem ter disciplinas cursadas.

public class InserirAlunos implements CommandLineRunner {
    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    public void run(String... args) throws Exception {
        Aluno a1 = new Aluno();

    }
}
