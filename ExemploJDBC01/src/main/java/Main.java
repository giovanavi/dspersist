import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            Connection conex = ConenctionFactory.getConnection();
            System.out.println("Conexão OK!!");

            String sql = "select * from teste where id=?";
            PreparedStatement st = conex.prepareStatement(sql);
            st.setInt(1, 2);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                System.out.println(id+" - "+nome);
            }
            conex.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
