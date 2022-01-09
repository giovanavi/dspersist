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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Crie uma classe para inserir, atualizar e deletar alunos e disciplinas separadamente. A classe deve permitir também
//        adicionar disciplinas cadastradas a um determinado aluno também já cadastrado.

@ComponentScan("trabalho2")
public class DisciplinaCRUD implements CommandLineRunner {
    @Autowired
    private DisciplinaDAO disciplinaDAO;
    @Autowired
    private AlunoDAO alunoDAO;

    @Transactional
    @Override
    public void run(String... args) {
        label:
        while (true) {
            Disciplina d = new Disciplina();
            String op = menu();
            if (op == null) {
                break;
            } else if (op.isEmpty()) {
                continue;
            }
            switch (op) {
                case "1": {//Inserir disciplina
                    setDisciplina(d);
                    disciplinaDAO.save(d);
                    break;
                }
//                case "2":{//Matricular aluno na disciplina
//                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina para matricular aluno");
//                    d = disciplinaDAO.findByCodigo(codigo);
//                    if(find(d)){
//                        Set<Disciplina> disciplinas = new HashSet<>();
//                        disciplinas.add(d);
//
//                        matricularAlunos(disciplinas);
//                    }
//                    break;
//                }
                case "3":{//Atualizar disciplina por codigo
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        setDisciplina(d);
                        disciplinaDAO.save(d);
                    }
                    break;
                }
                case "4":{//Listar disciplinas
                    List<Disciplina> lista = disciplinaDAO.findAll();
                    listDisciplinas(lista);
                    break;
                }
                case "5":{//Buscar disciplinas por nome
                    String nome = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
                    List<Disciplina> lista = disciplinaDAO.findByNomeContaining(nome);
                    listDisciplinas(lista);
                    break;
                }
                case "6":{//Buscar disciplina por código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        listDisciplina(d);
                    }
                    break ;
                }
                case "7": {//Mostrar alunos que cursam uma disciplina dado o código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findAlunosByCodigo(codigo);
                    if (find(d)) {
                        System.out.println(d.toString());
                        listAlunosCursandoDisciplina(d);
                    }
                    break ;
                }
                case "8": {//Excluir disciplina por código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        disciplinaDAO.delete(d);
                        for (Aluno aluno : d.getAlunos()) {
                            aluno.getDisciplinas().remove(d);
                        }
                    }
                    break;
                }
                case "9": {//Fechar menu
                    break label;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Comando inválido.");
                    break;
            }
        }
    }
//    b) Dado um código de disciplina, mostrar todos os alunos que a cursaram. (DISCIPLINA)
    public static String menu(){
        return JOptionPane.showInputDialog("""
                Selecione uma opção:
                1 - Inserir disciplina
                2 - Matricular aluno na disciplina
                3 - Atualizar disciplina por codigo
                4 - Listar disciplinas
                5 - Buscar disciplinas por nome
                6 - Buscar disciplina por código
                7 - Mostrar alunos que cursam uma disciplina
                8 - Excluir disciplina por código
                9 - Voltar para o menu principal""");
    }

    public static boolean find(Disciplina d) {
        if (d == null) {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
            return false;
        }else{
            return true;
        }
    }

//    DISICPLINA CURSADA POR : XXXXXX, XYYYYYYY, YYYY;

    @Transactional
    public static void listAlunosCursandoDisciplina(Disciplina d){
        StringBuilder sb = new StringBuilder();

        sb.append("Disciplina :");
        sb.append(" id=").append(d.getId());
        sb.append(" - codigo=").append(d.getCodigo());
        sb.append(" - nome=").append(d.getNome());
        sb.append("\n");
        //        sb.append("\nAlunos {");
        for (Aluno aluno : d.getAlunos()) {
            sb.append(aluno).append("\n");
//            sb.append(" nome=").append(aluno.getNome());
//            sb.append(" - matricula=").append(aluno.getMatricula());
        }
//        sb.append(" }");

        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum aluno cursa essa disciplina no momento.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listDisciplinas(List<Disciplina> lista){
        StringBuilder sb = new StringBuilder();
        for (Disciplina d : lista) {
            sb.append(d.toString());
            sb.append("\n");
        }
        if(sb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina encontrada.");
        }else{
            JOptionPane.showMessageDialog(null, sb);
        }
    }

    public static void listDisciplina(Disciplina d){
        if(d == null){
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
        }else{
            JOptionPane.showMessageDialog(null, d.toString());
        }
    }
    //ID, CODIGO, nome
    public static void setDisciplina(Disciplina d){
        String nome = JOptionPane.showInputDialog("Nome", d.getNome());
        String codigo = JOptionPane.showInputDialog("Código", d.getCodigo());
        d.setNome(nome);
        d.setCodigo(codigo);
    }

    //matricular alunos
//    public void matricularAlunos(Set<Disciplina> disciplina){
//        boolean isTrue = true;
//        while(isTrue) {
//            Aluno aluno;
//            String id = JOptionPane.showInputDialog(null, "Digite o id do aluno a ser matriculado. (Digite 0 para sair)");
//            if(Integer.parseInt(id) > 0){
//                aluno = alunoDAO.findAlunoId(Integer.parseInt(id));
//                if(aluno != null){
//                    JOptionPane.showMessageDialog(null, "Aluno:" +aluno);
//                    aluno.setDisciplinas(disciplina);
//                    alunoDAO.save(aluno);
//                }else{
//                    JOptionPane.showMessageDialog(null,"Aluno não encontrado.");
//                }
//            }else{
//                isTrue = false;
//            }
//        }
//
//    }
}
