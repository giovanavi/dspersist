package dspersist.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Funcionario {
    @Getter @Setter private Integer id;
    @NonNull @Getter @Setter private String cpf;//(11)
    @NonNull @Getter @Setter private String matricula;//(6)
    @NonNull @Getter @Setter private String nome;//(50)
    @NonNull @Getter @Setter private String email;//(30)
    @NonNull @Getter @Setter private String telefone;//(11)

}
/*
script sql
create table funcionarios(
id serial,
cpf varchar(11) unique not null,
matricula varchar(6) unique not null,
nome varchar(50),
email varchar(30),
telefone varchar(11));
 */