import java.io.*;
import java.util.*;

public class Jogo {
    private Map<String, Integer> ranking;
    private Scanner scanner;
    private static final String ARQUIVO_RANKING = "ranking.txt";

    public Jogo() {
        this.ranking = new HashMap<>();
        this.scanner = new Scanner(System.in);
        carregarRanking();
    }

    public void iniciarJogo() {
        System.out.println("Bem-vindo ao Pedra, Papel e Tesoura!");

        System.out.print("Digite seu nome de jogador: ");
        String nomeJogador = scanner.nextLine();
        JogadorPrincipal jogadorPrincipal = new JogadorPrincipal(nomeJogador, scanner);
        JogadorAdversario jogadorAdversario = new JogadorAdversario();

        int rodadas = 0;
        int vitoriasJogador = 0;
        int vitoriasCPU = 0;

        while (true) {
            rodadas++;
            System.out.println("\n--- Rodada " + rodadas + " ---");
            Jogador vencedorRodada = jogarRodada(jogadorPrincipal, jogadorAdversario);

            if (vencedorRodada == jogadorPrincipal) {
                System.out.println(jogadorPrincipal.getNome() + " venceu a rodada!");
                vitoriasJogador++;
            } else if (vencedorRodada == jogadorAdversario) {
                System.out.println("CPU venceu a rodada!");
                vitoriasCPU++;
            } else {
                System.out.println("Empate na rodada!");
            }

            System.out.println("Placar atual: " + jogadorPrincipal.getNome() + " " + vitoriasJogador + " x " + vitoriasCPU + " CPU");

            System.out.print("Deseja jogar outra rodada? (sim/nao): ");
            String continuar = scanner.nextLine().trim().toLowerCase();
            if (!continuar.equals("sim")) {
                break;
            }
        }

        System.out.println("\n--- Fim do Jogo ---");
        System.out.println("Resultado Final: " + jogadorPrincipal.getNome() + " " + vitoriasJogador + " x " + vitoriasCPU + " CPU");

        // Atualizar ranking
        ranking.put(nomeJogador, ranking.getOrDefault(nomeJogador, 0) + vitoriasJogador);
        salvarRanking();
        exibirRanking();

        scanner.close();
    }

    // Este método usa polimorfismo, pois recebe dois objetos do tipo Jogador
    private Jogador jogarRodada(Jogador j1, Jogador j2) {
        String jogada1 = j1.fazerJogada().toUpperCase();
        String jogada2 = j2.fazerJogada().toUpperCase();

        System.out.println(j1.getNome() + " jogou: " + jogada1);
        System.out.println(j2.getNome() + " jogou: " + jogada2);

        int resultado = determinarVencedor(jogada1, jogada2);

        if (resultado == 1) { // Jogador 1 vence
            return j1;
        } else if (resultado == 2) { // Jogador 2 vence
            return j2;
        } else { // Empate
            return null;
        }
    }

    /**
     * Determina o vencedor da rodada.
     * @param jogada1 Jogada do primeiro jogador.
     * @param jogada2 Jogada do segundo jogador.
     * @return 1 se jogada1 vence, 2 se jogada2 vence, 0 se empate.
     */
    private int determinarVencedor(String jogada1, String jogada2) {
        if (jogada1.equals(jogada2)) {
            return 0; // Empate
        }

        switch (jogada1) {
            case "PEDRA":
                return (jogada2.equals("TESOURA")) ? 1 : 2;
            case "PAPEL":
                return (jogada2.equals("PEDRA")) ? 1 : 2;
            case "TESOURA":
                return (jogada2.equals("PAPEL")) ? 1 : 2;
            default:
                return 0; // Isso não deveria acontecer com a validação do enum
        }
    }

    // --- Métodos de Manipulação de Arquivos ---

    private void carregarRanking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RANKING))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int pontuacao = Integer.parseInt(partes[1]);
                    ranking.put(nome, pontuacao);
                }
            }
            System.out.println("Ranking carregado com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de ranking não encontrado. Um novo será criado.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o ranking: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato no arquivo de ranking: " + e.getMessage());
        }
    }

    private void salvarRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RANKING))) {
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Ranking salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }

    public void exibirRanking() {
        System.out.println("\n--- Ranking ---");
        if (ranking.isEmpty()) {
            System.out.println("Nenhum jogador no ranking ainda.");
            return;
        }

        // Ordena o ranking por pontuação em ordem decrescente
        ranking.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " vitórias"));
    }
}