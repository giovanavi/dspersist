package dspersist.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Contato {
    @Getter @Setter private Integer id;
    @NonNull @Getter @Setter private String nome;
    @NonNull @Getter @Setter private String email;
    @NonNull @Getter @Setter private String endereco;
}