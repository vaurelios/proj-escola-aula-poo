import java.util.UUID;

public class Pessoa {
	private String id;
	private String _nome;
	private String _endereco;
	
	public Pessoa(String nome, String endereco)
	{
		id = UUID.randomUUID().toString();

		this._nome = nome;
		this._endereco = endereco;
	}
	
	public String getId()
	{
		return this.id;
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
