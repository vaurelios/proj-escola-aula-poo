public class Professor extends Pessoa {
	private double _salario;
	private int _escola_id;

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

    public int getEscolaId()
    {
        return this._escola_id;
    }

    public void setEscolaId(int escola_id)
    {
        this._escola_id = escola_id;
    }
}
