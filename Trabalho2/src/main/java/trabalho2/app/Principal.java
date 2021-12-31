package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import trabalho2.dao.AlunoDAO;
import trabalho2.dao.DisciplinaDAO;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;

import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.*;
import java.util.Scanner;

@SpringBootApplication
@EntityScan("trabalho2.entity")
@EnableJpaRepositories("trabalho2.dao")
public class Principal {
    @Autowired
    private AlunoDAO alunoDAO;
    @Autowired
    private DisciplinaDAO disciplinaDAO;

    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(AlunoCRUD.class);
//        builder.headless(false).run();

        SpringApplicationBuilder builder = new SpringApplicationBuilder(DisciplinaCRUD.class);
        builder.headless(false).run();

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(InserirAlunos.class);
//        builder.headless(false).run();

//        label:
//        while (true) {
//            Disciplina d = new Disciplina();
//            String op = menu();
//            if (op == null) {
//                break;
//            } else if (op.isEmpty()) {
//                continue;
//            }
//            switch (op) {
//                case "1": { //Menu Alunos
//                    SpringApplicationBuilder builder = new SpringApplicationBuilder(AlunoCRUD.class);
//                    builder.headless(false).run();
//                    break;
//                }
//                case "2": {//Menu Disciplinas
//                    SpringApplicationBuilder builder = new SpringApplicationBuilder(DisciplinaCRUD.class);
//                    builder.headless(false).run();
//                    break;
//                }
//                case "3": //Fecha menu principal
//                    break label;
//                default:
//                    JOptionPane.showMessageDialog(null, "Comando inválido.");
//                    break;
//            }
//        }

                //        while(true){
//            if(sc.nextLine().equals("inserir")){
//                System.out.println("Inserir Alunos");
//                SpringApplicationBuilder builder = new SpringApplicationBuilder(InserirAlunos.class);
//                builder.headless(false).run();
////                SpringApplication.run(InserirAlunos.class, args);
//            }else if (sc.nextLine().equals("aluno")){
//                System.out.println("Aluno CRUD");
//                SpringApplicationBuilder builder = new SpringApplicationBuilder(AlunoCRUD.class);
//                builder.headless(false).run();
////                SpringApplication.run(AlunoCRUD.class, args);
//            }else if (sc.nextLine().equals("disciplina")){
//                System.out.println("Disciplina CRUD");
//                SpringApplicationBuilder builder = new SpringApplicationBuilder(DisciplinaCRUD.class);
//                builder.headless(false).run()
//                ;
////                SpringApplication.run(DisciplinaCRUD.class, args);
//            }else{
//               break;
//            }
//        }
    }
    public static String menu(){
        return JOptionPane.showInputDialog("""
                Selecione uma opção:
                1 - Alunos
                2 - Disciplinas
                3 - Fechar menu""");
    }
}
