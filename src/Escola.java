import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Escola {
    private static Escola instance = null;
    private final Map<Integer, Turma> turmas;
    private final Map<Integer, Professor> profs;

    private Escola()
    {
        turmas = new HashMap<>();
        profs = new HashMap<>();
    }

    public static Escola getInstance()
    {
        if (instance == null)
            instance = new Escola();

        return instance;
    }

    public void obterDados()
    {
        try
        {
            ResultSet rs = Principal.dbConnection.createStatement().executeQuery("SELECT * FROM turmas");

            while (rs.next())
            {
                Turma t = new Turma(rs.getInt("id"), true);

                turmas.put(t.getId(), t);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected void populaDb() throws SQLException
    {
        for (Map.Entry<Integer, Turma> e : turmas.entrySet())
        {
            Turma t = e.getValue();

            PreparedStatement stmtTurma = Principal.dbConnection.prepareStatement("INSERT INTO turmas(serie) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmtTurma.setString(2, t.getSerie());

            if (stmtTurma.executeUpdate() != 0)
            {
                ResultSet rsTurma = stmtTurma.getGeneratedKeys();

                if (rsTurma.next())
                {
                    t.setId(rsTurma.getInt(1));
                    t.populaDb();
                }
            } else
            {
                System.out.println(stmtTurma.getResultSet().getWarnings().getMessage());
            }
        }

        for (Map.Entry<Integer, Professor> e : profs.entrySet())
        {
            Professor prof = e.getValue();

            PreparedStatement stmtProf = Principal.dbConnection.prepareStatement("INSERT INTO professores(nome, endereco, salario) VALUES(?, ?, ?)");
            stmtProf.setInt(1, prof.getId());
            stmtProf.setString(2, prof.getNome());
            stmtProf.setString(3, prof.getEndereco());
            stmtProf.setDouble(4, prof.getSalario());
            stmtProf.executeUpdate();
        }
    }

    public void cadastrarNovaTurma(Turma turma) throws SQLException
    {
        if (turmas.containsKey(turma.getId())) return;

        turmas.put(turma.getId(), turma);

        turma.populaDb();
    }

    public void cadastrarNovoProfessor(Professor prof)
    {
        if (profs.containsKey(prof.getId()))
        {
            return;
        }

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

    public int getQuantAlunos()
    {
        int count = 0;

        for (Turma t : turmas.values())
        {
            count += t.getQuantAlunos();
        }

        return count;
    }

    public Collection<Professor> getProfessores()
    {
        return profs.values();
    }

    public Collection<Turma> getTurmas()
    {
        return turmas.values();
    }

    public Turma getTurmaById(int id) throws IndexOutOfBoundsException
    {
        if (turmas.containsKey(id))
            return turmas.get(id);

        throw new IndexOutOfBoundsException("Turma com ID(" + id + ") não encontrada!");
    }

    public void removerTurma(int id)
    {
        turmas.remove(id);
    }

    public void removerProfessor(int id)
    {
        profs.remove(id);
    }

    public void matricularAluno(int tid, Aluno aluno)
    {
        turmas.get(tid).addAluno(aluno);
    }

    public void removerAluno(int tid, int aid)
    {
        turmas.get(tid).removerAluno(aid);
    }
}
