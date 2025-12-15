package sistemachamados.service;

import sistemachamados.model.StatusChamado;
import sistemachamados.model.Chamado;
import sistemachamados.repository.ChamadoRepository;
import java.util.ArrayList;
import java.util.List;

public class ChamadoService {
    private List<Chamado> chamados;
    private ChamadoRepository repository;
    private int proximoId;

    public ChamadoService() {
        this.repository = new ChamadoRepository();
        this.chamados = repository.carregar();
        this.proximoId = gerarProximoId();
    }

    private int gerarProximoId() {
        int maiorId = 0;

        for (Chamado c : chamados) {
            if (c.getId() > maiorId) {
                maiorId = c.getId();
            }
        }

        return maiorId + 1;
    }



    public void abrirChamado(String area, String descricao) {
        Chamado novoChamado = new Chamado(proximoId, area, descricao);
        chamados.add(novoChamado);
        repository.salvar(chamados);
        System.out.println("✓ Chamado #" + proximoId + " aberto com sucesso!");
        proximoId++;

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
                repository.salvar(chamados);
                System.out.println("✓ Chamado #" + id + " finalizado!");
                return;
            }
        }
        System.out.println("✗ Chamado não encontrado!");
    }
}