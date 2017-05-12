import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Turma {
	private String id;
	private String _serie;
	private Map<String, Aluno> alunos;
	
	public Turma(String serie)
	{
		id = UUID.randomUUID().toString();
		
		this._serie = serie;
		
		alunos = new HashMap<String, Aluno>();
	}

	public String getId() {
		return id;
	}

	public String getSerie() {
		return _serie;
	}
	
	public int getQuantAlunos()
	{
		return this.alunos.size();
	}
	
	public boolean alunoMatriculado(String id)
	{
		return alunos.containsKey(id);
	}

	public Aluno getAluno(String id)
	{
		return this.alunoMatriculado(id) ? alunos.get(id) : null;
	}

	public void addAluno(Aluno aluno)
	{
		if (!this.alunoMatriculado(id))
			this.alunos.put(aluno.getId(), aluno);
	}
	
	public void removerAluno(String id)
	{
		if (this.alunoMatriculado(id))
			this.alunos.remove(id);
	}
}
