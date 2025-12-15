package sistemachamados.service;

import sistemachamados.model.StatusChamado;
import sistemachamados.model.Chamado;
import java.util.ArrayList;
import java.util.List;

public class ChamadoService {
    private List<Chamado> chamados;
    private int proximoId;

    public ChamadoService() {
        this.chamados = new ArrayList<>();
        this.proximoId = 1;  // Começa do #001
    }

    public void abrirChamado(String area, String descricao) {
        Chamado novoChamado = new Chamado(proximoId, area, descricao);
        chamados.add(novoChamado);
        System.out.println("✓ Chamado #" + proximoId + " aberto com sucesso!");
        proximoId++;  // Próximo será #002, #003...
    }

    public List<Chamado> listarTodos() {
        return chamados;
    }

    public List<Chamado> listarPorStatus(StatusChamado status) {
        List<Chamado> filtrados = new ArrayList<>();

        for (Chamado c : chamados) {
            if (c.getStatus() == status) {
                filtrados.add(c);
            }
        }

        return filtrados;
    }

    public void finalizarChamado(int id) {
        for (Chamado c : chamados) {
            if (c.getId() == id) {
                c.setStatus(StatusChamado.FINALIZADO);
                System.out.println("✓ Chamado #" + id + " finalizado!");
                return;
            }
        }
        System.out.println("✗ Chamado não encontrado!");
    }
}