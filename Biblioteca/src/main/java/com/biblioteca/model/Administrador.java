package com.biblioteca.model;

/*
CREATE TABLE IF NOT EXISTS administrador(
	id serial,
	nome varchar(100) not null,
	email varchar(100),
	endereco varchar(100) not null,
	cpf varchar(14) not null,
	senha varchar(20) not null,
	CONSTRAINT adm_pkey PRIMARY KEY(id)
);
*/
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adm", nullable = false)
    @Getter @Setter private int id;

    @Column(name = "login", nullable = false, length = 20)
    @NonNull @Getter @Setter private String login;

    @Column(name = "senha", nullable = false, length = 20)
    @NonNull @Getter @Setter private String senha;

    @Override
    public String toString() {
        return "id= " + id + ", login= " + login + " senha= " + senha+ '}';
    }
}
