import java.sql.SQLException;
import java.util.Scanner;

public final class MenuUI {
    private static MenuUI instance = null;
    private Scanner scn;

    private MenuUI()
    {
        scn = new Scanner(System.in);
        scn.useDelimiter("\n\r|\n");
    }

    public static MenuUI getInstance()
    {
        if (instance == null)
            instance = new MenuUI();

        return instance;
    }

    public void run()
    {
        System.out.println("Selecione uma opção:");
        System.out.println(" - 1: Listar Objetos");
        System.out.println(" - 2: Criar Objetos");
        System.out.println(" - 3: Modificar Objetos");
        System.out.println(" - 4: Sair");

        switch (scn.nextInt())
        {
            case 1:
                listar();
                break;
            case 2:
                criar();
                break;
            case 3:
                modificar();
                break;
            case 4:
            default:
                return;
        }

        run();
    }

    private void listar()
    {
        System.out.println("Selecione uma opção:");
        System.out.println(" - 1: Listar Professores");
        System.out.println(" - 2: Listar Turmas");
        System.out.println(" - 3: Listar Alunos");
        System.out.println(" - 4: Retornar");

        switch (scn.nextInt())
        {
            case 1:
                listarProfessores();
                break;
            case 2:
                listarTurmas();
                break;
            case 3:
                listarAlunos();
                break;
            case 4:
            default:
                return;
        }

        listar();
    }

    private void listarProfessores()
    {
        System.out.printf("%-5s | %-27s | %-30s | %-7s\n", "ID", "Nome", "Endereço", "Salário");


        for (Professor prof : Escola.getInstance().getProfessores())
        {
            System.out.printf("%-5d | %-27s | %-30s | %-5.2f\n",
                    prof.getId(),
                    prof.getNome(),
                    prof.getEndereco(),
                    prof.getSalario());
        }
    }

    private void listarTurmas()
    {
        System.out.printf("%-5s | %-67s\n", "ID", "Série");

        for (Turma t : Escola.getInstance().getTurmas())
        {
            System.out.printf("%-5d | %-67s\n",
                    t.getId(),
                    t.getSerie());
        }
    }

    private void listarAlunos()
    {
        System.out.printf("%-5s | %-27s | %-32s | %-5s\n", "ID", "Nome", "Endereço", "ID da Turma");


        for (Turma t : Escola.getInstance().getTurmas())
        {
            for (Aluno a : t.getAlunosCollection())
            {
                System.out.printf("%-5d | %-27s | %-30s | %-5.2f\n",
                        a.getId(),
                        a.getNome(),
                        a.getEndereco(),
                        t.getId());
            }
        }
    }

    private void criar()
    {
        System.out.println("Selecione uma opção:");
        System.out.println(" - 1: Criar Professor");
        System.out.println(" - 2: Criar Turma");
        System.out.println(" - 3: Criar Aluno");
        System.out.println(" - 4: Retornar");

        switch (scn.nextInt())
        {
            case 1:
                criarProfessor();
                break;
            case 2:
                criarTurma();
                break;
            case 3:
                // criarAluno();
            case 4:
            default:
                return;
        }

        criar();
    }

    private void criarTurma()
    {
        System.out.println("Entre com os dados da nova turma...");
        System.out.printf("Entre com a série: ");
        String serie = scn.next();

        try
        {
            Turma t = new Turma(serie);
            Escola.getInstance().cadastrarNovaTurma(t);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void criarProfessor()
    {
        System.out.println("Entre com os dados do novo professor...");
        System.out.printf("Entre com o nome: ");
        String nome = scn.next();
        System.out.printf("Entre com o endereço: ");
        String endereco = scn.next();
        System.out.print("Entre Com Salário: ");
        double salario = scn.nextDouble();

        try
        {
            Professor p = new Professor(nome, endereco, salario);
            Escola.getInstance().cadastrarNovoProfessor(p);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void modificar()
    {
        System.out.println("Selecione uma opção:");
        System.out.println(" - 1: Alterar Turma");
        System.out.println(" - 2: Apagar Turma");
        System.out.println(" - 3: Alterar Professor");
        System.out.println(" - 4: Apagar Professor");
        System.out.println(" - 5: Alterar Aluno");
        System.out.println(" - 6: Apagar Aluno");
        System.out.println(" - 7: Retornar");

        switch (scn.nextInt())
        {
            case 1:
                // alterarTurmaInte();
                break;
            case 2:
                removerTurma();
                break;
            case 3:
                // alterarProfInte();
            case 4:
                // removerProfessor();
            case 5:
                // alterarAlunoInte();
                break;
            case 6:
                removerAluno();
                break;
            case 7:
            default:
                return;
        }

        modificar();
    }

    private void removerTurma()
    {
        System.out.printf("%-5s | %-72s\n", "ID", "Serie");

        for (Turma t : Escola.getInstance().getTurmas())
        {
            System.out.printf("%-5d | %-72s\n", t.getId(), t.getSerie());
        }


        System.out.printf("Digite um ID (-1 Para Voltar): ");

        int id = scn.nextInt();

        if (id == -1) return;

        Escola.getInstance().removerTurma(id);

        removerTurma();
    }

    private void removerProfessor()
    {
        System.out.printf("%-5s | %-30s | %-31s | %-5s\n", "ID", "Nome", "Endereço", "Salário");

        for (Professor p : Escola.getInstance().getProfessores())
        {
            System.out.printf("%-5d | %-30s | %-31s | %-5.2f\n",
                    p.getId(),
                    p.getNome(),
                    p.getEndereco(),
                    p.getSalario());
        }

        System.out.printf("Digite um ID (-1 Para Voltar): ");

        int id = scn.nextInt();

        if (id == -1) return;

        Escola.getInstance().removerProfessor(id);

        removerProfessor();
    }

    private void removerAluno()
    {
        System.out.printf("%-5s | %-30s | %-31s | %-5s\n", "ID", "Nome", "Endereço", "ID da Turma");

        for (Turma t : Escola.getInstance().getTurmas())
        {
            for (Aluno a : t.getAlunosCollection())
            {
                System.out.printf("%-5d | %-30s | %-31s | %-5d\n",
                        a.getId(),
                        a.getNome(),
                        a.getEndereco(),
                        t.getId());
            }
        }

        System.out.printf("Digite um ID (-1 Para Voltar): ");

        int id = scn.nextInt();

        if (id == -1) return;

        Escola.getInstance().removerProfessor(id);

        removerAluno();
    }
}
