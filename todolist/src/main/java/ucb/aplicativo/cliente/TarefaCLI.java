package ucb.aplicativo.cliente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ucb.aplicativo.control.TarefaServico;
import ucb.aplicativo.model.Tarefa;

public class TarefaCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TarefaServico servico = new TarefaServico();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        System.out.println("TO DO LIST: Aplicativo de Tarefas (CLI)\n");
        int opcao;
        do {
            menu();
            opcao = lerInt("Escolha: ");
            switch (opcao) {
                case 1 -> criar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void menu() {
        System.out.println("\n1 - Criar nova tarefa");
        System.out.println("2 - Listar todas as tarefas");
        System.out.println("3 - Atualizar uma tarefa");
        System.out.println("4 - Apagar uma tarefa");
        System.out.println("0 - Sair");
    }

    private static void criar() {
        System.out.println("\n--- Criar Tarefa ---");
        String titulo = lerLinha("Título: ");
        String descricao = lerLinha("Descrição: ");
        LocalDateTime dataHora = lerDataHora("Data/Hora (dd/MM/yyyy HH:mm): ");
        boolean concluida = lerBoolean("Concluída? (s/n): ");
        Tarefa t = servico.criar(titulo, descricao, dataHora, concluida);
        System.out.println("Criada: " + t);
    }

    private static void listar() {
        System.out.println("\n--- Lista de Tarefas ---");
        if (servico.listar().isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        for (Tarefa t : servico.listar()) {
            System.out.println(t);
        }
    }

    private static void atualizar() {
        System.out.println("\n--- Atualizar Tarefa ---");
        long id = lerLong("ID da tarefa: ");
        String titulo = lerLinhaOpcional("Novo título (ENTER para manter): ");
        String descricao = lerLinhaOpcional("Nova descrição (ENTER para manter): ");
        LocalDateTime dataHora = lerDataHoraOpcional("Nova data/hora (dd/MM/yyyy HH:mm ou ENTER): ");
        Boolean concluida = lerBooleanOpcional("Marcar como concluída? (s/n ou ENTER p/ manter): ");
        boolean ok = servico.atualizar(id, vazioParaNull(titulo), vazioParaNull(descricao), dataHora, concluida);
        System.out.println(ok ? "Atualizada com sucesso." : "ID não encontrado.");
    }

    private static void remover() {
        System.out.println("\n--- Remover Tarefa ---");
        long id = lerLong("ID da tarefa: ");
        boolean ok = servico.remover(id);
        System.out.println(ok ? "Removida com sucesso." : "ID não encontrado.");
    }

    // ------- helpers de entrada -------
    private static String lerLinha(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String lerLinhaOpcional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int lerInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }
    }

    private static long lerLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                return Long.parseLong(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }
    }

    private static LocalDateTime lerDataHora(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                return LocalDateTime.parse(s.trim(), FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use dd/MM/yyyy HH:mm.");
            }
        }
    }

    private static LocalDateTime lerDataHoraOpcional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) return null;
        try {
            return LocalDateTime.parse(s, FMT);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido ignorado. Mantendo anterior.");
            return null;
        }
    }

    private static boolean lerBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().toLowerCase();
            if (s.equals("s") || s.equals("sim")) return true;
            if (s.equals("n") || s.equals("nao") || s.equals("não")) return false;
            System.out.println("Responda s/n.");
        }
    }

    private static Boolean lerBooleanOpcional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim().toLowerCase();
        if (s.isEmpty()) return null;
        if (s.equals("s") || s.equals("sim")) return true;
        if (s.equals("n") || s.equals("nao") || s.equals("não")) return false;
        System.out.println("Entrada inválida. Mantendo anterior.");
        return null;
    }

    private static String vazioParaNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}
