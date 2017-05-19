import java.util.UUID;

public class Pessoa {
	private String _id;
	private String _nome;
	private String _endereco;

	public Pessoa(String id, String nome, String endereco)
	{
		this._id = id;
		this._nome = nome;
		this._endereco = endereco;
	}

	public Pessoa(String nome, String endereco)
	{
		this(UUID.randomUUID().toString(), nome, endereco);
	}
	
	public String getId()
	{
		return this._id;
	}

	protected void setId(String id)
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
