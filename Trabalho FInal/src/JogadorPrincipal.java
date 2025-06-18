import java.util.Scanner;

public class JogadorPrincipal extends Jogador { // herdando
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
            jogada = scanner.nextLine().trim(); // pega a jogada
            try {
                // valida a jogada usando o enum
                Jogada.fromString(jogada);
                return jogada;
            } catch (IllegalArgumentException e) { // se o texto dado pelo jogador não bater com a jogada retorna um erro
                System.out.println("Jogada inválida. Por favor, tente novamente.");
            }
        }
    }
}
