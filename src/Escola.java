import java.util.HashMap;
import java.util.Map;

public class Escola {
	private Map<String, Turma> turmas;
	private Map<String, Professor> profs;
	
	public Escola()
	{
		turmas = new HashMap<String, Turma>();
		profs = new HashMap<String, Professor>();
	}
	
	public void cadastrarNovaTurma(Turma turma)
	{
		if (turmas.containsKey(turma.getId()))
			return;
		
		turmas.put(turma.getId(), turma);
	}
	
	public void cadastrarNovoProfessor(Professor prof)
	{
		if (profs.containsKey(prof.getId()))
			return;
		
		profs.put(prof.getId(), prof);
	}
	
	public int getQuantTurmas()
	{
		return turmas.size();
	}
	
	public int getQuantProfessores()
	{
		return profs.size();
	}
	
	public void removerTurma(String id)
	{
		turmas.remove(id);
	}
	
	public void removerProfessor(String id)
	{
		profs.remove(id);
	}
	
	public void matricularAluno(String tid, Aluno aluno)
	{
		turmas.get(tid).addAluno(aluno);
	}
	
	public void removerAluno(String tid, String aid)
	{
		turmas.get(tid).removerAluno(aid);
	}
}
