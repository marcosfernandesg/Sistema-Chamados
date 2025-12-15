package sistemachamados;

import sistemachamados.model.Chamado;
import sistemachamados.model.StatusChamado;
import sistemachamados.service.ChamadoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChamadoService service = new ChamadoService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   🎫 SISTEMA DE CHAMADOS 🎫       ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("  1 - Abrir chamado");
            System.out.println("  2 - Listar todos os chamados");
            System.out.println("  3 - Listar por status");
            System.out.println("  4 - Finalizar chamado");
            System.out.println("  5 - Sair");
            System.out.println("════════════════════════════════════");
            System.out.print("→ Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        abrirChamado(scanner, service);
                        break;
                    case 2:
                        listarTodos(service);
                        break;
                    case 3:
                        listarPorStatus(scanner, service);
                        break;
                    case 4:
                        finalizarChamado(scanner, service);
                        break;
                    case 5:
                        System.out.println("\n✓ Encerrando sistema...");
                        break;
                    default:
                        System.out.println("\n✗ Opção inválida!");
                }

                if (opcao != 5) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("\n✗ Digite apenas números!");
                scanner.nextLine();
            }

        } while (opcao != 5);

        scanner.close();
    }

    private static void abrirChamado(Scanner scanner, ChamadoService service) {
        System.out.println("\n┌─ ABRIR NOVO CHAMADO ─────────────┐");

        System.out.print("│ Área (TI/RH/Financeiro): ");
        String area = scanner.nextLine();

        System.out.print("│ Descrição do problema: ");
        String descricao = scanner.nextLine();

        service.abrirChamado(area, descricao);
        System.out.println("└──────────────────────────────────┘");
    }

    private static void listarTodos(ChamadoService service) {
        System.out.println("\n┌─ TODOS OS CHAMADOS ──────────────┐");

        if (service.listarTodos().isEmpty()) {
            System.out.println("│ Nenhum chamado cadastrado.");
        } else {
            for (Chamado c : service.listarTodos()) {
                System.out.printf("│ #%03d | %s | %s | %s%n",
                        c.getId(), c.getArea(), c.getStatus(), c.getDescricao());
            }
        }

        System.out.println("└──────────────────────────────────┘");
    }

    private static void listarPorStatus(Scanner scanner, ChamadoService service) {
        System.out.println("\n┌─ FILTRAR POR STATUS ─────────────┐");
        System.out.println("│ 1 - ABERTO");
        System.out.println("│ 2 - PENDENTE");
        System.out.println("│ 3 - FINALIZADO");
        System.out.print("│ Escolha: ");1

        int opcao = Integer.parseInt(scanner.nextLine());
        StatusChamado status = null;

        switch (opcao) {
            case 1: status = StatusChamado.ABERTO; break;
            case 2: status = StatusChamado.PENDENTE; break;
            case 3: status = StatusChamado.FINALIZADO; break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("\nChamados com status: " + status);

        for (Chamado c : service.listarPorStatus(status)) {
            System.out.printf("#%03d | %s | %s%n",
                    c.getId(), c.getArea(), c.getDescricao());
        }

    } // <-- FECHA O MÉTODO listarPorStatus

    private static void finalizarChamado(Scanner scanner, ChamadoService service) {
        System.out.println("\n┌─ FINALIZAR CHAMADO ──────────────┐");
        System.out.print("│ ID do chamado: #");

        int id = Integer.parseInt(scanner.nextLine());
        service.finalizarChamado(id);

        System.out.println("└──────────────────────────────────┘");
    }}