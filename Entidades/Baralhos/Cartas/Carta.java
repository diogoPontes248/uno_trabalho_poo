package Uno.Entidades.Baralhos.Cartas;

public class Carta {
    String valor;
    String simbolo; //naipe ou cores do uno

    public Carta(String valor, String simbolo) {
        this.valor = valor;
        this.simbolo = simbolo;
    }

    public String getValor() {
        return valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

}
