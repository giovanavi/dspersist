package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoRepository;
import trabalho2.dao.DisciplinaRepository;
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
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    @Transactional
    public void run(String... args){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        Disciplina portugues = new Disciplina();
        portugues.setId(1);

        Disciplina matematica = new Disciplina();
        matematica.setId(2);

        Disciplina historia = new Disciplina();
        historia.setId(3);

        Disciplina ciencias = new Disciplina();
        ciencias.setId(4);

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
        alunoRepository.save(a1);

        Set<Disciplina> lista2 = new HashSet<>();
        Aluno a2 = new Aluno();
        a2.setCpf("22222222222");
        a2.setMatricula("222222");
        a2.setNome("Giovana Vieira");
        a2.setEmail("giovana@gmail.com");
        a2.setDataNascimento(LocalDate.parse("02/02/2002", dateFormatter));
        lista2.add(portugues);
        lista2.add(matematica);
        a2.setDisciplinas(lista2);
        alunoRepository.save(a2);


        Set<Disciplina> lista3 = new HashSet<>();
        Aluno a3 = new Aluno();
        a3.setCpf("33333333333");
        a3.setMatricula("333333");
        a3.setNome("Maria Eduarda");
        a3.setEmail("maria@gmail.com");
        a3.setDataNascimento(LocalDate.parse("03/12/2001", dateFormatter));
        lista3.add(ciencias);
        lista3.add(historia);
        a3.setDisciplinas(lista3);
        alunoRepository.save(a3);

        Set<Disciplina> lista4 = new HashSet<>();
        Aluno a4 = new Aluno();
        a4.setCpf("44444444444");
        a4.setMatricula("444444");
        a4.setNome("José Humberto");
        a4.setEmail("jose@gmail.com");
        a4.setDataNascimento(LocalDate.parse("22/06/2000", dateFormatter));
        lista4.add(historia);
        lista4.add(matematica);
        a4.setDisciplinas(lista4);
        alunoRepository.save(a4);

        //NAO MATRICULADO EM NENHUMA DISCIPLINA
        Aluno a5 = new Aluno();
        a5.setCpf("55555555555");
        a5.setMatricula("555555");
        a5.setNome("Antônio Anderson");
        a5.setEmail("anderson@gmail.com");
        a5.setDataNascimento(LocalDate.parse("20/05/1999", dateFormatter));
        alunoRepository.save(a5);

        //NAO MATRICULADO EM NENHUMA DISCIPLINA
        Aluno a6 = new Aluno();
        a6.setCpf("66666666666");
        a6.setMatricula("666666");
        a6.setNome("Arthur Vieira");
        a6.setEmail("arthur@gmail.com");
        a6.setDataNascimento(LocalDate.parse("24/01/1998", dateFormatter));
        alunoRepository.save(a6);

    }

}
