package ucb.aplicativo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private static long NEXT_ID = 1;

    private final long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private boolean concluida;

    public Tarefa(String titulo, String descricao, LocalDateTime dataHora, boolean concluida) {
        this.id = NEXT_ID++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.concluida = concluida;
    }

    public long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public boolean isConcluida() { return concluida; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public String getDataHoraFormatada() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora != null ? dataHora.format(fmt) : "";
    }

    @Override
    public String toString() {
        return String.format("#%d - %s | %s | %s | %s",
            id, titulo, descricao, getDataHoraFormatada(), concluida ? "Concluída" : "Pendente");
    }
}
