package dspersist;

import dspersist.dao.FuncionarioDAO;
import dspersist.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class MainFuncionario implements CommandLineRunner {
    @Autowired
    FuncionarioDAO funcionario;
    
    public static void main(String[]args) {
        //SpringApplication.run(MainFuncionario.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainFuncionario.class);
        builder.headless(false).run();
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Funcionario f = new Funcionario();
            String op = menu();
            if(op==null){
                break;
            } else if(op.isEmpty()){
                continue;
            }
            if(op.equals("1")) {//CADASTRAR
                setFuncionario(f);
                funcionario.create(f);
            } else if (op.equals("2")) {//UPDATE BY CPF
                String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionario a ser atualizado:");
                f = funcionario.findByCpf(cpf);
                setFuncionario(f);
                funcionario.update(f);
            } else if (op.equals("3")) {//UPDATE BY MATRICULA
                String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionario a ser atualizado:");
                f = funcionario.findByMatricula(matricula);
                setFuncionario(f);
                funcionario.update(f);
            } else if (op.equals("4")) {//LISTAR TUDO
                List<Funcionario> lista = funcionario.findAll();
                listFuncionarios(lista);
            } else if (op.equals("5")) {//LISTAR POR NOME
                String name = JOptionPane.showInputDialog("Digite o nome do funcionário:");
                List<Funcionario> lista = funcionario.findByName(name);
                listFuncionarios(lista);
            } else if (op.equals("6")) {//LISTAR POR CPF
                String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionário:");
                f = funcionario.findByCpf(cpf);
                listFuncionario(f);
            } else if (op.equals("7")) {//LISTAR POR MATRICULA
                String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionário:");
                f = funcionario.findByMatricula(matricula);
                listFuncionario(f);
            } else if (op.equals("8")) {//DELETE POR CPF
                String cpf = JOptionPane.showInputDialog("Digite o cpf do funcionario a ser excluído:");
                f = funcionario.findByCpf(cpf);
                funcionario.deleteByCpf(f.getCpf());
            } else if (op.equals("9")) {//DELETE POR MATRICULA
                String matricula = JOptionPane.showInputDialog("Digite a matricula do funcionario a ser excluído:");
                f = funcionario.findByMatricula(matricula);
                funcionario.deleteByMatricula(f.getMatricula());
            } else if (op.equals("10")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Comando inválido.");
            }
        }
    }

    public static String menu(){
        String op = JOptionPane.showInputDialog("Selecione uma opção:\n1 - Cadastrar" +
                "\n2 - Atualizar cadastro por cpf\n3 - Atualizar cadastro por matricula" +
                "\n4 - Listar funcionarios\n5 - Buscar funcionario por nome" +
                "\n6 - Buscar funcionario por cpf\n7 - Buscar funcioanrio por matricula" +
                "\n8 - Excluir cadastro por cpf\n9 - Excluir cadastro por matricula" +
                "\n10 - Fechar menu");
        return op;
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
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
        }else {
            JOptionPane.showMessageDialog(null, f);
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
