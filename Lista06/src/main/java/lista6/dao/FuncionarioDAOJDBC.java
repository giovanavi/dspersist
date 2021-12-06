package lista6.dao;

import lista6.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioDAOJDBC implements FuncionarioDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //SAVE
    public void create(Funcionario f) {
        String sql = "insert into funcionarios (cpf, matricula, nome, email, telefone) values " +
                "(:cpf,:matricula,:nome,:email,:telefone)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("cpf",f.getCpf())
                .addValue("matricula",f.getMatricula())
                .addValue("nome", f.getNome())
                .addValue("email", f.getEmail())
                .addValue("telefone", f.getTelefone());
        jdbcTemplate.update(sql, parameterSource);
    }

    //UPDATE
    public void update(Funcionario f){
        String sql = "update funcionarios set cpf=:cpf, matricula=:matricula, nome=:nome, " +
                "email=:email, telefone=:telefone where id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("cpf",f.getCpf())
                .addValue("matricula",f.getMatricula())
                .addValue("nome", f.getNome())
                .addValue("email", f.getEmail())
                .addValue("telefone", f.getTelefone())
                .addValue("id", f.getId());
        jdbcTemplate.update(sql, parameterSource);
    }

    //DELETE BY CPF
    public void deleteByCpf(String cpf){
        String sql = "delete from funcionarios where cpf=:cpf";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("cpf", cpf);
        jdbcTemplate.update(sql, parameterSource);

    }
    //DELETE BY MATRICULA

    public void deleteByMatricula(String matricula){
        String sql = "delete from funcionarios where matricula=:matricula";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("matricula", matricula);
        jdbcTemplate.update(sql, parameterSource);
    }

    //MAP
    public Funcionario map(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setId(rs.getInt("id"));
        f.setCpf(rs.getString("cpf"));
        f.setMatricula(rs.getString("matricula"));
        f.setNome(rs.getString("nome"));
        f.setEmail(rs.getString("email"));
        f.setTelefone(rs.getString("telefone"));
        return f;
    }

    //FIND ALL
    @Override
    public List<Funcionario> findAll() {
        String sql = "select * from funcionarios order by id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
    }

    //FIND BY NAME
    @Override
    public List<Funcionario> findByName(String name) {
        String sql = "select * from funcionarios where upper(nome) like :nome order by id asc";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("nome", "%"+name.toUpperCase()+"%");
        return jdbcTemplate.query(sql, parameterSource,(rs,rowNum)->map(rs));
    }

    //FIND BY CPF
    @Override
    public Funcionario findByCpf(String cpf) {
        String sql = "select * from funcionarios where cpf = :cpf";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("cpf", cpf);
        return jdbcTemplate.queryForObject(sql, parameterSource, (rs,rowNum) -> map(rs));
    }

    //FIND BY MATRICULA
    @Override
    public Funcionario findByMatricula(String matricula) {
        String sql = "select * from funcionarios where matricula = :matricula";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("matricula", matricula);
        return jdbcTemplate.queryForObject(sql, parameterSource, (rs,rowNum)->map(rs));
    }
}
