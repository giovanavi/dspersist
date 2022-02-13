package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.app.AlunoCRUD;
import trabalho2.app.DisciplinaCRUD;

import javax.swing.*;

@SpringBootApplication
@EntityScan("trabalho2.entity")
@EnableJpaRepositories("trabalho2.dao")
public class Principal {

    public static void main(String[] args) {

        label:
        while (true) {
            String op = menu();
            if (op == null) {
                break;
            } else if (op.isEmpty()) {
                continue;
            }
            switch (op) {
                case "1": { //Menu Alunos
                    SpringApplicationBuilder builder = new SpringApplicationBuilder(AlunoCRUD.class);
                    builder.headless(false).run();
                    break;
                }
                case "2": {//Menu Disciplinas
                    SpringApplicationBuilder builder = new SpringApplicationBuilder(DisciplinaCRUD.class);
                    builder.headless(false).run();
                    break;
                }
                case "3": {//Classe Adicionar Alunos
                    SpringApplicationBuilder builder = new SpringApplicationBuilder(InserirAlunos.class);
                    builder.headless(false).run();
                    break;
                }
                case "4": //Fecha menu principal
                    break label;
                default:
                    JOptionPane.showMessageDialog(null, "Comando inválido.");
                    break;
            }
        }

    }

    public static String menu(){
        return JOptionPane.showInputDialog("""
                Selecione uma opção:
                1 - Menu Alunos
                2 - Menu Disciplinas
                3 - Classe Adicionar Alunos
                4 - Fechar menu""");
    }

}
