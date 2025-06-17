import java.util.Random;

public class JogadorAdversario extends Jogador {
    private Random random;
    private String[] jogadasValidas = {"PEDRA", "PAPEL", "TESOURA"};

    public JogadorAdversario() {
        super("CPU");
        this.random = new Random();
    }

    @Override
    public String fazerJogada() {
        int indice = random.nextInt(jogadasValidas.length);
        return jogadasValidas[indice];
    }
}