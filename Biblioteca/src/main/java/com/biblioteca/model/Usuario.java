package com.biblioteca.model;

/*CREATE IF NOT EXISTS usuario(
        id serial,
        nome varchar(100) not null,
        email varchar(100)
        endereco varchar(100) not null,
        cpf varchar(14),
        datainsercao varchar(10) not null,
        CONSTRAINT usuario_pkey PRIMARY KEY (id);
        );*/

import lombok.*;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    @Getter @Setter
    private int id;

    @NonNull @Getter @Setter
    private String nome;

    @Column(name = "cpf", nullable = false, length = 14)
    @NonNull @Getter @Setter
    private String cpf;

    @Column(nullable = false)
    @NonNull @Getter @Setter
    private String email;

    @Column(nullable = false)
    @NonNull @Getter @Setter
    private String endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @Getter @Setter
    private List<Emprestimo> emprestimos;

//    USUARIO(1,N)     ---------      (0,N)EMPRESTIMO
}