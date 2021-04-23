import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import genetico.Individuo;
import genetico.Pais;
import genetico.Populacao;
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

		System.out.println();

		// int qtdItens = 4;
		// Item item1 = new Item("taco", 1, 10);
		// Item item2 = new Item("bola", 2, 20);
		// Item item3 = new Item("raquete", 3, 30);
		// Item item4 = new Item("robô", 4, 40);
		// ArrayList<Item> itens = new ArrayList<>(4);
		// itens.add(item1);
		// itens.add(item2);
		// itens.add(item3);
		// itens.add(item4);

		// cria os indivíduos
		Individuo ind = new Individuo(qtdItens);
		ArrayList<Individuo> individuos = ind.criaIndividuos(qtdItens);

		// pergunta a usuário tamanho da população
		int tamPop;
		do {
			try {
				System.out.println("Qual o tamanho da população?");
				System.out.print("Resposta: ");
				tamPop = scan.nextInt();
				scan.nextLine();
				if (tamPop <= 1)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				tamPop = 0;
				scan.nextLine();
			}
		} while (tamPop <= 1);

		System.out.println();

		Populacao populacao = new Populacao(tamPop);

		// cria a população com os indivíduos
		populacao.criaPopulacao(individuos, tamPop);

		// calcula fitness dos indivíduos da população
		populacao.calculaFitness(itens, capMochila, 150);

		// mostra os indivíduos da população após calculo do fitness
		populacao.mostraIndividuosDaPopulacao();

		System.out.println();

		// roda a roleta para selecionar os pais
		ArrayList<Individuo> pais = populacao.roleta();

		System.out.println();

		// faz reprodução e retorna arraylist para passar para população
		ArrayList<Individuo> filhos = Pais.reproduz(pais, tamPop);

		System.out.println();

		int qtdMutacoes = 0;
		if (tamPop < 100) {
			qtdMutacoes = 1;
		} else {
			qtdMutacoes = (int) (tamPop * 0.02);
		}

		// faz mutações
		filhos = populacao.mutacoes(filhos, qtdMutacoes);

		ArrayList<Individuo> novosIndividuos = new ArrayList<>(tamPop);
		novosIndividuos.addAll(pais);
		novosIndividuos.addAll(filhos);

		populacao.setIndividuos(novosIndividuos);

		System.out.println();

		// System.out.println("Após fazer crossover e mutação");
		// populacao.mostraIndividuosDaPopulacao();

		/*
		 * passa por novas populações, critério de parada é número de populações passado
		 * pelo usuário
		 */
		int numPopulacoes = 0;
		do {
			try {
				System.out.println("Quantas populações você terá até alcançar o resultado desejado?");
				System.out.println("Observação = Uma população obrigatóriamente já passou por crossover e mutação");
				System.out.print("Resposta: ");
				numPopulacoes = scan.nextInt();
				scan.nextLine();
				if (numPopulacoes < 1)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				numPopulacoes = 0;
				scan.nextLine();
			}
		} while (numPopulacoes < 1);

		System.out.println();

		int totalPopulacoes = numPopulacoes + 1;

		for (int contPop = 1; contPop <= numPopulacoes; contPop++) {
			populacao.calculaFitness(itens, capMochila, 150);
			// limpa arraylist de pais antes de descobrir os novos pais
			pais.clear();
			pais = populacao.roleta();
			// limpa arraylist de filhos antes de descobrir os novos filhos
			filhos.clear();
			filhos = Pais.reproduz(pais, tamPop);
			// mutações
			filhos = populacao.mutacoes(filhos, qtdMutacoes);
			novosIndividuos.clear();
			novosIndividuos.addAll(pais);
			novosIndividuos.addAll(filhos);
			populacao.setIndividuos(novosIndividuos);
		}

		System.out.println();

		System.out.println("Total de populações do programa: " + totalPopulacoes);

		System.out.println();

		System.out.println("Abaixo estão os indivíduos selecionados na última população");
		populacao.mostraIndividuosDaPopulacao();

		System.out.println("\n\nFim do programa");

	}
}
