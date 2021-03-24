import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import genetico.Individuo;
import genetico.Populacao;
import item.Item;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Teste de algoritmo evolucion�rio");
		System.out.println();

		// perguntar para usu�rio quantos itens tem dispon�vel
		// int qtdItens;
		// do {
		// try {
		// System.out.println("Quantos itens h� dispon�vel?");
		// System.out.print("Resposta: ");
		// qtdItens = scan.nextInt();
		// scan.nextLine();
		// if (qtdItens <= 0)
		// System.out.println("Op��o inv�lida");
		// } catch (InputMismatchException e) {
		// System.out.println("Op��o inv�lida");
		// qtdItens = 0;
		// scan.nextLine();
		// }
		// } while (qtdItens <= 0);

		// System.out.println();

		/*
		 * cadastro de itens. Pergunta nome, valor e peso. O nome serve para facilitar a
		 * identifica��o do item
		 */
		// Item item = new Item();
		// ArrayList<Item> itens = item.cadastraItens(scan, qtdItens);

		// System.out.println();

		// pede para o usu�rio qual ser� capacidade da mochila
		// int capMochila;
		// do {
		// try {
		// System.out.println("Qual a capacidade da mochila, o peso m�ximo que ela
		// suporta?");
		// System.out.print("Resposta: ");
		// capMochila = scan.nextInt();
		// scan.nextLine();
		// if (capMochila <= 0)
		// System.out.println("Op��o inv�lida");
		// } catch (InputMismatchException e) {
		// System.out.println("Op��o inv�lida");
		// capMochila = 0;
		// scan.nextLine();
		// }
		// } while (capMochila <= 0);

		// System.out.println();

		int qtdItens = 4;
		Item item1 = new Item("taco", 1, 10.0);
		Item item2 = new Item("bola", 2, 20.0);
		Item item3 = new Item("raquete", 3, 30.0);
		Item item4 = new Item("rob�", 4, 40.0);
		ArrayList<Item> itens = new ArrayList<>(4);
		itens.add(item1);
		itens.add(item2);
		itens.add(item3);
		itens.add(item4);

		// cria os indiv�duos
		Individuo ind = new Individuo(qtdItens);
		ArrayList<Individuo> individuos = ind.criaIndividuos(qtdItens, itens);

		// pergunta a usu�rio tamanho da popula��o
		int tamPop;
		do {
			try {
				System.out.println("Qual o tamanho da popula��o?");
				System.out.print("Resposta: ");
				tamPop = scan.nextInt();
				scan.nextLine();
				if (tamPop <= 1)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				tamPop = 0;
				scan.nextLine();
			}
		} while (tamPop <= 1);

		System.out.println();

		Populacao populacao = new Populacao(tamPop);

		// cria a popula��o com os indiv�duos
		populacao.criaPopulacao(individuos, tamPop);
		populacao.mostraIndividuosDaPopulacao();

		System.out.println("\n\nFim do programa");

	}
}
