package dspersist.dao;
import dspersist.model.Funcionario;
import java.util.List;

public interface FuncionarioDAO {

    public void create(Funcionario f);
    public void update(Funcionario f);
    public void deleteByCpf(String cpf);
    public void deleteByMatricula(String matricula);
    public List<Funcionario> findAll();
    public List<Funcionario> findByName(String name);
    public Funcionario findByCpf(String cpf);
    public Funcionario findByMatricula(String matricula);
}
