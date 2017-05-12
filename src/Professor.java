public class Professor extends Pessoa {
	private double _salario;
	
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
}
