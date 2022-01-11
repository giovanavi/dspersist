package trabalho2.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import trabalho2.dao.AlunoRepository;
import trabalho2.dao.DisciplinaRepository;
import trabalho2.entity.Aluno;
import trabalho2.entity.Disciplina;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Crie uma classe para inserir, atualizar e deletar alunos e disciplinas separadamente. A classe deve permitir também
//        adicionar disciplinas cadastradas a um determinado aluno também já cadastrado.

@ComponentScan("trabalho2")
public class DisciplinaCRUD implements CommandLineRunner {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
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
                case "1": {//INSERIR DISCIPLINAS
                    setDisciplina(d);
                    disciplinaRepository.save(d);
                    break;
                }
                case "2":{//UPDATE POR CORDIGO
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaRepository.findByCodigo(codigo);
                    if(find(d)){
                        setDisciplina(d);
                        disciplinaRepository.save(d);
                    }
                    break;
                }
                case "3":{//LISTAR DISCIPLINAS
                    List<Disciplina> lista = disciplinaRepository.findAll();
                    listDisciplinas(lista);
                    break;
                }
                case "4":{//BUSCAR DISCIPLINAS POR NOME
                    String nome = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
                    List<Disciplina> lista = disciplinaRepository.findByNomeContaining(nome);
                    listDisciplinas(lista);
                    break;
                }
                case "5":{//BUSCAR DISCIPLINAS POR CODIGO
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaRepository.findByCodigo(codigo);
                    listDisciplina(d);
                    break ;
                }
                case "6": {//MOSTRAR ALUNOS QUE CURSAM UMA DISCIPLINA PELO SEU CODIGO
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaRepository.findDisciplinaAndAlunosByCodigo(codigo);
                    listDisciplinaAndAlunosMatriculados(d);
                    break ;
                }
                case "7": {//EXCLUIR DISCIPLINA POR CODIGO
                    String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
                    d = disciplinaRepository.findByCodigo(codigo);
                    if(find(d)){
                        disciplinaRepository.delete(d);
                        if(d.getAlunos()!=null) {
                            for (Aluno aluno : d.getAlunos()) {
                                aluno.getDisciplinas().remove(d);
                            }
                        }
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

     public static String menu(){
        return JOptionPane.showInputDialog("""
                Selecione uma opção:
                1 - Inserir disciplina
                2 - Atualizar disciplina por código
                3 - Listar disciplinas
                4 - Buscar disciplinas por nome
                5 - Buscar disciplina por código
                6 - Mostrar alunos que cursam uma disciplina pelo seu código
                7 - Excluir disciplina por código
                8 - Voltar para o menu principal""");
    }

    public static boolean find(Disciplina d) {
        if (d == null) {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
            return false;
        }else{
            return true;
        }
    }

    public static void listDisciplinaAndAlunosMatriculados(Disciplina d){
        StringBuilder sb = new StringBuilder();
        if (!d.getAlunos().isEmpty()) {
            sb.append("Disicplina: ").append(d.getNome()).append(" cursada por:\n");
            for (Aluno aluno : d.getAlunos()) {
                sb.append(aluno.toString()).append("\n");
            }
        } else {
            sb.append("Disciplina: ").append(d.getNome()).append(" não tem alunos matriculados.");
        }

        if(!sb.isEmpty())
            JOptionPane.showMessageDialog(null, sb.toString());
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
        if(find(d)){
            JOptionPane.showMessageDialog(null, d.toString());
        }else{
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
        }
    }

    public static void setDisciplina(Disciplina d){
        String nome = JOptionPane.showInputDialog("Nome", d.getNome());
        String codigo = JOptionPane.showInputDialog("Código", d.getCodigo());
        d.setNome(nome);
        d.setCodigo(codigo);
    }

}
