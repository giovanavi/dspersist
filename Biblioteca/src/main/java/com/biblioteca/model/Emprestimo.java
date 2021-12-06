package com.biblioteca.model;

import lombok.*;
import javax.persistence.*;
/*CREATE TABLE IF NOT EXISTS emprestimo(
        id serial,
        dataEmprestimo varchar(10) not null,
        dataDevolucao varchar(10) not null,
        id_pess int not null,
        id_livro int not null,
        multa float default 0;
        CONSTRAINT emprestimo_pkey PRIMARY KEY(id),
        CONSTRAINT emprestimo_fkey1 FOREIGN KEY(id_pess) REFERENCES usuario(id),
        CONSTRAINT emprestimo_fkey2 FOREIGN KEY(id_livro) REFERENCES livro(isbn)
        );*/

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo", nullable = false)
    @Getter @Setter
    private int id;//PK

    @Column(name = "data_emprestimo", nullable = false, length = 10)
    @NonNull @Getter @Setter
    private String dataEmprestimo;

    @Column(name = "data_devolucao", nullable = false, length = 10)
    @NonNull @Getter @Setter
    private String dataDevolucao;

    @Getter @Setter
    private boolean atrasado = false;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @NonNull @Getter @Setter
    private Usuario usuario;//FK

    @OneToOne(optional = false)
    @JoinColumn(name = "id_livro")
    @NonNull @Getter @Setter
    private Livro livro;//FK


}
