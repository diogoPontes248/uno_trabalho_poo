package Uno.Entidades.Baralhos.Cartas.Enum;

public enum NaipesU {
    VERMELHO("Vermelho"), AMARELO("Amarelo"), VERDE("Verde"), AZUL("Azul"), WILD("Wild");

    private final String naipeU;
    NaipesU(String naipeU) {
        this.naipeU = naipeU;
    }

    public String getNaipeU() {
        return naipeU;
    }
}
