package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import trabalho2.dao.AlunoDAO;
import trabalho2.entity.Aluno;

import javax.swing.*;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

//Crie uma classe para adicionar via DAO pelo menos 6 alunos com suas respectivas disciplinas cursadas no banco de
//        dados. Pelo menos 50% dos alunos devem ter disciplinas cursadas.

//Crie uma classe para inserir, atualizar e deletar alunos e disciplinas separadamente. A classe deve permitir também
//        adicionar disciplinas cadastradas a um determinado aluno também já cadastrado.

@ComponentScan("trabalho2")
public class AlunoCRUD implements CommandLineRunner {
    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    public void run(String... args) throws Exception {
        label:
        while (true){
            Aluno a = new Aluno();
            String op = menu();
            if(op==null){
                break;
            } else if(op.isEmpty()){
                continue;
            }
            switch (op) {
                case "1": {//INSERIR ALUNO - OK
                    setAluno(a);
                    alunoDAO.save(a);
                    break;
                }
                case "2": {//UPDATE POR MATRICULA - OK
                    String matricula = JOptionPane.showInputDialog(null, "Digite a matricula do aluno a ser alterado:");
                    a = alunoDAO.findByMatricula(matricula);
                    if (find(a)) {
                        setAluno(a);
                        alunoDAO.save(a);
                    }
                    break;
                }
                case "3": {//LISTAR ALUNOS - OK
                    List<Aluno> lista = alunoDAO.findAll();
                    listAlunos(lista);
                    break;
                }
                case "4": {//LISTAR POR NOME - OK
                    String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
                    List<Aluno> lista = alunoDAO.findByNomeContainingIgnoreCase(nome);
                    listAlunos(lista);
                    break;
                }
                case "5": {//LISTAR NOME E EMAIL POR MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    String[] aluno = alunoDAO.findNomeByMatricula(matricula);
                    JOptionPane.showMessageDialog(null, aluno);
                    break ;
                }
                case "6":{//Listar alunos e a quantidade de disciplinas cursadas
                    List<Aluno> lista = alunoDAO.findAll();
                    listAlunosAndQuantDisciplinas(lista);
                    break ;
                }
                case "7":{//Buscar alunos que nasceram após a data OK
                    String data = JOptionPane.showInputDialog("Digite a data:");
                    List<Aluno> lista = alunoDAO.findByDataNascimentoAfter(data);
                    listAlunos(lista);
                    break ;
                }
                case "8":{//Listar alunos e suas respectivas disciplinas
                    List<Aluno> lista = alunoDAO.findAll();
                    listDisciplinasCursadasPorAluno(lista);
                }
                case "9":{//Excluir aluno por id
                    String id = JOptionPane.showInputDialog("Digite o id do aluno:");
//                    alunoDAO.deleteById(Integer.parseInt(id));
                    a = alunoDAO.findAlunoById(Integer.parseInt(id));
                    if(find(a)) {
                        alunoDAO.delete(a);
                    }
                    break;
                }
                case "10":{//Excluir aluno por matricula
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    a = alunoDAO.findByMatricula(matricula);
                    if(find(a)) {
                        alunoDAO.delete(a);
                    }
                    break;
                }
                case "11":
                    break label;
                default:
                    JOptionPane.showMessageDialog(null, "Comando inválido.");
                    break;
            }
        }

    }


    //ID, cpf, MATRICULA, nome, EMAIL, data_mascimento, disciplinas;

    public static String menu(){
        return JOptionPane.showInputDialog("""
                Selecione uma opção:
                1 - Inserir aluno
                2 - Atualizar cadastro por matricula
                3 - Listar alunos
                4 - Buscar alunos por nome
                5 - Buscar aluno por matricula
                6 - Listar alunos e a quantidade de disciplinas cursadas
                7 - Buscar alunos que nasceram após a data
                8 - Listar alunos e suas respectivas disciplinas
                9 - Excluir aluno por id
                10 - Excluir aluno por matricula
                11 - Fechar menu""");
    }


    public static boolean find(Aluno a) {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            return false;
        }else{
            return true;
        }
    }

//JOAOZINHO CURSA : XXXX, YYYYY, ZZZZZ;

    public static void listDisciplinasCursadasPorAluno(List<Aluno> lista){
        StringBuilder sb = new StringBuilder();
        for (Aluno a: lista) {
            sb.append(a.getNome()+" cursa: ");
            sb.append(a.getDisciplinasCursadas());
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum aluno cursa nenhuma disciplina no momento.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listAlunosAndQuantDisciplinas(List<Aluno> lista){
        StringBuilder sb = new StringBuilder();
        for (Aluno a : lista) {
            int quant = a.getQuantDisciplinasCursadas(a.getId());
            sb.append(a.getNome()+" está matriculado em "+quant+" diciplinas.");
            sb.append("\n");
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listAlunos(List<Aluno> lista){
        StringBuilder sb = new StringBuilder();
        for (Aluno a : lista) {
            sb.append(a.toString());
            sb.append("\n");
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listAluno(Aluno a){
        if(a == null){
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        }else{
            JOptionPane.showMessageDialog(null, a.toString());
        }
    }

    public static void setAluno(Aluno a) throws ParseException {
        String nome = JOptionPane.showInputDialog("Nome", a.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", a.getCpf());
        String matricula = JOptionPane.showInputDialog("Matricula", a.getMatricula());
        String email = JOptionPane.showInputDialog("Email", a.getEmail());
        String data_nascimento = JOptionPane.showInputDialog("Data de nascimento", a.getDataNascimento());
        a.setNome(nome);
        a.setCpf(cpf);
        a.setMatricula(matricula);
        a.setEmail(email);
        a.setDataNascimento(data_nascimento);
    }
}
