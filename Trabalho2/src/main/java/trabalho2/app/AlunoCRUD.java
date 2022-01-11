package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoRepository;
import trabalho2.dao.AlunosAndQuantDisciplinas;
import trabalho2.dao.DisciplinaRepository;
import trabalho2.dao.NomeAndEmailAluno;
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
    private AlunoRepository alunoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

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
                case "1": {//INSERIR ALUNO
                    setAluno(a);
                    alunoRepository.save(a);
                    break;
                }
                case "2": {//MATRICULAR ALUNO EM DISCIPLINAS
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno");
                    a = alunoRepository.findByMatricula(matricula);
                    if(find(a)){
                        Set<Disciplina> disciplinas = matricular();
                        if(a.getDisciplinas()==null)
                            a.setDisciplinas(disciplinas);
                        else
                            a.getDisciplinas().addAll(disciplinas);
                        alunoRepository.save(a);
                    }
                    break;
                }
                case "3": {//UPDATE POR MATRICULA
                    String matricula = JOptionPane.showInputDialog(null, "Digite a matricula do " +
                            "aluno a ser alterado:");
                    a = alunoRepository.findByMatricula(matricula);
                    if (find(a)) {
                        setAluno(a);
                        alunoRepository.save(a);
                    }
                    break;
                }
                case "4": {//LISTAR ALUNOS
                    List<Aluno> lista = alunoRepository.findAll();
                    listAlunos(lista);
                    break;
                }
                case "5": {//LISTAR POR NOME
                    String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
                    List<Aluno> lista = alunoRepository.findByNomeContainingIgnoreCase(nome);
                    listAlunos(lista);
                    break;
                }
                case "6": {//BUSCAR NOME E EMAIL POR MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    NomeAndEmailAluno aluno = alunoRepository.findNomeAndEmailByMatricula(matricula);
                    listNomeAndMatricula(aluno);
                    break ;
                }
                case "7": {//BUSCAR POR ALUNO ID
                    String id = JOptionPane.showInputDialog("Digite o id do aluno:");
                    a = alunoRepository.findAlunoId(Integer.parseInt(id));
                    listAluno(a);
                    break ;
                }
                case "8":{//BUSCAR ALUNOS QUE NASCERAM APOS UMA DATA
                    String data = JOptionPane.showInputDialog("Digite a data:");
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataFormatada = LocalDate.parse(data, formato);
                    List<Aluno> lista = alunoRepository.findByDataNascimentoAfter(dataFormatada);
                    listAlunos(lista);
                    break ;
                }
                case "9":{//LISTAR ALUNOS E A SUA QUANT DE DISCIPLINAS CURSADAS
                    List<AlunosAndQuantDisciplinas> lista = alunoRepository.findAlunosAndQuantDisciplinas();
                    listAlunosAndQuantDisciplinas(lista);
                    break ;
                }
                case "10":{//LISTAR ALUNOS E SUAS RESPECTIVAS DISCIPLINAS
                     String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
                    List<Aluno> lista = alunoRepository.findAlunoAndDisciplinasByNomeContainingIgnoreCase(nome);
                    listDisciplinasCursadasPorAluno(lista);
                    break;
                }
                case "11":{//EXCLUIR ALUNO POR ID
                    String id = JOptionPane.showInputDialog("Digite o id do aluno:");
                    a = alunoRepository.findAlunoId(Integer.parseInt(id));
                    if(find(a)) {
                        alunoRepository.delete(a);
                    }
                    break;
                }
                case "12":{//EXCLUIR ALUNO POR MATRICULA
                    String matricula = JOptionPane.showInputDialog("Digite a matricula do aluno:");
                    a = alunoRepository.findByMatricula(matricula);
                    if(find(a)) {;
                        alunoRepository.delete(a);
                    }
                    break;
                }
                case "13":
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
                1 - Inserir aluno
                2 - Matricular aluno em disciplinas
                3 - Atualizar cadastro de aluno por matricula
                4 - Listar alunos
                5 - Buscar alunos por nome
                6 - Buscar aluno por matricula
                7 - Buscar aluno pelo id
                8 - Buscar alunos que nasceram após a data
                9 - Listar alunos e a quantidade de disciplinas que cursam
                10 - Buscar alunos por nome com suas respectivas disciplinas 
                11 - Excluir aluno por id
                12 - Excluir aluno por matricula
                13 - Voltar para o menu principal""");
    }

    public static boolean find(Aluno a) {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            return false;
        }else{
            return true;
        }
    }

    public static void listDisciplinasCursadasPorAluno(List<Aluno> lista){
        StringBuilder sb = new StringBuilder();
        for (Aluno a: lista) {
            if(!a.getDisciplinas().isEmpty()) {
                sb.append("Aluno(a) ").append(a.getNome());
                sb.append(" com a matricula ").append("("+a.getMatricula()+")").append(" cursa:\n");
                for (Disciplina d: a.getDisciplinas()) {
                    sb.append(d.toString()).append("\n");
                }
            }else{
                sb.append("Aluno(a) ").append(a.getNome()).append(" com a matricula ").append(a.getMatricula());
                sb.append(" não cursa nehuma disciplina\n");
            }
            sb.append("\n");
        }
        if(!sb.isEmpty())
            JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void listAlunosAndQuantDisciplinas(List<AlunosAndQuantDisciplinas> lista){
        StringBuilder sb = new StringBuilder();
        for (AlunosAndQuantDisciplinas a : lista) {
            if(a.getQuant() > 0) {
                sb.append("Aluno(a): ").append(a.getNome()).append(" com matricula: ").append(a.getMatricula());
                sb.append(" está matriculado(a) em ").append(a.getQuant()).append(" disciplina(s)");
            }else{
                sb.append(a.getNome()).append(" não está matriculado(a) em nenhuma disciplina");
            }
            sb.append("\n");
        }
        if(!sb.isEmpty())
            JOptionPane.showMessageDialog(null, sb.toString());
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

    public static void listNomeAndMatricula(NomeAndEmailAluno aluno){
        if(aluno!=null){
                JOptionPane.showMessageDialog(null, "nome: " + aluno.getNome()
                        + " - email: " + aluno.getEmail());
        }
    }

    public static void listAluno(Aluno a){
        if(find(a)){
            JOptionPane.showMessageDialog(null, a.toString());
        }else{
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        }
    }

    public static void setAluno(Aluno a){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String nome = JOptionPane.showInputDialog("Nome", a.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", a.getCpf());
        String matricula = JOptionPane.showInputDialog("Matricula", a.getMatricula());
        String email = JOptionPane.showInputDialog("Email", a.getEmail());
        String data_nascimento = JOptionPane.showInputDialog("Data de nascimento", a.getDataNascimento());

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
            if(Integer.parseInt(codigo) != 0){
                disciplina = disciplinaRepository.findByCodigo(codigo);
                if(disciplina != null){
                    JOptionPane.showMessageDialog(null, "Aluno matriculado em "+disciplina);
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
