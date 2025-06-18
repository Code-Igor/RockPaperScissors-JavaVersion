import java.util.Random;

public class JogadorAdversario extends Jogador {
    private Random random;
    private String[] jogadasValidas = {"PEDRA", "PAPEL", "TESOURA"};

    public JogadorAdversario() {
        super("Adversário");
        this.random = new Random(); // random pras jogadas
    }

    @Override
    public String fazerJogada() {
        int indice = random.nextInt(jogadasValidas.length);// faz a jogada dentro do range
        return jogadasValidas[indice];
    }
}