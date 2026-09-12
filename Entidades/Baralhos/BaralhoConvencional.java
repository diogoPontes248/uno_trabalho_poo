package Uno.Entidades.Baralhos;
import Uno.Entidades.Baralhos.Cartas.Carta;
import Uno.Entidades.Baralhos.Cartas.Enum.NaipesC;
import Uno.Entidades.Baralhos.Cartas.Enum.ValoresC;

public class BaralhoConvencional extends Baralho {
    @Override
    public void criarBaralho() {
        for(NaipesC naipe : NaipesC.values()) {
            if(naipe != NaipesC.CORINGA) {
                for(ValoresC valor : ValoresC.values()) {
                    if(valor != ValoresC.PRETO && valor != ValoresC.VERMELHO) {
                        Carta carta = new Carta(valor.getValor(), naipe.getNaipe());
                        cartas.push(carta);
                    }
                }
            }
        }
        cartas.push(new Carta(ValoresC.PRETO.getValor(), NaipesC.CORINGA.getNaipe()));
        cartas.push(new Carta(ValoresC.VERMELHO.getValor(), NaipesC.CORINGA.getNaipe()));
    }

}