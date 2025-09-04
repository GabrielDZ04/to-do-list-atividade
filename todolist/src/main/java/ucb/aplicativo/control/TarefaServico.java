package ucb.aplicativo.control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ucb.aplicativo.model.Tarefa;

public class TarefaServico {

    private final List<Tarefa> tarefas = new ArrayList<>();

    public Tarefa criar(String titulo, String descricao, LocalDateTime dataHora, boolean concluida) {
        Tarefa t = new Tarefa(titulo, descricao, dataHora, concluida);
        tarefas.add(t);
        return t;
    }

    public List<Tarefa> listar() {
        return new ArrayList<>(tarefas);
    }

    public Optional<Tarefa> buscarPorId(long id) {
        return tarefas.stream().filter(t -> t.getId() == id).findFirst();
    }

    public boolean atualizar(long id, String titulo, String descricao, LocalDateTime dataHora, Boolean concluida) {
        Optional<Tarefa> opt = buscarPorId(id);
        if (opt.isEmpty()) return false;
        Tarefa t = opt.get();
        if (titulo != null) t.setTitulo(titulo);
        if (descricao != null) t.setDescricao(descricao);
        if (dataHora != null) t.setDataHora(dataHora);
        if (concluida != null) t.setConcluida(concluida);
        return true;
    }

    public boolean remover(long id) {
        return tarefas.removeIf(t -> t.getId() == id);
    }
}
