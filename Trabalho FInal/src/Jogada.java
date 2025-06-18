public enum Jogada { // usando enum para facilitar e deixar o código mais ""limpo""
    PEDRA,
    PAPEL,
    TESOURA;

    public static Jogada fromString(String texto) { // usando fromstring para fazer a comparação
        for (Jogada j : Jogada.values()) { // percorrendo pelas jogas
            if (j.name().equalsIgnoreCase(texto)) { // comparando para retornar a jogada
                return j;
            }
        }
        throw new IllegalArgumentException("Jogada inválida: " + texto); // exception
    }
}
