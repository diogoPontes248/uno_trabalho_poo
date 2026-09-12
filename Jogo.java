package Uno;

import Uno.Entidades.Baralhos.Baralho;
import Uno.Entidades.Baralhos.BaralhoConvencional;
import Uno.Entidades.Baralhos.BaralhoUNO;
import Uno.Entidades.Baralhos.Cartas.Carta;
import Uno.Entidades.Jogador;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Jogo {
    private Baralho baralho;
    private ArrayList<Jogador<Baralho>> jogadores;
    private Stack<Carta> mesa;
    private boolean direcao;
    private boolean fimDeJogo;

    public Jogo(int tipoBaralho) {
        if(tipoBaralho == 1){
            setBaralhoUnoOficial();
        }
        else {
            setBaralhoConvencional();
        }
        jogadores = new ArrayList<>();
        mesa = new Stack<>();
        direcao = false;
        fimDeJogo = false;
    }

    public void inciarMesa(){
        Carta teste = baralho.mostrarCarta();
        while(teste.getValor().equals("Pular") || teste.getValor().equals("Inverter") ||
                teste.getValor().equals("+2") || teste.getValor().equals("Wild") || teste.getValor().equals("Wild +4")
                || teste.getValor().equals("J") || teste.getValor().equals("Q") ||
                teste.getValor().equals("K") || teste.getValor().equals("Preto") || teste.getValor().equals("Vermelho")){
            baralho.embaralhar();
            teste = baralho.mostrarCarta();
        }
        mesa.push(baralho.getUmaCarta());
    }
    public void setBaralhoUnoOficial() {
        baralho = new BaralhoUNO();
        baralho.criarBaralho();
        baralho.embaralhar();
    }
    public void setBaralhoConvencional(){
        baralho = new BaralhoConvencional();
        baralho.criarBaralho();
        baralho.embaralhar();
    }
    public void adiciocionarJogador(String nome){
        jogadores.add(new Jogador<>(nome));
    }
    public void distribuirCartas(){
        for(Jogador<Baralho> jogador : jogadores){
            jogador.pegarMao(baralho);
        }
    }
    public boolean jogadaValida(Carta atual, Carta doJogador){
        return atual.getValor().equals(doJogador.getValor()) || atual.getSimbolo().equals(doJogador.getSimbolo())
                || doJogador.getSimbolo().equals("Wild") || doJogador.getSimbolo().equals("Coringa");
    }
    public boolean getFimDeJogo(){
        return fimDeJogo;
    }
    public void fimDeJogo(){
        fimDeJogo = true;
    }
    public boolean maoVazia(int indice){
        return jogadores.get(indice).maoVazia();
    }
    public int habilidadeDirecao(int indice){
        if(direcao){
            indice = indice + jogadores.size() - 2;
        }
        return indice;
    }
    public int habilidadePular(int indice){
        if(mesa.peek().getValor().equals("Pular") || mesa.peek().getValor().equals("J")){
            indice++;
        }
        return indice;
    }
    public void habilidadeCoringa(){
        Scanner sc = new Scanner(System.in);
        if(mesa.peek().getSimbolo().equals("Wild")){
            System.out.println("-Vermelho");
            System.out.println("-Amarelo");
            System.out.println("-Verde");
            System.out.println("-Azul");
            System.out.println("Digite uma cor: ");
            mesa.peek().setSimbolo(sc.nextLine());
        }
        if(mesa.peek().getSimbolo().equals("Coringa")){
            System.out.println("-Ouros");
            System.out.println("-Copas");
            System.out.println("-Espadas");
            System.out.println("-Paus");
            System.out.println("Digite uma cor: ");
            mesa.peek().setSimbolo(sc.nextLine());
        }
    }
    public void habilidadeInverter(){
        if(mesa.peek().getValor().equals("Inverter") || mesa.peek().getValor().equals("Q")){
            direcao = !direcao;
        }
    }
    public void verificarHabilidadeDeCompra(int indice){
        if(mesa.peek().getValor().equals("+2") || mesa.peek().getValor().equals("K")){
            jogadores.get(indice).comprarCarta(baralho, 2);
        }
        if(mesa.peek().getValor().equals("Wild +4") || mesa.peek().getValor().equals("Vermelho")){
            jogadores.get(indice).comprarCarta(baralho, 4);
        }
    }
    public void rodada(int indice) {
        System.out.println("\nRodada do Jogador " + jogadores.get(indice).getNome());
        System.out.println("\nCarta da mesa: " + mesa.peek().getValor() + " " + mesa.peek().getSimbolo());
        System.out.println("Cartas disponíveis: \n");
        jogadores.get(indice).imprimirMao();
        System.out.println(jogadores.get(indice).quantidadeDeCartas() + "- Comprar uma carta");
        Scanner sc = new Scanner(System.in);
        Carta carta;
        int indiceCarta;
        do {
            System.out.println("\nEscolha um carta: ");
            indiceCarta = sc.nextInt();
            if (indiceCarta == jogadores.get(indice).quantidadeDeCartas()) {
                jogadores.get(indice).comprarCarta(baralho, 1);
            } else if (jogadaValida(mesa.peek(), jogadores.get(indice).mostrarCarta(indiceCarta))) {
                carta = jogadores.get(indice).jogarCarta(indiceCarta);
                baralho.colocarCarta(mesa.pop());
                baralho.embaralhar();
                mesa.push(carta);
            } else {
                System.out.println("Esta carta não existe ou não é válida!!");
                indiceCarta = -3;
            }
        } while (indiceCarta < 0 || indiceCarta > jogadores.get(indice).quantidadeDeCartas());
    }
    public void imprimeVencedor(int indice) {
        System.out.println("O vencedor é: " + jogadores.get(indice).getNome() + "!!!");
    }


}
