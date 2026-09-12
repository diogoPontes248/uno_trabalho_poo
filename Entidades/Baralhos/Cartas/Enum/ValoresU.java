package Uno.Entidades.Baralhos.Cartas.Enum;

public enum ValoresU {
    UM("1"), DOIS("2"), TRES("3"), QUATRO("4"), CINCO("5"),
    SEIS("6"), SETE("7"), OITO("8"), NOVE("9"),
    PULAR("Pular"), INVERTER("Inverter"), MAISDOIS("+2"), WILD("Wild"), MAISQUATRO("Wild +4");

    private final String valor;
    ValoresU(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
