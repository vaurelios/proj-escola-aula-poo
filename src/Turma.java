
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Turma {

    private int _id;
    private String _uuid;
    private String _serie;
    private Map<String, Aluno> alunos;

    public Turma(String uuid, boolean fetch)
    {
        this._uuid = uuid;
    }

    public Turma(String serie)
    {
        _uuid = UUID.randomUUID().toString();

        this._serie = serie;

        alunos = new HashMap<>();
    }

    public int getId()
    {
        return this._id;
    }

    public void setId(int id)
    {
        this._id = id;
    }

    public String getUuid()
    {
        return this._uuid;
    }

    public void setUuid(String uuid)
    {
        this._uuid = uuid;
    }

    public String getSerie()
    {
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
        if (!this.alunoMatriculado(this.getUuid()))
        {
            this.alunos.put(aluno.getId(), aluno);
        }
    }

    public void removerAluno(String id)
    {
        if (this.alunoMatriculado(id))
        {
            this.alunos.remove(id);
        }
    }

    protected void obterDados()
    {
        try
        {
            PreparedStatement pstmt = Principal.dbConnection.prepareStatement("SELECT * FROM turmas WHERE id = ?");
            pstmt.setInt(1, this.getId());

            if (pstmt.execute())
            {
                ResultSet rs = pstmt.getResultSet();

                this._serie = rs.getString("serie");

                PreparedStatement stmtAlunos = Principal.dbConnection.prepareStatement("SELECT * FROM alunos WHERE turma_id = ?");
                stmtAlunos.setInt(1, this.getId());

                if (stmtAlunos.execute())
                {
                    ResultSet rsAluno = stmtAlunos.getResultSet();

                    Aluno aluno = new Aluno(rsAluno.getString("uuid"),
                            rsAluno.getString("nome"),
                            rsAluno.getString("endereco"));

                    alunos.put(aluno.getId(), aluno);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected void populaDb()
    {
        // salvar alunos
        try
        {
            PreparedStatement pstmt = Principal.dbConnection.prepareStatement("INSERT INTO alunos(uuid, nome, endereco, turma_id) VALUES(?, ?, ?, ?)");
        
            for (Map.Entry<String, Aluno> e : alunos.entrySet())
            {
                pstmt.setString(1, e.getValue().getId());
                pstmt.setString(2, e.getValue().getNome());
                pstmt.setString(3, e.getValue().getEndereco());
                pstmt.setInt(4, this.getId());
                pstmt.execute();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
