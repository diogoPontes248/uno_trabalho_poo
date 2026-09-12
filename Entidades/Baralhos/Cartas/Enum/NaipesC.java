package Uno.Entidades.Baralhos.Cartas.Enum;

public enum NaipesC {
    COPAS("Copas"), OUROS("Ouros"), ESPADAS("Espadas"), PAUS("Paus"), CORINGA("Coringa");

    private final String naipe;
    private NaipesC(String naipe) {
        this.naipe = naipe;
    }

    public String getNaipe() {
        return naipe;
    }
}
