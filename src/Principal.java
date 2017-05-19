import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static final String DB_URL = "jdbc:sqlite:escola.db";
	public static Connection dbConnection;
	public static boolean dbConnected = true;

	public static void main(String[] args)
	{
		// configurar conexão com banco de dados
		try {
			dbConnection = DriverManager.getConnection(DB_URL);

            System.out.println("Conectado a base de dados!");
        } catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnected = false;
		}

		ArrayList<Escola> escolas = new ArrayList<>();

		// obter dados se conectado
		if (dbConnected)
        {
            escolas = obterEscolas();
        }

        Scanner scn = new Scanner(System.in);

		menu:
		while (true)
        {
            System.out.println("Selecione uma opção:");
            System.out.println(" - 1: Listar Escolas");
            System.out.println(" - 2: Listar Professores");
            System.out.println(" - 3: Listar Turmas");
            System.out.println(" - 4: Listar Alunos");
            System.out.println(" - 5: Sair");

            switch (scn.nextInt())
            {
                case 1:
                    listarEscolas(escolas);
                    break;
                case 2:
                    listarProfessores(escolas);
                    break;
                case 3:
                    listarTurmas();
                    break;
                case 4:
                    listarAlunos();
                    break;
                case 5:
                default:
                    break menu;
            }
        }
	}

	private static void listarEscolas(ArrayList<Escola> escolas)
    {
        System.out.println("ID | Nome        | Endereço | # Professores | # Turmas | # Alunos");
        for (Escola e : escolas)
        {
            System.out.printf("%d | %s | %s | %d | %d | %d\n",
                    e.getId(),
                    e.getNome(),
                    e.getEndereco(),
                    e.getQuantProfessores(),
                    e.getQuantProfessores(),
                    e.getQuantAlunos()
                    );
        }
    }

	private static void listarProfessores(ArrayList<Escola> escolas)
    {
        System.out.println("ID | Nome     |  Endereço  | Salário | Escola");
        for (Escola e : escolas)
        {
            for (Professor prof : e.getProfessores())
            {
                System.out.printf("%d | %s | %s | %.2f | %d\n",
                        prof.getId(),
                        prof.getNome(),
                        prof.getEndereco(),
                        prof.getSalario(),
                        prof.getEscolaId()
                );
            }
        }
    }

	private static void listarTurmas()
    {

    }

	private static void listarAlunos()
    {

    }

	private static ArrayList<Escola> obterEscolas()
	{
		ArrayList<Escola> escolas = new ArrayList<>();

		try {
			PreparedStatement pstmt = Principal.dbConnection.prepareStatement("SELECT * FROM escolas");
			pstmt.execute();

			ResultSet rs = pstmt.getResultSet();

			while (rs.next())
			{
				Escola escola = new Escola(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"));
                escola.obterDados();

				escolas.add(escola);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return escolas;
	}
}
