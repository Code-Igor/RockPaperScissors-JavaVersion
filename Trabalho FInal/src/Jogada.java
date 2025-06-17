public enum Jogada {
    PEDRA,
    PAPEL,
    TESOURA;

    public static Jogada fromString(String texto) {
        for (Jogada j : Jogada.values()) {
            if (j.name().equalsIgnoreCase(texto)) {
                return j;
            }
        }
        throw new IllegalArgumentException("Jogada inválida: " + texto);
    }
}
