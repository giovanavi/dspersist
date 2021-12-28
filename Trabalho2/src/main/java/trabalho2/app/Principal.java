package trabalho2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EntityScan("trabalho2.entity")
@EnableJpaRepositories("trabalho2.dao")
public class Principal {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(AlunoCRUD.class);
//        builder.headless(false).run();

        SpringApplicationBuilder builder = new SpringApplicationBuilder(DisciplinaCRUD.class);
        builder.headless(false).run();


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
}
