
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Turma {

    private int _id;
    private String _serie;
    private Map<Integer, Aluno> alunos;

    public Turma(int id, boolean fetch) throws SQLException
    {
        this._id = id;

        alunos = new HashMap<>();

        if (fetch) obterDados();
    }

    public Turma(String serie) throws SQLException
    {
        this(ThreadLocalRandom.current().nextInt(1, 101), false);

        this._serie = serie;
    }

    public int getId()
    {
        return this._id;
    }

    public void setId(int id)
    {
        this._id = id;
    }

    public String getSerie()
    {
        return _serie;
    }

    public int getQuantAlunos()
    {
        return this.alunos.size();
    }

    public boolean alunoMatriculado(int id)
    {
        return alunos.containsKey(id);
    }

    public Aluno getAluno(int id) throws IndexOutOfBoundsException
    {
        if (alunoMatriculado(id))
            return alunos.get(id);

        throw new IndexOutOfBoundsException("Aluno com ID(" + id + ") não encontrado!");
    }

    public void addAluno(Aluno aluno)
    {
        if (!this.alunoMatriculado(aluno.getId()))
        {
            this.alunos.put(aluno.getId(), aluno);
        }
    }

    public void removerAluno(int id)
    {
        if (this.alunoMatriculado(id))
        {
            this.alunos.remove(id);
        }
    }

    public Collection<Aluno> getAlunosCollection()
    {
        return alunos.values();
    }

    protected void obterDados() throws SQLException
    {
        PreparedStatement pstmt = Principal.dbConnection.prepareStatement("SELECT * FROM turmas WHERE id = ?");
        pstmt.setInt(1, this.getId());

        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            this._serie = rs.getString("serie");

            PreparedStatement stmtAlunos = Principal.dbConnection.prepareStatement("SELECT * FROM alunos WHERE turma_id = ?");
            stmtAlunos.setInt(1, this.getId());

            ResultSet rsAluno = stmtAlunos.executeQuery();
            if (rsAluno.next())
            {
                Aluno aluno = new Aluno(rsAluno.getInt("id"),
                        rsAluno.getString("nome"),
                        rsAluno.getString("endereco"));

                alunos.put(aluno.getId(), aluno);
            }
        }
    }

    protected void populaDb() throws SQLException
    {
        // salvar alunos
        PreparedStatement pstmt = Principal.dbConnection.prepareStatement("INSERT INTO alunos(nome, endereco, turma_id) VALUES(?, ?, ?)");

        for (Map.Entry<Integer, Aluno> e : alunos.entrySet())
        {
            pstmt.setString(1, e.getValue().getNome());
            pstmt.setString(2, e.getValue().getEndereco());
            pstmt.setInt(3, this.getId());
            pstmt.executeUpdate();
        }
    }

    public boolean removerDb() throws SQLException
    {
        if (this.getId() == -1 || !Principal.dbConnected) return false;

        PreparedStatement pstmt = Principal.dbConnection.prepareStatement("DELETE FROM turmas WHERE id = ?");
        pstmt.setInt(1, this.getId());

        return pstmt.executeUpdate() > 0;
    }
}
