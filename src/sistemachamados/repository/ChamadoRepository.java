package sistemachamados.repository;

import sistemachamados.model.Chamado;
import sistemachamados.model.StatusChamado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChamadoRepository {

    private static final String ARQUIVO = "chamados.txt";

    public void salvar(List<Chamado> chamados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {

            for (Chamado c : chamados) {
                writer.write(
                        c.getId() + ";" +
                                c.getArea() + ";" +
                                c.getDescricao() + ";" +
                                c.getStatus()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar chamados: " + e.getMessage());
        }
    }

    public List<Chamado> carregar() {
        List<Chamado> chamados = new ArrayList<>();

        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return chamados;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0]);
                String area = partes[1];
                String descricao = partes[2];
                StatusChamado status = StatusChamado.valueOf(partes[3]);

                Chamado chamado = new Chamado(id, area, descricao);
                chamado.setStatus(status);

                chamados.add(chamado);
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar chamados: " + e.getMessage());
        }

        return chamados;
    }
}
