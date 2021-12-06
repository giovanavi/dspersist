package lista6.dao;

import lista6.model.Funcionario;

import java.util.List;

public interface FuncionarioDAO {
    void create(Funcionario f);
    void update(Funcionario f);
    void deleteByCpf(String cpf);
    void deleteByMatricula(String matricula);
    List<Funcionario> findAll();
    List<Funcionario> findByName(String name);
    Funcionario findByCpf(String cpf);
    Funcionario findByMatricula(String matricula);
}
