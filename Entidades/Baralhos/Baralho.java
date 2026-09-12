package Uno.Entidades.Baralhos;
import Uno.Entidades.Baralhos.Cartas.Carta;
import java.util.*;

public abstract class Baralho {
    protected Stack<Carta> cartas;

    public Baralho() {
        cartas = new Stack<>();
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public abstract void criarBaralho();

    public ArrayList<Carta> getCartas(int quantidade){
        ArrayList<Carta> coletadas = new ArrayList<>();
        for(int i = 0; i < quantidade; i++){
            coletadas.add(cartas.pop());
        }
        return coletadas;
    }

    public Carta getUmaCarta(){
        return cartas.pop();
    }

    public Carta mostrarCarta(){
        return cartas.peek();
    }

    public int tamanho(){
        return cartas.size();
    }

    public void colocarCarta(Carta carta){
        cartas.push(carta);
    }
}
