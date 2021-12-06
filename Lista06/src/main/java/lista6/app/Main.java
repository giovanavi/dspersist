package lista6.app;

import lista6.dao.FuncionarioJPADAONamedQuery;
import lista6.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import javax.swing.*;
import java.util.List;

@SpringBootApplication
@ComponentScan("lista6")
public class Main implements CommandLineRunner {
    @Autowired
    FuncionarioJPADAONamedQuery funcionario;

    public static void main(String[]args) {
        //SpringApplication.run(MainFuncionario.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        builder.headless(false).run();
    }

    @Override
    public void run(String... args) throws Exception {
        label:
        while (true) {
            Funcionario f = new Funcionario();
            String op = menu();
            if(op==null){
                break;
            } else if(op.isEmpty()){
                continue;
            }
            switch (op) {
                case "1": //CADASTRAR
                    setFuncionario(f);
                    funcionario.create(f);
                    break;
                case "2": {//UPDATE BY CPF
                    String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionário a ser atualizado:");
                    f = funcionario.findByCpf(cpf);
                    if(find(f)) {
                        setFuncionario(f);
                        funcionario.update(f);
                    }
                    break;
                }
                case "3": {//UPDATE BY MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionário a ser atualizado:");
                    f = funcionario.findByMatricula(matricula);
                    if(find(f)) {
                        setFuncionario(f);
                        funcionario.update(f);
                    }
                    break;
                }
                case "4": {//LISTAR TUDO
                    List<Funcionario> lista = funcionario.findAll();
                    listFuncionarios(lista);
                    break;
                }
                case "5": {//LISTAR POR NOME
                    String name = JOptionPane.showInputDialog("Digite o nome do funcionário:");
                    List<Funcionario> lista = funcionario.findByName(name);
                    listFuncionarios(lista);
                    break;
                }
                case "6": {//LISTAR POR CPF
                    String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionário:");
                    f = funcionario.findByCpf(cpf);
                    listFuncionario(f);
                    break;
                }
                case "7": {//LISTAR POR MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionário:");
                    f = funcionario.findByMatricula(matricula);
                    listFuncionario(f);
                    break;
                }
                case "8": {//DELETE POR CPF
                    String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionário a ser excluído:");
                    f = funcionario.findByCpf(cpf);
                    if(find(f))
                        funcionario.deleteByCpf(f.getCpf());
                    break;
                }
                case "9": {//DELETE POR MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionário a ser excluído:");
                    f = funcionario.findByMatricula(matricula);
                    if(find(f))
                        funcionario.deleteByMatricula(f.getMatricula());
                    break;
                }
                case "10":
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
                1 - Cadastrar
                2 - Atualizar cadastro por cpf
                3 - Atualizar cadastro por matricula
                4 - Listar funcionários
                5 - Buscar funcionário por nome
                6 - Buscar funcionário por cpf
                7 - Buscar funcionário por matricula
                8 - Excluir cadastro por cpf
                9 - Excluir cadastro por matricula
                10 - Fechar menu""");
    }

    public static boolean find(Funcionario f) {
        if (f == null) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
            return false;
        }else{
            return true;
        }
    }

    public static void listFuncionarios(List<Funcionario> lista){
        StringBuilder sb = new StringBuilder();
        for (Funcionario f : lista) {
            sb.append(f.toString());
            sb.append("\n");
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum funcionário encontrado.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listFuncionario(Funcionario f){
        if(f == null){
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
        }else{
            JOptionPane.showMessageDialog(null, f.toString());
        }
    }

    public static void setFuncionario(Funcionario f){
        String nome = JOptionPane.showInputDialog("Nome", f.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", f.getCpf());
        String matricula = JOptionPane.showInputDialog("Matricula", f.getMatricula());
        String email = JOptionPane.showInputDialog("Email", f.getEmail());
        String fone = JOptionPane.showInputDialog("Telefone", f.getTelefone());
        f.setNome(nome);
        f.setCpf(cpf);
        f.setMatricula(matricula);
        f.setEmail(email);
        f.setTelefone(fone);
    }

}
