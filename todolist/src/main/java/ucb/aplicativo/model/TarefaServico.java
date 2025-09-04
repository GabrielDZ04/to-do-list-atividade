package ucb.aplicativo.cliente;

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TarefaServico {

    static Tarefa[] tarefas = new Tarefa[100];
    static int contador = 0;

    private static final Scanner scanner = new Scanner(System.in);

    static void criar_tarefa() {

        System.out.println("Digite o título da tarefa (digite [N] para digitar nada): ");
        String titulo = scanner.nextLine();

        System.out.println("Digite a descrição da tarefa (digite [N] para digitar nada): ");
        String descricao = scanner.nextLine();

        System.out.println("Digite a data e hora da tarefa no formato (dd (dia)/MM (mês)/yyyy (ano) HH(hora):mm(minuto), ou [N] para agora: ");
        String dataHoraInput = scanner.nextLine();

        System.out.println("Digite se a tarefa está concluída (sim/não) (digite [N] para digitar nada): ");
        String concluidaInput = scanner.nextLine();
        boolean concluidaResult;
        if (concluidaInput.equalsIgnoreCase("sim")) {
            concluidaResult = true;
        } else if (concluidaInput.equalsIgnoreCase("não")) {
            concluidaResult = false;
        } else {
            concluidaResult = false; 
            
        }


        if (contador >= tarefas.length) {
            System.out.println("Limite máximo de tarefas atingido!");
            return;
        }
        
        Tarefa tarefa;

        if (titulo.equalsIgnoreCase("N")) {
            tarefa = new Tarefa();
        } else if (descricao.equalsIgnoreCase("N")) {
            tarefa = new Tarefa(titulo);
        } else if (dataHoraInput.equalsIgnoreCase("N")) {
            tarefa = new Tarefa(titulo, descricao);
        } else {
            //esta parte da conversão foi realizada com a ajuda do chatGPT
            LocalDateTime dataHora;
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                dataHora = LocalDateTime.parse(dataHoraInput.trim(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/hora inválido! Usando a data/hora atual.");
                dataHora = LocalDateTime.now();
            }
            tarefa = new Tarefa(titulo, descricao, dataHora, concluidaResult);
        }

        tarefas[contador++] = tarefa;

        System.out.println("\nTarefa criada:");
        System.out.println("Seu título: " + tarefa.getTitulo());
        System.out.println("Sua descrição: " + tarefa.getDescricao());
        System.out.println("Sua Data/Hora: " + tarefa.getDataHoraFormatada());
        System.out.println("Se está concluída: " + tarefa.isConcluida());
        System.out.println("Seu Tarefa ID: " + tarefa.getId());


    }

    static void listar_tarefa() {
        

        System.out.println("Digite o ID da tarefa que deseja listar: ");
        long id = scanner.nextLong();
        for (int i = 0; i < contador; i++) {
            if (tarefas[i].getId() == id) {
                System.out.println("\nTarefa encontrada:");
                System.out.println("Seu título: " + tarefas[i].getTitulo());
                System.out.println("Sua descrição: " + tarefas[i].getDescricao());
                System.out.println("Sua Data/Hora: " + tarefas[i].getDataHoraFormatada());
                System.out.println("Se está concluída: " + tarefas[i].isConcluida());
                System.out.println("Seu Tarefa ID: " + tarefas[i].getId());
                return;
            }
        }
    
    }

    static void atualizar_tarefa() {
        

        Tarefa sua_tarefa = null;

        System.out.println("Digite o ID da tarefa que deseja atualizar: ");
        long id = scanner.nextLong();
        scanner.nextLine(); 

        for(int i=0; i<=id; i++){
            if (tarefas[i] != null && tarefas[i].getId() == id) {
                sua_tarefa = tarefas[i];
            }
        }

        if (sua_tarefa == null) {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
            return;
        }

        System.out.println("Título atual: " + sua_tarefa.getTitulo());
        System.out.println("Digite o novo título (ou [N] para manter o atual): ");
        String novo_titulo = scanner.nextLine();
        if (!novo_titulo.equalsIgnoreCase("N")) {
            sua_tarefa.setTitulo(novo_titulo);
        }

        System.out.println("Descrição atual: " + sua_tarefa.getDescricao());
        System.out.println("Digite a nova descrição (ou [N] para manter a atual): ");
        String nova_descricao = scanner.nextLine();
        if (!nova_descricao.equalsIgnoreCase("N")) {
            sua_tarefa.setDescricao(nova_descricao);
        }

        System.out.println("Data/Hora atual: " + sua_tarefa.getDataHoraFormatada());
        System.out.println("Digite a nova data e hora (dd/MM/yyyy HH:mm) (ou [N] para manter a atual): ");
        String nova_dataHoraInput = scanner.nextLine();
        if (!nova_dataHoraInput.equalsIgnoreCase("N")) { //parte também feita com a ajuda do chatGPT
            LocalDateTime nova_dataHora;
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                nova_dataHora = LocalDateTime.parse(nova_dataHoraInput.trim(), fmt);
                sua_tarefa.setDataHora(nova_dataHora);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/hora inválido! Mantendo a data/hora atual.");
            }
        }

        System.out.println("Status atual de conclusão: " + (sua_tarefa.isConcluida() ? "Concluída" : "Não concluída"));
        System.out.println("Digite o novo status de conclusão (sim/não) (ou [N] para manter o atual): ");
        String novo_concluidaInput = scanner.nextLine();
        if (novo_concluidaInput.equalsIgnoreCase("sim")) {
            sua_tarefa.setConcluida(true);
        } else if (novo_concluidaInput.equalsIgnoreCase("não")) {
            sua_tarefa.setConcluida(false);
        }
        System.out.println("Tarefa atualizada com sucesso!");

    }

    static void remover_tarefa() {
        

        System.out.println("Digite o ID da tarefa que deseja remover: ");
        long id = scanner.nextLong();

        for (int i = 0; i < contador; i++) {
            if (tarefas[i].getId() == id) {
                for (int j = i; j < contador - 1; j++) {
                    tarefas[j] = tarefas[j + 1];
                }
                tarefas[--contador] = null;
                System.out.println("Tarefa com ID " + id + " removida com sucesso.");
                return;
            }
        }

        System.out.println("Tarefa com ID " + id + " não encontrada.");
    }

}
