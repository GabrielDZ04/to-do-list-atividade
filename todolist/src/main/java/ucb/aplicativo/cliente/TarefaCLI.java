package ucb.aplicativo.cliente;

import java.util.Scanner;

import ucb.aplicativo.control.TarefaServico;

public class TarefaCLI {
    public static void main(String[] args) {
        int resposta;
        TarefaServico tarefaservico = new TarefaServico();

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("TO DO LIST: Aplicativo de Tarefas (modelo CLI/terminal)\n");
            System.out.println("Funcionalidades disponíveis:\n");
            System.out.println("1 - Criar nova tarefa");
            System.out.println("2 - Listar todas as tarefas");
            System.out.println("3 - Atualizar uma tarefa");
            System.out.println("4 - Apagar uma tarefa\n");
            System.out.println("Digite o número da funcionalidade que deseja usar: ");
            int func = scanner.nextInt();
            scanner.nextLine();
            switch (func) {
                case 1:
                    tarefaservico.criar_tarefa();
                    break;
                case 2:
                    tarefaservico.listar_tarefa();
                    break;
                case 3:
                    tarefaservico.atualizar_tarefa();
                    break;
                case 4:
                    tarefaservico.remover_tarefa();
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
