package Uno.Entidades.Baralhos.Cartas.Enum;

public enum ValoresC {
    AS("Ás"), DOIS("2"), TRES("3"), QUATRO("4"), CINCO("5"),
    SEIS("6"), SETE("7"), OITO("8"), NOVE("9"),
    VALETE("J"), RAINHA("Q"), REI("K"), PRETO("Preto"), VERMELHO("Vermelho");

    private final String valor;
    ValoresC(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }
}
