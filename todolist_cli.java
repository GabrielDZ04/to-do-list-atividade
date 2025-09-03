import java.util.Scanner;

public class todolist_cli {
    public static void main(String[] args) {
        int resposta = 1;
        System.out.println("TO DO LIST: Aplicativo de Tarefas (modelo CLI/terminal)\n");
        System.out.println("Funcionalidades disponíveis:\n");
        System.out.println("1 - Criar nova tarefa");
        System.out.println("2 - Listar todas as tarefas");
        System.out.println("3 - Atualizar uma tarefa");
        System.out.println("4 - Apagar uma tarefa\n");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Digite o número da funcionalidade que deseja usar: ");
            int func = scanner.nextInt();
            scanner.nextLine();
            switch (func) {
                case 1:
                    TarefaServico.criar_tarefa();
                    break;
                case 2:
                    TarefaServico.listar_tarefa();
                    break;
                case 3:
                    TarefaServico.atualizar_tarefa();
                    break;
                case 4:
                    TarefaServico.remover_tarefa();
                    break;
                default:
                    System.out.println("Funcionalidade inválida. Tente novamente.");
            }
            System.out.println("\nDigite 0 para sair ou qualquer outro número para continuar: ");
            resposta = scanner.nextInt();
            scanner.nextLine();
        } while (resposta != 0);

        scanner.close();
    }

}
