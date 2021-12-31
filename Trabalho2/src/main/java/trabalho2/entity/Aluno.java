package trabalho2.entity;

import lombok.*;
import net.bytebuddy.build.Plugin;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

//id, cpf, matrícula, nome, email e data de nascimento.
//Os campos id, código, matrícula e email, considerados individualmente, não devem permitir duplicação,
// ou seja devem ser únicos.
//Existe uma associação entre alunos e disciplinas de modo que
//um aluno pode cursar várias disciplinas e uma disciplinas pode ser cursada por vários alunos.

//Aluno(1-n) -----------------  (n)Disciplina

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "disciplinas")
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id", unique = true)
    @Getter @Setter private Integer id;

//    @Column(unique = true)
    @NonNull @Getter @Setter private String cpf;

    @Column(unique = true)
    @NonNull @Getter @Setter private String matricula;

//    @Column(unique = true)
    @NonNull @Getter @Setter private String nome;

    @Column(unique = true)
    @NonNull @Getter @Setter private String email;

//    @Column(unique = true)
    @Column(name = "data_nascimento")
    @NonNull @Getter @Setter private LocalDate dataNascimento;

    @ManyToMany(mappedBy = "alunos")
    @Getter @Setter private List<Disciplina> disciplinas;

//    @Override
//    public String toString() {
//        return "Aluno{" +
//                "id=" + id +
//                ", cpf='" + cpf + '\'' +
//                ", matricula='" + matricula + '\'' +
//                ", nome='" + nome + '\'' +
//                ", email='" + email + '\'' +
//                ", dataNascimento=" + dataNascimento +
//                ", disciplinas=" + disciplinas +
//                '}';
//    }

    @Transactional
    public StringBuilder getDisciplinasCursadas(){
        StringBuilder sb = new StringBuilder();
        if(!this.disciplinas.isEmpty()) {
            sb.append(disciplinas.get(0).getNome());
            for (int i=1; i<this.disciplinas.size(); i++) {
                sb.append(", "+getDisciplinas().get(i).getNome());
            }
        }
        return sb;
    }

    @Transactional
    public int getQuantDisciplinasCursadas(Integer id){
        int quantidade = 0;
        if(!this.disciplinas.isEmpty()) {
            for (Disciplina disciplna : this.disciplinas) {
                quantidade++;
            }
        }else{
            return 0;
        }
        return quantidade;
    }
}

