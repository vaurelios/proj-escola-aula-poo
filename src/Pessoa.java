import java.util.concurrent.ThreadLocalRandom;

public class Pessoa {
	private int _id;
	private String _nome;
	private String _endereco;

	public Pessoa(int id)
	{
		this._id = id;
	}

	public Pessoa(int id, String nome, String endereco)
	{
		this(id);
		this._nome = nome;
		this._endereco = endereco;
	}

	public Pessoa(String nome, String endereco)
	{
		this(ThreadLocalRandom.current().nextInt(1, 101), nome, endereco);
	}

	public int getId()
	{
		return this._id;
	}

	protected void setId(int id)
	{
		this._id = id;
	}

	public String getNome()
	{
		return this._nome;
	}

	public void setNome(String nome)
	{
		this._nome = nome;
	}

	public String getEndereco()
	{
		return this._endereco;
	}

	public void setEndereco(String endereco)
	{
		this._endereco = endereco;
	}
}
