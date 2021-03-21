import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import genetico.Individuo;
import item.Item;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Teste de algoritmo evolucionário");
		System.out.println();

		// perguntar para usuário quantos itens tem disponível
		int qtdItens;
		do {
			try {
				System.out.println("Quantos itens há disponível?");
				System.out.print("Resposta: ");
				qtdItens = scan.nextInt();
				scan.nextLine();
				if (qtdItens <= 0)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				qtdItens = 0;
				scan.nextLine();
			}
		} while (qtdItens <= 0);

		System.out.println();

		/*
		 * cadastro de itens. Pergunta nome, valor e peso. O nome serve para facilitar a
		 * identificação do item
		 */
		Item item = new Item();
		ArrayList<Item> itens = item.cadastraItens(scan, qtdItens);

		System.out.println();

		// pede para o usuário qual será capacidade da mochila
		int capMochila;
		do {
			try {
				System.out.println("Qual a capacidade da mochila, o peso máximo que ela suporta?");
				System.out.print("Resposta: ");
				capMochila = scan.nextInt();
				scan.nextLine();
				if (capMochila <= 0)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				capMochila = 0;
				scan.nextLine();
			}
		} while (capMochila <= 0);

		// cria os indivíduos
		Individuo ind = new Individuo(qtdItens);
		ArrayList<Individuo> individuos = ind.criaIndividuos(qtdItens, itens);
		ind.mostraIndividuos(individuos, qtdItens);

		System.out.println("\n\nFim do programa");

	}
}
