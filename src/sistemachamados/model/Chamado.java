package sistemachamados.model;

public class Chamado {
    private int id;
    private String area;
    private String descricao;
    private StatusChamado status;  // "ABERTO", "PENDENTE" ou "FINALIZADO"

    public Chamado(int id, String area, String descricao) {
        this.id = id;
        this.area = area;
        this.descricao = descricao;
        this.status = StatusChamado.ABERTO;  // Todo chamado começa aberto
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }
}