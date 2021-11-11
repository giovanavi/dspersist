package dspersist.dao;

import dspersist.model.Contato;
import java.util.List;

public interface ContatoDAO {

    public List<Contato> findAll();
    public void read(Contato contato);

}
