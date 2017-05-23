import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aluno extends Pessoa {

	public Aluno(int id, String nome, String endereco)
	{
		super(id, nome, endereco);
	}

	public Aluno(String nome, String endereco)
	{
	    super(nome, endereco);
	}
}
