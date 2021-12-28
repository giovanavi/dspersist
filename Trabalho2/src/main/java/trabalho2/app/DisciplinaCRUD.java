package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import trabalho2.dao.DisciplinaDAO;
import trabalho2.entity.Disciplina;

import javax.swing.*;
import java.util.List;

//Crie uma classe para inserir, atualizar e deletar alunos e disciplinas separadamente. A classe deve permitir também
//        adicionar disciplinas cadastradas a um determinado aluno também já cadastrado.

@ComponentScan("trabalho2")
public class DisciplinaCRUD implements CommandLineRunner {
    @Autowired
    private DisciplinaDAO disciplinaDAO;

    @Override
    public void run(String... args) throws Exception {
        label:
        while (true) {
            Disciplina d = new Disciplina();
            String op = menu();
            if (op == null) {
                break;
            } else if (op.isEmpty()) {
                continue;
            }
            switch (op){
                case "1": {//Inserir disciplina
                    setDisciplina(d);
                    disciplinaDAO.save(d);
                    break;
                }
                case "2":{//Atualizar disciplina por codigo
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        setDisciplina(d);
                        disciplinaDAO.save(d);
                    }
                    break;
                }
                case "3":{//Listar disciplinas
                    List<Disciplina> lista = disciplinaDAO.findAll();
                    listDisciplinas(lista);
                    break;
                }
                case "4":{//Buscar disciplinas por nome
                    String nome = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
                    List<Disciplina> lista = disciplinaDAO.findByNomeContainingIgnoreCase(nome);
                    listDisciplinas(lista);
                    break;
                }
                case "5":{//Buscar disciplina por código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        listDisciplina(d);
                    }
                    break ;
                }
                case "6": {//Mostrar alunos que cursam uma disciplina dado o código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)) {
                        listAlunosCursandoDisciplina(d);
                    }
                    break ;
                }
                case "7": {//Excluir disciplina por código
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaDAO.findByCodigo(codigo);
                    if(find(d)){
                        disciplinaDAO.delete(d);
                    }
                    break;
                }
                case "8": {//Fechar menu
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
                2 - Atualizar disciplina por codigo
                3 - Listar disciplinas
                4 - Buscar disciplinas por nome
                5 - Buscar disciplina por código
                6 - Mostrar alunos que cursam uma disciplina
                7 - Excluir disciplina por código
                8 - Fechar menu""");
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

    public static void listAlunosCursandoDisciplina(Disciplina d){
        StringBuilder sb = new StringBuilder();

        sb.append(d.getNome()+" cursada por : ");
        sb.append("\n") ;
        sb.append(d.getAlunosMatriculados());

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
}
