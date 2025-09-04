import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class todolist {
    private static long nextId = 1;

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora; 
    private boolean concluida;
    private final long id;

    public todolist(String titulo, String descricao, LocalDateTime dataHora, boolean concluida) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.concluida = concluida;
        this.id = nextId++;
    }


    public todolist(String titulo, String descricao, LocalDateTime dataHora) {
        this(titulo, descricao, dataHora, false);
    }

    public todolist(String titulo, String descricao) {
        this(titulo, descricao, LocalDateTime.now()); 
    }

    public todolist(String titulo) {
        this(titulo, "Sem descricao");
    }

    public todolist() {
        this("Sem titulo");
    }

    // --- Getters ---
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public boolean isConcluida() { return concluida; }
    public long getId() { return id; }

    public String getDataHoraFormatada() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/AAAA HH:mm");
        return dataHora.format(fmt);
    }

    // --- Setters ---
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}