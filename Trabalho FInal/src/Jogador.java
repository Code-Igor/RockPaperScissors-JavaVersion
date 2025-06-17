public abstract class Jogador implements Jogavel {
    protected String nome;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método abstrato que será implementado pelas classes concretas
    @Override
    public abstract String fazerJogada();
}