package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoDAO;
import trabalho2.dao.AlunosAndQuantDisciplinas;
import trabalho2.dao.DisciplinaDAO;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Crie uma classe para adicionar via DAO pelo menos 6 alunos com suas respectivas disciplinas cursadas no banco de
//        dados. Pelo menos 50% dos alunos devem ter disciplinas cursadas.

//Crie uma classe para inserir, atualizar e deletar alunos e disciplinas separadamente. A classe deve permitir também
//        adicionar disciplinas cadastradas a um determinado aluno também já cadastrado.

@ComponentScan("trabalho2")
public class AlunoCRUD implements CommandLineRunner {
    @Autowired
    private AlunoDAO alunoDAO;
    @Autowired
    private DisciplinaDAO disciplinaDAO;


    @Transactional
    public void run(String... args) {
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
                case "2": {//MATRICULAR ALUNO EM DISCIPLINAS - OK
                    String matricula = JOptionPane.showInputDialog("Digite o código da disciplina para matricular aluno");
                    a = alunoDAO.findByMatricula(matricula);
                    if(find(a)){
                        Set<Disciplina> disciplinas = matricular();
                        a.getDisciplinas().addAll(disciplinas);
                        alunoDAO.save(a);
                    }
                    break;
                }
                case "3": {//UPDATE POR MATRICULA - OK
                    String matricula = JOptionPane.showInputDialog(null, "Digite a matricula do " +
                            "aluno a ser alterado:");
                    a = alunoDAO.findByMatricula(matricula);
                    if (find(a)) {
                        setAluno(a);
                        alunoDAO.save(a);
                    }
                    break;
                }
                case "4": {//LISTAR ALUNOS - OK
                    List<Aluno> lista = alunoDAO.findAll();
                    listAlunos(lista);
                    break;
                }
                case "5": {//LISTAR POR NOME - OK
                    String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
                    List<Aluno> lista = alunoDAO.findByNomeContaining(nome);
                    listAlunos(lista);
                    break;
                }
                case "6": {//LISTAR NOME E EMAIL POR MATRICULA -- FAZER
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    a = alunoDAO.findNomeAndEmail(matricula);
                    listNomeAndMatricula(a);
                    break ;
                }
                case "7":{//Listar alunos e a quantidade de disciplinas cursadas
                    List<AlunosAndQuantDisciplinas> lista = alunoDAO.findAlunosAndQuantDisciplinas();
                    listAlunosAndQuantDisciplinas(lista);
                    break ;
                }
                case "8":{//Buscar alunos que nasceram após a data -- FEITO JPA
                    String data = JOptionPane.showInputDialog("Digite a data:");
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataFormatada = LocalDate.parse(data, formato);
                    List<Aluno> lista = alunoDAO.findByDataNascimentoAfter(dataFormatada);
                    listAlunos(lista);
                    break ;
                }
                case "9":{//Listar alunos e suas respectivas disciplinas
                    String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
                    List<Aluno> lista = alunoDAO.findAlunoAndDisciplinasByNomeContaining(nome);
                    listDisciplinasCursadasPorAluno(lista);
                    break;
                }
                case "10":{//Excluir aluno por id
                    String id = JOptionPane.showInputDialog("Digite o id do aluno:");
                    a = alunoDAO.findAlunoId(Integer.parseInt(id));
                    if(find(a)) {
                        alunoDAO.delete(a);
//                        for (Disciplina disciplina : a.getDisciplinas()) {
//                            disciplina.getAlunos().remove(a);
//                        }
                    }
                    break;
                }
                case "11":{//Excluir aluno por matricula
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    a = alunoDAO.findByMatricula(matricula);
                    if(find(a)) {;
                        alunoDAO.delete(a);
//                        for (Disciplina disciplina : a.getDisciplinas()) {
//                            disciplina.getAlunos().remove(a);
//                        }
                    }
                    break;
                }
                case "12":
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
                2 - Matriculas aluno em disciplinas
                3 - Atualizar cadastro por matricula
                4 - Listar alunos
                5 - Buscar alunos por nome
                6 - Buscar aluno por matricula
                7 - Listar alunos e a quantidade de disciplinas cursadas
                8 - Buscar alunos que nasceram após a data
                9 - Listar alunos e suas respectivas disciplinas
                10 - Excluir aluno por id
                11 - Excluir aluno por matricula
                12 - Voltar para o menu principal""");
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
            if(!a.getDisciplinas().isEmpty()) {
                sb.append("Aluno(a): ").append(a.getNome()).append(" cursa {");
                for (Disciplina d: a.getDisciplinas()) {
                    sb.append("  ").append(d.getNome());
                }
                sb.append(" }");
            }else{
                sb.append("Aluno(a): ").append(a.getNome());
                sb.append(" não cursa nehuma disciplina");
            }
            sb.append(".\n");
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listAlunosAndQuantDisciplinas(List<AlunosAndQuantDisciplinas> lista){
        StringBuilder sb = new StringBuilder();
        for (AlunosAndQuantDisciplinas a : lista) {
            if (a.getQuant() > 0) {
                sb.append(a.getNome()).append(" está matriculado(a) em ").append(a.getQuant()).append("disciplina(s)");
                sb.append("\n");
            }else{
                sb.append(a.getNome()).append(" não está matriculado(a) em nenhuma disciplina");
                sb.append("\n");
            }
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

    public static void listNomeAndMatricula(Aluno aluno){
        if(find(aluno)){
                JOptionPane.showMessageDialog(null, "nome: " + aluno.getNome()
                        + " email:" + aluno.getEmail() );
        }
    }

    public static void listAluno(Aluno a){
        if(a == null){
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        }else{
            JOptionPane.showMessageDialog(null, a.toString());
        }
    }

    public static void setAluno(Aluno a){
        String nome = JOptionPane.showInputDialog("Nome", a.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", a.getCpf());
        String matricula = JOptionPane.showInputDialog("Matricula", a.getMatricula());
        String email = JOptionPane.showInputDialog("Email", a.getEmail());
        String data_nascimento = JOptionPane.showInputDialog("Data de nascimento", a.getDataNascimento());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data_nascimento, formato);

        a.setNome(nome);
        a.setCpf(cpf);
        a.setMatricula(matricula);
        a.setEmail(email);
        a.setDataNascimento(dataFormatada);
    }

    public Set<Disciplina> matricular(){
        boolean isTrue = true;
        Set<Disciplina> disciplinas = new HashSet<>();
        while(isTrue) {
            Disciplina disciplina;
            String codigo = JOptionPane.showInputDialog(null, "Digite o código da disciplina a ser matriculada. (Digite 0 para sair)");
            if(Integer.parseInt(codigo) > 0){
                disciplina = disciplinaDAO.findByCodigo(codigo);
                if(disciplina != null){
                    JOptionPane.showMessageDialog(null, "Disciplina: "+disciplina);
                    disciplinas.add(disciplina);
                }else{
                    JOptionPane.showMessageDialog(null,"Disciplina não encontrada.");
                }
            }else{
                isTrue = false;
            }
        }
        return disciplinas;

    }
}
