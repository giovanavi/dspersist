package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoDAO;
import trabalho2.dao.DisciplinaDAO;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


//3-Crie uma classe para adicionar via DAO pelo menos 6 alunos com suas respectivas disciplinas cursadas no
//        banco de dados. Pelo menos 50% dos alunos devem ter disciplinas cursadas.

@ComponentScan("trabalho2")
public class InserirAlunos implements CommandLineRunner{
    @Autowired
    private DisciplinaDAO disciplinaDAO;
    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    @Transactional
    public void run(String... args){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Disciplina portugues = new Disciplina();
        portugues.getId();

        Disciplina ciencias = new Disciplina();
        ciencias.getId();

        Set<Disciplina> lista1 = new HashSet<>();
        Aluno a1 = new Aluno();
        a1.setCpf("11111111111");
        a1.setMatricula("111111");
        a1.setNome("João Miguel");
        a1.setEmail("joao@gmail.com");
        a1.setDataNascimento(LocalDate.parse("10/12/2003", dateFormatter));
        lista1.add(portugues);
        lista1.add(ciencias);
        a1.setDisciplinas(lista1);
        alunoDAO.save(a1);

        Set<Disciplina> lista2 = new HashSet<>();
        Aluno a2 = new Aluno();
        a2.setCpf("22222222222");
        a2.setMatricula("222222");
        a2.setNome("Giovana Vieira");
        a2.setEmail("giovana@gmail.com");
        a2.setDataNascimento(LocalDate.parse("02/02/200", dateFormatter));
//        a2.setDisciplinas(lista1);
//        alunoDAO.save(a2);


        Set<Disciplina> lista3 = new HashSet<>();
        Aluno a3 = new Aluno();
        a3.setCpf("33333333333");
        a3.setMatricula("333333");
        a3.setNome("Maria Eduarda");
        a3.setEmail("maria@gmail.com");
        a3.setDataNascimento(LocalDate.parse("03/12/2001", dateFormatter));
//        a3.setDisciplinas(lista3);
//        alunoDAO.save(a3);

        Set<Disciplina> lista4 = new HashSet<>();
        Aluno a4 = new Aluno();
        a4.setCpf("44444444444");
        a4.setMatricula("444444");
        a4.setNome("José Humberto");
        a4.setEmail("jose@gmail.com");
        a4.setDataNascimento(LocalDate.parse("22/06/2000", dateFormatter));
//        a4.setDisciplinas(lista4);
//        alunoDAO.save(a4);

        Set<Disciplina> lista5 = new HashSet<>();
        Aluno a5 = new Aluno();
        a5.setCpf("55555555555");
        a5.setMatricula("555555");
        a5.setNome("Antônia Adriana");
        a5.setEmail("adriana@gmail.com");
        a5.setDataNascimento(LocalDate.parse("20/05/1999", dateFormatter));
//        a5.setDisciplinas(lista5);
//        alunoDAO.save(a5);

        Set<Disciplina> lista6 = new HashSet<>();
        Aluno a6 = new Aluno();
        a6.setCpf("66666666666");
        a6.setMatricula("666666");
        a6.setNome("Arthur Vieira");
        a6.setEmail("arthur@gmail.com");
        a6.setDataNascimento(LocalDate.parse("24/01/1998", dateFormatter));
//        a6.setDisciplinas(lista6);
//        alunoDAO.save(a6);

    }

}
