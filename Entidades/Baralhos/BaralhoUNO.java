package Uno.Entidades.Baralhos;
import Uno.Entidades.Baralhos.Cartas.*;
import Uno.Entidades.Baralhos.Cartas.Enum.NaipesU;
import Uno.Entidades.Baralhos.Cartas.Enum.ValoresU;

public class BaralhoUNO extends Baralho {
    @Override
    public void criarBaralho() {
         for(NaipesU naipe : NaipesU.values()) {
             if(naipe != NaipesU.WILD) {
                 for(ValoresU valor : ValoresU.values()) {
                     if(valor != ValoresU.WILD && valor != ValoresU.MAISQUATRO) {
                         Carta carta = new Carta(valor.getValor(), naipe.getNaipeU());
                         cartas.push(carta);
                     }
                 }
             }
         }
         cartas.push(new Carta(ValoresU.WILD.getValor(), NaipesU.WILD.getNaipeU()));
         cartas.push(new Carta(ValoresU.MAISQUATRO.getValor(), NaipesU.WILD.getNaipeU()));
    }

}
