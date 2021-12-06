package lista6.model;
import lombok.*;

import javax.persistence.*;

@NamedQuery(name = "Funcionario.update", query = "update Funcionario as f set f.cpf=:cpf, f.matricula=:matricula, f.nome=:nome, f.email=:email, f.telefone=:telefone where f.id=:id")
@NamedQuery(name = "Funcionario.deleteByCpf", query = "delete from Funcionario where cpf = :cpf")
@NamedQuery(name = "Funcionario.deleteByMatricula", query = "delete from Funcionario where matricula = :matricula")
@NamedQuery(name = "Funcionario.findAll", query = "select f from Funcionario as f order by id asc")
@NamedQuery(name = "Funcionario.findByName", query = "select f from Funcionario as f where f.nome like :nome order by id asc")
@NamedQuery(name = "Funcionario.findByCpf", query = "select f from Funcionario as f where cpf= :cpf")
@NamedQuery(name = "Funcionario.findByMatricula", query = "select f from Funcionario as f where matricula= :matricula")

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @NonNull @Getter @Setter private String cpf;//(11)
    @NonNull @Getter @Setter private String matricula;//(6)
    @NonNull @Getter @Setter private String nome;//(50)
    @NonNull @Getter @Setter private String email;//(30)
    @NonNull @Getter @Setter private String telefone;//(11)

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append(" - cpf: ").append(cpf);
        sb.append(" - matricula: ").append(matricula);
        sb.append(" - nome: ").append(nome);
        sb.append(" - email: ").append(email);
        sb.append(" - telefone: ").append(telefone);
        return sb.toString();
    }
}
