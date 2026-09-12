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
    private ArrayList<Jogador> jogadores;
    private Stack<Carta> mesa;
    private String naipeAtual;
    private String valorAtual;
    private boolean direcao;
    private boolean fimDeJogo;

    public Jogo(int tipoBaralho) {
        if(tipoBaralho == 1){
            baralho = new BaralhoUNO();
            baralho.criarBaralho();
        }
        else {
            baralho = new BaralhoConvencional();
            baralho.criarBaralho();
        }
        jogadores = new ArrayList<>();
        mesa = new Stack<>();
        direcao = false;
        fimDeJogo = false;
    }

    public void iniciarMesa(){
        Carta teste = baralho.mostrarCarta();
        while(teste.getValor().equals("Pular") || teste.getValor().equals("Inverter") ||
                teste.getValor().equals("+2") || teste.getValor().equals("Wild") || teste.getValor().equals("Wild +4")
                || teste.getValor().equals("J") || teste.getValor().equals("Q") ||
                teste.getValor().equals("K") || teste.getValor().equals("Preto") || teste.getValor().equals("Vermelho")){
            baralho.embaralhar();
            teste = baralho.mostrarCarta();
        }
        teste = baralho.getUmaCarta();
        naipeAtual = teste.getSimbolo();
        valorAtual = teste.getValor();
        mesa.push(teste);
    }

    public void adiciocionarJogador(String nome){
        jogadores.add(new Jogador(nome));
    }

    public void distribuirCartas(){
        for(Jogador jogador : jogadores){
            jogador.pegarMao(baralho);
        }
    }

    public boolean jogadaValida(Carta doJogador){
        return valorAtual.equals(doJogador.getValor()) || naipeAtual.equals(doJogador.getSimbolo())
                || doJogador.getSimbolo().equals("Wild") || doJogador.getSimbolo().equals("Coringa");
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
        if(valorAtual.equals("Pular") || valorAtual.equals("J")){
            indice++;
        }
        return indice;
    }

    public void habilidadeCoringa(){
        Scanner sc = new Scanner(System.in);
        String naipe;
        if(naipeAtual.equals("Wild")){
            System.out.println("-Vermelho");
            System.out.println("-Amarelo");
            System.out.println("-Verde");
            System.out.println("-Azul");
            System.out.println("Digite uma cor: ");
            naipe = sc.nextLine();
            naipeAtual = naipe;
        }
        if(naipeAtual.equals("Coringa")){
            System.out.println("-Ouros");
            System.out.println("-Copas");
            System.out.println("-Espadas");
            System.out.println("-Paus");
            System.out.println("Digite uma cor: ");
            naipe = sc.nextLine();
            naipeAtual = naipe;
        }
    }

    public void habilidadeInverter(){
        if(valorAtual.equals("Inverter") || valorAtual.equals("Q")){
            direcao = !direcao;
        }
    }

    public void verificarHabilidadeDeCompra(int indice){
        if(valorAtual.equals("+2") || valorAtual.equals("K")){
            jogadores.get(indice).comprarCarta(baralho, 2);
        }
        if(valorAtual.equals("Wild +4") || valorAtual.equals("Vermelho")){
            jogadores.get(indice).comprarCarta(baralho, 4);
        }
    }

    public void rodada(int indice) {
        System.out.println("\nRodada do Jogador " + jogadores.get(indice).getNome());
        System.out.println("\nCarta da mesa: " + valorAtual + " " + naipeAtual);
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
            } else if (jogadaValida(jogadores.get(indice).mostrarCarta(indiceCarta))) {
                carta = jogadores.get(indice).jogarCarta(indiceCarta);
                naipeAtual = carta.getSimbolo();
                valorAtual = carta.getValor();
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

    public void iniciarJogo(){
        iniciarMesa();
        distribuirCartas();

        for(int i = 0; !fimDeJogo; i++){
            if(baralho.tamanho() < 4){
                while(!mesa.empty()){
                    baralho.colocarCarta(mesa.pop());
                }
                baralho.embaralhar();
            }

            i = i % jogadores.size();

            verificarHabilidadeDeCompra(i);

            rodada(i);

            if(maoVazia(i)){
                imprimeVencedor(i);
                fimDeJogo = true;
            }

            habilidadeCoringa();
            habilidadeInverter();
            i = habilidadeDirecao(i);
            i = habilidadePular(i);
        }
    }
}
