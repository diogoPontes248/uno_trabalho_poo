package Uno.Entidades;

import Uno.Entidades.Baralhos.Baralho;
import Uno.Entidades.Baralhos.Cartas.Carta;

import java.util.ArrayList;

public class Jogador{
    String nome;
    private ArrayList<Carta> cartas;

    public Jogador(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public void pegarMao(Baralho baralho){
        cartas = baralho.getCartas(7);
    }

    public int quantidadeDeCartas(){
        return cartas.size();
    }

    public void imprimirMao(){
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println(i + "- " + cartas.get(i).getValor() + " " + cartas.get(i).getSimbolo());
        }
    }

    public Boolean maoVazia(){
        return cartas.isEmpty();
    }

    public Carta jogarCarta(int indice){
        if(indice < 0 || indice >= cartas.size()){
            return null;
        }
        Carta carta = cartas.get(indice);
        cartas.remove(carta);
        return carta;
    }

    public Carta mostrarCarta(int indice){
        if(indice < 0 || indice >= cartas.size()){
            return new Carta("50", "inexistente");
        }
        return cartas.get(indice);
    }

    public void comprarCarta(Baralho baralho, int quantidade){
        for(int i = 0; i < quantidade; i++){
            cartas.add(baralho.getUmaCarta());
        }
    }

}
