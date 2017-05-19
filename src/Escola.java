
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Escola {

    private final Map<String, Turma> turmas;
    private final Map<String, Professor> profs;
    private int _id;
    private String _nome;
    private String _endereco;

    public Escola(int id)
    {
        this._id = id;

        turmas = new HashMap<>();
        profs = new HashMap<>();
    }

    public Escola(int id, String nome, String endereco)
    {
        this(id);

        this._nome = nome;
        this._endereco = endereco;
    }

    public Escola(String nome, String endereco)
    {
        this(-1, nome, endereco);
    }

    public int getId()
    {
        return this._id;
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

    protected void obterDados()
    {
        if (this.getId() == -1) return;

        try {
            PreparedStatement pstmt = Principal.dbConnection.prepareStatement("SELECT * FROM escolas WHERE id = ?");
            pstmt.setInt(1, this.getId());
            pstmt.execute();

            ResultSet rs = pstmt.getResultSet();

            this.setNome(rs.getString("nome"));
            this.setEndereco(rs.getString("endereco"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void populaDb()
    {
        try
        {
            PreparedStatement pstmt = Principal.dbConnection.prepareStatement("INSERT INTO escolas(nome, endereco) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.getNome());
            pstmt.setString(2, this.getEndereco());

            int rows = pstmt.executeUpdate();

            if (rows == 0)
            {
                System.out.println("Falha ao inserir escola na base de dados!");

                return;
            }

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                int id = rs.getInt(1);

                for (Map.Entry<String, Turma> e : turmas.entrySet())
                {
                    Turma t = e.getValue();

                    PreparedStatement stmtTurma = Principal.dbConnection.prepareStatement("INSERT INTO turmas(uuid, serie, escola_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    stmtTurma.setString(1, t.getUuid());
                    stmtTurma.setString(2, t.getSerie());
                    stmtTurma.setInt(3, id);

                    if (stmtTurma.executeUpdate() != 0)
                    {
                        ResultSet rsTurma = stmtTurma.getGeneratedKeys();

                        if (rsTurma.next())
                        {
                            t.setId(rsTurma.getInt(1));
                            t.populaDb();
                        }
                    }
                    else
                    {
                        System.out.println(stmtTurma.getResultSet().getWarnings().getMessage());
                    }
                }

                for (Map.Entry<String, Professor> e : profs.entrySet())
                {
                    Professor prof = e.getValue();

                    PreparedStatement stmtProf = Principal.dbConnection.prepareStatement("INSERT INTO professores(uuid, nome, endereco, salario, escola_id) VALUES(?, ?, ?, ?, ?)");
                    stmtProf.setString(1, prof.getId());
                    stmtProf.setString(2, prof.getNome());
                    stmtProf.setString(3, prof.getEndereco());
                    stmtProf.setDouble(4, prof.getSalario());
                    stmtProf.setInt(5, id);
                    stmtProf.execute();
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void cadastrarNovaTurma(Turma turma)
    {
        if (turmas.containsKey(turma.getUuid()))
        {
            return;
        }

        turmas.put(turma.getUuid(), turma);
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
