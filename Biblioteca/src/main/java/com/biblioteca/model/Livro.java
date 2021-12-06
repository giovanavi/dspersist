package com.biblioteca.model;


/*
CREATE TABLE IF NOT EXISTS livro(
	ISBN int not null,
	titulo varchar(50) not null,
	autor varchar(50) not null,
	editora varchar(50) not null,
	datainsercao varchar(10) not null,
	status int default 0;
	CONSTRAINT livro_pkey PRIMARY KEY(isbn)
);
*/

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro", nullable = false)
    @Getter @Setter
    private int id;

    @Column(name = "isbn", nullable = false, length = 17)
    @NonNull @Getter @Setter
    private String isbn;

    @Column(nullable = false)
    @NonNull @Getter @Setter
    private String titulo;

    @Column(nullable = false)
    @NonNull @Getter @Setter
    private String autor;

    @Column(nullable = false)
    @NonNull @Getter @Setter
    private String editora;


    @Getter @Setter
    private boolean emprestado = false;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "livro", targetEntity = Emprestimo.class)
    @Getter @Setter
    private Emprestimo emprestimo;
}
