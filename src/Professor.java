import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Professor extends Pessoa {
    private double _salario;

    public Professor(int id, String nome, String endereco, double salario)
    {
        super(id);

        this.setNome(nome);
        this.setEndereco(endereco);
        this.setSalario(salario);
    }

    public Professor(String nome, String endereco, double salario)
    {
        super(nome, endereco);

        this._salario = salario;
    }

    public double getSalario()
    {
        return this._salario;
    }

    public void setSalario(double salario)
    {
        this._salario = salario;
    }

    public void populaDb() throws SQLException
    {
        if (Principal.dbConnected == false) return;

        PreparedStatement pstmt = Principal.dbConnection.prepareStatement("INSERT INTO professores(nome, endereco, salario) VALUES(?, ?, ?)");
        pstmt.setString(1, this.getNome());
        pstmt.setString(2, this.getEndereco());
        pstmt.setDouble(3, this.getSalario());
        pstmt.executeUpdate();
    }

    public boolean removerDb() throws SQLException
    {
        if (this.getId() == -1 || !Principal.dbConnected) return false;

        PreparedStatement pstmt = Principal.dbConnection.prepareStatement("DELETE FROM professores WHERE id = ?");
        pstmt.setInt(1, this.getId());

        return pstmt.executeUpdate() > 0;
    }
}
