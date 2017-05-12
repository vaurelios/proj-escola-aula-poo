
public class Principal {

	public static void main(String[] args)
	{
		Aluno a1 = new Aluno("turma 01", "rua 0");
		Turma t1 = new Turma("ads2016.1");
		Professor p1 = new Professor("sorim", "rua1", 800.00);
		Escola e1 = new Escola(); 
		
		
		Aluno a2 = new Aluno("turma 01", "rua 0");
		Professor p2 = new Professor("sorim", "rua1", 800.00);
		
		
		e1.cadastrarNovaTurma(t1);
		e1.cadastrarNovoProfessor(p1);
		e1.cadastrarNovoProfessor(p2);
		
		e1.matricularAluno(t1.getId(), a1);
		e1.matricularAluno(t1.getId(), a2);

		System.out.println("Quantidade de Alunos: " + t1.getQuantAlunos());
		System.out.println("Quantidade de Professores: " + e1.getQuantProfessores());
		System.out.println("Quantidade de Turmas: " + e1.getQuantTurmas());
		
		e1.removerAluno(t1.getId(), a1.getId());
		e1.removerProfessor(p1.getId());
		e1.removerTurma(t1.getId());
	}

}
