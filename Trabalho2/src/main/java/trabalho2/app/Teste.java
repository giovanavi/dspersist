package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoDAO;
import trabalho2.dao.DisciplinaDAO;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;

import javax.swing.*;
import java.text.ParseException;
import java.util.List;


@ComponentScan("trabalho2")
public class Teste implements CommandLineRunner {
    @Autowired
    private DisciplinaDAO disciplinaDAO;
    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    @Transactional
    public void run(String... args) throws ParseException {

//        Aluno a1 = new Aluno();
//        a1.setNome("Joao");a1.setEmail("joao@gmail.com");a1.setCpf("12345678990");a1.setDataNascimento("12/12/2012");a1.setMatricula("123456");
//        a1 = alunoDAO.save(a1);
//
//        Aluno a2 = new Aluno();
//        a2.setNome("Maria");a2.setEmail("maria@gmail.com");a2.setCpf("09876543212");a2.setDataNascimento("10/10/2010");a2.setMatricula("654321");
//        a2 = alunoDAO.save(a2);
//
//        List<Aluno> alunos = new ArrayList<>();
//        alunos.add(a1);
//        alunos.add(a2);
//
//        Disciplina d = new Disciplina();
//        d.setCodigo("123");
//        d.setNome("Matematica");
//        d.setAlunos(alunos);
//
//        d = disciplinaDAO.save(d);
//        System.out.println("Disciplina "+ d.getNome()+" salva.");

//        Disciplina d = disciplinaDAO.findByCodigo("123");
//        System.out.println("Disciplina "+d.getNome());
//        for (Aluno aluno : d.getAlunos()) {
//            System.out.println("Aluno: "+aluno.getNome()+ " " );
//        }

//        Disciplina d2 = new Disciplina();
//        d2.setCodigo("456");
//        d2.setNome("Portugues");
//        d2 = disciplinaDAO.save(d2);

//        Aluno a1 = alunoDAO.findByMatricula("123456");
//        Aluno a2 = alunoDAO.findByMatricula("654321");


//        List<Aluno> lista = new ArrayList<>();
//        lista.add(a1);
//        lista.add(a2);


//        Disciplina dd = disciplinaDAO.findByCodigo("123");
//        dd.setAlunos(lista);
//        dd = disciplinaDAO.save(dd);
//
//        System.out.println("Aluno: "+ a1.getNome()+" matriculado em "+dd.getNome());
//        System.out.println("Aluno: "+ a2.getNome()+" matriculado em "+dd.getNome());

//        Disciplina d = disciplinaDAO.findByCodigo("123");
//        disciplinaDAO.delete(d);

//            Aluno a = alunoDAO.findByMatricula("123456")
//            alunoDAO.delete(a);


//        Aluno a1 = new Aluno();
//        a1.setNome("Joao");a1.setEmail("joao@gmail.com");a1.setCpf("12345678990");
//        a1.setDataNascimento(new SimpleDateFormat("20/12/2002"));a1.setMatricula("123456");
//        alunoDAO.save(a1);
//
//        List<Aluno> alunos = new ArrayList<>();
//        alunos.add(a1);
//
//        List<Disciplina> lista = new ArrayList<>();
//
//        Disciplina d1 = new Disciplina();
//        Disciplina d2 = new Disciplina();
//        Disciplina d3 = new Disciplina();
//
//        d1.setCodigo("123");d1.setNome("Matematica");d1.setAlunos(alunos);
//        disciplinaDAO.save(d1);
//        lista.add(d1);
//
//        d2.setCodigo("456");d2.setNome("Portugues");d2.setAlunos(alunos);
//        disciplinaDAO.save(d2);
//        lista.add(d2);
//
//        d3.setCodigo("789");d3.setNome("Ciencias");d3.setAlunos(alunos);
//        disciplinaDAO.save(d3);
//        lista.add(d3);
//
//        for (Disciplina d: lista) {
//            System.out.println("Disciplina: "+ d.getNome()+ "alunos: "+ d.getAlunos());
//        }

//        String nascimento = "10/12/2000";


//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate data = LocalDate.parse(nascimento, formato);
//        System.out.println(formato.format(data));

//        Aluno a = new Aluno();
//        a.setNome("joao");a.setCpf("09876543212");a.setMatricula("456");a.setEmail("joao@gmail.com");
//        a.setDataNascimento(data);
//        alunoDAO.save(a);
//        System.out.println(a.toString());

//        String nascimento1 = "10/12/2002";
//        DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate data1 = LocalDate.parse(nascimento1, formato1);
//
//        Aluno a1 = new Aluno();
//        a1.setNome("giovana");a1.setCpf("09812365487");a1.setMatricula("789");a1.setEmail("giovana@gmail.com");
//        a1.setDataNascimento(data1);
//        alunoDAO.save(a1);
//        System.out.println(a1.toString());
//
//        String nascimento2 = "13/12/2002";
//        DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate data2 = LocalDate.parse(nascimento2, formato2);
//
//        Aluno a2 = new Aluno();
//        a2.setNome("arthur");a2.setCpf("62626262626");a2.setMatricula("098");a2.setEmail("dsjbddds@gmail.com");
//        a2.setDataNascimento(data2);
//        alunoDAO.save(a2);
//        System.out.println(a2.toString());
//
//        String nascimento3 = "16/12/2002";
//        DateTimeFormatter formato3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate data3 = LocalDate.parse(nascimento3, formato3);
//
//        Aluno a3 = new Aluno();
//        a3.setNome("adriana");a3.setCpf("90990099099");a3.setMatricula("726");a3.setEmail("jojojo@gmail.com");
//        a3.setDataNascimento(data3);
//        alunoDAO.save(a3);
//        System.out.println(a3.toString());

//        String nascimento3 = "14/12/2002";
//        DateTimeFormatter formato3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate data3 = LocalDate.parse(nascimento3, formato3);
//        List<Aluno> lista = alunoDAO.findByDataNascimentoAfter(data3);
//
//        for (Aluno aluno : lista) {
//            System.out.println(aluno.toString());
//        }
//

//        System.out.println("----------FIND ALUNO AND QUANT DISCIPLINAS CURSADAS----------");
//        List<AlunosAndQuantDisciplinas> lista = alunoDAO.findAlunosAndQuantDisciplinas();
//        System.out.println("Lista: "+ lista);
//        for (AlunosAndQuantDisciplinas aluno: lista) {
//            System.out.println("Nome: "+aluno.getNome()+" cursa "+aluno.getQuant()+" disciplinas.");
//        }
//
//        System.out.println("----------FIND ALUNO AND EMAIL----------");
//        Aluno a = alunoDAO.findNomeAndEmail("098");
//        System.out.println("Aluno: "+ a);
//        System.out.println("Nome: "+ a.getNome()+" email: "+a.getEmail());

//        System.out.println("----------FIND ALUNO AND DISCIPLINAS----------");
//        List<Aluno> lista = alunoDAO.findAlunoAndDisciplinasByNomeContaining("a");
//        System.out.println("Lista: "+lista);
//        for (Aluno aluno : lista) {
//            System.out.println("Nome: "+aluno.getNome()+" disciplinas: "+aluno.getDisciplinas());
//        }

    }

}