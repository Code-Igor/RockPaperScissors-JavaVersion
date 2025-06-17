import java.util.Scanner;

public class JogadorPrincipal extends Jogador {
    private Scanner scanner; // para pegar o input

    public JogadorPrincipal(String nome, Scanner scanner) {
        super(nome);
        this.scanner = scanner;
    }

    @Override
    public String fazerJogada() {
        String jogada;
        while (true) {
            System.out.print(nome + ", escolha sua jogada (Pedra, Papel, Tesoura): ");
            jogada = scanner.nextLine().trim();
            try {
                // valida a jogada usando o enum
                Jogada.fromString(jogada);
                return jogada;
            } catch (IllegalArgumentException e) {
                System.out.println("Jogada inválida. Por favor, tente novamente.");
            }
        }
    }
}
