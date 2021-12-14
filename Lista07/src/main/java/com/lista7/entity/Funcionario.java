package com.lista7.entity;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@NamedQuery(name = "findByCpf", query = "select f from Funcionario f where f.cpf = :cpf")

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @NonNull @Getter @Setter
    private String cpf;//(11)

    @NonNull @Getter @Setter
    private String matricula;//(6)

    @NonNull @Getter @Setter
    private String nome;//(50)

    @NonNull @Getter @Setter
    private String email;//(30)

    @NonNull @Getter @Setter
    private String telefone;//(11)

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
