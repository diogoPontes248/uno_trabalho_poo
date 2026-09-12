package Uno;

import java.util.Scanner;

public class Uno {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Baralho UNO - 1");
        System.out.println("Baralho Convencional - 0");
        System.out.println("Digite o modo de jogo: ");
        int tipoBaralho = input.nextInt();
        input.nextLine();

        Jogo jogo = new Jogo(tipoBaralho);
        int quantidadeJogadores;

        do{
            System.out.println("Digite a quantidade de jogadores: ");
            quantidadeJogadores = input.nextInt();
            input.nextLine();
        }while(quantidadeJogadores > 4 || quantidadeJogadores < 2);

        for(int i = 0; i < quantidadeJogadores; i++) {
            System.out.println("Digite o nome do jogador: ");
            String nomeJogador = input.nextLine();
            jogo.adiciocionarJogador(nomeJogador);
        }

        jogo.iniciarJogo();

        input.close();
    }
}