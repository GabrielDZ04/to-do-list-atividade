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
}